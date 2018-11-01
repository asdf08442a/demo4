package com.enterprise.demo.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  long countByExample(UserRoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  int deleteByExample(UserRoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  int insert(UserRoleEntity record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  int insertSelective(UserRoleEntity record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  List<UserRoleEntity> selectByExample(UserRoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  int updateByExampleSelective(@Param("record") UserRoleEntity record,
      @Param("example") UserRoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * user_role
   *
   * @mbg.generated
   */
  int updateByExample(@Param("record") UserRoleEntity record,
      @Param("example") UserRoleExample example);
}