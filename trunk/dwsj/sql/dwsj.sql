/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : dwsj

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2014-04-29 01:20:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dwsj_images`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_images`;
CREATE TABLE `dwsj_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_place` int(11) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_images
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_places`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_places`;
CREATE TABLE `dwsj_places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_user` int(11) NOT NULL,
  `place_name` varchar(255) NOT NULL,
  `place_description` varchar(255) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_places
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_users`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_users`;
CREATE TABLE `dwsj_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_users
-- ----------------------------
INSERT INTO `dwsj_users` VALUES ('1', 'dwsj', 'dwsj', 'dwsj', '2014-04-29 00:39:34');
INSERT INTO `dwsj_users` VALUES ('2', 'dwsj1', 'dwsj1', 'dwsj1', '2014-04-29 00:00:00');
