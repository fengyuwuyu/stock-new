/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : stock1

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 29/01/2019 17:19:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stock_monitor
-- ----------------------------
DROP TABLE IF EXISTS `stock_monitor`;
CREATE TABLE `stock_monitor`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票代码',
  `begin_date` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` date NULL DEFAULT NULL COMMENT '结束时间',
  `buy_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '买入价格',
  `sell_price_high` decimal(10, 0) NULL DEFAULT NULL COMMENT '最高出售价格',
  `sell_price_low` decimal(10, 0) NULL DEFAULT NULL COMMENT '最低出售价格',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票监控' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 1, 1, '[0],[1],[1],', '高炮团', '高炮团', '', NULL);
INSERT INTO `sys_dept` VALUES (2, 5, 1, NULL, '测试', '测试fullname', '测试tips', 1);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `en_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文类别',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_date` datetime(0) NOT NULL,
  `update_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (7, 0, '药品单位', 'DW', '药品单位', '2018-06-20 08:31:03', '2018-06-19 18:52:51');
INSERT INTO `sys_dict` VALUES (9, 7, '支', NULL, '支', '2018-06-19 18:31:11', '2018-06-19 18:31:14');
INSERT INTO `sys_dict` VALUES (11, 0, '药品规格', 'YPGG', '药品规格', '2018-06-19 18:53:30', '2018-06-19 18:53:30');
INSERT INTO `sys_dict` VALUES (13, 0, '进货渠道', 'JHQD', '进货渠道', '2018-06-19 18:54:25', '2018-06-19 18:54:25');
INSERT INTO `sys_dict` VALUES (14, 7, '袋', NULL, '袋', '2018-06-19 18:58:44', '2018-06-19 18:58:44');
INSERT INTO `sys_dict` VALUES (15, 11, '1支10毫升', NULL, '1支10毫升', '2018-06-19 18:59:24', '2018-06-19 18:59:24');
INSERT INTO `sys_dict` VALUES (16, 11, '1支20毫升', NULL, '1支20毫升', '2018-06-19 18:59:29', '2018-06-19 18:59:29');
INSERT INTO `sys_dict` VALUES (20, 13, '北京渠道', NULL, '北京渠道', '2018-06-19 19:00:42', '2018-06-19 19:00:42');
INSERT INTO `sys_dict` VALUES (21, 13, '天津渠道', NULL, '天津渠道', '2018-06-19 19:00:48', '2018-06-19 19:00:48');
INSERT INTO `sys_dict` VALUES (22, 0, '用法', 'YF', NULL, '2018-06-20 14:15:00', '2018-06-20 14:15:00');
INSERT INTO `sys_dict` VALUES (23, 0, '周期', 'ZQ', NULL, '2018-06-20 14:15:28', '2018-06-20 14:15:28');
INSERT INTO `sys_dict` VALUES (24, 0, '用量', 'YL', NULL, '2018-06-20 14:15:44', '2018-06-20 14:15:44');
INSERT INTO `sys_dict` VALUES (25, 0, '住院', 'ZY', NULL, '2018-06-20 14:15:56', '2018-06-20 14:15:56');
INSERT INTO `sys_dict` VALUES (26, 0, '身份', 'SF', NULL, '2018-06-20 14:16:04', '2018-06-20 14:16:04');
INSERT INTO `sys_dict` VALUES (27, 22, '口服', 'KF', NULL, '2018-06-21 05:11:59', '2018-06-20 15:12:08');
INSERT INTO `sys_dict` VALUES (28, 22, '喷剂', 'PJ', NULL, '2018-06-20 15:12:20', '2018-06-20 15:12:20');
INSERT INTO `sys_dict` VALUES (29, 23, '1/日', '', NULL, '2018-06-20 15:12:48', '2018-06-20 15:12:48');
INSERT INTO `sys_dict` VALUES (30, 23, '3/日', '', NULL, '2018-06-20 15:12:58', '2018-06-20 15:12:58');
INSERT INTO `sys_dict` VALUES (31, 24, '10ML', '', NULL, '2018-06-20 15:13:18', '2018-06-20 15:13:18');
INSERT INTO `sys_dict` VALUES (32, 24, '100ML', '', NULL, '2018-06-20 15:13:28', '2018-06-20 15:13:28');
INSERT INTO `sys_dict` VALUES (33, 25, '卫生队', '', NULL, '2018-06-20 15:13:41', '2018-06-20 15:13:41');
INSERT INTO `sys_dict` VALUES (34, 25, '陆军总院', '', NULL, '2018-06-20 15:13:49', '2018-06-20 15:13:49');
INSERT INTO `sys_dict` VALUES (38, 0, '门诊', 'OUTPATIENT', NULL, '2018-06-21 16:16:43', '2018-06-21 16:16:43');
INSERT INTO `sys_dict` VALUES (39, 38, '普通门诊', '', NULL, '2018-06-21 16:16:57', '2018-06-21 16:16:57');
INSERT INTO `sys_dict` VALUES (40, 38, '发热门诊', '', NULL, '2018-06-21 16:17:06', '2018-06-21 16:17:06');
INSERT INTO `sys_dict` VALUES (41, 26, '战士', 'ZS', NULL, '2018-06-26 17:32:40', '2018-06-26 17:32:40');
INSERT INTO `sys_dict` VALUES (42, 26, '干部', 'GB', NULL, '2018-06-26 17:33:35', '2018-06-26 17:33:35');
INSERT INTO `sys_dict` VALUES (43, 26, '班长', 'BZ', NULL, '2018-06-26 17:33:56', '2018-06-26 17:33:56');
INSERT INTO `sys_dict` VALUES (44, 26, '副班长', 'FBZ', NULL, '2018-06-26 17:34:05', '2018-06-26 17:34:05');
INSERT INTO `sys_dict` VALUES (45, 26, '团长', 'TZ', NULL, '2018-06-26 17:34:19', '2018-06-26 17:34:19');
INSERT INTO `sys_dict` VALUES (46, 0, '主要诊断', 'ZYZD', NULL, '2018-06-27 13:07:43', '2018-06-27 13:07:50');
INSERT INTO `sys_dict` VALUES (47, 46, '发烧', '', NULL, '2018-06-27 13:08:05', '2018-06-27 13:08:05');
INSERT INTO `sys_dict` VALUES (48, 46, '感冒', '', NULL, '2018-06-27 13:08:10', '2018-06-27 13:08:10');
INSERT INTO `sys_dict` VALUES (49, 0, '给药途径', 'GYTJ', NULL, '2018-07-02 17:18:08', '2018-07-02 17:18:08');
INSERT INTO `sys_dict` VALUES (50, 49, '注射', 'ZS', NULL, '2018-07-02 17:18:26', '2018-07-02 17:18:26');
INSERT INTO `sys_dict` VALUES (51, 0, '理疗方式', 'LLFS', NULL, '2018-07-03 13:30:56', '2018-07-03 13:30:56');
INSERT INTO `sys_dict` VALUES (52, 51, '针灸', 'ZJ', NULL, '2018-07-03 13:31:44', '2018-07-03 13:31:44');
INSERT INTO `sys_dict` VALUES (53, 51, '拔罐', 'BG', NULL, '2018-07-03 13:31:56', '2018-07-03 13:31:56');
INSERT INTO `sys_dict` VALUES (54, 0, '转诊医院', 'ZZYY', NULL, '2018-07-04 17:54:35', '2018-07-04 17:54:35');
INSERT INTO `sys_dict` VALUES (55, 54, '陆军总医院', '', NULL, '2018-07-04 17:54:53', '2018-07-04 17:54:53');
INSERT INTO `sys_dict` VALUES (56, 54, '测试', '', NULL, '2018-07-04 17:55:00', '2018-07-04 17:55:00');
INSERT INTO `sys_dict` VALUES (57, 0, '去向', 'QX', NULL, '2018-07-05 11:07:36', '2018-07-05 11:07:36');
INSERT INTO `sys_dict` VALUES (58, 57, '住院', '', NULL, '2018-07-05 11:07:44', '2018-07-05 11:07:44');
INSERT INTO `sys_dict` VALUES (59, 57, '卫生队', '', NULL, '2018-07-05 11:08:08', '2018-07-05 11:08:08');
INSERT INTO `sys_dict` VALUES (60, 57, '连队', '', NULL, '2018-07-05 11:08:14', '2018-07-05 11:08:14');
INSERT INTO `sys_dict` VALUES (61, 0, '常规医嘱', 'CGYZ', NULL, '2018-07-05 15:27:34', '2018-07-05 15:27:34');
INSERT INTO `sys_dict` VALUES (62, 61, '常规医嘱1', '', NULL, '2018-07-05 15:27:43', '2018-07-05 15:27:43');
INSERT INTO `sys_dict` VALUES (63, 61, '常规医嘱2', '', NULL, '2018-07-05 15:27:52', '2018-07-05 15:27:52');
INSERT INTO `sys_dict` VALUES (64, 0, '体检类别', 'TJLB', NULL, '2018-07-10 16:57:35', '2018-07-10 16:57:35');
INSERT INTO `sys_dict` VALUES (65, 64, '定期性体检', '', NULL, '2018-07-10 16:58:09', '2018-07-10 16:58:09');
INSERT INTO `sys_dict` VALUES (66, 64, '预防性体检', '', NULL, '2018-07-10 16:58:18', '2018-07-10 16:58:18');
INSERT INTO `sys_dict` VALUES (67, 64, '鉴定性体检', '', NULL, '2018-07-10 16:58:26', '2018-07-10 16:58:26');
INSERT INTO `sys_dict` VALUES (68, 24, '10L', '', NULL, '2018-07-26 15:16:47', '2018-07-26 15:16:47');
INSERT INTO `sys_dict` VALUES (69, 49, '静滴', 'JD', NULL, '2018-07-26 15:17:24', '2018-07-26 15:17:33');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (1, '登录日志', 50, '2018-07-27 09:19:19', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (2, '退出日志', 50, '2018-07-27 09:22:06', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (3, '登录日志', 51, '2018-07-27 09:22:17', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (4, '登录日志', 1, '2018-07-27 09:37:11', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (5, '退出日志', 1, '2018-07-27 09:45:09', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (6, '登录日志', 51, '2018-07-27 09:45:22', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (7, '退出日志', 51, '2018-07-27 09:45:34', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (8, '登录日志', 1, '2018-07-27 09:45:34', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (9, '登录日志', 1, '2018-07-27 09:45:37', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (10, '退出日志', 1, '2018-07-27 09:46:14', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (11, '登录日志', 51, '2018-07-27 09:46:21', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (12, '退出日志', 51, '2018-07-27 09:47:35', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (13, '登录日志', 1, '2018-07-27 09:47:37', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (14, '登录日志', 1, '2018-07-27 09:47:45', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (15, '退出日志', 1, '2018-07-27 09:47:42', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (16, '登录日志', 1, '2018-07-27 09:48:42', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (17, '退出日志', 51, '2018-07-27 09:48:53', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (18, '登录日志', 50, '2018-07-27 09:48:56', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (19, '退出日志', 50, '2018-07-27 09:49:12', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (20, '登录日志', 51, '2018-07-27 09:49:19', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (21, '登录日志', 1, '2018-07-27 09:50:54', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (22, '登录日志', 1, '2018-07-27 09:52:02', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (23, '退出日志', 1, '2018-07-27 09:52:42', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (24, '登录失败日志', NULL, '2018-07-27 09:52:56', '成功', '账号:zwj,账号密码错误', '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (25, '登录日志', 51, '2018-07-27 09:53:10', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (26, '退出日志', 1, '2018-07-27 09:53:15', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (27, '登录日志', 1, '2018-07-27 09:53:23', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (28, '退出日志', 1, '2018-07-27 09:55:09', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (29, '退出日志', 51, '2018-07-27 09:58:24', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (30, '登录日志', 1, '2018-07-27 09:58:26', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (31, '退出日志', 1, '2018-07-27 09:59:05', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (32, '登录日志', 1, '2018-07-27 10:06:44', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (33, '登录日志', 1, '2018-07-27 10:11:21', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (34, '退出日志', 1, '2018-07-27 10:12:21', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (35, '退出日志', 1, '2018-07-27 10:12:47', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (36, '登录日志', 51, '2018-07-27 10:13:02', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (37, '退出日志', 51, '2018-07-27 10:25:20', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (38, '登录日志', 51, '2018-07-27 10:26:42', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (39, '退出日志', 51, '2018-07-27 10:28:45', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (40, '登录失败日志', NULL, '2018-07-27 10:29:21', '成功', '账号:zwj,账号密码错误', '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (41, '登录日志', 51, '2018-07-27 10:29:37', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (42, '退出日志', 51, '2018-07-27 10:32:26', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (43, '登录日志', 50, '2018-07-27 10:32:29', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (44, '退出日志', 50, '2018-07-27 10:34:22', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (45, '登录日志', 53, '2018-07-27 10:34:34', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (46, '退出日志', 53, '2018-07-27 10:35:21', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (47, '登录日志', 54, '2018-07-27 10:35:29', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (48, '退出日志', 54, '2018-07-27 10:36:16', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (49, '登录日志', 50, '2018-07-27 10:36:19', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (50, '登录日志', 1, '2018-07-27 10:50:05', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (51, '登录日志', 1, '2018-07-27 11:51:43', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (52, '登录日志', 1, '2018-07-27 11:53:05', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (53, '退出日志', 1, '2018-07-27 11:56:13', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (54, '登录日志', 58, '2018-07-27 11:56:35', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (55, '退出日志', 1, '2018-07-27 11:59:08', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (56, '登录日志', 59, '2018-07-27 11:59:14', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (57, '登录日志', 1, '2018-07-27 11:59:17', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (58, '退出日志', 58, '2018-07-27 12:00:35', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (59, '登录日志', 1, '2018-07-27 12:00:37', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (60, '退出日志', 1, '2018-07-27 12:01:40', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (61, '登录日志', 58, '2018-07-27 12:01:47', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (62, '退出日志', 58, '2018-07-27 12:02:18', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (63, '登录日志', 1, '2018-07-27 12:02:20', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (64, '登录日志', 1, '2018-07-27 13:10:43', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (65, '退出日志', 1, '2018-07-27 13:14:24', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (66, '登录日志', 60, '2018-07-27 13:14:34', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (67, '退出日志', 60, '2018-07-27 13:16:35', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (68, '登录日志', 1, '2018-07-27 13:16:50', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (69, '登录日志', 1, '2018-07-27 13:20:42', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (70, '登录日志', 1, '2018-07-27 13:26:09', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (71, '退出日志', 1, '2018-07-27 13:32:41', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (72, '登录日志', 60, '2018-07-27 13:32:49', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (73, '退出日志', 60, '2018-07-27 13:33:14', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (74, '登录日志', 53, '2018-07-27 13:33:28', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (75, '退出日志', 53, '2018-07-27 13:33:45', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (76, '登录日志', 1, '2018-07-27 13:33:48', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (77, '退出日志', 1, '2018-07-27 13:34:14', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (78, '登录日志', 53, '2018-07-27 13:34:21', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (79, '登录失败日志', NULL, '2018-07-27 13:35:04', '成功', '账号:韩港,账号密码错误', '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (80, '登录日志', 1, '2018-07-27 13:35:09', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (81, '退出日志', 1, '2018-07-27 13:35:33', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (82, '登录日志', 59, '2018-07-27 13:35:42', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (83, '登录日志', 1, '2018-07-27 13:35:46', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (84, '退出日志', 1, '2018-07-27 13:36:22', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (85, '登录失败日志', NULL, '2018-07-27 13:36:30', '成功', '账号:hg,账号密码错误', '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (86, '登录日志', 51, '2018-07-27 13:36:41', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (87, '退出日志', 51, '2018-07-27 13:41:48', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (88, '登录日志', 1, '2018-07-27 13:41:49', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (89, '退出日志', 53, '2018-07-27 13:43:22', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (90, '登录日志', 1, '2018-07-27 13:43:25', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (91, '退出日志', 1, '2018-07-27 13:44:22', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (92, '退出日志', 1, '2018-07-27 13:44:29', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (93, '登录日志', 53, '2018-07-27 13:44:31', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (94, '登录日志', 59, '2018-07-27 13:44:37', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (95, '退出日志', 59, '2018-07-27 13:44:45', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (96, '登录日志', 1, '2018-07-27 13:44:47', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (97, '退出日志', 1, '2018-07-27 13:45:24', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (98, '登录日志', 59, '2018-07-27 13:45:33', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (99, '退出日志', 53, '2018-07-27 13:47:32', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (100, '登录日志', 1, '2018-07-27 13:47:34', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (101, '退出日志', 1, '2018-07-27 13:48:49', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (102, '退出日志', 59, '2018-07-27 13:48:55', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (103, '登录日志', 1, '2018-07-27 13:48:56', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (104, '登录日志', 53, '2018-07-27 13:48:57', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (105, '退出日志', 53, '2018-07-27 13:49:37', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (106, '登录日志', 1, '2018-07-27 13:49:40', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (107, '退出日志', 1, '2018-07-27 13:51:18', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (108, '登录日志', 53, '2018-07-27 13:51:26', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (109, '退出日志', 53, '2018-07-27 13:52:02', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (110, '登录日志', 1, '2018-07-27 13:52:04', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (111, '退出日志', 1, '2018-07-27 14:07:53', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (112, '登录日志', 1, '2018-07-27 14:17:00', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (113, '退出日志', 1, '2018-07-27 14:17:26', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (114, '登录日志', 54, '2018-07-27 14:17:34', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (115, '退出日志', 54, '2018-07-27 14:18:09', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (116, '登录日志', 1, '2018-07-27 14:24:20', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (117, '退出日志', 1, '2018-07-27 14:55:04', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (118, '退出日志', 1, '2018-07-27 15:00:10', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (119, '登录日志', 1, '2018-07-27 15:03:41', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (120, '登录日志', 1, '2018-07-27 15:06:49', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (121, '登录日志', 1, '2018-07-27 15:08:43', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (122, '登录日志', 1, '2018-07-27 15:09:51', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (123, '登录日志', 1, '2018-07-27 15:11:30', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (124, '退出日志', 1, '2018-07-27 15:19:33', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (125, '登录日志', 1, '2018-07-27 15:19:37', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (126, '登录日志', 1, '2018-07-27 15:19:39', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (127, '退出日志', 1, '2018-07-27 15:20:45', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (128, '登录日志', 1, '2018-07-27 15:21:21', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (129, '退出日志', 1, '2018-07-27 15:22:23', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (130, '登录日志', 1, '2018-07-27 15:41:32', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (131, '退出日志', 1, '2018-07-27 16:03:29', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (132, '登录日志', 51, '2018-07-27 16:03:41', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (133, '退出日志', 51, '2018-07-27 16:08:20', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (134, '登录日志', 50, '2018-07-27 16:08:23', '成功', NULL, '172.16.2.187');
INSERT INTO `sys_login_log` VALUES (135, '登录日志', 1, '2018-07-27 16:11:00', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (136, '登录日志', 1, '2018-07-27 16:50:03', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (137, '登录日志', 1, '2018-07-27 16:53:06', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (138, '退出日志', 1, '2018-07-27 16:54:13', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (139, '登录日志', 1, '2018-07-27 17:01:22', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (140, '登录日志', 1, '2018-07-27 17:12:13', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (141, '登录日志', 1, '2018-07-27 17:15:36', '成功', NULL, '172.16.2.154');
INSERT INTO `sys_login_log` VALUES (142, '退出日志', 1, '2018-07-27 17:17:34', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (143, '登录日志', 50, '2018-07-27 17:17:41', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (144, '退出日志', 50, '2018-07-27 17:17:58', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (145, '登录日志', 1, '2018-07-27 17:18:01', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (146, '退出日志', 1, '2018-07-27 17:18:42', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (147, '登录日志', 50, '2018-07-27 17:18:50', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (148, '退出日志', 50, '2018-07-27 17:19:26', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (149, '登录日志', 1, '2018-07-27 17:19:29', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (150, '退出日志', 1, '2018-07-27 17:20:03', '成功', NULL, '172.16.2.162');
INSERT INTO `sys_login_log` VALUES (151, '登录日志', 1, '2018-07-27 17:20:21', '成功', NULL, '127.0.0.1');
INSERT INTO `sys_login_log` VALUES (152, '登录日志', 1, '2019-01-04 10:43:14', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (153, '登录日志', 1, '2019-01-04 13:06:41', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (154, '登录日志', 1, '2019-01-04 13:09:45', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (155, '退出日志', 1, '2019-01-04 13:31:28', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (156, '登录日志', 1, '2019-01-04 13:31:35', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (157, '登录日志', 1, '2019-01-24 15:38:55', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (158, '登录日志', 1, '2019-01-25 09:30:41', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (159, '登录日志', 1, '2019-01-25 12:34:37', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (160, '登录日志', 1, '2019-01-25 14:04:19', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (161, '登录日志', 1, '2019-01-25 14:05:17', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (162, '登录日志', 1, '2019-01-25 14:11:56', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (163, '登录日志', 1, '2019-01-25 15:56:43', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (164, '登录日志', 1, '2019-01-25 16:01:55', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (165, '退出日志', 1, '2019-01-28 11:25:30', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (166, '登录日志', 1, '2019-01-28 11:25:41', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (167, '登录日志', 1, '2019-01-28 15:29:48', '成功', NULL, '172.16.2.185');
INSERT INTO `sys_login_log` VALUES (168, '登录日志', 1, '2019-01-28 15:47:07', '成功', NULL, '172.16.2.185');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `num` int(65) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) NULL DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) NULL DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(65) NULL DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) NULL DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'fa-user', '#', 98, 1, 1, NULL, 1, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', NULL, '/dict', 4, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dict/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dict/update', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dict/delete', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (148, 'code', '0', '[0],', '代码生成', 'fa-code', '/code', 99, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (185, 'dict_sublist', 'dict', '[0],[system],[dict],', '二级字典详情', 'fa-list', '/dict/dict_sublist', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (315, 'subDict_add', 'dict', '[0],[system],[dict],', '二级字典添加', '', '/dict/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (322, 'stock', '0', '[0],', '股票管理', '', '#', 1, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (323, 'searcher_index', 'stock', '[0],[stock],', '每日详情', '', '/searcher', 1, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (324, 'monitor', 'stock', '[0],[stock],', '股票监控', '', '/monitor', 99, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (325, 'monitor_list', 'monitor', '[0],[system],[monitor],', '股票监控列表', '', '/monitor/list', 99, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (326, 'monitor_add', 'monitor', '[0],[system],[monitor],', '股票监控添加', '', '/monitor/add', 99, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (327, 'monitor_update', 'monitor', '[0],[system],[monitor],', '股票监控更新', '', '/monitor/update', 99, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (328, 'monitor_delete', 'monitor', '[0],[system],[monitor],', '股票监控删除', '', '/monitor/delete', 99, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (329, 'monitor_detail', 'monitor', '[0],[system],[monitor],', '股票监控详情', '', '/monitor/detail', 99, 3, 0, NULL, 1, 0);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法名称',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES (1, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 09:40:09', '成功', '角色名称=团级干部');
INSERT INTO `sys_operation_log` VALUES (2, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 09:40:09', '成功', '角色名称=团级干部');
INSERT INTO `sys_operation_log` VALUES (3, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:40:36', '成功', '角色名称=团级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (4, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:40:36', '成功', '角色名称=团级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (5, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-07-27 09:41:21', '成功', '部门简称=高炮团;;;字段名称:上级名称,旧值:,新值:总公司;;;字段名称:部门简称,旧值:总公司,新值:高炮团;;;字段名称:部门全称,旧值:总公司,新值:高炮团');
INSERT INTO `sys_operation_log` VALUES (6, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-07-27 09:41:21', '成功', '部门简称=高炮团;;;字段名称:上级名称,旧值:,新值:总公司;;;字段名称:部门简称,旧值:总公司,新值:高炮团;;;字段名称:部门全称,旧值:总公司,新值:高炮团');
INSERT INTO `sys_operation_log` VALUES (7, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-07-27 09:41:36', '成功', '部门简称=高炮团;;;');
INSERT INTO `sys_operation_log` VALUES (8, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-07-27 09:41:36', '成功', '部门简称=高炮团;;;');
INSERT INTO `sys_operation_log` VALUES (9, '异常日志', '', 1, NULL, NULL, '2018-07-27 09:42:37', '失败', 'org.springframework.dao.DataIntegrityViolationException: \r\n### Error updating database.  Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n### The error may involve com.stylefeng.guns.modular.system.dao.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user   ( account,  `password`,  salt,  `name`,    sex,  email,  phone,    deptid,  `status`,  createtime,    user_no )  VALUES   ( ?,  ?,  ?,  ?,    ?,  ?,  ?,    ?,  ?,  ?,    ? )\r\n### Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\n; SQL []; Field \'user_depname\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:243)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n	at com.sun.proxy.TProxy91.insert(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:278)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:57)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\r\n	at com.sun.proxy.TProxy161.insert(Unknown Source)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImpl.insert(ServiceImpl.java:98)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImplTTFastClassBySpringCGLIBTT3e2398a4.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at com.alibaba.druid.support.spring.stat.DruidStatInterceptor.invoke(DruidStatInterceptor.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptorT1.proceedWithInvocation(TransactionInterceptor.java:99)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.service.impl.UserServiceImplTTEnhancerBySpringCGLIBTT967a8da9.insert(<generated>)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrController.add(UserMgrController.java:234)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT87f11409.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.intercept.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:30)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.BussinessLogAop.recordSysLog(BussinessLogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.PermissionAop.doPermission(PermissionAop.java:62)\r\n	at sun.reflect.GeneratedMethodAccessor302.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTT421b87ce.add(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(Unknown Source)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Unknown Source)\r\nCaused by: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3878)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3814)\r\n	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2478)\r\n	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2625)\r\n	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2551)\r\n	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1861)\r\n	at com.mysql.jdbc.PreparedStatement.execute(PreparedStatement.java:1192)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3051)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.wall.WallFilter.preparedStatement_execute(WallFilter.java:619)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.execute(PreparedStatementProxyImpl.java:167)\r\n	at com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:498)\r\n	at sun.reflect.GeneratedMethodAccessor127.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)\r\n	at com.sun.proxy.TProxy100.execute(Unknown Source)\r\n	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:46)\r\n	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:50)\r\n	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)\r\n	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)\r\n	at sun.reflect.GeneratedMethodAccessor172.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)\r\n	at com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor.intercept(OptimisticLockerInterceptor.java:72)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)\r\n	at com.sun.proxy.TProxy97.update(Unknown Source)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:198)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:185)\r\n	at sun.reflect.GeneratedMethodAccessor202.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)\r\n	... 139 more\r\n');
INSERT INTO `sys_operation_log` VALUES (10, '异常日志', '', 1, NULL, NULL, '2018-07-27 09:45:04', '失败', 'org.springframework.dao.DataIntegrityViolationException: \r\n### Error updating database.  Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n### The error may involve com.stylefeng.guns.modular.system.dao.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user   ( account,  `password`,  salt,  `name`,    sex,  email,  phone,    deptid,  `status`,  createtime,    user_no )  VALUES   ( ?,  ?,  ?,  ?,    ?,  ?,  ?,    ?,  ?,  ?,    ? )\r\n### Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\n; SQL []; Field \'user_depname\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:243)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n	at com.sun.proxy.TProxy91.insert(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:278)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:57)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\r\n	at com.sun.proxy.TProxy161.insert(Unknown Source)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImpl.insert(ServiceImpl.java:98)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImplTTFastClassBySpringCGLIBTT3e2398a4.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at com.alibaba.druid.support.spring.stat.DruidStatInterceptor.invoke(DruidStatInterceptor.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptorT1.proceedWithInvocation(TransactionInterceptor.java:99)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.service.impl.UserServiceImplTTEnhancerBySpringCGLIBTT967a8da9.insert(<generated>)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrController.add(UserMgrController.java:234)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT87f11409.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.intercept.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:30)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.BussinessLogAop.recordSysLog(BussinessLogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.PermissionAop.doPermission(PermissionAop.java:62)\r\n	at sun.reflect.GeneratedMethodAccessor302.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTT421b87ce.add(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(Unknown Source)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Unknown Source)\r\nCaused by: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3878)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3814)\r\n	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2478)\r\n	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2625)\r\n	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2551)\r\n	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1861)\r\n	at com.mysql.jdbc.PreparedStatement.execute(PreparedStatement.java:1192)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3051)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.wall.WallFilter.preparedStatement_execute(WallFilter.java:619)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.execute(PreparedStatementProxyImpl.java:167)\r\n	at com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:498)\r\n	at sun.reflect.GeneratedMethodAccessor127.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)\r\n	at com.sun.proxy.TProxy100.execute(Unknown Source)\r\n	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:46)\r\n	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:50)\r\n	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)\r\n	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)\r\n	at sun.reflect.GeneratedMethodAccessor172.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)\r\n	at com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor.intercept(OptimisticLockerInterceptor.java:72)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)\r\n	at com.sun.proxy.TProxy97.update(Unknown Source)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:198)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:185)\r\n	at sun.reflect.GeneratedMethodAccessor202.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)\r\n	... 139 more\r\n');
INSERT INTO `sys_operation_log` VALUES (11, '异常日志', '', 1, NULL, NULL, '2018-07-27 09:45:58', '失败', 'org.springframework.dao.DataIntegrityViolationException: \r\n### Error updating database.  Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n### The error may involve com.stylefeng.guns.modular.system.dao.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_user   ( account,  `password`,  salt,  `name`,    sex,  email,  phone,    deptid,  `status`,  createtime,    user_no )  VALUES   ( ?,  ?,  ?,  ?,    ?,  ?,  ?,    ?,  ?,  ?,    ? )\r\n### Cause: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\n; SQL []; Field \'user_depname\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:243)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)\r\n	at com.sun.proxy.TProxy91.insert(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:278)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:57)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)\r\n	at com.sun.proxy.TProxy161.insert(Unknown Source)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImpl.insert(ServiceImpl.java:98)\r\n	at com.baomidou.mybatisplus.service.impl.ServiceImplTTFastClassBySpringCGLIBTT3e2398a4.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at com.alibaba.druid.support.spring.stat.DruidStatInterceptor.invoke(DruidStatInterceptor.java:72)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptorT1.proceedWithInvocation(TransactionInterceptor.java:99)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.service.impl.UserServiceImplTTEnhancerBySpringCGLIBTT967a8da9.insert(<generated>)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrController.add(UserMgrController.java:234)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT87f11409.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.intercept.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:30)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.BussinessLogAop.recordSysLog(BussinessLogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.PermissionAop.doPermission(PermissionAop.java:62)\r\n	at sun.reflect.GeneratedMethodAccessor302.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTT421b87ce.add(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(Unknown Source)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Unknown Source)\r\nCaused by: java.sql.SQLException: Field \'user_depname\' doesn\'t have a default value\r\n	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3878)\r\n	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3814)\r\n	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2478)\r\n	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2625)\r\n	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2551)\r\n	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1861)\r\n	at com.mysql.jdbc.PreparedStatement.execute(PreparedStatement.java:1192)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3051)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.wall.WallFilter.preparedStatement_execute(WallFilter.java:619)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)\r\n	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)\r\n	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.execute(PreparedStatementProxyImpl.java:167)\r\n	at com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:498)\r\n	at sun.reflect.GeneratedMethodAccessor127.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)\r\n	at com.sun.proxy.TProxy100.execute(Unknown Source)\r\n	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:46)\r\n	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at sun.reflect.GeneratedMethodAccessor158.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\r\n	at com.sun.proxy.TProxy98.update(Unknown Source)\r\n	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:50)\r\n	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)\r\n	at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)\r\n	at sun.reflect.GeneratedMethodAccessor172.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)\r\n	at com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor.intercept(OptimisticLockerInterceptor.java:72)\r\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)\r\n	at com.sun.proxy.TProxy97.update(Unknown Source)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:198)\r\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:185)\r\n	at sun.reflect.GeneratedMethodAccessor202.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplateTSqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)\r\n	... 139 more\r\n');
INSERT INTO `sys_operation_log` VALUES (12, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:46:12', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (13, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:46:12', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (14, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 09:48:40', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (15, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 09:48:40', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (16, '业务日志', '删除管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2018-07-27 09:51:05', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (17, '业务日志', '删除管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2018-07-27 09:51:05', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (18, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 09:51:26', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (19, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 09:51:26', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (20, '业务日志', '删除管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2018-07-27 09:51:36', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (21, '业务日志', '删除管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'delete', '2018-07-27 09:51:36', '成功', '账号=zjx');
INSERT INTO `sys_operation_log` VALUES (22, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:52:39', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患信息删除,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (23, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:52:40', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患信息删除,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (24, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:54:00', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (25, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 09:54:00', '成功', '角色名称=医师,资源名称=系统管理,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (26, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 09:54:42', '成功', '角色名称=医师;;;字段名称:null,旧值:1,新值:3');
INSERT INTO `sys_operation_log` VALUES (27, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 09:54:42', '成功', '角色名称=医师;;;字段名称:null,旧值:1,新值:3');
INSERT INTO `sys_operation_log` VALUES (28, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2018-07-27 09:54:56', '成功', '账号=admin;;;字段名称:头像,旧值:7d296f36-d2de-4859-964f-1d95ccc61d79.jpg,新值:acdfa6aa-d948-4544-bd3f-d7ea661d6828.jpg');
INSERT INTO `sys_operation_log` VALUES (29, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2018-07-27 09:54:56', '成功', '账号=admin;;;字段名称:头像,旧值:7d296f36-d2de-4859-964f-1d95ccc61d79.jpg,新值:acdfa6aa-d948-4544-bd3f-d7ea661d6828.jpg');
INSERT INTO `sys_operation_log` VALUES (30, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 10:03:26', '成功', '角色名称=营级干部');
INSERT INTO `sys_operation_log` VALUES (31, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 10:03:26', '成功', '角色名称=营级干部');
INSERT INTO `sys_operation_log` VALUES (32, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 10:12:08', '成功', '角色名称=营级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (33, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 10:12:08', '成功', '角色名称=营级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (34, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 11:55:47', '成功', '账号=zs');
INSERT INTO `sys_operation_log` VALUES (35, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 11:55:47', '成功', '账号=zs');
INSERT INTO `sys_operation_log` VALUES (36, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 11:56:06', '成功', '账号=zs,角色名称集合=团级干部');
INSERT INTO `sys_operation_log` VALUES (37, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 11:56:06', '成功', '账号=zs,角色名称集合=团级干部');
INSERT INTO `sys_operation_log` VALUES (38, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 11:58:51', '成功', '账号=hg');
INSERT INTO `sys_operation_log` VALUES (39, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 11:58:51', '成功', '账号=hg');
INSERT INTO `sys_operation_log` VALUES (40, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 12:01:37', '成功', '账号=zs,角色名称集合=营级干部');
INSERT INTO `sys_operation_log` VALUES (41, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 12:01:37', '成功', '账号=zs,角色名称集合=营级干部');
INSERT INTO `sys_operation_log` VALUES (42, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 13:12:46', '成功', '账号=zwx');
INSERT INTO `sys_operation_log` VALUES (43, '业务日志', '添加管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'add', '2018-07-27 13:12:46', '成功', '账号=zwx');
INSERT INTO `sys_operation_log` VALUES (44, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:13:06', '成功', '账号=zwx,角色名称集合=团级干部');
INSERT INTO `sys_operation_log` VALUES (45, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:13:06', '成功', '账号=zwx,角色名称集合=团级干部');
INSERT INTO `sys_operation_log` VALUES (46, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:13:54', '成功', '角色名称=团级干部;;;字段名称:角色的父级,旧值:--,新值:首长;;;字段名称:null,旧值:2,新值:3');
INSERT INTO `sys_operation_log` VALUES (47, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:13:54', '成功', '角色名称=团级干部;;;字段名称:角色的父级,旧值:--,新值:首长;;;字段名称:null,旧值:2,新值:3');
INSERT INTO `sys_operation_log` VALUES (48, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:14:10', '成功', '角色名称=首长;;;字段名称:null,旧值:1,新值:3');
INSERT INTO `sys_operation_log` VALUES (49, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:14:10', '成功', '角色名称=首长;;;字段名称:null,旧值:1,新值:3');
INSERT INTO `sys_operation_log` VALUES (50, '业务日志', '修改菜单', 1, 'com.stylefeng.guns.modular.system.controller.MenuController', 'edit', '2018-07-27 13:26:40', '成功', '菜单名称=个人查询;;;字段名称:null,旧值:1,新值:0');
INSERT INTO `sys_operation_log` VALUES (51, '业务日志', '修改菜单', 1, 'com.stylefeng.guns.modular.system.controller.MenuController', 'edit', '2018-07-27 13:26:40', '成功', '菜单名称=个人查询;;;字段名称:null,旧值:1,新值:0');
INSERT INTO `sys_operation_log` VALUES (52, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:34:09', '成功', '角色名称=作训;;;字段名称:null,旧值:2,新值:3');
INSERT INTO `sys_operation_log` VALUES (53, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:34:09', '成功', '角色名称=作训;;;字段名称:null,旧值:2,新值:3');
INSERT INTO `sys_operation_log` VALUES (54, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:36:17', '成功', '账号=hg,角色名称集合=营级干部');
INSERT INTO `sys_operation_log` VALUES (55, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:36:17', '成功', '账号=hg,角色名称集合=营级干部');
INSERT INTO `sys_operation_log` VALUES (56, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:40:11', '成功', '角色名称=作训;;;字段名称:null,旧值:3,新值:2');
INSERT INTO `sys_operation_log` VALUES (57, '业务日志', '修改角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'edit', '2018-07-27 13:40:11', '成功', '角色名称=作训;;;字段名称:null,旧值:3,新值:2');
INSERT INTO `sys_operation_log` VALUES (58, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:40:23', '成功', '角色名称=作训,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (59, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:40:23', '成功', '角色名称=作训,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (60, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 13:40:49', '成功', '角色名称=作训全团查询');
INSERT INTO `sys_operation_log` VALUES (61, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 13:40:49', '成功', '角色名称=作训全团查询');
INSERT INTO `sys_operation_log` VALUES (62, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:41:03', '成功', '账号=ljx,角色名称集合=作训,作训全团查询');
INSERT INTO `sys_operation_log` VALUES (63, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:41:03', '成功', '账号=ljx,角色名称集合=作训,作训全团查询');
INSERT INTO `sys_operation_log` VALUES (64, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 13:43:32', '成功', '角色名称=连级干部');
INSERT INTO `sys_operation_log` VALUES (65, '业务日志', '添加角色', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'add', '2018-07-27 13:43:32', '成功', '角色名称=连级干部');
INSERT INTO `sys_operation_log` VALUES (66, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2018-07-27 13:43:54', '成功', '账号=hg;;;');
INSERT INTO `sys_operation_log` VALUES (67, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'edit', '2018-07-27 13:43:54', '成功', '账号=hg;;;');
INSERT INTO `sys_operation_log` VALUES (68, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:44:04', '成功', '账号=hg,角色名称集合=营级干部,连级干部');
INSERT INTO `sys_operation_log` VALUES (69, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:44:04', '成功', '账号=hg,角色名称集合=营级干部,连级干部');
INSERT INTO `sys_operation_log` VALUES (70, '业务日志', '重置管理员密码', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'reset', '2018-07-27 13:44:07', '成功', '账号=hg');
INSERT INTO `sys_operation_log` VALUES (71, '业务日志', '重置管理员密码', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'reset', '2018-07-27 13:44:07', '成功', '账号=hg');
INSERT INTO `sys_operation_log` VALUES (72, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:44:24', '成功', '账号=hg,角色名称集合=连级干部');
INSERT INTO `sys_operation_log` VALUES (73, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 13:44:24', '成功', '账号=hg,角色名称集合=连级干部');
INSERT INTO `sys_operation_log` VALUES (74, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:45:21', '成功', '角色名称=连级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (75, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:45:21', '成功', '角色名称=连级干部,资源名称=综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除');
INSERT INTO `sys_operation_log` VALUES (76, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:48:40', '成功', '角色名称=作训全团查询,资源名称=综合查询,住院信息,列表,病休信息,列表,训练伤信息,列表');
INSERT INTO `sys_operation_log` VALUES (77, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 13:48:40', '成功', '角色名称=作训全团查询,资源名称=综合查询,住院信息,列表,病休信息,列表,训练伤信息,列表');
INSERT INTO `sys_operation_log` VALUES (78, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 14:17:24', '成功', '账号=qh,角色名称集合=首长');
INSERT INTO `sys_operation_log` VALUES (79, '业务日志', '分配角色', 1, 'com.stylefeng.guns.modular.system.controller.UserMgrController', 'setRole', '2018-07-27 14:17:24', '成功', '账号=qh,角色名称集合=首长');
INSERT INTO `sys_operation_log` VALUES (80, '异常日志', '', 1, NULL, NULL, '2018-07-27 17:18:18', '失败', 'com.stylefeng.guns.core.exception.GunsException: 不能删除超级管理员\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrController.delete(UserMgrController.java:278)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTT87f11409.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.intercept.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:30)\r\n	at sun.reflect.GeneratedMethodAccessor163.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.BussinessLogAop.recordSysLog(BussinessLogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.PermissionAop.doPermission(PermissionAop.java:54)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTc885d61c.delete(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(Unknown Source)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Unknown Source)\r\n');
INSERT INTO `sys_operation_log` VALUES (81, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 17:18:29', '成功', '角色名称=医师,资源名称=系统管理,字典管理,修改字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (82, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 17:18:29', '成功', '角色名称=医师,资源名称=系统管理,字典管理,修改字典,修改菜单跳转,字典列表,字典详情,二级字典详情,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患列表,病患详情,药品查询,健康档案,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,个人查询,体检信息');
INSERT INTO `sys_operation_log` VALUES (83, '异常日志', '', 1, NULL, NULL, '2018-07-27 17:18:47', '失败', 'com.stylefeng.guns.core.exception.GunsException: 不能删除超级管理员\r\n	at com.stylefeng.guns.modular.system.controller.RoleController.remove(RoleController.java:173)\r\n	at com.stylefeng.guns.modular.system.controller.RoleControllerTTFastClassBySpringCGLIBTT847ae672.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.intercept.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:30)\r\n	at sun.reflect.GeneratedMethodAccessor163.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.BussinessLogAop.recordSysLog(BussinessLogAop.java:46)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\r\n	at com.stylefeng.guns.core.aop.PermissionAop.doPermission(PermissionAop.java:62)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\r\n	at com.stylefeng.guns.modular.system.controller.RoleControllerTTEnhancerBySpringCGLIBTTfca196f7.remove(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(Unknown Source)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Unknown Source)\r\n');
INSERT INTO `sys_operation_log` VALUES (84, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 17:20:00', '成功', '角色名称=卫生队领导,资源名称=系统管理,用户管理,添加用户,修改用户,删除用户,重置密码,冻结用户,解除冻结用户,分配角色,分配角色跳转,编辑用户跳转,用户列表,角色管理,添加角色,修改角色,删除角色,配置权限,修改角色跳转,角色分配跳转,角色列表,菜单管理,添加菜单,修改菜单,删除菜单,菜单编辑跳转,菜单列表,业务日志,清空日志,日志列表,日志详情,部门管理,添加部门,修改部门,删除部门,修改部门跳转,部门列表,部门详情,字典管理,添加字典,修改字典,修改菜单跳转,字典列表,字典详情,二级字典详情,登录日志,清空登录日志,登录日志列表,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患信息删除,病患列表,病患详情,药品查询,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,药房管理,药品目录管理,药品目录管理列表,药品目录管理添加,药品目录管理更新,药品目录管理删除,药品目录管理详情,药库管理,药库管理列表,药库管理添加,药库管理更新,药库管理删除,药库管理详情,药房管理,药房管理列表,药房管理添加,药房管理更新,药房管理删除,药房管理详情,出入库记录,出入库记录列表,就诊取药,取药,查看处方');
INSERT INTO `sys_operation_log` VALUES (85, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2018-07-27 17:20:01', '成功', '角色名称=卫生队领导,资源名称=系统管理,用户管理,添加用户,修改用户,删除用户,重置密码,冻结用户,解除冻结用户,分配角色,分配角色跳转,编辑用户跳转,用户列表,角色管理,添加角色,修改角色,删除角色,配置权限,修改角色跳转,角色分配跳转,角色列表,菜单管理,添加菜单,修改菜单,删除菜单,菜单编辑跳转,菜单列表,业务日志,清空日志,日志列表,日志详情,部门管理,添加部门,修改部门,删除部门,修改部门跳转,部门列表,部门详情,字典管理,添加字典,修改字典,修改菜单跳转,字典列表,字典详情,二级字典详情,登录日志,清空登录日志,登录日志列表,就诊管理,今日就诊,病患信息列表,病患信息添加,病患信息更新,病患信息详情,诊断,病患查询,病患信息删除,病患列表,病患详情,药品查询,转诊管理,转诊办理,转诊办理列表,转诊办理删除,转诊办理开单,开单汇总,删除,汇总,打印,转诊回报,回报,修改,删除,住院管理,住院信息录入,列表,入院,修改,删除,出院,常规医嘱,长期医嘱,临时医嘱,体温记录,长期医嘱添加,长期医嘱修改,长期医嘱删除,临时医嘱添加,临时医嘱修改,临时医嘱删除,住院信息查询,详情,修改,删除,综合查询,个人信息,列表,门诊信息,列表,转诊信息,列表,住院信息,列表,病休信息,列表,评残信息,评残信息列表,评残信息添加,打印,训练伤信息,列表,发热信息,列表,体检信息,体检信息列表,体检信息添加,体检信息更新,体检信息详情,体检信息删除,药房管理,药品目录管理,药品目录管理列表,药品目录管理添加,药品目录管理更新,药品目录管理删除,药品目录管理详情,药库管理,药库管理列表,药库管理添加,药库管理更新,药库管理删除,药库管理详情,药房管理,药房管理列表,药房管理添加,药房管理更新,药房管理删除,药房管理详情,出入库记录,出入库记录列表,就诊取药,取药,查看处方');
INSERT INTO `sys_operation_log` VALUES (86, '业务日志', '菜单新增', 1, 'com.stylefeng.guns.modular.system.controller.MenuController', 'add', '2018-07-27 17:31:48', '成功', '菜单名称=二级字典添加');
INSERT INTO `sys_operation_log` VALUES (87, '业务日志', '菜单新增', 1, 'com.stylefeng.guns.modular.system.controller.MenuController', 'add', '2018-07-27 17:31:48', '成功', '菜单名称=二级字典添加');

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) NULL DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9143 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (7901, 209, 8);
INSERT INTO `sys_relation` VALUES (7902, 271, 8);
INSERT INTO `sys_relation` VALUES (7903, 304, 8);
INSERT INTO `sys_relation` VALUES (7904, 272, 8);
INSERT INTO `sys_relation` VALUES (7905, 307, 8);
INSERT INTO `sys_relation` VALUES (7906, 273, 8);
INSERT INTO `sys_relation` VALUES (7907, 308, 8);
INSERT INTO `sys_relation` VALUES (7908, 274, 8);
INSERT INTO `sys_relation` VALUES (7909, 309, 8);
INSERT INTO `sys_relation` VALUES (7910, 275, 8);
INSERT INTO `sys_relation` VALUES (7911, 310, 8);
INSERT INTO `sys_relation` VALUES (7912, 288, 8);
INSERT INTO `sys_relation` VALUES (7913, 289, 8);
INSERT INTO `sys_relation` VALUES (7914, 290, 8);
INSERT INTO `sys_relation` VALUES (7915, 294, 8);
INSERT INTO `sys_relation` VALUES (7916, 295, 8);
INSERT INTO `sys_relation` VALUES (7917, 311, 8);
INSERT INTO `sys_relation` VALUES (7918, 296, 8);
INSERT INTO `sys_relation` VALUES (7919, 312, 8);
INSERT INTO `sys_relation` VALUES (7920, 297, 8);
INSERT INTO `sys_relation` VALUES (7921, 298, 8);
INSERT INTO `sys_relation` VALUES (7922, 299, 8);
INSERT INTO `sys_relation` VALUES (7923, 300, 8);
INSERT INTO `sys_relation` VALUES (7924, 302, 8);
INSERT INTO `sys_relation` VALUES (7925, 303, 8);
INSERT INTO `sys_relation` VALUES (8351, 209, 9);
INSERT INTO `sys_relation` VALUES (8352, 271, 9);
INSERT INTO `sys_relation` VALUES (8353, 304, 9);
INSERT INTO `sys_relation` VALUES (8354, 272, 9);
INSERT INTO `sys_relation` VALUES (8355, 307, 9);
INSERT INTO `sys_relation` VALUES (8356, 273, 9);
INSERT INTO `sys_relation` VALUES (8357, 308, 9);
INSERT INTO `sys_relation` VALUES (8358, 274, 9);
INSERT INTO `sys_relation` VALUES (8359, 309, 9);
INSERT INTO `sys_relation` VALUES (8360, 275, 9);
INSERT INTO `sys_relation` VALUES (8361, 310, 9);
INSERT INTO `sys_relation` VALUES (8362, 288, 9);
INSERT INTO `sys_relation` VALUES (8363, 289, 9);
INSERT INTO `sys_relation` VALUES (8364, 290, 9);
INSERT INTO `sys_relation` VALUES (8365, 294, 9);
INSERT INTO `sys_relation` VALUES (8366, 295, 9);
INSERT INTO `sys_relation` VALUES (8367, 311, 9);
INSERT INTO `sys_relation` VALUES (8368, 296, 9);
INSERT INTO `sys_relation` VALUES (8369, 312, 9);
INSERT INTO `sys_relation` VALUES (8370, 297, 9);
INSERT INTO `sys_relation` VALUES (8371, 298, 9);
INSERT INTO `sys_relation` VALUES (8372, 299, 9);
INSERT INTO `sys_relation` VALUES (8373, 300, 9);
INSERT INTO `sys_relation` VALUES (8374, 302, 9);
INSERT INTO `sys_relation` VALUES (8375, 303, 9);
INSERT INTO `sys_relation` VALUES (8623, 209, 10);
INSERT INTO `sys_relation` VALUES (8624, 271, 10);
INSERT INTO `sys_relation` VALUES (8625, 304, 10);
INSERT INTO `sys_relation` VALUES (8626, 272, 10);
INSERT INTO `sys_relation` VALUES (8627, 307, 10);
INSERT INTO `sys_relation` VALUES (8628, 273, 10);
INSERT INTO `sys_relation` VALUES (8629, 308, 10);
INSERT INTO `sys_relation` VALUES (8630, 274, 10);
INSERT INTO `sys_relation` VALUES (8631, 309, 10);
INSERT INTO `sys_relation` VALUES (8632, 275, 10);
INSERT INTO `sys_relation` VALUES (8633, 310, 10);
INSERT INTO `sys_relation` VALUES (8634, 288, 10);
INSERT INTO `sys_relation` VALUES (8635, 289, 10);
INSERT INTO `sys_relation` VALUES (8636, 290, 10);
INSERT INTO `sys_relation` VALUES (8637, 294, 10);
INSERT INTO `sys_relation` VALUES (8638, 295, 10);
INSERT INTO `sys_relation` VALUES (8639, 311, 10);
INSERT INTO `sys_relation` VALUES (8640, 296, 10);
INSERT INTO `sys_relation` VALUES (8641, 312, 10);
INSERT INTO `sys_relation` VALUES (8642, 297, 10);
INSERT INTO `sys_relation` VALUES (8643, 298, 10);
INSERT INTO `sys_relation` VALUES (8644, 299, 10);
INSERT INTO `sys_relation` VALUES (8645, 300, 10);
INSERT INTO `sys_relation` VALUES (8646, 302, 10);
INSERT INTO `sys_relation` VALUES (8647, 303, 10);
INSERT INTO `sys_relation` VALUES (8648, 209, 7);
INSERT INTO `sys_relation` VALUES (8649, 271, 7);
INSERT INTO `sys_relation` VALUES (8650, 304, 7);
INSERT INTO `sys_relation` VALUES (8651, 272, 7);
INSERT INTO `sys_relation` VALUES (8652, 307, 7);
INSERT INTO `sys_relation` VALUES (8653, 273, 7);
INSERT INTO `sys_relation` VALUES (8654, 308, 7);
INSERT INTO `sys_relation` VALUES (8655, 274, 7);
INSERT INTO `sys_relation` VALUES (8656, 309, 7);
INSERT INTO `sys_relation` VALUES (8657, 275, 7);
INSERT INTO `sys_relation` VALUES (8658, 310, 7);
INSERT INTO `sys_relation` VALUES (8659, 288, 7);
INSERT INTO `sys_relation` VALUES (8660, 289, 7);
INSERT INTO `sys_relation` VALUES (8661, 290, 7);
INSERT INTO `sys_relation` VALUES (8662, 294, 7);
INSERT INTO `sys_relation` VALUES (8663, 295, 7);
INSERT INTO `sys_relation` VALUES (8664, 311, 7);
INSERT INTO `sys_relation` VALUES (8665, 296, 7);
INSERT INTO `sys_relation` VALUES (8666, 312, 7);
INSERT INTO `sys_relation` VALUES (8667, 297, 7);
INSERT INTO `sys_relation` VALUES (8668, 298, 7);
INSERT INTO `sys_relation` VALUES (8669, 299, 7);
INSERT INTO `sys_relation` VALUES (8670, 300, 7);
INSERT INTO `sys_relation` VALUES (8671, 302, 7);
INSERT INTO `sys_relation` VALUES (8672, 303, 7);
INSERT INTO `sys_relation` VALUES (8673, 209, 12);
INSERT INTO `sys_relation` VALUES (8674, 271, 12);
INSERT INTO `sys_relation` VALUES (8675, 304, 12);
INSERT INTO `sys_relation` VALUES (8676, 272, 12);
INSERT INTO `sys_relation` VALUES (8677, 307, 12);
INSERT INTO `sys_relation` VALUES (8678, 273, 12);
INSERT INTO `sys_relation` VALUES (8679, 308, 12);
INSERT INTO `sys_relation` VALUES (8680, 274, 12);
INSERT INTO `sys_relation` VALUES (8681, 309, 12);
INSERT INTO `sys_relation` VALUES (8682, 275, 12);
INSERT INTO `sys_relation` VALUES (8683, 310, 12);
INSERT INTO `sys_relation` VALUES (8684, 288, 12);
INSERT INTO `sys_relation` VALUES (8685, 289, 12);
INSERT INTO `sys_relation` VALUES (8686, 290, 12);
INSERT INTO `sys_relation` VALUES (8687, 294, 12);
INSERT INTO `sys_relation` VALUES (8688, 295, 12);
INSERT INTO `sys_relation` VALUES (8689, 311, 12);
INSERT INTO `sys_relation` VALUES (8690, 296, 12);
INSERT INTO `sys_relation` VALUES (8691, 312, 12);
INSERT INTO `sys_relation` VALUES (8692, 297, 12);
INSERT INTO `sys_relation` VALUES (8693, 298, 12);
INSERT INTO `sys_relation` VALUES (8694, 299, 12);
INSERT INTO `sys_relation` VALUES (8695, 300, 12);
INSERT INTO `sys_relation` VALUES (8696, 302, 12);
INSERT INTO `sys_relation` VALUES (8697, 303, 12);
INSERT INTO `sys_relation` VALUES (8698, 209, 11);
INSERT INTO `sys_relation` VALUES (8699, 274, 11);
INSERT INTO `sys_relation` VALUES (8700, 309, 11);
INSERT INTO `sys_relation` VALUES (8701, 275, 11);
INSERT INTO `sys_relation` VALUES (8702, 310, 11);
INSERT INTO `sys_relation` VALUES (8703, 295, 11);
INSERT INTO `sys_relation` VALUES (8704, 311, 11);
INSERT INTO `sys_relation` VALUES (8705, 105, 6);
INSERT INTO `sys_relation` VALUES (8706, 132, 6);
INSERT INTO `sys_relation` VALUES (8707, 139, 6);
INSERT INTO `sys_relation` VALUES (8708, 155, 6);
INSERT INTO `sys_relation` VALUES (8709, 156, 6);
INSERT INTO `sys_relation` VALUES (8710, 157, 6);
INSERT INTO `sys_relation` VALUES (8711, 185, 6);
INSERT INTO `sys_relation` VALUES (8712, 186, 6);
INSERT INTO `sys_relation` VALUES (8713, 187, 6);
INSERT INTO `sys_relation` VALUES (8714, 188, 6);
INSERT INTO `sys_relation` VALUES (8715, 189, 6);
INSERT INTO `sys_relation` VALUES (8716, 190, 6);
INSERT INTO `sys_relation` VALUES (8717, 192, 6);
INSERT INTO `sys_relation` VALUES (8718, 205, 6);
INSERT INTO `sys_relation` VALUES (8719, 193, 6);
INSERT INTO `sys_relation` VALUES (8720, 194, 6);
INSERT INTO `sys_relation` VALUES (8721, 231, 6);
INSERT INTO `sys_relation` VALUES (8722, 199, 6);
INSERT INTO `sys_relation` VALUES (8723, 206, 6);
INSERT INTO `sys_relation` VALUES (8724, 207, 6);
INSERT INTO `sys_relation` VALUES (8725, 239, 6);
INSERT INTO `sys_relation` VALUES (8726, 240, 6);
INSERT INTO `sys_relation` VALUES (8727, 241, 6);
INSERT INTO `sys_relation` VALUES (8728, 242, 6);
INSERT INTO `sys_relation` VALUES (8729, 243, 6);
INSERT INTO `sys_relation` VALUES (8730, 244, 6);
INSERT INTO `sys_relation` VALUES (8731, 245, 6);
INSERT INTO `sys_relation` VALUES (8732, 246, 6);
INSERT INTO `sys_relation` VALUES (8733, 247, 6);
INSERT INTO `sys_relation` VALUES (8734, 248, 6);
INSERT INTO `sys_relation` VALUES (8735, 249, 6);
INSERT INTO `sys_relation` VALUES (8736, 250, 6);
INSERT INTO `sys_relation` VALUES (8737, 208, 6);
INSERT INTO `sys_relation` VALUES (8738, 251, 6);
INSERT INTO `sys_relation` VALUES (8739, 252, 6);
INSERT INTO `sys_relation` VALUES (8740, 253, 6);
INSERT INTO `sys_relation` VALUES (8741, 254, 6);
INSERT INTO `sys_relation` VALUES (8742, 255, 6);
INSERT INTO `sys_relation` VALUES (8743, 256, 6);
INSERT INTO `sys_relation` VALUES (8744, 258, 6);
INSERT INTO `sys_relation` VALUES (8745, 259, 6);
INSERT INTO `sys_relation` VALUES (8746, 260, 6);
INSERT INTO `sys_relation` VALUES (8747, 261, 6);
INSERT INTO `sys_relation` VALUES (8748, 262, 6);
INSERT INTO `sys_relation` VALUES (8749, 263, 6);
INSERT INTO `sys_relation` VALUES (8750, 264, 6);
INSERT INTO `sys_relation` VALUES (8751, 265, 6);
INSERT INTO `sys_relation` VALUES (8752, 266, 6);
INSERT INTO `sys_relation` VALUES (8753, 267, 6);
INSERT INTO `sys_relation` VALUES (8754, 257, 6);
INSERT INTO `sys_relation` VALUES (8755, 268, 6);
INSERT INTO `sys_relation` VALUES (8756, 269, 6);
INSERT INTO `sys_relation` VALUES (8757, 270, 6);
INSERT INTO `sys_relation` VALUES (8758, 209, 6);
INSERT INTO `sys_relation` VALUES (8759, 271, 6);
INSERT INTO `sys_relation` VALUES (8760, 304, 6);
INSERT INTO `sys_relation` VALUES (8761, 272, 6);
INSERT INTO `sys_relation` VALUES (8762, 307, 6);
INSERT INTO `sys_relation` VALUES (8763, 273, 6);
INSERT INTO `sys_relation` VALUES (8764, 308, 6);
INSERT INTO `sys_relation` VALUES (8765, 274, 6);
INSERT INTO `sys_relation` VALUES (8766, 309, 6);
INSERT INTO `sys_relation` VALUES (8767, 275, 6);
INSERT INTO `sys_relation` VALUES (8768, 310, 6);
INSERT INTO `sys_relation` VALUES (8769, 288, 6);
INSERT INTO `sys_relation` VALUES (8770, 289, 6);
INSERT INTO `sys_relation` VALUES (8771, 290, 6);
INSERT INTO `sys_relation` VALUES (8772, 294, 6);
INSERT INTO `sys_relation` VALUES (8773, 295, 6);
INSERT INTO `sys_relation` VALUES (8774, 311, 6);
INSERT INTO `sys_relation` VALUES (8775, 296, 6);
INSERT INTO `sys_relation` VALUES (8776, 312, 6);
INSERT INTO `sys_relation` VALUES (8777, 297, 6);
INSERT INTO `sys_relation` VALUES (8778, 298, 6);
INSERT INTO `sys_relation` VALUES (8779, 299, 6);
INSERT INTO `sys_relation` VALUES (8780, 300, 6);
INSERT INTO `sys_relation` VALUES (8781, 302, 6);
INSERT INTO `sys_relation` VALUES (8782, 303, 6);
INSERT INTO `sys_relation` VALUES (8783, 313, 6);
INSERT INTO `sys_relation` VALUES (8784, 314, 6);
INSERT INTO `sys_relation` VALUES (8785, 105, 5);
INSERT INTO `sys_relation` VALUES (8786, 106, 5);
INSERT INTO `sys_relation` VALUES (8787, 107, 5);
INSERT INTO `sys_relation` VALUES (8788, 108, 5);
INSERT INTO `sys_relation` VALUES (8789, 109, 5);
INSERT INTO `sys_relation` VALUES (8790, 110, 5);
INSERT INTO `sys_relation` VALUES (8791, 111, 5);
INSERT INTO `sys_relation` VALUES (8792, 112, 5);
INSERT INTO `sys_relation` VALUES (8793, 113, 5);
INSERT INTO `sys_relation` VALUES (8794, 165, 5);
INSERT INTO `sys_relation` VALUES (8795, 166, 5);
INSERT INTO `sys_relation` VALUES (8796, 167, 5);
INSERT INTO `sys_relation` VALUES (8797, 114, 5);
INSERT INTO `sys_relation` VALUES (8798, 115, 5);
INSERT INTO `sys_relation` VALUES (8799, 116, 5);
INSERT INTO `sys_relation` VALUES (8800, 117, 5);
INSERT INTO `sys_relation` VALUES (8801, 118, 5);
INSERT INTO `sys_relation` VALUES (8802, 162, 5);
INSERT INTO `sys_relation` VALUES (8803, 163, 5);
INSERT INTO `sys_relation` VALUES (8804, 164, 5);
INSERT INTO `sys_relation` VALUES (8805, 119, 5);
INSERT INTO `sys_relation` VALUES (8806, 120, 5);
INSERT INTO `sys_relation` VALUES (8807, 121, 5);
INSERT INTO `sys_relation` VALUES (8808, 122, 5);
INSERT INTO `sys_relation` VALUES (8809, 150, 5);
INSERT INTO `sys_relation` VALUES (8810, 151, 5);
INSERT INTO `sys_relation` VALUES (8811, 128, 5);
INSERT INTO `sys_relation` VALUES (8812, 134, 5);
INSERT INTO `sys_relation` VALUES (8813, 158, 5);
INSERT INTO `sys_relation` VALUES (8814, 159, 5);
INSERT INTO `sys_relation` VALUES (8815, 131, 5);
INSERT INTO `sys_relation` VALUES (8816, 135, 5);
INSERT INTO `sys_relation` VALUES (8817, 136, 5);
INSERT INTO `sys_relation` VALUES (8818, 137, 5);
INSERT INTO `sys_relation` VALUES (8819, 152, 5);
INSERT INTO `sys_relation` VALUES (8820, 153, 5);
INSERT INTO `sys_relation` VALUES (8821, 154, 5);
INSERT INTO `sys_relation` VALUES (8822, 132, 5);
INSERT INTO `sys_relation` VALUES (8823, 138, 5);
INSERT INTO `sys_relation` VALUES (8824, 139, 5);
INSERT INTO `sys_relation` VALUES (8825, 155, 5);
INSERT INTO `sys_relation` VALUES (8826, 156, 5);
INSERT INTO `sys_relation` VALUES (8827, 157, 5);
INSERT INTO `sys_relation` VALUES (8828, 185, 5);
INSERT INTO `sys_relation` VALUES (8829, 133, 5);
INSERT INTO `sys_relation` VALUES (8830, 160, 5);
INSERT INTO `sys_relation` VALUES (8831, 161, 5);
INSERT INTO `sys_relation` VALUES (8832, 186, 5);
INSERT INTO `sys_relation` VALUES (8833, 187, 5);
INSERT INTO `sys_relation` VALUES (8834, 188, 5);
INSERT INTO `sys_relation` VALUES (8835, 189, 5);
INSERT INTO `sys_relation` VALUES (8836, 190, 5);
INSERT INTO `sys_relation` VALUES (8837, 192, 5);
INSERT INTO `sys_relation` VALUES (8838, 205, 5);
INSERT INTO `sys_relation` VALUES (8839, 193, 5);
INSERT INTO `sys_relation` VALUES (8840, 191, 5);
INSERT INTO `sys_relation` VALUES (8841, 194, 5);
INSERT INTO `sys_relation` VALUES (8842, 231, 5);
INSERT INTO `sys_relation` VALUES (8843, 199, 5);
INSERT INTO `sys_relation` VALUES (8844, 207, 5);
INSERT INTO `sys_relation` VALUES (8845, 239, 5);
INSERT INTO `sys_relation` VALUES (8846, 240, 5);
INSERT INTO `sys_relation` VALUES (8847, 241, 5);
INSERT INTO `sys_relation` VALUES (8848, 242, 5);
INSERT INTO `sys_relation` VALUES (8849, 243, 5);
INSERT INTO `sys_relation` VALUES (8850, 244, 5);
INSERT INTO `sys_relation` VALUES (8851, 245, 5);
INSERT INTO `sys_relation` VALUES (8852, 246, 5);
INSERT INTO `sys_relation` VALUES (8853, 247, 5);
INSERT INTO `sys_relation` VALUES (8854, 248, 5);
INSERT INTO `sys_relation` VALUES (8855, 249, 5);
INSERT INTO `sys_relation` VALUES (8856, 250, 5);
INSERT INTO `sys_relation` VALUES (8857, 208, 5);
INSERT INTO `sys_relation` VALUES (8858, 251, 5);
INSERT INTO `sys_relation` VALUES (8859, 252, 5);
INSERT INTO `sys_relation` VALUES (8860, 253, 5);
INSERT INTO `sys_relation` VALUES (8861, 254, 5);
INSERT INTO `sys_relation` VALUES (8862, 255, 5);
INSERT INTO `sys_relation` VALUES (8863, 256, 5);
INSERT INTO `sys_relation` VALUES (8864, 258, 5);
INSERT INTO `sys_relation` VALUES (8865, 259, 5);
INSERT INTO `sys_relation` VALUES (8866, 260, 5);
INSERT INTO `sys_relation` VALUES (8867, 261, 5);
INSERT INTO `sys_relation` VALUES (8868, 262, 5);
INSERT INTO `sys_relation` VALUES (8869, 263, 5);
INSERT INTO `sys_relation` VALUES (8870, 264, 5);
INSERT INTO `sys_relation` VALUES (8871, 265, 5);
INSERT INTO `sys_relation` VALUES (8872, 266, 5);
INSERT INTO `sys_relation` VALUES (8873, 267, 5);
INSERT INTO `sys_relation` VALUES (8874, 257, 5);
INSERT INTO `sys_relation` VALUES (8875, 268, 5);
INSERT INTO `sys_relation` VALUES (8876, 269, 5);
INSERT INTO `sys_relation` VALUES (8877, 270, 5);
INSERT INTO `sys_relation` VALUES (8878, 209, 5);
INSERT INTO `sys_relation` VALUES (8879, 271, 5);
INSERT INTO `sys_relation` VALUES (8880, 304, 5);
INSERT INTO `sys_relation` VALUES (8881, 272, 5);
INSERT INTO `sys_relation` VALUES (8882, 307, 5);
INSERT INTO `sys_relation` VALUES (8883, 273, 5);
INSERT INTO `sys_relation` VALUES (8884, 308, 5);
INSERT INTO `sys_relation` VALUES (8885, 274, 5);
INSERT INTO `sys_relation` VALUES (8886, 309, 5);
INSERT INTO `sys_relation` VALUES (8887, 275, 5);
INSERT INTO `sys_relation` VALUES (8888, 310, 5);
INSERT INTO `sys_relation` VALUES (8889, 288, 5);
INSERT INTO `sys_relation` VALUES (8890, 289, 5);
INSERT INTO `sys_relation` VALUES (8891, 290, 5);
INSERT INTO `sys_relation` VALUES (8892, 294, 5);
INSERT INTO `sys_relation` VALUES (8893, 295, 5);
INSERT INTO `sys_relation` VALUES (8894, 311, 5);
INSERT INTO `sys_relation` VALUES (8895, 296, 5);
INSERT INTO `sys_relation` VALUES (8896, 312, 5);
INSERT INTO `sys_relation` VALUES (8897, 297, 5);
INSERT INTO `sys_relation` VALUES (8898, 298, 5);
INSERT INTO `sys_relation` VALUES (8899, 299, 5);
INSERT INTO `sys_relation` VALUES (8900, 300, 5);
INSERT INTO `sys_relation` VALUES (8901, 302, 5);
INSERT INTO `sys_relation` VALUES (8902, 303, 5);
INSERT INTO `sys_relation` VALUES (8903, 210, 5);
INSERT INTO `sys_relation` VALUES (8904, 211, 5);
INSERT INTO `sys_relation` VALUES (8905, 212, 5);
INSERT INTO `sys_relation` VALUES (8906, 213, 5);
INSERT INTO `sys_relation` VALUES (8907, 214, 5);
INSERT INTO `sys_relation` VALUES (8908, 215, 5);
INSERT INTO `sys_relation` VALUES (8909, 216, 5);
INSERT INTO `sys_relation` VALUES (8910, 217, 5);
INSERT INTO `sys_relation` VALUES (8911, 218, 5);
INSERT INTO `sys_relation` VALUES (8912, 219, 5);
INSERT INTO `sys_relation` VALUES (8913, 220, 5);
INSERT INTO `sys_relation` VALUES (8914, 221, 5);
INSERT INTO `sys_relation` VALUES (8915, 222, 5);
INSERT INTO `sys_relation` VALUES (8916, 223, 5);
INSERT INTO `sys_relation` VALUES (8917, 224, 5);
INSERT INTO `sys_relation` VALUES (8918, 225, 5);
INSERT INTO `sys_relation` VALUES (8919, 226, 5);
INSERT INTO `sys_relation` VALUES (8920, 227, 5);
INSERT INTO `sys_relation` VALUES (8921, 228, 5);
INSERT INTO `sys_relation` VALUES (8922, 229, 5);
INSERT INTO `sys_relation` VALUES (8923, 230, 5);
INSERT INTO `sys_relation` VALUES (8924, 232, 5);
INSERT INTO `sys_relation` VALUES (8925, 233, 5);
INSERT INTO `sys_relation` VALUES (8926, 234, 5);
INSERT INTO `sys_relation` VALUES (9085, 322, 1);
INSERT INTO `sys_relation` VALUES (9086, 323, 1);
INSERT INTO `sys_relation` VALUES (9087, 324, 1);
INSERT INTO `sys_relation` VALUES (9088, 328, 1);
INSERT INTO `sys_relation` VALUES (9089, 325, 1);
INSERT INTO `sys_relation` VALUES (9090, 329, 1);
INSERT INTO `sys_relation` VALUES (9091, 326, 1);
INSERT INTO `sys_relation` VALUES (9092, 327, 1);
INSERT INTO `sys_relation` VALUES (9093, 105, 1);
INSERT INTO `sys_relation` VALUES (9094, 106, 1);
INSERT INTO `sys_relation` VALUES (9095, 107, 1);
INSERT INTO `sys_relation` VALUES (9096, 108, 1);
INSERT INTO `sys_relation` VALUES (9097, 109, 1);
INSERT INTO `sys_relation` VALUES (9098, 110, 1);
INSERT INTO `sys_relation` VALUES (9099, 111, 1);
INSERT INTO `sys_relation` VALUES (9100, 112, 1);
INSERT INTO `sys_relation` VALUES (9101, 113, 1);
INSERT INTO `sys_relation` VALUES (9102, 165, 1);
INSERT INTO `sys_relation` VALUES (9103, 166, 1);
INSERT INTO `sys_relation` VALUES (9104, 167, 1);
INSERT INTO `sys_relation` VALUES (9105, 114, 1);
INSERT INTO `sys_relation` VALUES (9106, 115, 1);
INSERT INTO `sys_relation` VALUES (9107, 116, 1);
INSERT INTO `sys_relation` VALUES (9108, 117, 1);
INSERT INTO `sys_relation` VALUES (9109, 118, 1);
INSERT INTO `sys_relation` VALUES (9110, 162, 1);
INSERT INTO `sys_relation` VALUES (9111, 163, 1);
INSERT INTO `sys_relation` VALUES (9112, 164, 1);
INSERT INTO `sys_relation` VALUES (9113, 131, 1);
INSERT INTO `sys_relation` VALUES (9114, 136, 1);
INSERT INTO `sys_relation` VALUES (9115, 137, 1);
INSERT INTO `sys_relation` VALUES (9116, 135, 1);
INSERT INTO `sys_relation` VALUES (9117, 152, 1);
INSERT INTO `sys_relation` VALUES (9118, 153, 1);
INSERT INTO `sys_relation` VALUES (9119, 154, 1);
INSERT INTO `sys_relation` VALUES (9120, 132, 1);
INSERT INTO `sys_relation` VALUES (9121, 315, 1);
INSERT INTO `sys_relation` VALUES (9122, 138, 1);
INSERT INTO `sys_relation` VALUES (9123, 139, 1);
INSERT INTO `sys_relation` VALUES (9124, 140, 1);
INSERT INTO `sys_relation` VALUES (9125, 155, 1);
INSERT INTO `sys_relation` VALUES (9126, 185, 1);
INSERT INTO `sys_relation` VALUES (9127, 156, 1);
INSERT INTO `sys_relation` VALUES (9128, 157, 1);
INSERT INTO `sys_relation` VALUES (9129, 119, 1);
INSERT INTO `sys_relation` VALUES (9130, 120, 1);
INSERT INTO `sys_relation` VALUES (9131, 121, 1);
INSERT INTO `sys_relation` VALUES (9132, 122, 1);
INSERT INTO `sys_relation` VALUES (9133, 150, 1);
INSERT INTO `sys_relation` VALUES (9134, 151, 1);
INSERT INTO `sys_relation` VALUES (9135, 133, 1);
INSERT INTO `sys_relation` VALUES (9136, 160, 1);
INSERT INTO `sys_relation` VALUES (9137, 161, 1);
INSERT INTO `sys_relation` VALUES (9138, 128, 1);
INSERT INTO `sys_relation` VALUES (9139, 158, 1);
INSERT INTO `sys_relation` VALUES (9140, 134, 1);
INSERT INTO `sys_relation` VALUES (9141, 159, 1);
INSERT INTO `sys_relation` VALUES (9142, 148, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT 1 COMMENT '保留字段(暂时没用）',
  `type` int(11) NULL DEFAULT 1,
  `pid` int(11) NULL DEFAULT 0 COMMENT '父角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, '超级管理员', 'administrator', 1, 3, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段',
  `user_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_depname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'acdfa6aa-d948-4544-bd3f-d7ea661d6828.jpg', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', 'admin', '2018-06-14 00:00:00', 1, 'sn93@qq.com', '', '1', 1, 1, '2016-01-29 08:49:53', 25, 'admin', '');

SET FOREIGN_KEY_CHECKS = 1;
