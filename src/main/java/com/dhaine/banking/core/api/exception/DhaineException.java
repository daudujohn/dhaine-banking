package com.dhaine.banking.core.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
public class DhaineException extends RuntimeException {
  private String code = "500000";
  private HttpStatus httpStatus;

  public DhaineException(String message) {
    super(message);
  }

  public DhaineException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
