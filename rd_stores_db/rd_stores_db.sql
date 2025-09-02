-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: rd_stores_db
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `bill_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `payment_id` int(11) unsigned zerofill NOT NULL,
  `billed_date` date NOT NULL,
  `billed_time` time DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  UNIQUE KEY `bill_id_UNIQUE` (`bill_id`),
  KEY `fk_bill_payment1_idx` (`payment_id`),
  CONSTRAINT `fk_bill_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (00000000001,00000000001,'2025-09-02','20:50:35'),(00000000002,00000000002,'2025-09-02','20:51:43'),(00000000003,00000000003,'2025-09-02','20:53:36');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_item`
--

DROP TABLE IF EXISTS `bill_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_item` (
  `bill_id` int(11) unsigned zerofill NOT NULL,
  `item_id` int(11) unsigned zerofill NOT NULL,
  `bill_item_quantity` int NOT NULL,
  PRIMARY KEY (`bill_id`,`item_id`),
  KEY `fk_bill_item_item1_idx` (`item_id`),
  CONSTRAINT `fk_bill_item_bill1` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`),
  CONSTRAINT `fk_bill_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_item`
--

LOCK TABLES `bill_item` WRITE;
/*!40000 ALTER TABLE `bill_item` DISABLE KEYS */;
INSERT INTO `bill_item` VALUES (00000000001,00000000002,1),(00000000001,00000000010,1),(00000000001,00000000012,1),(00000000001,00000000014,1),(00000000001,00000000015,10),(00000000001,00000000016,1),(00000000002,00000000002,3),(00000000002,00000000006,1),(00000000002,00000000008,4),(00000000002,00000000009,2),(00000000002,00000000016,2),(00000000002,00000000019,1),(00000000003,00000000008,1),(00000000003,00000000010,3),(00000000003,00000000013,10),(00000000003,00000000014,1),(00000000003,00000000015,20),(00000000003,00000000018,2),(00000000003,00000000020,10),(00000000003,00000000022,2);
/*!40000 ALTER TABLE `bill_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashier`
--

DROP TABLE IF EXISTS `cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cashier` (
  `cashier_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`cashier_id`),
  UNIQUE KEY `cashier_id_UNIQUE` (`cashier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier`
--

LOCK TABLES `cashier` WRITE;
/*!40000 ALTER TABLE `cashier` DISABLE KEYS */;
INSERT INTO `cashier` VALUES (00000000001,'admin','admin123','john');
/*!40000 ALTER TABLE `cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `cashier_id` int(11) unsigned zerofill NOT NULL,
  `item_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `unit_price` double NOT NULL,
  PRIMARY KEY (`item_id`,`cashier_id`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`),
  KEY `fk_item_cashier1_idx` (`cashier_id`),
  CONSTRAINT `fk_item_cashier1` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`cashier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (00000000001,00000000001,'Salt (500g)',70),(00000000002,00000000001,'Sugar (1kg)',140),(00000000003,00000000001,'fresh Milk (1L)',420),(00000000004,00000000001,'Eggs (12 pcs)',450),(00000000005,00000000001,'Bread (1 loaf)',150),(00000000006,00000000001,'Butter (200g)',450),(00000000007,00000000001,'Coconut Oil (500ml)',400),(00000000008,00000000001,'Tea (100g)',400),(00000000009,00000000001,'Coffee (100g)',120),(00000000010,00000000001,'Bananas (1kg)',220),(00000000011,00000000001,'Tomatoes (1kg)',250),(00000000012,00000000001,'Potatoes (1kg)',200),(00000000013,00000000001,'Onions (1kg)',200),(00000000014,00000000001,'Garlic (100g)',150),(00000000015,00000000001,'Red Rice (1kg)',300),(00000000016,00000000001,'Dhal (Parippu, 1kg)',450),(00000000017,00000000001,'Chickpeas (1kg)',400),(00000000018,00000000001,'Curry Powder (100g)',150),(00000000019,00000000001,'Milk Powder (400g)',850),(00000000020,00000000001,'Instant Noodles (1 pack)',120),(00000000021,00000000001,'Vinegar (250ml)',200),(00000000022,00000000001,'Tomato Ketchup (250g)',250);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `cashier_id` int NOT NULL,
  `payment_date` date NOT NULL,
  `payment_time` time NOT NULL,
  `paid_amount` double NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_id_UNIQUE` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (00000000001,1,'2025-09-02','20:50:34',5000),(00000000002,1,'2025-09-02','20:51:42',4500),(00000000003,1,'2025-09-02','20:53:35',11500);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-02 20:59:45
