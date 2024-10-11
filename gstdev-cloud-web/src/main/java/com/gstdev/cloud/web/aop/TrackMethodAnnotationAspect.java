// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;

import java.util.Objects;

@Aspect
@Configuration
@Slf4j
public class TrackMethodAnnotationAspect {

  private final Environment environment;

  public TrackMethodAnnotationAspect(Environment environment) {
    this.environment = environment;
  }

  @Around("com.gstdev.cloud.web.aop.CommonJoinPointConfig.trackMethod()")
  public Object trackMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch watch = new StopWatch();
    Integer port = Integer.valueOf(Objects.requireNonNull(environment.getProperty("local.server.port")));

    String name = joinPoint.getSignature().getName();
    String type = joinPoint.getSignature().getDeclaringTypeName();

    log.info("[Riching Cloud] |- Entering in the method {}, in class {}, running on port {}", name, type, port);

    watch.start();
    Object returnObject = joinPoint.proceed();
    watch.stop();

    log.info("[Riching Cloud] |- Method {} in class {} took {} milliseconds to execute.", name, type, watch.getTotalTimeMillis());

    return returnObject;
  }
}
