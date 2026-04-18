package com.dhaine.banking.service.modules.transaction.service;

import com.dhaine.banking.core.api.request.PaginationRequest;
import com.dhaine.banking.core.api.response.PaginatedResponse;
import com.dhaine.banking.service.modules.transaction.dto.TransactionRecordDTO;
import java.time.LocalDate;
import java.util.*;
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

  public PaginatedResponse<TransactionRecordDTO> retrieveTransactionHistory(
      String accountNumber, int pageNumber, int pageSize) {
    //    TODO: add account ownership check
    List<TransactionRecordDTO> transactionList = this.retrieveTransactionRecords(accountNumber);
    int transactionSize = transactionList.size();

    PaginationRequest paginationRequest = new PaginationRequest();
    paginationRequest.setPageNumber(pageNumber);
    paginationRequest.setPageSize(pageSize);

    List<TransactionRecordDTO> transactionContent =
        transactionList.subList(
            paginationRequest.getPageStart(), paginationRequest.getPageEnd(transactionSize));
    return PaginatedResponse.<TransactionRecordDTO>builder()
        .content(transactionContent)
        .isFirstPage(paginationRequest.isFirstPage())
        .isLastPage(paginationRequest.isLastPage(transactionSize))
        .currentPage(paginationRequest.getPageNumber())
        .totalPages(paginationRequest.getTotalPages(transactionSize))
        .totalItems(transactionSize)
        .build();
  }

  public void createTransactionHistory(
      String accountNumber, TransactionRecordDTO transactionRecordDTO) {
    List<TransactionRecordDTO> accountTransactionRecords =
        this.retrieveTransactionRecords(accountNumber);

    transactionRecordDTO.setTransactionDate(LocalDate.now());
    accountTransactionRecords.add(transactionRecordDTO);
    transactionRecords.put(accountNumber, accountTransactionRecords);
  }

  private List<TransactionRecordDTO> retrieveTransactionRecords(String accountNumber) {
    return transactionRecords.getOrDefault(accountNumber, new ArrayList<>());
  }
}
