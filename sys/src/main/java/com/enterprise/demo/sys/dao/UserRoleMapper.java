package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.UserRole;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

  Set<String> selectRoleIdsByUserId(@Param("userId") String userId);

  Set<String> selectUserIdsByRoleId(@Param("roleId") String roleId);
}
