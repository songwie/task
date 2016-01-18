-- --------------------------------------------------------
-- 主机:                           121.40.121.133
-- 服务器版本:                        5.5.40-0ubuntu0.12.04.1 - (Ubuntu)
-- 服务器操作系统:                      debian-linux-gnu
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 task 的数据库结构
CREATE DATABASE IF NOT EXISTS `task` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `task`;


-- 导出  表 task.QRTZ_BLOB_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_BLOB_TRIGGERS 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_CALENDARS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_CALENDARS 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_CRON_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_CRON_TRIGGERS 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES
	('schedulerCluster', '18', 'DEFAULT', '0/1 * * * * ?', 'Asia/Shanghai');
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_FIRED_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_FIRED_TRIGGERS 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_FIRED_TRIGGERS` (`SCHED_NAME`, `ENTRY_ID`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `INSTANCE_NAME`, `FIRED_TIME`, `PRIORITY`, `STATE`, `JOB_NAME`, `JOB_GROUP`, `IS_NONCONCURRENT`, `REQUESTS_RECOVERY`) VALUES
	('schedulerCluster', 'NON_CLUSTERED1453104664614', '18', 'DEFAULT', 'NON_CLUSTERED', 1453104715000, 5, 'EXECUTING', '18', 'DEFAULT', '0', '0'),
	('schedulerCluster', 'NON_CLUSTERED1453104664615', '18', 'DEFAULT', 'NON_CLUSTERED', 1453104716000, 5, 'ACQUIRED', NULL, NULL, '0', '0');
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_JOB_DETAILS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_JOB_DETAILS 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
	('schedulerCluster', '18', 'DEFAULT', NULL, 'com.songwie.task.task.MyTask', '0', '0', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61703FE8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D6170133F3F760A3F00025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC13F603F000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000F770800000010000000007800);
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_LOCKS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_LOCKS 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('schedulerCluster', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_PAUSED_TRIGGER_GRPS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_PAUSED_TRIGGER_GRPS 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_SCHEDULER_STATE 结构
CREATE TABLE IF NOT EXISTS `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_SCHEDULER_STATE 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;


-- 导出  表 task.QRTZ_SIMPLE_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_SIMPLE_TRIGGERS 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_SIMPROP_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_SIMPROP_TRIGGERS 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.QRTZ_TRIGGERS 结构
CREATE TABLE IF NOT EXISTS `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  task.QRTZ_TRIGGERS 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
	('schedulerCluster', '18', 'DEFAULT', '18', 'DEFAULT', NULL, 1453104716000, 1453104715000, 5, 'ACQUIRED', 'CRON', 1453104218000, 0, NULL, 0, _binary '');
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;


-- 导出  表 task.TTASK_JOB 结构
CREATE TABLE IF NOT EXISTS `TTASK_JOB` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `V_JOB_ID` varchar(100) DEFAULT '0' COMMENT '任务ID',
  `V_JOB_NAME` varchar(100) DEFAULT '0' COMMENT '任务名称',
  `N_JOB_STATUS` int(1) DEFAULT '1' COMMENT '任务状态（0禁用 1启用 2删除）',
  `V_CRON_EXPRE` varchar(51) DEFAULT '1' COMMENT '执行计划',
  `V_JOB_DESC` varchar(200) DEFAULT '0' COMMENT '任务描述',
  `D_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建日期',
  `V_OPER_USER` varchar(20) DEFAULT '0' COMMENT '创建人',
  `N_POST_TYPE` int(1) DEFAULT '1' COMMENT '提交类型（1POST,2GET）',
  `V_JOB_URL` varchar(500) DEFAULT '0' COMMENT '参数URL',
  `V_JOB_PARAMS` varchar(200) DEFAULT '0' COMMENT '调用参数',
  `V_RESULT` text COMMENT '调用结果',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 正在导出表  task.TTASK_JOB 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `TTASK_JOB` DISABLE KEYS */;
INSERT INTO `TTASK_JOB` (`ID`, `V_JOB_ID`, `V_JOB_NAME`, `N_JOB_STATUS`, `V_CRON_EXPRE`, `V_JOB_DESC`, `D_CREATE_TIME`, `V_OPER_USER`, `N_POST_TYPE`, `V_JOB_URL`, `V_JOB_PARAMS`, `V_RESULT`) VALUES
	(18, NULL, '测试', 1, '0/5 * * * * ?', '测试', '2016-01-18 16:03:38', '10000', 1, 'https://www.baidu.com/', '', NULL);
