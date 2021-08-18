/*
Navicat MySQL Data Transfer

Source Server         : localhost8.0
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : radio

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-07-28 09:05:48
*/

SET FOREIGN_KEY_CHECKS=0;

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
