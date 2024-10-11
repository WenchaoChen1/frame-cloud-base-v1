// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.exception;

public class DefaultException extends Exception {

  private static final long serialVersionUID = 1L;

  private DefaultErrorCode errorCode;

  public DefaultException() {
    super();
  }

  public DefaultException(DefaultErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public DefaultException(String message, DefaultErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public DefaultException(String message, Throwable cause, DefaultErrorCode errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public DefaultException(Throwable cause, DefaultErrorCode errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public DefaultErrorCode getErrorCode() {
    return errorCode;
  }
}
