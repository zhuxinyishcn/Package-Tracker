-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 03, 2019 at 11:59 PM
-- Server version: 10.0.35-MariaDB
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pzhang`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `addressid` int(11) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Street` varchar(100) NOT NULL,
  `zip` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`addressid`, `City`, `Street`, `zip`) VALUES
(2, 'Lincoln', '1705 Arbor Dr, Lincoln', '68503'),
(5, 'Lincoln', '187 N 2nd Street', '68116'),
(7, 'Lincoln', '1400 R St, Lincoln, NE 68588', '68508'),
(10, 'Chicago', '122 East Jorgenson Street', '60613'),
(12, 'Omaha', '6001 Dodge Street', '68182'),
(15, 'Dallas', '8896 East 1st Ave.', '75001'),
(17, 'Omaha', '42nd and Emile', '68198'),
(20, 'Barton', '4235 Pepper Rd', '05822'),
(22, 'Omaha', '12916 Millard Airport Plaza', '68137'),
(27, 'Omaha', '4501 Abbott Dr', '68110'),
(32, 'Ashland', '16600 Quarry Oaks Dr Ashland', '68003'),
(35, 'Shanghai', '366 Fengyuan Way', '200092'),
(36, 'Lincoln', '1400 R St, Lincoln, NE 68588', '68508'),
(38, 'Lincoln', '1400 R St, Lincoln, NE 68588', '68508'),
(41, 'Lincoln', '1400 R St2, Lincoln, NE 68588', '68508'),
(44, 'Lincoln', '1400 R St, Lincoln, NE 68588', '68508'),
(46, 'Lincoln', '140024 R St, Lincoln', '68508'),
(48, 'Lincoln', '140024 R St, Lincoln', '68508'),
(50, 'omaha', '1924 st ', '68905'),
(52, 'da', '19823', '1917'),
(54, 'dsf', 'sf', 'sdf');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(55),
(55),
(55),
(55),
(55);

-- --------------------------------------------------------

--
-- Table structure for table `Package`
--

