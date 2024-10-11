
// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.token;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

@Getter
public class QRAuthenticationToken extends PreAuthenticatedAuthenticationToken {
  private String type;
  private String clientId;
  private String code;


  public QRAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }

  public QRAuthenticationToken(Object principal, Object credentials) {
    super(principal, credentials, null);
    setAuthenticated(false);
  }

  public QRAuthenticationToken(String type, String clientId, String code) {
    super(clientId, code, null);

    this.type = type;
    this.clientId = clientId;
    this.code = code;

    setAuthenticated(false);
  }
}
