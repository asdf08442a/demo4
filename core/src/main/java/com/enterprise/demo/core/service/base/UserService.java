package com.enterprise.demo.core.service.base;

import com.enterprise.demo.common.util.InspectionUtils;
import com.enterprise.demo.core.dao.entity.UserEntity;
import com.enterprise.demo.core.dao.entity.UserExample;
import com.enterprise.demo.core.dao.mapper.UserMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Resource
  private UserMapper userMapper;

  public UserEntity getByUserId(String userId) {
    UserExample example = new UserExample();
    example.createCriteria().andUserIdEqualTo(userId);
    List<UserEntity> userEntities = userMapper.selectByExample(example);
    return InspectionUtils.isEmpty(userEntities) ? null : userEntities.get(0);
  }

}
