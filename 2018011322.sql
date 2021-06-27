-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.19 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 2018011322 的数据库结构
CREATE DATABASE IF NOT EXISTS `2018011322` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `2018011322`;

-- 导出  表 2018011322.administrator 结构
CREATE TABLE IF NOT EXISTS `administrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `administrator_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- 正在导出表  2018011322.administrator 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`id`, `name`, `password`) VALUES
	(1, 'wjz', 'wjz');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;

-- 导出  表 2018011322.commodity 结构
CREATE TABLE IF NOT EXISTS `commodity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `shop_id` int NOT NULL,
  `shop_name` varchar(20) DEFAULT NULL,
  `photo_one` char(128) DEFAULT NULL,
  `inventory` int DEFAULT '0',
  `kind_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `degree` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `bargain` tinyint DEFAULT NULL COMMENT '0:不许议价,1:准许议价',
  `size` text,
  `instruction` text,
  `status` tinyint DEFAULT '0' COMMENT '0:待审核,1:发布,2:锁定,3:下架',
  `score` double DEFAULT '5' COMMENT '平均分数(5分制)',
  `evaluation_count` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `commodity_kind_id_fk` (`kind_id`),
  KEY `commodity_shop_id_fk` (`shop_id`),
  CONSTRAINT `commodity_kind_id_fk` FOREIGN KEY (`kind_id`) REFERENCES `kind` (`id`),
  CONSTRAINT `commodity_shop_id_fk` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='商品';

-- 正在导出表  2018011322.commodity 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` (`id`, `name`, `shop_id`, `shop_name`, `photo_one`, `inventory`, `kind_id`, `price`, `degree`, `discount`, `bargain`, `size`, `instruction`, `status`, `score`, `evaluation_count`) VALUES
	(2, '小米手机', 1, 'wjz', '/img/bd743b44-725a-4e66-89cb-2ba0fc38cdf5.jpg', 5, 1, 999, NULL, 0.8, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 1, 2.8462, 13),
	(3, '相册', 1, 'wjz', '/img/84256d9e-f928-47f0-9c51-efa8009c2a77.jpg', 3, 5, 47.5, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 1, 3, 1),
	(4, '冰山一角', 1, 'wjz', '/img/296d6643-77d2-41b9-820c-40afaca09282.jpg', 1, 5, 49.5, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 3, 3, 1),
	(5, '手机', 1, 'wjz', '/img/ec5cc38d-40c8-4e7b-8a47-ed2b84a71a1c.jpg', 1, 1, 4888, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(6, '联想小新薄笔记本电脑 ', 1, 'wjz', '/img/a01b4271-b789-4a06-8140-4b20b594cb67.jpg', 0, 4, 4499, NULL, 1, 0, NULL, '高色域学生办公设计师游戏本 八核锐龙R7-5700U 16G内存 512G固态 升级15.6英寸IPS全面屏【DC调光护眼无闪烁】联想小新Air15 2021超轻薄笔记本电脑 ', 1, 5, 1),
	(8, '华为手机', 1, 'wjz', '/img/26722340-8770-4871-bbb7-5942a0fb573a.jpg', 1, 1, 2988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 2, 5, 0),
	(9, '华为手机', 1, 'wjz', '/img/a672cbab-3065-4058-b935-b8c8ba2f14b2.jpg', 0, 1, 3988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(10, '相册', 1, 'wjz', '/img/84256d9e-f928-47f0-9c51-efa8009c2a77.jpg', 1, 5, 45, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 1, 4, 1),
	(11, '相册', 1, 'wjz', '/img/84256d9e-f928-47f0-9c51-efa8009c2a77.jpg', 1, 5, 84, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 1, 5, 0),
	(12, '相册', 1, 'wjz', '/img/84256d9e-f928-47f0-9c51-efa8009c2a77.jpg', 5, 5, 55, NULL, 0.9, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合.', 3, 3, 1),
	(13, '华为手机', 1, 'wjz', '/img/a61bcc22-220c-4c12-ac9d-9dcb6bd98f07.jpg', 8, 1, 2988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 0, 5, 0),
	(14, '华为手机', 1, 'wjz', '/img/353c9569-5b85-44c6-9f56-66f835b43a12.jpg', 5, 1, 2988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 3, 5, 0),
	(29, 'RIO', 1, 'wjz', '/img/bbfd17f3-bbc2-4e0e-b3ae-671296b2418b.png', 47, 3, 88, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(30, '按摩椅', 1, 'wjz', '/img/2ce0870c-cecd-42da-be44-7f68496c9085.png', 0, 5, 6988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(31, '护肤品', 1, 'wjz', '/img/ce87de44-2fbd-47fb-b1d0-c9bfbf2ccc2d.png', 0, 5, 369, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(32, '手表', 1, 'wjz', '/img/d28300e0-5845-4304-8f07-694a7dd36677.png', 0, 5, 1555, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(33, '大礼包', 1, 'wjz', '/img/346be572-7c7c-4fb0-8490-e8e104ebb57c.jpg', 0, 3, 198, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 1, 5, 0),
	(34, '华为手机', 1, 'wjz', '/img/23283ff0-96c3-47bc-a3fc-7640cf87f9a6.jpg', 0, 1, 2988, NULL, 1, 0, NULL, '商品质量完好，商家信誉保证，大小适中，造型精致，非常适合', 0, 5, 0);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;

-- 导出  表 2018011322.commodity_evaluation 结构
CREATE TABLE IF NOT EXISTS `commodity_evaluation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `evaluation` text,
  `commodity_id` int DEFAULT NULL,
  `score` tinyint DEFAULT NULL COMMENT '0~5星级',
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CommodityEvaluation_commodity_id_fk` (`commodity_id`),
  KEY `CommodityEvaluation_user_id_fk` (`user_id`),
  CONSTRAINT `CommodityEvaluation_commodity_id_fk` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`id`),
  CONSTRAINT `CommodityEvaluation_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='商品评价';

