package com.dhaine.banking.core.api.response;

/**
 * @author Daudu John
 * @createdOn Mar-21(Sat)-2026
 */
public class ControllerResponse {

  public static DhaineApiResponse success(Object data, String message) {
    return DhaineApiResponse.builder()
        .success(true)
        .responseCode(0)
        .message(message)
        .data(data)
        .build();
  }

  public static DhaineApiResponse success(String message) {
    return DhaineApiResponse.builder().success(true).responseCode(0).message(message).build();
  }
}
