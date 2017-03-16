CREATE TABLE IF NOT EXISTS Utilisateur (
pseudo VARCHAR(30) PRIMARY KEY,
nom VARCHAR(30),
prenom VARCHAR(30),
mail VARCHAR(50),
mdp VARCHAR(100));

CREATE TABLE IF NOT EXISTS Sujet (
idSujet INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
libelle VARCHAR(50),
dateCreation DATE,
createur VARCHAR(30) REFERENCES Utilisateur(pseudo) ON DELETE CASCADE); 

CREATE TABLE IF NOT EXISTS Message (
idMessage INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
contenu VARCHAR(255),
idSujet INTEGER(5) REFERENCES Sujet(idSujet) ON DELETE CASCADE,
createur VARCHAR(30) REFERENCES Utilisateur(pseudo) ON DELETE CASCADE);

INSERT INTO Utilisateur VALUES ("coco722", "Thebaudin", "Corentin", "babouin@babouin.ba", MD5("babouin"));

INSERT INTO Utilisateur VALUES ("bigpig", "Barone", "Piero", "pig@chihuahua.gro", MD5("pigchihua"));

INSERT INTO Utilisateur VALUES ("maitreDArts", "Rousselot", "Victor", "lemaitre@machin.me", MD5("lemaitre"));

INSERT INTO Sujet (libelle, dateCreation, createur) VALUES ("Tuto du jeu", NOW(), "coco722");
