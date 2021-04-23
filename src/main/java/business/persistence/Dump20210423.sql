-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: cupcake
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingridiens_bottom`
--

DROP TABLE IF EXISTS `ingridiens_bottom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingridiens_bottom` (
  `ingridient_id` int NOT NULL AUTO_INCREMENT,
  `ingridient_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`ingridient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingridiens_bottom`
--

LOCK TABLES `ingridiens_bottom` WRITE;
/*!40000 ALTER TABLE `ingridiens_bottom` DISABLE KEYS */;
INSERT INTO `ingridiens_bottom` VALUES (1,'Chocolate',5),(2,'Vanilla',5),(3,'Nutmeg',5),(4,'Pistacio',6),(5,'Almond',7);
/*!40000 ALTER TABLE `ingridiens_bottom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingridiens_top`
--

DROP TABLE IF EXISTS `ingridiens_top`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingridiens_top` (
  `ingridient_id` int NOT NULL AUTO_INCREMENT,
  `ingridient_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`ingridient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingridiens_top`
--

LOCK TABLES `ingridiens_top` WRITE;
/*!40000 ALTER TABLE `ingridiens_top` DISABLE KEYS */;
INSERT INTO `ingridiens_top` VALUES (1,'Chocolate',5),(2,'Blueberry',5),(3,'Rasberry',5),(4,'Crispy',6),(5,'Strawberry',6),(6,'Rum/Raisin',7),(7,'Orange',8),(8,'Lemon',8),(9,'Blue cheese	',9);
/*!40000 ALTER TABLE `ingridiens_top` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `ingridient_bottom_id` int NOT NULL,
  `Ingridient_top_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  KEY `fk_cupcake_link_orders1_idx` (`order_id`),
  KEY `fk_orderline_ingridiens_bottom1_idx` (`ingridient_bottom_id`),
  KEY `fk_orderline_ingridiens_top1_idx` (`Ingridient_top_id`),
  CONSTRAINT `fk_orderline_ingridiens_bottom1` FOREIGN KEY (`ingridient_bottom_id`) REFERENCES `ingridiens_bottom` (`ingridient_id`),
  CONSTRAINT `fk_orderline_ingridiens_top1` FOREIGN KEY (`Ingridient_top_id`) REFERENCES `ingridiens_top` (`ingridient_id`),
  CONSTRAINT `fk_orderline_orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (2,2,15,2,20),(3,3,14,2,20),(2,2,17,4,40),(2,2,18,2,20);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `pickuptime` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `totalprice` double NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_users1_idx` (`user_id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (14,9,'11:00',20,'2021-04-23 13:54:56'),(15,3,'12:00',20,'2021-04-23 13:55:39'),(16,3,'12:00',20,'2021-04-23 13:56:17'),(17,3,'12:00',40,'2021-04-23 14:05:00'),(18,3,'11:00',20,'2021-04-23 14:05:35');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `role` varchar(45) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'bruger1','1','employee',500),(3,'bruger3','3','customer',90),(9,'bruger9','9','customer',480);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-23 16:07:09
