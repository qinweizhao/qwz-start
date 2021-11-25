/*
 Navicat Premium Data Transfer

 Source Server         : TX_Cloud
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 42.192.49.72:3306
 Source Schema         : project-authority

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 24/11/2021 21:05:22
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`    bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `menu_type`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `parent_id`  bigint NULL DEFAULT 0 COMMENT '父菜单ID',
    `path`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
    `icon`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `component`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `status`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1062 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 'M', 0, '', 'el-icon-s-operation', '', '0', 'sys:manage');
INSERT INTO `sys_menu`
VALUES (2, '用户管理', 'C', 1, '/sys/user', 'el-icon-s-custom', 'sys/User', '0', 'sys:user:list');
INSERT INTO `sys_menu`
VALUES (3, '角色管理', 'C', 1, '/sys/role', 'el-icon-rank', 'sys/Role', '0', 'sys:role:list');
INSERT INTO `sys_menu`
VALUES (4, '菜单管理', 'C', 1, '/sys/menu', 'el-icon-menu', 'sys/Menu', '0', 'sys:menu:list');
INSERT INTO `sys_menu`
VALUES (7, '添加角色', 'F', 3, '', '', '', '0', 'sys:role:save');
INSERT INTO `sys_menu`
VALUES (8, '添加用户', 'F', 2, NULL, NULL, 'sys/User', '0', 'sys:user:save');
INSERT INTO `sys_menu`
VALUES (9, '修改用户', 'F', 2, NULL, NULL, 'sys/User', '0', 'sys:user:update');
INSERT INTO `sys_menu`
VALUES (10, '删除用户', 'F', 2, NULL, NULL, 'sys/User', '0', 'sys:user:delete');
INSERT INTO `sys_menu`
VALUES (11, '分配角色', 'F', 2, NULL, NULL, 'sys/User', '0', 'sys:user:role');
INSERT INTO `sys_menu`
VALUES (12, '重置密码', 'F', 2, NULL, NULL, NULL, '0', 'sys:user:repass');
INSERT INTO `sys_menu`
VALUES (13, '修改角色', 'F', 3, NULL, NULL, NULL, '0', 'sys:role:update');
INSERT INTO `sys_menu`
VALUES (14, '删除角色', 'F', 3, NULL, NULL, NULL, '0', 'sys:role:delete');
INSERT INTO `sys_menu`
VALUES (15, '分配权限', 'F', 3, NULL, NULL, NULL, '0', 'sys:role:perm');
INSERT INTO `sys_menu`
VALUES (16, '添加菜单', 'F', 4, NULL, NULL, NULL, '0', 'sys:menu:save');
INSERT INTO `sys_menu`
VALUES (17, '修改菜单', 'F', 4, NULL, NULL, NULL, '0', 'sys:menu:update');
INSERT INTO `sys_menu`
VALUES (18, '删除菜单', 'F', 4, NULL, NULL, NULL, '0', 'sys:menu:delete');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`   bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `role_key`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
    `status`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '角色状态（0正常 1停用）',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, 'admin', 'admin', '0');
INSERT INTO `sys_role`
VALUES (2, 'test', 'test', '0');
INSERT INTO `sys_role`
VALUES (3, '1111', '111', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1, 1);
INSERT INTO `sys_role_menu`
VALUES (1, 2);
INSERT INTO `sys_role_menu`
VALUES (1, 3);
INSERT INTO `sys_role_menu`
VALUES (1, 4);
INSERT INTO `sys_role_menu`
VALUES (1, 5);
INSERT INTO `sys_role_menu`
VALUES (1, 6);
INSERT INTO `sys_role_menu`
VALUES (1, 7);
INSERT INTO `sys_role_menu`
VALUES (1, 8);
INSERT INTO `sys_role_menu`
VALUES (1, 9);
INSERT INTO `sys_role_menu`
VALUES (1, 10);
INSERT INTO `sys_role_menu`
VALUES (1, 11);
INSERT INTO `sys_role_menu`
VALUES (1, 12);
INSERT INTO `sys_role_menu`
VALUES (1, 13);
INSERT INTO `sys_role_menu`
VALUES (1, 14);
INSERT INTO `sys_role_menu`
VALUES (1, 15);
INSERT INTO `sys_role_menu`
VALUES (1, 16);
INSERT INTO `sys_role_menu`
VALUES (1, 17);
INSERT INTO `sys_role_menu`
VALUES (1, 18);
INSERT INTO `sys_role_menu`
VALUES (1, 19);
INSERT INTO `sys_role_menu`
VALUES (2, 1);
INSERT INTO `sys_role_menu`
VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`   bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户账号',
    `password`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户昵称',
    `avatar`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
    `status`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC', 'qwz',
        'https://cdn.jsdelivr.net/gh/qinhua/halo-theme-joe2.0@master/source/svg/spinner-preloader.svg', '0');
INSERT INTO `sys_user`
VALUES (2, 'test2', '$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC', 'test2',
        'https://cdn.jsdelivr.net/gh/qinhua/halo-theme-joe2.0@master/source/svg/spinner-preloader.svg', '0');
INSERT INTO `sys_user`
VALUES (3, 'test3', '$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC', 'test3',
        'https://www.qinweizhao.com/upload/2021/11/avatar-9b1b1e49686548049357f88d3eba6e7e.png', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 1);
INSERT INTO `sys_user_role`
VALUES (3, 1);

SET
FOREIGN_KEY_CHECKS = 1;
