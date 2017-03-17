CREATE TABLE IF NOT EXISTS Utilisateur (
pseudo VARCHAR(30) PRIMARY KEY,
nom VARCHAR(30),
prenom VARCHAR(30),
mail VARCHAR(50) NOT NULL,
mdp VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS Sujet (
idSujet INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
libelle VARCHAR(50) NOT NULL,
description VARCHAR(255),
dateCreation DATE NOT NULL,
createur VARCHAR(30) NOT NULL); 

CREATE TABLE IF NOT EXISTS Message (
idMessage INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
contenu VARCHAR(255) NOT NULL,
idSujet INTEGER(5) NOT NULL,
createur VARCHAR(30) NOT NULL);

ALTER TABLE Sujet ADD CONSTRAINT pk_createur1 FOREIGN KEY(createur) REFERENCES Utilisateur(pseudo) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Message ADD CONSTRAINT pk_sujet FOREIGN KEY(idSujet) REFERENCES Sujet(idSujet) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Message ADD CONSTRAINT pk_createur2 FOREIGN KEY(createur) REFERENCES Utilisateur(pseudo) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO Utilisateur VALUES ("coco722", "Thebaudin", "Corentin", "babouin@babouin.ba", MD5("babouin"));

INSERT INTO Utilisateur VALUES ("bigpig", "Barone", "Piero", "pig@chihuahua.gro", MD5("pigchihua"));

INSERT INTO Utilisateur VALUES ("maitreDArts", "Rousselot", "Victor", "lemaitre@machin.me", MD5("lemaitre"));

INSERT INTO Sujet (libelle, dateCreation, createur) VALUES ("Tuto du jeu", NOW(), "coco722");
