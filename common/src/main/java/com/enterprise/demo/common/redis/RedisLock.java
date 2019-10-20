package com.enterprise.demo.common.redis;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedisLock {

  private static RedissonClient redisson = RedisLockManager.getRedisson();

  public static boolean lock(String lockKey) {
    return lock(lockKey, 30);
  }

  public static boolean lock(String lockKey, long leaseTime) {
    return lock(lockKey, leaseTime, TimeUnit.MINUTES);
  }

  public static boolean lock(String lockKey, long leaseTime, TimeUnit timeUnit) {
    RLock rLock = redisson.getLock(lockKey);
    // 加锁，并且设置锁过期时间，防止死锁的产生
    rLock.lock(leaseTime, timeUnit);
    return true;
  }

  public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
    RLock lock = redisson.getLock(lockKey);
    try {
      return lock.tryLock(waitTime, leaseTime, unit);
    } catch (InterruptedException e) {
      return false;
    }
  }

  public static void unlock(String lockKey) {
    redisson.getLock(lockKey).unlock();
  }
}
