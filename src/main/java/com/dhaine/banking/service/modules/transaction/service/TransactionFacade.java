package com.dhaine.banking.service.modules.transaction.service;

import com.dhaine.banking.core.api.exception.DhaineException;
import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.service.AccountService;
import com.dhaine.banking.service.modules.transaction.dto.DepositRequest;
import com.dhaine.banking.service.modules.transaction.dto.TransactionRecordDTO;
import com.dhaine.banking.service.modules.transaction.dto.WithdrawalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Service
@RequiredArgsConstructor
public class TransactionFacade {
  private final AccountService accountService;
  private final TransactionHistoryService transactionHistoryService;

  public void deposit(DepositRequest depositRequest) {
    AccountDTO accountDTO = accountService.retrieveAccount(depositRequest.getAccountNumber());

    double newBalance = accountDTO.getBalance() + depositRequest.getAmount();

    accountDTO.setBalance(newBalance);

    accountService.updateAccount(accountDTO);

    TransactionRecordDTO transactionRecordDTO = new TransactionRecordDTO();
    transactionRecordDTO.setTransactionType("CREDIT");
    transactionRecordDTO.setAmount(depositRequest.getAmount());
    transactionRecordDTO.setAccountBalance(newBalance);
    transactionHistoryService.createTransactionHistory(
        accountDTO.getAccountNumber(), transactionRecordDTO);
  }

  public void withdraw(WithdrawalRequest withdrawalRequest) {
    AccountDTO accountDTO = accountService.retrieveAccount(withdrawalRequest.getAccountNumber());

    double balanceAfterWithdrawal =
        accountDTO.getBalance() - withdrawalRequest.getWithdrawnAmount();

    if (balanceAfterWithdrawal < 500) {
      throw new DhaineException(
          "You do not have sufficient funds to process this transaction. Kindly top-up your account");
    }

    accountDTO.setBalance(balanceAfterWithdrawal);

    accountService.updateAccount(accountDTO);

    TransactionRecordDTO transactionRecordDTO = new TransactionRecordDTO();
    transactionRecordDTO.setTransactionType("DEBIT");
    transactionRecordDTO.setAmount(withdrawalRequest.getWithdrawnAmount());
    transactionRecordDTO.setAccountBalance(balanceAfterWithdrawal);
    transactionHistoryService.createTransactionHistory(
        accountDTO.getAccountNumber(), transactionRecordDTO);
  }
}
