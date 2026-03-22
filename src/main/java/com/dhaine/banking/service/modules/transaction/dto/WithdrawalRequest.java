package com.dhaine.banking.service.modules.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Getter
public class WithdrawalRequest {
  @NotBlank(message = "Kindly supply a valid account number")
  private String accountNumber;

  @NotBlank(message = "Account password is required")
  private String accountPassword;

  @NotNull(message = "Withdrawn amount is required") @DecimalMin(value = "1.00", message = "Withdrawn amount amount must be at least one")
  private Double withdrawnAmount;
}
