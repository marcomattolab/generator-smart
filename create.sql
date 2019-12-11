-------------------------------------------------------------
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
-- Host: localhost   Database: angulardb
-------------------------------------------------------------

-- Table structure for table `AUTORE`
DROP TABLE IF EXISTS `AUTORE`;
CREATE TABLE `AUTORE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `COGNOME` varchar(45) DEFAULT NULL,
  `NOTE` varchar(1000) DEFAULT NULL,
  `DATA_NASCITA` datetime DEFAULT NULL,
  `SESSO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


-- Table structure for table `LIBRO`
DROP TABLE IF EXISTS `LIBRO`;
CREATE TABLE `LIBRO` (
 `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITOLO` varchar(1000) DEFAULT NULL,
  `PRESTITO` varchar(10) DEFAULT NULL,
  `BESTSELLER` varchar(10) DEFAULT NULL,
  `DATA_PUBBLICAZIONE` date DEFAULT NULL,
  `ISBN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;


-- Table structure for table `NOTIFICHE`
DROP TABLE IF EXISTS `NOTIFICHE`;
CREATE TABLE `NOTIFICHE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CANALE` varchar(100) NOT NULL,
  `TESTO` varchar(1000) NOT NULL,
  `ANNO` int(4) NOT NULL,
  `NUMERO` decimal(10,0) DEFAULT NULL,
  `ESITO` varchar(50) NOT NULL,
  `DATA_INVIO` date DEFAULT NULL,
   PRIMARY KEY (`ID`)
) ;

-- Table structure for table `ORDINE`
DROP TABLE IF EXISTS `ORDINE`;
CREATE TABLE `ORDINE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIZIONE` varchar(100) NOT NULL,
  `NUMERO` decimal(10,0) DEFAULT NULL,
  `NOTE` varchar(2000) NOT NULL,
  `DATA_ORDINE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;


------------------------------------------------------------------------------
------------------------------------------------------------------------------

-- Table structure for table `DATABASECHANGELOG`
DROP TABLE IF EXISTS `DATABASECHANGELOG`;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ;

-- Dumping data for table `DATABASECHANGELOG`
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-12-02 12:14:10',1,'EXECUTED','7:3881592bcb556ed52e621bfaee4cc7b2','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; createTable tableName=jhi_persistent_token; addForeignKeyConstraint baseTableName=jhi_user_a...','',NULL,'3.5.4',NULL,NULL,'3749249865'),('20181202111651-1','jhipster','config/liquibase/changelog/20181202111651_added_entity_Cliente.xml','2018-12-02 12:18:57',2,'EXECUTED','7:6eae2e53aa57e582b0215a5d2afa343b','createTable tableName=cliente; dropDefaultValue columnName=data_creazione, tableName=cliente; dropDefaultValue columnName=data_modifica, tableName=cliente','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111652-1','jhipster','config/liquibase/changelog/20181202111652_added_entity_Acquirente.xml','2018-12-02 12:18:57',3,'EXECUTED','7:bf800699d69f89893a22634287d38648','createTable tableName=acquirente; dropDefaultValue columnName=data_creazione, tableName=acquirente; dropDefaultValue columnName=data_modifica, tableName=acquirente','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111653-1','jhipster','config/liquibase/changelog/20181202111653_added_entity_Immobile.xml','2018-12-02 12:18:57',4,'EXECUTED','7:69ea1e4775263e6cda43f5ce46615e2c','createTable tableName=immobile; dropDefaultValue columnName=data_creazione, tableName=immobile; dropDefaultValue columnName=data_modifica, tableName=immobile','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111654-1','jhipster','config/liquibase/changelog/20181202111654_added_entity_Files.xml','2018-12-02 12:18:57',5,'EXECUTED','7:2d8886cb1983bad31cca68901ba0d7ba','createTable tableName=files; dropDefaultValue columnName=data_creazione, tableName=files; dropDefaultValue columnName=data_modifica, tableName=files','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111655-1','jhipster','config/liquibase/changelog/20181202111655_added_entity_Partner.xml','2018-12-02 12:18:58',6,'EXECUTED','7:0a1cd0bc77066534fdf3d76618c61f3c','createTable tableName=partner; dropDefaultValue columnName=data_creazione, tableName=partner; dropDefaultValue columnName=data_modifica, tableName=partner','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111656-1','jhipster','config/liquibase/changelog/20181202111656_added_entity_Incarico.xml','2018-12-02 12:18:58',7,'EXECUTED','7:65a27bb493a38346358de11a228c867e','createTable tableName=incarico; dropDefaultValue columnName=data_contatto, tableName=incarico; dropDefaultValue columnName=data_creazione, tableName=incarico; dropDefaultValue columnName=data_modifica, tableName=incarico; createTable tableName=inc...','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111657-1','jhipster','config/liquibase/changelog/20181202111657_added_entity_Notifiche.xml','2018-12-02 12:18:58',8,'EXECUTED','7:f428a994070850769cdb33c0987ee4e5','createTable tableName=notifiche; dropDefaultValue columnName=data_creazione, tableName=notifiche; dropDefaultValue columnName=data_modifica, tableName=notifiche','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111658-1','jhipster','config/liquibase/changelog/20181202111658_added_entity_ListaContatti.xml','2018-12-02 12:18:58',9,'EXECUTED','7:94112d11cd858e9a538006e20790fff5','createTable tableName=lista_contatti','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111659-1','jhipster','config/liquibase/changelog/20181202111659_added_entity_Geolocalizzazione.xml','2018-12-02 12:18:58',10,'EXECUTED','7:c3c2bd7a8bf366b1c6f261d651c8a437','createTable tableName=geolocalizzazione; dropDefaultValue columnName=data_creazione, tableName=geolocalizzazione; dropDefaultValue columnName=data_modifica, tableName=geolocalizzazione','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111653-2','jhipster','config/liquibase/changelog/20181202111653_added_entity_constraints_Immobile.xml','2018-12-02 12:18:58',11,'EXECUTED','7:7239af82fa91e20e6a58060445987717','addForeignKeyConstraint baseTableName=immobile, constraintName=fk_immobile_locazione_id, referencedTableName=geolocalizzazione','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111654-2','jhipster','config/liquibase/changelog/20181202111654_added_entity_constraints_Files.xml','2018-12-02 12:18:58',12,'EXECUTED','7:87977f15b53c7a916701245b62007c9c','addForeignKeyConstraint baseTableName=files, constraintName=fk_files_immobile_id, referencedTableName=immobile','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111656-2','jhipster','config/liquibase/changelog/20181202111656_added_entity_constraints_Incarico.xml','2018-12-02 12:18:58',13,'EXECUTED','7:4428b853b10ce0319fc04a249a5bdfa5','addForeignKeyConstraint baseTableName=incarico, constraintName=fk_incarico_immobile_id, referencedTableName=immobile; addForeignKeyConstraint baseTableName=incarico_partner, constraintName=fk_incarico_partner_incaricos_id, referencedTableName=inca...','',NULL,'3.5.4',NULL,NULL,'3749537492'),('20181202111658-2','jhipster','config/liquibase/changelog/20181202111658_added_entity_constraints_ListaContatti.xml','2018-12-02 12:18:58',14,'EXECUTED','7:11c6a32d97baa1b7723cee054bbd7286','addForeignKeyConstraint baseTableName=lista_contatti, constraintName=fk_lista_contatti_cliente_id, referencedTableName=cliente','',NULL,'3.5.4',NULL,NULL,'3749537492');

-- Table structure for table `DATABASECHANGELOGLOCK`
DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;

-- Dumping data for table `DATABASECHANGELOGLOCK`
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);

