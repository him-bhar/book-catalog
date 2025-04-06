package com.bookcatalog.rest;

import com.bookcatalog.rest.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(exception = {UnsupportedOperationException.class})
  public ResponseEntity<ErrorResponse> handleBadRequest(UnsupportedOperationException ex) {
    return ResponseEntity.badRequest().body(ErrorResponse.builder().description(ex.getMessage()).build());
  }
}
