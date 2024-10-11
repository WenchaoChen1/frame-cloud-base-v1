// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DefaultErrorCode {

  GENERAL(2),

  AUTHENTICATION(10),

  JWT_TOKEN_EXPIRED(11),

  CREDENTIALS_EXPIRED(15),

  PERMISSION_DENIED(20),

  INVALID_ARGUMENTS(30),

  BAD_REQUEST_PARAMS(31),

  ITEM_NOT_FOUND(32),

  TOO_MANY_REQUESTS(33),

  TOO_MANY_UPDATES(34);

  private int errorCode;

  DefaultErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  @JsonValue
  public int getErrorCode() {
    return errorCode;
  }
}
