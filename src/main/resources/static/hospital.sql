/*
 Navicat Premium Data Transfer

 Source Server         : Vetch
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 23/04/2021 16:19:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名',
  `password` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_account_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES (1, 'admin', '111111', 1);
INSERT INTO `sys_account` VALUES (2, '张三', '111111', 2);
INSERT INTO `sys_account` VALUES (3, '李四', '111111', 3);
INSERT INTO `sys_account` VALUES (9, '1234', '1234', 3);
INSERT INTO `sys_account` VALUES (10, 't1', '111111', 3);
INSERT INTO `sys_account` VALUES (12, '12', '111111', 2);
INSERT INTO `sys_account` VALUES (13, '13', '111111', 2);
INSERT INTO `sys_account` VALUES (14, '14', '111111', 2);
INSERT INTO `sys_account` VALUES (15, '15', '111111', 2);
INSERT INTO `sys_account` VALUES (16, '16', '111111', 2);
INSERT INTO `sys_account` VALUES (17, '17', '111111', 2);
INSERT INTO `sys_account` VALUES (18, '18', '111111', 2);
INSERT INTO `sys_account` VALUES (19, '19', '111111', 2);
INSERT INTO `sys_account` VALUES (23, 'xxxx2', '111111', 2);

-- ----------------------------
-- Table structure for sys_appoint
-- ----------------------------
DROP TABLE IF EXISTS `sys_appoint`;
CREATE TABLE `sys_appoint`  (
  `appoint_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约信息ID',
  `time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT CURRENT_TIMESTAMP(100) COMMENT '患者预约时间',
  `seeTime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '患者预约就诊时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否成功就诊',
  `message_id` bigint(20) NOT NULL COMMENT '医生ID',
  `patient_id` bigint(20) NOT NULL COMMENT '医生ID',
  PRIMARY KEY (`appoint_id`) USING BTREE,
  INDEX `message_id`(`message_id`) USING BTREE,
  INDEX `patient_id`(`patient_id`) USING BTREE,
  CONSTRAINT `sys_appoint_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `sys_message` (`message_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_appoint_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `web_patient` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预约信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_appoint
-- ----------------------------
INSERT INTO `sys_appoint` VALUES (1, '2021-04-15 11:25:45', '2021-04-24-08:30', '0', 1, 1);
INSERT INTO `sys_appoint` VALUES (2, '2021-04-15 11:25:45', '2021-04-24-11:30', '0', 1, 6);
INSERT INTO `sys_appoint` VALUES (3, '2021-04-15 11:25:45', '2021-04-24-14:30', '0', 1, 7);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '首页', 0, 1, 'patient', 'web/patient/index', 1, 0, 'C', '0', '0', 'web:patient:list', 'user');
INSERT INTO `sys_menu` VALUES (2, '个人中心', 0, 2, 'patientInfo', 'web/patientInfo/index', 1, 0, 'C', '0', '0', 'web:patientinfo:list', 'user');
INSERT INTO `sys_menu` VALUES (3, '我的预约', 0, 3, 'appoint', 'web/appoint/index', 1, 0, 'C', '0', '0', 'web:appoint:list', 'component');
INSERT INTO `sys_menu` VALUES (4, '医生查询', 1, 1, '', NULL, 1, 0, 'F', '0', '0', 'sys:dpinfo:query', '#');
INSERT INTO `sys_menu` VALUES (5, '患者预约', 3, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:appoint:add', '#');
INSERT INTO `sys_menu` VALUES (6, '患者预约撤销', 3, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:appoint:remove', '#');
INSERT INTO `sys_menu` VALUES (7, '患者预约查询', 3, 2, '', NULL, 1, 0, 'F', '0', '0', 'web:appoint:query', '#');
INSERT INTO `sys_menu` VALUES (8, '患者预约下载', 3, 3, '', NULL, 1, 0, 'F', '0', '0', 'web:appoint:export', '#');
INSERT INTO `sys_menu` VALUES (9, '患者信息修改', 2, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:patientinfo:edit', '#');
INSERT INTO `sys_menu` VALUES (10, '个人中心', 0, 1, 'doctorInfo', 'web/doctorInfo/index', 1, 0, 'C', '0', '0', 'web:doctorinfo:list', 'user');
INSERT INTO `sys_menu` VALUES (11, '医生信息修改', 10, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:doctorinfo:edit', '#');
INSERT INTO `sys_menu` VALUES (12, '门诊安排', 0, 2, 'arrangement', 'web/arrangement/index', 1, 0, 'C', '0', '0', 'web:arrangement:list', 'component');
INSERT INTO `sys_menu` VALUES (13, '门诊安排查询', 12, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:arrangement:query', '#');
INSERT INTO `sys_menu` VALUES (14, '门诊安排下载', 12, 1, '', NULL, 1, 0, 'F', '0', '0', 'web:arrangement:export', '#');
INSERT INTO `sys_menu` VALUES (16, '医生管理', 0, 2, 'dpinfo', 'system/dpinfo/index', 1, 0, 'C', '0', '0', 'sys:dpinfo:list', 'user');
INSERT INTO `sys_menu` VALUES (17, '查询医生', 16, 1, '', NULL, 1, 0, 'F', '0', '0', 'sys:dpinfo:query', '#');
INSERT INTO `sys_menu` VALUES (18, '添加医生', 16, 1, '', NULL, 1, 0, 'F', '0', '0', 'sys:dpinfo:add', '#');
INSERT INTO `sys_menu` VALUES (19, '修改医生', 16, 1, '', NULL, 1, 0, 'F', '0', '0', 'sys:dpinfo:edit', '#');
INSERT INTO `sys_menu` VALUES (20, '删除医生', 16, 1, '', NULL, 1, 0, 'F', '0', '0', 'sys:dpinfo:remove', '#');

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '医生预约信息ID',
  `seeTime` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生就诊时间',
  `maxNum` int(11) NULL DEFAULT NULL COMMENT '医生最大预约数量',
  `remanNum` int(11) NULL DEFAULT NULL COMMENT '医生剩余预约数量',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `doctor_id`(`doctor_id`) USING BTREE,
  CONSTRAINT `sys_message_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `web_doctor` (`doctor_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生预约信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES (1, '17:30-23:00', 15, 10, 1);
INSERT INTO `sys_message` VALUES (3, '10:00-10:00', 2, 1, 3);
INSERT INTO `sys_message` VALUES (4, '', 2, 1, 4);
INSERT INTO `sys_message` VALUES (5, '', 2, 1, 5);
INSERT INTO `sys_message` VALUES (6, '', 2, 1, 6);
INSERT INTO `sys_message` VALUES (7, '', 2, 1, 7);
INSERT INTO `sys_message` VALUES (8, '', 2, 1, 8);
INSERT INTO `sys_message` VALUES (9, '', 2, 1, 9);
INSERT INTO `sys_message` VALUES (10, '', 2, 1, 10);
INSERT INTO `sys_message` VALUES (14, '09:00-11:00', 13, NULL, 15669);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员');
INSERT INTO `sys_role` VALUES (2, '医生');
INSERT INTO `sys_role` VALUES (3, '患者');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (3, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (2, 13);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);

-- ----------------------------
-- Table structure for web_doctor
-- ----------------------------
DROP TABLE IF EXISTS `web_doctor`;
CREATE TABLE `web_doctor`  (
  `doctor_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '医生ID',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生头像',
  `realName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生真实姓名',
  `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生职称',
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生科室',
  `specialty` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医生专长',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '医生账号删除状态（0：未删除，1：删除）',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户账号ID',
  PRIMARY KEY (`doctor_id`) USING BTREE,
  INDEX `web_doctor_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `web_doctor_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_account` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15670 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of web_doctor
-- ----------------------------
INSERT INTO `web_doctor` VALUES (1, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 2);
INSERT INTO `web_doctor` VALUES (3, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称xx', 'xx', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 12);
INSERT INTO `web_doctor` VALUES (4, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 13);
INSERT INTO `web_doctor` VALUES (5, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 14);
INSERT INTO `web_doctor` VALUES (6, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 15);
INSERT INTO `web_doctor` VALUES (7, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 16);
INSERT INTO `web_doctor` VALUES (8, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 17);
INSERT INTO `web_doctor` VALUES (9, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 18);
INSERT INTO `web_doctor` VALUES (10, '/profile/avatar/2021/04/22/5b9ca08bcd6d5747bebd573c8d87a9b9.jpeg', '真实姓名', '职称', '科室', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '0', 19);
INSERT INTO `web_doctor` VALUES (15669, '', 'xxxx', 'xxxx', 'xxxx', 'xxxx', '0', 23);

-- ----------------------------
-- Table structure for web_patient
-- ----------------------------
DROP TABLE IF EXISTS `web_patient`;
CREATE TABLE `web_patient`  (
  `patient_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '患者身份证号',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '患者账号运行状态（0：正常，1：停用）',
  `banTime` timestamp(0) NULL DEFAULT NULL COMMENT '患者账号停用时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户账号ID',
  PRIMARY KEY (`patient_id`) USING BTREE,
  INDEX `web_patient_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `web_patient_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_account` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '患者信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of web_patient
-- ----------------------------
INSERT INTO `web_patient` VALUES (1, '1311313131313131313', '0', NULL, 3);
INSERT INTO `web_patient` VALUES (6, '111111', '1', '2021-05-20 00:00:00', 9);
INSERT INTO `web_patient` VALUES (7, '1234143232131', '0', NULL, 10);

SET FOREIGN_KEY_CHECKS = 1;
