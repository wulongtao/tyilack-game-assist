/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50721
Source Host           : 119.29.105.15:3306
Source Database       : tyilack_game_assist

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-11 16:03:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '游戏名称',
  `logo` varchar(255) NOT NULL COMMENT '下边tab图标',
  `status` int(11) DEFAULT '0' COMMENT '游戏状态，0->未启动，1->配置完成可启动任务，2->正在执行任务',
  `complete_operation` int(11) DEFAULT '0' COMMENT '任务执行完成之后的操作，0->待命，1->关机',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', 'soul', 'image\\\\soul\\\\basic\\\\tab.png', '0', '0', '2019-02-16 13:58:06', '2019-02-16 13:58:29');

-- ----------------------------
-- Table structure for game_command_group
-- ----------------------------
DROP TABLE IF EXISTS `game_command_group`;
CREATE TABLE `game_command_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '指令集名称',
  `repeat` int(11) DEFAULT '1' COMMENT '重复次数',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_command_group
-- ----------------------------
INSERT INTO `game_command_group` VALUES ('1', ' 登录指令集', '1', '2019-02-16 14:14:06', '2019-02-16 14:14:06');
INSERT INTO `game_command_group` VALUES ('2', '测试指令集', '1', '2019-03-07 11:03:50', '2019-03-07 11:03:50');

-- ----------------------------
-- Table structure for game_command_group_item
-- ----------------------------
DROP TABLE IF EXISTS `game_command_group_item`;
CREATE TABLE `game_command_group_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT '指令集ID',
  `condition` varchar(255) DEFAULT NULL COMMENT '执行条件，一般是存在某个图表执行指定操作',
  `location` varchar(255) DEFAULT NULL COMMENT '区域找图定位图片，或者某个具体坐标如：11,110',
  `location_click` tinyint(4) DEFAULT '1' COMMENT '0->不点击location位置，1->点击location位置',
  `offset_x` int(11) DEFAULT '0' COMMENT 'x方向偏移量',
  `offset_y` int(11) DEFAULT '0' COMMENT 'y方向偏移量',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作指令，包括鼠标操作和键盘操作',
  `duration` int(11) DEFAULT '1000' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_command_group_item
-- ----------------------------
INSERT INTO `game_command_group_item` VALUES ('1', '1', null, '1', '1', '0', '0', null, '3000', '2019-02-16 14:15:35', '2019-03-06 16:35:13');
INSERT INTO `game_command_group_item` VALUES ('2', '1', null, '2', '1', '0', '0', null, '20000', '2019-02-16 14:15:35', '2019-03-06 16:35:16');
INSERT INTO `game_command_group_item` VALUES ('3', '2', '3', '3', '1', '0', '0', 'a 1 1,b 2 2', '1000', '2019-03-07 11:07:13', '2019-03-07 11:52:48');

-- ----------------------------
-- Table structure for game_gallery
-- ----------------------------
DROP TABLE IF EXISTS `game_gallery`;
CREATE TABLE `game_gallery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '图片分组名称',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_gallery
-- ----------------------------
INSERT INTO `game_gallery` VALUES ('1', '登录相关', '2019-03-06 11:23:38', '2019-03-06 11:24:11');
INSERT INTO `game_gallery` VALUES ('2', '前置操作图标', '2019-03-09 14:02:07', '2019-03-09 14:02:07');

-- ----------------------------
-- Table structure for game_gallery_item
-- ----------------------------
DROP TABLE IF EXISTS `game_gallery_item`;
CREATE TABLE `game_gallery_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gallery_id` int(11) NOT NULL COMMENT '图片分组ID',
  `name` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_gallery_item
-- ----------------------------
INSERT INTO `game_gallery_item` VALUES ('1', '1', '频道', 'image\\\\soul\\\\basic\\\\channel.png', '2019-03-06 11:24:45', '2019-03-06 11:24:45');
INSERT INTO `game_gallery_item` VALUES ('2', '1', '登录二维码', 'image\\\\soul\\\\basic\\\\qr_code.png', '2019-03-06 11:25:05', '2019-03-06 11:25:05');
INSERT INTO `game_gallery_item` VALUES ('3', '1', '微信', 'image/soul/basic/wechat.png', '2019-03-07 11:05:45', '2019-03-07 11:05:45');

-- ----------------------------
-- Table structure for game_param
-- ----------------------------
DROP TABLE IF EXISTS `game_param`;
CREATE TABLE `game_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '参数名',
  `desc` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_param
-- ----------------------------
INSERT INTO `game_param` VALUES ('1', 'screen_capture_base_path', '截图相对路径', '/upload/capture/', '2019-02-14 17:21:50', '2019-02-14 17:21:50');
INSERT INTO `game_param` VALUES ('2', 'domain_url', '默认域名网址', 'http://localhost:8080', '2019-02-14 17:21:50', '2019-02-14 17:21:50');
INSERT INTO `game_param` VALUES ('3', 'local_image_dir', '图片存放路径', 'E:\\\\project\\\\JavaProject\\\\tyilack-game-assist\\\\public\\\\', '2019-02-16 14:47:36', '2019-02-16 16:14:52');

-- ----------------------------
-- Table structure for game_pre_command
-- ----------------------------
DROP TABLE IF EXISTS `game_pre_command`;
CREATE TABLE `game_pre_command` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `group_id` int(11) NOT NULL COMMENT '指令集ID',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_pre_command
-- ----------------------------

-- ----------------------------
-- Table structure for game_task
-- ----------------------------
DROP TABLE IF EXISTS `game_task`;
CREATE TABLE `game_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `group_id` int(11) NOT NULL COMMENT '指令集ID',
  `trigger_time` datetime DEFAULT NULL COMMENT '游戏触发时间',
  `order` int(11) DEFAULT '0' COMMENT '任务优先级排序',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_task
-- ----------------------------

-- ----------------------------
-- Table structure for game_task_command_group
-- ----------------------------
DROP TABLE IF EXISTS `game_task_command_group`;
CREATE TABLE `game_task_command_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL COMMENT '任务ID',
  `group_id` int(11) NOT NULL COMMENT '指令集ID',
  `order` int(11) DEFAULT '0' COMMENT '顺序，数字小的最先运行',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_task_command_group
-- ----------------------------

-- ----------------------------
-- Table structure for game_windows_runner
-- ----------------------------
DROP TABLE IF EXISTS `game_windows_runner`;
CREATE TABLE `game_windows_runner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `group_id` int(11) NOT NULL COMMENT '命令集合ID',
  `name` varchar(255) DEFAULT NULL COMMENT '运行程序名称',
  `program_source` varchar(255) NOT NULL COMMENT '运行程序路径',
  `duration` int(11) DEFAULT '1000' COMMENT '运行程序后等待的时间',
  `repeat` int(11) DEFAULT '1' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_windows_runner
-- ----------------------------
INSERT INTO `game_windows_runner` VALUES ('1', '1', '1', '魔域运行程序', 'C:\\Program Files (x86)\\NetDragon\\魔域-箭破天穹\\play.exe', '3000', '2', '2019-02-16 14:17:50', '2019-02-16 16:42:34');
SET FOREIGN_KEY_CHECKS=1;
