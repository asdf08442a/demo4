package com.enterprise.demo.sys.shiro;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.PermissionService;
import com.enterprise.demo.sys.service.RoleService;
import com.enterprise.demo.sys.service.UserService;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private PermissionService permissionService;
  @Autowired
  private RedisSessionDAO redisSessionDAO;

  /**
   * 认证
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    // 获取用户的输入的账号.
    String username = (String) token.getPrincipal();
    User user = userService.selectByUsername(username);
    if (user == null) {
      throw new UnknownAccountException();
    }
    if (CoreConst.STATUS_INVALID.equals(user.getStatus())) {
      // 帐号锁定
      throw new LockedAccountException();
    }
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        user,
        user.getPassword(),
        ByteSource.Util.bytes(user.getSalt()),
        getName()
    );
    // 当验证都通过后，把用户信息放在session里
    Session session = SecurityUtils.getSubject().getSession();
    session.setAttribute("userSession", user);
    session.setAttribute("userSessionId", user.getUserId());
    return authenticationInfo;
  }

  /**
   * 授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    if (principals == null) {
      throw new AuthorizationException("principals should not be null");
    }
    User user = (User) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setRoles(roleService.findRoleByUserId(user.getUserId()));
    info.setStringPermissions(permissionService.selectPermsByUserId(user.getUserId()));
    return info;
  }

  /**
   * 清除认证信息
   */
  public void removeCachedAuthenticationInfo(Set<String> userIds) {
    if (CollectionUtils.isEmpty(userIds)) {
      return;
    }
    List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
    RealmSecurityManager securityManager =
        (RealmSecurityManager) SecurityUtils.getSecurityManager();
    MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
    for (SimplePrincipalCollection simplePrincipalCollection : list) {
      realm.clearCachedAuthenticationInfo(simplePrincipalCollection);
    }
  }

  /**
   * 根据userId 清除当前session存在的用户的权限缓存
   */
  public void clearAuthorizationByUserId(Set<String> userIds) {
    if (CollectionUtils.isEmpty(userIds)) {
      return;
    }
    List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
    RealmSecurityManager securityManager =
        (RealmSecurityManager) SecurityUtils.getSecurityManager();
    MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
    for (SimplePrincipalCollection simplePrincipalCollection : list) {
      realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
    }
  }

  /**
   * 根据用户id获取所有spc
   */
  private List<SimplePrincipalCollection> getSpcListByUserIds(Set<String> userIds) {
    // 获取所有session
    Collection<Session> sessions = redisSessionDAO.getActiveSessions();
    // 定义返回
    List<SimplePrincipalCollection> list = Lists.newArrayList();
    for (Session session : sessions) {
      // 获取session登录信息。
      Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
      if (obj instanceof SimplePrincipalCollection) {
        // 强转
        SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
        // 判断用户，匹配用户ID。
        obj = spc.getPrimaryPrincipal();
        if (obj instanceof User) {
          User user = (User) obj;
          // 比较用户ID，符合即加入集合
          if (userIds.contains(user.getUserId())) {
            list.add(spc);
          }
        }
      }
    }
    return list;
  }

}