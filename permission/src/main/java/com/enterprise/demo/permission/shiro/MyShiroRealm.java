package com.enterprise.demo.permission.shiro;

import com.enterprise.demo.permission.dao.UserEntity;
import com.enterprise.demo.permission.enums.StatusEnum;
import com.enterprise.demo.permission.service.PermissionService;
import com.enterprise.demo.permission.service.RoleService;
import com.enterprise.demo.permission.service.UserService;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
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
    String userId = (String) token.getPrincipal();
    log.info("userid is {}", userId);
    UserEntity userEntity = userService.selectByUserId(userId);
    if (userEntity == null) {
      // 无此用户
      throw new UnknownAccountException();
    }
    if (StatusEnum.UNUSE.equals(userEntity.getIsUse())) {
      // 帐号锁定
      throw new LockedAccountException();
    }
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        userEntity,
        null,
        null,
        getName());
    return authenticationInfo;
  }

  /**
   * 认证密码匹配调用方法
   */
  @Override
  protected void assertCredentialsMatch(AuthenticationToken authcToken,
      AuthenticationInfo info) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    // 若有token则登陆成功
    if (StringUtils.isNotBlank(String.valueOf(token.getPassword()))) {
      return;
    }
    super.assertCredentialsMatch(token, info);
  }

  /**
   * 授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    if (principals == null) {
      throw new AuthorizationException("principals should not be null");
    }
    UserEntity userEntity = (UserEntity) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    Set<String> roles = roleService.selectRoleIdsByUserId(userEntity.getUserId());
    info.setRoles(roles);
    info.setStringPermissions(permissionService.selectPermsByRoleIds(roles));
    return info;
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
        if (obj instanceof String) {
          String userId = (String) obj;
          // 比较用户ID，符合即加入集合
          if (userIds.contains(userId)) {
            list.add(spc);
          }
        }
      }
    }
    return list;
  }

}
