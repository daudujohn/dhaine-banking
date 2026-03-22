package com.dhaine.banking.service.modules.transaction.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Getter
public class DepositRequest {
  @NotBlank(message = "Kindly supply a valid account number")
  private String accountNumber;

  @NotNull(message = "Deposit amount is required") @DecimalMin(value = "1.00", message = "Deposit amount must be at least one")
  @DecimalMax(value = "1000000.00", message = "Deposit amount must be at most a million")
  private Double amount;
}
