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
//import cn.hutool.core.img.gif.GifDecoder;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class ImageLoadUtils {
//
//  /**
//   * 根据路径获取图片
//   *
//   * @param path 本地路径 or 网络地址
//   * @return 图片
//   * @throws IOException
//   */
//  public static BufferedImage getImageByPath(String path) throws IOException {
//    if (StringUtils.isBlank(path)) {
//      return null;
//    }
//
//    InputStream stream = FileUtils.getInputStream(path);
//
//    return ImageIO.read(stream);
//  }
//
//  /**
//   * 根据路径获取gif图片
//   *
//   * @param path
//   * @return
//   * @throws IOException
//   */
//  public static GifDecoder getGifByPath(String path) throws IOException {
//    if (StringUtils.isBlank(path)) {
//      return null;
//    }
//
//    GifDecoder decoder = new GifDecoder();
//    decoder.read(FileUtils.getInputStream(path));
//
//    return decoder;
//  }
//}
