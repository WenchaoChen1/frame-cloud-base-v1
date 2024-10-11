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
//import org.springframework.util.Base64Utils;
//
//import java.io.IOException;
//import java.security.cert.CertificateEncodingException;
//import java.security.cert.X509Certificate;
//
//public class SslUtils {
//
//  private SslUtils() {
//  }
//
//  public static String getX509CertificateString(X509Certificate cert) throws CertificateEncodingException, IOException {
//    Base64Utils.encodeToString(cert.getEncoded());
//    return EncryptionUtils.trimNewLines(Base64Utils.encodeToString(cert.getEncoded()));
//  }
//
//  public static String getX509CertificateString(javax.security.cert.X509Certificate cert) throws javax.security.cert.CertificateEncodingException, IOException {
//    Base64Utils.encodeToString(cert.getEncoded());
//    return EncryptionUtils.trimNewLines(Base64Utils.encodeToString(cert.getEncoded()));
//  }
//}
