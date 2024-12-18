-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: article
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `author_id` int NOT NULL COMMENT '作者ID',
  `context` text COMMENT '文章内容',
  `channel_id` int NOT NULL COMMENT '频道ID',
  `status` int DEFAULT '0' COMMENT '文章状态（例如：0-草稿，1-发布）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cover_url` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (5,'dwadawd',1,'<p>dwadaw</p>',2,0,'2024-12-11 22:58:24','2024-12-11 22:58:24','http://localhost:8080/file/download/-4699249126497176431.png'),(6,'dwada',1,'<p>dwad</p>',2,0,'2024-12-11 23:04:03','2024-12-11 23:04:03','http://localhost:8080/file/download/-7338540266450060279.png'),(7,'fesfesf',1,'<p>fesfsef</p>',4,0,'2024-12-11 23:14:10','2024-12-11 23:14:10','http://localhost:8080/file/download/-8908633638885693841.png'),(8,'fesfse',1,'<p>vesfsf</p>',2,0,'2024-12-11 23:16:47','2024-12-11 23:16:47','http://localhost:8080/file/download/-5929661060942634302.jpg'),(9,'fsefs',1,'<p>fsfsf</p>',3,0,'2024-12-12 11:05:44','2024-12-12 11:05:44','http://localhost:8080/file/download/-5911459779574408735.jpg'),(10,'sgdgdg',1,'<p>grsgsdgs</p>',3,0,'2024-12-12 11:06:16','2024-12-12 11:06:16','http://localhost:8080/file/download/-7532708888702831370.png'),(11,'dadwd',1,'<p>awdawd</p>',2,0,'2024-12-12 15:32:40','2024-12-12 15:32:40','http://localhost:8080/file/download/-7764193976064255001.jpg'),(12,'feafe',1,'<p>efaefae</p>',2,0,'2024-12-12 15:47:59','2024-12-12 15:47:59','http://localhost:8080/file/download/-8934497631803135412.png'),(13,'efaefae',1,'<p>efaefa</p>',4,0,'2024-12-12 15:48:06','2024-12-12 15:48:06','http://localhost:8080/file/download/-7792170830371993885.jpg'),(14,'efafa',1,'<p>efaef</p>',3,0,'2024-12-12 15:48:13','2024-12-12 15:48:13','http://localhost:8080/file/download/-8764902502727312316.jpg'),(16,'百年孤独观后感',1,'       <h2>魔幻现实主义的瑰丽篇章</h2>         <p>《百年孤独》是加西亚·马尔克斯的一部不朽经典，书中通过布恩迪亚家族百年的兴衰史，描绘了一个充满魔幻与现实交织的世界。作品不仅展现了个体命运的孤独，还折射了拉丁美洲的社会现实与历史命运。</p>          <h2>孤独的主题贯穿始终</h2>         <p>整部作品的主题围绕“孤独”展开，每一代布恩迪亚家族成员都以各自独特的方式经历着孤独。这种孤独不仅来源于个体内心的渴望与失落，也源于与外界环境的对抗与疏离。作品通过细腻而深刻的笔触，让读者感受到这种孤独既是宿命的枷锁，也是人类灵魂深处的必然归宿。</p>          <h2>语言与结构的非凡魅力</h2>         <p>马尔克斯运用了大量象征与隐喻，将现实与幻想无缝融合。他的语言华丽而细腻，叙事结构复杂而有序，每一个细节都蕴藏深意。从马孔多的创建到最终的毁灭，读者仿佛置身于一个恢弘的寓言之中。</p>          <h2>读后感悟</h2>         <p>《百年孤独》不仅是一部文学巨著，更是一面镜子，映照出人类在历史洪流中的无助与坚韧。这部作品让我深刻认识到，孤独既是痛苦的，也是成就自我认知的必要过程。布恩迪亚家族的百年命运告诉我们：理解孤独，接受孤独，或许正是摆脱孤独的开始。</p>          <p>总之，《百年孤独》是一部值得反复阅读的经典，它以非凡的文学魅力与深刻的思想内涵，让每一位读者都能在其中找到属于自己的感悟。</p>   ',3,0,'2024-12-13 08:30:31','2024-12-13 08:39:16','http://localhost:8080/file/download/-6546254777874256567.png');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_user_collect`
--

DROP TABLE IF EXISTS `article_user_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_user_collect` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `article_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_user_collect`
--

LOCK TABLES `article_user_collect` WRITE;
/*!40000 ALTER TABLE `article_user_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_user_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_user_like`
--

DROP TABLE IF EXISTS `article_user_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_user_like` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `article_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_user_like`
--

LOCK TABLES `article_user_like` WRITE;
/*!40000 ALTER TABLE `article_user_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `label` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'getUsers',NULL),(2,'getArticles',NULL),(3,'getUserInfo',NULL),(4,'allAuthority',NULL);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'java'),(2,'android'),(3,'c/c++'),(4,'react'),(5,'vue'),(6,'python');
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `authLabel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `label` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `channelId` int unsigned DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','人员权限与身份管理','最高管理员',NULL,0),(2,'user','','普通用户',NULL,1),(3,'channelAdmin','频道管理','频道管理员',1,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `authority_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
INSERT INTO `role_authority` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1);
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_unique` (`username`),
  UNIQUE KEY `user_unique_1` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'river','$2a$10$zJRDvmcGnO5EaEX3c/IYJew0fSlLMgNoQKrmFkjsDp0XuoUBaVgtS','lichangjiang0490@qq.com','17839528192','2024-12-04 15:36:28','2024-12-05 04:23:54',0),(2,'river776','$2a$10$378o3OAdLNt4JA0cWaSesOyxJv3FJveN7gTve38a7VQWEbk6ToK0e','zhenming338@gmail.com',NULL,'2024-12-16 01:25:53','2024-12-16 01:30:56',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,3),(3,2,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-18 17:22:22
