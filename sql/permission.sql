-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `name` varchar(64) NOT NULL COMMENT '权限名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '权限描述',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT '权限访问路径',
  `perms` varchar(256) NOT NULL DEFAULT '' COMMENT '权限标识',
  `parent_id` varchar(32) NOT NULL COMMENT '父级权限id',
  `type` tinyint unsigned NOT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `order_num` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) NOT NULL DEFAULT '' COMMENT '图标',
	`is_use` tinyint unsigned NOT NULL DEFAULT 1 COMMENT '权限是否有效：0否，1有',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('2', '合同管理', '合同管理', '', '', '0', 0, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('201', '合同导入', '合同导入', '', 'import', '2', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('20101', '批量导入合同', '批量导入合同', '/batch/contract/import', 'contract:import', '201', 2, 0, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('20102', '单个录入合同', '单个录入合同', '/contract/add', 'contract:add', '201', 2, 0, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('202', '合同查询', '合同查询', '', '', '2', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('203', '公司信息管理', '公司信息管理', '', '', '2', 1, 3, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('3', '还款计划管理', '还款计划管理', '', '', '0', 0, 3, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('301', '还款计划导入', '还款计划导入', '', '', '3', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('302', '还款计划查询', '还款计划查询', '', '', '3', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('303', '还款明细', '还款明细', '', '', '3', 1, 3, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('4', '通知管理', '通知管理', '', '', '0', 0, 4, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('401', '通知列表', '通知列表', '', '', '4', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('402', '群发助手', '群发助手', '', '', '4', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('5', '车险暖心宝', '车险暖心宝', '', '', '0', 0, 5, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('501', '申请贷款', '申请贷款', '', '', '5', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('502', '我的申请', '我的申请', '', '', '5', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('6', '小桔库融贷', '小桔库融贷', '', '', '0', 0, 6, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('601', '借款', '借款', '', '', '6', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('602', '还款', '还款', '', '', '6', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('7', '余额管理', '余额管理', '', '', '0', 0, 7, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('8', '商户中心', '商户中心', '', '', '0', 0, 8, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('801', '商户信息', '商户信息', '', '', '8', 1, 1, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('802', '角色管理', '角色管理', '', '', '8', 1, 2, '', 1);
INSERT INTO `permission` (permission_id, name, description, url, perms, parent_id, type, order_num, icon, is_use)
VALUES ('803', '用户管理', '用户管理', '', '', '8', 1, 3, '', 1);

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '2');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '201');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '20101');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '20102');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '202');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('3', '203');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('4', '3');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('4', '301');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('4', '302');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('4', '303');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
	`type` tinyint unsigned NOT NULL COMMENT '角色类型：0超级，1商户，2用户',
	`is_use` tinyint unsigned NOT NULL DEFAULT 1 COMMENT '角色是否有效：0否，1有',
	`is_default` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '是否默认角色：0否，1是',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`(role_id, name, description, type) VALUES ('1', '超级管理员', '超级管理员', 0);
INSERT INTO `role`(role_id, name, description, type) VALUES ('2', '普通管理员', '普通管理员', 1);
INSERT INTO `role`(role_id, name, description, type) VALUES ('3', '合同管理员', '合同管理员', 2);
INSERT INTO `role`(role_id, name, description, type) VALUES ('4', '还款计划管理员', '还款计划管理员', 2);

-- ----------------------------
-- Table structure for `tenant_role`
-- ----------------------------
DROP TABLE IF EXISTS `tenant_role`;
CREATE TABLE `tenant_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(32) NOT NULL COMMENT '商户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户角色表';

-- ----------------------------
-- Records of tenant_role
-- ----------------------------
INSERT INTO `tenant_role`(tenant_id, role_id) VALUES ('11', '2');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
	`tenant_id` varchar(32) NOT NULL COMMENT '商户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`(user_id, tenant_id, role_id) VALUES ('22', '11', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`              int unsigned     NOT NULL AUTO_INCREMENT,
	`tenant_id`       varchar(32)      NOT NULL COMMENT '商户id',
  `user_id`         varchar(32)      NOT NULL COMMENT '用户id',
  `username`        varchar(64)      NOT NULL COMMENT '用户名',
  `is_use`          tinyint unsigned NOT NULL DEFAULT 1 COMMENT '用户是否有效：0否，1有',
	`last_login_time` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `gmt_create`      timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified`    timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`(tenant_id, user_id, username) VALUES ('11', '22', 'admin');
