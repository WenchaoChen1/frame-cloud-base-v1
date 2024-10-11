// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

  private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


  /**
   * 获取指定日期（月份）第一天
   *
   * @param date
   * @return
   */
  public static Date getFirstDayOfMonth(String date) {
    int year = Integer.parseInt(date.substring(0, 4));
    int month = Integer.parseInt(date.substring(5, 7));
    Calendar cal = Calendar.getInstance();
    //设置年份
    cal.set(Calendar.YEAR, year);
    //设置月份
    cal.set(Calendar.MONTH, month - 1);
    //获取某月最小天数
    int firstDay = cal.getMinimum(Calendar.DATE);
    //设置日历中月份的最小天数
    cal.set(Calendar.DAY_OF_MONTH, firstDay);
    ////格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date parse = null;
    try {
      parse = sdf.parse(sdf.format(cal.getTime()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }

  /**
   * 获取指定日期（月份）最后一天
   *
   * @param date
   * @return
   */
  public static Date getLastDayOfMonth(String date) {
    int year = Integer.parseInt(date.substring(0, 4));
    int month = Integer.parseInt(date.substring(5, 7));
    Calendar cal = Calendar.getInstance();
    //设置年份
    cal.set(Calendar.YEAR, year);
    //设置月份
    cal.set(Calendar.MONTH, month - 1);
    //获取某月最大天数
    int lastDay = cal.getActualMaximum(Calendar.DATE);
    //设置日历中月份的最大天数
    cal.set(Calendar.DAY_OF_MONTH, lastDay);
    //格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date parse = null;
    try {
      parse = sdf.parse(sdf.format(cal.getTime()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }

  /**
   * 根据当前日期获取本月第一天
   *
   * @return
   */
  public static Date getFirstTime(Date d) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, 0);
    c.setTime(d);
    c.set(Calendar.DAY_OF_MONTH, 1);
    //设置为1号,当前日期既为本月第一天
    c.set(Calendar.DAY_OF_MONTH, 1);
    //将小时至0
    c.set(Calendar.HOUR_OF_DAY, 0);
    //将分钟至0
    c.set(Calendar.MINUTE, 0);
    //将秒至0
    c.set(Calendar.SECOND, 0);
    //将毫秒至0
    c.set(Calendar.MILLISECOND, 0);
    String first = format.format(c.getTime());
    Date date = null;
    try {
      date = format.parse(first);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * 根据当前日期获取本月最后一天
   *
   * @return
   */
  public static Date getLastTime(Date d) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar ca = Calendar.getInstance();
    ca.setTime(d);
    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
    //将小时至23
    ca.set(Calendar.HOUR_OF_DAY, 23);
    //将分钟至59
    ca.set(Calendar.MINUTE, 59);
    //将秒至59
    ca.set(Calendar.SECOND, 59);
    //将毫秒至999
    ca.set(Calendar.MILLISECOND, 999);
    String last = format.format(ca.getTime());
    Date date = null;
    try {
      date = format.parse(last);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * 获取上个月的今天
   *
   * @return 日期
   */
  public static Date getLastMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    return calendar.getTime();
  }

  //计算两个日期相差天数
  public static int differentDaysByMillisecond(Date date1, Date date2) {
    return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
  }

  //年月转日期
  public static Date monthToDate(String monthValue) {
    //格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date parse = null;
    try {
      parse = sdf.parse(monthValue);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }

  /**
   * 取当天0点时间
   *
   * @return /
   */
  public static Date getTodayDate() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String year = timestamp.toString().substring(0, 4);
    String month = timestamp.toString().substring(5, 7);
    String day = timestamp.toString().substring(8, 10);
    String date = year + "-" + month + "-" + day + " 00:00:00";
    SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
    Date parse = new Date();
    try {
      parse = format.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }


  /**
   * 指定日期 获取日期0点
   */
  public static Date getToDayStartDate(String date) {
    date += " 00:00:00";
    SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
    Date parse = new Date();
    try {
      parse = format.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }

  /**
   * 指定日期 获取日期24点
   */
  public static Date getToDayEndDate(String date) {
    date += " 23:59:59";
    SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
    Date parse = new Date();
    try {
      parse = format.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }


  /**
   * 根据Date类型数据取出当天0点
   *
   * @param date 时间
   * @return /
   * @throws ParseException
   */
  public static Date getTodayDateFirst(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime();
  }

  /**
   * 根据Date类型数据取出当天24点
   *
   * @param date 时间
   * @return /
   * @throws ParseException 时间转换异常
   */
  public static Date getTodayDateLast(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();

  }

  /**
   * 取出当天24点时间
   */

  public static Date getNowDay() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String now = format.format(date);
    now = now.substring(0, 10) + " 23:59:59";

    try {
      return format.parse(now);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取三天前的凌晨时间
   *
   * @return 时间
   */
  public static Date getFewDaysAgo() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, -2);
    date = calendar.getTime();
    String date1 = format.format(date);
    date1 = date1.substring(0, 10) + " 00:00:00";
    try {
      return format.parse(date1);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取月末 的 日期  只获取 日
   *
   * @param year  年
   * @param month 月
   * @return 月末
   */
  public static int getLastDayByMonth(String year, String month) {
    Calendar cal = Calendar.getInstance();
    //年
    cal.set(Calendar.YEAR, Integer.parseInt(year));
    //月，因为Calendar里的月是从0开始，所以要-1
    cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
    //日，设为一号
    cal.set(Calendar.DATE, 1);
    //月份加一，得到下个月的一号
    cal.add(Calendar.MONTH, 1);
    //下一个月减一为本月最后一天
    cal.add(Calendar.DATE, -1);

    return cal.get(Calendar.DAY_OF_MONTH);//获得月末是几号
  }

  /**
   * 获取上个月
   *
   * @return 上个月的 年月
   */
  public static String getMonthByBefore() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date); // 设置为当前时间
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
    date = calendar.getTime();
    return format.format(date);
  }

  /**
   * 获取格式化后的当前时间
   *
   * @return 格式化后时间
   */
  public static String getNowDayFormat(String format) {
    return new SimpleDateFormat(format).format(new Date());
  }

}
