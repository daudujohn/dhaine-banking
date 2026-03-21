package com.dhaine.banking.service.modules.account.service;

import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.dto.CreateAccountRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Service
@RequiredArgsConstructor
public class AccountService {
  private final Map<String, AccountDTO> accounts = new HashMap<>();

  public AccountDTO createAccount(CreateAccountRequest createAccountRequest) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setAccountName(createAccountRequest.getAccountName());
    accountDTO.setAccountPassword(createAccountRequest.getAccountPassword());
    accountDTO.setBalance(createAccountRequest.getInitialDeposit());
    //            TODO: make it globally random
    accountDTO.setAccountNumber(LocalDateTime.now().toString());

    accounts.put(accountDTO.getAccountNumber(), accountDTO);

    return accountDTO;
  }
}
