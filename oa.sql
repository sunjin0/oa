/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80036
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 80036
File Encoding         : 65001

Date: 2024-04-19 16:52:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pattern` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '/');
INSERT INTO `resources` VALUES ('2', '/admin');
INSERT INTO `resources` VALUES ('3', '/user');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROOT', '超级管理员');
INSERT INTO `role` VALUES ('2', 'ADMIN', '管理员');
INSERT INTO `role` VALUES ('3', 'USER', '普通用户');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `resource_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1');
INSERT INTO `role_resource` VALUES ('2', '2', '2');
INSERT INTO `role_resource` VALUES ('3', '3', '3');
INSERT INTO `role_resource` VALUES ('4', '2', '2');

-- ----------------------------
-- Table structure for role_route
-- ----------------------------
DROP TABLE IF EXISTS `role_route`;
CREATE TABLE `role_route` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `route_id` int DEFAULT NULL COMMENT '路由Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_route
-- ----------------------------
INSERT INTO `role_route` VALUES ('1', '1', '1');
INSERT INTO `role_route` VALUES ('2', '1', '2');
INSERT INTO `role_route` VALUES ('3', '1', '3');
INSERT INTO `role_route` VALUES ('4', '1', '4');
INSERT INTO `role_route` VALUES ('5', '2', '1');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `parent_id` int DEFAULT NULL COMMENT '父路由Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由表';

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES ('1', '用户管理', '/user/management', '0');
INSERT INTO `route` VALUES ('2', '角色管理', '/role/management', '0');
INSERT INTO `route` VALUES ('3', '资源管理', '/resources/management', '0');
INSERT INTO `route` VALUES ('4', '权限分配', '/role-resources/management', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enable` tinyint DEFAULT NULL,
  `locked` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('2', 'admin', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('3', 'user', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('107', 'user3', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('108', 'user4', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('109', 'user5', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('110', 'user6', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('111', 'user7', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('112', 'user8', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('113', 'user9', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('114', 'user10', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('115', 'user11', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('116', 'user12', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('117', 'user13', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('118', 'user14', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('119', 'user15', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('120', 'user16', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('121', 'user17', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('122', 'user18', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('123', 'user19', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('124', 'user20', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('125', 'user21', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('126', 'user22', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('127', 'user23', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('128', 'user24', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('129', 'user25', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('130', 'user26', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('131', 'user27', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('132', 'user28', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('133', 'user29', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('134', 'user30', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('135', 'user31', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('136', 'user32', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('137', 'user33', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('138', 'user34', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('139', 'user35', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('140', 'user36', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('141', 'user37', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('142', 'user38', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('143', 'user39', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('144', 'user40', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('145', 'user41', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('146', 'user42', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('147', 'user43', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('148', 'user44', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('149', 'user45', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('150', 'user46', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('151', 'user47', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('152', 'user48', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('153', 'user49', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('154', 'user50', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('155', 'user51', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('156', 'user52', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('157', 'user53', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('158', 'user54', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('159', 'user55', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('160', 'user56', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('161', 'user57', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('162', 'user58', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('163', 'user59', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('164', 'user60', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('165', 'user61', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('166', 'user62', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('167', 'user63', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('168', 'user64', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('169', 'user65', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('170', 'user66', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('171', 'user67', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('172', 'user68', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('173', 'user69', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('174', 'user70', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('175', 'user71', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('176', 'user72', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('177', 'user73', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('178', 'user74', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('179', 'user75', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('180', 'user76', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('181', 'user77', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('182', 'user78', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('183', 'user79', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('184', 'user80', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('185', 'user81', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('186', 'user82', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('187', 'user83', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('188', 'user84', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('189', 'user85', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('190', 'user86', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('191', 'user87', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('192', 'user88', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('193', 'user89', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('194', 'user90', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('195', 'user91', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('196', 'user92', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('197', 'user93', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('198', 'user94', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('199', 'user95', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('200', 'user96', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('201', 'user97', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('202', 'user98', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('204', '张三', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');
INSERT INTO `user` VALUES ('205', '张五', 'wflYk5saIuyUdCjuOkX6NQ==', '1', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '3');
INSERT INTO `user_role` VALUES ('4', '1', '2');