-- 正在导出表  2018011322.commodity_evaluation 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `commodity_evaluation` DISABLE KEYS */;
INSERT INTO `commodity_evaluation` (`id`, `evaluation`, `commodity_id`, `score`, `user_id`) VALUES
	(1, '这是一个测试，这个东西太好了把！', 2, 5, 1),
	(2, '这是一个测试，这个东西太好了把！', 2, 5, 1),
	(3, '这是一个测试，这个东西太好了把！', 2, 5, 1),
	(4, '一般', 2, 2, 1),
	(5, '商品有损坏', 2, 2, 1),
	(6, '一般', 2, 2, 1),
	(7, '一般', 2, 2, 1),
	(8, '44', 2, 2, 1),
	(9, '44', 2, 2, 1),
	(10, '一般', 2, 2, 1),
	(11, '一般', 2, 2, 1),
	(12, '一般', 2, 2, 1),
	(13, '真好', 10, 4, 1),
	(14, '一般', 4, 3, 1),
	(15, 'nice', 6, 5, 1),
	(16, 'nice', 2, 4, 1),
	(17, '1', 12, 3, 1),
	(18, '一般', 3, 3, 5);
/*!40000 ALTER TABLE `commodity_evaluation` ENABLE KEYS */;

-- 导出  表 2018011322.commodity_photo 结构
CREATE TABLE IF NOT EXISTS `commodity_photo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `photo` char(128) DEFAULT NULL,
  `commodity_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CommodityPhoto_commodity_id_fk` (`commodity_id`),
  CONSTRAINT `CommodityPhoto_commodity_id_fk` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='商品图片';

-- 正在导出表  2018011322.commodity_photo 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `commodity_photo` DISABLE KEYS */;
INSERT INTO `commodity_photo` (`id`, `photo`, `commodity_id`) VALUES
	(2, '/img/bd743b44-725a-4e66-89cb-2ba0fc38cdf5.jpg', 2),
	(3, '/img/84256d9e-f928-47f0-9c51-efa8009c2a77.jpg', 3),
	(4, '/img/296d6643-77d2-41b9-820c-40afaca09282.jpg', 4),
	(5, '/img/ec5cc38d-40c8-4e7b-8a47-ed2b84a71a1c.jpg', 5),
	(6, '/img/a01b4271-b789-4a06-8140-4b20b594cb67.jpg', 6),
	(7, '/img/26722340-8770-4871-bbb7-5942a0fb573a.jpg', 8),
	(8, '/img/a672cbab-3065-4058-b935-b8c8ba2f14b2.jpg', 9),
	(9, '/img/a61bcc22-220c-4c12-ac9d-9dcb6bd98f07.jpg', 13),
	(10, '/img/353c9569-5b85-44c6-9f56-66f835b43a12.jpg', 14),
	(14, '/img/bbfd17f3-bbc2-4e0e-b3ae-671296b2418b.png', 29),
	(15, '/img/2ce0870c-cecd-42da-be44-7f68496c9085.png', 30),
	(16, '/img/ce87de44-2fbd-47fb-b1d0-c9bfbf2ccc2d.png', 31),
	(17, '/img/d28300e0-5845-4304-8f07-694a7dd36677.png', 32),
	(18, '/img/346be572-7c7c-4fb0-8490-e8e104ebb57c.jpg', 33),
	(19, '/img/45a60bbb-d216-46b7-8e14-13a265d5078f.jpg', 34),
	(20, '/img/23283ff0-96c3-47bc-a3fc-7640cf87f9a6.jpg', 34);
/*!40000 ALTER TABLE `commodity_photo` ENABLE KEYS */;

-- 导出  表 2018011322.kind 结构
CREATE TABLE IF NOT EXISTS `kind` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kind_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='类别';

-- 正在导出表  2018011322.kind 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `kind` DISABLE KEYS */;
INSERT INTO `kind` (`id`, `name`) VALUES
	(5, '其他'),
	(2, '图书'),
	(1, '手机'),
	(4, '文具'),
	(3, '食品');
/*!40000 ALTER TABLE `kind` ENABLE KEYS */;

-- 导出  表 2018011322.middle_count 结构
CREATE TABLE IF NOT EXISTS `middle_count` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `creat_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `middle_count_order_id_fk` (`order_id`),
  KEY `middle_count_sell_id_fk` (`seller_id`),
  KEY `middle_count_user_id_fk` (`user_id`),
  CONSTRAINT `middle_count_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `middle_count_sell_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `sell` (`id`),
  CONSTRAINT `middle_count_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='中间用户';

-- 正在导出表  2018011322.middle_count 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `middle_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `middle_count` ENABLE KEYS */;

