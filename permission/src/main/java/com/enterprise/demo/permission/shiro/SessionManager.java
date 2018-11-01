package com.enterprise.demo.permission.shiro;

import java.io.Serializable;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

/**
 * @author jinzg
 * @date 2018/10/28
 */
public class SessionManager extends DefaultWebSessionManager {

  public SessionManager() {
    super();
  }

  @Override
  protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

    String sid = WebUtils.toHttp(request).getHeader("sessionId");
    if (StringUtils.isNotBlank(sid)) {
      // 设置当前session状态
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
          ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
      return sid;
    } else {
      return super.getSessionId(request, response);
    }
  }
}
