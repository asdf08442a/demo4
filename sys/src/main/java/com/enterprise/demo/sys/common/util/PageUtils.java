package com.enterprise.demo.sys.common.util;

/**
 * @author superzheng
 * @version V1.0
 * @date 2018年7月11日
 */
public class PageUtils {

  public static Integer getPageNo(Integer limit, Integer offset) {
    return offset == 0 ? 1 : offset / limit + 1;
  }
}
