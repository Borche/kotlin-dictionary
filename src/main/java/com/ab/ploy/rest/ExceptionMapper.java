/* Copyright © 2021 */
package com.ab.ploy.rest;

import com.ab.ploy.rest.model.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(MongoWriteException.class)
  public ResponseEntity<com.ab.ploy.rest.model.ErrorResponse> handleMongoWriteException(
      MongoWriteException e) {
    if (e.getMessage().contains("duplicate key error")) {
      return ResponseEntity.badRequest()
          .body(new ErrorResponse("Word already exists in that language"));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @ExceptionHandler
  public ResponseEntity<com.ab.ploy.rest.model.ErrorResponse> handleInvalidFormatException(
      InvalidFormatException e) {
    return ResponseEntity.badRequest().body(new ErrorResponse("Invalid value or format"));
  }
}
