// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.utils;

import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils extends org.springframework.web.util.WebUtils {

  public static String getCookieValue(String name) {
    HttpServletRequest request = WebUtils.getRequest();
    Assert.notNull(request, "Request from RequestContextHolder is null");

    return getCookieValue(request, name);
  }

  public static String getCookieValue(HttpServletRequest request, String name) {
    Cookie cookie = getCookie(request, name);
    return cookie != null ? cookie.getValue() : null;
  }

  public static void removeCookie(HttpServletResponse response, String key) {
    setCookie(response, key, null, 0);
  }

  public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAgeInSeconds);
    cookie.setHttpOnly(true);

    response.addCookie(cookie);
  }
}
