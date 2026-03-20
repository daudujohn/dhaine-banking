package com.dhaine.banking.core.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhaineApiError {
  private String message;
  private String code;
  private String apiPath;
  private LocalDateTime errorTimeStamp = LocalDateTime.now();
}
