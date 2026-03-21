package com.dhaine.banking.core.api.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@RestControllerAdvice(basePackages = "com.dhaine.banking.service.modules")
public class DhaineResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  private static final String DEFAULT_SUCCESS_MESSAGE = "Request completed successfully";

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (body instanceof DhaineApiResponse<?>) return body;

    return DhaineApiResponse.builder()
        .success(true)
        .message(DEFAULT_SUCCESS_MESSAGE)
        .data(body)
        .build();
  }
}
