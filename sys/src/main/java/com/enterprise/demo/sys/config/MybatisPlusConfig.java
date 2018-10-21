package com.enterprise.demo.sys.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.enterprise.demo.sys.dao")
public class MybatisPlusConfig {

  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    paginationInterceptor.setLocalPage(true);// 开启pageHelper支持
    return paginationInterceptor;
  }

  /**
   * 性能分析插件
   */
  @Bean
  @Profile({"dev", "test"})
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    performanceInterceptor.setFormat(true);// sql格式化
    performanceInterceptor.setMaxTime(1000);// sql最大执行时间
    return performanceInterceptor;
  }
}