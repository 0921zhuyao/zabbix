package com.jd.framework.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jd.common.utils.StringUtils;
import com.jd.framework.shiro.authc.LoginType;
import com.jd.framework.shiro.authc.UserModularRealmAuthenticator;
import com.jd.framework.shiro.realm.AppRealm;
import com.jd.framework.shiro.realm.UserRealm;
import com.jd.framework.shiro.web.filter.captcha.CaptchaValidateFilter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	@Value("${shiro.session.expireTime}")
	private int expireTime;

	@Value("${shiro.session.validationInterval}")
	private int validationInterval;

	@Value("${shiro.user.captchaEnabled}")
	private boolean captchaEnabled;

	@Value("${shiro.user.captchaType}")
	private String captchaType;

	@Value("${shiro.cookie.domain}")
	private String domain;

	@Value("${shiro.cookie.path}")
	private String path;

	@Value("${shiro.cookie.httpOnly}")
	private boolean httpOnly;

	@Value("${shiro.cookie.maxAge}")
	private int maxAge;

	@Value("${shiro.user.loginUrl}")
	private String loginUrl;

	@Value("${shiro.user.indexUrl}")
	private String indexUrl;

	@Value("${shiro.user.unauthorizedUrl}")
	private String unauthorizedUrl;

	/**
	 * 缓存管理器 使用Ehcache实现
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("df-cache");
		EhCacheManager em = new EhCacheManager();
		if (StringUtils.isNull(cacheManager)) {
			em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
			return em;
		} else {
			em.setCacheManager(cacheManager);
			return em;
		}
	}

	/**
	 * 自定义Realm
	 */
	@Bean
	public AppRealm appRealm(EhCacheManager cacheManager) {
		AppRealm appRealm = new AppRealm();
		appRealm.setCacheManager(cacheManager);
		appRealm.setName(LoginType.APP.toString());
		return appRealm;
	}
	
	/**
	 * 自定义Realm
	 */
	@Bean
	public UserRealm userRealm(EhCacheManager cacheManager) {
		UserRealm userRealm = new UserRealm();
		userRealm.setCacheManager(cacheManager);
		userRealm.setName(LoginType.AMDIN.toString());
		return userRealm;
	}
	/**
	 * 安全管理器
	 */
	@Bean
	public SecurityManager securityManager(EhCacheManager cacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		securityManager.setAuthenticator(modularRealmAuthenticator());
		// 设置realm.
		Collection<Realm> realms = new ArrayList<>();
		realms.add(userRealm(cacheManager));
		realms.add(appRealm(cacheManager));   
		securityManager.setRealms(realms);
		// 记住我
		securityManager.setRememberMeManager(rememberMeManager());
		// 注入缓存管理器;
		securityManager.setCacheManager(getEhCacheManager());
		// session管理器
		securityManager.setSessionManager(new DefaultWebSessionManager());
		return securityManager;
	}

	/**
	 * 系统自带的Realm管理，主要针对多realm
	 * */
	@Bean
	public ModularRealmAuthenticator modularRealmAuthenticator(){
		//自己重写的ModularRealmAuthenticator
		UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		return modularRealmAuthenticator;
	}
	/**
	 * Shiro过滤器配置
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		shiroFilterFactoryBean.setSuccessUrl(indexUrl);
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/favicon.ico**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/ajax/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/mine/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/services/**", "anon");
		filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/login", "anon,captchaValidate");
		filterChainDefinitionMap.put("/*", "user");

		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("captchaValidate", captchaValidateFilter());
		shiroFilterFactoryBean.setFilters(filters);

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	/**
	 * 自定义验证码过滤器
	 */
	@Bean
	public CaptchaValidateFilter captchaValidateFilter() {
		CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
		captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
		captchaValidateFilter.setCaptchaType(captchaType);
		return captchaValidateFilter;
	}

	/**
	 * cookie 属性设置
	 */
	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setMaxAge(maxAge * 24 * 60 * 60);
		return cookie;
	}

	/**
	 * 记住我
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
		return cookieRememberMeManager;
	}

	/**
	 * thymeleaf模板引擎和shiro框架的整合
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 开启Shiro注解通知器
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
