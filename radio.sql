/*
Navicat MySQL Data Transfer

Source Server         : localhost8.0
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : radio

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-07-26 16:47:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_title` varchar(255) NOT NULL COMMENT '文章标题',
  `path` varchar(255) NOT NULL COMMENT '文章路径',
  `create_time` datetime NOT NULL COMMENT '文章创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次修改时间',
  `author` varchar(255) NOT NULL COMMENT '作者',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '123', 'C:\\Users\\zhangyujia\\Desktop\\新建文本文档.txt', '2021-07-21 16:19:43', '2021-07-27 16:19:48', 'tom', '待通过');
INSERT INTO `article` VALUES ('2', '新建文本文档', 'E:/file/article/新建文本文档.txt', '2021-07-24 14:01:58', '2021-07-24 14:01:58', '英语编辑', '待审阅');
INSERT INTO `article` VALUES ('3', 'hjewgjah', 'E:/file/article/hjewgjah.txt', '2021-07-24 14:03:24', '2021-07-24 14:03:24', 'yingyuj', '待审阅');
INSERT INTO `article` VALUES ('4', '返回给', 'E:/file/article/.txt', '2021-07-24 16:42:23', '2021-07-24 16:42:23', '文学编辑', '待审阅');

-- ----------------------------
-- Table structure for check_log
-- ----------------------------
DROP TABLE IF EXISTS `check_log`;
CREATE TABLE `check_log` (
  `check_id` bigint NOT NULL AUTO_INCREMENT COMMENT '签到id',
  `check_datetime` datetime NOT NULL COMMENT '签到日期时间',
  `username` varchar(255) NOT NULL COMMENT '签到人员username',
  PRIMARY KEY (`check_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_log
-- ----------------------------
INSERT INTO `check_log` VALUES ('5', '2021-07-23 17:15:03', 'root');
INSERT INTO `check_log` VALUES ('6', '2021-07-23 21:11:27', 'root');
INSERT INTO `check_log` VALUES ('7', '2021-07-23 21:33:45', 'root');
INSERT INTO `check_log` VALUES ('8', '2021-07-26 13:54:06', 'root');

-- ----------------------------
-- Table structure for edit_arrange
-- ----------------------------
DROP TABLE IF EXISTS `edit_arrange`;
CREATE TABLE `edit_arrange` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `arrange_date` date NOT NULL COMMENT '安排日期',
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edit_arrange
-- ----------------------------
INSERT INTO `edit_arrange` VALUES ('1', '2021-07-28', '5');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', '体育');
INSERT INTO `group` VALUES ('2', '音乐');
INSERT INTO `group` VALUES ('3', '文学');
INSERT INTO `group` VALUES ('4', '英语');
INSERT INTO `group` VALUES ('5', '生活');
INSERT INTO `group` VALUES ('6', '督导');
INSERT INTO `group` VALUES ('7', '宣策');
INSERT INTO `group` VALUES ('8', '栏目总编');
INSERT INTO `group` VALUES ('9', '技术');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成员姓名',
  `password` varchar(255) NOT NULL COMMENT '登陆密码',
  `username` varchar(255) NOT NULL COMMENT '登陆用户名',
  `join_time` datetime NOT NULL COMMENT '加入时间',
  `create_time` datetime NOT NULL COMMENT '创建时间（自动生成）',
  `update_time` datetime NOT NULL COMMENT '最后活动时间',
  `root` int NOT NULL DEFAULT '0' COMMENT '是否为超管',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'root', '123456', 'root', '2021-07-21 13:53:17', '2021-07-21 13:53:19', '2021-07-21 13:53:29', '1');
INSERT INTO `member` VALUES ('2', '台长', '123456', 'taizhang', '2021-07-21 13:54:40', '2021-07-21 13:54:44', '2021-07-21 13:54:46', '0');
INSERT INTO `member` VALUES ('3', '英语组长', '123456', 'yingyuz', '2021-07-21 13:55:17', '2021-07-21 13:55:22', '2021-07-21 13:55:25', '0');
INSERT INTO `member` VALUES ('4', '英语播音', '123456', 'yingyub', '2021-07-21 13:56:00', '2021-07-21 13:56:03', '2021-07-21 13:56:06', '0');
INSERT INTO `member` VALUES ('5', '英语编辑', '123456', 'yingyuj', '2021-07-22 15:59:46', '2021-07-22 15:59:51', '2021-07-22 15:59:54', '0');
INSERT INTO `member` VALUES ('6', '督导', '123456', 'dudao', '2021-07-22 19:55:46', '2021-07-22 19:55:50', '2021-07-22 19:55:54', '0');
INSERT INTO `member` VALUES ('7', '技术', '123456', 'jishu', '2021-07-22 19:56:12', '2021-07-22 19:56:14', '2021-07-22 19:56:16', '0');
INSERT INTO `member` VALUES ('8', '宣策', '123456', 'xuance', '2021-07-22 19:56:36', '2021-07-22 19:56:38', '2021-07-22 19:56:40', '0');
INSERT INTO `member` VALUES ('9', '体育组长', '123456', 'tiyuz', '2021-07-22 19:57:26', '2021-07-22 19:57:29', '2021-07-22 19:57:32', '0');
INSERT INTO `member` VALUES ('10', '音乐组长', '123456', 'yinyuez', '2021-07-22 19:57:26', '2021-07-22 19:57:29', '2021-07-22 19:57:32', '0');
INSERT INTO `member` VALUES ('11', '文学组长', '123456', 'wenxuez', '2021-07-22 19:57:26', '2021-07-22 19:57:29', '2021-07-22 19:57:32', '0');
INSERT INTO `member` VALUES ('12', '生活组长', '123456', 'shenghuoz', '2021-07-22 19:57:26', '2021-07-22 19:57:29', '2021-07-22 19:57:32', '0');
INSERT INTO `member` VALUES ('13', '体育编辑', '123456', 'tiyuj', '2021-07-22 15:59:46', '2021-07-22 15:59:51', '2021-07-22 15:59:54', '0');
INSERT INTO `member` VALUES ('14', '体育播音', '123456', 'tiyub', '2021-07-21 13:56:00', '2021-07-21 13:56:03', '2021-07-21 13:56:06', '0');
INSERT INTO `member` VALUES ('15', '音乐编辑', '123456', 'yinyuej', '2021-07-22 15:59:46', '2021-07-22 15:59:51', '2021-07-22 15:59:54', '0');
INSERT INTO `member` VALUES ('16', '音乐播音', '123456', 'yinyueb', '2021-07-21 13:56:00', '2021-07-21 13:56:03', '2021-07-21 13:56:06', '0');
INSERT INTO `member` VALUES ('17', '文学播音', '123456', 'wenxueb', '2021-07-21 13:56:00', '2021-07-21 13:56:03', '2021-07-21 13:56:06', '0');
INSERT INTO `member` VALUES ('18', '文学编辑', '123456', 'wenxuej', '2021-07-22 15:59:46', '2021-07-22 15:59:51', '2021-07-22 15:59:54', '0');
INSERT INTO `member` VALUES ('19', '生活播音', '123456', 'shenghuob', '2021-07-21 13:56:00', '2021-07-21 13:56:03', '2021-07-21 13:56:06', '0');
INSERT INTO `member` VALUES ('20', '生活编辑', '123456', 'shenghuoj', '2021-07-22 15:59:46', '2021-07-22 15:59:51', '2021-07-22 15:59:54', '0');
INSERT INTO `member` VALUES ('21', '栏目总编', '123456', 'lanmu', '2021-07-28 22:11:37', '2021-07-28 22:11:41', '2021-07-27 22:11:49', '0');

-- ----------------------------
-- Table structure for member_group
-- ----------------------------
DROP TABLE IF EXISTS `member_group`;
CREATE TABLE `member_group` (
  `member_id` bigint NOT NULL,
  `group_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_group
-- ----------------------------
INSERT INTO `member_group` VALUES ('3', '4');
INSERT INTO `member_group` VALUES ('4', '4');
INSERT INTO `member_group` VALUES ('5', '4');
INSERT INTO `member_group` VALUES ('6', '6');
INSERT INTO `member_group` VALUES ('7', '9');
INSERT INTO `member_group` VALUES ('8', '7');
INSERT INTO `member_group` VALUES ('9', '1');
INSERT INTO `member_group` VALUES ('10', '2');
INSERT INTO `member_group` VALUES ('11', '3');
INSERT INTO `member_group` VALUES ('12', '5');
INSERT INTO `member_group` VALUES ('13', '1');
INSERT INTO `member_group` VALUES ('14', '1');
INSERT INTO `member_group` VALUES ('15', '2');
INSERT INTO `member_group` VALUES ('16', '2');
INSERT INTO `member_group` VALUES ('17', '3');
INSERT INTO `member_group` VALUES ('18', '3');
INSERT INTO `member_group` VALUES ('19', '5');
INSERT INTO `member_group` VALUES ('20', '5');
INSERT INTO `member_group` VALUES ('21', '8');

-- ----------------------------
-- Table structure for member_role
-- ----------------------------
DROP TABLE IF EXISTS `member_role`;
CREATE TABLE `member_role` (
  `member_id` bigint NOT NULL COMMENT '成员id',
  `role_id` bigint NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_role
-- ----------------------------
INSERT INTO `member_role` VALUES ('2', '1');
INSERT INTO `member_role` VALUES ('3', '2');
INSERT INTO `member_role` VALUES ('4', '3');
INSERT INTO `member_role` VALUES ('5', '4');
INSERT INTO `member_role` VALUES ('6', '5');
INSERT INTO `member_role` VALUES ('7', '6');
INSERT INTO `member_role` VALUES ('8', '7');
INSERT INTO `member_role` VALUES ('9', '2');
INSERT INTO `member_role` VALUES ('10', '2');
INSERT INTO `member_role` VALUES ('11', '2');
INSERT INTO `member_role` VALUES ('12', '2');
INSERT INTO `member_role` VALUES ('13', '4');
INSERT INTO `member_role` VALUES ('14', '3');
INSERT INTO `member_role` VALUES ('15', '4');
INSERT INTO `member_role` VALUES ('16', '3');
INSERT INTO `member_role` VALUES ('17', '3');
INSERT INTO `member_role` VALUES ('18', '4');
INSERT INTO `member_role` VALUES ('19', '3');
INSERT INTO `member_role` VALUES ('20', '4');
INSERT INTO `member_role` VALUES ('21', '8');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'broadcast');
INSERT INTO `permission` VALUES ('2', 'broadcastz');
INSERT INTO `permission` VALUES ('3', 'edit');
INSERT INTO `permission` VALUES ('4', 'lanmuzongbian');
INSERT INTO `permission` VALUES ('5', 'taizhang');
INSERT INTO `permission` VALUES ('6', 'jishu');
INSERT INTO `permission` VALUES ('7', 'dudao');
INSERT INTO `permission` VALUES ('8', 'xuance');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '台长', null);
INSERT INTO `role` VALUES ('2', '播音组长', null);
INSERT INTO `role` VALUES ('3', '播音', null);
INSERT INTO `role` VALUES ('4', '编辑', null);
INSERT INTO `role` VALUES ('5', '督导', null);
INSERT INTO `role` VALUES ('6', '技术', null);
INSERT INTO `role` VALUES ('7', '宣策', null);
INSERT INTO `role` VALUES ('8', '栏目总编', null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `permission_id` bigint NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('2', '2');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('4', '3');
INSERT INTO `role_permission` VALUES ('5', '7');
INSERT INTO `role_permission` VALUES ('6', '6');
INSERT INTO `role_permission` VALUES ('7', '8');
INSERT INTO `role_permission` VALUES ('8', '4');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `edit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编辑',
  `broadcast` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '播音',
  `group` varchar(255) NOT NULL COMMENT '组别',
  `article_id` bigint NOT NULL COMMENT '稿子id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', '2021-07-25 12:00:00', '张三', '王五', '英語', '1');
INSERT INTO `schedule` VALUES ('2', '2021-07-26 15:05:55', '王五', '王五', '文學', '2');
