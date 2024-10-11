// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuth {
  private String username;

  private String password;
}
