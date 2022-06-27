/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.16 : Database - aklatan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aklatan` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `aklatan`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(32) DEFAULT NULL,
  `passwordx` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`username`,`passwordx`) values ('admin','*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19');

/*Table structure for table `aklat` */

DROP TABLE IF EXISTS `aklat`;

CREATE TABLE `aklat` (
  `bookID` varchar(225) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `borrow` int(32) DEFAULT NULL,
  `total` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `aklat` */

insert  into `aklat`(`bookID`,`title`,`category`,`borrow`,`total`) values ('PUP-Novel-Mystery-0','The Adventures of Sherlock Holmes','Novel-Mystery',0,100),('PUP-Novel-Mystery-1','The Valley of Fear','Novel-Mystery',0,100),('PUP-Novel-Mystery-2','The Casebook of Sherlock Holmes','Novel-Mystery',0,111),('PUP-Novel-Mystery-3','The Hounds of the Baskervilles','Novel-Mystery',0,111),('PUP-Novel-Mystery-4','Physics by Mr.Sarmiento','Science and Technology',0,100),('PUP-Novel-Mystery-5','The Secret of Numbers','Mathematics',2,10),('PUP-Novel-Mystery-6','Bakit Hindi ka Crush ng Crush mo?','Novel-Lovestory',0,10),('PUP-Novel-Mystery-7','Romeo and Juliet','Novel-Lovestory',0,10),('PUP-Novel-Mystery-8','Philippine History and Geography','History',0,10),('PUP-Novel-Mystery-9','Detective Conan','Manga',0,10),('PUP-Literature-10','One Piece','Manga',1,111);

/*Table structure for table `borr` */

DROP TABLE IF EXISTS `borr`;

CREATE TABLE `borr` (
  `brwID` int(255) NOT NULL AUTO_INCREMENT,
  `studID` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `penalties` varchar(255) DEFAULT NULL,
  KEY `brwID` (`brwID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `borr` */

insert  into `borr`(`brwID`,`studID`,`title`,`borrow_date`,`penalties`) values (9,'PUP-BSIT-2','hp','2015-03-20 03:12:37','0'),(10,'PUP-BSIT-2','hp','2015-03-20 03:12:39','0'),(12,'PUP-BSIT-1','One Piece','2015-03-15 09:39:50','50');

/*Table structure for table `returns` */

DROP TABLE IF EXISTS `returns`;

CREATE TABLE `returns` (
  `borrowID` int(255) DEFAULT NULL,
  `studID` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `retDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `returns` */

insert  into `returns`(`borrowID`,`studID`,`title`,`retDate`) values (3,'PUP-BSIT-1','aw','2015-03-19 20:34:15'),(5,'PUP-BSIT-1','aw','2015-03-20 01:04:05'),(7,'PUP-BSIT-2','hp','2015-03-20 03:11:01'),(8,'PUP-BSIT-2','hp','2015-03-20 03:13:02'),(11,'PUP-BSIT-1','One Piece','2015-03-20 09:38:49');

/*Table structure for table `stu` */

DROP TABLE IF EXISTS `stu`;

CREATE TABLE `stu` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `passwordx` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `surname` varchar(32) DEFAULT NULL,
  `course` varchar(32) DEFAULT NULL,
  `level` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stu` */

insert  into `stu`(`id`,`username`,`passwordx`,`name`,`surname`,`course`,`level`) values ('PUP-ACCOUNTANCY-7','JessyJ','*8C36208C9385101F48EB2C405CDB0F1A85D317EC','Jess','Domino','ACCOUNTANCY','First Year'),('PUP-BSIT-1','Jampol','*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19','Jhon Paul','Renes','BSIT','Second Year'),('PUP-BSIT-2','NENE','*7534F9EAEE5B69A586D1E9C1ACE3E3F9F6FCC446','ramon','villanea','BSIT','Third Year'),('PUP-BSIT-3','Maya','*E6CC90B878B948C35E92B003C792C46C58C4AF40','Hjmaya','Ruiz','BSIT','Third Year'),('PUP-BSIT-4','JuanT','*7F9578E7235FCF775386E1FE53FF6FA42174507A','Juan','Tamad','BSIT','First Year'),('PUP-BSIT-8','TricYchan09','*33F15A6D0DEE851574B906B89C42D919F894A626','Sean Christian Philip','Paller','BSIT','Second Year'),('PUP-BSIT-9','tricy09','*32893B6DBA6956F1228D82373BD32544302E2632','Ava Patricia','Bañez','BSIT','First Year'),('PUP-EDUC-MATH-6','BogartK','*A7E068E787C81CB58195A7C8C51C533AA58CDBFA','Bogart','Gagoin','EDUC-MATH','Third Year'),('PUP-HRDM-5','AliyahD','*E27E61E0F167977486A83B053794F47B87C435E8','Aliyah','De Ryes','HRDM','Fourth Year');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
