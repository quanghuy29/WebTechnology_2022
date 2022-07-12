-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 19, 2022 at 10:11 AM
-- Server version: 8.0.29
-- PHP Version: 7.2.24-0ubuntu0.18.04.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlyktx`
--

-- --------------------------------------------------------

--
-- Table structure for table `Manager`
--

CREATE TABLE `Manager` (
  `ManagerId` bigint NOT NULL,
  `UserId` bigint NOT NULL,
  `Fullname` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Email` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `YearOfService` int NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `OrderK`
--

CREATE TABLE `OrderK` (
  `OrderId` int NOT NULL,
  `TypeId` int NOT NULL,
  `StudentId` int NOT NULL,
  `Topic` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Content` text CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `SentDate` date NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `OrderType`
--

CREATE TABLE `OrderType` (
  `TypeId` int NOT NULL,
  `TypeName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Role`
--

CREATE TABLE `Role` (
  `RoleId` bigint NOT NULL,
  `RoleName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `RoleCode` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Role`
--

INSERT INTO `Role` (`RoleId`, `RoleName`, `RoleCode`) VALUES
(1, 'Quản lý sinh viên', 'QLSV'),
(2, 'Quản lý trưởng', 'QLT'),
(3, 'Quản lý phòng ở', 'QLPO'),
(4, 'Kế toán', 'KT');

-- --------------------------------------------------------

--
-- Table structure for table `Room`
--

CREATE TABLE `Room` (
  `RoomId` int NOT NULL,
  `RoomCode` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `MaxSlots` int NOT NULL,
  `AvailableSlots` int NOT NULL,
  `RoomState` int NOT NULL,
  `RoomPaymentState` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8  ;

-- --------------------------------------------------------

--
-- Table structure for table `RoomPayment`
--

CREATE TABLE `RoomPayment` (
  `PaymentId` int NOT NULL,
  `StudentRoomId` int NOT NULL,
  `PaymentMoney` int NOT NULL,
  `PaymentDate` date NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE `Student` (
  `StudentId` int NOT NULL,
  `StudentCode` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Fullname` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `YearSchool` int NOT NULL,
  `Class` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `StudentRoom`
--

CREATE TABLE `StudentRoom` (
  `StudentRoomId` int NOT NULL,
  `StudentId` int NOT NULL,
  `RoomId` int NOT NULL,
  `PayMoneyRemain` float NOT NULL,
  `PaymentState` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8  ;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `UserId` int NOT NULL,
  `RoleId` bigint NOT NULL,
  `Username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `UtilityBill`
--

CREATE TABLE `UtilityBill` (
  `BillId` int NOT NULL,
  `RoomId` int NOT NULL,
  `Money` int NOT NULL,
  `PaymentDate` date NOT NULL,
  `State` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Manager`
--
ALTER TABLE `Manager`
  ADD PRIMARY KEY (`ManagerId`),
  ADD UNIQUE KEY `UserId` (`UserId`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- Indexes for table `OrderK`
--
ALTER TABLE `OrderK`
  ADD KEY `FK_OrderK_OderType_TypeId` (`TypeId`),
  ADD KEY `FK_OrderK_Student_StudentId` (`StudentId`);

--
-- Indexes for table `OrderType`
--
ALTER TABLE `OrderType`
  ADD PRIMARY KEY (`TypeId`);

--
-- Indexes for table `Role`
--
ALTER TABLE `Role`
  ADD PRIMARY KEY (`RoleId`),
  ADD UNIQUE KEY `RoleCode` (`RoleCode`);

--
-- Indexes for table `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`RoomId`),
  ADD UNIQUE KEY `RoomCode` (`RoomCode`);

--
-- Indexes for table `RoomPayment`
--
ALTER TABLE `RoomPayment`
  ADD PRIMARY KEY (`PaymentId`),
  ADD KEY `FK_RoomPayment_StudentRoom_StudentRoomId` (`StudentRoomId`);

--
-- Indexes for table `Student`
--
ALTER TABLE `Student`
  ADD PRIMARY KEY (`StudentId`),
  ADD UNIQUE KEY `StudentCode` (`StudentCode`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- Indexes for table `StudentRoom`
--
ALTER TABLE `StudentRoom`
  ADD PRIMARY KEY (`StudentRoomId`),
  ADD UNIQUE KEY `RoomId` (`RoomId`),
  ADD UNIQUE KEY `StudentId` (`StudentId`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `FK_User_Role` (`RoleId`);

--
-- Indexes for table `UtilityBill`
--
ALTER TABLE `UtilityBill`
  ADD KEY `FK_UtilityBill_Room_RoomId` (`RoomId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Manager`
--
ALTER TABLE `Manager`
  MODIFY `ManagerId` bigint NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `OrderType`
--
ALTER TABLE `OrderType`
  MODIFY `TypeId` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Role`
--
ALTER TABLE `Role`
  MODIFY `RoleId` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Room`
--
ALTER TABLE `Room`
  MODIFY `RoomId` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `RoomPayment`
--
ALTER TABLE `RoomPayment`
  MODIFY `PaymentId` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Student`
--
ALTER TABLE `Student`
  MODIFY `StudentId` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `StudentRoom`
--
ALTER TABLE `StudentRoom`
  MODIFY `StudentRoomId` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserId` int NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `OrderK`
--
ALTER TABLE `OrderK`
  ADD CONSTRAINT `FK_OrderK_OderType_TypeId` FOREIGN KEY (`TypeId`) REFERENCES `OrderType` (`TypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_OrderK_Student_StudentId` FOREIGN KEY (`StudentId`) REFERENCES `Student` (`StudentId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `RoomPayment`
--
ALTER TABLE `RoomPayment`
  ADD CONSTRAINT `FK_RoomPayment_StudentRoom_StudentRoomId` FOREIGN KEY (`StudentRoomId`) REFERENCES `StudentRoom` (`StudentRoomId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `StudentRoom`
--
ALTER TABLE `StudentRoom`
  ADD CONSTRAINT `FK_StudentRoom_Room_RoomId` FOREIGN KEY (`RoomId`) REFERENCES `Room` (`RoomId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_StudentRoom_Student_StudentId` FOREIGN KEY (`StudentId`) REFERENCES `Student` (`StudentId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `User`
--
ALTER TABLE `User`
  ADD CONSTRAINT `FK_User_Role` FOREIGN KEY (`RoleId`) REFERENCES `Role` (`RoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `UtilityBill`
--
ALTER TABLE `UtilityBill`
  ADD CONSTRAINT `FK_UtilityBill_Room_RoomId` FOREIGN KEY (`RoomId`) REFERENCES `Room` (`RoomId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
