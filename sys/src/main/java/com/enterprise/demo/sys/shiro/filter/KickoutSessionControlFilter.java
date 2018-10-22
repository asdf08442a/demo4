package com.enterprise.demo.sys.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.enterprise.demo.sys.entity.User;
import com.google.common.collect.Maps;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

@Slf4j
public class KickoutSessionControlFilter extends AccessControlFilter {

  public static final String SHIRO_REDIS_KEY = "shiro_redis_cache";

  /**
   * 踢出后到的地址
   */
  private String kickoutUrl;
  /**
   * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
   */
  private boolean kickoutAfter = false;
  /**
   * 同一个帐号最大会话数 默认5
   */
  private int maxSession = 5;

  private SessionManager sessionManager;
  private Cache<String, Deque<Serializable>> cache;

  public void setKickoutUrl(String kickoutUrl) {
    this.kickoutUrl = kickoutUrl;
  }

  public void setKickoutAfter(boolean kickoutAfter) {
    this.kickoutAfter = kickoutAfter;
  }

  public void setMaxSession(int maxSession) {
    this.maxSession = maxSession;
  }

  public void setSessionManager(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  public void setCacheManager(CacheManager cacheManager) {
    // 设置Cache的key的前缀
    this.cache = cacheManager.getCache(KickoutSessionControlFilter.SHIRO_REDIS_KEY);
  }

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
      Object mappedValue) {
    return false;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
      throws Exception {
    Subject subject = getSubject(request, response);
    if (!subject.isAuthenticated() && !subject.isRemembered()) {
      // 如果没有登录，直接进行之后的流程
      return true;
    }
    Session session = subject.getSession();
    User user = (User) subject.getPrincipal();
    String username = user.getUsername();
    Serializable sessionId = session.getId();

    // 读取缓存，没有就存入
    Deque<Serializable> deque = cache.get(username);
    // 如果此用户没有session队列，也就是还没有登录过，缓存中没有
    // 就new一个空队列，不然deque对象为空，会报空指针
    if (deque == null) {
      deque = new LinkedList<Serializable>();
    }

    // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
    if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
      // 将sessionId存入队列
      deque.push(sessionId);
      // 将用户的sessionId队列缓存
      cache.put(username, deque);
    }

    // 如果队列里的sessionId数超出最大会话数，开始踢人
    while (deque.size() > maxSession) {
      Serializable kickoutSessionId;
      if (kickoutAfter) {
        // 如果踢出后者
        kickoutSessionId = deque.removeFirst();
        // 踢出后再更新下缓存队列
        cache.put(username, deque);
      } else {
        // 否则踢出前者
        kickoutSessionId = deque.removeLast();
        // 踢出后再更新下缓存队列
        cache.put(username, deque);
      }

      try {
        // 获取被踢出的sessionId的session对象
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
        if (kickoutSession != null) {
          // 设置会话的kickout属性表示踢出了
          kickoutSession.setAttribute("kickout", true);
        }
      } catch (Exception e) {//ignore exception
      }
    }

    // 如果被踢出了，直接退出，重定向到踢出后的地址
    if (session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout")) {
      subject.logout();
      saveRequest(request);
      // 判断是不是Ajax请求
      if ("XMLHttpRequest"
          .equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
        Map<String, String> resultMap = Maps.newHashMap();
        resultMap.put("user_status", "300");
        resultMap.put("message", "您已经在其他地方登录，请重新登录！");
        out(response, resultMap);
      } else {
        WebUtils.issueRedirect(request, response, kickoutUrl);
      }
      return false;
    }
    return true;
  }

  private void out(ServletResponse hresponse, Map<String, String> resultMap) {
    try {
      hresponse.setCharacterEncoding("UTF-8");
      PrintWriter out = hresponse.getWriter();
      out.println(JSON.toJSONString(resultMap));
      out.flush();
      out.close();
    } catch (Exception e) {
      log.error("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
    }
  }
}
