-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: FutureForce19
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CART`
--

DROP TABLE IF EXISTS `CART`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CART` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `qty` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FK_18krg3e4xytyvx2a5jyx2lln5` (`product_id`),
  CONSTRAINT `FK_18krg3e4xytyvx2a5jyx2lln5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CART`
--

LOCK TABLES `CART` WRITE;
/*!40000 ALTER TABLE `CART` DISABLE KEYS */;
INSERT INTO `CART` VALUES (5,1,3,9);
/*!40000 ALTER TABLE `CART` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Delivery`
--

DROP TABLE IF EXISTS `Delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `deliveryExecutiveId` int(11) DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tf4wd13h6vkks22uhh3bf7bf0` (`deliveryExecutiveId`),
  KEY `FK_135uo1lw9ecki1xi8c4fmtmlj` (`orderId`),
  CONSTRAINT `FK_135uo1lw9ecki1xi8c4fmtmlj` FOREIGN KEY (`orderId`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_tf4wd13h6vkks22uhh3bf7bf0` FOREIGN KEY (`deliveryExecutiveId`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Delivery`
--

LOCK TABLES `Delivery` WRITE;
/*!40000 ALTER TABLE `Delivery` DISABLE KEYS */;
INSERT INTO `Delivery` VALUES (1,'2019-08-06',2,1),(2,'2019-08-06',2,2),(3,'2019-08-06',2,3),(4,'2019-08-06',2,3),(5,'2019-08-06',10,4),(6,'2019-08-06',2,4),(7,'2019-07-31',10,5),(8,'2019-08-06',2,6);
/*!40000 ALTER TABLE `Delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `INVENTORY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDeleted` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `vendorId` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fysbv90i24fctupwvyxvd29hg` (`name`),
  KEY `FK_lxqpkyctofye525hhu1w81fe7` (`product_id`),
  CONSTRAINT `FK_lxqpkyctofye525hhu1w81fe7` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INVENTORY`
--

LOCK TABLES `INVENTORY` WRITE;
/*!40000 ALTER TABLE `INVENTORY` DISABLE KEYS */;
INSERT INTO `INVENTORY` VALUES (1,_binary '','Xiaomi Redmi K20 Pro',0,'4',1),(2,_binary '\0','Vivo Z1 Pro',2,'4',2),(3,_binary '\0','Samsung Galaxy A50',100,'4',3),(4,_binary '\0','Dell Inspiron 3552 (Z565160HIN9)(CDC/4GB/500GB/Win 10) Laptop ',2,'4',4),(5,_binary '\0','Asus X512DA-EJ438T (Ryzen 5 4GB 256GB SSD Windows) Laptop ',1,'4',5),(6,_binary '\0','Lenovo Ideapad 320 (80XV010DIN) (E2/4GB/1TB/Win 10) Laptop',3,'4',6),(7,_binary '\0','Canon 5DS (Body) DSLR Camera',0,'4',7),(8,_binary '\0','Nikon D800E (Body only) DSLR Camera',0,'4',8),(9,_binary '\0','Canon EOS 7D Mark II (Body) DSLR Camera',0,'4',9),(10,_binary '','Nike shoes',0,'4',10),(11,_binary '\0','Nike Air Max Sequent 4.5',122,'4',11);
/*!40000 ALTER TABLE `INVENTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderDetail`
--

DROP TABLE IF EXISTS `OrderDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `OrderDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `producty_name` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderDetail`
--

LOCK TABLES `OrderDetail` WRITE;
/*!40000 ALTER TABLE `OrderDetail` DISABLE KEYS */;
INSERT INTO `OrderDetail` VALUES (1,100000,1,10,'Nike shoes',10),(2,20000,2,10,'Nike shoes',2),(3,160000,3,7,'Canon 5DS (Body) DSLR Camera',1),(4,999999,3,9,'Canon EOS 7D Mark II (Body) DSLR Camera',1),(5,400009,4,6,'Lenovo Ideapad 320 (80XV010DIN) (E2/4GB/1TB/Win 10) Laptop',1),(6,999999,4,9,'Canon EOS 7D Mark II (Body) DSLR Camera',1),(7,27999,5,1,'Xiaomi Redmi K20 Pro',1),(8,12000,6,11,'Nike Air Max Sequent 4.5',1);
/*!40000 ALTER TABLE `OrderDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ORDERS` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `deliveryDate` date DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `recipientName` varchar(255) DEFAULT NULL,
  `recipientPhone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalAmount` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
INSERT INTO `ORDERS` VALUES (1,'123 Secret Street','Baltimore','USA','2019-08-06','2019-07-31','Rob','0987654321','Maryland','Delivered',100000,3,'207551'),(2,'123 Secret Street','Baltimore','USA','2019-08-06','2019-07-31','Rob','0987654321','Maryland','Left from Salesforce E-commerce',20000,3,'207551'),(3,'123 Secret Street','Baltimore','USA','2019-08-06','2019-07-31','Rob','0987654321','Maryland','Pending',1159999,3,'207551'),(4,'123 Secret Street','Baltimore','USA','2019-08-06','2019-07-31','Raj','0987654321','Maryland','Pending',1400008,9,'567890'),(5,'123 Secret Street','Baltimore','USA','2019-08-06','2019-07-31','Raj','0987654321','Maryland','Delivered',27999,9,'567890'),(6,'adsdgfgf','Pune','India','2019-08-06','2019-07-31','Laveen Ekka','1234567878','Maha','Pending',12000,12,'123456');
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `PRODUCT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` longtext,
  `image_link` longtext,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT`
--

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
INSERT INTO `PRODUCT` VALUES (1,'mobiles','Qualcomm Snapdragon 855 Octa core Processor','https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQkFsITuZFkVoIclTZS7-ihEsQnUoYSGAetfP7vMVZ64hRFk3u0HwDT-0CVseA2kWlNTKekuxn1vw&usqp=CAc','Xiaomi Redmi K20 Pro','27999',0),(2,'mobiles','ualcomm Snapdragon 712 Octa core Processor','https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcSqV9I559zneUyP7RoLhkKbPlnYa5GgnLnrYl4zSL9d4kcpLOJm1DiVy3mcHWLOyyrBs1Hiucih&usqp=CAc','Vivo Z1 Pro','14990',2),(3,'mobiles','6.4 inches (16.26 cm) bezel-less display with waterdrop notch','https://rukminim1.flixcart.com/image/832/832/jsyyufk0/mobile/b/w/f/samsung-galaxy-a50-sm-a505fzwdins-original-imafefghh95xxr6d.jpeg?q=70','Samsung Galaxy A50','24000',100),(4,'laptops','Intel HD Graphic Processor','https://rukminim1.flixcart.com/image/312/312/jwkzwy80/computer/e/h/m/dell-na-laptop-original-imafh8bvaazfndsq.jpeg?q=70','Dell Inspiron 3552 (Z565160HIN9)(CDC/4GB/500GB/Win 10) Laptop ','400000',2),(5,'laptops','Intel HD Graphic Processor','https://rukminim1.flixcart.com/image/312/312/jv8exzk0/computer/m/n/h/asus-na-laptop-original-imafg4km4jja46qs.jpeg?q=70','Asus X512DA-EJ438T (Ryzen 5 4GB 256GB SSD Windows) Laptop ','267432',1),(6,'laptops','AMD Radeon R5 Graphic Processor','https://rukminim1.flixcart.com/image/312/312/ji0lbbk0/computer/a/n/p/lenovo-na-laptop-original-imaf5wstgkjvfhjw.jpeg?q=70','Lenovo Ideapad 320 (80XV010DIN) (E2/4GB/1TB/Win 10) Laptop','400009',3),(7,'Dslr','700 Shots','https://rukminim1.flixcart.com/image/312/312/camera/d/6/7/canon-dslr-eos-5d-mark-iv-dslr-original-imaemgtbmhvwhzdm.jpeg?q=70','Canon 5DS (Body) DSLR Camera','160000',0),(8,'Dslr','Built-in flash, External flash supported','https://rukminim1.flixcart.com/image/832/832/lens/zoom/c/y/z/nikon-af-s-dx-nikkor-18-140mm-f-3-5-5-6g-ed-vr-original-imadsyhudgcffrgc.jpeg?q=70','Nikon D800E (Body only) DSLR Camera','200000',0),(9,'Dslr','Built-in flash, External flash supported','https://rukminim1.flixcart.com/image/312/312/jk8lz0w0/dslr-camera/h/k/h/eos-7d-mark-ii-canon-original-imaf6n7gkmgdf4tx.jpeg?q=70','Canon EOS 7D Mark II (Body) DSLR Camera','999999',0),(10,'Shoes','asd','asd','Nike shoes','10000',0),(11,'Shoes','The Nike Air Max Sequent 4.5 redefines comfort with the combination of a U-shaped Max Air unit, responsive foam and a breathable mesh in the upper.','https://c.static-nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/iaohdysasfqybhqcqbcq/air-max-sequent-4-5-running-shoe-Gd242B.jpg','Nike Air Max Sequent 4.5','12000',122);
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `recipient_phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kfu0161nvirkey6fwd6orucv7` (`user_id`),
  CONSTRAINT `FK_kfu0161nvirkey6fwd6orucv7` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (1,'','','',_binary '','','','','654321',1),(2,'','','',_binary '','','','','123456',2),(4,'123 Secret Street','Baltimore','USA',_binary '','Rob','0987654321','Maryland','207551',3),(5,'123 Secret Street','Baltimore','USA',_binary '','Ram','0987654321','Maryland','123457',7),(6,'','','',_binary '','','','','123456',8),(7,'123 Secret Street','Baltimore','USA',_binary '\0','Raj','0987654321','Maryland','567890',9),(8,'','','',_binary '','','','','567890',10),(9,'123 Secret Street','Baltimore','USA',_binary '\0','Ram','0987654321','Maryland','207551',9),(10,'123 Secret Street','Baltimore','India',_binary '','Rao','1234567890','Maryland','207551',11),(11,'123 Secret Street','Baltimore','India',_binary '','','','Maryland','207552',9),(12,'adsdgfgf','Pune','India',_binary '','Laveen Ekka','1234567878','Maha','123456',12);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'de1@gmail.com','de1','de1','de1','0987654321','delivery'),(2,'de2@gmail.com','de2','de2','de2','0987654321','delivery'),(3,'c1@gmail.com','c1','c1','c1','0987654321','customer'),(4,'v1@gmail.com','v1','v1','v1','0987654321','manager'),(5,'da1@gmail.com','da1','da1','da1','0987654321','da'),(6,'v2@gmail.com','Vendor1','v1','v2','0987654321','manager'),(7,'c2@gmail.com','customer2','c2','c2','0987654321','customer'),(8,'de3@gmail.com','DeliveryExec','E3','de3','0987654321','delivery'),(9,'c3@gmail.com','Customer3','C3','c3','0987654321','customer'),(10,'de4@gmail.com','DeliveryExec4','DE4','de4','0987654321','delivery'),(11,'c5@gmail.com','Customer5','C5','c5','0987654321','customer'),(12,'laveen.ekka@gmail.com','Laveen','Ekka','1234','1234567878','customer');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-31 17:49:17
