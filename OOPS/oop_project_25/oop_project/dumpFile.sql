-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: oop_project
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `Archive`
--

DROP TABLE IF EXISTS `Archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Archive` (
  `datetime` datetime DEFAULT NULL,
  `itemId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Archive`
--

LOCK TABLES `Archive` WRITE;
/*!40000 ALTER TABLE `Archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `Archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `itemId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `itemName` varchar(40) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category` varchar(30) DEFAULT NULL,
  `discount` decimal(4,2) DEFAULT NULL,
  `discountedPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Nike sports shoes',7000,17,'Sports',10.00,6300.00),(2,'Skechers Step Lite',3000,32,'Sports',5.00,2850.00),(3,'Nivia Football',500,74,'Sports',10.00,450.00),(4,'Yonex Shuttle raquet',700,48,'Sports',20.00,560.00),(5,'Nike sweat shirts',2000,26,'Sports',10.00,1800.00),(6,'OnePlus Nord 2 5G',20000,10,'Electronics',5.00,19000.00),(7,'Apple 20W USB-Charger',1300,20,'Electronics',20.00,1040.00),(8,'Sony Bluetooth Earbuds',5000,15,'Electronics',30.00,3500.00),(9,'Apple Watch Series 3',12000,5,'Electronics',5.00,11400.00),(10,'Samsung (43 inches) Smart TV',30000,9,'Electronics',15.00,25500.00),(11,'Redgear Pro Series Wired Gamepad',1000,20,'Video Games',0.00,1000.00),(12,'EvoFox Fireblade Gaming Wired Keyboard',1200,10,'Video Games',10.00,1080.00),(13,'Grand Theft Auto 5 (GTA 5) PS4',4000,5,'Video Games',20.00,3200.00),(14,'Formula 1 2020 PC',2500,15,'Video Games',75.00,625.00),(15,'Cricket 2020 PC',2000,34,'Video Games',5.00,1900.00),(16,'Ben Martin Men\'s slim fit jeans',2998,44,'Clothing',80.00,599.60),(17,'Pepe Jeans Men\'s Regular Polo Shirt',1799,38,'Clothing',40.00,1079.40),(18,'Tommy Hilfiger Women\'s slim T-shirt',1599,42,'Clothing',50.00,799.50),(19,'Pepe Jeans Women\'s Chino Jeans',2199,36,'Clothing',58.00,923.58),(20,'Baby Girl\'s midi dress',1098,24,'Clothing',52.00,527.04),(21,'Dabur Honey 1 kg',400,5,'Grocery ',20.00,320.00),(22,'Aashirvaad Atta 10kg',600,10,'Grocery ',30.00,420.00),(23,'Olive Oil 5l',1100,2,'Grocery',50.00,550.00),(24,'Tata Salt 1kg',30,100,'Grocery',2.00,29.40),(25,'TajMahal Tea 1kg',800,20,'Grocery',40.00,480.00),(26,'The Psychology of money',399,50,'Books',31.00,275.31),(27,'Head First Android Development',550,12,'Books',25.00,412.50),(28,'Harry Potter And The Order Of Phoenix ',750,36,'Books',5.00,712.50),(29,'Lord of the Rings',150,15,'Books',10.00,135.00),(30,'The jungle Book',89,20,'Books',35.00,57.85);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04 20:26:24
