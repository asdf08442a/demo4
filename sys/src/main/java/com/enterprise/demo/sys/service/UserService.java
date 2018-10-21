package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.dto.UserOnlineDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.User;
import java.util.List;

public interface UserService {

  int selectRoleUserCnt(String roleId);

  User selectByUsername(String username);

  void updateLastLoginTime(User principal);

  User selectByUserId(String userId);

  int updateByUserId(User user);

  List<User> findUsers(User user);

  int insert(User user);

  int updateStatusBatch(List<String> userIdsList, Integer status);

  ResponseDTO addAssignRole(String userId, List<String> roleIds);

  List<UserOnlineDTO> selectOnlineUsers(UserOnlineDTO user);

  void kickout(String sessionId, String username);
}
