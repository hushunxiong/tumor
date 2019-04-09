package com.wonders.health.tumor.common.shiro;

import com.google.common.collect.Maps;
import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.shiro.cas.MyShiroCasRealm;
import com.wonders.shiro.cas.client.session.SingleSignOutFilter;
import com.wonders.shiro.web.filter.authc.LogoutFilter;
import com.wonders.shiro.web.filter.authc.UserFilter;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);


    @Bean
    public CacheManager getCacheManager() {
        CacheManager manager = null;
        try {
            manager = new CacheManager(ResourceUtils.getInputStreamForPath("classpath:cache/ehcache-local.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @Bean
    public EhCacheManager getEhCacheManager(CacheManager manager) {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(manager);
        return em;
    }

    @Bean(name = "myShiroCasRealm")
    public MyShiroCasRealm myShiroCasRealm(EhCacheManager cacheManager,
                                           AuthServiceI authService,
                                           @Value("${default.casServerUrl") String defaultCasServerUrl,
                                           @Value("${default.sysServerUrl}") String defaultsysServerUrl,
                                           @Value("${casServerUrlPrefix}") String casServerUrlPrefix,
                                           @Value("${shiroServerUrlPrefix}") String shiroServerUrlPrefix,
                                           @Value("${shiroServerUrlSuffix}") String shiroServerUrlSuffix
    ) {
        MyShiroCasRealm realm = new MyShiroCasRealm();
        realm.setCacheManager(cacheManager);
        realm.setCasServerUrlPrefix(casServerUrlPrefix);
        realm.setShiroServerUrlPrefix(shiroServerUrlPrefix);
        realm.setShiroServerUrlSuffix(shiroServerUrlSuffix);
        realm.setDefaultSysServerUrl(defaultsysServerUrl);
        realm.setDefaultCasServerUrl(defaultCasServerUrl);
        realm.setAuthService(authService);
        realm.setSystemName("ZNZFX");
        return realm;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean authenticationFilterRegistration(@Value("${casServerUrlPrefix}") String casServerUrlPrefix,
                                                                   @Value("${shiroServerUrlPrefix}") String shiroServerUrlPrefix) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("");
        registration.setName("AuthenticationFilter");
        registration.addInitParameter("casServerLoginUrl", casServerUrlPrefix + "/login");
        registration.addInitParameter("serverName", shiroServerUrlPrefix);
        return registration;
    }

    @Bean
    public FilterRegistrationBean cas20ProxyReceivingTicketValidationRegistration(@Value("${casServerUrlPrefix}") String casServerUrlPrefix,
                                                                                  @Value("${shiroServerUrlPrefix}") String shiroServerUrlPrefix) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        registration.addUrlPatterns("");
        registration.setName("Cas20ProxyReceivingTicketValidationFilter");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("casServerUrlPrefix", casServerUrlPrefix);
        registration.addInitParameter("serverName", shiroServerUrlPrefix);
        return registration;
    }
    /*@Bean
    public FilterRegistrationBean singleSignOutRegistration(@Value("${casServerUrlPrefix}") String casServerUrlPrefix) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        registration.addUrlPatterns("*//*");
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setDispatcherTypes(DispatcherType.FORWARD);
        registration.setName("SingleSignOutFilter");
        registration.addInitParameter("casServerUrlPrefix", casServerUrlPrefix);
        return registration;
    }*/

    @Bean
    public ServletListenerRegistrationBean SingleSignOutRegistration(@Value("${casServerUrlPrefix}") String casServerUrlPrefix) {
        ServletListenerRegistrationBean registration = new ServletListenerRegistrationBean();
        registration.setListener(new SingleSignOutHttpSessionListener());
        return registration;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("uid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getSessionManager(SimpleCookie sessionIdCookie) {

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(14400000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationInterval(14400000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroCasRealm myShiroCasRealm,
                                                                  EhCacheManager ehCacheManager,
                                                                  DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroCasRealm);
        dwsm.setSessionManager(sessionManager);
        dwsm.setCacheManager(ehCacheManager);
        // 指定 SubjectFactory
        dwsm.setSubjectFactory(new CasSubjectFactory());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }


    /**
     * CAS过滤器
     *
     * @return
     */
    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        casFilter.setFailureUrl("/authFail");// 我们选择认证失败后再打开登录页面
        return casFilter;
    }

    /**
     * ShiroFilter<br/>
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                            DefaultWebSessionManager sessionManager,
                                                            CasFilter casFilter,
                                                            AuthServiceI authService,
                                                            @Value("${default.casServerUrl}") String defaultCasServerUrl,
                                                            @Value("${default.sysServerUrl}") String defaultSysServerUrl,
                                                            @Value("${shiroServerUrlSuffix}") String shiroServerUrlSuffix,
                                                            @Value("${shiro.loginUrl}") String loginUrl,
                                                            @Value("${shiro.successUrl}") String successUrl,
                                                            @Value("${shiro.unauthorizedUrl}") String unauthorizedUrl,
                                                            @Value("${shiro.anonUrl}") String anonUrl,
                                                            @Value("${shiro.logoutUrl}") String logoutUrl) throws Exception {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 登录地址
        shiroFilterFactoryBean.setLoginUrl(defaultCasServerUrl + "/login" + "?service=" + defaultSysServerUrl + loginUrl);
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        //登出后要跳转的链接
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setName("logoutFilter");
        logoutFilter.setEnabled(true);
        logoutFilter.setRedirectUrl(defaultCasServerUrl + "/logout?service=" + defaultSysServerUrl);
        logoutFilter.setAuthService(authService);
        logoutFilter.setDefaultCasServerUrl(defaultCasServerUrl);

        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setSessionManager(sessionManager);
        singleSignOutFilter.setEnabled(true);

        UserFilter userFilter = new UserFilter();
        userFilter.setAuthService(authService);
        userFilter.setDefaultCasServerUrl(defaultCasServerUrl);
        userFilter.setDefaultSysServerUrl(defaultSysServerUrl);
        userFilter.setLoginFuncUrl(shiroServerUrlSuffix);
        userFilter.setLoginUrl(loginUrl);

        // 添加casFilter到shiroFilter中
        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("casFilter", casFilter);
        filters.put("logout", logoutFilter);
        filters.put("slo", singleSignOutFilter);
        filters.put("user", userFilter);

        shiroFilterFactoryBean.setFilters(filters);
        // 这里配置授权链，跟mvc的xml配置一样
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 设置免认证 url
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(anonUrl, ",");
        for (String url : anonUrls) {
            filterChainDefinitionMap.put(url, "anon");
        }
        filterChainDefinitionMap.put("/login", "slo,casFilter");
        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
        filterChainDefinitionMap.put(logoutUrl, "logout");
        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
