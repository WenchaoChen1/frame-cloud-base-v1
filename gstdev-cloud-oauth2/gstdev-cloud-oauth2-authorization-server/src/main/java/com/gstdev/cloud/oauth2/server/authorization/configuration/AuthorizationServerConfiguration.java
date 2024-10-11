// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.configuration;


import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationFailureHandler;
import com.gstdev.cloud.oauth2.server.authorization.provider.PasswordAuthenticationProvider;
import com.gstdev.cloud.oauth2.server.authorization.service.DefaultUserDetailsService;
import com.gstdev.cloud.oauth2.server.authorization.service.OAuth2ClientService;
import com.gstdev.cloud.oauth2.server.authorization.service.RedisOAuth2AuthorizationConsentService;
import com.gstdev.cloud.oauth2.server.authorization.service.RedisOAuth2AuthorizationService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.gstdev.cloud.oauth2.server.authorization.converter.PasswordAuthenticationConverter;
import com.gstdev.cloud.oauth2.server.authorization.customizer.DefaultOAuth2TokenCustomizer;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.security.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class AuthorizationServerConfiguration {
  @Resource
  private AuthorizationServerProperties authorizationServerProperties;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
//  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity, JwtDecoder jwtDecoder, BearerTokenResolver bearerTokenResolver, OAuth2AuthorizationService authorizationService) throws Exception {
    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

    DefaultAuthenticationFailureHandler errorResponseHandler = new DefaultAuthenticationFailureHandler();
    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();

    authorizationServerConfigurer.authorizationEndpoint(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));
    authorizationServerConfigurer.clientAuthentication(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));
    authorizationServerConfigurer.tokenRevocationEndpoint(endpoint -> endpoint.errorResponseHandler(errorResponseHandler));

    authorizationServerConfigurer.tokenEndpoint(endpoint -> {
      // @formatter:off
      endpoint.accessTokenRequestConverter(
        new DelegatingAuthenticationConverter(Arrays.asList(
          new OAuth2AuthorizationCodeAuthenticationConverter(),
          new OAuth2RefreshTokenAuthenticationConverter(),
          new OAuth2ClientCredentialsAuthenticationConverter(),
          new PasswordAuthenticationConverter()
        ))
      );
       // @formatter:on
      endpoint.errorResponseHandler(errorResponseHandler);
    });

    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
    // @formatter:off
    httpSecurity.requestMatcher(endpointsMatcher)
      .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))

      .exceptionHandling(handling -> {
        handling.accessDeniedHandler(accessDeniedHandler);
        handling.authenticationEntryPoint(authenticationEntryPoint);
      })
      .oauth2ResourceServer(resourceServer -> {
        resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
        resourceServer.bearerTokenResolver(bearerTokenResolver);

        resourceServer.accessDeniedHandler(accessDeniedHandler);
        resourceServer.authenticationEntryPoint(authenticationEntryPoint);
      })
      .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//      .formLogin(Customizer.withDefaults())
      .apply(authorizationServerConfigurer);
    // @formatter:on

    SecurityFilterChain securityFilterChain = httpSecurity.build();

    AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
    @SuppressWarnings("unchecked")
    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = httpSecurity.getSharedObject(OAuth2TokenGenerator.class);

    PasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
    httpSecurity.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);

    return securityFilterChain;
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository() {

//     @formatter:off
    RegisteredClient codeRegisteredClient = RegisteredClient
    .withId(UUID.randomUUID().toString())
    .clientId("code-client")
    .clientName("Code Client")
    .clientSecret(passwordEncoder.encode("black123"))

    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)

    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)

    .redirectUri("http://127.0.0.1:8080/authorized")
    .redirectUri("http://www.baidu.com")

    .scope("project:read")
    .scope("project:write")

    .clientSettings(ClientSettings.builder()
      .requireAuthorizationConsent(false)
      .requireProofKey(false)
      .build()
    )

    .tokenSettings(TokenSettings.builder()
      .accessTokenTimeToLive(Duration.ofHours(1))
      .refreshTokenTimeToLive(Duration.ofHours(3))
      .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
      .reuseRefreshTokens(true)
      .build()
    )
    .build();

    RegisteredClient credentialsRegisteredClient = RegisteredClient
    .withId(UUID.randomUUID().toString())
    .clientId("credentials-client")
    .clientName("Credentials Client")
    .clientSecret(passwordEncoder.encode("black123"))

    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)

    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)

    .clientSettings(ClientSettings.builder()
      .requireAuthorizationConsent(false)
      .requireProofKey(false)
      .build()
    )

    .tokenSettings(TokenSettings.builder()
      .accessTokenTimeToLive(Duration.ofHours(720))
      .refreshTokenTimeToLive(Duration.ofHours(720))
      .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
      .reuseRefreshTokens(true)
      .build()
    )
    .build();

    RegisteredClient passwordRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("password-client")
      .clientName("Password Client")
      .clientSecret(passwordEncoder.encode("black123"))

      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)

      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)

      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )

      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(720))
        .refreshTokenTimeToLive(Duration.ofHours(720))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();
