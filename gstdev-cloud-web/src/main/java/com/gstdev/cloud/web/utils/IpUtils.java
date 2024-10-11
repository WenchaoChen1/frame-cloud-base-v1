// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

  public static String getIPAddress(HttpServletRequest request) {
    if (request == null) {
      return "unknown";
    }

    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("X-Forwarded-For");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("X-Real-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
  }

  public static String getMultistageReverseProxyIp(String ip) {
    if (ip != null && ip.indexOf(",") > 0) {
      final String[] ips = ip.trim().split(",");
      for (String subIp : ips) {
        if (false == isUnknown(subIp)) {
          ip = subIp;
          break;
        }
      }
    }

    return ip;
  }

  public static boolean isUnknown(String checkString) {
    return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
  }
}
