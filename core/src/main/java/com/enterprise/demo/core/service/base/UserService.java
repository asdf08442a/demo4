package com.enterprise.demo.core.service.base;

import com.enterprise.demo.common.util.InspectionUtils;
import com.enterprise.demo.core.dao.entity.UserEntity;
import com.enterprise.demo.core.dao.entity.UserExample;
import com.enterprise.demo.core.dao.mapper.UserMapper;
import com.enterprise.demo.core.enums.YesNoEnum;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Resource
  private UserMapper userMapper;

  public Boolean save(UserEntity userEntity) {
    return userMapper.insertSelective(userEntity) == 1;
  }

  @Cacheable(cacheNames = "user", key = "#userId", unless = "#result == null")
  public UserEntity getByUserId(String userId) {
    UserExample example = new UserExample();
    example.createCriteria()
        .andUserIdEqualTo(userId)
        .andIsDeletedEqualTo(YesNoEnum.NO.getKey());
    List<UserEntity> userEntities = userMapper.selectByExample(example);
    return InspectionUtils.isEmpty(userEntities) ? null : userEntities.get(0);
  }

  @CacheEvict(cacheNames = "user", key = "#userId")
  public Boolean deleteByUserId(String userId) {
    UserExample example = new UserExample();
    example.createCriteria().andUserIdEqualTo(userId);
    UserEntity update = new UserEntity();
    update.setIsDeleted(YesNoEnum.YES.getKey());
    return userMapper.updateByExampleSelective(update, example) == 1;
  }

}
