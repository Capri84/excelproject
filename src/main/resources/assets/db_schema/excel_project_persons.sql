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
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `persons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `middlename` varchar(256) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `inn` varchar(12) DEFAULT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id_idx` (`id`),
  CONSTRAINT `address_id` FOREIGN KEY (`id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'Numanoğlu','Ayşe','Сергеевна','1981-07-30','Ж','772564290903',1),(2,'Schmitt','Ariane','Леонидовна','1960-11-30','Ж','770430385282',2),(3,'Sadıklar','Barış','Евгеньевич','1971-09-06','М','772473933515',3),(4,'Patel','Jacob','Павлович','1964-04-28','М','770247517194',4),(5,'Huppertz','Edit','Ярославовна','1985-07-08','Ж','771909897009',5),(6,'Jimenez','Alvin','Петрович','1995-03-24','М','772589420287',6),(7,'Leclerc','Brian','Давыдович','1996-08-03','М','772089048840',7),(8,'Silveira','Georgeta','Павловна','1987-10-25','Ж','771342546782',8),(9,'Çatalbaş','Buse','Геннадиевна','1974-07-08','Ж','771738334780',9),(10,'Christiansen','Nikolaj','Филиппович','1978-05-11','М','770216237729',10),(11,'Storck','Bettina','Владиславовна','1954-06-26','Ж','771092442812',11),(12,'Niska','Samu','Степанович','1980-04-22','М','770130253912',12),(13,'Medina','Carmen','Васильевна','1967-06-24','Ж','772700766408',13),(14,'Gibson','Fred','Иосифович','1975-05-17','М','774651228736',14),(15,'Lorenzo','Rodrigo','Савельевич','1960-08-15','М','772229805806',15),(16,'Wirkkala','Neea','Евгеньевна','1960-02-10','Ж','771523803618',16),(17,'Walker','Rita','Кирилловна','1967-11-17','Ж','771367563204',17),(18,'Fernandez','Garance','Григорьевна','1986-09-07','Ж','772045524234',18),(19,'Van benten','Louisa','Афанасьевна','1961-11-10','Ж','771371552413',19),(20,'Bakırcıoğlu','Adem','Дмитриевич','1965-08-07','М','772912100218',20),(21,'Termaat','Chaira','Егоровна','1979-11-25','Ж','770695156168',21),(22,'Nielsen','Gustav','Денисович','1955-06-09','М','772276870009',22),(23,'Fagervik','Eleonora','Робертовна','1964-07-20','Ж','772696713094',23),(24,'Møller','Kasper','Наумович','1947-07-20','М','773570093278',24),(25,'Carpentier','Cyrill','Давыдович','1987-07-17','М','772762281202',25),(26,'Houtveen','Silke','Витальевич','1996-10-08','М','773513249000',26),(27,'Miller','Melissa','Кирилловна','1965-07-18','Ж','772996911192',27),(28,'Marshall','Maureen','Егоровна','1992-12-21','Ж','770424938130',28),(29,'Ribeiro','Sadraque','Тихонович','1951-08-12','М','770520318001',29),(30,'Lehtola','Iiris','Аркадьевна','1963-11-04','Ж','772978152633',30),(31,'Edwards','Nixon','Станиславович','1974-06-29','М','771671466211',31),(32,'Serrano','Cesar','Родионович','1985-10-01','М','770443749240',32),(33,'Philipse','Serpil','Павловна','1990-08-11','Ж','771396812896',33),(34,'Niessen','Damini','﻿Александровна','1994-10-04','Ж','771340129504',34),(35,'Fabre','Giorgia','Валериевна','1962-01-24','Ж','771077202492',35),(36,'Sezek','Ümit','Алексеевич','1993-02-07','М','772133447652',36),(37,'Da paz','Xico','Валентинович','1961-10-30','М','773647821821',37),(38,'Johnson','Sienna','Григорьевна','1994-02-10','Ж','770304558841',38),(39,'Pulkkinen','Eemil','Наумович','1959-01-18','М','772927076021',39),(40,'Dubois','Adriano','Сергеевич','1994-07-17','М','771033342421',40),(41,'Mortensen','Freja','Ефимовна','1946-05-11','Ж','772046680973',41),(42,'Castro','Inmaculada','Арсеньевна','1988-06-06','Ж','772992411801',42),(43,'Guerrero','Jonathan','Леонидович','1984-06-18','М','770956020615',43);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
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
