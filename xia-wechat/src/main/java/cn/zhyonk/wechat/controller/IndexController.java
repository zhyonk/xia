package cn.zhyonk.wechat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.zhyonk.common.utils.JedisUtils;
import cn.zhyonk.common.utils.PropertiesUtils;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.controller.BaseController;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.entity.WechatUser;
import cn.zhyonk.jwt.JWT;
import cn.zhyonk.rpc.api.LocalUserService;
import cn.zhyonk.rpc.api.OauthService;
import cn.zhyonk.rpc.api.UserService;
import cn.zhyonk.wechat.service.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@RestController
@RequestMapping(value = "/wechat")
@Api(value = "首页")
public class IndexController extends BaseController {

	@Autowired
	private OauthService oauthService;
	@Autowired
	private UserService userService;
	@Autowired
	private WeixinService wxService;

	@Autowired
	private LocalUserService localUserService;

	@RequestMapping(value = "/index")
	@ApiOperation(value = "首页")
	public ModelAndView loginPost(HttpServletRequest request) {
		PropertiesUtils config = new PropertiesUtils("xia.properties");
		if (request.getParameter("openId") != null && request.getParameter("token")!=null) {
			String url = config.readProperty("vue.url");
			return new ModelAndView("redirect:" + url+"/reservation?openid="+request.getParameter("openId")+"&token="+request.getParameter("token"));
		}
		String baseUrl = config.readProperty("xia.baseURL");
		baseUrl = baseUrl + "/wechat/getAccessCode";
		String url = wxService.oauth2buildAuthorizationUrl(baseUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		System.out.println(url);
		return new ModelAndView("redirect:" + url);
	}
	

	@RequestMapping(value = "/setMenuBtn")
	@ApiOperation(value = "重设菜单")
	public ResponseData setMenuBtn(HttpServletRequest request) throws WxErrorException {
		PropertiesUtils config = new PropertiesUtils("xia.properties");
		WxMenu wxMenu = new WxMenu();
		WxMenuButton button = new WxMenuButton();
		button.setName("首页");
		button.setType(WxConsts.MenuButtonType.VIEW);
		String url = config.readProperty("xia.index");
		button.setUrl(url);
		ArrayList<WxMenuButton> list = new ArrayList<WxMenuButton>();
		list.add(button);
		wxMenu.setButtons(list);
		wxService.getMenuService().menuCreate(wxMenu);
		System.out.println("重设菜单成功");
		ResponseData ok = ResponseData.ok();
		ok.putDataValue("info", "重设菜单成功");
		return ok;
	}

	@RequestMapping(value = "/getAccessCode")
	@ApiOperation(value = "服务器获取access code")
	public ModelAndView getAccessCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		PropertiesUtils config = new PropertiesUtils("xia.properties");
		String baseUrl = config.readProperty("xia.baseURL");
		try {
			WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
			WxMpUser wxMpUser = wxService.oauth2getUserInfo(token, null);
			WechatUser wuser = new WechatUser().cast(wxMpUser);
			EntityWrapper<WechatUser> wechatUserWrapper = new EntityWrapper<>();
			wechatUserWrapper.where("openId='" + wuser.getOpenId() + "'");
			int count = localUserService.selectCount(wechatUserWrapper);
			if (count == 1) {
				logger.info("<<openId>>: " + wuser.getOpenId() + " 该openid已经存在，更新中....");
				localUserService.update(wuser, wechatUserWrapper);
			} else {
				logger.info("<<openId>>: " + wuser.getOpenId() + " 该openid不存在，插入中....");
				localUserService.insert(wuser);
			}
			String redisToken = createToken(wuser.getOpenId());
			return new ModelAndView("redirect:" + baseUrl + "/wechat/index?openId=" + wuser.getOpenId()+"&token="+redisToken);
		} catch (WxErrorException e) {
			WxError error = e.getError();
			int errorCode = error.getErrorCode();
			if (errorCode == 40029) {
				String url = config.readProperty("vue.url");
				logger.info("此用户未关注公众号，跳转到关注界面.........");
				return new ModelAndView("redirect:" + url+"/follow");
			}
		}
		ResponseData responseData = ResponseData.badRequest();
		return new ModelAndView(responseData.getMessage());
	}
	//创建token
	private String createToken(String openid) {
		 Login login = new Login();
		 login.setUid(openid);
		 long refTime = System.currentTimeMillis()+60L*1000L*50L;;
		//给用户jwt加密生成token
         String token = JWT.sign(login,refTime);
         //封装成对象返回给客户端
         RedisLogin rlogin = new RedisLogin();
         rlogin.setRefTime(refTime);
         rlogin.setToken(token);
         JedisUtils.setObject(openid, rlogin, 0);
         return token;
	}

	@RequestMapping(value="/getUserInfo")
	@ApiOperation(value = "获取微信用户的信息")
	public ResponseData getUserInfo(HttpServletRequest request,HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		String openid = request.getParameter("openid");
		ResponseData responseData;
		if (openid!=null) {
			WechatUser wechatInfo = localUserService.selectUserInfoByOpenid(openid);
			responseData = ResponseData.ok();
			responseData.putDataValue("userInfo", wechatInfo);
			if (wechatInfo!=null) {
				responseData.putDataValue("wechatInfo", wechatInfo);
			}
			return responseData;
		}
		responseData = ResponseData.unauthorized();
        return responseData;
	}
	
	@RequestMapping(value="/getBannerList")
	@ApiOperation(value = "获取微信用户的信息")
	public ResponseData getBannerList(HttpServletRequest request,HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		String openid = request.getParameter("openid");
		ResponseData responseData;
		if (openid!=null) {
			WechatUser wechatInfo = localUserService.selectUserInfoByOpenid(openid);
			responseData = ResponseData.ok();
			responseData.putDataValue("userInfo", wechatInfo);
			if (wechatInfo!=null) {
				responseData.putDataValue("wechatInfo", wechatInfo);
			}
			return responseData;
		}
		responseData = ResponseData.unauthorized();
        return responseData;
	}

}
