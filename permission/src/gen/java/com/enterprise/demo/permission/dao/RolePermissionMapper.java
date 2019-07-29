package com.enterprise.demo.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  long countByExample(RolePermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  int deleteByExample(RolePermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  int insert(RolePermissionEntity record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  int insertSelective(RolePermissionEntity record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  List<RolePermissionEntity> selectByExample(RolePermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  int updateByExampleSelective(@Param("record") RolePermissionEntity record,
      @Param("example") RolePermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * role_permission
   *
   * @mbg.generated
   */
  int updateByExample(@Param("record") RolePermissionEntity record,
      @Param("example") RolePermissionExample example);
}