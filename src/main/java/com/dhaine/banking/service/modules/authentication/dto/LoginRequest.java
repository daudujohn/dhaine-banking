package com.dhaine.banking.service.modules.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Getter
@Setter
public class LoginRequest {
  @NotBlank(message = "Account number cannot be blank")
  private String accountNumber;

  @NotBlank(message = "Account password cannot be blank")
  private String accountPassword;
}
