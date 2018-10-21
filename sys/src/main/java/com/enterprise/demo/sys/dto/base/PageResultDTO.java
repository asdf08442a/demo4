package com.enterprise.demo.sys.dto.base;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResultDTO {

  private List rows;
  private Long total;
}
