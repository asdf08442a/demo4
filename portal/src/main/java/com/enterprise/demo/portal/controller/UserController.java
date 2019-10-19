package com.enterprise.demo.portal.controller;

import com.enterprise.demo.common.base.BizResponse;
import com.enterprise.demo.core.dao.entity.UserEntity;
import com.enterprise.demo.core.dto.UserDTO;
import com.enterprise.demo.core.dto.UserIdDTO;
import com.enterprise.demo.core.service.base.UserService;
import javax.annotation.Resource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Resource
  private UserService userService;

  @PostMapping("/save")
  public BizResponse<Boolean> save(@Valid @RequestBody UserDTO userDTO) {
    log.info("save request:{}", userDTO);
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId("111");
    BeanUtils.copyProperties(userDTO, userEntity);
    return BizResponse.build(userService.save(userEntity));
  }

  @GetMapping("/getByUserId")
  public BizResponse<UserEntity> getByUserId(@Valid UserIdDTO userIdDTO) {
    log.info("getByUserId request:{}", userIdDTO);
    return BizResponse.build(userService.getByUserId(userIdDTO.getUserId()));
  }

  @PostMapping("/deleteByUserId")
  public BizResponse<Boolean> deleteByUserId(@Valid @RequestBody UserIdDTO userIdDTO) {
    log.info("deleteByUserId request:{}", userIdDTO);
    return BizResponse.build(userService.deleteByUserId(userIdDTO.getUserId()));
  }
}
