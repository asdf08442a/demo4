package com.enterprise.demo.common.mybatis;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

/**
 * MyBatis代码生成器插件。 作用是：生成的实体类都带上"Entity"后缀名。
 */
public class EntityNameSuffixGeneratorPlugin extends PluginAdapter {

  public EntityNameSuffixGeneratorPlugin() {
    super();
  }

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    String old = introspectedTable.getBaseRecordType();
    introspectedTable.setBaseRecordType(old + "Entity");
  }
}

