package cn.zhyonk.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.zhyonk.common.utils.PropertiesUtils;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.entity.WechatUser;
import cn.zhyonk.rpc.api.OauthService;
import cn.zhyonk.wechat.service.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@RestController
@RequestMapping(value="/wechat")
@Api(value="首页")
public class IndexController {

	@Autowired
	private OauthService oauthService;
	
	@Autowired
	private WeixinService wxService;
	
	@RequestMapping(value="/index")
	@ApiOperation(value = "首页")
	public ModelAndView loginPost(HttpServletRequest request) {
		if (request.getParameter("openId")!=null) {
			ResponseData responseData = ResponseData.ok();
	        responseData.putDataValue("index", "success go to index");
	        return new ModelAndView(responseData.getMessage());
		}
		PropertiesUtils config = new PropertiesUtils("xia.properties");
		String baseUrl = config.readProperty("xia.baseURL");
		baseUrl = baseUrl+"/wechat/getAccessCode";
		String url = wxService.oauth2buildAuthorizationUrl(baseUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		System.out.println(url);
        return new ModelAndView("redirect:"+ url);
	}
	
	@RequestMapping(value="/getAccessCode")
	@ApiOperation(value = "服务器获取access code")
	public ModelAndView getAccessCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		PropertiesUtils config = new PropertiesUtils("xia.properties");
		String baseUrl = config.readProperty("xia.baseURL");
		try {
            WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxService.oauth2getUserInfo(token, null);
            WechatUser wuser = new WechatUser().cast(wxMpUser);
            return new ModelAndView("redirect:"+baseUrl+"/index?openId=" + wuser.getOpenId());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
		 ResponseData responseData = ResponseData.badRequest();
		return new ModelAndView(responseData.getMessage());
	}

}
