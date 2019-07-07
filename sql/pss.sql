/*
 Navicat MySQL Data Transfer

 Source Server         : data
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : pss

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/07/2019 11:20:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cargo
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `CargoId` int(8) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `CargoName` varchar(30) NOT NULL COMMENT '商品名称',
  `SafetyStock` double(10,2) NOT NULL COMMENT '安全存量',
  `Unit` varchar(10) NOT NULL COMMENT '单位',
  `BuyPrice` decimal(10,2) NOT NULL COMMENT '建议采购价',
  `SellPrice` decimal(10,2) NOT NULL COMMENT '建议销售价',
  PRIMARY KEY (`CargoId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cargo
-- ----------------------------
BEGIN;
INSERT INTO `cargo` VALUES (1, '桃子', 50.00, 'kg', 2.40, 3.50);
INSERT INTO `cargo` VALUES (2, '橘子', 25.00, 'kg', 1.50, 2.00);
INSERT INTO `cargo` VALUES (3, '草莓', 30.00, 'kg', 3.00, 4.00);
INSERT INTO `cargo` VALUES (4, '橙子', 35.00, 'kg', 2.50, 3.00);
INSERT INTO `cargo` VALUES (5, '木瓜', 20.00, 'kg', 2.00, 3.00);
INSERT INTO `cargo` VALUES (6, '西瓜', 30.00, 'kg', 2.50, 3.50);
INSERT INTO `cargo` VALUES (7, '番茄', 30.00, 'kg', 2.50, 3.50);
INSERT INTO `cargo` VALUES (8, '胡萝卜', 30.00, 'kg', 0.70, 1.50);
INSERT INTO `cargo` VALUES (10, '空心菜', 20.00, 'kg', 2.00, 3.00);
INSERT INTO `cargo` VALUES (11, '椰子', 20.00, '个', 10.00, 14.00);
INSERT INTO `cargo` VALUES (13, '山竹', 30.00, 'kg', 15.00, 20.00);
INSERT INTO `cargo` VALUES (14, '榴莲', 30.00, '个', 100.00, 300.00);
COMMIT;

-- ----------------------------
-- Table structure for cuscontact
-- ----------------------------
DROP TABLE IF EXISTS `cuscontact`;
CREATE TABLE `cuscontact` (
  `CusId` int(8) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `CusName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名称',
  `CusTel` longtext NOT NULL COMMENT '客户电话',
  PRIMARY KEY (`CusId`) USING BTREE,
  KEY `CusName` (`CusName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cuscontact
-- ----------------------------
BEGIN;
INSERT INTO `cuscontact` VALUES (1, '张三三', '13712345678');
INSERT INTO `cuscontact` VALUES (2, '李四国', '13760345678');
INSERT INTO `cuscontact` VALUES (3, '王五奎', '13750345678');
INSERT INTO `cuscontact` VALUES (4, '林青青', '13755345678');
INSERT INTO `cuscontact` VALUES (5, '王令飓', '13712345660');
INSERT INTO `cuscontact` VALUES (6, '林峰', '13756345678');
INSERT INTO `cuscontact` VALUES (7, '陆白鹤', '13759345678');
INSERT INTO `cuscontact` VALUES (8, '邱顺平', '13030303030');
COMMIT;

-- ----------------------------
-- Table structure for cusoper
-- ----------------------------
DROP TABLE IF EXISTS `cusoper`;
CREATE TABLE `cusoper` (
  `OperId` int(8) NOT NULL AUTO_INCREMENT COMMENT '客户编号，外键',
  `CusId` int(8) NOT NULL COMMENT '客户名称，外键',
  `CusTel` varchar(20) NOT NULL COMMENT '客户电话',
  `CusAddr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户地址',
  `StaffId` int(8) NOT NULL COMMENT '负责人，外键，员工',
  `FaxNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传真',
  `ComName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  PRIMARY KEY (`OperId`) USING BTREE,
  KEY `CusName` (`CusId`) USING BTREE,
  KEY `StaffId` (`StaffId`),
  CONSTRAINT `cusoper_ibfk_1` FOREIGN KEY (`CusId`) REFERENCES `cuscontact` (`CusId`),
  CONSTRAINT `cusoper_ibfk_2` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cusoper
-- ----------------------------
BEGIN;
INSERT INTO `cusoper` VALUES (3, 1, '13030303030', '福州软件园1号楼101', 2, '6666667', '福建农林大学');
INSERT INTO `cusoper` VALUES (4, 2, '1505050505', '萨法擦', 3, '77777', '福建农林大学');
INSERT INTO `cusoper` VALUES (5, 2, '13413111111', 'asdasa', 1, 'q1231231', 'asda');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `OrderId` int(20) NOT NULL AUTO_INCREMENT COMMENT '交易单号',
  `StaffId` int(8) NOT NULL COMMENT '售货员，外键，员工',
  `CusId` int(8) NOT NULL,
  `SentAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '送货地址',
  `InvNum` longtext NOT NULL COMMENT '发票号',
  `Money` decimal(30,2) NOT NULL COMMENT '总交易金额',
  `OrderDate` date DEFAULT NULL COMMENT '交易日期',
  PRIMARY KEY (`OrderId`) USING BTREE,
  KEY `StaffId` (`StaffId`),
  KEY `CusId` (`CusId`),
  KEY `SentAdd` (`SentAdd`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffId`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`CusId`) REFERENCES `cuscontact` (`CusId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (1, 3, 1, '福建农林大学', '3613345679', 300.00, '2019-06-23');
INSERT INTO `order` VALUES (2, 2, 1, '福建农林大学', '3711345679', 350.00, '2019-06-24');
INSERT INTO `order` VALUES (3, 3, 1, '福建农林大学', '3712345678', 200.00, '2019-06-24');
INSERT INTO `order` VALUES (4, 2, 1, '福州软件园1号楼101', '3722345678', 300.00, '2019-06-24');
INSERT INTO `order` VALUES (5, 5, 1, '福建农林大学1', '3723345678', 220.00, '2019-06-25');
COMMIT;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `PurId` int(8) NOT NULL AUTO_INCREMENT COMMENT '采购单单号',
  `SupId` int(8) NOT NULL COMMENT '供应商Id',
  `Date` date DEFAULT NULL COMMENT '采购日期',
  `Total` decimal(10,0) DEFAULT NULL COMMENT '总金额',
  `CargoId` int(8) NOT NULL COMMENT '商品Id',
  `Number` int(8) DEFAULT NULL COMMENT '数量',
  `BuyPrice` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `Amount` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`PurId`),
  KEY `CargoId` (`CargoId`),
  KEY `purchase_ibfk_1` (`SupId`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`SupId`) REFERENCES `supplier` (`SupId`),
  CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`CargoId`) REFERENCES `cargo` (`CargoId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of purchase
-- ----------------------------
BEGIN;
INSERT INTO `purchase` VALUES (1, 2, '2019-06-24', 63, 1, 21, 3.00, 30, 1);
INSERT INTO `purchase` VALUES (2, 1, '2019-06-23', 2020, 1, 101, 20.00, 200, 1);
INSERT INTO `purchase` VALUES (3, 1, '2019-06-27', 44, 1, 2, 22.00, NULL, 1);
INSERT INTO `purchase` VALUES (4, 2, '2019-06-27', 40, 1, 20, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (5, 1, '2019-06-27', 12, 2, 3, 4.00, NULL, 0);
INSERT INTO `purchase` VALUES (6, 1, '2019-06-28', 20, 2, 10, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (7, 3, '2019-06-28', 30, 3, 10, 3.00, NULL, 0);
INSERT INTO `purchase` VALUES (8, 1, '2019-06-28', 23, 6, 10, 2.30, NULL, 0);
INSERT INTO `purchase` VALUES (9, 2, '2019-06-28', 40, 2, 20, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (10, 1, '2019-06-27', 20, 10, 10, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (29, 1, '2019-06-30', 2, 1, 1, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (30, 1, '2019-06-30', 4, 1, 2, 2.00, NULL, 0);
INSERT INTO `purchase` VALUES (31, 1, '2019-07-01', 40, 2, 10, 4.00, NULL, 0);
INSERT INTO `purchase` VALUES (32, 2, '2019-07-01', 120, 1, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (33, 2, '2019-07-01', 120, 2, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (34, 2, '2019-07-01', 120, 2, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (35, 2, '2019-07-02', 120, 1, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (36, 10, '2019-07-02', 24, 14, 10, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (37, 4, '2019-07-02', 24, 13, 10, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (38, 6, '2019-07-02', 120, 14, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (39, 1, '2019-07-02', 120, 1, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (40, 2, '2019-07-02', 12, 4, 5, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (41, 2, '2019-07-02', 120, 4, 50, 2.40, NULL, 0);
INSERT INTO `purchase` VALUES (42, 1, '2019-07-03', 1, 6, 1, 1.00, NULL, 0);
INSERT INTO `purchase` VALUES (43, 2, '2019-07-03', 1, 6, 1, 1.00, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for returnnote
-- ----------------------------
DROP TABLE IF EXISTS `returnnote`;
CREATE TABLE `returnnote` (
  `ReturnId` int(8) NOT NULL AUTO_INCREMENT COMMENT '退货单单号，外键',
  `PurId` int(8) DEFAULT NULL COMMENT '采购单号，外键',
  `SupId` int(8) DEFAULT NULL COMMENT '供应商标号，外键',
  `CargoId` int(8) DEFAULT NULL COMMENT '商品编号，外键',
  `Number` int(8) DEFAULT NULL COMMENT '商品数量',
  `ReturnPrice` decimal(10,2) DEFAULT NULL COMMENT '退货单价',
  `Remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `StaffId` int(8) DEFAULT NULL COMMENT '经办人，外键',
  `ReturnDate` date DEFAULT NULL COMMENT '退货日期',
  `total` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`ReturnId`) USING BTREE,
  KEY `SupId` (`SupId`),
  KEY `CargoId` (`CargoId`),
  KEY `StaffId` (`StaffId`),
  KEY `PurId` (`PurId`),
  CONSTRAINT `returnnote_ibfk_1` FOREIGN KEY (`SupId`) REFERENCES `supplier` (`SupId`),
  CONSTRAINT `returnnote_ibfk_2` FOREIGN KEY (`CargoId`) REFERENCES `cargo` (`CargoId`),
  CONSTRAINT `returnnote_ibfk_3` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffId`),
  CONSTRAINT `returnnote_ibfk_4` FOREIGN KEY (`PurId`) REFERENCES `purchase` (`PurId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of returnnote
-- ----------------------------
BEGIN;
INSERT INTO `returnnote` VALUES (7, 1, 2, 1, 2, 2.00, NULL, 3, '2019-07-03', 4.00);
INSERT INTO `returnnote` VALUES (9, 2, 1, 1, 1, 1.00, '1', 1, '2019-07-04', 1.00);
INSERT INTO `returnnote` VALUES (10, 3, 1, 1, 1, 1.00, '1', 1, '2019-07-04', 1.00);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `RoleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `RoleName` varchar(10) DEFAULT NULL COMMENT '角色信息',
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理');
INSERT INTO `role` VALUES (2, '未通过');
INSERT INTO `role` VALUES (3, '普通用户');
COMMIT;

-- ----------------------------
-- Table structure for saleout
-- ----------------------------
DROP TABLE IF EXISTS `saleout`;
CREATE TABLE `saleout` (
  `SaleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '销售编号',
  `CargoId` int(8) NOT NULL COMMENT '商品编号',
  `Number` int(8) NOT NULL COMMENT '数量',
  `Price` decimal(10,2) NOT NULL COMMENT '单价',
  `StaffId` int(8) NOT NULL COMMENT '负责人（员工Id)（外键',
  `CusId` int(8) NOT NULL COMMENT '客户编号',
  `OutDate` date NOT NULL COMMENT '出库日期',
  `Total` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `Status` int(2) DEFAULT '0' COMMENT '状态，默认0',
  PRIMARY KEY (`SaleId`) USING BTREE,
  KEY `CargoId` (`CargoId`),
  KEY `StaffId` (`StaffId`),
  KEY `CusId` (`CusId`),
  CONSTRAINT `saleout_ibfk_1` FOREIGN KEY (`CargoId`) REFERENCES `cargo` (`CargoId`),
  CONSTRAINT `saleout_ibfk_2` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`StaffId`),
  CONSTRAINT `saleout_ibfk_3` FOREIGN KEY (`CusId`) REFERENCES `cuscontact` (`CusId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of saleout
-- ----------------------------
BEGIN;
INSERT INTO `saleout` VALUES (1, 3, 0, 20.00, 1, 1, '2019-06-24', 100.00, 1);
INSERT INTO `saleout` VALUES (2, 5, 23, 2.00, 4, 1, '2019-06-24', 46.00, 1);
INSERT INTO `saleout` VALUES (3, 1, 50, 11.00, 1, 2, '2019-07-02', 550.00, 0);
INSERT INTO `saleout` VALUES (4, 3, 50, 11.00, 1, 2, '2019-07-02', 550.00, 0);
INSERT INTO `saleout` VALUES (5, 3, 50, 11.00, 1, 1, '2019-07-02', 550.00, 0);
INSERT INTO `saleout` VALUES (6, 3, 10, 11.00, 1, 1, '2019-07-02', 110.00, 0);
INSERT INTO `saleout` VALUES (7, 13, 50, 11.00, 1, 1, '2019-07-02', 550.00, 0);
INSERT INTO `saleout` VALUES (8, 13, 50, 11.00, 1, 1, '2019-07-01', 550.00, 0);
INSERT INTO `saleout` VALUES (9, 3, 50, 20.00, 1, 1, '2019-07-02', 550.00, 0);
INSERT INTO `saleout` VALUES (10, 1, 3, 11.00, 1, 1, '2019-07-03', 33.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for salereturn
-- ----------------------------
DROP TABLE IF EXISTS `salereturn`;
CREATE TABLE `salereturn` (
  `SaleId` int(8) NOT NULL COMMENT '销售编号',
  `ResaleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '退货单编号',
  `CargoId` int(8) NOT NULL COMMENT '商品编号',
  `Number` int(8) DEFAULT NULL COMMENT '数量',
  `Price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `Remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `ReturnDate` datetime DEFAULT NULL COMMENT '退货日期',
  `Total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ResaleId`) USING BTREE,
  KEY `SaleId` (`SaleId`),
  KEY `CargoId` (`CargoId`),
  CONSTRAINT `salereturn_ibfk_2` FOREIGN KEY (`CargoId`) REFERENCES `cargo` (`CargoId`),
  CONSTRAINT `salereturn_ibfk_3` FOREIGN KEY (`SaleId`) REFERENCES `saleout` (`SaleId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of salereturn
-- ----------------------------
BEGIN;
INSERT INTO `salereturn` VALUES (1, 8, 3, 2, 20.00, '', '2019-07-04 00:00:00', 40.00);
INSERT INTO `salereturn` VALUES (1, 9, 3, 1, 1.00, '1', '2019-07-04 00:00:00', 1.00);
INSERT INTO `salereturn` VALUES (2, 10, 5, 1, 1.00, '2', '2019-07-04 00:00:00', 1.00);
INSERT INTO `salereturn` VALUES (2, 11, 5, 2, 2.00, '1', '2019-07-04 00:00:00', 4.00);
INSERT INTO `salereturn` VALUES (1, 12, 3, 3, 1.00, '1', '2019-07-04 00:00:00', 3.00);
COMMIT;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `StaffId` int(8) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `CName` varchar(10) NOT NULL COMMENT '中文姓名',
  `EName` varchar(60) DEFAULT NULL COMMENT '英文姓名',
  `Phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `MPhone` varchar(20) NOT NULL COMMENT '移动电话',
  `Email` varchar(32) NOT NULL COMMENT '电子邮箱',
  `Address` varchar(100) DEFAULT NULL COMMENT '联络地址',
  PRIMARY KEY (`StaffId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
BEGIN;
INSERT INTO `staff` VALUES (1, '张一', 'Amy', '2020221', '13712312345', 'zhangyi@163.com', '福州软件园1号楼101');
INSERT INTO `staff` VALUES (2, '张二', 'Bom', '2020222', '13712312346', 'zhangtwo@163.com', '福州软件园1号楼102');
INSERT INTO `staff` VALUES (3, '张三', 'Candy', '2020223', '13712312347', 'zhangsan@163.com', '福州软件园1号楼103');
INSERT INTO `staff` VALUES (4, '林一', 'Dobby', '2020231', '13712312351', 'linyi@163.com', '福州软件园2号楼101');
INSERT INTO `staff` VALUES (5, '林二', 'Easy', '2020232', '13712312352', 'lin2@163.com', '福州软件园2号楼102');
INSERT INTO `staff` VALUES (6, '林三', 'Funny', '2020233', '13712312353', 'lin3@163.com', '福州软件园2号楼103');
COMMIT;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `StockId` int(8) NOT NULL AUTO_INCREMENT COMMENT '库存编号',
  `Number` int(8) DEFAULT NULL COMMENT '当前数量',
  `CargoId` int(8) NOT NULL COMMENT '商品名称',
  `BuyDate` date DEFAULT NULL COMMENT '最后进货日期',
  `SellDate` date DEFAULT NULL COMMENT '最后送货日期',
  PRIMARY KEY (`StockId`) USING BTREE,
  KEY `CargoId` (`CargoId`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`CargoId`) REFERENCES `cargo` (`CargoId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of stock
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES (1, 10, 3, '2019-07-02', '2019-07-03');
INSERT INTO `stock` VALUES (2, 100, 2, '2019-07-01', '2019-07-01');
INSERT INTO `stock` VALUES (7, 116, 1, '2019-07-02', '2019-07-03');
INSERT INTO `stock` VALUES (8, 100, 4, '2019-07-02', '2019-06-01');
INSERT INTO `stock` VALUES (9, 113, 5, '2019-06-12', '2019-06-19');
INSERT INTO `stock` VALUES (10, 120, 6, '2019-07-03', '2019-07-01');
INSERT INTO `stock` VALUES (11, 200, 7, '2019-06-05', '2019-06-17');
INSERT INTO `stock` VALUES (12, 20, 8, '2019-06-11', '2019-06-26');
INSERT INTO `stock` VALUES (13, 50, 10, '2019-06-11', '2019-07-15');
INSERT INTO `stock` VALUES (14, 100, 11, '2019-07-01', '2019-07-01');
INSERT INTO `stock` VALUES (15, 200, 13, '2019-06-10', '2019-06-27');
INSERT INTO `stock` VALUES (16, 175, 14, '2019-06-24', '2019-06-25');
COMMIT;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `SupId` int(8) NOT NULL AUTO_INCREMENT COMMENT '供应商Id',
  `Name` varchar(255) NOT NULL COMMENT '供应商名',
  `Tel` varchar(255) NOT NULL COMMENT '供应商联系电话',
  `Address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`SupId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
BEGIN;
INSERT INTO `supplier` VALUES (1, '新日新水果批发', '13712345678', '上街镇xx路xx');
INSERT INTO `supplier` VALUES (2, '兴旺水果', '13612345677', '福建农林大学创新楼205店面');
INSERT INTO `supplier` VALUES (3, '龙眼批发', '13612312345', '上下店路淮安小区203店面');
INSERT INTO `supplier` VALUES (4, '欣鑫水果', '13312398765', '福建农林大学');
INSERT INTO `supplier` VALUES (5, '东北水果批发', '13634565432', '建设路101号店面');
INSERT INTO `supplier` VALUES (6, '小东蔬菜批发', '15860123322', '福建农林大学宝玲楼401店面');
INSERT INTO `supplier` VALUES (7, '海蓝蔬菜批发', '15830123322', '六一北路2003号店面');
INSERT INTO `supplier` VALUES (8, '七七蔬菜', '15830123323', '建新镇洪山桥附近');
INSERT INTO `supplier` VALUES (9, '湖北小龙水果店', '13607778888', '五一南路203店面');
INSERT INTO `supplier` VALUES (10, '东北老陈蔬果批发', '18865012345', '北京路25号店面');
INSERT INTO `supplier` VALUES (11, '老农人蔬果', '15588812312', '青年北路32号');
INSERT INTO `supplier` VALUES (14, '感奥术大师多', '123123', '231');
INSERT INTO `supplier` VALUES (15, '123', '11', '11');
INSERT INTO `supplier` VALUES (16, '阿达', 'as', '11');
INSERT INTO `supplier` VALUES (17, '邱2', 'weq', 'qwe');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `UserName` varchar(20) NOT NULL COMMENT '用户名',
  `UserPass` varchar(20) NOT NULL COMMENT '密码',
  `RoleId` int(8) DEFAULT '2' COMMENT '角色Id',
  PRIMARY KEY (`UserId`),
  KEY `role` (`RoleId`),
  CONSTRAINT `role` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '123', 1);
INSERT INTO `user` VALUES (2, 'user1', '123', 2);
INSERT INTO `user` VALUES (3, 'user2', '123', 3);
INSERT INTO `user` VALUES (4, '1', '1', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
