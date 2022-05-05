CREATE DATABASE NAVIGATION;

USE NAVIGATION;

CREATE TABLE siteUser (
id int PRIMARY KEY IDENTITY,
nom varchar(100) NOT NULL,
prenom varchar(100) NOT NULL,
dateNaissance date NOT NULL,
email varchar(100) NOT NULL UNIQUE,
password varchar(max) NOT NULL,
dateHeureInscription datetime NOT NULL);
Role_id int NOT NULL DEFAULT 2 FOREIGN KEY REFERENCES Role(Role_id);

CREATE TABLE Role(
Role_id int PRIMARY KEY IDENTITY,
Role varchar(15)
);

INSERT INTO Role (Role) VALUES 
('admin'),
('user');


