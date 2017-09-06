/*
Navicat MySQL Data Transfer

Source Server         : 120.24.79.86
Source Server Version : 50631
Source Host           : 120.24.79.86:3306
Source Database       : mcoding

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-09-26 12:17:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_wx_account_config
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_account_config`;
CREATE TABLE `t_wx_account_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '公众号 名字',
  `account_type` int(2) NOT NULL COMMENT '公众号类型，1服务号，2订阅号，3企业号',
  `code` varchar(100) NOT NULL COMMENT '微信号',
  `origin_id` varchar(100) NOT NULL COMMENT '公众号 原始id',
  `app_id` varchar(100) NOT NULL,
  `app_secret` varchar(255) NOT NULL,
  `token` varchar(100) NOT NULL,
  `aes_key` varchar(43) DEFAULT NULL,
  `encrypt_type` int(2) DEFAULT '1' COMMENT '加密模式，1明文模式，2兼容模式，3安全模式',
  `is_pay_enable` int(1) NOT NULL DEFAULT '0' COMMENT '是否支持微信支付，1支持，0不支持',
  `mch_id` varchar(100) DEFAULT NULL COMMENT '微信支付，商户id',
  `mch_key` varchar(255) DEFAULT NULL COMMENT '微信支付,商户key',
  `domain` varchar(255) DEFAULT NULL COMMENT '公众号服务器域名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='微信账号表';

-- ----------------------------
-- Records of t_wx_account_config
-- ----------------------------
INSERT INTO `t_wx_account_config` VALUES ('1', 'hzy_wexin', '1', 'hzy_weixin', '-1', 'wx4cd8d60cd66ac9e3', 'f360f581cf1b4758ab5470caf1f7d3ed', 'imp_crm', '', '1', '0', '1ss', '1', 'http://hzywx.wicp.net:80/wechat_module');
INSERT INTO `t_wx_account_config` VALUES ('2', 'codingmobi', '1', 'sns_module', '-1', 'wx07c01da2e6bcb01d', 'd9f44c882f8c2a8d6cfb7ff4c3d2039c', 'byhealthymerriplus', '', '1', '0', '1272421901', 'c9da3fc852d9fe9aa3440a9622834ad5', 'http://mcoding.cn:18080/sns_module');
INSERT INTO `t_wx_account_config` VALUES ('3', 'JLD', '1', 'JLD', '-1', 'wxe2ece419e88d3bd5', '1d91d9e944ac5c278d0ceac35235e1e7', 'beyondGXM', '', '1', '0', '1325594301', 'c9da3fc852d9fe9aa3440a9622834ad5', 'http://selangsela.com:8080/goods_module/');
INSERT INTO `t_wx_account_config` VALUES ('4', '麦车超人', '1', 'maiche', 'gh_56997f96bdd4', 'wxe2ece419e88d3bd5', '1d91d9e944ac5c278d0ceac35235e1e7', 'beyondGXM', '', '1', '0', '', '', 'http://hzywx.wicp.net:80/wechat_module');

-- ----------------------------
-- Table structure for t_wx_member
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_member`;
CREATE TABLE `t_wx_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` int(11) DEFAULT NULL COMMENT '关联的会员id',
  `wx_openid` varchar(255) DEFAULT NULL COMMENT 'open_id',
  `wx_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '称昵',
  `wx_sex` int(2) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `wx_city` varchar(255) DEFAULT NULL COMMENT '用户所在城市',
  `wx_country` varchar(255) DEFAULT NULL COMMENT '用户所在国家',
  `wx_province` varchar(255) DEFAULT NULL COMMENT '用户所在省份',
  `wx_language` varchar(255) DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `wx_headimgurl` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `wx_subscribe` int(3) DEFAULT NULL COMMENT '是否关注公众号，1已关注，0未关注',
  `wx_subscribe_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用户关注时间',
  `wx_unionid` varchar(255) DEFAULT NULL COMMENT '管理多个公众号时，作为唯一吗',
  `wx_remark` varchar(255) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注',
  `wx_groupid` int(11) DEFAULT NULL COMMENT '用户所在的分组ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_openid` (`wx_openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信会员信息';

-- ----------------------------
-- Records of t_wx_member
-- ----------------------------
INSERT INTO `t_wx_member` VALUES ('1', '1', 'o_3tTs_YB1FfnRs_AEN7deGZ_lyI', null, null, null, null, null, null, null, null, null, null, null, null, '2016-08-05 13:23:22');

-- ----------------------------
-- Table structure for t_wx_msg_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_msg_rule`;
CREATE TABLE `t_wx_msg_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `wx_account_id` int(11) NOT NULL COMMENT '微信公众号id',
  `wx_account_code` varchar(11) DEFAULT NULL COMMENT '微信公众号编码',
  `handlers` varchar(255) DEFAULT NULL COMMENT '规则处理器',
  `is_enable` int(2) DEFAULT '1' COMMENT '是否启用',
  `is_default` int(2) DEFAULT '0' COMMENT '是否默认处理器',
  `from_user_name` varchar(255) DEFAULT NULL COMMENT '消息来源openid',
  `msg_type` varchar(255) DEFAULT NULL COMMENT '消息类型',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `msg_start_time` datetime DEFAULT NULL COMMENT '消息的有效开始时间',
  `msg_end_time` datetime DEFAULT NULL COMMENT '消息有效的截止时间',
  `event` varchar(255) DEFAULT NULL COMMENT '事件类型',
  `event_key` varchar(255) DEFAULT NULL COMMENT '事件KEY值',
  `match_type` int(4) DEFAULT '100' COMMENT '匹配类型,100完全匹配，200正则匹配',
  `reply_content_ref_id` int(11) DEFAULT NULL COMMENT '回复内容的外键id',
  `reply_text` varchar(255) DEFAULT NULL COMMENT '回复的文本',
  `priority` int(11) DEFAULT '0' COMMENT '优先级',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='微信消息规则';

-- ----------------------------
-- Records of t_wx_msg_rule
-- ----------------------------
INSERT INTO `t_wx_msg_rule` VALUES ('1', null, '1', 'hzy_weixin', null, '1', '0', null, 'text', '哈哈', null, null, null, null, '100', null, '您好', '0', '2016-09-22 12:22:21');
INSERT INTO `t_wx_msg_rule` VALUES ('2', '问候语', '1', 'hzy_weixin', '', '1', '0', '', 'text', '你好', null, null, '', '', '200', null, '不好', '14', '2016-09-22 13:15:54');
INSERT INTO `t_wx_msg_rule` VALUES ('3', null, '1', 'hzy_weixin', '', '1', '0', '', 'text', '跟你好', null, null, '', '', '200', null, '跟你好', '13', '2016-09-22 15:51:45');
INSERT INTO `t_wx_msg_rule` VALUES ('4', null, '1', 'hzy_weixin', '', '1', '0', '', 'event', '', null, null, 'SCAN', '', '100', null, '扫码', '1', '2016-09-22 15:54:25');
INSERT INTO `t_wx_msg_rule` VALUES ('5', null, '1', 'hzy_weixin', '', '1', '0', '', 'event', '', null, null, 'subscribe', '', '100', null, '谢谢关注', '1', '2016-09-22 15:57:28');
INSERT INTO `t_wx_msg_rule` VALUES ('6', null, '1', 'hzy_weixin', '', '1', '0', '', 'image', '', null, null, '', '', '100', null, '图片', '0', '2016-09-22 15:58:52');
INSERT INTO `t_wx_msg_rule` VALUES ('7', '菜单点击处理', '4', 'maiche', '', '1', '0', '', 'event', '', null, null, 'CLICK', 'new1', '100', null, '收到new1', '1', '2016-09-22 16:23:56');

-- ----------------------------
-- Table structure for t_wx_template_message
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_template_message`;
CREATE TABLE `t_wx_template_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wx_account_id` int(11) DEFAULT NULL,
  `template_id` varchar(255) DEFAULT NULL COMMENT '模板消息id',
  `type` varchar(20) DEFAULT NULL COMMENT '模板消息类型',
  `first` varchar(255) DEFAULT NULL COMMENT 'first',
  `remark` varchar(255) DEFAULT NULL COMMENT 'remark',
  `color` varchar(10) DEFAULT NULL COMMENT 'color',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_u_aid_type` (`wx_account_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='模板消息';

-- ----------------------------
-- Records of t_wx_template_message
-- ----------------------------
INSERT INTO `t_wx_template_message` VALUES ('21', '2', '4qulcoYuBBzI-apeX9AWZQ_f8RuNI-llvs5xxiLbvzI', 'SnsReceiveCommentMsg', '您发的帖子收到一条回复，赶紧去看看吧！', '点击进入查看帖子详情！并对你的好友进行回复啦！', '#e18f12', '2016-05-27 18:22:20', null);
INSERT INTO `t_wx_template_message` VALUES ('22', '1', '32H6U_RSNr47g9h7n-WQchpr6i175iVXT8fkgbx_v0E	', 'SnsReceiveCommentMsg', '您发的帖子收到一条回复，赶紧去看看吧！', '点击进入查看帖子详情！并对你的好友进行回复啦！', '#e18f12', '2016-05-27 18:37:02', null);



INSERT INTO `mcoding`.`t_base_dic_group` (`id`, `name`, `code`, `description`, `parent_id`) VALUES ('16', '微信消息类型', 'wx_msg_type', '微信消息类型', NULL);
INSERT INTO `mcoding`.`t_base_dic_group` (`id`, `name`, `code`, `description`, `parent_id`) VALUES ('17', '微信事件推送类型', 'wx_msg_event_type', '微信事件推送类型', NULL);
INSERT INTO `mcoding`.`t_base_dic_group` (`id`, `name`, `code`, `description`, `parent_id`) VALUES ('18', '消息匹配类型', 'wx_msg_match_type', '消息匹配类型', NULL);

INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('48', '16', 'wx_msg_type_text', 'text', '文本消息', '文本消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('49', '16', 'wx_msg_type_image', 'image', '图片消息', '图片消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('50', '16', 'wx_msg_type_voice', 'voice', '语音消息', '语音消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('51', '16', 'wx_msg_type_video', 'video', '视频消息', '视频消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('52', '16', 'wx_msg_type_shortvideo', 'shortvideo', '小视频消息', '小视频消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('53', '16', 'wx_msg_type_location', 'location', '地理位置消息', '地理位置消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('54', '16', 'wx_msg_type_link', 'link', '链接消息', '链接消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('55', '16', 'wx_msg_type_event', 'event', '事件推送', '事件');

INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('56', '17', 'wx_msg_event_type_subscribe', 'subscribe', '关注', '关注');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('57', '17', 'wx_msg_event_type_unsubscribe', 'unsubscribe', '取消关注', '取消关注');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('58', '17', 'wx_msg_event_type_scan', 'SCAN', '已关注，扫二维码', '扫关注二维码');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('59', '17', 'wx_msg_event_location', 'LOCATION', '上报地理位置事件', '上报地理位置事件');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('60', '17', 'wx_msg_event_type_click', 'CLICK', '点击菜单拉取消息', '点击菜单拉取消息');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('61', '17', 'wx_msg_event_type_view', 'VIEW', '点击菜单跳转链接', '点击菜单跳转链接');

INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('62', '18', 'wx_msg_match_type_all', '100', '完全匹配', '完全匹配');
INSERT INTO `mcoding`.`t_base_dic_group_item` (`id`, `group_id`, `code`, `value`, `name`, `description`) VALUES ('63', '18', 'wx_msg_match_type_regex', '200', '模糊匹配', '正则表达式匹配');

