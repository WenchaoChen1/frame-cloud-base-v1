// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer status = HttpStatus.BAD_REQUEST.value();

  public BadRequestException(String msg) {
    super(msg);
  }

  public BadRequestException(HttpStatus status, String msg) {
    super(msg);
    this.status = status.value();
  }

  public BadRequestException(Exception ex) {
    ex.printStackTrace();
  }

  public BadRequestException(HttpStatus status, Exception ex) {
    this.status = status.value();
    ex.printStackTrace();
  }

  public BadRequestException(String msg, Exception ex) {
    super(msg);
    ex.printStackTrace();
  }

  public BadRequestException(HttpStatus status, String msg, Exception ex) {
    super(msg);
    this.status = status.value();
    ex.printStackTrace();
  }
}
