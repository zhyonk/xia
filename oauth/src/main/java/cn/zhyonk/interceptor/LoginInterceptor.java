package cn.zhyonk.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.zhyonk.annotation.IsLogin;
import cn.zhyonk.common.ResponseCode;
import cn.zhyonk.common.ResponseVO;
import cn.zhyonk.common.utils.JedisUtils;
import cn.zhyonk.common.utils.StringUtils;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.entity.enums.LoginResponseCode;
import cn.zhyonk.jwt.JWT;

public class LoginInterceptor implements HandlerInterceptor{
	 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		PrintWriter writer = null;
		HandlerMethod method = null;
		try {
			method = (HandlerMethod) handler;
		} catch (Exception e) {
			writer = response.getWriter();
			ResponseVO responseVO = ResponseCode.buildEnumResponseVO(ResponseCode.REQUEST_URL_NOT_SERVICE, false);
        	responseMessage(response, writer, responseVO);
        	return false;
		}  
//	    IsLogin isLogin = method.getMethodAnnotation(IsLogin.class);
//		if(null == isLogin){
//        	return true;
//        }
		
		response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");
        //token不存在
        if(StringUtils.isEmpty(token)) {
        	
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.LOGIN_TOKEN_NOT_NULL, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        if(StringUtils.isEmpty(uid)){
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.USERID_NOT_NULL, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        	
        Login login = JWT.unsign(token, Login.class);
        //解密token后的loginId与用户传来的loginId判断是否一致
        if(null == login || !StringUtils.equals(login.getUid(), uid)){
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.USERID_NOT_UNAUTHORIZED, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        
        //验证登录时间
        RedisLogin redisLogin = (RedisLogin)JedisUtils.getObject(uid);
        if(null == redisLogin){
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.RESPONSE_CODE_UNLOGIN_ERROR, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        
        if(!StringUtils.equals(token, redisLogin.getToken())){
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.USERID_NOT_UNAUTHORIZED, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        //系统时间>有效期(说明已经超过有效期)
        if (System.currentTimeMillis() > redisLogin.getRefTime()) {
        	writer = response.getWriter();
        	ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.LOGIN_TIME_EXP, false);
        	responseMessage(response, writer, responseVO);
        	return false;
        }
        
        //重新刷新有效期
        redisLogin = new RedisLogin(uid, token, System.currentTimeMillis() + 60L* 1000L* 30L);
		JedisUtils.setObject(uid , redisLogin, 1567877612);
		return true;
	}
 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
 
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseVO responseVO) {
        response.setContentType("application/json; charset=utf-8");  
        JSONObject result = new JSONObject();
        result.put("result", responseVO);
        out.print(result);
        out.flush();
        out.close();
    }
 
}
