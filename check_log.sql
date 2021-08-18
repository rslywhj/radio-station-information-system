/*
Navicat MySQL Data Transfer

Source Server         : mysql8.0
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : radio

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-07-23 16:07:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for check_log
-- ----------------------------
DROP TABLE IF EXISTS `check_log`;
CREATE TABLE `check_log` (
  `check_id` bigint NOT NULL AUTO_INCREMENT COMMENT '签到id',
  `check_datetime` datetime NOT NULL COMMENT '签到日期时间',
  `username` varchar(255) NOT NULL COMMENT '签到人员username',
  PRIMARY KEY (`check_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
