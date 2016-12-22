CREATE DATABASE  IF NOT EXISTS `apartments`;
USE `apartments`;
DROP TABLE IF EXISTS `flats`;

CREATE TABLE `flats` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `district` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `area` double NOT NULL,
  `rooms` int(2) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `flats` VALUES (1,'Darnitsa','Voskresenskaya 14',100,3,90000),
  (2,'Darnitsa','Voskresenskaya 14',55,1,45000),
  (3,'Borschagovka','Ulica 22',85,3,70000),
  (4,'Sviatoshin','Pobedi 2',100,2,95000),
  (5,'Darnitsa','Voskresenskaya 12',50,1,40000);
