package com.enterprise.demo.common.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonUtils {

  private static final Logger log = LoggerFactory.getLogger(GsonUtils.class);

  public static final String MAP_KEY_REGX = "\"\\w+\":";

  public static <T> T parseObject(String json, Class<T> c) {
    return gson().fromJson(json, c);
  }

  /**
   * 将json字符串转换为java对象。
   *
   * @deprecated 因为com.google.common.reflect.TypeToken被标记了@Beta， 所以推荐使用com.google.gson.reflect.TypeToken
   */
  @Deprecated
  public static <T> T parseObject(String json,
      @SuppressWarnings("UnstableApiUsage") TypeToken<T> tt) {
    try {
      return gson().fromJson(json, tt.getType());
    } catch (Exception e) {
      log.error("parse object failed. JSON:" + json, e);
      return null;
    }
  }

  public static <T> T parseObject(String json, com.google.gson.reflect.TypeToken<T> tt) {
    try {
      return gson().fromJson(json, tt.getType());
    } catch (Exception e) {
      log.error("parse object failed. JSON:" + json, e);
      return null;
    }
  }

  /**
   * 将json字符串转换为java对象。使用默认的gson方式解析json。
   */
  @Deprecated
  public static <T> T parseObjectByDefault(String json,
      @SuppressWarnings("UnstableApiUsage") TypeToken<T> tt) {
    try {
      return new Gson().fromJson(json, tt.getType());
    } catch (Exception e) {
      log.error("parse object failed. JSON:" + json, e);
      return null;
    }
  }

  /**
   * 将json字符串转换为java对象。使用默认的gson方式解析json。
   *
   * @deprecated 因为com.google.common.reflect.TypeToken被标记了@Beta， 所以推荐使用com.google.gson.reflect.TypeToken
   */
  @Deprecated
  public static <T> T parseObjectByDefault(String json, com.google.gson.reflect.TypeToken<T> tt) {
    try {
      return new Gson().fromJson(json, tt.getType());
    } catch (Exception e) {
      log.error("parse object failed. JSON:" + json, e);
      return null;
    }
  }

  public static String toJson(Object obj) {
    if (obj == null) {
      return "{}";
    }
    return gson().toJson(obj);
  }

  /**
   * json转换为map，并平铺所有字段
   */
  public static Map<String, Object> toFlatMap(String json) {
    if (InspectionUtils.isBlank(json)) {
      return null;
    }
    Map<String, Object> map = new HashMap<>();
    JsonElement ele = new JsonParser().parse(json);

    if (ele.isJsonObject()) {
      JsonObject jObj = ele.getAsJsonObject();
      jObj.entrySet().forEach(entry -> handleJsonElement(map, entry.getKey(), entry.getValue()));
    } else {
      log.warn("invalid bizContent json: {}", json);
    }

    return map;
  }

  private static void handleJsonElement(
      Map<String, Object> map, String keyPrefix, JsonElement ele) {
    if (ele == null || ele.isJsonNull()) {
      return;
    }
    if (ele.isJsonPrimitive()) {
      JsonPrimitive jp = (JsonPrimitive) ele;
      if (jp.isBoolean()) {
        map.put(keyPrefix, ele.getAsBoolean());
      } else if (jp.isNumber()) {
        map.put(keyPrefix, jp.getAsDouble());
      } else if (jp.isJsonNull()) {
        map.put(keyPrefix, null);
      } else {
        map.put(keyPrefix, jp.getAsString());
      }
      map.put(keyPrefix, ele.getAsString());
    } else if (ele.isJsonArray()) {
      JsonArray jArr = ele.getAsJsonArray();
      if (jArr != null && jArr.size() > 0) {
        for (int i = 0; i < jArr.size(); i++) {
          handleJsonElement(map, keyPrefix + "[" + i + "]", jArr.get(i));
        }
      }
    } else if (ele.isJsonObject()) {
      JsonObject jObj = ele.getAsJsonObject();
      jObj.entrySet()
          .forEach(
              entry ->
                  handleJsonElement(map, keyPrefix + "." + entry.getKey(), entry.getValue()));
    }
  }


  private static GsonBuilder gsonBuilder =
      new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .registerTypeAdapter(
              Date.class,
              (JsonDeserializer<Date>)
                  (json, typeOfT, context) -> {
                    String str = json.getAsString();
                    if (InspectionUtils.isDigit(str)) {
                      return new Date(json.getAsJsonPrimitive().getAsLong());
                    } else {
                      try {
                        return getDateFormat().parse(str);
                      } catch (ParseException e) {
                        log.warn("parse date failed.", e);
                        return null;
                      }
                    }
                  })
          .registerTypeAdapter(
              Long.class,
              (JsonDeserializer<Long>)
                  (json, typeOfT, context) -> {
                    if (json.isJsonNull()) {
                      return null;
                    } else if (json.getAsString().length() == 0) {
                      return null;
                    } else {
                      return json.getAsLong();
                    }
                  })
          .registerTypeAdapter(
              Integer.class,
              (JsonDeserializer<Integer>)
                  (json, typeOfT, context) -> {
                    if (json.isJsonNull()) {
                      return null;
                    } else if (json.getAsString().length() == 0) {
                      return null;
                    } else {
                      return json.getAsInt();
                    }
                  })
          .registerTypeAdapter(
              String.class,
              (JsonDeserializer<String>)
                  (json, typeOfT, context) -> {
                    if (json.isJsonNull()) {
                      return null;
                    } else if (json.isJsonArray() || json.isJsonObject()) {
                      return json.toString();
                    } else {
                      return json.getAsString();
                    }
                  });

  private static SimpleDateFormat getDateFormat() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  }

  private static Gson gson() {
    return gsonBuilder.create();
  }

  public static String prefixUpperJsonConvertLower(String prefixUpperJson) {
    String prefixLowerJson = prefixUpperJson;
    Pattern pattern = Pattern.compile(MAP_KEY_REGX);
    Matcher matcher = pattern.matcher(prefixLowerJson);
    while (matcher.find()) {
      String match = matcher.group();
      String prefixLowerKey = matcher.group().substring(0, 2).toLowerCase() + matcher.group().substring(2);
      prefixLowerJson = prefixLowerJson.replaceFirst(match, prefixLowerKey);
    }
    return prefixLowerJson;
  }

}