CREATE TABLE `Package` (
  `PackageID` int(11) NOT NULL,
  `currentLocation` int(11) DEFAULT NULL,
  `priorityID` int(11) NOT NULL,
  `shippingTime` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `trackingNumber` binary(40) DEFAULT NULL,
  `receiver` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `PackageSet` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Packages`
--

CREATE TABLE `Packages` (
  `PackageID` int(11) NOT NULL,
  `currentLocation` int(11) DEFAULT NULL,
  `priorityID` int(11) NOT NULL,
  `shippingTime` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `trackingNumber` varchar(40) DEFAULT NULL,
  `receiver` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `PackageSet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Packages`
--

INSERT INTO `Packages` (`PackageID`, `currentLocation`, `priorityID`, `shippingTime`, `status`, `trackingNumber`, `receiver`, `sender`, `PackageSet`) VALUES
(3, 0, 0, '2019/12/02 21:19:05', 'Shipping', '8bdf111f-1b84-496b-b806-3a26f721a91b', 4, 1, 1),
(8, 6, 0, '2019/12/02 21:19:34', 'Shipping', '36a7715d-9bc3-41bb-9554-ffbdc5f9e2b7', 9, 6, 6),
(13, 1, 4, '2019/12/02 21:20:04', 'just Test it! again', '40ac7974-1978-4e28-9423-6dab8e8f189c', 14, 11, 11),
(33, 1, 0, '2019/12/02 21:22:22', 'Shipping', 'e560889c-ca9b-4bc2-a9c1-d4f3f3d2406d', 34, 31, 31),
(39, 1, 0, '2019/12/03 20:51:34', 'Shipping', '5157ab40-ec9c-4ac4-a356-631f1969e5eb', 40, 37, 37);

-- --------------------------------------------------------

--
-- Table structure for table `Patron`
--

CREATE TABLE `Patron` (
  `PatronID` int(50) NOT NULL,
  `PatronLogin` varchar(15) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Address` varchar(255) NOT NULL,
  `WarehouseID` int(11) DEFAULT NULL,
  `Geocoding` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Patron`
--

INSERT INTO `Patron` (`PatronID`, `PatronLogin`, `Name`, `Address`, `WarehouseID`, `Geocoding`) VALUES
(1, 'UNL', 'University of Nebraska - Lincoln City Union', '1400 R St, Lincoln, NE 68588', 1, '40.817663,-96.700037'),
(2, 'UNL-E', 'University of Nebraska - Lincoln East Union', '1705 Arbor Dr, Lincoln, NE 68503', 1, '40.830192,-96.667434'),
(3, 'UNO', 'University of Nebraska - Omaha', '6001 Dodge Street, Omaha, NE, 68182', 8, '41.258258,-96.011684'),
(4, 'UNMC', 'University of Nebraska Medical Center', '42nd and Emile, Omaha, NE 68198', 8, '41.25435,-95.97573'),
(5, 'MIQ', 'Millard Airport', '12916 Millard Airport Plaza, Omaha, NE 68137', 7, '41.19515,-96.11372'),
(6, 'OMA', 'Millard Airport', '4501 Abbott Dr, Omaha, NE 68110', 8, '41.30187,-95.89374'),
(7, 'QOGC', 'Quarry Oaks Golf Club', '16600 Quarry Oaks Dr, Ashland, NE 68003', 6, '41.00975,-96.27671');

-- --------------------------------------------------------

--
-- Table structure for table `Receiver`
--

CREATE TABLE `Receiver` (
  `receiverid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Receiver`
--

INSERT INTO `Receiver` (`receiverid`, `name`, `address`) VALUES
(4, 'Bryan', 5),
(9, 'dddsx258', 10),
(14, 'system Admin!', 15),
(19, 'James', 20),
(34, 'Bicker', 32),
(40, 'dddsx258', 41),
(45, 'test123', 46),
(47, 'test123', 48),
(51, 'panda', 52);

-- --------------------------------------------------------

--
-- Table structure for table `Sender`
--

CREATE TABLE `Sender` (
  `SenderID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `address` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Sender`
--

INSERT INTO `Sender` (`SenderID`, `name`, `userName`, `address`) VALUES
(1, 'University of Nebraska - Lincoln East Union', 'unl-east', 2),
(6, 'UNL', 'sxc258', 7),
(11, 'system Admin:)!', 'uno', 12),
(16, 'University of Nebraska Medical Center', 'medical center', 17),
(21, 'Millard Airport', 'millard airport', 22),
(26, 'Millard Airport East', 'millard airport-east', 27),
(31, 'Quarry Oaks Golf Club', 'golf for ever', 32),
(37, 'test', 'sxc258', 38),
(43, 'admin', 'Admin', 44),
(49, 'xhu', 'das', 50),
(53, 'er', 'df', 54);

-- --------------------------------------------------------

--
-- Table structure for table `Warehouse`
--

CREATE TABLE `Warehouse` (
  `warehouseID` int(11) NOT NULL,
  `Latitude` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `Longitude` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`addressid`);

--
-- Indexes for table `Package`
--
ALTER TABLE `Package`
  ADD PRIMARY KEY (`PackageID`),
  ADD UNIQUE KEY `UK_dv7ybv8omx2s9gpnyte98y2os` (`trackingNumber`),
  ADD UNIQUE KEY `UKdv7ybv8omx2s9gpnyte98y2os` (`trackingNumber`),
  ADD KEY `FKi379w3bmf4sjoecpx47g10nal` (`receiver`),
  ADD KEY `FK7a3lijdeudq2dmtn3as1gn1ys` (`sender`),
  ADD KEY `FK4h8kbwhgopsef0hdhiogp3sir` (`PackageSet`);

--
-- Indexes for table `Packages`
--
ALTER TABLE `Packages`
  ADD PRIMARY KEY (`PackageID`),
  ADD UNIQUE KEY `UK_g5fistly507x2q81ho998vrj8` (`trackingNumber`),
  ADD UNIQUE KEY `UKg5fistly507x2q81ho998vrj8` (`trackingNumber`),
  ADD KEY `FKhg9g1mmcmrtfhfbxbmhn3uqpb` (`receiver`),
  ADD KEY `FKnevvw7h9qrgi9wa9x4gh0s6ro` (`sender`),
  ADD KEY `FKpblun7iiad7n3mawp835mno7j` (`PackageSet`);

--
-- Indexes for table `Patron`
--
ALTER TABLE `Patron`
  ADD PRIMARY KEY (`PatronID`),
  ADD KEY `WarehouseID` (`WarehouseID`);

--
-- Indexes for table `Receiver`
--
ALTER TABLE `Receiver`
  ADD PRIMARY KEY (`receiverid`),
  ADD KEY `FK45a22m4p5p052c3bfx481076x` (`address`);

--
-- Indexes for table `Sender`
--
ALTER TABLE `Sender`
  ADD PRIMARY KEY (`SenderID`),
  ADD KEY `FKd33j1wg56l8bno47quh68lad9` (`address`);

--
-- Indexes for table `Warehouse`
--
ALTER TABLE `Warehouse`
  ADD PRIMARY KEY (`warehouseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Patron`
--
ALTER TABLE `Patron`
  MODIFY `PatronID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Packages`
--
ALTER TABLE `Packages`
  ADD CONSTRAINT `FKhg9g1mmcmrtfhfbxbmhn3uqpb` FOREIGN KEY (`receiver`) REFERENCES `Receiver` (`receiverid`),
  ADD CONSTRAINT `FKnevvw7h9qrgi9wa9x4gh0s6ro` FOREIGN KEY (`sender`) REFERENCES `Sender` (`SenderID`),
  ADD CONSTRAINT `FKpblun7iiad7n3mawp835mno7j` FOREIGN KEY (`PackageSet`) REFERENCES `Sender` (`SenderID`);

--
-- Constraints for table `Patron`
--
ALTER TABLE `Patron`
  ADD CONSTRAINT `Patron_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `Warehouse` (`WarehouseID`);

--
-- Constraints for table `Receiver`
--
ALTER TABLE `Receiver`
  ADD CONSTRAINT `FK45a22m4p5p052c3bfx481076x` FOREIGN KEY (`address`) REFERENCES `Address` (`addressid`);

--
-- Constraints for table `Sender`
--
ALTER TABLE `Sender`
  ADD CONSTRAINT `FKd33j1wg56l8bno47quh68lad9` FOREIGN KEY (`address`) REFERENCES `Address` (`addressid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
