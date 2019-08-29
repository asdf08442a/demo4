package com.enterprise.demo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

  private int code;
  private String msg;
  private T data;
}
