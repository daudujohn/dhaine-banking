package com.dhaine.banking.service.modules.authentication.controller;

import static com.dhaine.banking.core.api.constant.ApiVersionConstant.API_V1;
import static com.dhaine.banking.core.api.constant.AuthenticationApiConstant.AUTHENTICATION_CONTROLLER_DESCRIPTION;
import static com.dhaine.banking.core.api.constant.AuthenticationApiConstant.AUTHENTICATION_CONTROLLER_TITLE;

import com.dhaine.banking.core.api.response.ControllerResponse;
import com.dhaine.banking.core.api.response.DhaineApiResponse;
import com.dhaine.banking.service.modules.authentication.dto.LoginRequest;
import com.dhaine.banking.service.modules.authentication.dto.LoginResponse;
import com.dhaine.banking.service.modules.authentication.service.AuthenticationFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daudu John
 * @createdOn Mar-22(Sun)-2026
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1)
@Tag(name = AUTHENTICATION_CONTROLLER_TITLE, description = AUTHENTICATION_CONTROLLER_DESCRIPTION)
public class AuthenticationController {
  public final AuthenticationFacade authenticationFacade;

  @PostMapping("/login")
  public DhaineApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    return ControllerResponse.success(authenticationFacade.login(loginRequest), "Login successful");
  }
}
