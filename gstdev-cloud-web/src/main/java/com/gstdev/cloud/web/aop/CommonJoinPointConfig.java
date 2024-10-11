// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

  @Pointcut("@annotation(com.gstdev.cloud.commons.annotations.TrackMethod)")
  public void trackMethod() {
  }
}
