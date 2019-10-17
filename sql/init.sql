DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`              int unsigned     NOT NULL AUTO_INCREMENT,
  `user_id`         varchar(32)      NOT NULL DEFAULT '' COMMENT '用户id',
  `user_name`       varchar(64)      NOT NULL DEFAULT '' COMMENT '用户名',
  `address`         varchar(64)      NOT NULL DEFAULT '' COMMENT '住址',
  `cellphone`       varchar(16)      NOT NULL DEFAULT '' COMMENT '手机联系方式',
  `is_use`          tinyint unsigned NOT NULL DEFAULT 1 COMMENT '用户是否有效：0否，1有',
  `gmt_create`      timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified`    timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


