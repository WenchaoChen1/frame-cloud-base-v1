// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.exception;


public class IncorrectParameterException extends RuntimeException {

  private static final long serialVersionUID = 601995650578985289L;

  public IncorrectParameterException(String message) {
    super(message);
  }

  public IncorrectParameterException(String message, Throwable cause) {
    super(message, cause);
  }
}
