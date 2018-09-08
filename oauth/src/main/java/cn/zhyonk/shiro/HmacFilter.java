package cn.zhyonk.shiro;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;

import cn.zhyonk.common.ResponseVO;
import cn.zhyonk.common.utils.ResponseData;
import cn.zhyonk.common.utils.StringUtils;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.entity.enums.LoginResponseCode;

public class HmacFilter extends AccessControlFilter{
    public static final String DEFAULT_UID = "uid";
    public static final String DEFAULT_TOKEN = "token";
    public static final String DEFAUL_REFTIME = "refTime";

    /**
     * 是否放行
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, 
                                                        Object mappedValue) throws Exception {
        if (null != getSubject(request, response) 
                && getSubject(request, response).isAuthenticated()) {
            return true;//已经认证过直接放行
        }
        return false;//转到拒绝访问处理逻辑
    }

    /**
     * 拒绝处理
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  
                                                                          throws Exception {
        if(isHmacSubmission(request)){//如果是Hmac鉴权的请求
            //创建令牌
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);//认证
                return true;//认证成功，过滤器链继续
            } catch (AuthenticationException e) {//认证失败，发送401状态并附带异常信息
            	e.printStackTrace();
            	response.setCharacterEncoding("utf-8");
                ResponseVO responseVO = LoginResponseCode.buildEnumResponseVO(LoginResponseCode.RESPONSE_CODE_UNLOGIN_ERROR, false);
                responseMessage((HttpServletResponse)response,response.getWriter(),responseVO);
            }
        }
        return false;//打住，访问到此为止
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String uid = request.getParameter(DEFAULT_UID);
        String token= request.getParameter(DEFAULT_TOKEN);
        long reftime= Long.parseLong(request.getParameter(DEFAUL_REFTIME));
        return new RedisLogin(uid, token, reftime);
    }
    
    protected boolean isHmacSubmission(ServletRequest request) {
        String uid = request.getParameter(DEFAULT_UID);
        String token= request.getParameter(DEFAULT_TOKEN);
        String reftime= request.getParameter(DEFAUL_REFTIME);
        return (request instanceof HttpServletRequest)
                            && StringUtils.isNotBlank(uid)
                            && StringUtils.isNotBlank(token)
                            && StringUtils.isNotBlank(reftime);
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

