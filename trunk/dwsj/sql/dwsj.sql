/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : dwsj

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2014-05-08 17:48:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dwsj_comments`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_comments`;
CREATE TABLE `dwsj_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_user` int(11) NOT NULL,
  `dwsj_information` int(11) NOT NULL DEFAULT '0',
  `dwsj_image` int(11) NOT NULL DEFAULT '0',
  `comment` varchar(1000) NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_images`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_images`;
CREATE TABLE `dwsj_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_place` int(11) NOT NULL,
  `dwsj_user` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image_information` varchar(1000) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_images
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_information`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_information`;
CREATE TABLE `dwsj_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_place` int(11) NOT NULL,
  `dwsj_user` int(11) NOT NULL,
  `information` varchar(1000) NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_information
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_places`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_places`;
CREATE TABLE `dwsj_places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_user` int(11) NOT NULL,
  `place_name` varchar(255) NOT NULL,
  `place_description` varchar(1000) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_places
-- ----------------------------

-- ----------------------------
-- Table structure for `dwsj_rates`
-- ----------------------------
DROP TABLE IF EXISTS `dwsj_rates`;
CREATE TABLE `dwsj_rates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwsj_user` int(11) NOT NULL,
  `dwsj_information` int(11) NOT NULL DEFAULT '0',
  `dwsj_image` int(11) NOT NULL DEFAULT '0',
  `rate` int(11) NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_rates
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
  `guide` int(11) DEFAULT '0',
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dwsj_users
-- ----------------------------
