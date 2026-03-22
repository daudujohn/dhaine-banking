package com.dhaine.banking.service.modules.authentication.service;

import com.dhaine.banking.core.api.exception.DhaineException;
import com.dhaine.banking.service.modules.account.dto.AccountDTO;
import com.dhaine.banking.service.modules.authentication.dto.LoginRequest;
import com.dhaine.banking.service.modules.authentication.dto.LoginResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final PasswordEncoder passwordEncoder;

  public LoginResponse login(LoginRequest loginRequest, AccountDTO accountDTO) {
    boolean matches =
        passwordEncoder.matches(loginRequest.getAccountPassword(), accountDTO.getAccountPassword());

    if (!matches) throw new DhaineException("Wrong credentials", HttpStatus.UNAUTHORIZED);

    String credentials = loginRequest.getAccountNumber() + "::" + accountDTO.getAccountPassword();

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setAccessToken(
        Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8)));
    return loginResponse;
  }
}