-- Table structure for table `jhi_authority`
DROP TABLE IF EXISTS `jhi_authority`;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ;

-- Dumping data for table `jhi_authority`
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');

-- Table structure for table `jhi_persistent_audit_event`
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ;

-- Dumping data for table `jhi_persistent_audit_event`
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2018-12-02 10:15:15','AUTHENTICATION_SUCCESS'),(2,'admin','2018-12-02 10:19:42','AUTHENTICATION_SUCCESS');

-- Table structure for table `jhi_persistent_audit_evt_data`
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ;

-- Dumping data for table `jhi_persistent_audit_evt_data`
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'remoteAddress','0:0:0:0:0:0:0:1');

-- Table structure for table `jhi_persistent_token`
DROP TABLE IF EXISTS `jhi_persistent_token`;
CREATE TABLE `jhi_persistent_token` (
  `series` varchar(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(20) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ;

-- Dumping data for table `jhi_persistent_token`
-- Table structure for table `jhi_user`
DROP TABLE IF EXISTS `jhi_user`;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ;

-- Dumping data for table `jhi_user`
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','',_binary '','it',NULL,NULL,'system',NULL,NULL,'system',NULL),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','',_binary '','it',NULL,NULL,'system',NULL,NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','',_binary '','it',NULL,NULL,'system',NULL,NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','',_binary '','it',NULL,NULL,'system',NULL,NULL,'system',NULL);

-- Table structure for table `jhi_user_authority`
DROP TABLE IF EXISTS `jhi_user_authority`;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ;

-- Dumping data for table `jhi_user_authority`
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');


