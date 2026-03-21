package com.dhaine.banking.service.modules.account.controller;

import static com.dhaine.banking.core.api.constant.AccountApiConstant.*;

import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.dto.CreateAccountRequest;
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
@RequestMapping()
@Tag(name = ACCOUNT_CONTROLLER_TITLE, description = ACCOUNT_CONTROLLER_DESCRIPTION)
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/create_account")
  public AccountDTO createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
    return accountService.createAccount(createAccountRequest);
  }
}
