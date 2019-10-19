package com.enterprise.demo.core.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserIdDTO {

  @NotBlank(message = "userId is blank")
  private String userId;
}
