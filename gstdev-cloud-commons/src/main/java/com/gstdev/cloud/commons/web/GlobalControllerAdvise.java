//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Tech <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.web;
//
//import com.gstdev.cloud.commons.message.ResponseErrorMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.ObjectUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalControllerAdvise {
//
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  public Result<String> handleException(Exception ex, Object Func) {
//    log.error("Throwable: {}", ex.getMessage());
//    ex.printStackTrace();
//    String message;
//
//    if (ex instanceof MethodArgumentNotValidException) {
//      StringBuilder errMsg = new StringBuilder();
//      BindingResult bindResult = ((MethodArgumentNotValidException) ex).getBindingResult();
//      List<FieldError> fieldErrorList = bindResult.getFieldErrors();
//      fieldErrorList.forEach(fieldErrors -> {
//          if (errMsg.length() > 0) {
//            errMsg.append(",");
//          }
//          errMsg.append(fieldErrors.getDefaultMessage());
//        }
//      );
//      message = errMsg.toString();
//    } else {
//      message = ObjectUtils.isEmpty(ex.getMessage()) ? ResponseErrorMessage.RESP_INTERNAL_SERVER_ERROR.getMessage() : ex.getMessage();
//    }
//
//    return Result.fail(ResponseErrorMessage.RESP_INTERNAL_SERVER_ERROR.getCode(), message);
//  }
//}
