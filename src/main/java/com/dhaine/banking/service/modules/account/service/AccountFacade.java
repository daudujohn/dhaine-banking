package com.dhaine.banking.service.modules.account.service;

import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.dto.CreateAccountRequest;
import com.dhaine.banking.service.modules.account.dto.CreateAccountResponse;
import com.dhaine.banking.service.modules.transaction.dto.TransactionRecordDTO;
import com.dhaine.banking.service.modules.transaction.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountFacade {
  private final AccountService accountService;
  private final TransactionHistoryService transactionHistoryService;

  public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
    AccountDTO accountDTO = accountService.createAccount(createAccountRequest);

    TransactionRecordDTO transactionRecordDTO = new TransactionRecordDTO();
    transactionRecordDTO.setTransactionType("CREDIT");
    transactionRecordDTO.setAmount(accountDTO.getBalance());
    transactionRecordDTO.setAccountBalance(accountDTO.getBalance());
    transactionHistoryService.createTransactionHistory(
        accountDTO.getAccountNumber(), transactionRecordDTO);

    CreateAccountResponse createAccountResponse = new CreateAccountResponse();
    createAccountResponse.setAccountName(accountDTO.getAccountName());
    createAccountResponse.setAccountNumber(accountDTO.getAccountNumber());
    return createAccountResponse;
  }
}
