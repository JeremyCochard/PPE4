-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 28 mars 2022 à 14:45
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `zonestockage`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `ConsultationReservationClient`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultationReservationClient` (`numClientReservation` INT(11))  BEGIN

    DECLARE nbClient int(11) DEFAULT 0;

SELECT count(numClient)
    INTO nbClient
    FROM reservation
    WHERE id = idreservation
    and numClient = numClientReservation;


 if (nbClient=1) then
select * from reservation where numClient=numClientReservation;

   end if;

END$$

DROP PROCEDURE IF EXISTS `ModifierReservationClient`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModifierReservationClient` (`numClient` INT(5), `dateReservationClient` DATE, `datePrevueStockageClient` DATE, `nbJoursDeStockagePrevuClient` SMALLINT(6), `quantiteClient` INT(11), `etatClient` INT(10))  BEGIN

    DECLARE nbClient int(11) DEFAULT 0;

SELECT count(numClient)
    INTO nbClient
    FROM reservation
    WHERE id = idreservation
    and numClient = numClientReservation;


 if (nbClient=1) then

UPDATE reservation set dateReservation = dateReservationClient , nbJoursDeStockagePrevu = nbJoursDeStockagePrevuClient,
quantite = quantiteClient,etat = etatClient;
   end if;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `bloc`
--

