// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.exception;

import com.gstdev.cloud.commons.utils.ThrowableUtils;
import com.gstdev.cloud.web.domain.Error;
import com.gstdev.cloud.web.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Object> NoHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());

    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> validationMethodArgumentException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("验证不通过");

    BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
    if (!fieldErrorList.isEmpty()) {
      List<Error> errors = new ArrayList<Error>();
      fieldErrorList.forEach(fieldError -> {
        Error err = new Error();
        err.setResource(fieldError.getObjectName());
        err.setField(fieldError.getField());
        err.setCode(fieldError.getCode());
        err.setMessage(fieldError.getDefaultMessage());

        errors.add(err);
      });

      errorResponse.setErrors(errors);
    }

    return buildResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handleException(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
    log.error(ThrowableUtils.getStackTrace(ex));

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());

    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse, HttpStatus status) {
    return new ResponseEntity<>(errorResponse, status);
  }
}
