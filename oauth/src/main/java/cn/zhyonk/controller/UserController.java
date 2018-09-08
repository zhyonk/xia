package cn.zhyonk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhyonk.annotation.IsLogin;
import cn.zhyonk.common.utils.JedisUtils;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.entity.User;
import cn.zhyonk.jwt.JWT;
import cn.zhyonk.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/user")
@Api(value="用户管理")
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;
	
	
	@IsLogin
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiOperation(value = "用户登录")
	public ResponseData login(HttpServletRequest request, @RequestParam( "loginName") String loginName,
            @RequestParam("password") String password) {
        Login login = new Login();
        login.setLoginName(loginName);
        login.setPassword(password);
        ResponseData responseData = ResponseData.ok();
        //先到数据库验证
        String loginId = userService.checkLogin(login);
        if(null != loginId) {
            User user = userService.getUserByLoginId(loginId);
            login.setUid(loginId);
            long refTime = System.currentTimeMillis()+60L*1000L*50L;;
            //给用户jwt加密生成token
            String token = JWT.sign(login,refTime);
            //封装成对象返回给客户端
            responseData.putDataValue("token", token);
            responseData.putDataValue("uid", 123465);
            RedisLogin rlogin = new RedisLogin();
            rlogin.setRefTime(refTime);
            rlogin.setToken(token);
            rlogin.setUid("123456");
            JedisUtils.setObject("123456", rlogin, 0);
        }
        else{
            responseData =  ResponseData.customerError();
        }   
        return responseData;
	}

	@RequestMapping(value="/test",method=RequestMethod.POST)
	@ApiOperation(value = "用户登录")
	public ResponseData test(HttpServletRequest request) {
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("test status", "success");
        return responseData;
	}
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	@ApiOperation(value = "用户注册")
	public ResponseData regist(HttpServletRequest request) {
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("test status", "success");
        return responseData;
	}
}
