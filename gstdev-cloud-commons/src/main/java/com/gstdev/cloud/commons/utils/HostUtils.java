// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostUtils {

  /**
   * 获取运行主机ip地址
   *
   * @return ip地址，或者null
   */
  public static String getHostAddress() {
    InetAddress address;
    try {
      address = InetAddress.getLocalHost();
      return address.getHostAddress();
    } catch (UnknownHostException e) {
      return null;
    }
  }
}
