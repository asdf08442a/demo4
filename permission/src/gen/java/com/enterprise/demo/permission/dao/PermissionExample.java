package com.enterprise.demo.permission.dao;

import com.enterprise.demo.permission.enums.PermissionTypeEnum;
import com.enterprise.demo.permission.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PermissionExample {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  protected String orderByClause;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  protected boolean distinct;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public PermissionExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getOffset() {
    return offset;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  protected abstract static class GeneratedCriteria {

    protected List<Criterion> typeCriteria;

    protected List<Criterion> isUseCriteria;

    protected List<Criterion> allCriteria;

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
      typeCriteria = new ArrayList<Criterion>();
      isUseCriteria = new ArrayList<Criterion>();
    }

    public List<Criterion> getTypeCriteria() {
      return typeCriteria;
    }

    protected void addTypeCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      typeCriteria.add(new Criterion(condition, value,
          "com.xiaoju.manhattan.auto.framework.mybatis.CodeEnumTypeHandler"));
      allCriteria = null;
    }

    protected void addTypeCriterion(String condition, PermissionTypeEnum value1,
        PermissionTypeEnum value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      typeCriteria.add(new Criterion(condition, value1, value2,
          "com.xiaoju.manhattan.auto.framework.mybatis.CodeEnumTypeHandler"));
      allCriteria = null;
    }

    public List<Criterion> getIsUseCriteria() {
      return isUseCriteria;
    }

    protected void addIsUseCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      isUseCriteria.add(new Criterion(condition, value,
          "com.xiaoju.manhattan.auto.framework.mybatis.CodeEnumTypeHandler"));
      allCriteria = null;
    }

    protected void addIsUseCriterion(String condition, StatusEnum value1, StatusEnum value2,
        String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      isUseCriteria.add(new Criterion(condition, value1, value2,
          "com.xiaoju.manhattan.auto.framework.mybatis.CodeEnumTypeHandler"));
      allCriteria = null;
    }

    public boolean isValid() {
      return criteria.size() > 0
          || typeCriteria.size() > 0
          || isUseCriteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      if (allCriteria == null) {
        allCriteria = new ArrayList<Criterion>();
        allCriteria.addAll(criteria);
        allCriteria.addAll(typeCriteria);
        allCriteria.addAll(isUseCriteria);
      }
      return allCriteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
      allCriteria = null;
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
      allCriteria = null;
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
      allCriteria = null;
    }

    public Criteria andIdIsNull() {
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andPermissionIdIsNull() {
      addCriterion("permission_id is null");
      return (Criteria) this;
    }

    public Criteria andPermissionIdIsNotNull() {
      addCriterion("permission_id is not null");
      return (Criteria) this;
    }

    public Criteria andPermissionIdEqualTo(String value) {
      addCriterion("permission_id =", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdNotEqualTo(String value) {
      addCriterion("permission_id <>", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdGreaterThan(String value) {
      addCriterion("permission_id >", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdGreaterThanOrEqualTo(String value) {
      addCriterion("permission_id >=", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdLessThan(String value) {
      addCriterion("permission_id <", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdLessThanOrEqualTo(String value) {
      addCriterion("permission_id <=", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdLike(String value) {
      addCriterion("permission_id like", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdNotLike(String value) {
      addCriterion("permission_id not like", value, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdIn(List<String> values) {
      addCriterion("permission_id in", values, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdNotIn(List<String> values) {
      addCriterion("permission_id not in", values, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdBetween(String value1, String value2) {
      addCriterion("permission_id between", value1, value2, "permissionId");
      return (Criteria) this;
    }

    public Criteria andPermissionIdNotBetween(String value1, String value2) {
      addCriterion("permission_id not between", value1, value2, "permissionId");
      return (Criteria) this;
    }

    public Criteria andNameIsNull() {
      addCriterion("name is null");
      return (Criteria) this;
    }

    public Criteria andNameIsNotNull() {
      addCriterion("name is not null");
      return (Criteria) this;
    }

    public Criteria andNameEqualTo(String value) {
      addCriterion("name =", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotEqualTo(String value) {
      addCriterion("name <>", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThan(String value) {
      addCriterion("name >", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThanOrEqualTo(String value) {
      addCriterion("name >=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThan(String value) {
      addCriterion("name <", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThanOrEqualTo(String value) {
      addCriterion("name <=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLike(String value) {
      addCriterion("name like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotLike(String value) {
      addCriterion("name not like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameIn(List<String> values) {
      addCriterion("name in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotIn(List<String> values) {
      addCriterion("name not in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameBetween(String value1, String value2) {
      addCriterion("name between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotBetween(String value1, String value2) {
      addCriterion("name not between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNull() {
      addCriterion("description is null");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNotNull() {
      addCriterion("description is not null");
      return (Criteria) this;
    }

    public Criteria andDescriptionEqualTo(String value) {
      addCriterion("description =", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotEqualTo(String value) {
      addCriterion("description <>", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThan(String value) {
      addCriterion("description >", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
      addCriterion("description >=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThan(String value) {
      addCriterion("description <", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThanOrEqualTo(String value) {
      addCriterion("description <=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLike(String value) {
      addCriterion("description like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotLike(String value) {
      addCriterion("description not like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionIn(List<String> values) {
      addCriterion("description in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotIn(List<String> values) {
      addCriterion("description not in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionBetween(String value1, String value2) {
      addCriterion("description between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotBetween(String value1, String value2) {
      addCriterion("description not between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andUrlIsNull() {
      addCriterion("url is null");
      return (Criteria) this;
    }

    public Criteria andUrlIsNotNull() {
      addCriterion("url is not null");
      return (Criteria) this;
    }

    public Criteria andUrlEqualTo(String value) {
      addCriterion("url =", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotEqualTo(String value) {
      addCriterion("url <>", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThan(String value) {
      addCriterion("url >", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThanOrEqualTo(String value) {
      addCriterion("url >=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThan(String value) {
      addCriterion("url <", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThanOrEqualTo(String value) {
      addCriterion("url <=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLike(String value) {
      addCriterion("url like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotLike(String value) {
      addCriterion("url not like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlIn(List<String> values) {
      addCriterion("url in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotIn(List<String> values) {
      addCriterion("url not in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlBetween(String value1, String value2) {
      addCriterion("url between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotBetween(String value1, String value2) {
      addCriterion("url not between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andPermsIsNull() {
      addCriterion("perms is null");
      return (Criteria) this;
    }

    public Criteria andPermsIsNotNull() {
      addCriterion("perms is not null");
      return (Criteria) this;
    }

    public Criteria andPermsEqualTo(String value) {
      addCriterion("perms =", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsNotEqualTo(String value) {
      addCriterion("perms <>", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsGreaterThan(String value) {
      addCriterion("perms >", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsGreaterThanOrEqualTo(String value) {
      addCriterion("perms >=", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsLessThan(String value) {
      addCriterion("perms <", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsLessThanOrEqualTo(String value) {
      addCriterion("perms <=", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsLike(String value) {
      addCriterion("perms like", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsNotLike(String value) {
      addCriterion("perms not like", value, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsIn(List<String> values) {
      addCriterion("perms in", values, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsNotIn(List<String> values) {
      addCriterion("perms not in", values, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsBetween(String value1, String value2) {
      addCriterion("perms between", value1, value2, "perms");
      return (Criteria) this;
    }

    public Criteria andPermsNotBetween(String value1, String value2) {
      addCriterion("perms not between", value1, value2, "perms");
      return (Criteria) this;
    }

    public Criteria andParentIdIsNull() {
      addCriterion("parent_id is null");
      return (Criteria) this;
    }

    public Criteria andParentIdIsNotNull() {
      addCriterion("parent_id is not null");
      return (Criteria) this;
    }

    public Criteria andParentIdEqualTo(String value) {
      addCriterion("parent_id =", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotEqualTo(String value) {
      addCriterion("parent_id <>", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdGreaterThan(String value) {
      addCriterion("parent_id >", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdGreaterThanOrEqualTo(String value) {
      addCriterion("parent_id >=", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLessThan(String value) {
      addCriterion("parent_id <", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLessThanOrEqualTo(String value) {
      addCriterion("parent_id <=", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLike(String value) {
      addCriterion("parent_id like", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotLike(String value) {
      addCriterion("parent_id not like", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdIn(List<String> values) {
      addCriterion("parent_id in", values, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotIn(List<String> values) {
      addCriterion("parent_id not in", values, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdBetween(String value1, String value2) {
      addCriterion("parent_id between", value1, value2, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotBetween(String value1, String value2) {
      addCriterion("parent_id not between", value1, value2, "parentId");
      return (Criteria) this;
    }

    public Criteria andTypeIsNull() {
      addCriterion("type is null");
      return (Criteria) this;
    }

    public Criteria andTypeIsNotNull() {
      addCriterion("type is not null");
      return (Criteria) this;
    }

    public Criteria andTypeEqualTo(PermissionTypeEnum value) {
      addTypeCriterion("type =", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotEqualTo(PermissionTypeEnum value) {
      addTypeCriterion("type <>", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThan(PermissionTypeEnum value) {
      addTypeCriterion("type >", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeGreaterThanOrEqualTo(PermissionTypeEnum value) {
      addTypeCriterion("type >=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThan(PermissionTypeEnum value) {
      addTypeCriterion("type <", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeLessThanOrEqualTo(PermissionTypeEnum value) {
      addTypeCriterion("type <=", value, "type");
      return (Criteria) this;
    }

    public Criteria andTypeIn(List<PermissionTypeEnum> values) {
      addTypeCriterion("type in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotIn(List<PermissionTypeEnum> values) {
      addTypeCriterion("type not in", values, "type");
      return (Criteria) this;
    }

    public Criteria andTypeBetween(PermissionTypeEnum value1, PermissionTypeEnum value2) {
      addTypeCriterion("type between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andTypeNotBetween(PermissionTypeEnum value1, PermissionTypeEnum value2) {
      addTypeCriterion("type not between", value1, value2, "type");
      return (Criteria) this;
    }

    public Criteria andOrderNumIsNull() {
      addCriterion("order_num is null");
      return (Criteria) this;
    }

    public Criteria andOrderNumIsNotNull() {
      addCriterion("order_num is not null");
      return (Criteria) this;
    }

    public Criteria andOrderNumEqualTo(Integer value) {
      addCriterion("order_num =", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumNotEqualTo(Integer value) {
      addCriterion("order_num <>", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumGreaterThan(Integer value) {
      addCriterion("order_num >", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
      addCriterion("order_num >=", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumLessThan(Integer value) {
      addCriterion("order_num <", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
      addCriterion("order_num <=", value, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumIn(List<Integer> values) {
      addCriterion("order_num in", values, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumNotIn(List<Integer> values) {
      addCriterion("order_num not in", values, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumBetween(Integer value1, Integer value2) {
      addCriterion("order_num between", value1, value2, "orderNum");
      return (Criteria) this;
    }

    public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
      addCriterion("order_num not between", value1, value2, "orderNum");
      return (Criteria) this;
    }

    public Criteria andIconIsNull() {
      addCriterion("icon is null");
      return (Criteria) this;
    }

    public Criteria andIconIsNotNull() {
      addCriterion("icon is not null");
      return (Criteria) this;
    }

    public Criteria andIconEqualTo(String value) {
      addCriterion("icon =", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconNotEqualTo(String value) {
      addCriterion("icon <>", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconGreaterThan(String value) {
      addCriterion("icon >", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconGreaterThanOrEqualTo(String value) {
      addCriterion("icon >=", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconLessThan(String value) {
      addCriterion("icon <", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconLessThanOrEqualTo(String value) {
      addCriterion("icon <=", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconLike(String value) {
      addCriterion("icon like", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconNotLike(String value) {
      addCriterion("icon not like", value, "icon");
      return (Criteria) this;
    }

    public Criteria andIconIn(List<String> values) {
      addCriterion("icon in", values, "icon");
      return (Criteria) this;
    }

    public Criteria andIconNotIn(List<String> values) {
      addCriterion("icon not in", values, "icon");
      return (Criteria) this;
    }

    public Criteria andIconBetween(String value1, String value2) {
      addCriterion("icon between", value1, value2, "icon");
      return (Criteria) this;
    }

    public Criteria andIconNotBetween(String value1, String value2) {
      addCriterion("icon not between", value1, value2, "icon");
      return (Criteria) this;
    }

    public Criteria andIsUseIsNull() {
      addCriterion("is_use is null");
      return (Criteria) this;
    }

    public Criteria andIsUseIsNotNull() {
      addCriterion("is_use is not null");
      return (Criteria) this;
    }

    public Criteria andIsUseEqualTo(StatusEnum value) {
      addIsUseCriterion("is_use =", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotEqualTo(StatusEnum value) {
      addIsUseCriterion("is_use <>", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseGreaterThan(StatusEnum value) {
      addIsUseCriterion("is_use >", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseGreaterThanOrEqualTo(StatusEnum value) {
      addIsUseCriterion("is_use >=", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseLessThan(StatusEnum value) {
      addIsUseCriterion("is_use <", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseLessThanOrEqualTo(StatusEnum value) {
      addIsUseCriterion("is_use <=", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseIn(List<StatusEnum> values) {
      addIsUseCriterion("is_use in", values, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotIn(List<StatusEnum> values) {
      addIsUseCriterion("is_use not in", values, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseBetween(StatusEnum value1, StatusEnum value2) {
      addIsUseCriterion("is_use between", value1, value2, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotBetween(StatusEnum value1, StatusEnum value2) {
      addIsUseCriterion("is_use not between", value1, value2, "isUse");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNull() {
      addCriterion("gmt_create is null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNotNull() {
      addCriterion("gmt_create is not null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateEqualTo(Date value) {
      addCriterion("gmt_create =", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotEqualTo(Date value) {
      addCriterion("gmt_create <>", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThan(Date value) {
      addCriterion("gmt_create >", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
      addCriterion("gmt_create >=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThan(Date value) {
      addCriterion("gmt_create <", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
      addCriterion("gmt_create <=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIn(List<Date> values) {
      addCriterion("gmt_create in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotIn(List<Date> values) {
      addCriterion("gmt_create not in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateBetween(Date value1, Date value2) {
      addCriterion("gmt_create between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
      addCriterion("gmt_create not between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIsNull() {
      addCriterion("gmt_modified is null");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIsNotNull() {
      addCriterion("gmt_modified is not null");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedEqualTo(Date value) {
      addCriterion("gmt_modified =", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotEqualTo(Date value) {
      addCriterion("gmt_modified <>", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedGreaterThan(Date value) {
      addCriterion("gmt_modified >", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
      addCriterion("gmt_modified >=", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedLessThan(Date value) {
      addCriterion("gmt_modified <", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
      addCriterion("gmt_modified <=", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIn(List<Date> values) {
      addCriterion("gmt_modified in", values, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotIn(List<Date> values) {
      addCriterion("gmt_modified not in", values, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedBetween(Date value1, Date value2) {
      addCriterion("gmt_modified between", value1, value2, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
      addCriterion("gmt_modified not between", value1, value2, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andPermissionIdLikeInsensitive(String value) {
      addCriterion("upper(permission_id) like", value.toUpperCase(), "permissionId");
      return (Criteria) this;
    }

    public Criteria andNameLikeInsensitive(String value) {
      addCriterion("upper(name) like", value.toUpperCase(), "name");
      return (Criteria) this;
    }

    public Criteria andDescriptionLikeInsensitive(String value) {
      addCriterion("upper(description) like", value.toUpperCase(), "description");
      return (Criteria) this;
    }

    public Criteria andUrlLikeInsensitive(String value) {
      addCriterion("upper(url) like", value.toUpperCase(), "url");
      return (Criteria) this;
    }

    public Criteria andPermsLikeInsensitive(String value) {
      addCriterion("upper(perms) like", value.toUpperCase(), "perms");
      return (Criteria) this;
    }

    public Criteria andParentIdLikeInsensitive(String value) {
      addCriterion("upper(parent_id) like", value.toUpperCase(), "parentId");
      return (Criteria) this;
    }

    public Criteria andIconLikeInsensitive(String value) {
      addCriterion("upper(icon) like", value.toUpperCase(), "icon");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * permission
   *
   * @mbg.generated do_not_delete_during_merge
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * permission
   *
   * @mbg.generated
   */
  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}