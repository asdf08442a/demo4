package com.enterprise.demo.sys.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {

  private Integer status;
  private String msg;
  private T data;
}
