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
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class DefaultThreadFactory implements ThreadFactory {
//
//  private static final AtomicInteger poolNumber = new AtomicInteger(1);
//  private final ThreadGroup group;
//  private final AtomicInteger threadNumber = new AtomicInteger(1);
//  private final String namePrefix;
//
//  public static DefaultThreadFactory forName(String name) {
//    return new DefaultThreadFactory(name);
//  }
//
//  private DefaultThreadFactory(String name) {
//    SecurityManager s = System.getSecurityManager();
//    group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
//    namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
//  }
//
//  @Override
//  public Thread newThread(Runnable r) {
//    Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
//    if (t.isDaemon()) {
//      t.setDaemon(false);
//    }
//
//    if (t.getPriority() != Thread.NORM_PRIORITY) {
//      t.setPriority(Thread.NORM_PRIORITY);
//    }
//
//    return t;
//  }
//}
