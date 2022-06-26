-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (arm64)
--
-- Host: 127.0.0.1    Database: scsp
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_announce`
--

DROP TABLE IF EXISTS `tb_announce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_announce` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '公告标题',
  `detail` varchar(2000) DEFAULT NULL COMMENT '详情',
  `signature` varchar(40) DEFAULT NULL COMMENT '署名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_announce`
--

LOCK TABLES `tb_announce` WRITE;
/*!40000 ALTER TABLE `tb_announce` DISABLE KEYS */;
INSERT INTO `tb_announce` VALUES (1,'公告标题222','公告详情','公告署名','2022-06-25 15:52:28','2022-06-25 15:58:06',1),(2,'公告标题','公告详情1','公告署名','2022-06-25 16:17:03','2022-06-25 16:17:03',1),(3,'公告标2题','公告详情','公告署名','2022-06-25 16:17:07','2022-06-25 23:58:52',0),(4,'公告标题','公告详情3','公告署名','2022-06-25 16:17:09','2022-06-25 16:17:09',0),(5,'公告标题','公告详情4','公告署名','2022-06-25 16:17:12','2022-06-25 16:17:12',0),(6,'公告标题','公告详情5','公告署名','2022-06-25 16:17:15','2022-06-25 16:17:15',0),(7,'公告标题','公告详情5','公告署名','2022-06-25 16:56:57','2022-06-25 16:56:57',0),(8,'公告标题','公告详情','公告署名','2022-06-25 23:57:26','2022-06-25 23:57:26',0);
/*!40000 ALTER TABLE `tb_announce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_collage`
--

DROP TABLE IF EXISTS `tb_collage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_collage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '学院名',
  `detail` varchar(100) DEFAULT NULL COMMENT '描述',
  `manager_name` varchar(50) DEFAULT NULL COMMENT '管理人名称',
  `people_count` int DEFAULT NULL COMMENT '学院人数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_collage`
--

LOCK TABLES `tb_collage` WRITE;
/*!40000 ALTER TABLE `tb_collage` DISABLE KEYS */;
INSERT INTO `tb_collage` VALUES (1,'计算机学院','计算机学院哦','某某某',500,'2022-06-26 09:23:26','2022-06-26 09:23:28',0);
/*!40000 ALTER TABLE `tb_collage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) DEFAULT NULL COMMENT '课程名',
  `credit` float(3,1) DEFAULT NULL COMMENT '学分',
  `detail` varchar(100) DEFAULT NULL COMMENT '课程详情',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` int DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course`
--

LOCK TABLES `tb_course` WRITE;
/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
INSERT INTO `tb_course` VALUES (1,'计算机系统结构',3.0,'计算机系统结构AA','2022-06-26 11:34:28','2022-06-26 11:34:30',0),(2,'计算机网络',3.0,'奥迪','2022-06-26 12:46:15','2022-06-26 12:46:17',0),(3,'操作系统',3.0,'奥迪','2022-06-26 12:46:59','2022-06-26 12:47:01',0),(4,'音乐鉴赏',2.0,'大','2022-06-26 12:47:25','2022-06-26 12:47:27',0),(5,'计算机组成原理',3.0,' 大','2022-06-26 12:48:00','2022-06-26 12:48:01',0),(6,'java',3.0,'大','2022-06-26 12:48:14','2022-06-26 12:48:16',0);
/*!40000 ALTER TABLE `tb_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course_course_time`
--

DROP TABLE IF EXISTS `tb_course_course_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course_course_time` (
  `course_id` int DEFAULT NULL,
  `course_time_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course_course_time`
--

LOCK TABLES `tb_course_course_time` WRITE;
/*!40000 ALTER TABLE `tb_course_course_time` DISABLE KEYS */;
INSERT INTO `tb_course_course_time` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,2),(4,1),(4,2),(4,3),(4,4),(5,5),(5,6),(5,7),(6,8),(6,9);
/*!40000 ALTER TABLE `tb_course_course_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course_teacher`
--

DROP TABLE IF EXISTS `tb_course_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course_teacher` (
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `teacher_id` int DEFAULT NULL COMMENT '教师用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course_teacher`
--

LOCK TABLES `tb_course_teacher` WRITE;
/*!40000 ALTER TABLE `tb_course_teacher` DISABLE KEYS */;
INSERT INTO `tb_course_teacher` VALUES (1,1012),(2,1012),(3,1012),(4,1012),(5,1012),(6,1012),(6,1013),(5,1013);
/*!40000 ALTER TABLE `tb_course_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course_time`
--

DROP TABLE IF EXISTS `tb_course_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course_time` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程时间对应的序号',
  `day` int DEFAULT NULL COMMENT '课程是星期几',
  `start_time` time DEFAULT NULL COMMENT '开始时间',
  `end_time` time DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course_time`
--

LOCK TABLES `tb_course_time` WRITE;
/*!40000 ALTER TABLE `tb_course_time` DISABLE KEYS */;
INSERT INTO `tb_course_time` VALUES (1,1,'08:15:00','09:00:00'),(2,1,'09:10:00','09:55:00'),(3,1,'10:15:00','11:00:00'),(4,1,'11:10:00','11:55:00'),(5,1,'13:50:00','14:35:00'),(6,1,'14:45:00','15:30:00'),(7,1,'15:40:00','16:25:00'),(8,1,'16:45:00','17:30:00'),(9,1,'17:40:00','18:25:00'),(10,1,'19:20:00','20:05:00'),(11,1,'20:15:00','21:00:00'),(12,1,'21:10:00','21:55:00'),(13,2,'08:15:00','09:00:00'),(14,2,'09:10:00','09:55:00'),(15,2,'10:15:00','11:00:00'),(16,2,'11:10:00','11:55:00'),(17,2,'13:50:00','14:35:00'),(18,2,'14:45:00','15:30:00'),(19,2,'15:40:00','16:25:00'),(20,2,'16:45:00','17:30:00'),(21,2,'17:40:00','18:25:00'),(22,2,'19:20:00','20:05:00'),(23,2,'20:15:00','21:00:00'),(24,2,'21:10:00','21:55:00'),(25,3,'08:15:00','09:00:00'),(26,3,'09:10:00','09:55:00'),(27,3,'10:15:00','11:00:00'),(28,3,'11:10:00','11:55:00'),(29,3,'13:50:00','14:35:00'),(30,3,'14:45:00','15:30:00'),(31,3,'15:40:00','16:25:00'),(32,3,'16:45:00','17:30:00'),(33,3,'17:40:00','18:25:00'),(34,3,'19:20:00','20:05:00'),(35,3,'20:15:00','21:00:00'),(36,3,'21:10:00','21:55:00'),(37,4,'08:15:00','09:00:00'),(38,4,'09:10:00','09:55:00'),(39,4,'10:15:00','11:00:00'),(40,4,'11:10:00','11:55:00'),(41,4,'13:50:00','14:35:00'),(42,4,'14:45:00','15:30:00'),(43,4,'15:40:00','16:25:00'),(44,4,'16:45:00','17:30:00'),(45,4,'17:40:00','18:25:00'),(46,4,'19:20:00','20:05:00'),(47,4,'20:15:00','21:00:00'),(48,4,'21:10:00','21:55:00'),(49,5,'08:15:00','09:00:00'),(50,5,'09:10:00','09:55:00'),(51,5,'10:15:00','11:00:00'),(52,5,'11:10:00','11:55:00'),(53,5,'13:50:00','14:35:00'),(54,5,'14:45:00','15:30:00'),(55,5,'15:40:00','16:25:00'),(56,5,'16:45:00','17:30:00'),(57,5,'17:40:00','18:25:00'),(58,5,'19:20:00','20:05:00'),(59,5,'20:15:00','21:00:00'),(60,5,'21:10:00','21:55:00'),(61,6,'08:15:00','09:00:00'),(62,6,'09:10:00','09:55:00'),(63,6,'10:15:00','11:00:00'),(64,6,'11:10:00','11:55:00'),(65,6,'13:50:00','14:35:00'),(66,6,'14:45:00','15:30:00'),(67,6,'15:40:00','16:25:00'),(68,6,'16:45:00','17:30:00'),(69,6,'17:40:00','18:25:00'),(70,6,'19:20:00','20:05:00'),(71,6,'20:15:00','21:00:00'),(72,6,'21:10:00','21:55:00'),(73,7,'08:15:00','09:00:00'),(74,7,'09:10:00','09:55:00'),(75,7,'10:15:00','11:00:00'),(76,7,'11:10:00','11:55:00'),(77,7,'13:50:00','14:35:00'),(78,7,'14:45:00','15:30:00'),(79,7,'15:40:00','16:25:00'),(80,7,'16:45:00','17:30:00'),(81,7,'17:40:00','18:25:00'),(82,7,'19:20:00','20:05:00'),(83,7,'20:15:00','21:00:00'),(84,7,'21:10:00','21:55:00');
/*!40000 ALTER TABLE `tb_course_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_major`
--

DROP TABLE IF EXISTS `tb_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `college_id` int DEFAULT NULL COMMENT '所属学院id',
  `name` varchar(20) DEFAULT NULL COMMENT '专业名',
  `detail` varchar(100) DEFAULT NULL COMMENT '描述',
  `manager_name` varchar(50) DEFAULT NULL COMMENT '管理人名称',
  `people_count` int DEFAULT NULL COMMENT '专业人数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_major`
--

LOCK TABLES `tb_major` WRITE;
/*!40000 ALTER TABLE `tb_major` DISABLE KEYS */;
INSERT INTO `tb_major` VALUES (1,1,'计算机科学与技术','卡瓦鲁多好啦我会抽不出我的腰挎发i','码力',200,'2022-06-26 09:25:46','2022-06-26 09:25:48',0),(2,1,'人工智能','外镀哈我iu一般粗跟v哇','麻麻力',100,'2022-06-26 09:26:44','2022-06-26 09:26:46',0),(3,1,'物联网','卡u和我对比u awhile','码尼',100,'2022-06-26 09:27:31','2022-06-26 09:27:33',0);
/*!40000 ALTER TABLE `tb_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_news`
--

DROP TABLE IF EXISTS `tb_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '公告标题',
  `detail` varchar(2000) DEFAULT NULL COMMENT '详情',
  `signature` varchar(40) DEFAULT NULL COMMENT '署名',
  `see_count` int DEFAULT NULL COMMENT '0',
  `college_id` int DEFAULT NULL COMMENT '所属学院id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news` VALUES (10,'news','detail','shuming',0,123,'2022-06-25 22:35:16','2022-06-25 22:35:16',1),(11,'工要与机意称','in labore','do non enim',99,59,'2022-06-25 22:35:20','2022-06-26 18:40:01',0),(12,'news','detail','shuming',200,123,'2022-06-25 22:35:23','2022-06-26 18:40:01',0),(13,'今放度重法队','in sed sit fugiat deserunt','magna ad laborum',50,42,'2022-06-25 22:35:27','2022-06-26 18:40:01',0),(14,'123',NULL,NULL,12,NULL,NULL,NULL,0),(15,'123',NULL,NULL,12,NULL,NULL,NULL,1),(16,'22',NULL,NULL,3,NULL,NULL,'2022-06-26 18:40:01',0),(17,'33',NULL,NULL,7,NULL,NULL,NULL,0),(18,'44',NULL,NULL,34,NULL,NULL,NULL,0),(19,'55',NULL,NULL,4,NULL,NULL,NULL,0),(20,'news','detail','shuming',0,123,'2022-06-25 23:59:18','2022-06-25 23:59:18',0);
/*!40000 ALTER TABLE `tb_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `detail` varchar(100) DEFAULT NULL COMMENT '角色详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'学生','学校的学生'),(2,'老师','学校的老师');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_number` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学号',
  `password` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `true_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `ebg` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学历',
  `icon` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `major_id` int DEFAULT NULL COMMENT '专业id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_user_id_number_uindex` (`id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1011,'123123','123456','123123','本科',NULL,1,'2022-06-25 04:57:22','2022-06-26 01:43:09',0),(1012,'123456','123456','刘俊','博士',NULL,NULL,'2022-06-26 05:18:24','2022-06-26 05:18:26',0),(1013,'t123123123','123456','泥运足','博士',NULL,NULL,'2022-06-26 05:46:42','2022-06-26 05:46:44',0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_course_record`
--

DROP TABLE IF EXISTS `tb_user_course_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_course_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(30) DEFAULT NULL COMMENT ' 课程名称',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `described` varchar(10) DEFAULT 'yyyy年春季学期' COMMENT '选课描述',
  `score` int DEFAULT '0' COMMENT '成绩',
  `count` int DEFAULT '1' COMMENT '第几次选该课程',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_courseId_score` (`course_id`,`score`),
  KEY `idx_userId_courseId` (`user_id`,`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_course_record`
--

LOCK TABLES `tb_user_course_record` WRITE;
/*!40000 ALTER TABLE `tb_user_course_record` DISABLE KEYS */;
INSERT INTO `tb_user_course_record` VALUES (1,1,'计算机系统结构',1011,'2022年春季学期',60,2,'2022-06-26 11:51:29','2022-06-26 11:51:30',0),(2,2,'计算机网络',1011,'2022年春季学期',70,2,'2022-06-26 15:24:16','2022-06-26 15:24:18',0),(3,3,'操作系统',1011,'2022年春季学期',70,2,'2022-06-26 15:35:00','2022-06-26 15:35:05',0),(4,4,'音乐鉴赏',1011,'2022年春季学期',70,2,'2022-06-26 15:35:01','2022-06-26 15:35:07',0),(5,5,'计算机组成原理',1011,'2022年春季学期',70,2,'2022-06-26 15:35:02','2022-06-26 15:35:10',0),(6,6,'java',1011,'2022年春季学期',70,2,'2022-06-26 15:35:04','2022-06-26 15:35:09',0),(7,1,'计算机系统结构',1011,'2021年春季学期',0,1,'2022-06-26 11:51:29','2022-06-26 11:51:29',0),(8,2,'计算机网络',1011,'2021年春季学期',0,1,'2022-06-26 15:24:16','2022-06-26 15:24:16',0),(9,3,'操作系统',1011,'2021年春季学期',0,1,'2022-06-26 15:35:00','2022-06-26 15:35:00',0),(10,4,'音乐鉴赏',1011,'2021年春季学期',0,1,'2022-06-26 15:35:01','2022-06-26 15:35:01',0),(11,5,'计算机组成原理',1011,'2021年春季学期',0,1,'2022-06-26 15:35:02','2022-06-26 15:35:02',0),(12,6,'java',1011,'2021年春季学期',0,1,'2022-06-26 15:35:04','2022-06-26 15:35:04',0),(13,1,'计算机系统结构',1011,'2023年春季学期',91,3,'2022-06-26 15:35:04','2022-06-26 15:35:04',0),(14,2,'计算机网络',1011,'2023年春季学期',92,3,'2022-06-26 11:51:29','2022-06-26 11:51:29',0),(15,3,'操作系统',1011,'2023年春季学期',93,3,'2022-06-26 15:24:16','2022-06-26 15:24:16',0),(16,4,'音乐鉴赏',1011,'2023年春季学期',94,3,'2022-06-26 15:35:00','2022-06-26 15:35:00',0),(17,5,'计算机组成原理',1011,'2023年春季学期',95,3,'2022-06-26 15:35:01','2022-06-26 15:35:01',0),(18,6,'java',1011,'2023年春季学期',96,3,'2022-06-26 15:35:02','2022-06-26 15:35:02',0);
/*!40000 ALTER TABLE `tb_user_course_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_info`
--

DROP TABLE IF EXISTS `tb_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_info` (
  `user_id` int NOT NULL,
  `china_id` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `home_address` varchar(100) DEFAULT NULL COMMENT '家庭住址',
  `dormitory` varchar(100) DEFAULT NULL COMMENT '宿舍地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_info`
--

LOCK TABLES `tb_user_info` WRITE;
/*!40000 ALTER TABLE `tb_user_info` DISABLE KEYS */;
INSERT INTO `tb_user_info` VALUES (1011,'522227200009210924','914577981@qq.com','19141273392','中国四川成都','江安校区西园6舍5单元102b','2022-06-26 09:21:44','2022-06-26 09:21:46',0);
/*!40000 ALTER TABLE `tb_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_major`
--

DROP TABLE IF EXISTS `tb_user_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `major_id` int DEFAULT NULL COMMENT '专业id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_major`
--

LOCK TABLES `tb_user_major` WRITE;
/*!40000 ALTER TABLE `tb_user_major` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_role` (
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(20) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-26 19:08:42
