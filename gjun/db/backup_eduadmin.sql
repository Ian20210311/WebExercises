-- MySQL dump 10.13  Distrib 5.6.51, for Win64 (x86_64)
--
-- Host: localhost    Database: eduadmin
-- ------------------------------------------------------
-- Server version	5.6.51-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `eduadmin`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `eduadmin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `eduadmin`;

--
-- Table structure for table `daily_chk`
--

DROP TABLE IF EXISTS `daily_chk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_chk` (
  `daily_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水號，PK值',
  `student_id` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '學生ID，reference stdent_info',
  `chk_in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '到校時間',
  PRIMARY KEY (`daily_no`),
  UNIQUE KEY `daily_no_UNIQUE` (`daily_no`),
  KEY `student_id_fk_student_id_idx` (`student_id`),
  CONSTRAINT `student_info_FK_daily_chk` FOREIGN KEY (`student_id`) REFERENCES `student_info` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每日上學紀錄';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_chk`
--

LOCK TABLES `daily_chk` WRITE;
/*!40000 ALTER TABLE `daily_chk` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_chk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_info`
--

DROP TABLE IF EXISTS `leave_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_info` (
  `leave_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '請假流水號, PK值',
  `student_id` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '學生身分證',
  `type_no` int(11) NOT NULL DEFAULT '0' COMMENT '請假類別',
  `reason` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '理由',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '請假時間',
  PRIMARY KEY (`leave_no`),
  UNIQUE KEY `leave_no_UNIQUE` (`leave_no`),
  KEY `student_info_FK_leave_info_idx` (`student_id`),
  KEY `leave_type_FK_leave_info_idx` (`type_no`),
  CONSTRAINT `leave_type_FK_leave_info` FOREIGN KEY (`type_no`) REFERENCES `leave_type` (`type_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_info_FK_leave_info` FOREIGN KEY (`student_id`) REFERENCES `student_info` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_info`
--

LOCK TABLES `leave_info` WRITE;
/*!40000 ALTER TABLE `leave_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_type`
--

DROP TABLE IF EXISTS `leave_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_type` (
  `type_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '請假總類，PK值\n',
  `typename` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '婚假',
  `leave_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '給假原則',
  PRIMARY KEY (`type_no`),
  UNIQUE KEY `type_no_UNIQUE` (`type_no`),
  UNIQUE KEY `typename_UNIQUE` (`typename`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_type`
--

LOCK TABLES `leave_type` WRITE;
/*!40000 ALTER TABLE `leave_type` DISABLE KEYS */;
INSERT INTO `leave_type` VALUES (1,'婚假','8日'),(2,'喪假','父母、養父母、繼父母、配偶喪亡者，喪假8日'),(3,'普通傷病假','未住院者，1年內合計不得超過30日'),(4,'公傷病假','因職業災害而致失能、傷害或疾病者，其治療、休養期間，給予公傷病假'),(5,'事假','1年內合計不得超過14日'),(6,'公假','依法令規定應給予公假'),(7,'QQQ','測試');
/*!40000 ALTER TABLE `leave_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schdule_line`
--

DROP TABLE IF EXISTS `schdule_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schdule_line` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'pk ',
  `message` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'message to be send',
  `setting_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'schedule time',
  `is_sent` varchar(1) COLLATE utf8_bin NOT NULL DEFAULT 'n' COMMENT '寄送 flag',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  PRIMARY KEY (`msg_id`),
  UNIQUE KEY `msg_id_UNIQUE` (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='line message 排程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schdule_line`
--

LOCK TABLES `schdule_line` WRITE;
/*!40000 ALTER TABLE `schdule_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `schdule_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_info` (
  `id_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `student_id` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '學生身分證',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '學生姓名',
  `county` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '縣市',
  `city` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '鄉鎮[市]區',
  `zip` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '郵遞區號',
  `road` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '地址',
  `parent_mail` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '家長email address',
  `pwdHash` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'hash 密碼',
  `pwdSeed` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'seed值(1-10)',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '註冊時間',
  `pwd_hash` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pwd_seed` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_no`),
  UNIQUE KEY `id_no_UNIQUE` (`id_no`),
  UNIQUE KEY `student_id_UNIQUE` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_info`
--

LOCK TABLES `student_info` WRITE;
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
INSERT INTO `student_info` VALUES (1,'A123456789','john','大安區','台北市','23154','賓夕法尼亞大道1600號','freddyruan@hotmail.com','51509442','5','2021-03-23 11:43:31',NULL,NULL),(2,'B123456789','Mary','大安區','台北市','23154','賓夕法尼亞大道1600號','freddyruan@hotmail.com','41509442','4','2021-03-23 12:36:34',NULL,NULL),(3,'C123456789','Mary','大安區','台北市','23154','賓夕法尼亞大道1600號','freddyruan@hotmail.com','71509442','7','2021-03-26 09:33:08',NULL,NULL),(4,'D123456789','Mary','大安區','台北市','23154','賓夕法尼亞大道1600號','freddyruan@hotmail.com','31509442','3','2021-03-26 09:49:17',NULL,NULL),(5,'E123456789','Mary','大安區','台北市','23154','賓夕法尼亞大道1600號','freddyruan@hotmail.com','11509442','1','2021-03-26 10:31:00',NULL,NULL);
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-29 21:32:59
