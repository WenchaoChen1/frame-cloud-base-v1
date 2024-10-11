//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Tech <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.utils;
//
//import java.awt.*;
//
//public class ColorUtils {
//
//  /**
//   * 全透明颜色
//   */
//  public static Color OPACITY = ColorUtils.int2color(0x00FFFFFF);
//
//  /**
//   * 米黄色
//   */
//  public static Color OFF_WHITE = ColorUtils.int2color(0xFFF7EED6);
//
//  public static Color int2color(int color) {
//    int a = (0xff000000 & color) >>> 24;
//    int r = (0x00ff0000 & color) >> 16;
//    int g = (0x0000ff00 & color) >> 8;
//    int b = (0x000000ff & color);
//
//    return new Color(r, g, b, a);
//  }
//
//  public static String int2htmlColor(int color) {
//    int a = (0xff000000 & color) >>> 24;
//    int r = (0x00ff0000 & color) >> 16;
//    int g = (0x0000ff00 & color) >> 8;
//    int b = (0x000000ff & color);
//
//    return "#" + NumUtils.toHex(r) + NumUtils.toHex(g) + NumUtils.toHex(b) + NumUtils.toHex(a);
//  }
//}
