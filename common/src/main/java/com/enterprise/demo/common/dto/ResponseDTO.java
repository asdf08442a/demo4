package com.enterprise.demo.common.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@Data
@RequiredArgsConstructor
public class ResponseDTO<T> {

  private int code;
  private String msg;
  private T data;
}
