package com.enterprise.demo.sys.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

  String oldPassword;
  String newPassword;
  String confirmNewPassword;
}
