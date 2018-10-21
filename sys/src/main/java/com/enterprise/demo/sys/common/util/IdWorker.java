package com.enterprise.demo.sys.common.util;

import java.net.SocketException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Snowflake
 */
@Slf4j
public class IdWorker {

  /**
   * 起始时间戳
   */
  private static final long twepoch = 1532671831229L;// 1288834974657L 1532671831229L
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

  private static IdWorker permissionIdWorker = new IdWorker(0);
  private static IdWorker roleIdWorker = new IdWorker(1);
  private static IdWorker userIdWorker = new IdWorker(2);

  /**
   * Instantiates a new Id worker.
   *
   * @param workerId the worker id
   */
  public IdWorker(long workerId) {
    try {
      String ip = IpUtils.getRealIp();
      if (StringUtils.isEmpty(ip)) {
        throw new RuntimeException("IdWorker get ip is empty");
      }
      this.datacenterId = Math.abs(ip.hashCode() % 31);
      check(workerId, datacenterId);
      log.info("ip:{},workerId:{},datacenterId:{}", ip, workerId, datacenterId);
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
  private static long tilNextMillis(long lastTimestamp) {
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
  private static long timeGen() {
    return System.currentTimeMillis();
  }

  public static long genPermissionId() {
    return permissionIdWorker.nextId();
  }

  public static long genRoleId() {
    return roleIdWorker.nextId();
  }

  public static long genUserId() {
    return userIdWorker.nextId();
  }

  private void check(long workerId, long datacenterId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(String
          .format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
  }

}
