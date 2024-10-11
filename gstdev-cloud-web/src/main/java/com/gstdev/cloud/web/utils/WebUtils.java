// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class WebUtils {

  public static HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  public static HttpServletResponse getResponse() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }

  public static void renderJson(HttpServletResponse response, Object object) {
    renderJson(response, JsonUtils.toJson(object), MediaType.APPLICATION_JSON.toString());
  }

  public static void renderJson(HttpServletResponse response, String string, String type) {
    try {
      response.setContentType(type);
      response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
      response.getWriter().print(string);
      response.getWriter().flush();
      response.getWriter().close();
    } catch (IOException e) {
      log.error("[Riching Cloud] |- Render response to Json error!");
    }
  }

  public static HttpServletRequest toHttp(ServletRequest request) {
    return (HttpServletRequest) request;
  }

  public static HttpServletResponse toHttp(ServletResponse response) {
    return (HttpServletResponse) response;
  }
}
