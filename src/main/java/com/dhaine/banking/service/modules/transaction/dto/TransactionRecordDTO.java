package com.dhaine.banking.service.modules.transaction.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Getter
@Setter
public class TransactionRecordDTO {
  private LocalDate transactionDate;
  private String transactionType;
  private String narration;
  private Double amount;
  private Double accountBalance;
}
