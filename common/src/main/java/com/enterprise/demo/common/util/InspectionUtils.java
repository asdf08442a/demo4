package com.enterprise.demo.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 数据有效性判断的工具类。 主要是空判断，包括各种集合和字符串的空判断。 另外还有数字、手机号、数字相等的判断。
 */
public class InspectionUtils {

  public static boolean isEmpty(CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static boolean isEmpty(Collection<?> coll) {
    return coll == null || coll.isEmpty();
  }

  public static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  public static boolean isEmpty(boolean[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(byte[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(short[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(int[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(long[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(float[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(double[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(char[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmpty(Object[] arr) {
    return arr == null || arr.length == 0;
  }

  public static boolean isEmtpy(Optional<?> opt) {
    return !isNotEmpty(opt);
  }

  /**
   * 判断对象是否为空。 根据对象的不同类型，判断方法也不同，具体如下： CharSequence:  obj == null || obj.length == 0 Collection: obj == null ||
   * obj.size() == 0 Map:           obj == null || obj.size() == 0 array:         obj == null || obj.length == 0 other
   * object:  obj == null
   *
   * @param obj 任意对象
   */
  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    } else if (obj instanceof CharSequence) {
      return isEmpty((CharSequence) obj);
    } else if (obj instanceof Collection) {
      return isEmpty((Collection<?>) obj);
    } else if (obj instanceof Map) {
      return isEmpty((Map<?, ?>) obj);
    } else if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    } else {
      return false;
    }
  }

  public static boolean isNotEmpty(CharSequence cs) {
    return !isEmpty(cs);
  }

  public static boolean isNotEmpty(Collection<?> coll) {
    return !isEmpty(coll);
  }

  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }

  public static boolean isNotEmpty(boolean[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(byte[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(short[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(int[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(long[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(float[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(double[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(char[] arr) {
    return !isEmpty(arr);
  }

  public static boolean isNotEmpty(Object[] arr) {
    return !isEmpty(arr);
  }

  /**
   * 判断对象是否为空。 根据对象的不同类型，判断方法也不同，具体如下： CharSequence:  obj != null && obj.length > 0 Collection: obj != null &&
   * obj.size() > 0 Map:           obj != null && obj.size() > 0 array:         obj != null && obj.length > 0 other
   * object:  obj != null
   *
   * @param obj 任意对象
   */
  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }

  public static boolean isNotEmpty(Optional<?> opt) {
    return opt.isPresent() && isNotEmpty(opt.get());
  }

  public static boolean isBlank(CharSequence cs) {
    if (isEmpty(cs)) {
      return true;
    }
    for (int i = 0; i < cs.length(); i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNotBlank(CharSequence cs) {
    return !isBlank(cs);
  }

  private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");

  public static boolean isDigit(CharSequence cs) {
    return !isBlank(cs) && DIGIT_PATTERN.matcher(cs).matches();
  }

  public static boolean isEqualNumbers(Number a, Number b) {
    return (a == null && b == null) || (a != null && b != null && a.doubleValue() == b
        .doubleValue());
  }

  public static boolean isMobile(String str) {
    return isDigit(str) && str.length() == 11;
  }

  private static final Pattern ID_NO_PTN = Pattern.compile("[1-9]\\d{13}(\\d{3})?[0-9xX]");

  public static boolean isIdNo(String str) {
    return isNotBlank(str) && ID_NO_PTN.matcher(str).matches();
  }


  /**
   * 判断是否是中文，包括生僻字和繁体字
   */
  public static boolean isChinese(String str) {
    if (InspectionUtils.isBlank(str)) {
      return false;
    }
    for (char c : str.toCharArray()) {
      if (!isChinese(c)) {
        return false;
      }
    }
    return true;
  }

  private static final char MIN_ZH = '\u4e00';
  private static final char MAX_ZH = '\u9fa5';

  /**
   * 判断是否是中文字符，包括生僻字和繁体字
   */
  public static boolean isChinese(char c) {
    return MIN_ZH <= c && c <= MAX_ZH;
  }

  /**
   * 判断是否属于中国词语。合法字符包括：汉字（包括生僻字和繁体字）、英文字母、数字
   */
  public static boolean isZhWord(String str) {
    if (isBlank(str)) {
      return false;
    }
    for (char c : str.toCharArray()) {
      if (!isChinese(c) && !Character.isLetterOrDigit(c)) {
        return false;
      }
    }
    return true;
  }

}
