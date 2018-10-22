package com.enterprise.demo.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.enterprise.demo.sys.shiro.MyShiroRealm;
import com.enterprise.demo.sys.shiro.ShiroService;
import com.enterprise.demo.sys.shiro.filter.KickoutSessionControlFilter;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置
 **/
@Configuration
public class ShiroConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.database}")
  private int database;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.timeout}")
  private int timeout;

  @Autowired
  private ShiroService shiroService;

  /**
   * shiro管理生命周期
   */
  @Bean
  public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
   */
  @Bean
  public ShiroDialect shiroDialect() {
    return new ShiroDialect();
  }

  @Bean
  public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // 必须设置 SecurityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 未授权界面
    shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
    // 限制同一帐号同时在线的个数
    Map<String, Filter> filtersMap = Maps.newLinkedHashMap();
    filtersMap.put("kickout", kickoutSessionControlFilter());
    shiroFilterFactoryBean.setFilters(filtersMap);
    // 拦截器.
    Map<String, String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // 设置realm
    securityManager.setRealm(myShiroRealm());
    // 记住我
    securityManager.setRememberMeManager(rememberMeManager());
    // 自定义缓存实现 使用redis
    securityManager.setCacheManager(redisCacheManager());
    // 自定义session管理 使用redis
    securityManager.setSessionManager(sessionManager());
    return securityManager;
  }

  @Bean
  public MyShiroRealm myShiroRealm() {
    MyShiroRealm myShiroRealm = new MyShiroRealm();
    myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    return myShiroRealm;
  }

  /**
   * 凭证匹配器
   */
  @Bean
  public HashedCredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    hashedCredentialsMatcher.setHashIterations(2);
    return hashedCredentialsMatcher;
  }

  /**
   * cookie管理对象;记住我功能
   */
  public CookieRememberMeManager rememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
    // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
    cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
    return cookieRememberMeManager;
  }

  /**
   * cookie对象;
   */
  public SimpleCookie rememberMeCookie() {
    // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    // 记住我cookie生效时间1天 ,单位秒
    simpleCookie.setMaxAge(86400);
    return simpleCookie;
  }

  /**
   * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
   */
  @Bean
  public RedisCacheManager redisCacheManager() {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    redisCacheManager.setRedisManager(redisManager());
    return redisCacheManager;
  }

  /**
   * 配置shiro redisManager 使用的是shiro-redis开源插件
   */
  public RedisManager redisManager() {
    RedisManager redisManager = new RedisManager();
    redisManager.setHost(host);
    redisManager.setPort(port);
    redisManager.setDatabase(database);
    redisManager.setPassword(password);
    redisManager.setTimeout(timeout);
    return redisManager;
  }

  /**
   * shiro session的管理
   */
  @Bean
  public DefaultWebSessionManager sessionManager() {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    sessionManager.setSessionDAO(redisSessionDAO());
    return sessionManager;
  }

  /**
   * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
   */
  @Bean
  public RedisSessionDAO redisSessionDAO() {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setRedisManager(redisManager());
    return redisSessionDAO;
  }

  /**
   * 限制同一账号登录同时登录人数控制
   */
  public KickoutSessionControlFilter kickoutSessionControlFilter() {
    KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
    //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
    //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
    //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
    kickoutSessionControlFilter.setCacheManager(redisCacheManager());
    //用于根据会话ID，获取会话进行踢出操作的；
    kickoutSessionControlFilter.setSessionManager(sessionManager());
    //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
    kickoutSessionControlFilter.setKickoutAfter(false);
    //同一个用户最大的会话数，默认5；比如5的意思是同一个用户允许最多同时五个人登录；
    kickoutSessionControlFilter.setMaxSession(5);
    //被踢出后重定向到的地址；
    kickoutSessionControlFilter.setKickoutUrl("/kickout");
    return kickoutSessionControlFilter;
  }
}
