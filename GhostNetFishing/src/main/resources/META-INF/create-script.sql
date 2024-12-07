-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 14. Okt 2024 um 16:15
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ghostnetfishing`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `contact`
--

CREATE TABLE `contact` ( `id` int(11) NOT NULL, `name` varchar(255) NOT NULL, `tel` varchar(255) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tabellenstruktur für Tabelle `ghostnet`
--

CREATE TABLE `ghostnet` ( `height` float NOT NULL, `id` int(11) NOT NULL, `latitude` float NOT NULL, `longitude` float NOT NULL, `state` tinyint(4) DEFAULT NULL, `width` float NOT NULL, `reportingUser_id` int(11) DEFAULT NULL, `retrievingUser_id` int(11) DEFAULT NULL, `contact_id` int(11) DEFAULT NULL, `retrievingContact_id` int(11) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (  `id` int(11) NOT NULL, `password` varchar(255) DEFAULT NULL,  `username` varchar(255) DEFAULT NULL,  `contact_id` int(11) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `contact`
--
ALTER TABLE `contact`  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `ghostnet`
--
ALTER TABLE `ghostnet`  ADD PRIMARY KEY (`id`),  ADD KEY `FKjgsweskjy4kt6mj6r9ag48mje` (`reportingUser_id`),  ADD KEY `FKgdyu52vn1o179aos466joc5dx` (`retrievingUser_id`),  ADD KEY `FKav0hk5fpcalun990h2swb7b6h` (`contact_id`),  ADD KEY `FK91qfu4yswb8tpj8eeda2a3tdl` (`retrievingContact_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`  ADD PRIMARY KEY (`id`),  ADD KEY `FKe802x9r45hjb14p4so15juv6y` (`contact_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `contact`
--
ALTER TABLE `contact`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=246;

--
-- AUTO_INCREMENT für Tabelle `ghostnet`
--
ALTER TABLE `ghostnet`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `ghostnet`
--
ALTER TABLE `ghostnet`  ADD CONSTRAINT `FK91qfu4yswb8tpj8eeda2a3tdl` FOREIGN KEY (`retrievingContact_id`) REFERENCES `contact` (`id`),  ADD CONSTRAINT `FKav0hk5fpcalun990h2swb7b6h` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`),  ADD CONSTRAINT `FKgdyu52vn1o179aos466joc5dx` FOREIGN KEY (`retrievingUser_id`) REFERENCES `user` (`id`),  ADD CONSTRAINT `FKjgsweskjy4kt6mj6r9ag48mje` FOREIGN KEY (`reportingUser_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `user`
--
ALTER TABLE `user`  ADD CONSTRAINT `FKe802x9r45hjb14p4so15juv6y` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
