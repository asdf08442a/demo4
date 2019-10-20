package com.enterprise.demo.common.redis;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedisLock {

  private static RedissonClient redisson = RedisLockManager.getRedisson();

  public static boolean lock(String key) {
    RLock rLock = redisson.getLock(key);
    // 加锁，并且设置锁过期时间，防止死锁的产生
    rLock.lock(30, TimeUnit.SECONDS);
    return true;
  }

  public static void unlock(String key) {
    redisson.getLock(key).unlock();
  }
}
