// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.filter;

public class DingTalkAuthenticationFilter extends QRAuthenticationFilter {

  public static String DEFAULT_LOGIN_PROCESS_URL = "/login/";

  public DingTalkAuthenticationFilter() {
    super(DEFAULT_LOGIN_PROCESS_URL);
  }
}
