/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : 127.0.0.1
 Source Database       : yxyqcy_test

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : utf-8

 Date: 10/05/2017 22:43:30 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `f_movie_theatre`
-- ----------------------------
DROP TABLE IF EXISTS `f_movie_theatre`;
CREATE TABLE `f_movie_theatre` (
  `business_id` varchar(64) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL COMMENT '电影院名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `ORDER_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `f_movie_theatre`
-- ----------------------------
BEGIN;
INSERT INTO `f_movie_theatre` VALUES ('0001', '中传国际影城(天津西站店)', null, null, '2017-02-03 20:41:46', null, '2017-02-03 20:41:48', null, '0', '1', null), ('0002', '横店电影城(瑞景店)', null, null, '2017-02-03 20:42:32', null, '2017-02-03 20:42:36', null, '0', '1', null), ('0003', '中影国际影城(津湾店)', null, null, '2017-02-03 20:43:14', null, '2017-02-03 20:43:17', null, '0', '1', null), ('0004', '金逸影城(大悦城IMAX店)', null, null, '2017-02-03 20:43:36', null, '2017-02-03 20:43:39', null, '0', '1', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
