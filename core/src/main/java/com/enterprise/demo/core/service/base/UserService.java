package com.enterprise.demo.core.service.base;

import com.enterprise.demo.common.base.BizException;
import com.enterprise.demo.common.base.ErrorCode;
import com.enterprise.demo.common.util.InspectionUtils;
import com.enterprise.demo.core.dao.entity.UserEntity;
import com.enterprise.demo.core.dao.entity.UserExample;
import com.enterprise.demo.core.dao.mapper.UserMapper;
import com.enterprise.demo.core.dto.UserUpdateDTO;
import com.enterprise.demo.core.enums.YesNoEnum;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    List<UserEntity> userEntities = userMapper.selectByExample(getUserExample(userId));
    return InspectionUtils.isEmpty(userEntities) ? null : userEntities.get(0);
  }

  @CachePut(cacheNames = "user", key = "#userUpdateDTO.userId")
  public UserEntity update(UserUpdateDTO userUpdateDTO) {
    UserEntity updateEntity = getByUserId(userUpdateDTO.getUserId());
    if (Objects.isNull(updateEntity)) {
      throw new BizException(ErrorCode.USER_INFO_NOT_FOUND);
    }
    BeanUtils.copyProperties(userUpdateDTO, updateEntity);
    int result = userMapper.updateByExample(updateEntity, getUserExample(userUpdateDTO.getUserId()));
    if (result != 1) {
      throw new BizException(ErrorCode.UPDATE_ERROR);
    }
    return updateEntity;
  }

  @CacheEvict(cacheNames = "user", key = "#userId")
  public Boolean deleteByUserId(String userId) {
    UserExample example = new UserExample();
    example.createCriteria().andUserIdEqualTo(userId);
    UserEntity update = new UserEntity();
    update.setIsDeleted(YesNoEnum.YES.getKey());
    return userMapper.updateByExampleSelective(update, example) == 1;
  }

  private UserExample getUserExample(String userId) {
    UserExample example = new UserExample();
    example.createCriteria()
        .andUserIdEqualTo(userId)
        .andIsDeletedEqualTo(YesNoEnum.NO.getKey());
    return example;
  }

}
