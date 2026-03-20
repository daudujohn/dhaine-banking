package com.dhaine.banking.core.api.exception;

import com.dhaine.banking.core.api.response.DhaineApiResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@ControllerAdvice
public class DhaineControllerAdvice {

  @ExceptionHandler(DhaineException.class)
  public ResponseEntity<Object> handleDhaineException(DhaineException ex, WebRequest request) {

    HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST;

    return buildFailureResponse(ex.getErrors(), status);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<?> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException exception, WebRequest request) {
    DhaineApiError dhaineApiError = new DhaineApiError();
    dhaineApiError.setApiPath(request.getContextPath());
    dhaineApiError.setMessage(exception.getMessage());
    return buildFailureResponse(List.of(dhaineApiError), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGenericError(Exception exception, WebRequest request) {
    DhaineApiError dhaineApiError = new DhaineApiError();
    dhaineApiError.setApiPath(request.getContextPath());
    dhaineApiError.setMessage(exception.getMessage());
    return buildFailureResponse(List.of(dhaineApiError), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static ResponseEntity<Object> buildFailureResponse(
      List<DhaineApiError> apiErrors, HttpStatus httpStatus) {
    String message = "";
    if (httpStatus.is4xxClientError()) {
      message =
          "Kindly verify that you are providing the correct information. Check the errors field for details on what might be wrong with your request.";
    } else if (httpStatus.is5xxServerError()) {
      message =
          "We encountered an issue processing your request. Please contact support for assistance.";
    }

    return new ResponseEntity<>(
        DhaineApiResponse.builder().success(false).message(message).errors(apiErrors).build(),
        httpStatus);
  }
}
