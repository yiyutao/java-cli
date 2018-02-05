package net.yiyutao.shiro;

import net.yiyutao.common.RedisKeyPrefix;
import net.yiyutao.utils.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: masterYI
 * @date: 2017/11/30
 * @time: 11:27
 * Description:shiro基本配置
 */
@Configuration
public class ShiroConfiguration {

    @Bean("shiroSessionDao")
    public ShiroSessionDao shiroSessionDao() {
        return new ShiroSessionDao();
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm, @Qualifier("shiroSessionManager") DefaultWebSessionManager defaultWebSessionManager, @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        defaultWebSecurityManager.setRealm(shiroRealm);
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean("shiroFilter")
    public AbstractShiroFilter shiroFilter(@Qualifier("securityManager") SecurityManager manager) throws Exception {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        return (AbstractShiroFilter) bean.getObject();
    }

    @Bean("simpleCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("YYT_SESSIONSID");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean("shiroSessionManager")
    public DefaultWebSessionManager shiroSessionManager(@Qualifier("simpleCookie") SimpleCookie simpleCookie, @Qualifier("shiroSessionDao") ShiroSessionDao shiroSessionDao) {

        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        //全局会话超时时间。默认30分钟
        shiroSessionManager.setGlobalSessionTimeout(86400000);
        //是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
        shiroSessionManager.setDeleteInvalidSessions(true);
        //会话验证器调度时间
        shiroSessionManager.setSessionValidationInterval(1800000);
        //定时检查失效的sessions
        shiroSessionManager.setSessionValidationSchedulerEnabled(true);
        //启用sessions id cookie,默认为容器的JSESSIONID
        shiroSessionManager.setSessionIdCookieEnabled(true);
        //覆盖容器默认的JSESSIONID
        shiroSessionManager.setSessionIdCookie(simpleCookie);
        //通过url重写不保存session id
        shiroSessionManager.setSessionIdUrlRewritingEnabled(false);
        shiroSessionManager.setSessionDAO(shiroSessionDao);
        return shiroSessionManager;

    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate, @Value("${sessionExpire}") Long expire) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisTemplate(redisTemplate);
        redisCacheManager.setKeyPrefix(RedisKeyPrefix.SHIRO_CACHE_KEY);
        if (expire != null) {
            redisCacheManager.setExpire(expire);
        }
        return redisCacheManager;
    }

    @Bean
    public CustomDefaultFilterChainManager customDefaultFilterChainManager(@Value("${anon}") String anonUrls, @Value("${authc}") String authcUrls) {
        CustomDefaultFilterChainManager customDefaultFilterChainManager = new CustomDefaultFilterChainManager();
        //未登录跳转的URL
        customDefaultFilterChainManager.setLoginUrl("/rest/nologin");
        customDefaultFilterChainManager.setUnauthorizedUrl("/rest/unauthorized");
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        if (StringUtils.isNotBlank(anonUrls)) {
            String[] anonUrlArr = anonUrls.split(",");
            for (String anonUrl : anonUrlArr) {
                filterChainDefinitionMap.put(anonUrl, "anon");
            }
        }
        if (StringUtils.isNotBlank(authcUrls)) {
            String[] authcUrlArr = authcUrls.split(",");
            for (String authcUrl : authcUrlArr) {
                filterChainDefinitionMap.put(authcUrl, "authc");
            }
        }

        customDefaultFilterChainManager.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return customDefaultFilterChainManager;
    }

    @Bean
    public CustomPathMatchingFilterChainResolver customPathMatchingFilterChainResolver(@Qualifier("customDefaultFilterChainManager") CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        CustomPathMatchingFilterChainResolver customPathMatchingFilterChainResolver = new CustomPathMatchingFilterChainResolver();
        customPathMatchingFilterChainResolver.setCustomDefaultFilterChainManager(customDefaultFilterChainManager);
        return customPathMatchingFilterChainResolver;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro动态代理
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(@Qualifier("customPathMatchingFilterChainResolver") CustomPathMatchingFilterChainResolver customPathMatchingFilterChainResolver,
                                                               @Qualifier("shiroFilter") AbstractShiroFilter shiroFilter) {

        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(shiroFilter);
        methodInvokingFactoryBean.setTargetMethod("setFilterChainResolver");
        Object[] arguments = {customPathMatchingFilterChainResolver};
        methodInvokingFactoryBean.setArguments(arguments);
        return methodInvokingFactoryBean;
    }


}
