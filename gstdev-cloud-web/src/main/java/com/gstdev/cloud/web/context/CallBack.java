// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.context;

public interface CallBack {

  void executor();

  default String getCallBackName() {
    return Thread.currentThread().getId() + ":" + this.getClass().getName();
  }
}

