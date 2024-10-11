// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.message;

public enum ResponseErrorMessage implements ErrorMessage {

  RESP_PARAM_MISS("RESP000001", "Param Missing"),
  RESP_PARAM_TYPE_ERROR("RESP000002", "Param Type Error"),
  RESP_PARAM_BIND_ERROR("RESP000003", "Param Bind Error"),
  RESP_PARAM_VALID_ERROR("RESP000004", "Param Invalid Error"),
  RESP_NOT_FOUND("RESP000005", "Not Found"),
  RESP_MSG_NOT_READABLE("RESP000006", "Message not Readable"),
  RESP_METHOD_NOT_SUPPORTED("RESP000007", "Method not Support"),
  RESP_MEDIA_TYPE_NOT_SUPPORTED("RESP000008", "Media Type not Support"),
  RESP_INTERNAL_SERVER_ERROR("RESP000009", "Internal Server Error");

  private String code;
  private String message;

  ResponseErrorMessage(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
