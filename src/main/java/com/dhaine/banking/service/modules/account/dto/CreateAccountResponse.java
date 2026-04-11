package com.dhaine.banking.service.modules.account.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
public class CreateAccountResponse {
  private String accountName;
  private String accountNumber;
}
