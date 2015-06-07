CREATE DATABASE IF NOT EXISTS digitalmall 
DEFAULT CHARACTER SET 'utf8';
USE digitalmall;


-- ----------------------------
-- Table structure for 用户表
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(18) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(18) NOT NULL COMMENT '登录名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '移动电话',
  `nick` varchar(18) NOT NULL COMMENT '会员名',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`,`email`,`mobile`,`nick`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


 
