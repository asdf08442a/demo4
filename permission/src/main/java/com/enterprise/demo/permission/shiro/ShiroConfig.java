package com.enterprise.demo.permission.shiro;

import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

  @Bean
  public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // 必须设置 SecurityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 拦截器.
    Map<String, String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    // 未授权界面
    shiroFilterFactoryBean.setUnauthorizedUrl("/noPermission");
    return shiroFilterFactoryBean;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // 设置realm
    securityManager.setRealm(myShiroRealm());
    // 自定义缓存实现 使用redis
    securityManager.setCacheManager(redisCacheManager());
    // 自定义session管理 使用redis
    securityManager.setSessionManager(sessionManager());
    return securityManager;
  }

  @Bean
  public MyShiroRealm myShiroRealm() {
    MyShiroRealm myShiroRealm = new MyShiroRealm();
    return myShiroRealm;
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
   * shiro session的管理
   */
  @Bean
  public DefaultWebSessionManager sessionManager() {
    DefaultWebSessionManager sessionManager = new SessionManager();
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

}
