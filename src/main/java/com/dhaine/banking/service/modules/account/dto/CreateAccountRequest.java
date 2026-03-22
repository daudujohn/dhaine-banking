package com.dhaine.banking.service.modules.account.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
public class CreateAccountRequest {
  @NotBlank(message = "Account name cannot be blank")
  private String accountName;

  @NotBlank(message = "Account password cannot be blank")
  private String accountPassword;

  @NotNull(message = "An initial deposit is required to create an account") @DecimalMin(value = "500.00", message = "Initial deposit must be at least five hundred")
  private Double initialDeposit;
}
