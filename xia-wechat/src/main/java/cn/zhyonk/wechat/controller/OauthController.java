package cn.zhyonk.wechat.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zhyonk.annotation.IsLogin;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.controller.BaseController;
import cn.zhyonk.rpc.api.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/oauth")
@Api(value="用户管理")
public class OauthController extends BaseController{

	@Autowired
	private OauthService oauthService;
	
	@IsLogin
	@RequestMapping(value="/checkToken")
	@ApiOperation(value = "首页")
	public ResponseData login(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (oauthService.checkToken()) {
			
		}else {
			
		}
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("index", "success go to index");
        return responseData;
	}

}
