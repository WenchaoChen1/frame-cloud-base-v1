// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.web.utils;

import cn.hutool.core.util.ObjectUtil;
import com.gstdev.cloud.web.exception.BadRequestException;


public class ValidationUtils {

  public static void isNull(Object obj, String entity, String parameter, Object value) {
    if (ObjectUtil.isNull(obj)) {
      String msg = entity + " 不存在: " + parameter + " is " + value;
      throw new BadRequestException(msg);
    }
  }
}
