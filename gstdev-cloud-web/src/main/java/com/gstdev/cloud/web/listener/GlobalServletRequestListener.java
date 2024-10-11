// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class GlobalServletRequestListener implements ServletRequestListener {

  private static final ThreadLocal<HttpServletRequest> THREAD_REQUEST = new ThreadLocal<>();

  public static HttpServletRequest getServletRequest() {
    return THREAD_REQUEST.get();
  }

  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    THREAD_REQUEST.remove();
  }

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    if (servletRequest instanceof HttpServletRequest) {
      THREAD_REQUEST.set((HttpServletRequest) servletRequest);
    }
  }
}
