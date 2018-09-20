package cn.zhyonk.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhyonk<br>
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    

    /**
     * 此处注入一个realm
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm){
    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        /*
         * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions
         * 所以需要配合context.setSessionCreationEnabled(false);
         */
        ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        return securityManager;
    }

    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
    	AgileSubjectFactory subjectFactory = new AgileSubjectFactory();
       return subjectFactory;
    }
    
    @Bean
    public DefaultSessionManager sessionManager(){
       DefaultSessionManager sessionManager = new DefaultSessionManager();
       sessionManager.setSessionValidationSchedulerEnabled(false);
       return sessionManager;
    }
}