package com.dhaine.banking.service.modules.account.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
public class AccountDTO {
  private String accountNumber;
  private String accountName;
  private String accountPassword;
  private double balance;
}