DROP TABLE IF EXISTS `bloc`;
CREATE TABLE IF NOT EXISTS `bloc` (
  `codeBloc` char(1) NOT NULL,
  PRIMARY KEY (`codeBloc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `bloc`
--

INSERT INTO `bloc` (`codeBloc`) VALUES
('A'),
('B'),
('C'),
('D'),
('E');

-- --------------------------------------------------------

--
-- Structure de la table `pile`
--

DROP TABLE IF EXISTS `pile`;
CREATE TABLE IF NOT EXISTS `pile` (
  `numPile` char(1) NOT NULL,
  `numTravee` char(1) NOT NULL,
  `codeBloc` char(1) NOT NULL,
  `capacite` tinyint(4) NOT NULL,
  PRIMARY KEY (`numPile`,`numTravee`,`codeBloc`),
  KEY `numTravee` (`numTravee`),
  KEY `codeBloc` (`codeBloc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pile`
--

INSERT INTO `pile` (`numPile`, `numTravee`, `codeBloc`, `capacite`) VALUES
('1', '1', 'A', 10),
('1', '1', 'B', 10),
('1', '1', 'C', 10),
('1', '1', 'D', 10),
('1', '1', 'E', 10),
('1', '2', 'A', 10),
('1', '2', 'B', 10),
('1', '2', 'C', 10),
('1', '2', 'D', 10),
('1', '2', 'E', 10),
('1', '3', 'A', 10),
('1', '3', 'B', 10),
('1', '3', 'C', 10),
('1', '3', 'D', 10),
('1', '3', 'E', 10),
('2', '1', 'A', 10),
('2', '1', 'B', 10),
('2', '1', 'C', 10),
('2', '1', 'D', 10),
('2', '1', 'E', 10),
('2', '2', 'A', 10),
('2', '2', 'B', 10),
('2', '2', 'C', 10),
('2', '2', 'D', 10),
('2', '2', 'E', 10),
('2', '3', 'A', 10),
('2', '3', 'B', 10),
('2', '3', 'C', 10),
('2', '3', 'D', 10),
('2', '3', 'E', 10),
('3', '1', 'A', 10),
('3', '1', 'B', 10),
('3', '1', 'C', 10),
('3', '1', 'D', 10),
('3', '1', 'E', 10),
('3', '2', 'A', 10),
('3', '2', 'B', 10),
('3', '2', 'C', 10),
('3', '2', 'D', 10),
('3', '2', 'E', 10),
('3', '3', 'A', 10),
('3', '3', 'B', 10),
('3', '3', 'C', 10),
('3', '3', 'D', 10),
('3', '3', 'E', 10);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateReservation` date NOT NULL,
  `datePrevueStockage` date NOT NULL,
  `nbJoursDeStockagePrevu` smallint(6) NOT NULL,
  `quantite` int(11) NOT NULL,
  `etat` enum('demande','encours','termine','') NOT NULL,
  `numClient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `numClient` (`numClient`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `dateReservation`, `datePrevueStockage`, `nbJoursDeStockagePrevu`, `quantite`, `etat`, `numClient`) VALUES
(1, '2016-12-04', '2016-12-05', 5, 50, 'encours', 1),
(2, '2016-12-05', '2016-12-07', 10, 34, 'encours', 1),
(3, '2016-12-05', '2016-12-06', 8, 7, 'encours', 2),
(4, '2016-12-08', '2016-12-20', 4, 13, 'demande', 1),
(13, '2022-03-28', '2022-04-07', 10, 10, 'demande', 3);

-- --------------------------------------------------------

--
-- Structure de la table `reservationstockee`
--

DROP TABLE IF EXISTS `reservationstockee`;
CREATE TABLE IF NOT EXISTS `reservationstockee` (
  `numPile` char(1) NOT NULL,
  `numTravee` char(1) NOT NULL,
  `codeBloc` char(1) NOT NULL,
  `idReservation` int(11) NOT NULL,
  `emplacementDepart` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `dateDebutEffective` datetime NOT NULL,
  `dateFinEffective` datetime NOT NULL,
  PRIMARY KEY (`numPile`,`numTravee`,`codeBloc`,`idReservation`),
  KEY `idReservation` (`idReservation`),
  KEY `bloc` (`codeBloc`),
  KEY `numPile` (`numPile`),
  KEY `travee` (`numTravee`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservationstockee`
--

INSERT INTO `reservationstockee` (`numPile`, `numTravee`, `codeBloc`, `idReservation`, `emplacementDepart`, `quantite`, `dateDebutEffective`, `dateFinEffective`) VALUES
('1', '1', 'A', 1, 1, 4, '2016-12-05 00:00:00', '2016-12-10 00:00:00'),
('1', '1', 'A', 3, 5, 4, '2016-12-11 00:00:00', '2016-12-19 00:00:00'),
('1', '1', 'A', 4, 9, 2, '2016-12-20 00:00:00', '2016-12-24 00:00:00'),
('1', '1', 'B', 4, 1, 10, '2016-12-24 00:00:00', '2016-12-28 00:00:00'),
('1', '1', 'C', 4, 1, 1, '2016-12-28 00:00:00', '2017-01-01 00:00:00'),
('2', '1', 'A', 2, 1, 7, '2016-12-22 00:00:00', '2016-12-27 00:00:00'),
('3', '1', 'A', 2, 1, 10, '2016-11-20 00:00:00', '2016-12-01 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `travee`
--

DROP TABLE IF EXISTS `travee`;
CREATE TABLE IF NOT EXISTS `travee` (
  `numTravee` char(1) NOT NULL,
  `codeBloc` char(1) NOT NULL,
  PRIMARY KEY (`numTravee`,`codeBloc`),
  KEY `codeBloc` (`codeBloc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `travee`
--

INSERT INTO `travee` (`numTravee`, `codeBloc`) VALUES
('1', 'A'),
('1', 'B'),
('1', 'C'),
('1', 'D'),
('1', 'E'),
('2', 'A'),
('2', 'B'),
('2', 'C'),
('2', 'D'),
('2', 'E'),
('3', 'A'),
('3', 'B'),
('3', 'C'),
('3', 'D'),
('3', 'E');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `numUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `raisonSociale` varchar(200) NOT NULL,
  `login` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` enum('client','fournisseur','administrateur','dsi','docker') NOT NULL,
  PRIMARY KEY (`numUtilisateur`),
  UNIQUE KEY `identifiant` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`numUtilisateur`, `raisonSociale`, `login`, `password`, `type`) VALUES
(1, 'SA THOLDI', 'tholdi', '*BA401E5F7BA3C4ED500DF2A4BFD611A83AD14AF2', 'client'),
(2, 'SA. TANSPORT MARITIME DU HAVRE', 'SA.TMH', '*259832BA90778A99DB69A810F6EABF486010C8BF', 'client'),
(3, 'test', 'jcoc', '21/09/2002', 'client');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `pile`
--
ALTER TABLE `pile`
  ADD CONSTRAINT `pile_ibfk_1` FOREIGN KEY (`numTravee`) REFERENCES `travee` (`numTravee`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pile_ibfk_2` FOREIGN KEY (`codeBloc`) REFERENCES `travee` (`codeBloc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`numClient`) REFERENCES `utilisateur` (`numUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservationstockee`
--
ALTER TABLE `reservationstockee`
  ADD CONSTRAINT `reservationStockee_ibfk_1` FOREIGN KEY (`idReservation`) REFERENCES `reservation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservationStockee_ibfk_2` FOREIGN KEY (`numPile`,`numTravee`,`codeBloc`) REFERENCES `pile` (`numPile`, `numTravee`, `codeBloc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `travee`
--
ALTER TABLE `travee`
  ADD CONSTRAINT `travee_ibfk_1` FOREIGN KEY (`codeBloc`) REFERENCES `bloc` (`codeBloc`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