/*!40000 ALTER TABLE `TTASK_JOB` ENABLE KEYS */;


-- 导出  表 task.TTASK_JOB_RESULT 结构
CREATE TABLE IF NOT EXISTS `TTASK_JOB_RESULT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `V_JOB_ID` varchar(100) DEFAULT '0' COMMENT '任务ID',
  `V_JOB_NAME` varchar(100) DEFAULT '0' COMMENT '任务名称',
  `D_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建日期',
  `V_RESULT` longtext COMMENT '调用结果',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;

-- 正在导出表  task.TTASK_JOB_RESULT 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `TTASK_JOB_RESULT` DISABLE KEYS */;
INSERT INTO `TTASK_JOB_RESULT` (`ID`, `V_JOB_ID`, `V_JOB_NAME`, `D_CREATE_TIME`, `V_RESULT`) VALUES
	(47, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(48, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(49, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(50, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(51, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(52, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(53, '18', '测试', '2016-01-18 16:04:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(54, '18', '测试', '2016-01-18 16:04:35', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(55, '18', '测试', '2016-01-18 16:04:35', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(56, '18', '测试', '2016-01-18 16:04:35', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(57, '18', '测试', '2016-01-18 16:04:35', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(58, '18', '测试', '2016-01-18 16:04:36', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(59, '18', '测试', '2016-01-18 16:04:40', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(60, '18', '测试', '2016-01-18 16:04:45', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(61, '18', '测试', '2016-01-18 16:04:50', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(62, '18', '测试', '2016-01-18 16:04:55', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(63, '18', '测试', '2016-01-18 16:05:00', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(64, '18', '测试', '2016-01-18 16:05:05', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(65, '18', '测试', '2016-01-18 16:05:08', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(66, '18', '测试', '2016-01-18 16:05:10', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(67, '18', '测试', '2016-01-18 16:05:15', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(68, '18', '测试', '2016-01-18 16:05:56', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(69, '18', '测试', '2016-01-18 16:05:56', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(70, '18', '测试', '2016-01-18 16:05:57', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(71, '18', '测试', '2016-01-18 16:05:58', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(72, '18', '测试', '2016-01-18 16:05:59', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(73, '18', '测试', '2016-01-18 16:06:00', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(74, '18', '测试', '2016-01-18 16:06:01', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(75, '18', '测试', '2016-01-18 16:06:02', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(76, '18', '测试', '2016-01-18 16:06:03', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(77, '18', '测试', '2016-01-18 16:06:04', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(78, '18', '测试', '2016-01-18 16:06:05', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(79, '18', '测试', '2016-01-18 16:06:06', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(80, '18', '测试', '2016-01-18 16:06:07', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(81, '18', '测试', '2016-01-18 16:06:08', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(82, '18', '测试', '2016-01-18 16:06:09', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(83, '18', '测试', '2016-01-18 16:06:10', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(84, '18', '测试', '2016-01-18 16:06:11', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(85, '18', '测试', '2016-01-18 16:06:12', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(86, '18', '测试', '2016-01-18 16:06:13', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(87, '18', '测试', '2016-01-18 16:06:14', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(88, '18', '测试', '2016-01-18 16:06:15', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(89, '18', '测试', '2016-01-18 16:06:16', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(90, '18', '测试', '2016-01-18 16:06:17', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(91, '18', '测试', '2016-01-18 16:06:18', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(92, '18', '测试', '2016-01-18 16:06:19', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(93, '18', '测试', '2016-01-18 16:06:20', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(94, '18', '测试', '2016-01-18 16:11:07', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(95, '18', '测试', '2016-01-18 16:11:07', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(96, '18', '测试', '2016-01-18 16:11:08', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(97, '18', '测试', '2016-01-18 16:11:09', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(98, '18', '测试', '2016-01-18 16:11:10', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(99, '18', '测试', '2016-01-18 16:11:11', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(100, '18', '测试', '2016-01-18 16:11:12', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(101, '18', '测试', '2016-01-18 16:11:13', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(102, '18', '测试', '2016-01-18 16:11:14', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(103, '18', '测试', '2016-01-18 16:11:15', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(104, '18', '测试', '2016-01-18 16:11:16', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(105, '18', '测试', '2016-01-18 16:11:17', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(106, '18', '测试', '2016-01-18 16:11:18', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(107, '18', '测试', '2016-01-18 16:11:19', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(108, '18', '测试', '2016-01-18 16:11:20', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(109, '18', '测试', '2016-01-18 16:11:21', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(110, '18', '测试', '2016-01-18 16:11:22', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(111, '18', '测试', '2016-01-18 16:11:23', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(112, '18', '测试', '2016-01-18 16:11:24', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(113, '18', '测试', '2016-01-18 16:11:25', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(114, '18', '测试', '2016-01-18 16:11:26', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(115, '18', '测试', '2016-01-18 16:11:27', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(116, '18', '测试', '2016-01-18 16:11:28', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(117, '18', '测试', '2016-01-18 16:11:29', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(118, '18', '测试', '2016-01-18 16:11:30', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(119, '18', '测试', '2016-01-18 16:11:31', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(120, '18', '测试', '2016-01-18 16:11:32', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(121, '18', '测试', '2016-01-18 16:11:33', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(122, '18', '测试', '2016-01-18 16:11:34', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(123, '18', '测试', '2016-01-18 16:11:35', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(124, '18', '测试', '2016-01-18 16:11:36', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(125, '18', '测试', '2016-01-18 16:11:37', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(126, '18', '测试', '2016-01-18 16:11:38', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(127, '18', '测试', '2016-01-18 16:11:39', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(128, '18', '测试', '2016-01-18 16:11:40', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(129, '18', '测试', '2016-01-18 16:11:41', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(130, '18', '测试', '2016-01-18 16:11:42', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(131, '18', '测试', '2016-01-18 16:11:43', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(132, '18', '测试', '2016-01-18 16:11:44', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(133, '18', '测试', '2016-01-18 16:11:45', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(134, '18', '测试', '2016-01-18 16:11:46', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(135, '18', '测试', '2016-01-18 16:11:47', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(136, '18', '测试', '2016-01-18 16:11:48', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(137, '18', '测试', '2016-01-18 16:11:49', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(138, '18', '测试', '2016-01-18 16:11:50', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(139, '18', '测试', '2016-01-18 16:11:51', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(140, '18', '测试', '2016-01-18 16:11:52', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(141, '18', '测试', '2016-01-18 16:11:53', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(142, '18', '测试', '2016-01-18 16:11:54', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n'),
	(143, '18', '测试', '2016-01-18 16:11:55', '<html>\r\n<head><title>302 Found</title></head>\r\n<body bgcolor="white">\r\n<center><h1>302 Found</h1></center>\r\n<hr><center>pr-nginx_1-0-257_BRANCH Branch\nTime : Tue Jan  5 14:24:59 CST 2016</center>\r\n</body>\r\n</html>\r\n');
/*!40000 ALTER TABLE `TTASK_JOB_RESULT` ENABLE KEYS */;


-- 导出  表 task.TTASK_USER 结构
CREATE TABLE IF NOT EXISTS `TTASK_USER` (
  `OPER_NO` int(11) NOT NULL AUTO_INCREMENT,
  `OPER_TYPE` varchar(10) DEFAULT NULL,
  `OPER_USER_NAME` varchar(20) DEFAULT NULL,
  `OPER_PASSWORD` varchar(20) DEFAULT NULL,
  `OPER_BRANCH_ID` int(11) DEFAULT NULL,
  `OPER_REAL_NAME` varchar(20) DEFAULT NULL,
  `OPER_PHONE` varchar(15) DEFAULT NULL,
  `OPER_EMAIL` varchar(50) DEFAULT NULL,
  `OPER_ID_NO` varchar(20) DEFAULT NULL,
  `OPER_ROLE` varchar(10) DEFAULT NULL,
  `OPER_STATUS` varchar(10) DEFAULT NULL,
  `OPER_START_DATE` date DEFAULT NULL,
  `OPER_END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`OPER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;

-- 正在导出表  task.TTASK_USER 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `TTASK_USER` DISABLE KEYS */;
INSERT INTO `TTASK_USER` (`OPER_NO`, `OPER_TYPE`, `OPER_USER_NAME`, `OPER_PASSWORD`, `OPER_BRANCH_ID`, `OPER_REAL_NAME`, `OPER_PHONE`, `OPER_EMAIL`, `OPER_ID_NO`, `OPER_ROLE`, `OPER_STATUS`, `OPER_START_DATE`, `OPER_END_DATE`) VALUES
	(10000, '1', 'admin', 'admin', 10000, '10000', '10000', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `TTASK_USER` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
