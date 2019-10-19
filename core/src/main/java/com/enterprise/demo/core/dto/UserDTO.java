package com.enterprise.demo.core.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

  @NotBlank(message = "userName is blank")
  private String userName;
  @NotBlank(message = "address is blank")
  private String address;
  @NotBlank(message = "cellphone is blank")
  private String cellphone;
}
