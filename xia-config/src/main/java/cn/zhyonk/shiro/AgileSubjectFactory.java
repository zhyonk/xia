package cn.zhyonk.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

import cn.zhyonk.entity.RedisLogin;

public class AgileSubjectFactory extends DefaultWebSubjectFactory { 

    public Subject createSubject(SubjectContext context) { 
        AuthenticationToken token = context.getAuthenticationToken();
        if((token instanceof RedisLogin)){
            // 当token为HmacToken时， 不创建 session 
            context.setSessionCreationEnabled(false);
        }
        return super.createSubject(context); 
    }
}

