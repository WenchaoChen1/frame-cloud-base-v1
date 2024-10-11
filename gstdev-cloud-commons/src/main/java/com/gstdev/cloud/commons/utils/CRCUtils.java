//// ====================================================
////
//// This file is part of the Riching Cloud Platform.
////
//// Create by Riching Technology <support@richingtech.com>
//// Copyright (c) 2020-2025 richingtech.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.utils;
//
//public class CRCUtils {
//
//  public static String getCRC2(byte[] bytes) {
//    int CRC = 0x0000ffff;
//    int POLYNOMIAL = 0x0000a001;
//
//    int i, j;
//    for (i = 0; i < bytes.length; i++) {
//      CRC ^= (int) bytes[i];
//      for (j = 0; j < 8; j++) {
//        if ((CRC & 0x00000001) == 1) {
//          CRC >>= 1;
//          CRC ^= POLYNOMIAL;
//        } else {
//          CRC >>= 1;
//        }
//      }
//    }
//    //CRC = ( (CRC & 0x0000FF00) >> 8) | ( (CRC & 0x000000FF ) << 8);
//    return Integer.toHexString(CRC);
//  }
//}
