/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : zyf

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 01/11/2022 16:51:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `short_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门简称',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `type` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `parent_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门名称',
  `is_hide` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否隐藏',
  `is_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否显示',
  `level` int(11) NULL DEFAULT NULL COMMENT '分层',
  `sort` int(11) NULL DEFAULT NULL COMMENT '部门排序',
  `tenant_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  `yl_appid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'appid',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '是否有效:0-有效 1-禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人昵称',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `last_update_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人创建人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1583259968756609025', 'LBFW', '龙保服务', 'LBFW', '1', NULL, 'root', '0', '1', 2, 3, NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', '2022-10-21 10:47:19', 'superadmin', '超管wow');
INSERT INTO `t_department` VALUES ('1583288786133266433', '测试部门', '测试部门', 'TEST', '10', '测试部门', 'LBFW', '0', '1', 3, 1, NULL, NULL, 0, '2022-10-21 10:47:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288786544308226', NULL, '信息技术部', 'XXJSB', '10', NULL, 'LBFW', '0', '1', 3, 1, NULL, NULL, 0, '2022-10-21 10:47:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288786946961409', NULL, '培训基地', 'PXJD', '10', NULL, 'LBFW', '0', '1', 3, 2, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288787349614594', NULL, '综合办公室', 'ZHBGS', '10', NULL, 'LBFW', '0', '1', 3, 3, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288787760656385', NULL, '财务室', 'CWS', '10', NULL, 'LBFW', '0', '1', 3, 5, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288788175892481', NULL, '护卫犬管理部', 'HWQGLB', '10', NULL, 'LBFW', '0', '1', 3, 6, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288788595322882', NULL, '人力资源部', 'RLZYB', '10', NULL, 'LBFW', '0', '1', 3, 7, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288789002170370', NULL, '法务室', 'WFS', '10', NULL, 'LBFW', '0', '1', 3, 8, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288789396434946', NULL, '市场拓展部', 'SHTZB', '10', NULL, 'LBFW', '0', '1', 3, 9, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288789794893825', NULL, '后勤部', 'HQB', '10', NULL, 'LBFW', '0', '1', 3, 10, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288790214324225', NULL, '监察室', 'JCS', '10', NULL, 'LBFW', '0', '1', 3, 11, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288790621171713', NULL, '大浪分公司', 'DL', '11', NULL, 'LBFW', '0', '1', 3, 12, NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583288967203110914', NULL, '中队1', 'zd1', '13', NULL, 'TEST', '0', '1', 4, 1, NULL, NULL, 0, '2022-10-21 10:48:01', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583289053387669505', NULL, '分队1分队1分队1分队1', 'fd1', '14', NULL, 'zd1', '0', '1', 5, 1, NULL, NULL, 0, '2022-10-21 10:48:21', 'superadmin', '超管wow', '2022-10-21 16:49:23', '6', '龙保管理员');
INSERT INTO `t_department` VALUES ('1583291019769659394', NULL, '华联中队', 'DL_HL', '13', NULL, 'DL', '0', '1', 4, 1, NULL, NULL, 0, '2022-10-21 10:56:10', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291270461640706', NULL, '大浪中队', 'DL_DL', '13', NULL, 'DL', '0', '1', NULL, 4, NULL, NULL, 0, '2022-10-21 10:57:10', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291283019386881', NULL, '观澜分公司', 'GL', '11', NULL, 'LBFW', '0', '1', NULL, 13, NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291283686281217', NULL, '观联中队', 'GL_GLI', '13', NULL, 'GL', '0', '1', NULL, 3, NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291284474810369', NULL, '观澜中队', 'GL_GLA', '13', NULL, 'GL', '0', '1', NULL, 1, NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291285250756610', NULL, '牛湖中队', 'GL_NH', '13', NULL, 'GL', '0', '1', NULL, 2, NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291285967982594', NULL, '民治分公司', 'MZ', '11', NULL, 'LBFW', '0', '1', NULL, 14, NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291286634876929', NULL, '民治中队', 'MZ_MZ', '13', NULL, 'MZ', '0', '1', NULL, 1, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291287343714305', NULL, '民联中队', 'MZ_ML', '13', NULL, 'MZ', '0', '1', NULL, 2, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291288123854850', NULL, '福城分公司', 'FC', '11', NULL, 'LBFW', '0', '1', NULL, 15, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291288706863106', NULL, '福联中队', 'FC_FL', '13', NULL, 'FC', '0', '1', NULL, 1, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291289419894785', NULL, '福城中队', 'FC_FC', '13', NULL, 'FC', '0', '1', NULL, 2, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291290200035330', NULL, '龙华分公司', 'LH', '11', NULL, 'LBFW', '0', '1', NULL, 16, NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291290787237890', NULL, '龙华中队', 'LH_LH', '13', NULL, 'LH', '0', '1', NULL, 1, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291291571572738', NULL, '龙联中队', 'LH_LL', '13', NULL, 'LH', '0', '1', NULL, 2, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291292292993025', NULL, '执法中队', 'LH_ZF', '13', NULL, 'LH', '0', '1', NULL, 3, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291293073133569', NULL, '观湖分公司', 'GH', '11', NULL, 'LBFW', '0', '1', NULL, 17, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291293664530434', NULL, '观联中队', 'GH_GL', '13', NULL, 'GH', '0', '1', NULL, 1, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291294453059586', NULL, '观湖中队', 'GH_GH', '13', NULL, 'GH', '0', '1', NULL, 2, NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291295178674177', NULL, '龙强物业', 'LQWY', '12', NULL, 'LBFW', '0', '1', NULL, 18, NULL, NULL, 0, '2022-10-21 10:57:16', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583291295828791298', NULL, '龙保科技', 'LBKJ', '12', NULL, 'LBFW', '0', '1', NULL, 19, NULL, NULL, 0, '2022-10-21 10:57:16', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1583292057780207618', NULL, '执法中队', 'DL_HF', '13', NULL, 'DL', '0', '1', 4, 2, NULL, NULL, 0, '2022-10-21 11:00:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1585104843696107521', NULL, '分队测试', 'FDCS', '14', NULL, 'DL_HF', '0', '1', 5, 1, NULL, NULL, 0, '2022-10-26 11:03:39', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department` VALUES ('1585889460011253761', '3333', '2222', '1111', '14', '44', 'DL_HL', '0', '1', 5, 2, NULL, NULL, 0, '2022-10-28 15:01:26', '6', '管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_department_relate
-- ----------------------------
DROP TABLE IF EXISTS `t_department_relate`;
CREATE TABLE `t_department_relate`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父祖部门id',
  `child_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '子孙部门id',
  `tenant_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  `yl_appid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'appid',
  `disabled` int(11) NULL DEFAULT 0 COMMENT '是否有效:0-有效 1-禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人昵称',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_user` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `last_update_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人创建人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department_relate
-- ----------------------------
INSERT INTO `t_department_relate` VALUES ('1583259969113124865', 'LBFW', 'LBFW', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583280670297632769', 'LBFW', 'TEST', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583280670419267586', 'TEST', 'TEST', NULL, NULL, 0, '2022-10-21 10:15:02', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288786296844290', 'LBFW', 'TEST', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288786418479106', 'TEST', 'TEST', NULL, NULL, 0, '2022-10-21 10:47:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288786703691778', 'LBFW', 'XXJSB', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288786829520897', 'XXJSB', 'XXJSB', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288787114733569', 'LBFW', 'PXJD', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288787227979778', 'PXJD', 'PXJD', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288787534163970', 'LBFW', 'ZHBGS', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288787643215874', 'ZHBGS', 'ZHBGS', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288787932622849', 'LBFW', 'CWS', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288788041674753', 'CWS', 'CWS', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288788352053250', 'LBFW', 'HWQGLB', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288788473688066', 'HWQGLB', 'HWQGLB', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288788763095042', 'LBFW', 'RLZYB', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288788884729857', 'RLZYB', 'RLZYB', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288789169942529', 'LBFW', 'WFS', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288789291577346', 'WFS', 'WFS', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288789572595714', 'LBFW', 'SHTZB', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288789681647618', 'SHTZB', 'SHTZB', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288789975248898', 'LBFW', 'HQB', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288790084300802', 'HQB', 'HQB', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288790377902082', 'LBFW', 'JCS', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288790499536898', 'JCS', 'JCS', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288790784749569', 'LBFW', 'DL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288790910578689', 'DL', 'DL', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288967458963458', 'LBFW', 'zd1', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288967521878018', 'TEST', 'zd1', NULL, NULL, 0, '2022-10-21 10:15:02', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288967572209665', 'LBFW', 'zd1', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288967622541314', 'TEST', 'zd1', NULL, NULL, 0, '2022-10-21 10:47:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583288967702233090', 'zd1', 'zd1', NULL, NULL, 0, '2022-10-21 10:48:01', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583289053731602433', 'fd1', 'fd1', NULL, NULL, 0, '2022-10-21 10:48:21', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291019840962561', 'LBFW', 'DL_HL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291019891294209', 'DL', 'DL_HL', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291019945820162', 'DL_HL', 'DL_HL', NULL, NULL, 0, '2022-10-21 10:56:10', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291270637801473', 'LBFW', 'DL_DL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291270767824897', 'DL', 'DL_DL', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291270893654017', 'DL_DL', 'DL_DL', NULL, NULL, 0, '2022-10-21 10:57:10', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291283166187522', 'LBFW', 'GL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291283300405250', 'GL', 'GL', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291283887607810', 'LBFW', 'GL_GLI', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291284017631233', 'GL', 'GL_GLI', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291284151848961', 'GL_GLI', 'GL_GLI', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291284671942658', 'LBFW', 'GL_GLA', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291284797771777', 'GL', 'GL_GLA', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291284927795201', 'GL_GLA', 'GL_GLA', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291285439500289', 'LBFW', 'GL_NH', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291285506609154', 'GL', 'GL_NH', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291285640826881', 'GL_NH', 'GL_NH', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291286169309186', 'LBFW', 'MZ', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291286295138306', 'MZ', 'MZ', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291286777483265', 'LBFW', 'MZ_MZ', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291286890729474', 'MZ', 'MZ_MZ', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291287020752898', 'MZ_MZ', 'MZ_MZ', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291287540846594', 'LBFW', 'MZ_ML', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291287670870017', 'MZ', 'MZ_ML', NULL, NULL, 0, '2022-10-21 10:57:13', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291287737978881', 'MZ_ML', 'MZ_ML', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291288258072578', 'LBFW', 'FC', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291288383901698', 'FC', 'FC', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291288903995394', 'LBFW', 'FC_FL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291289029824514', 'FC', 'FC_FL', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291289159847938', 'FC_FL', 'FC_FL', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291289617027073', 'LBFW', 'FC_FC', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291289747050497', 'FC', 'FC_FC', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291289877073922', 'FC_FC', 'FC_FC', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291290392973314', 'LBFW', 'LH', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291290455887873', 'LH', 'LH', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291290980175874', 'LBFW', 'LH_LH', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291291110199298', 'LH', 'LH_LH', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291291240222722', 'LH_LH', 'LH_LH', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291291768705026', 'LBFW', 'LH_LL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291291894534146', 'LH', 'LH_LL', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291291961643010', 'LH_LL', 'LH_LL', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291292490125314', 'LBFW', 'LH_ZF', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291292620148738', 'LH', 'LH_ZF', NULL, NULL, 0, '2022-10-21 10:57:14', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291292745977857', 'LH_ZF', 'LH_ZF', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291293270265858', 'LBFW', 'GH', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291293333180417', 'GH', 'GH', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291293861662722', 'LBFW', 'GH_GL', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291293995880450', 'GH', 'GH_GL', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291294130098177', 'GH_GL', 'GH_GL', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291294583083010', 'LBFW', 'GH_GH', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291294717300738', 'GH', 'GH_GH', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291294847324161', 'GH_GH', 'GH_GH', NULL, NULL, 0, '2022-10-21 10:57:15', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291295371612162', 'LBFW', 'LQWY', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291295501635586', 'LQWY', 'LQWY', NULL, NULL, 0, '2022-10-21 10:57:16', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291296034312193', 'LBFW', 'LBKJ', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583291296155947010', 'LBKJ', 'LBKJ', NULL, NULL, 0, '2022-10-21 10:57:16', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583292057851510786', 'LBFW', 'DL_HF', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583292057893453826', 'DL', 'DL_HF', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583292057931202561', 'DL_HF', 'DL_HF', NULL, NULL, 0, '2022-10-21 11:00:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583380086234529793', 'LBFW', 'fd1', NULL, NULL, 0, '2022-10-21 16:50:05', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583380086276472834', 'TEST', 'fd1', NULL, NULL, 0, '2022-10-21 16:50:05', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583380086310027265', 'LBFW', 'fd1', NULL, NULL, 0, '2022-10-21 16:50:05', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583380086347776001', 'TEST', 'fd1', NULL, NULL, 0, '2022-10-21 16:50:05', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1583380086393913345', 'zd1', 'fd1', NULL, NULL, 0, '2022-10-21 16:50:05', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585104843859685377', 'LBFW', 'FDCS', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585104843935182849', 'DL', 'FDCS', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585104844002291714', 'DL_HF', 'FDCS', NULL, NULL, 0, '2022-10-21 11:00:17', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585104844065206273', 'FDCS', 'FDCS', NULL, NULL, 0, '2022-10-26 11:03:39', '6', '龙保管理员', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585889460191608834', 'LBFW', '1111', NULL, NULL, 0, '2022-10-21 08:52:47', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585889460262912002', 'DL', '1111', NULL, NULL, 0, '2022-10-21 10:47:18', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585889460325826561', 'DL_HL', '1111', NULL, NULL, 0, '2022-10-21 10:56:10', 'superadmin', '超管wow', NULL, NULL, NULL);
INSERT INTO `t_department_relate` VALUES ('1585889460392935426', '1111', '1111', NULL, NULL, 0, '2022-10-28 15:01:26', '6', '管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionaries`;
CREATE TABLE `t_dictionaries`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `yl_appid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'yl app id',
  `tenant_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  `yl_app_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'app_name',
  `dic_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明，描述',
  `dic_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典唯一编码',
  `dic_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '值',
  `value_type` int(2) NULL DEFAULT 0 COMMENT '0-非富文本;1-富文本；2-图片；3-文件',
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外键，父级id。没有父级null',
  `dic_level` int(2) NULL DEFAULT 0 COMMENT '层级（0，1）。最顶级是0',
  `is_use` int(1) NULL DEFAULT 1 COMMENT '显示状态:0当前禁用,1当前使用',
  `sort` int(3) NULL DEFAULT 5 COMMENT '排序序号',
  `disabled` int(1) NULL DEFAULT 0 COMMENT '是否有效:0-有效 1-禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人昵称',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `last_update_user_nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人创建人昵称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_code`(`dic_code`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dictionaries
-- ----------------------------
INSERT INTO `t_dictionaries` VALUES ('1329318640427651074', NULL, NULL, NULL, '后台标题', 'cms_title', '基础服务', 0, '-1', 1, 1, 8, 0, '2020-11-19 15:00:36', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1329332574954164225', NULL, NULL, NULL, '登陆背景图', 'login_back_ground_image', '1557280296944111618', 2, '-1', 1, 1, 9, 0, '2020-11-19 15:55:58', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1329357485571538945', NULL, NULL, NULL, '多文件', 'moulti_file', 'moulti_file,D1B01207-CF69-41A8-9322-60F9744AC38E', 3, '-1', 1, 1, 2, 1, '2020-11-19 17:34:57', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1329361975301341186', NULL, NULL, NULL, '再多文件', '12', '12', 3, '-1', 1, 1, 10, 1, '2020-11-19 17:52:48', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1373280539488194561', NULL, NULL, NULL, '餐类', 'dic_meal_options', '1', 0, '-1', 1, 1, 7, 1, '2021-03-20 22:29:30', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1373280567178989570', NULL, NULL, NULL, '中餐', 'dic_meal_options_child_1', '1', 0, '1373280539488194561', 2, 1, 2, 0, '2021-03-20 22:29:36', 'superadmin', '超管', '2021-03-20 23:34:24', 'superadmin', '超管');
INSERT INTO `t_dictionaries` VALUES ('1373280588578328577', NULL, NULL, NULL, '早餐', 'dic_meal_options_child_2', '2', 0, '1373280539488194561', 2, 1, 1, 0, '2021-03-20 22:29:41', 'superadmin', '超管', '2021-03-20 23:34:09', 'superadmin', '超管');
INSERT INTO `t_dictionaries` VALUES ('1373296926478733313', NULL, NULL, NULL, '晚餐', '', '3', 0, '1373280539488194561', 2, 1, 3, 0, '2021-03-20 23:34:37', 'superadmin', '超管', '2021-03-20 23:34:52', 'superadmin', '超管');
INSERT INTO `t_dictionaries` VALUES ('1373299290652708866', NULL, NULL, NULL, '状态', 'disabled_options', 'disabled_options', 0, '-1', 1, 1, 1, 0, '2021-03-20 23:44:00', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1373299309854232577', NULL, NULL, NULL, '正常', '', '0', 0, '1373299290652708866', 2, 1, 2, 0, '2021-03-20 23:44:05', 'superadmin', '超管', '2022-10-31 11:15:41', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1373299316653199362', NULL, NULL, NULL, '已删除', '1', '1', 0, '1373299290652708866', 2, 1, 1, 0, '2021-03-20 23:44:06', 'superadmin', '超管', '2022-10-31 11:16:14', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1555395792654667777', NULL, NULL, NULL, '222', '11', '11', 0, '-1', 1, 1, 3, 1, '2022-08-05 11:30:29', 'superadmin', '超管', '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1586923654153740290', NULL, NULL, NULL, '单方事故森岛帆高', '个讽德诵功', '和轨道局', 0, '-1', 1, 1, 5, 0, NULL, NULL, NULL, '2022-11-01 01:14:19', '1', '管理员');
INSERT INTO `t_dictionaries` VALUES ('1587043706685288450', NULL, NULL, NULL, '个讽德诵功', '鹤骨鸡肤', '接口', 0, '-1', 1, 1, 4, 0, NULL, NULL, NULL, '2022-11-01 01:14:19', '1', '管理员');

SET FOREIGN_KEY_CHECKS = 1;
