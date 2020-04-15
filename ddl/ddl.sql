-- ----------------------------
-- Database for spring_security
-- ----------------------------
CREATE DATABASE IF NOT EXISTS `spring_security` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE spring_security;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4 NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4 NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERNAME` ( `username` ) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE `sys_role` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`role_name` VARCHAR ( 32 ) COLLATE utf8mb4 NOT NULL COMMENT '角色名 中文',
`role_desc` VARCHAR ( 128 ) COLLATE utf8mb4 NOT NULL COMMENT '角色描述',
`role_code` VARCHAR ( 32 ) COLLATE utf8mb4 NOT NULL COMMENT '角色代号 英文 如admin',
`sort` INT ( 11 ) NOT NULL COMMENT '用于排序',
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_RoleCode` (`role_code`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT=2 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
CREATE TABLE `sys_menu` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`menu_name` VARCHAR ( 64 ) COLLATE utf8mb4 DEFAULT NULL COMMENT '权限名',
`url` VARCHAR ( 64 ) COLLATE utf8mb4 DEFAULT NULL COMMENT '访问路径',
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_MenuName` (`menu_name`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
CREATE TABLE `sys_user_role` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`user_id` INT ( 11 ) DEFAULT NULL,
`role_id` INT ( 11 ) DEFAULT NULL,
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_UserId_RoleId` (`user_id`, `role_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
CREATE TABLE `sys_role_menu` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`role_id` INT ( 11 ) DEFAULT NULL,
`menu_id` INT ( 11 ) DEFAULT NULL,
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_RoleId_MenuId` (`role_id`, `menu_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
