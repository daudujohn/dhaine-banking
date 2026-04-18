package com.dhaine.banking.service.modules.transaction.controller;

import static com.dhaine.banking.core.api.constant.ApiVersionConstant.API_V1;
import static com.dhaine.banking.core.api.constant.PaginationConstant.*;
import static com.dhaine.banking.core.api.constant.TransactionApiConstant.TRANSACTION_CONTROLLER_DESCRIPTION;
import static com.dhaine.banking.core.api.constant.TransactionApiConstant.TRANSACTION_CONTROLLER_TITLE;

import com.dhaine.banking.core.api.response.ControllerResponse;
import com.dhaine.banking.core.api.response.DhaineApiResponse;
import com.dhaine.banking.service.modules.transaction.dto.DepositRequest;
import com.dhaine.banking.service.modules.transaction.dto.WithdrawalRequest;
import com.dhaine.banking.service.modules.transaction.service.TransactionFacade;
import com.dhaine.banking.service.modules.transaction.service.TransactionHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1)
@Tag(name = TRANSACTION_CONTROLLER_TITLE, description = TRANSACTION_CONTROLLER_DESCRIPTION)
public class TransactionController {
  private final TransactionFacade transactionFacade;
  private final TransactionHistoryService transactionService;

  @PostMapping("/deposit")
  public DhaineApiResponse deposit(@Valid @RequestBody DepositRequest depositRequest) {
    transactionFacade.deposit(depositRequest);
    return ControllerResponse.success("Your deposit request has been processed successfully");
  }

  @PostMapping("/withdrawal")
  public DhaineApiResponse withdraw(@Valid @RequestBody WithdrawalRequest withdrawalRequest) {
    transactionFacade.withdraw(withdrawalRequest);
    return ControllerResponse.success("Your withdrawal request has been processed successfully");
  }

  @GetMapping("/account_statement/{accountNumber}")
  public DhaineApiResponse retrieveHistory(
      @PathVariable String accountNumber,
      @RequestParam(value = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false)
          int pageNumber,
      @RequestParam(value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false)
          int pageSize) {
    return ControllerResponse.success(
        transactionService.retrieveTransactionHistory(accountNumber, pageNumber, pageSize),
        "Transaction history retrieved successfully");
  }
}
