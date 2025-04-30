-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 22 avr. 2025 à 10:49
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `erp`
--

-- --------------------------------------------------------

--
-- Structure de la table `action_besoin`
--

CREATE TABLE `action_besoin` (
  `id` bigint(20) NOT NULL,
  `besoin_id` bigint(20) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `date_action` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type_action` varchar(255) NOT NULL,
  `manager` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `action_besoin`
--

INSERT INTO `action_besoin` (`id`, `besoin_id`, `created_by`, `date_action`, `description`, `type_action`, `manager`) VALUES
(31, 11, 1, '2025-04-21 22:32:12.000000', 'sssssssssssss', 'Note', 4),
(33, 11, 1, '2025-04-21 23:15:45.000000', 'vvvvvvvvvvv', 'Suivi de mission', 4);

-- --------------------------------------------------------

--
-- Structure de la table `attachments`
--

CREATE TABLE `attachments` (
  `id` bigint(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `action_besoin_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `attachments`
--

INSERT INTO `attachments` (`id`, `file_name`, `file_path`, `file_type`, `action_besoin_id`) VALUES
(24, 'Procedure of study in China with scholarship 2025.pdf', 'uploads\\53b2adec-6bd3-4f6b-aa05-8304545e20c2_Procedure of study in China with scholarship 2025.pdf', 'application/pdf', 31),
(27, 'besoin.svg', 'uploads\\8eae91eb-662a-481e-8b52-533a8db2438b_besoin.svg', 'image/svg+xml', 33),
(28, 'building.svg', 'uploads\\ebded877-a706-41c3-8260-ba1e5c1195b3_building.svg', 'image/svg+xml', 33);

-- --------------------------------------------------------

--
-- Structure de la table `besoins`
--

CREATE TABLE `besoins` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `creation_date` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `priority` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `manager_responsable` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `besoins`
--

INSERT INTO `besoins` (`id`, `created_by`, `creation_date`, `description`, `priority`, `status`, `title`, `contact_id`, `manager_responsable`) VALUES
(11, 2, '2025-04-21 21:54:21.000000', 'Besoin d\'un développeur', 'BASSE', 'ABANDONNÉ', 'Développeur', 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

CREATE TABLE `companies` (
  `id` bigint(20) NOT NULL,
  `addresse` varchar(255) DEFAULT NULL,
  `agence` varchar(255) NOT NULL,
  `code_ape` varchar(255) DEFAULT NULL,
  `effectif` int(11) NOT NULL,
  `filiales` varchar(255) DEFAULT NULL,
  `informations` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_fournisseur` varchar(255) DEFAULT NULL,
  `pays` varchar(255) NOT NULL,
  `pole` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `precisiez` varchar(255) DEFAULT NULL,
  `provenance` varchar(255) NOT NULL,
  `rcs` varchar(255) DEFAULT NULL,
  `responsable_manager` varchar(255) NOT NULL,
  `secteur` varchar(255) NOT NULL,
  `siret` varchar(255) DEFAULT NULL,
  `site_web` varchar(255) DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `statut_juridique` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) NOT NULL,
  `tva` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `companies`
--

INSERT INTO `companies` (`id`, `addresse`, `agence`, `code_ape`, `effectif`, `filiales`, `informations`, `nom`, `numero_fournisseur`, `pays`, `pole`, `postal_code`, `precisiez`, `provenance`, `rcs`, `responsable_manager`, `secteur`, `siret`, `site_web`, `statut`, `statut_juridique`, `telephone`, `tva`, `ville`) VALUES
(5, '', 'BU Conseil Tunisie', '', 10, 'fff', '', 'poulina group', '', 'Tunisia', 'DEVELOPMENT', '', 'fff', 'Prospection', '', 'Ilyes', 'Aéronautique', '', '', 'Prospect', '', '21222222', '', '');

-- --------------------------------------------------------

--
-- Structure de la table `contacts`
--

CREATE TABLE `contacts` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `function` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `company_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `contacts`
--

INSERT INTO `contacts` (`id`, `email`, `firstname`, `function`, `lastname`, `phone`, `company_id`) VALUES
(3, 'mechergui.aziz843@gmail.com', 'aziz', 'developpeur', 'mechergui', 12345678, 5);

-- --------------------------------------------------------

--
-- Structure de la table `historique_besoin`
--

CREATE TABLE `historique_besoin` (
  `id` bigint(20) NOT NULL,
  `action_by` bigint(20) NOT NULL,
  `action_date` datetime(6) NOT NULL,
  `besoin_id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `historique_besoin`
--

INSERT INTO `historique_besoin` (`id`, `action_by`, `action_date`, `besoin_id`, `description`, `status`, `title`) VALUES
(78, 2, '2025-04-21 21:54:21.000000', 11, 'Creation', 'A_TRAITER', 'Developpeur'),
(79, 1, '2025-04-21 23:30:05.000000', 11, 'Changement de statut', 'ABANDONNÉ', 'Développeur');

-- --------------------------------------------------------

--
-- Structure de la table `type_action`
--

CREATE TABLE `type_action` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `belong_to` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_action`
--

INSERT INTO `type_action` (`id`, `name`, `belong_to`) VALUES
(4, 'Note', 'Besoin'),
(9, 'Suivi de mission', 'CRM'),
(20, 'Suivi de mission', 'Besoin');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `firstname`, `lastname`, `role`, `phone`) VALUES
(1, 'aziz', '$2a$10$QjPF58LxnUAUWFPpW5vw0OpWj5yZWV.ZvPYOBgDr.MBMrgiiJ3nkC', 'mechergui.aziz843@gmail.com', 'Aziz', 'Ben Salah', 'Directeur Associé', 98765432),
(2, 'ilyes', '$2a$10$OcJ1Argr3i7cI.IpsAHBOuaZk29vPfyxNPCv06a/y5rXcGgYmfPNa', 'ilyes.hbibi999@gmail.com', 'ilyes', 'lahbibi', 'Commercial', 12345678),
(3, 'ahmed', '$2a$10$aGIo5HVKdnfM.T8Z7YWLqOI9s/dOGRk4Hg3GGyHnQVAGWjJZNfsPi', 'ahmed@example.com', 'ahmed', 'ben mohamed', 'Marketing', 12345678),
(4, 'mirado', '$2a$10$QjPF58LxnUAUWFPpW5vw0OpWj5yZWV.ZvPYOBgDr.MBMrgiiJ3nkC', 'mechergui.aziz843@gmail.com', 'mirado', 'lalalarina', 'Manager De Production', 12345678);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `action_besoin`
--
ALTER TABLE `action_besoin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bes_act` (`besoin_id`),
  ADD KEY `fk_act_usr` (`created_by`),
  ADD KEY `FKsx42t7ldx4tn7k8fmjy1jc8a3` (`manager`);

--
-- Index pour la table `attachments`
--
ALTER TABLE `attachments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlgtw2bekvhfapgcija5qq7u29` (`action_besoin_id`);

--
-- Index pour la table `besoins`
--
ALTER TABLE `besoins`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk4iipsymjwauttvh0xphgjl5a` (`contact_id`),
  ADD KEY `FKkeghbp2ut5s0d35f79wpn9tuh` (`manager_responsable`);

--
-- Index pour la table `companies`
--
ALTER TABLE `companies`
  ADD UNIQUE KEY `id` (`id`) USING BTREE,
  ADD UNIQUE KEY `UK9e1jo6jdypktl6bqwkiatmxuo` (`nom`);

--
-- Index pour la table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5nvk2pja04n1pbiyk1xn7739l` (`company_id`);

--
-- Index pour la table `historique_besoin`
--
ALTER TABLE `historique_besoin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_his_user` (`action_by`),
  ADD KEY `fk_his_besoin` (`besoin_id`);

--
-- Index pour la table `type_action`
--
ALTER TABLE `type_action`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `action_besoin`
--
ALTER TABLE `action_besoin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `attachments`
--
ALTER TABLE `attachments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `besoins`
--
ALTER TABLE `besoins`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `historique_besoin`
--
ALTER TABLE `historique_besoin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT pour la table `type_action`
--
ALTER TABLE `type_action`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `action_besoin`
--
ALTER TABLE `action_besoin`
  ADD CONSTRAINT `FKsx42t7ldx4tn7k8fmjy1jc8a3` FOREIGN KEY (`manager`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_act_usr` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_bes_act` FOREIGN KEY (`besoin_id`) REFERENCES `besoins` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `FKlgtw2bekvhfapgcija5qq7u29` FOREIGN KEY (`action_besoin_id`) REFERENCES `action_besoin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `besoins`
--
ALTER TABLE `besoins`
  ADD CONSTRAINT `FKk4iipsymjwauttvh0xphgjl5a` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FKkeghbp2ut5s0d35f79wpn9tuh` FOREIGN KEY (`manager_responsable`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `contacts`
--
ALTER TABLE `contacts`
  ADD CONSTRAINT `FK5nvk2pja04n1pbiyk1xn7739l` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `historique_besoin`
--
ALTER TABLE `historique_besoin`
  ADD CONSTRAINT `fk_his_besoin` FOREIGN KEY (`besoin_id`) REFERENCES `besoins` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_his_user` FOREIGN KEY (`action_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
