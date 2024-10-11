// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.cloud.commons.web;

import java.security.MessageDigest;

public class SHA1Utils {
  private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  /**
   * Takes the raw bytes from the digest and formats them correct.
   *
   * @param bytes the raw bytes from the digest.
   * @return the formatted bytes.
   */
  public static String getFormattedText(byte[] bytes) {
    int len = bytes.length;
    StringBuilder buf = new StringBuilder(len * 2);

    for (int j = 0; j < len; j++) {
      buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
      buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
    }

    return buf.toString();
  }

  public static String encode(String str) {
    if (str == null) {
      return null;
    }

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(str.getBytes());

      return getFormattedText(messageDigest.digest());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
