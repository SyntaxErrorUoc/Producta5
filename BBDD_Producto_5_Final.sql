CREATE DATABASE  IF NOT EXISTS `onlinestore` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `onlinestore`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: onlinestore
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `CP` varchar(10) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Precio` double NOT NULL,
  `TiempoPreparacion` decimal(21,0) DEFAULT NULL,
  PRIMARY KEY (`CP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES ('12345','Primera carga',0,36000000000000);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `mail` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) NOT NULL,
  `vip` int DEFAULT NULL,
  `descuento` double NOT NULL DEFAULT '0',
  `cuotaAnual` int DEFAULT NULL,
  `TipoVip` varchar(31) NOT NULL,
  PRIMARY KEY (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('cespigol2@uoc.edu','Carlos2',NULL,'Barcelona',0,0,0,'CERO'),('cespigol@uoc.edu','Carlos',NULL,'Barcelona',1,0,0,'UNO'),('jf3.palacios@gmail.com','Juan',NULL,'En mi casa',1,10,30,'UNO'),('jjoyas2@uoc.edu','Chus2',NULL,'Malaga',1,0,0,'UNO'),('jjoyas3@uoc.edu','Chus3',NULL,'Malaga',1,0,0,'UNO'),('jjoyas6@gmail.com','Chus',NULL,'Malaga',1,12,10,'UNO'),('jjoyas@uoc.edu','Chus',NULL,'Malaga',0,0,0,'CERO'),('jpalaciosded2@uoc.edu','Juan',NULL,'Gijon',1,12,10,'UNO');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `numeroPedido` int NOT NULL,
  `fechaHoraPedido` datetime NOT NULL,
  `costeEnvio` double NOT NULL,
  `cantidad` int NOT NULL,
  `enviado` bit(1) DEFAULT NULL,
  `Articulo_CP` varchar(10) NOT NULL,
  `ClienteStandard_mail` varchar(45) NOT NULL,
  PRIMARY KEY (`numeroPedido`),
  KEY `fk_Pedido_Articulo_idx` (`Articulo_CP`),
  KEY `fk_Pedido_ClienteStandard1_idx` (`ClienteStandard_mail`),
  CONSTRAINT `fk_Pedido_Articulo` FOREIGN KEY (`Articulo_CP`) REFERENCES `articulo` (`CP`),
  CONSTRAINT `fk_Pedido_ClienteStandard1` FOREIGN KEY (`ClienteStandard_mail`) REFERENCES `cliente` (`mail`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2222,'2023-12-31 12:00:00',12,1,_binary '','12345','jf3.palacios@gmail.com'),(2225,'2023-12-01 11:00:00',0,120,_binary '','12345','jf3.palacios@gmail.com'),(2226,'2023-12-14 23:00:00',15,1,_binary '\0','12345','jf3.palacios@gmail.com'),(2229,'2023-12-05 15:00:00',10,5,_binary '\0','12345','jf3.palacios@gmail.com');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `nombre` varchar(45) NOT NULL,
  `contrasenya` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('carlos','12345678'),('chus','12345678'),('juan','12345678'),('pablo','12345678'),('admin','admin');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-02 23:12:13
