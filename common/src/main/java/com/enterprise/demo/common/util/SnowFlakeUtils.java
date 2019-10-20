package com.enterprise.demo.common.util;

import java.net.SocketException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SnowFlakeUtils {

  /**
   * 2019-01-01
   */
  private static final long twepoch = 1546272000000L;
  private static final long workerIdBits = 5L;
  private static final long datacenterIdBits = 5L;
  private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
  private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  private static final long sequenceBits = 12L;
  private static final long workerIdShift = sequenceBits;
  private static final long datacenterIdShift = sequenceBits + workerIdBits;
  private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

  private static long workerId;
  private static long datacenterId;
  private static long sequence = 0L;
  private static long lastTimestamp = -1L;

  /**
   * Init.
   */
  @PostConstruct
  public void init() {
    try {
      String ip = IpUtils.getRealIp();
      if (InspectionUtils.isEmpty(ip)) {
        throw new RuntimeException("IdWorker get ip is empty");
      }
      SnowFlakeUtils.workerId = SnowFlakeUtils.datacenterId = Math.abs(ip.hashCode() % 31);
      log.info(
          String.format("ip:%s,workerId:%s,datacenterId；%s", ip, workerId, datacenterId));
    } catch (SocketException e) {
      log.error("init error,error:{}", e);
      throw new RuntimeException("IdWorker init error");
    }
  }

  /**
   * Next id long.
   *
   * @return the long
   */
  public static synchronized long nextId() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(
          String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = timestamp;

    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
        | (workerId << workerIdShift) | sequence;
  }

  /**
   * Til next millis long.
   *
   * @param lastTimestamp the last timestamp
   * @return the long
   */
  protected static long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  /**
   * Time gen long.
   *
   * @return the long
   */
  protected static long timeGen() {
    return System.currentTimeMillis();
  }
}
