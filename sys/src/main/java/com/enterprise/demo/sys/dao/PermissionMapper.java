package com.enterprise.demo.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.enterprise.demo.sys.entity.Permission;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 */
public interface PermissionMapper extends BaseMapper<Permission> {

  Set<String> selectPermsByPermissionIds(@Param("permissionIds") Set<String> permissionIds);

  List<Permission> selectMenuByPermissionIds(@Param("permissionIds") Set<String> permissionIds);

}
