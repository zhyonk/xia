package cn.zhyonk.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.zhyonk.common.utils.PropertiesUtils;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.controller.BaseController;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.User;
import cn.zhyonk.entity.WechatUser;
import cn.zhyonk.jwt.JWT;
import cn.zhyonk.rpc.api.LocalUserService;
import cn.zhyonk.rpc.api.OauthService;
import cn.zhyonk.rpc.api.UserService;
import cn.zhyonk.wechat.service.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
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
		if (request.getParameter("openId") != null) {
			String url = config.readProperty("vue.url");
			return new ModelAndView("redirect:" + url+"/reservation/"+request.getParameter("openId"));
		}
		String baseUrl = config.readProperty("xia.baseURL");
		baseUrl = baseUrl + "/wechat/getAccessCode";
		String url = wxService.oauth2buildAuthorizationUrl(baseUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		System.out.println(url);
		return new ModelAndView("redirect:" + url);
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
			return new ModelAndView("redirect:" + baseUrl + "/wechat/index?openId=" + wuser.getOpenId());
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

}
