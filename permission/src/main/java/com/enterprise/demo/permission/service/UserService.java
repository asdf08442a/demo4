package com.enterprise.demo.permission.service;

import com.enterprise.demo.permission.dao.UserEntity;
import com.enterprise.demo.permission.dao.UserExample;
import com.enterprise.demo.permission.dao.UserMapper;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinzg
 * @date 2018/10/30
 */
@Service
@Slf4j
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public UserEntity selectByUserId(String userId) {
    UserExample example = new UserExample();
    example.createCriteria().andUserIdEqualTo(userId);
    List<UserEntity> userEntities = userMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(userEntities)) {
      return null;
    }
    return userEntities.get(0);
  }

  public boolean updateLastLoginTime(UserEntity userEntity) {
    UserEntity user = new UserEntity();
    user.setLastLoginTime(new Date());
    UserExample example = new UserExample();
    example.createCriteria().andUserIdEqualTo(userEntity.getUserId());
    return userMapper.updateByExampleSelective(user, example) == 1;
  }

}
