// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.web;

import com.gstdev.cloud.commons.message.ErrorMessage;

public class Result<T> {

  private Boolean success;
  private String message;
  private String code;
  private T data;

  private Result(){

  }

  private Result(Boolean success, String message, String code, T data) {
    this.success = success;
    this.message = message;
    this.code = code;
    this.data = data;
  }

  public static <T> Result<T> of(Boolean success, String msg, String code, T data) {
    return new Result<>(success, msg, code, data);
  }

  public static <T> Result<T> of(Boolean success, String msg, T data) {
    return of(success, msg, "0", data);
  }

  public static <T> Result<T> success(String msg, T data) {
    return of(Boolean.TRUE, msg, data);
  }

  public static <T> Result<T> fail(String code, String msg, T data) {
    return of(Boolean.FALSE, msg, code, data);
  }

  public static <T> Result<T> fail(String msg, T data) {
    return of(Boolean.FALSE, msg, data);
  }

  public static <T> Result<T> success() {
    return success(null, null);
  }

  public static <T> Result<T> success(String msg) {
    return success(msg, null);
  }

  public static <T> Result<T> success(T data) {
    return success(null, data);
  }

  public static <T> Result<T> fail(String msg) {
    return of(Boolean.FALSE, msg, null);
  }

  public static <T> Result<T> fail(String code, String msg) {
    return of(Boolean.FALSE, msg, code, null);
  }

  public static <T> Result<T> fail(ErrorMessage errorMessage) {
    return fail(errorMessage.getCode(), errorMessage.getMessage());
  }

  public static <T> Result<T> fail(ErrorMessage errorMessage, T data) {
    return fail(errorMessage.getCode(), errorMessage.getMessage(), data);
  }

  public static <T> Result<T> fail(T data) {
    return fail("", data);
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
