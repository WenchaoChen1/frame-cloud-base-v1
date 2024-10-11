package com.gstdev.cloud.oauth2.server.authorization.service;

import com.gstdev.cloud.commons.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

  @Autowired
  private RedisUtils redisUtils;

  private static final Long TIMEOUT = 10L;

  @Override
  public void save(OAuth2AuthorizationConsent authorizationConsent) {
    Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");

    redisUtils.set(buildKey(authorizationConsent), authorizationConsent, TIMEOUT,
      TimeUnit.MINUTES);
  }

  @Override
  public void remove(OAuth2AuthorizationConsent authorizationConsent) {
    Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");
    redisUtils.del(buildKey(authorizationConsent));
  }

  @Override
  public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
    Assert.hasText(registeredClientId, "registeredClientId cannot be empty");
    Assert.hasText(principalName, "principalName cannot be empty");
    return (OAuth2AuthorizationConsent) redisUtils.get(buildKey(registeredClientId, principalName));
  }

  private static String buildKey(String registeredClientId, String principalName) {
    return "token:consent:" + registeredClientId + ":" + principalName;
  }

  private static String buildKey(OAuth2AuthorizationConsent authorizationConsent) {
    return buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
  }
}
