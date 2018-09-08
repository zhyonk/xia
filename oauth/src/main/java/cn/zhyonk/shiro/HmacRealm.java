package cn.zhyonk.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhyonk.common.utils.JedisUtils;
import cn.zhyonk.common.utils.StringUtils;
import cn.zhyonk.entity.Login;
import cn.zhyonk.entity.RedisLogin;
import cn.zhyonk.jwt.JWT;
import cn.zhyonk.service.IUserService;

public class HmacRealm extends AuthorizingRealm{
    
	@Autowired
    private IUserService userService;//账号服务(持久化服务)
    
    public Class<?> getAuthenticationTokenClass() {
        return RedisLogin.class;//此Realm只支持HmacToken
    }
    
    /**
     *  认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
                                                          throws AuthenticationException {
    	RedisLogin redislogin = (RedisLogin)authenticationToken;
    	String uid = redislogin.getUid();
    	String token = redislogin.getToken();
    	//token不存在
        if(StringUtils.isEmpty(token)) {
        	 throw new AuthenticationException("Token 为空");
        }
        if(StringUtils.isEmpty(uid)){
        	 throw new AuthenticationException("uid 等于空");
        }
        Login login = JWT.unsign(token, Login.class);
        //解密token后的loginId与用户传来的loginId判断是否一致
        if(null == login || !StringUtils.equals(login.getUid(), uid)){
        	 throw new AuthenticationException("解密token后的loginId与用户传来的loginId判断是否一致");
        }
        
        //验证登录时间
        RedisLogin redisLogin = (RedisLogin)JedisUtils.getObject(uid);
        if(null == redisLogin){
        	 throw new AuthenticationException("验证登录时间");
        }
        
        if(!StringUtils.equals(token, redisLogin.getToken())){
        	 throw new AuthenticationException("Token验证失败！！！");
        }
        //系统时间>有效期(说明已经超过有效期)
        if (System.currentTimeMillis() > redisLogin.getRefTime()) {
        	 throw new AuthenticationException("系统时间>有效期(说明已经超过有效期)");
        }
        //重新刷新有效期
        redisLogin = new RedisLogin(uid, token, System.currentTimeMillis() + 60L* 1000L* 30L);
		JedisUtils.setObject(uid , redisLogin, 1567877612);
        // 此处可以添加查询数据库检查账号是否存在、是否被锁定、是否被禁用等等逻辑
        return new SimpleAuthenticationInfo(uid,Boolean.TRUE,getName());
    }
    
    /** 
     * 授权 
     */  
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String uid = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        // 根据客户标识（可以是用户名、app id等等） 查询并设置角色
        Set<String> roles = userService.loadRoles(uid);
        info.setRoles(roles);
      // 根据客户标识（可以是用户名、app id等等） 查询并设置权限
        Set<String> permissions = userService.loadPermissions(uid);
        info.setStringPermissions(permissions);
        return info;  
    }
}

