package com.enterprise.demo.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 操作日期类的工具类
 */
public class DateUtils {

  private static final String YYYY_MM_DD = "yyyy-MM-dd";
  private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

  /**
   * 当天的开始时间点，即0时0分0秒。
   */
  public static Date startOfDay(Date date) {
    return asDate(asLocalDate(date));
  }

  /**
   * 当天的最后一刻
   */
  public static Date endOfDay(Date date) {
    LocalDateTime localDateTime = asLocalDate(date).atStartOfDay().plusDays(1);
    return asDate(localDateTime.minus(1, ChronoUnit.NANOS));
  }

  /**
   * 将 <b>yyyy-MM-dd HH:mm:ss</b> 格式的字符串转换为 Date
   */
  public static Date parseDatetime(String formattedDate) {
    if (InspectionUtils.isBlank(formattedDate)) {
      throw new IllegalArgumentException("parameter of formattedDate is blank!");
    }
    try {
      return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(formattedDate);
    } catch (ParseException e) {
      throw new RuntimeException("parse datetime failed. formattedDate:" + formattedDate, e);
    }
  }

  /**
   * 将 <b>yyyy-MM-dd</b> 格式的字符串转换为 Date
   */
  public static Date parseDate(String formattedDate) {
    if (InspectionUtils.isBlank(formattedDate)) {
      throw new IllegalArgumentException("parameter of formattedDate is blank!");
    }
    try {
      return new SimpleDateFormat(YYYY_MM_DD).parse(formattedDate);
    } catch (ParseException e) {
      throw new RuntimeException("parse date failed. formattedDate：" + formattedDate, e);
    }
  }

  /**
   * 将Date格式化为 <b>yyyy-MM-dd</b> 格式的字符串
   */
  public static String formatDate(Date date) {
    return new SimpleDateFormat(YYYY_MM_DD).format(date);
  }

  /**
   * 将Date格式化为 <b>yyyy-MM-dd HH:mm:ss</b> 格式的字符串
   */
  public static String formatDatetime(Date date) {
    return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
  }

  /**
   * 计算两个日期间的天数。 结果不包括开始日期，包括结束日期。 例如：getDayDelta('2018-01-25', '2018-01-26') = 1
   */
  public static int getDayDelta(Date start, Date end) {
    return (int) asLocalDate(start).until(asLocalDate(end), ChronoUnit.DAYS);
  }

  /**
   * 将字符串中的数字转换为Date，如果字符串中不包含数字，则返回null。 强制将字符串中的数字部分依次转换为年、月、日、时、分、秒。 如果数字部分只有一个则只转换年，如果有两个则转换为年月，以此类推。
   */
  public static Date parseViolently(String s) {
    if (InspectionUtils.isBlank(s)) {
      return null;
    }
    String[] arr = s.trim().split("\\D+");
    if (arr.length > 0) {
      int year = Integer.parseInt(arr[0]);
      int month = arr.length > 1 ? Integer.parseInt(arr[1]) : 1;
      int day = arr.length > 2 ? Integer.parseInt(arr[2]) : 1;
      int hour = arr.length > 3 ? Integer.parseInt(arr[3]) : 0;
      int minute = arr.length > 4 ? Integer.parseInt(arr[4]) : 0;
      int second = arr.length > 5 ? Integer.parseInt(arr[5]) : 0;
      ZonedDateTime zdt = ZonedDateTime.of(year, month, day, hour, minute, second,
          0, ZoneId.systemDefault());
      return Date.from(zdt.toInstant());
    } else {
      return null;
    }
  }

  /**
   * 从 1970-01-01 00:00:00 以来的天数
   */
  public static long epochDayCount(Date day) {
    return (day.getTime() / 1000 + OffsetDateTime.now().getOffset().getTotalSeconds()) /
        ChronoUnit.DAYS.getDuration().getSeconds();
  }

