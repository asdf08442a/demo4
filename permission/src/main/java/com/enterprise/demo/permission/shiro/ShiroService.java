package com.enterprise.demo.permission.shiro;

import com.enterprise.demo.common.util.SpringContextHolder;
import com.enterprise.demo.permission.dao.PermissionEntity;
import com.enterprise.demo.permission.service.PermissionService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {

  @Autowired
  private PermissionService permissionService;

  /**
   * 初始化权限
   */
  public Map<String, String> loadFilterChainDefinitions() {
    // 权限控制map.从数据库获取
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/logout", "logout");

    List<PermissionEntity> permissionList = permissionService.selectAll();
    for (PermissionEntity permission : permissionList) {
      if (StringUtils.isNotBlank(permission.getUrl()) && StringUtils
          .isNotBlank(permission.getPerms())) {
        String perm = "perms[" + permission.getPerms() + "]";
        filterChainDefinitionMap.put(permission.getUrl(), perm);
      }
    }
    filterChainDefinitionMap.put("/**", "user");
    return filterChainDefinitionMap;
  }

  /**
   * 重新加载权限
   */
  public void updatePermission() {
    ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder
        .getBean(ShiroFilterFactoryBean.class);
    synchronized (shiroFilterFactoryBean) {

      AbstractShiroFilter shiroFilter;
      try {
        shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
      } catch (Exception e) {
        throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
      }

      PathMatchingFilterChainResolver filterChainResolver =
          (PathMatchingFilterChainResolver) shiroFilter
              .getFilterChainResolver();
      DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
          .getFilterChainManager();

      // 清空老的权限控制
      manager.getFilterChains().clear();
      shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
      shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

      // 重新构建生成
      Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
      for (Map.Entry<String, String> entry : chains.entrySet()) {
        String url = entry.getKey();
        String chainDefinition = entry.getValue().trim().replace(" ", "");
        manager.createChain(url, chainDefinition);
      }
    }
  }
}
