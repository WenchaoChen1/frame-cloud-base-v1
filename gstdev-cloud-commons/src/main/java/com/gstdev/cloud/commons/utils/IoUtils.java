// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.utils;

import java.io.*;

public class IoUtils {

  public static ByteArrayInputStream toByteArrayInputStream(InputStream inputStream) throws IOException {
    if (inputStream instanceof ByteArrayInputStream) {
      return (ByteArrayInputStream) inputStream;
    }

    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
      BufferedInputStream br = new BufferedInputStream(inputStream);
      byte[] b = new byte[1024];
      for (int c; (c = br.read(b)) != -1; ) {
        bos.write(b, 0, c);
      }

      b = null;
      br.close();
      inputStream.close();

      return new ByteArrayInputStream(bos.toByteArray());
    }
  }
}
