package com.dhaine.banking.core.api.response;

import com.dhaine.banking.core.api.exception.DhaineApiError;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DhaineApiResponse<T> {
  private Integer responseCode;
  private boolean success;
  private String message;
  private T data;
  private List<DhaineApiError> errors;
}
