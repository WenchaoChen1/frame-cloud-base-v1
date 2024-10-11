//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Tech <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.concurrent;
//
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class TheadFactoryName implements ThreadFactory {
//
//  private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
//  private final ThreadGroup group;
//  private final AtomicInteger threadNumber = new AtomicInteger(1);
//  private final String namePrefix;
//
//  public TheadFactoryName() {
//    this("riching-pool");
//  }
//
//  private TheadFactoryName(String name) {
//    SecurityManager s = System.getSecurityManager();
//    group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
//
//    this.namePrefix = name + POOL_NUMBER.getAndIncrement();
//  }
//
//  @Override
//  public Thread newThread(Runnable r) {
//    Thread t = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
//    if (t.isDaemon()) {
//      t.setDaemon(false);
//    }
//    if (t.getPriority() != Thread.NORM_PRIORITY) {
//      t.setPriority(Thread.NORM_PRIORITY);
//    }
//
//    return t;
//  }
//}
