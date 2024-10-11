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
//import com.google.common.util.concurrent.FutureCallback;
//import com.google.common.util.concurrent.Futures;
//import com.google.common.util.concurrent.ListenableFuture;
//import com.google.common.util.concurrent.MoreExecutors;
//
//import java.util.concurrent.Executor;
//import java.util.function.Consumer;
//
//public class DonAsynchron {
//
//  public static <T> void withCallback(ListenableFuture<T> future, Consumer<T> onSuccess,
//                                      Consumer<Throwable> onFailure) {
//    withCallback(future, onSuccess, onFailure, null);
//  }
//
//  public static <T> void withCallback(ListenableFuture<T> future, Consumer<T> onSuccess,
//                                      Consumer<Throwable> onFailure, Executor executor) {
//    FutureCallback<T> callback = new FutureCallback<T>() {
//      @Override
//      public void onSuccess(T result) {
//        try {
//          onSuccess.accept(result);
//        } catch (Throwable th) {
//          onFailure(th);
//        }
//      }
//
//      @Override
//      public void onFailure(Throwable t) {
//        onFailure.accept(t);
//      }
//    };
//
//    if (executor != null) {
//      Futures.addCallback(future, callback, executor);
//    } else {
//      Futures.addCallback(future, callback, MoreExecutors.directExecutor());
//    }
//  }
//}
