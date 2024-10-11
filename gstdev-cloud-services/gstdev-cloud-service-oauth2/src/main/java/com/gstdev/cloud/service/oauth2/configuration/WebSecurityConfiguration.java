// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.oauth2.configuration;

import java.util.ArrayList;
import java.util.List;

import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAccessDeniedHandler;
import com.gstdev.cloud.oauth2.server.authorization.handler.DefaultAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@Slf4j
@EnableWebSecurity
@EnableConfigurationProperties(WhiteListProperties.class)
public class WebSecurityConfiguration {

  @Resource
  private WhiteListProperties whiteListProperties;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UrlBasedCorsConfigurationSource configurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    corsConfiguration.setAllowedHeaders(List.of("*"));
    corsConfiguration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    return corsConfigurationSource;
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpsSecurity, JwtDecoder jwtDecoder, BearerTokenResolver bearerTokenResolver, UrlBasedCorsConfigurationSource configurationSource) throws Exception {
    DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
    DefaultAuthenticationEntryPoint authenticationEntryPoint = new DefaultAuthenticationEntryPoint();
    List<String> whiteList = whiteListProperties.getWhitelist();

    // @formatter:off
    httpsSecurity.authorizeRequests(authorizeRequests -> {
      for (String s : whiteList) {
        authorizeRequests.antMatchers(s).permitAll();
        log.info(s);
      }
      authorizeRequests.antMatchers("/swagger-ui/**","/swagger-resources/**", "/v3/**").permitAll();
      authorizeRequests.anyRequest().authenticated();
    })
      .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .csrf().disable()
      .cors(cors -> cors.configurationSource(configurationSource))
//      .formLogin(Customizer.withDefaults())
      .exceptionHandling(handling -> {
        handling.accessDeniedHandler(accessDeniedHandler);
        handling.authenticationEntryPoint(authenticationEntryPoint);
      })
      .oauth2ResourceServer(resourceServer -> {
        resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder));
        resourceServer.bearerTokenResolver(bearerTokenResolver);

        resourceServer.accessDeniedHandler(accessDeniedHandler);
        resourceServer.authenticationEntryPoint(authenticationEntryPoint);
      });
    // @formatter:on

    return httpsSecurity.build();
  }
}