-- 导出  表 2018011322.orders 结构
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commodity_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `shop_id` int DEFAULT NULL,
  `creat_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT '0' COMMENT '0:待支付,1:取消支付,2:等待发货,3:运输中,4:已收货,5:退货审核中,6:已退货,7:拒绝退货',
  `can_return` tinyint DEFAULT '0' COMMENT '0:能退货,1:不能退货',
  PRIMARY KEY (`id`),
  KEY `Order_commodity_id_fk` (`commodity_id`),
  KEY `Order_shop_id_fk` (`shop_id`),
  KEY `Order_user_id_fk` (`user_id`),
  CONSTRAINT `Order_commodity_id_fk` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`id`),
  CONSTRAINT `Order_shop_id_fk` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `Order_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='订单';

-- 正在导出表  2018011322.orders 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `commodity_id`, `quantity`, `amount`, `user_id`, `shop_id`, `creat_time`, `end_time`, `status`, `can_return`) VALUES
	(1, 2, 3, 2697.3, 1, 1, '2021-05-21 21:41:46', '2021-05-23 13:16:04', 1, 0),
	(2, 2, 3, 2697.3, 1, 1, '2021-05-21 21:58:06', NULL, 3, 1),
	(3, 29, 4, 352, 1, 1, '2021-05-22 18:49:33', NULL, 2, 1),
	(4, 29, 4, 352, 1, 1, '2021-05-22 23:16:04', '2021-06-26 23:24:54', 8, 0),
	(5, 3, 1, 47.5, 1, 1, '2021-05-22 23:18:46', '2021-05-23 13:14:07', 8, 0),
	(6, 2, 3, 2397.6, 1, 1, '2021-05-25 19:58:19', NULL, 0, 0),
	(7, 29, 4, 352, 1, 1, '2021-05-25 19:58:19', NULL, 0, 0),
	(8, 2, 4, 3196.8, 1, 1, '2021-05-30 13:56:39', '2021-05-30 14:03:01', 8, 1),
	(9, 3, 1, 47.5, 5, 1, '2021-05-30 14:11:52', NULL, 0, 0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 导出  表 2018011322.rate 结构
CREATE TABLE IF NOT EXISTS `rate` (
  `id` int NOT NULL,
  `rate` double DEFAULT '0' COMMENT '百分比',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rate_level_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  2018011322.rate 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
INSERT INTO `rate` (`id`, `rate`) VALUES
	(1, 0.001),
	(2, 0.002),
	(3, 0.005),
	(4, 0.0075),
	(5, 0.01);
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;

-- 导出  表 2018011322.sell 结构
CREATE TABLE IF NOT EXISTS `sell` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `sex` tinyint DEFAULT NULL COMMENT '0:男,1:女',
  `card` char(16) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `status` tinyint DEFAULT '0' COMMENT '0:待审核,1:正常,2:审核失败',
  `wallet` double DEFAULT '0',
  `id_card_up` char(128) DEFAULT NULL,
  `id_card_back` char(128) DEFAULT NULL,
  `license` char(128) DEFAULT NULL,
  `level` tinyint DEFAULT '5' COMMENT 'level',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商家';

-- 正在导出表  2018011322.sell 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sell` DISABLE KEYS */;
INSERT INTO `sell` (`id`, `name`, `phone`, `sex`, `card`, `password`, `status`, `wallet`, `id_card_up`, `id_card_back`, `license`, `level`) VALUES
	(1, 'wjz', '18811025387', 0, '1111111111111111', 'wjz', 1, 6449.116, '/img/2b8748d7-a7cf-4b5d-b86a-4e7e32b81d23.png', '/img/b02f87b2-13ad-473d-bc44-27898fba786e.png', '/img/129df754-55e5-474e-bfe8-6d98c86285ba.jpg', 3);
/*!40000 ALTER TABLE `sell` ENABLE KEYS */;

-- 导出  表 2018011322.shop 结构
CREATE TABLE IF NOT EXISTS `shop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `seller_id` int DEFAULT NULL,
  `score` double DEFAULT '5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shop_name_uindex` (`name`),
  KEY `shop_sell_id_fk` (`seller_id`),
  CONSTRAINT `shop_sell_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `sell` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='店铺';

-- 正在导出表  2018011322.shop 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` (`id`, `name`, `seller_id`, `score`) VALUES
	(1, 'wjz', 1, 5);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;

-- 导出  表 2018011322.shopping_cart 结构
CREATE TABLE IF NOT EXISTS `shopping_cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commodity_id` int DEFAULT NULL,
  `quantity` int DEFAULT '1',
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ShoppingCart_commodity_id_fk` (`commodity_id`),
  KEY `ShoppingCart_user_id_fk` (`user_id`),
  CONSTRAINT `ShoppingCart_commodity_id_fk` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`id`),
  CONSTRAINT `ShoppingCart_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='购物车';

-- 正在导出表  2018011322.shopping_cart 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` (`id`, `commodity_id`, `quantity`, `user_id`) VALUES
	(13, 2, 4, 1),
	(14, 29, 3, 1),
	(15, 3, 1, 1),
	(16, 3, 1, 5);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;

-- 导出  表 2018011322.shop_evaluation 结构
CREATE TABLE IF NOT EXISTS `shop_evaluation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `evaluation` text,
  `shop_id` int DEFAULT NULL,
  `score` tinyint DEFAULT NULL COMMENT '0~5星级',
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shopEvaluation_shop_id_fk` (`shop_id`),
  KEY `shopEvaluation_user_id_fk` (`user_id`),
  CONSTRAINT `shopEvaluation_shop_id_fk` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `shopEvaluation_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺评价';

-- 正在导出表  2018011322.shop_evaluation 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `shop_evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_evaluation` ENABLE KEYS */;

-- 导出  表 2018011322.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `city` text,
  `sex` tinyint DEFAULT NULL COMMENT '0:男,1:女',
  `card` char(16) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `status` tinyint DEFAULT '0' COMMENT '0:待审核,1:正常,2:审核失败',
  `wallet` double DEFAULT '0',
  `points` double DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='普通用户';

-- 正在导出表  2018011322.user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `phone`, `email`, `city`, `sex`, `card`, `password`, `status`, `wallet`, `points`) VALUES
	(1, 'wjz', '18811025387', '1637011263@qq.com', '北京', 0, '1111111111111111', 'wjz', 1, 71.5, 6246.1),
	(3, 'wjz1', '18811025387', '1637051263@qq.com', '北京', 0, '1547896325416852', 'wjz', 3, 200, 0),
	(5, 'wjz123', '18811025387', '1637011245@qq.com', '北京', 0, '1547896325416852', 'wjz', 1, 0, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
