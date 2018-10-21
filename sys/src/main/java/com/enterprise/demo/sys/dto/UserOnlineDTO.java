package com.enterprise.demo.sys.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserOnlineDTO {

  //Session Id
  private String sessionId;
  //username
  private String username;
  //Session Host
  private String host;
  //Session创建时间
  private Date startTime;
  //Session最后交互时间
  private Date lastAccess;
  /*最后登录时间*/
  private Date lastLoginTime;
  //Session timeout
  private long timeout;
  //session 是否踢出
  private boolean sessionStatus = Boolean.TRUE;

}
