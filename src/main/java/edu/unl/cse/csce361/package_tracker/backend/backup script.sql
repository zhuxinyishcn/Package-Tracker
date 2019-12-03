-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 29, 2019 at 05:10 PM
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
CREATE DATABASE IF NOT EXISTS `pzhang` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pzhang`;

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `addressid` int(11) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Street` varchar(100) NOT NULL,
  `zip` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`addressid`, `City`, `Street`, `zip`) VALUES
(1, 'Lincoln', '1400 R St, Lincoln, NE 68588', '68508');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Table structure for table `Package`
--

CREATE TABLE `Package` (
  `PackageID` int(15) UNSIGNED ZEROFILL NOT NULL,
  `InboundID` int(50) NOT NULL,
  `OutboundID` int(50) NOT NULL,
  `CurrentLocation` int(11) NOT NULL DEFAULT '1',
  `Status` varchar(50) DEFAULT 'Requested'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Package`
--

INSERT INTO `Package` (`PackageID`, `InboundID`, `OutboundID`, `CurrentLocation`, `Status`) VALUES
(000000000000001, 1, 2, 10, 'Delivered');

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
-- Table structure for table `Warehouse`
--

CREATE TABLE `Warehouse` (
  `WarehouseID` int(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Geocode` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Warehouse`
--

INSERT INTO `Warehouse` (`WarehouseID`, `Name`, `Address`, `Geocode`) VALUES
(1, 'Lincoln Hub (O and 27ST)', '2701 O St, Lincoln, NE 68510', '50.813314,-96.682142'),
(2, 'O and 84', '100 N 84th St , Lincoln, NE 68505', '40.81386,-96.605443'),
(3, 'O and Nebraska', '8525 Andermatt Drive, Lincoln, NE 68526', '40.735401,-96.603936'),
(4, 'I80 and 154 st', '14541 Castlewood St, Waverly, NE 68462', '40.907467,-96.521302'),
(5, 'I80 and 250 st', '14599 250th St, Greenwood, NE 68366', '40.987032,-96.368809'),
(6, 'I80 and Nebraska Crossing', '21209 Nebraska Crossing Dr, Gretna, NE 68028', '41.096143,-96.253173'),
(7, 'I80 and 118th St', '6271 S 118th St, Omaha, NE 68137', '41.096143,-96.253173'),
(8, 'Omaha Hub', '3110 Farnam St, Omaha, NE 68131', '41.258655,-95.958501'),
(9, 'UserRequested', 'UserRequested', '0.000000,0.000000'),
(10, 'Delivered', 'Delivered', '0.000000,0.000000');

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
  ADD KEY `InboundID` (`InboundID`),
  ADD KEY `OutboundID` (`OutboundID`),
  ADD KEY `CurrentLocation` (`CurrentLocation`);

--
-- Indexes for table `Patron`
--
ALTER TABLE `Patron`
  ADD PRIMARY KEY (`PatronID`),
  ADD KEY `WarehouseID` (`WarehouseID`);

--
-- Indexes for table `Warehouse`
--
ALTER TABLE `Warehouse`
  ADD PRIMARY KEY (`WarehouseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Package`
--
ALTER TABLE `Package`
  MODIFY `PackageID` int(15) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Patron`
--
ALTER TABLE `Patron`
  MODIFY `PatronID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `Warehouse`
--
ALTER TABLE `Warehouse`
  MODIFY `WarehouseID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Package`
--
ALTER TABLE `Package`
  ADD CONSTRAINT `Package_ibfk_1` FOREIGN KEY (`InboundID`) REFERENCES `Patron` (`PatronID`),
  ADD CONSTRAINT `Package_ibfk_2` FOREIGN KEY (`OutboundID`) REFERENCES `Patron` (`PatronID`),
  ADD CONSTRAINT `Package_ibfk_3` FOREIGN KEY (`CurrentLocation`) REFERENCES `Warehouse` (`WarehouseID`);

--
-- Constraints for table `Patron`
--
ALTER TABLE `Patron`
  ADD CONSTRAINT `Patron_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `Warehouse` (`WarehouseID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
