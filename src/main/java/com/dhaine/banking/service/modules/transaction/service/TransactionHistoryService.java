package com.dhaine.banking.service.modules.transaction.service;

import com.dhaine.banking.service.modules.transaction.dto.TransactionRecordDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Service
@RequiredArgsConstructor
public class TransactionHistoryService {
  private final Map<String, List<TransactionRecordDTO>> transactionRecords = new HashMap<>();

  public List<TransactionRecordDTO> retrieveTransactionHistory(String accountNumber) {
    return transactionRecords.getOrDefault(accountNumber, new ArrayList<>());
  }

  public void createTransactionHistory(
      String accountNumber, TransactionRecordDTO transactionRecordDTO) {
    List<TransactionRecordDTO> accountTransactionRecords =
        this.retrieveTransactionHistory(accountNumber);

    transactionRecordDTO.setTransactionDate(LocalDate.now());
    accountTransactionRecords.add(transactionRecordDTO);
    transactionRecords.put(accountNumber, accountTransactionRecords);
  }
}
