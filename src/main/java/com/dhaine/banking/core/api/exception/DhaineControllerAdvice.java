package com.dhaine.banking.core.api.exception;

import com.dhaine.banking.core.api.response.DhaineApiResponse;
import com.dhaine.banking.core.constant.SystemConstant;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author Daudu John
 * @createdOn Mar-20(Fri)-2026
 */
@Slf4j
@ControllerAdvice
public class DhaineControllerAdvice {

  @ExceptionHandler(DhaineException.class)
  public ResponseEntity<Object> handleDhaineException(DhaineException ex, WebRequest request) {
    logExceptionDetails(ex);

    HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST;

    return buildFailureResponse(ex.getMessage(), status);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<?> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException exception, WebRequest request) {
    logExceptionDetails(exception);
    DhaineApiError dhaineApiError = new DhaineApiError();
    dhaineApiError.setApiPath(request.getContextPath());
    dhaineApiError.setMessage(exception.getMessage());
    return buildFailureResponse(List.of(dhaineApiError), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleValidationException(
      MethodArgumentNotValidException ex, WebRequest request) {

    List<DhaineApiError> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(
                error -> {
                  DhaineApiError dhaineApiError = new DhaineApiError();
                  dhaineApiError.setMessage(error.getDefaultMessage());
                  return dhaineApiError;
                })
            .toList();

    return buildFailureResponse(errors.getFirst().getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleMessageNotReadable(HttpMessageNotReadableException ex) {
    logExceptionDetails(ex);
    return buildFailureResponse(
        "Invalid request body. Please check your JSON syntax.", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGenericError(Exception exception, WebRequest request) {
    logExceptionDetails(exception);
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

  public static ResponseEntity<Object> buildFailureResponse(String message, HttpStatus httpStatus) {
    return new ResponseEntity<>(
        DhaineApiResponse.builder().success(false).message(message).build(), httpStatus);
  }

  private void logExceptionDetails(Exception exception) {
    String exceptionName = exception.getClass().getSimpleName();

    StackTraceElement stackTraceElement =
        Arrays.stream(exception.getStackTrace())
            .filter(f -> f.getClassName().startsWith(SystemConstant.BASE_PACKAGE))
            .findFirst()
            .orElse(null);

    if (stackTraceElement == null) {
      log.error("[{}] — {}", exceptionName, exception.getMessage());
    } else {
      log.error(
          "[{}] thrown at {}.{}() line:{} — {}",
          exceptionName,
          stackTraceElement.getClassName(),
          stackTraceElement.getMethodName(),
          stackTraceElement.getLineNumber(),
          exception.getMessage());
    }
  }
}
