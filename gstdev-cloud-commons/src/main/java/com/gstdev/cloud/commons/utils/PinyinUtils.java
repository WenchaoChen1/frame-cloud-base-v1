// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.cloud.commons.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Slf4j
public class PinyinUtils {

  private PinyinUtils() {
    throw new IllegalStateException("PinyinUtils");
  }

  public static String getFirstLetter(String chinese) {
    StringBuilder pinyinStr = new StringBuilder();
    char[] newChar = chinese.toCharArray();

    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

    for (char c : newChar) {
      if (c > 128) {
        try {
          pinyinStr.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0].charAt(0));
        } catch (BadHanyuPinyinOutputFormatCombination e) {
          log.error(e.getMessage(), e);
        }
      } else {
        pinyinStr.append(c);
      }
    }

    return pinyinStr.toString();
  }

  public static String getPinyin(String chinese) {
    StringBuilder pinyinStr = new StringBuilder();
    char[] newChar = chinese.toCharArray();

    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

    for (char c : newChar) {
      if (c > 128) {
        try {
          pinyinStr.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0]);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
          log.error(e.getMessage(), e);
        }
      } else {
        pinyinStr.append(c);
      }
    }

    return pinyinStr.toString();
  }
}