//     @formatter:on
//    JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

//    /** 此处保留作为client入库代码案例
//     RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//     .clientId("messaging-client")
//     .clientSecret("secret")
//     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//     .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//     .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//     .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//     .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//     .redirectUri("http://127.0.0.1:8080/authorized")
//     .scope(OidcScopes.OPENID)
//     .scope("message.read")
//     .scope("message.write")
//     .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//     .build();
//
//    if (null == registeredClientRepository.findByClientId("messaging-client")) {
//      registeredClientRepository.save(registeredClient);
//      log.info("Add client");
//    }

//     registeredClientRepository.save(registeredClient);
//    return registeredClientRepository;
    return new InMemoryRegisteredClientRepository(codeRegisteredClient, credentialsRegisteredClient, passwordRegisteredClient);
  }

  @Bean
  public AuthorizationServerSettings authorizationServerSettings(AuthorizationServerProperties authorizationServerProperties) {
    // @formatter:off
    return AuthorizationServerSettings.builder()
      .issuer(authorizationServerProperties.getIssuerUri())
      .authorizationEndpoint(authorizationServerProperties.getAuthorizationEndpoint())
      .tokenEndpoint(authorizationServerProperties.getAccessTokenEndpoint())
      .tokenRevocationEndpoint(authorizationServerProperties.getTokenRevocationEndpoint())
      .tokenIntrospectionEndpoint(authorizationServerProperties.getTokenIntrospectionEndpoint())
      .jwkSetEndpoint(authorizationServerProperties.getJwkSetEndpoint())
      .build();
    // @formatter:on
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource() throws Exception {
    String path = authorizationServerProperties.getJksPath();
    String alias = authorizationServerProperties.getJksAlias();
    String pass = authorizationServerProperties.getJksPass();

    ClassPathResource resource = new ClassPathResource(path);
    KeyStore jks = KeyStore.getInstance("jks");
    char[] pin = pass.toCharArray();
    jks.load(resource.getInputStream(), pin);
    RSAKey rsaKey = RSAKey.load(jks, alias, pin);
    JWKSet jwkSet = new JWKSet(rsaKey);

    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> buildCustomizer() {
    DefaultOAuth2TokenCustomizer tokenCustomizer = new DefaultOAuth2TokenCustomizer();
    return tokenCustomizer;
  }

  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    var converter = new JwtAuthenticationConverter();
    var authorities = new JwtGrantedAuthoritiesConverter();

    authorities.setAuthoritiesClaimName("authorities");
    authorities.setAuthorityPrefix("");

    converter.setJwtGrantedAuthoritiesConverter(authorities);

    return converter;
  }

  @Bean
  public BearerTokenResolver bearerTokenResolver() {
    return new DefaultBearerTokenResolver();
  }

  @Bean
  public OAuth2AuthorizationService authorizationService() {
    //Redis存储token
    return new InMemoryOAuth2AuthorizationService();
  }

  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService() {
    return new RedisOAuth2AuthorizationConsentService();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new DefaultUserDetailsService();
  }
}
