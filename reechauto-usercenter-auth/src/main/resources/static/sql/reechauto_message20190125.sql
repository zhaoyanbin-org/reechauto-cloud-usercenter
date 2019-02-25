/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19-log : Database - reechauto_auth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reechauto_auth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `reechauto_auth`;

/*Table structure for table `client_details` */

DROP TABLE IF EXISTS `client_details`;

CREATE TABLE `client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(1024) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(4096) DEFAULT NULL,
  `authorized_grant_types` varchar(512) DEFAULT NULL,
  `web_server_redirect_uri` varchar(1024) DEFAULT NULL,
  `authorities` varchar(512) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `client_details` */

insert  into `client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('reechauto','','$2a$10$1gJV4NryaTAeVdgtSd78mudQcpwkqZQEZJ5CiQFuuQwC2mA3G4xMS','abc,aab','authorization_code,password,refresh_token,implicit,client_credentials','http://www.baidu.com','ROLE_ADMIN',7200,72000,NULL,NULL),('reechauto_dep','reechauto_app,reechauto_auth','$2a$10$G9QG1ag6/97j6B9Eih/yeu6O.BJxu00zGmKpBG6KfRlhH8EU5MBLS','all','authorization_code,password,refresh_token,implicit,client_credentials','http://www.baidu.com','ROLE_ADMIN,ROLE_USER',7200,72000,NULL,NULL);

/*Table structure for table `resource_scope` */

DROP TABLE IF EXISTS `resource_scope`;

CREATE TABLE `resource_scope` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scope` varchar(20) NOT NULL COMMENT '范围ID',
  `scope_tips` varchar(256) DEFAULT NULL COMMENT '范围说明',
  `resource_id` varchar(32) NOT NULL COMMENT '所属微服务ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `resource_scope` */

/*Table structure for table `resource_server` */

DROP TABLE IF EXISTS `resource_server`;

CREATE TABLE `resource_server` (
  `resource_id` varchar(32) NOT NULL COMMENT '微服务ID',
  `resource_name` varchar(256) NOT NULL COMMENT '微服务名称',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `resource_server` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单(1.菜单。2.按钮)',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` varchar(2) DEFAULT 'Y' COMMENT 'Y/N',
  `icon` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`code`,`p_id`,`p_code`,`name`,`url`,`is_menu`,`level`,`sort`,`status`,`icon`) values (1,'1',0,'0','1111','111',1,1,1,'Y',NULL);

/*Table structure for table `sys_privilege` */

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `role_id` varchar(20) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_privilege` */

insert  into `sys_privilege`(`role_id`,`menu_id`,`create_time`,`create_by`) values ('ROLE_ADMIN',1,'2019-01-23 10:56:57',NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` varchar(20) NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '说明',
  `status` varchar(2) NOT NULL DEFAULT 'Y' COMMENT 'Y/N',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `unique_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`tips`,`status`,`create_time`,`create_by`,`update_time`,`update_by`) values ('ROLE_ADMIN','admin','1111','Y','2019-01-23 10:56:17',112233,NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` varchar(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` bigint(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`,`create_time`,`create_by`,`update_time`,`update_by`) values (112233,'ROLE_ADMIN','2019-01-23 10:55:50',112233,NULL,NULL);

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `account_num` varchar(64) NOT NULL COMMENT '帐号',
  `account_type` varchar(20) NOT NULL DEFAULT 'mobile' COMMENT 'normal普通帐号,mobile:手机,email:邮箱，idcard:身份证号',
  `account_from` varchar(64) NOT NULL DEFAULT 'system' COMMENT '帐号来源，默认系统',
  `pid` varchar(128) NOT NULL DEFAULT '0' COMMENT '默认为0',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_account` */

insert  into `user_account`(`account_num`,`account_type`,`account_from`,`pid`,`user_id`,`create_time`) values ('18240349328','mobile','system','0',112233,'2019-01-14 09:44:24'),('zhaoyanbin2003@163.com','email','system','0',112233,'2019-01-14 09:45:08'),('zhaoyb','account','system','0',112233,'2019-01-14 09:44:49');

/*Table structure for table `user_details` */

DROP TABLE IF EXISTS `user_details`;

CREATE TABLE `user_details` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `password` varchar(256) NOT NULL,
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实名称',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `sex` varchar(10) DEFAULT '保密' COMMENT '(男/女/保密)',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `img_url` varchar(2000) DEFAULT NULL COMMENT '头像url',
  `city` varchar(200) DEFAULT NULL COMMENT '城市',
  `user_status` varchar(20) NOT NULL DEFAULT 'OK' COMMENT '用户状态：OK,LOCK',
  `is_del` varchar(1) DEFAULT 'N' COMMENT 'Y/N(已删除/未删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_details` */

insert  into `user_details`(`user_id`,`password`,`real_name`,`nick_name`,`sex`,`birthday`,`img_url`,`city`,`user_status`,`is_del`,`create_time`,`modify_time`) values (112233,'$2a$10$Bwm//xvDJqgpwpqDc2hYHOqFAHIHiOR0vAzmOXNWBsxWA0F7MMZfO','赵彦彬','彦彬大哥','保密','2019-01-14 09:43:52',NULL,'北京','OK','N','2019-01-14 09:44:05','2019-01-14 09:44:05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
