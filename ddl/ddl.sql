-- 创建数据库
-- spring_security
CREATE DATABASE IF NOT EXISTS spring_security DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

USE spring_security;

-- 创建数据表
-- user
CREATE TABLE IF NOT EXISTS `user` (
`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
`username` VARCHAR ( 32 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
`password` VARCHAR ( 64 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
PRIMARY KEY ( `id` ),
UNIQUE KEY `USERNAME` ( `username` ) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表';

-- role
CREATE TABLE IF NOT EXISTS `role` (
`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
`code` VARCHAR ( 32 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代号',
`name` VARCHAR ( 64 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
PRIMARY KEY ( `id` ),
UNIQUE KEY `CODE` ( `code` ) USING BTREE,
UNIQUE KEY `NAME` ( `name` ) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表';

-- menu
CREATE TABLE IF NOT EXISTS `menu` (
`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
`org` VARCHAR ( 32 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单分组',
`name` VARCHAR ( 32 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
`url` VARCHAR ( 255 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由',
PRIMARY KEY ( `id` ),
UNIQUE KEY `URL` ( `url` ) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表';

-- user_role
CREATE TABLE IF NOT EXISTS `user_role` (
`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
`user_id` INT ( 11 ) NOT NULL COMMENT '用户id',
`role_id` INT ( 11 ) NOT NULL COMMENT '角色id',
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_userId_roleId` (`user_id`,`role_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表';

-- role_menu
CREATE TABLE IF NOT EXISTS `role_menu` (
`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
`role_id` INT ( 11 ) NOT NULL COMMENT '角色id',
`menu_id` INT ( 11 ) NOT NULL COMMENT '菜单id',
PRIMARY KEY ( `id` ),
UNIQUE KEY `IX_roleId_menuId` (`role_id`,`menu_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表';