  public static Date asDate(LocalDate localDate) {
    if (localDate == null) {
      return null;
    }
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date asDate(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate asLocalDate(Date date) {
    if (date == null) {
      return null;
    }
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalTime asLocalTime(Date date) {
    if (date == null) {
      return null;
    }
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
  }

  public static LocalDateTime asLocalDateTime(Date date) {
    if (date == null) {
      return null;
    }
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static Date addDays(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusDays(delta));
  }

  public static Date addMonths(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusMonths(delta));
  }

  public static Date addYears(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusYears(delta));
  }

  public static Date addSeconds(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusSeconds(delta));
  }

  public static Date addMinites(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusMinutes(delta));
  }

  public static Date addHours(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusHours(delta));
  }

  public static Date addWeeks(Date date, int delta) {
    return asDate(asLocalDateTime(date).plusWeeks(delta));
  }

  /**
   * 判断date1是否在date2之前。只考虑天，同一天的不同时分秒认为是同一天。
   */
  public static boolean isDayBefore(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }
    return asLocalDate(date1).isBefore(asLocalDate(date2));
  }

  /**
   * 判断date1是否在date2之前或者同一天。只考虑天，同一天的不同时分秒认为是同一天。
   */
  public static boolean isDayBeforeOrSame(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }
    LocalDate ld1 = asLocalDate(date1);
    LocalDate ld2 = asLocalDate(date2);
    return ld1.isBefore(ld2) || ld1.isEqual(ld2);
  }

  /**
   * 判断date1是否在date2之后。只考虑天，同一天的不同时分秒认为是同一天。
   */
  public static boolean isDayAfter(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }
    return asLocalDate(date1).isAfter(asLocalDate(date2));
  }

  /**
   * 判断date1是否在date2之后。只比时间，不比天。
   */
  public static boolean isTimeAfter(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }

    LocalTime time1 = asLocalTime(date1);
    LocalTime time2 = asLocalTime(date2);
    return time1.isAfter(time2);
  }

  /**
   * 判断date1是否在date2之后或者同一天。只考虑天，同一天的不同时分秒认为是同一天。
   */
  public static boolean isDayAfterOrSame(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }
    LocalDate ld1 = asLocalDate(date1);
    LocalDate ld2 = asLocalDate(date2);
    return ld1.isAfter(ld2) || ld1.isEqual(ld2);
  }


  /**
   * 判断date1和date2是否为同一天。
   */
  public static boolean isSameDay(Date date1, Date date2) {
    if (date1 == null || date2 == null) {
      throw new IllegalArgumentException("日期参数为空");
    }
    return asLocalDate(date1).equals(asLocalDate(date2));
  }


  /**
   * 判断指定日期是否在今天之前
   */
  public static boolean isBeforeToday(Date date) {
    return isDayBefore(date, new Date());
  }

  /**
   * 判断指定日期是否在今天之前
   */
  public static boolean isAfterToday(Date date) {
    return isDayAfter(date, new Date());
  }

  /**
   * 构建Date实例
   */
  public static Date dateOf(int year) {
    return asDate(LocalDate.of(year, 1, 1));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12
   */
  public static Date dateOf(int year, int month) {
    return asDate(LocalDate.of(year, month, 1));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12
   */
  public static Date dateOf(int year, int month, int dayOfMonth) {
    return asDate(LocalDate.of(year, month, dayOfMonth));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12 小时参数范围是：0 - 23
   */
  public static Date dateOf(int year, int month, int dayOfMonth, int hour) {
    return asDate(LocalDateTime.of(year, month, dayOfMonth, hour, 0));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12 小时参数范围是：0 - 23
   */
  public static Date dateOf(int year, int month, int dayOfMonth, int hour, int minute) {
    return asDate(LocalDateTime.of(year, month, dayOfMonth, hour, minute));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12 小时参数范围是：0 - 23
   */
  public static Date dateOf(int year, int month, int dayOfMonth, int hour, int minute, int second) {
    return asDate(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
  }

  /**
   * 构建Date实例。 月份参数范围是：1 - 12 小时参数范围是：0 - 23 纳秒参数范围是：0 - 999,999,999
   */
  public static Date dateOf(int year, int month, int dayOfMonth, int hour, int minute, int second,
      int nanoOfSecond) {
    LocalDateTime ldt = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    return asDate(ldt.withNano(nanoOfSecond));
  }
}
