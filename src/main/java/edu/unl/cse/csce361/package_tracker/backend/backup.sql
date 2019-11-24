-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-11-21 18:16:07
-- 服务器版本： 10.0.35-MariaDB
-- PHP 版本： 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `pzhang`
--

-- --------------------------------------------------------

--
-- 表的结构 `Address`
--

CREATE TABLE `Address` (
  `AddressID` int(50) NOT NULL,
  `Street` varchar(50) NOT NULL,
  `City` varchar(15) NOT NULL,
  `State` varchar(2) NOT NULL,
  `Type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `Address`
--

INSERT INTO `Address` (`AddressID`, `Street`, `City`, `State`, `Type`) VALUES
(1, '1400 R St', 'Lincoln', 'NE', 'Patron'),
(2, 'O and 27 ST', 'Lincoln', 'NE', 'Warehouse'),
(3, '1705 Arbor Dr', 'Lincoln', 'NE', 'patron');

-- --------------------------------------------------------

--
-- 表的结构 `Package`
--

CREATE TABLE `Package` (
  `PackageID` int(15) UNSIGNED ZEROFILL NOT NULL,
  `InboundID` int(50) NOT NULL,
  `OutboundID` int(50) NOT NULL,
  `CurrentLocation` int(11) NOT NULL,
  `Status` varchar(50) DEFAULT 'Unknown'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `Package`
--

INSERT INTO `Package` (`PackageID`, `InboundID`, `OutboundID`, `CurrentLocation`, `Status`) VALUES
(000000000000001, 1, 2, 2, 'In Transit');

-- --------------------------------------------------------

--
-- 表的结构 `Patron`
--

CREATE TABLE `Patron` (
  `PatronID` int(50) NOT NULL,
  `PatronLogin` varchar(15) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `AddressID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `Patron`
--

INSERT INTO `Patron` (`PatronID`, `PatronLogin`, `Name`, `AddressID`) VALUES
(1, 'UNL-C', 'University of Nebraska - Lincoln City Union', 1),
(2, 'UNL-E', 'University of Nebraska - Lincoln East Union', 3);

-- --------------------------------------------------------

--
-- 表的结构 `Warehouse`
--

CREATE TABLE `Warehouse` (
  `WarehouseID` int(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `AddressID` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `Warehouse`
--

INSERT INTO `Warehouse` (`WarehouseID`, `Name`, `AddressID`) VALUES
(1, 'O and 27ST', 2);

--
-- 转储表的索引
--

--
-- 表的索引 `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`AddressID`);

--
-- 表的索引 `Package`
--
ALTER TABLE `Package`
  ADD PRIMARY KEY (`PackageID`),
  ADD KEY `InboundID` (`InboundID`),
  ADD KEY `OutboundID` (`OutboundID`),
  ADD KEY `CurrentLocation` (`CurrentLocation`);

--
-- 表的索引 `Patron`
--
ALTER TABLE `Patron`
  ADD PRIMARY KEY (`PatronID`),
  ADD UNIQUE KEY `PatronLogin` (`PatronLogin`),
  ADD KEY `PatronAddressID` (`AddressID`);

--
-- 表的索引 `Warehouse`
--
ALTER TABLE `Warehouse`
  ADD PRIMARY KEY (`WarehouseID`),
  ADD KEY `AddressID` (`AddressID`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `Address`
--
ALTER TABLE `Address`
  MODIFY `AddressID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- 使用表AUTO_INCREMENT `Package`
--
ALTER TABLE `Package`
  MODIFY `PackageID` int(15) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `Patron`
--
ALTER TABLE `Patron`
  MODIFY `PatronID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `Warehouse`
--
ALTER TABLE `Warehouse`
  MODIFY `WarehouseID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 限制导出的表
--

--
-- 限制表 `Package`
--
ALTER TABLE `Package`
  ADD CONSTRAINT `CurrentLocation` FOREIGN KEY (`CurrentLocation`) REFERENCES `Address` (`AddressID`),
  ADD CONSTRAINT `InboundPartonID` FOREIGN KEY (`InboundID`) REFERENCES `Patron` (`PatronID`),
  ADD CONSTRAINT `OutBoundPartonId` FOREIGN KEY (`OutboundID`) REFERENCES `Patron` (`PatronID`);

--
-- 限制表 `Patron`
--
ALTER TABLE `Patron`
  ADD CONSTRAINT `PatronAddressID` FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`);

--
-- 限制表 `Warehouse`
--
ALTER TABLE `Warehouse`
  ADD CONSTRAINT `WarehouseAddressID` FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
