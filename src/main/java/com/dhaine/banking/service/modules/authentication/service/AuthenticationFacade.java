package com.dhaine.banking.service.modules.authentication.service;

import com.dhaine.banking.core.api.exception.DhaineException;
import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.account.service.AccountService;
import com.dhaine.banking.service.modules.authentication.dto.LoginRequest;
import com.dhaine.banking.service.modules.authentication.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Service
@RequiredArgsConstructor
public class AuthenticationFacade {
  private final AccountService accountService;
  private final AuthenticationService authenticationService;

  public LoginResponse login(LoginRequest loginRequest) {
    AccountDTO accountDTO;
    try {
      accountDTO = accountService.retrieveAccount(loginRequest.getAccountNumber());
    } catch (DhaineException dhaineException) {
      throw new DhaineException("Wrong credentials", HttpStatus.UNAUTHORIZED);
    }

    return authenticationService.login(loginRequest, accountDTO);
  }
}
