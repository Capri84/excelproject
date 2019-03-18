CREATE DATABASE  IF NOT EXISTS `excel_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `excel_project`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: excel_project
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postcode` varchar(256) DEFAULT NULL,
  `country` varchar(256) DEFAULT NULL,
  `region` varchar(256) DEFAULT NULL,
  `city` varchar(256) DEFAULT NULL,
  `street` varchar(256) DEFAULT NULL,
  `house` int(11) DEFAULT NULL,
  `flat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'144436','Новая Зеландия','Курская область','Balıkesir','6529 Maçka cd',55,215),(2,'145260','Украина','Московская область','Masein','9731 Avenue de l\'abbé-roussel',140,64),(3,'150853','Колумбия','Калужская область','Sakarya','1582 Fatih sultan mehmet cd',126,188),(4,'184875','Кипр','Республика Марий Эл','Invercargill','2544 Courtenay place',34,87),(5,'185674','Турция','﻿Алтайский край','Fellbach','Lindenstraße 103',86,235),(6,'186238','Малайзия','Севастополь','Milwaukee','4301 N stelling rd',66,129),(7,'109193','Филиппины','Ставропольский край','Weesen','4224 Rue du village',112,264),(8,'163411','Албания','Новосибирская область','Campo largo','6109 Rua pará ',8,217),(9,'139268','Марокко','Приморский край','Batman','4769 Anafartalar cd',81,139),(10,'185459','Сингапур','Челябинская область','Stokkemarke','2077 Hyacintvej',58,277),(11,'116131','Иордания','Республика Северная Осетия - Алания','Pasewalk','Im winkel 190',55,277),(12,'102292','Соединенные Штаты','Чувашская Республика - Чувашия','Kuhmo','6341 Tahmelantie',6,131),(13,'136917','Исландия','Республика Коми','Málaga','8705 Calle del arenal',41,196),(14,'152149','Кения','г. Санкт-Петербург','Toowoomba','8511 Bruce st',51,294),(15,'181631','Китай','Волгоградская область','Orihuela','5547 Calle del pez',13,276),(16,'142529','Польша','Хабаровский край','Brändö','7587 Rotuaari',90,197),(17,'138676','Кения','Кемеровская область','Mackay','5271 Brown terrace',117,18),(18,'194330','Исландия','Приморский край','Argenteuil','3962 Route de genas',86,26),(19,'103410','Эстония','Камчатский край','Schiedam','8395 Potterstraat',77,168),(20,'154447','Турция','Сахалинская область','Bitlis','1935 Filistin cd',140,106),(21,'138842','Исландия','Приморский край','Terneuzen','7406 Wittevrouwenstraat',118,200),(22,'183836','Германия','Республика Марий Эл','Ølstykke','1543 Gartnervej',131,132),(23,'193738','Малайзия','г. Москва','Torpo','Erich mogensøns vei 9206',119,33),(24,'179780','Аргентина','Самарская область','Sandved','9428 Saturnvej',13,139),(25,'172169','Турция','Мурманская область','Riviera','7954 Rue des jardins',64,174),(26,'174383','Аргентина','Костромская область','Landsmeer','2344 Kintgenshaven',99,115),(27,'166972','Норвегия','Краснодарский край','El cajon','5505 Ranchview dr',99,40),(28,'171026','Боливия','Ульяновская область','Brisbane','4542 Mcclellan rd',61,11),(29,'133748','Мексика','Пензенская область','Criciúma','3080 Rua são pedro ',12,71),(30,'186427','Венгрия','Республика Бурятия','Tohmajärvi','4303 Tehtaankatu',20,99),(31,'141856','Кувейт','Кемеровская область','Whanganui','9735 Lambton quay',1,72),(32,'188313','Ливия','Сахалинская область','Santa cruz de tenerife','3829 Calle de tetuán',114,287),(33,'189447','Бразилия','Республика Башкортостан','Hillegom','2699 Biltsche grift',90,37),(34,'117667','Австрия','Республика Хакасия','Uitgeest','5617 Voorstraat',139,111),(35,'180279','Сербия','Республика Коми','Baden','2893 Quai charles-de-gaulle',31,248),(36,'195581','﻿Австралия','Севастополь','Tekirdağ','5990 Maçka cd',112,50),(37,'138741','Дания','Саратовская область','Poços de caldas','4718 Avenida d. pedro ii',2,81),(38,'105545','Мексика','Республика Бурятия','Dunedin','5174 Tennent drive',40,9),(39,'190802','Барбадос','Краснодарский край','Vesanto','5010 Itsenäisyydenkatu',17,69),(40,'186618','Марокко','Саратовская область','Novaggio','7903 Rue de l\'abbaye',100,201),(41,'115537','Бахрейн','Чувашская Республика - Чувашия','Hornbæk','2572 Hirsevænget',15,208),(42,'169512','Боливия','Красноярский край','Santa cruz de tenerife','6956 Calle de arganzuela',78,275),(43,'151262','Боливия','Чувашская Республика - Чувашия','Hospitalet de llobregat','877 Calle del arenal',145,298);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18  5:35:48
