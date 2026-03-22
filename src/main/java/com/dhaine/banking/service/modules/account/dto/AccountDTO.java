package com.dhaine.banking.service.modules.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
  private String accountNumber;
  private String accountName;
  @ToString.Exclude private String accountPassword;
  private double balance;
}
