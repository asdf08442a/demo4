package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.UserOnlineDTO;
import com.enterprise.demo.sys.dto.UserSessionDTO;
import com.enterprise.demo.sys.dto.base.PageResultDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.service.UserService;
import java.io.Serializable;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/online/user")
public class OnlineUserController {

  @Autowired
  private UserService userService;

  /**
   * 在线用户列表
   */
  @PostMapping("/list")
  @ResponseBody
  public PageResultDTO onlineUsers(UserOnlineDTO user, Integer limit, Integer offset) {
    List<UserOnlineDTO> userList = userService.selectOnlineUsers(user);
    int endIndex = (offset + limit) > userList.size() ? userList.size() : (offset + limit);
    return ResultUtils.table(userList.subList(offset, endIndex), (long) userList.size());
  }

  /**
   * 强制踢出用户
   */
  @PostMapping("/kickout")
  @ResponseBody
  public ResponseDTO kickout(String sessionId, String username) {
    if (SecurityUtils.getSubject().getSession().getId().equals(sessionId)) {
      return ResultUtils.error("不能踢出自己");
    }
    userService.kickout(sessionId, username);
    return ResultUtils.success("踢出用户成功");
  }

  /**
   * 批量强制踢出用户
   */
  @PostMapping("/batch/kickout")
  @ResponseBody
  public ResponseDTO kickout(@RequestBody List<UserSessionDTO> sessions) {
    // 要踢出的用户中是否有自己
    boolean hasOwn = false;
    Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
    for (UserSessionDTO sessionDTO : sessions) {
      if (sessionDTO.getSessionId().equals(sessionId)) {
        hasOwn = true;
      } else {
        userService.kickout(sessionDTO.getSessionId(), sessionDTO.getUsername());
      }
    }
    if (hasOwn) {
      return ResultUtils.success("不能踢出自己");
    }
    return ResultUtils.success("踢出用户成功");
  }
}
