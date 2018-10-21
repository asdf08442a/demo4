package com.enterprise.demo.sys.common.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.io.File;

/**
 * 代码生成
 **/
public class MybatisPlusUtils {

  public static void main(String[] args) {
    // 定义需生成模块
    shell("demo-sys");
  }

  private static void shell(String model) {
    File file = new File(model);
    String path = file.getAbsolutePath();
    AutoGenerator mpg = new AutoGenerator();
    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(path + "/src/main/java");
    gc.setFileOverride(true);
    gc.setActiveRecord(false);
    gc.setEnableCache(false);// XML 二级缓存
    gc.setBaseResultMap(true);// XML ResultMap
    gc.setBaseColumnList(true);// XML columList
    gc.setAuthor("jinzg");

    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    gc.setMapperName("%sMapper");
    gc.setXmlName("%sMapper");
    gc.setServiceName("%sService");
    gc.setServiceImplName("%sServiceImpl");
    gc.setControllerName("%sController");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setDbType(DbType.MYSQL);
    dsc.setDriverName("com.mysql.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("123456");
    dsc.setUrl("jdbc:mysql://192.168.196.128:3306/sys?useUnicode=true&characterEncoding=utf8");
    mpg.setDataSource(dsc);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
    strategy.setEntityLombokModel(true);// entity添加lombok注解
    //strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
    // strategy.setInclude(new String[] { "user" }); // 需要生成的表
    // strategy.setExclude(new String[]{"test"}); // 排除生成的表
    // 自定义实体父类
    // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
    // 自定义实体，公共字段
    // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
    // 自定义 mapper 父类
    // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
    // 自定义 service 父类
    // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
    // 自定义 service 实现类父类
    // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
    // 自定义 controller 父类
    // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
    // 【实体】是否生成字段常量（默认 false）
    // public static final String ID = "test_id";
    // strategy.setEntityColumnConstant(true);
    // 【实体】是否为构建者模型（默认 false）
    // public User setName(String name) {this.name = name; return this;}
    // strategy.setEntityBuilderModel(true);
    mpg.setStrategy(strategy);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent("com.enterprise.demo");
    pc.setXml("sys.dao.xml");
    pc.setMapper("sys.dao");
    pc.setEntity("sys.pojo.entity");
    pc.setServiceImpl("sys.service.impl");
    pc.setService("sys.service");
    pc.setController("sys.controller");
    mpg.setPackageInfo(pc);

    // 控制生成
    TemplateConfig tc = new TemplateConfig();
    tc.setController(null);
    tc.setService(null);
    tc.setServiceImpl(null);
    mpg.setTemplate(tc);

    // 执行生成
    mpg.execute();
  }
}
