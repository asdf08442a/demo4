package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.common.util.PasswordHelper;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dao.UserMapper;
import com.enterprise.demo.sys.dao.UserRoleMapper;
import com.enterprise.demo.sys.dto.UserOnlineDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.entity.UserRole;
import com.enterprise.demo.sys.service.UserService;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserRoleMapper userRoleMapper;
  @Autowired
  private RedisSessionDAO redisSessionDAO;
  @Autowired
  private SessionManager sessionManager;
  @Autowired
  private RedisCacheManager redisCacheManager;

  @Override
  public int selectRoleUserCnt(String roleId) {
    return userRoleMapper.selectCount(new EntityWrapper<UserRole>().eq("role_id", roleId));
  }

  @Override
  public User selectByUsername(String username) {
    List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
    if (CollectionUtils.isEmpty(users)) {
      return null;
    }
    return users.get(0);
  }

  @Override
  public void updateLastLoginTime(User user) {
    userMapper.updateLastLoginTime(user);
  }

  @Override
  public User selectByUserId(String userId) {
    List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("user_id", userId));
    if (CollectionUtils.isEmpty(users)) {
      return null;
    }
    return users.get(0);
  }

  @Override
  public int updateByUserId(User user) {
    return userMapper.update(user, new EntityWrapper<User>().eq("user_id", user.getUserId()));
  }

  @Override
  public List<User> findUsers(User user) {
    Wrapper<User> wrapper = new EntityWrapper<User>().eq("status", CoreConst.STATUS_VALID);
    if (user != null && StringUtils.isNotBlank(user.getUsername())) {
      wrapper.like("username", user.getUsername());
    }
    if (user != null && StringUtils.isNotBlank(user.getEmail())) {
      wrapper.like("email", user.getEmail());
    }
    if (user != null && StringUtils.isNotBlank(user.getPhone())) {
      wrapper.like("phone", user.getPhone());
    }
    return userMapper.selectList(wrapper);
  }

  @Override
  public int insert(User user) {
    user.setUserId(String.valueOf(IdWorker.genUserId()));
    user.setStatus(CoreConst.STATUS_VALID);
    PasswordHelper.encryptPassword(user);
    return userMapper.insert(user);
  }

  @Override
  public int updateStatusBatch(List<String> userIdsList, Integer status) {
    if (CoreConst.STATUS_INVALID.equals(status)) {
      userRoleMapper.delete(new EntityWrapper<UserRole>().in("user_id", userIdsList.toArray()));
    }
    return userMapper.updateStatusBatch(userIdsList, status);
  }

  @Override
  public ResponseDTO addAssignRole(String userId, List<String> roleIds) {
    userRoleMapper.delete(new EntityWrapper<UserRole>()
        .eq("user_id", userId));
    for (String roleId : roleIds) {
      UserRole userRole = new UserRole();
      userRole.setUserId(userId);
      userRole.setRoleId(roleId);
      userRoleMapper.insert(userRole);
    }
    return ResultUtils.success("分配权限成功");
  }

  @Override
  public List<UserOnlineDTO> selectOnlineUsers(UserOnlineDTO user) {
    // 因为我们是用redis实现了shiro的session的Dao,而且是采用了shiro+redis这个插件
    // 所以从spring容器中获取redisSessionDAO来获取session列表.
    Collection<Session> sessions = redisSessionDAO.getActiveSessions();
    Iterator<Session> it = sessions.iterator();
    List<UserOnlineDTO> onlineUserList = Lists.newArrayList();
    while (it.hasNext()) {
      // 这是shiro已经存入session的现在直接取就是了
      Session session = it.next();
      // 标记为已提出的不加入在线列表
      if (session.getAttribute("kickout") != null) {
        continue;
      }
      UserOnlineDTO onlineUser = getUserOnlineDTO(session);
      if (onlineUser != null) {
        // 用户名搜索
        if (StringUtils.isNotBlank(user.getUsername())
            && !onlineUser.getUsername().contains(user.getUsername())) {
          continue;
        }
        onlineUserList.add(onlineUser);
      }
    }
    return onlineUserList;
  }

  @Override
  public void kickout(String sessionId, String username) {
    sessionManager.getSession(new DefaultSessionKey(sessionId)).setAttribute("kickout", true);
    // 读取缓存,找到并从队列中移除
    Cache<String, Deque<Serializable>> cache = redisCacheManager
        .getCache(redisCacheManager.getKeyPrefix() +
            username);
    Deque<Serializable> deques = cache.get(username);
    for (Serializable deque : deques) {
      if (sessionId.equals(deque)) {
        deques.remove(deque);
        break;
      }
    }
    cache.put(username, deques);
  }

  private UserOnlineDTO getUserOnlineDTO(Session session) {
    // 获取session登录信息。
    Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
    if (obj == null) {
      return null;
    }
    // 确保是SimplePrincipalCollection对象。
    if (obj instanceof SimplePrincipalCollection) {
      SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
      obj = spc.getPrimaryPrincipal();
      if (obj instanceof User) {
        User user = (User) obj;
        UserOnlineDTO userOnlineDTO = new UserOnlineDTO();
        userOnlineDTO.setLastAccess(session.getLastAccessTime());
        userOnlineDTO.setHost(session.getHost());
        userOnlineDTO.setSessionId(session.getId().toString());
        userOnlineDTO.setLastLoginTime(user.getLastLoginTime());
        userOnlineDTO.setTimeout(session.getTimeout());
        userOnlineDTO.setStartTime(session.getStartTimestamp());
        userOnlineDTO.setSessionStatus(false);
        userOnlineDTO.setUsername(user.getUsername());
        return userOnlineDTO;
      }
    }
    return null;
  }

}
