package com.enterprise.demo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

  private int code;
  private String msg;
  private T data;
}
