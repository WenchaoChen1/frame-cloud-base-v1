// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

  private static final List<CallBack> CALL_BACKS = new ArrayList<>();

  private static ApplicationContext applicationContext = null;
  private static boolean addCallback = true;

  public synchronized static void addCallBacks(CallBack callBack) {
    if (addCallback) {
      SpringContextHolder.CALL_BACKS.add(callBack);
    } else {
      log.warn("[Riching Cloud] |- CallBack: {} cannot be added, execute directly", callBack.getCallBackName());
      callBack.executor();
    }
  }

  public static Object getBean(String name) {
    assertContextInjected();
    return applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> clazz) {
    assertContextInjected();
    return applicationContext.getBean(clazz);
  }

  public static <T> T getBean(String name, Class<T> clazz) {
    assertContextInjected();
    return applicationContext.getBean(name, clazz);
  }

  public static <T> T getProperties(String property, T defaultValue, Class<T> requiredType) {
    T result = defaultValue;

    try {
      result = getBean(Environment.class).getProperty(property, requiredType);
    } catch (Exception ignored) {
    }

    return result;
  }

  public static String getProperties(String property) {
    return getProperties(property, null, String.class);
  }

  public static <T> T getProperties(String property, Class<T> requiredType) {
    return getProperties(property, null, requiredType);
  }

  private static void assertContextInjected() {
    if (applicationContext == null) {
      throw new IllegalStateException("[Riching Cloud] |- applicaitonContext cannot be injected, please define SpringContextHolder in applicationContext" + ".xml or register SpringContextHolder in SpringBoot start class.");
    }
  }

  private static void clearHolder() {
    log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
    applicationContext = null;
  }

  @Override
  public void destroy() {
    SpringContextHolder.clearHolder();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringContextHolder.applicationContext != null) {
      log.warn("[Riching Cloud] |- SpringContextHolder ApplicationContext is override, original ApplicationContext is: " + SpringContextHolder.applicationContext);
    }

    SpringContextHolder.applicationContext = applicationContext;
    if (addCallback) {
      for (CallBack callBack : SpringContextHolder.CALL_BACKS) {
        callBack.executor();
      }
      CALL_BACKS.clear();
    }

    SpringContextHolder.addCallback = false;
  }
}
