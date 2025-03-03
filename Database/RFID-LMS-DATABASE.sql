-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: rfid_lms
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_uid` varchar(10) NOT NULL,
  `title` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `edition` varchar(20) NOT NULL,
  `isbn_number` int NOT NULL,
  `genre_id` int NOT NULL,
  `book_language_id` int NOT NULL,
  `shelf_location` varchar(20) NOT NULL,
  `book_price` int DEFAULT NULL,
  `status` enum('issued','available','unavailable') NOT NULL DEFAULT 'available',
  PRIMARY KEY (`book_uid`),
  KEY `genre_id` (`genre_id`),
  KEY `book_language_id` (`book_language_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`book_language_id`) REFERENCES `book_language` (`book_language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('1A3D6BC1','Web Technology','Aniket Nagane','Nirali Publishing','1st',243456789,1,3,'A3-25',200,'available'),('24BE47B','Marketing Management','M.V Kulkarni','D.Y Patil','2nd',293456789,1,3,'A3-31',600,'available'),('2AE94CC1','Relational Database','Abhijeet Mankar','Nirali Publishing','1st',253456789,1,3,'A3-26',230,'available'),('43CA3110','300 Brave Men','Gautam Pradhan','Platinum Press Publishing','1st',223456789,2,3,'A3-22',560,'available'),('948C3A7B','Advance Java','Kiran Digrase','Kiran Publishing','2nd',273456789,1,3,'A3-30',500,'available'),('9A61DC1','Yayati','V.S Khandekar','Mehta Publishing','1st',123456789,2,1,'A3-12',550,'available'),('B001','The Alchemist','Paulo Coelho','HarperCollins','1st',9780,1,1,'A1-05',850,'available'),('CAA19CC1','Midnight Children','Dr.Arun Mande','Riya Publishing','2nd',233456789,2,1,'A3-23',560,'available'),('DFERFE45','FVERV F','VERVRV','VREFGWRGV','1ST',12356,1,1,'FW2',234,'available'),('EAB28CC1','Java Programming','Kiran Digrase','Kiran Publishing','3rd',263456789,1,3,'A3-30',500,'available'),('F4F1A17B','MIS System','Vivek Marathe','D.Y Patil','1st',283456789,1,3,'A3-31',600,'available');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_borrowed_student`
--

DROP TABLE IF EXISTS `book_borrowed_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_borrowed_student` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `student_uid` varchar(10) NOT NULL,
  `book_uid` varchar(10) NOT NULL,
  `borrowed_date` date NOT NULL,
  `expected_return_date` date NOT NULL,
  `actual_return_date` date DEFAULT NULL,
  `fine` int DEFAULT NULL,
  `status` enum('borrowed','returned','overdue') NOT NULL DEFAULT 'borrowed',
  `remark` varchar(30) DEFAULT NULL,
  `librarian_id` int NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `student_uid` (`student_uid`),
  KEY `book_uid` (`book_uid`),
  KEY `librarian_id` (`librarian_id`),
  CONSTRAINT `book_borrowed_student_ibfk_1` FOREIGN KEY (`student_uid`) REFERENCES `student` (`student_uid`),
  CONSTRAINT `book_borrowed_student_ibfk_2` FOREIGN KEY (`book_uid`) REFERENCES `book` (`book_uid`),
  CONSTRAINT `book_borrowed_student_ibfk_3` FOREIGN KEY (`librarian_id`) REFERENCES `librarian` (`librarian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_borrowed_student`
--

LOCK TABLES `book_borrowed_student` WRITE;
/*!40000 ALTER TABLE `book_borrowed_student` DISABLE KEYS */;
INSERT INTO `book_borrowed_student` VALUES (1,'94F7687B','CAA19CC1','2024-12-17','2024-12-24',NULL,0,'returned',NULL,2),(2,'8452C77B','F4F1A17B','2024-12-18','2024-12-25',NULL,0,'returned',NULL,2),(3,'8452C77B','43CA3110','2024-12-18','2024-12-25',NULL,0,'returned',NULL,2),(4,'8452C77B','43CA3110','2024-12-18','2024-12-25',NULL,0,'returned',NULL,2),(39,'8452C77B','CAA19CC1','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(41,'8452C77B','43CA3110','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(42,'536ACBD','1A3D6BC1','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(43,'536ACBD','24BE47B','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(44,'536ACBD','2AE94CC1','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(45,'536ACBD','EAB28CC1','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(46,'536ACBD','2AE94CC1','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(47,'94F7687B','F4F1A17B','2024-12-22','2024-12-29','2024-12-22',0,'returned',NULL,1),(51,'536ACBD','43CA3110','2024-12-23','2024-12-30','2024-12-23',0,'returned',NULL,1),(52,'536ACBD','43CA3110','2024-12-23','2024-12-30','2024-12-23',0,'returned',NULL,1),(53,'536ACBD','24BE47B','2024-12-23','2024-12-30','2024-12-23',0,'returned',NULL,1),(54,'536ACBD','1A3D6BC1','2024-12-23','2024-12-30','2024-12-23',0,'returned',NULL,1),(55,'8452C77B','CAA19CC1','2024-12-23','2024-12-30','2024-12-23',0,'returned',NULL,1),(57,'8452C77B','2AE94CC1','2024-12-31','2025-01-07','2025-01-01',0,'returned',NULL,2),(58,'8452C77B','1A3D6BC1','2024-12-31','2025-01-07','2024-12-31',0,'returned',NULL,2),(59,'8452C77B','EAB28CC1','2024-12-31','2025-01-07','2024-12-31',0,'returned',NULL,2),(60,'536ACBD','1A3D6BC1','2024-12-31','2025-01-07','2025-01-01',0,'returned',NULL,2),(61,'8452C77B','948C3A7B','2024-12-31','2025-01-07','2024-12-31',0,'returned',NULL,2),(62,'8452C77B','1A3D6BC1','2024-12-31','2025-01-07','2024-12-31',0,'returned',NULL,2),(63,'8452C77B','1A3D6BC1','2024-12-31','2025-01-07','2025-01-01',0,'returned',NULL,2),(64,'536ACBD','9A61DC1','2025-01-01','2025-01-08','2025-01-01',0,'returned',NULL,2),(65,'94F7687B','CAA19CC1','2025-01-01','2025-01-08','2025-01-01',0,'returned',NULL,2),(66,'94F7687B','F4F1A17B','2024-12-15','2024-12-20','2025-01-01',0,'returned',NULL,2),(67,'94F7687B','EAB28CC1','2024-12-15','2024-12-21','2025-01-01',0,'returned',NULL,2),(68,'8452C77B','43CA3110','2024-12-15','2024-12-21','2025-01-01',0,'returned',NULL,2);
/*!40000 ALTER TABLE `book_borrowed_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_language`
--

DROP TABLE IF EXISTS `book_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_language` (
  `book_language_id` int NOT NULL AUTO_INCREMENT,
  `book_language_name` varchar(20) NOT NULL,
  PRIMARY KEY (`book_language_id`),
  UNIQUE KEY `book_language_name` (`book_language_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_language`
--

LOCK TABLES `book_language` WRITE;
/*!40000 ALTER TABLE `book_language` DISABLE KEYS */;
INSERT INTO `book_language` VALUES (3,'English'),(2,'Hindi'),(1,'Marathi');
/*!40000 ALTER TABLE `book_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) NOT NULL,
  `course_branch` varchar(20) NOT NULL,
  `course_duration` varchar(10) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_name` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'BCA','Computer Science','3 years'),(2,'MCA','Computer Science','2 years'),(3,'B.com','Commerce','3 years'),(4,'M.com','Commerce','2 years');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(20) NOT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE KEY `genre_name` (`genre_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Education'),(2,'Novel');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarian` (
  `librarian_id` int NOT NULL AUTO_INCREMENT,
  `librarian_name` varchar(20) NOT NULL,
  `age` int DEFAULT NULL,
  `email_id` varchar(30) NOT NULL,
  `phone_number` int NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`librarian_id`),
  UNIQUE KEY `email_id` (`email_id`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES (1,'Aditiya Kate',32,'adi123@gmail.com',123456,'Pune'),(2,'Namrata Shinde',29,'namrata25@gmail.com',526785,'Pune');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian_credentials`
--

DROP TABLE IF EXISTS `librarian_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarian_credentials` (
  `credential_id` int NOT NULL AUTO_INCREMENT,
  `librarian_id` int DEFAULT NULL,
  `librarian_username` varchar(20) NOT NULL,
  `librarian_password` varchar(30) NOT NULL,
  PRIMARY KEY (`credential_id`),
  KEY `librarian_id` (`librarian_id`),
  CONSTRAINT `librarian_credentials_ibfk_1` FOREIGN KEY (`librarian_id`) REFERENCES `librarian` (`librarian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian_credentials`
--

LOCK TABLES `librarian_credentials` WRITE;
/*!40000 ALTER TABLE `librarian_credentials` DISABLE KEYS */;
INSERT INTO `librarian_credentials` VALUES (1,1,'Adi@123','Adi@123'),(2,2,'Pass@123','Pass@123');
/*!40000 ALTER TABLE `librarian_credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_uid` varchar(10) NOT NULL,
  `student_first_name` varchar(20) NOT NULL,
  `student_last_name` varchar(20) NOT NULL,
  `course_id` int NOT NULL,
  `roll_no` int NOT NULL,
  `email_id` varchar(30) NOT NULL,
  `phone_number` int NOT NULL,
  `DOB` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `issued_book_count` int NOT NULL DEFAULT '0',
  `date_registration` date NOT NULL,
  `end_registration` date DEFAULT NULL,
  `status` enum('active','not-active') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`student_uid`),
  UNIQUE KEY `email_id` (`email_id`),
  UNIQUE KEY `phone_number` (`phone_number`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('536ACBD','Prathamesh','Tupe',1,77,'tupeprathamesh12@gmail.com',866895,'2003-08-14','Pune, Maharashtra',0,'2024-12-16','2025-12-16','active'),('8452C77B','Deepak','Suthar',3,50,'suthardeppak55@gmail.com',895262,'2003-02-05','Pune, Maharashtra',0,'2024-12-16','2025-12-16','active'),('94F1F4B7','Akshay ','Tupe',1,55,'akshay12@gmail.com',518849,'2005-08-22','Pune, Maharashtra',0,'2024-12-30','2025-12-30','active'),('94F7687B','Vedant','Joglekar',2,32,'vedant123@gmail.com',98235,'2000-07-25','Pune, Maharashtra',0,'2024-12-16','2025-12-16','active'),('94F7F67','Suraj','Mankar',2,52,'suraj123@gmail.com',521284,'1995-02-04','Pune, Maharashtra',0,'2024-12-31','2025-12-31','active');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_credentials`
--

DROP TABLE IF EXISTS `student_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_credentials` (
  `credential_id` int NOT NULL AUTO_INCREMENT,
  `student_uid` varchar(20) DEFAULT NULL,
  `student_username` varchar(30) NOT NULL,
  `student_password` varchar(30) NOT NULL,
  PRIMARY KEY (`credential_id`),
  KEY `student_uid` (`student_uid`),
  CONSTRAINT `student_credentials_ibfk_1` FOREIGN KEY (`student_uid`) REFERENCES `student` (`student_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_credentials`
--

LOCK TABLES `student_credentials` WRITE;
/*!40000 ALTER TABLE `student_credentials` DISABLE KEYS */;
INSERT INTO `student_credentials` VALUES (1,'536ACBD','Pratham@123','Pratham@123'),(2,'8452C77B','Deepak@123','Deepak@123'),(3,'94F7687B','Vedant@123','Vedant@123');
/*!40000 ALTER TABLE `student_credentials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-03  7:17:25
