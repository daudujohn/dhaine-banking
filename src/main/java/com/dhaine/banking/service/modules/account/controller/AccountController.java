package com.dhaine.banking.service.modules.account.controller;

import static com.dhaine.banking.core.api.constant.AccountApiConstant.*;
import static com.dhaine.banking.core.api.constant.ApiVersionConstant.API_V1;

import com.dhaine.banking.core.api.response.ControllerResponse;
import com.dhaine.banking.core.api.response.DhaineApiResponse;
import com.dhaine.banking.service.modules.account.dto.CreateAccountRequest;
import com.dhaine.banking.service.modules.account.service.AccountFacade;
import com.dhaine.banking.service.modules.account.service.AccountService;
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
@Tag(name = ACCOUNT_CONTROLLER_TITLE, description = ACCOUNT_CONTROLLER_DESCRIPTION)
public class AccountController {
  private final AccountFacade accountFacade;
  private final AccountService accountService;

  @PostMapping("/create_account")
  public DhaineApiResponse createAccount(
      @Valid @RequestBody CreateAccountRequest createAccountRequest) {
    return ControllerResponse.success(
        accountFacade.createAccount(createAccountRequest), "Account created successfully");
  }

  @GetMapping("/account_info/{accountNumber}")
  public DhaineApiResponse retrieveAccount(@PathVariable String accountNumber) {
    return ControllerResponse.success(
        accountService.retrieveAccountInformation(accountNumber),
        "Account information retrieved successfully");
  }
}
