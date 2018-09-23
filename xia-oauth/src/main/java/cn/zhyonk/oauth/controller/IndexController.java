package cn.zhyonk.oauth.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zhyonk.annotation.IsLogin;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/")
@Api(value="用户管理")
public class IndexController extends BaseController{

	
	@IsLogin
	@RequestMapping(value="index",method=RequestMethod.POST)
	@ApiOperation(value = "首页")
	public ResponseData login(HttpServletRequest request) {
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("index", "success go to index");
        return responseData;
	}

}
