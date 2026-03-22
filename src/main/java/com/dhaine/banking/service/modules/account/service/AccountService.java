package com.dhaine.banking.service.modules.account.service;

import com.dhaine.banking.core.api.exception.DhaineException;
import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.dto.CreateAccountRequest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
  private final PasswordEncoder passwordEncoder;
  private final Map<String, AccountDTO> accounts = new HashMap<>();
  private final SecureRandom random = new SecureRandom();

  public AccountDTO createAccount(CreateAccountRequest createAccountRequest) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setAccountName(createAccountRequest.getAccountName());
    accountDTO.setAccountPassword(
        passwordEncoder.encode(createAccountRequest.getAccountPassword()));
    accountDTO.setBalance(createAccountRequest.getInitialDeposit());
    accountDTO.setAccountNumber(generateAccountNumber());

    accounts.put(accountDTO.getAccountNumber(), accountDTO);

    log.info(ObjectUtils.toString(accounts));

    return accountDTO;
  }

  public AccountDTO retrieveAccount(String accountNumber) {
    AccountDTO accountDTO = accounts.get(accountNumber);

    if (accountDTO == null) throw new DhaineException("Account not found");

    return accountDTO;
  }

  public AccountDTO retrieveAccountInformation(String accountNumber) {
    AccountDTO accountDTO = this.retrieveAccount(accountNumber);
    accountDTO.setAccountPassword(null);
    return accountDTO;
  }

  public AccountDTO updateAccount(AccountDTO accountDTO) {
    AccountDTO accountToUpdate = this.retrieveAccount(accountDTO.getAccountNumber());

    accountToUpdate.setBalance(accountDTO.getBalance());

    accounts.put(accountDTO.getAccountNumber(), accountToUpdate);

    return accountToUpdate;
  }

  private static String generateAccountNumber() {
    String numbers = "0123456789";
    StringBuilder sb = new StringBuilder();
    Random random = new SecureRandom();

    for (int i = 0; i < 10; i++) {
      int index = random.nextInt(numbers.length());
      sb.append(numbers.charAt(index));
    }

    return sb.toString();
  }
}
