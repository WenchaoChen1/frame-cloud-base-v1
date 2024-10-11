// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.message;

import com.gstdev.cloud.commons.message.AbstractMessage;

public enum GlobalResultMessage implements AbstractMessage {

  // @formatter:off
  MISSING("missing", "资源不存在"),
  MISSING_FIELD("missing_field", "缺少个别字段"),
  INVALID("invalid", "格式不合法"),
  ALREAD_EXISTS("already_exists", "资源已经存在"),
  UNPROCESSABLE("unprocessable", "输入不合法");
  // @formatter:on

  private String code;
  private String message;

  GlobalResultMessage(String code, String message) {
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
