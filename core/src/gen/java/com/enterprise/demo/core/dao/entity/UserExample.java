package com.enterprise.demo.core.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table user
   *
   * @mbg.generated
   */
  protected String orderByClause;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table user
   *
   * @mbg.generated
   */
  protected boolean distinct;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table user
   *
   * @mbg.generated
   */
  protected List<Criteria> oredCriteria;

  private Integer limit;

  private Integer offset;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public UserExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
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
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
   *
   * @mbg.generated
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table user
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
   * This class was generated by MyBatis Generator. This class corresponds to the database table user
   *
   * @mbg.generated
   */
  protected abstract static class GeneratedCriteria {

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
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

    public Criteria andUserIdIsNull() {
      addCriterion("user_id is null");
      return (Criteria) this;
    }

    public Criteria andUserIdIsNotNull() {
      addCriterion("user_id is not null");
      return (Criteria) this;
    }

    public Criteria andUserIdEqualTo(String value) {
      addCriterion("user_id =", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotEqualTo(String value) {
      addCriterion("user_id <>", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdGreaterThan(String value) {
      addCriterion("user_id >", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdGreaterThanOrEqualTo(String value) {
      addCriterion("user_id >=", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLessThan(String value) {
      addCriterion("user_id <", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLessThanOrEqualTo(String value) {
      addCriterion("user_id <=", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLike(String value) {
      addCriterion("user_id like", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotLike(String value) {
      addCriterion("user_id not like", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdIn(List<String> values) {
      addCriterion("user_id in", values, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotIn(List<String> values) {
      addCriterion("user_id not in", values, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdBetween(String value1, String value2) {
      addCriterion("user_id between", value1, value2, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotBetween(String value1, String value2) {
      addCriterion("user_id not between", value1, value2, "userId");
      return (Criteria) this;
    }

    public Criteria andUserNameIsNull() {
      addCriterion("user_name is null");
      return (Criteria) this;
    }

    public Criteria andUserNameIsNotNull() {
      addCriterion("user_name is not null");
      return (Criteria) this;
    }

    public Criteria andUserNameEqualTo(String value) {
      addCriterion("user_name =", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotEqualTo(String value) {
      addCriterion("user_name <>", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameGreaterThan(String value) {
      addCriterion("user_name >", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameGreaterThanOrEqualTo(String value) {
      addCriterion("user_name >=", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLessThan(String value) {
      addCriterion("user_name <", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLessThanOrEqualTo(String value) {
      addCriterion("user_name <=", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLike(String value) {
      addCriterion("user_name like", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotLike(String value) {
      addCriterion("user_name not like", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameIn(List<String> values) {
      addCriterion("user_name in", values, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotIn(List<String> values) {
      addCriterion("user_name not in", values, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameBetween(String value1, String value2) {
      addCriterion("user_name between", value1, value2, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotBetween(String value1, String value2) {
      addCriterion("user_name not between", value1, value2, "userName");
      return (Criteria) this;
    }

    public Criteria andAddressIsNull() {
      addCriterion("address is null");
      return (Criteria) this;
    }

    public Criteria andAddressIsNotNull() {
      addCriterion("address is not null");
      return (Criteria) this;
    }

    public Criteria andAddressEqualTo(String value) {
      addCriterion("address =", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressNotEqualTo(String value) {
      addCriterion("address <>", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressGreaterThan(String value) {
      addCriterion("address >", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressGreaterThanOrEqualTo(String value) {
      addCriterion("address >=", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressLessThan(String value) {
      addCriterion("address <", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressLessThanOrEqualTo(String value) {
      addCriterion("address <=", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressLike(String value) {
      addCriterion("address like", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressNotLike(String value) {
      addCriterion("address not like", value, "address");
      return (Criteria) this;
    }

    public Criteria andAddressIn(List<String> values) {
      addCriterion("address in", values, "address");
      return (Criteria) this;
    }

    public Criteria andAddressNotIn(List<String> values) {
      addCriterion("address not in", values, "address");
      return (Criteria) this;
    }

    public Criteria andAddressBetween(String value1, String value2) {
      addCriterion("address between", value1, value2, "address");
      return (Criteria) this;
    }

    public Criteria andAddressNotBetween(String value1, String value2) {
      addCriterion("address not between", value1, value2, "address");
      return (Criteria) this;
    }

    public Criteria andCellphoneIsNull() {
      addCriterion("cellphone is null");
      return (Criteria) this;
    }

    public Criteria andCellphoneIsNotNull() {
      addCriterion("cellphone is not null");
      return (Criteria) this;
    }

    public Criteria andCellphoneEqualTo(String value) {
      addCriterion("cellphone =", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneNotEqualTo(String value) {
      addCriterion("cellphone <>", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneGreaterThan(String value) {
      addCriterion("cellphone >", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneGreaterThanOrEqualTo(String value) {
      addCriterion("cellphone >=", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneLessThan(String value) {
      addCriterion("cellphone <", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneLessThanOrEqualTo(String value) {
      addCriterion("cellphone <=", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneLike(String value) {
      addCriterion("cellphone like", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneNotLike(String value) {
      addCriterion("cellphone not like", value, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneIn(List<String> values) {
      addCriterion("cellphone in", values, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneNotIn(List<String> values) {
      addCriterion("cellphone not in", values, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneBetween(String value1, String value2) {
      addCriterion("cellphone between", value1, value2, "cellphone");
      return (Criteria) this;
    }

    public Criteria andCellphoneNotBetween(String value1, String value2) {
      addCriterion("cellphone not between", value1, value2, "cellphone");
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

    public Criteria andIsUseEqualTo(Integer value) {
      addCriterion("is_use =", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotEqualTo(Integer value) {
      addCriterion("is_use <>", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseGreaterThan(Integer value) {
      addCriterion("is_use >", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseGreaterThanOrEqualTo(Integer value) {
      addCriterion("is_use >=", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseLessThan(Integer value) {
      addCriterion("is_use <", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseLessThanOrEqualTo(Integer value) {
      addCriterion("is_use <=", value, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseIn(List<Integer> values) {
      addCriterion("is_use in", values, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotIn(List<Integer> values) {
      addCriterion("is_use not in", values, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseBetween(Integer value1, Integer value2) {
      addCriterion("is_use between", value1, value2, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsUseNotBetween(Integer value1, Integer value2) {
      addCriterion("is_use not between", value1, value2, "isUse");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIsNull() {
      addCriterion("is_deleted is null");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIsNotNull() {
      addCriterion("is_deleted is not null");
      return (Criteria) this;
    }

    public Criteria andIsDeletedEqualTo(Integer value) {
      addCriterion("is_deleted =", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotEqualTo(Integer value) {
      addCriterion("is_deleted <>", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedGreaterThan(Integer value) {
      addCriterion("is_deleted >", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
      addCriterion("is_deleted >=", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedLessThan(Integer value) {
      addCriterion("is_deleted <", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
      addCriterion("is_deleted <=", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIn(List<Integer> values) {
      addCriterion("is_deleted in", values, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotIn(List<Integer> values) {
      addCriterion("is_deleted not in", values, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
      addCriterion("is_deleted between", value1, value2, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
      addCriterion("is_deleted not between", value1, value2, "isDeleted");
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

    public Criteria andUserIdLikeInsensitive(String value) {
      addCriterion("upper(user_id) like", value.toUpperCase(), "userId");
      return (Criteria) this;
    }

    public Criteria andUserNameLikeInsensitive(String value) {
      addCriterion("upper(user_name) like", value.toUpperCase(), "userName");
      return (Criteria) this;
    }

    public Criteria andAddressLikeInsensitive(String value) {
      addCriterion("upper(address) like", value.toUpperCase(), "address");
      return (Criteria) this;
    }

    public Criteria andCellphoneLikeInsensitive(String value) {
      addCriterion("upper(cellphone) like", value.toUpperCase(), "cellphone");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table user
   *
   * @mbg.generated do_not_delete_during_merge
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table user
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