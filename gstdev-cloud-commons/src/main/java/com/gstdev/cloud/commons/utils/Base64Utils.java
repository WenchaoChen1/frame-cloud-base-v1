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
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//
//public class Base64Utils {
//
//  public static String encode(BufferedImage bufferedImage, String imgType) throws IOException {
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    ImageIO.write(bufferedImage, imgType, outputStream);
//
//    return encode(outputStream);
//  }
//
//  public static String encode(ByteArrayOutputStream outputStream) {
//    return Base64.getEncoder().encodeToString(outputStream.toByteArray());
//  }
//
//  public static BufferedImage decode2Img(String base64) throws IOException {
//    byte[] bytes = Base64.getDecoder().decode(base64.getBytes("utf-8"));
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//
//    return ImageIO.read(inputStream);
//  }
//}
