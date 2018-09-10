package cn.zhyonk.shiro;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;

import cn.zhyonk.common.ResponseVO;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.common.utils.StringUtils;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.entity.enums.LoginResponseCode;
import cn.zhyonk.jwt.JWT;

public class HmacFilter extends AccessControlFilter {
	public static final String DEFAULT_TOKEN = "token";

	/**
	 * 是否放行
	 */
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (null != getSubject(request, response) && getSubject(request, response).isAuthenticated()) {
			return true;// 已经认证过直接放行
		}
		return false;// 转到拒绝访问处理逻辑
	}

	/**
	 * 拒绝处理
	 */
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isHmacSubmission(request)) {// 如果是Hmac鉴权的请求
			// 创建令牌
			AuthenticationToken token = createToken(request, response);
			try {
				Subject subject = getSubject(request, response);
				subject.login(token);// 认证
				return true;// 认证成功，过滤器链继续
			} catch (UnsupportedTokenException e) {// 认证失败，发送401状态并附带异常信息
				response.setCharacterEncoding("utf-8");
				ResponseVO responseVO = LoginResponseCode
						.buildEnumResponseVO(LoginResponseCode.USERID_NOT_UNAUTHORIZED, false);
				responseMessage((HttpServletResponse) response, response.getWriter(), responseVO);

			} catch (ExpiredCredentialsException e) {
				response.setCharacterEncoding("utf-8");
				ResponseVO responseVO = LoginResponseCode
						.buildEnumResponseVO(LoginResponseCode.LOGIN_TOKEN_NOT_NULL, false);
				responseMessage((HttpServletResponse) response, response.getWriter(), responseVO);
			} catch (UnknownAccountException e) {
				response.setCharacterEncoding("utf-8");
				ResponseVO responseVO = LoginResponseCode
						.buildEnumResponseVO(LoginResponseCode.USERID_NOT_NULL, false);
				responseMessage((HttpServletResponse) response, response.getWriter(), responseVO);
			} catch (CredentialsException e) {
				response.setCharacterEncoding("utf-8");
				ResponseVO responseVO = LoginResponseCode
						.buildEnumResponseVO(LoginResponseCode.LOGIN_TIME_EXP, false);
				responseMessage((HttpServletResponse) response, response.getWriter(), responseVO);
			}
		}
		return false;// 打住，访问到此为止
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String token = request.getParameter(DEFAULT_TOKEN);
		Login login = JWT.unsign(token, Login.class);
		Map<String, Object> claims = JWT.getClaims(token);
		Long maxAge = (Long) claims.get("exp");
		return new RedisLogin(login.getUid(), token, maxAge);
	}

	protected boolean isHmacSubmission(ServletRequest request) {
		String token = request.getParameter("token");
		Login login = JWT.unsign(token, Login.class);
		Map<String, Object> claims = JWT.getClaims(token);
		Long maxAge = (Long) claims.get("exp");
		return (request instanceof ServletRequest) && StringUtils.isNotBlank(login.getUid()) && StringUtils.isNotBlank(token)
				&& StringUtils.isNotBlank(String.valueOf(maxAge));
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
