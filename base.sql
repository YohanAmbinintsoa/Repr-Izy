CREATE TABLE Categorie(
   idCategorie VARCHAR(50) ,
   nomCategrorie VARCHAR(255)  NOT NULL,
   PRIMARY KEY(idCategorie),
   UNIQUE(nomCategrorie)
);

CREATE TABLE Type(
   idType VARCHAR(50) ,
   nomType VARCHAR(255)  NOT NULL,
   fk_Categorie VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idType),
   UNIQUE(nomType),
   FOREIGN KEY(fk_Categorie) REFERENCES Categorie(idCategorie)
);

CREATE TABLE Transmission(
   idTransmission VARCHAR(50) ,
   nomTransmission VARCHAR(255) ,
   PRIMARY KEY(idTransmission)
);

CREATE TABLE Pays(
   idPays VARCHAR(50) ,
   nomPays VARCHAR(255)  NOT NULL,
   Path TEXT,
   PRIMARY KEY(idPays),
   UNIQUE(nomPays)
);

CREATE TABLE Energie(
   idEnergie VARCHAR(50) ,
   nomEnergie VARCHAR(255)  NOT NULL,
   PRIMARY KEY(idEnergie),
   UNIQUE(nomEnergie)
);

CREATE TABLE Etat(
   idEtat VARCHAR(50) ,
   nomEtat VARCHAR(255)  NOT NULL,
   PRIMARY KEY(idEtat),
   UNIQUE(nomEtat)
);

CREATE TABLE Marque(
   idMarque VARCHAR(50) ,
   nomMarque VARCHAR(255) ,
   fk_Pays VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idMarque),
   FOREIGN KEY(fk_Pays) REFERENCES Pays(idPays)
);

CREATE TABLE Modele(
   idModele VARCHAR(50) ,
   nomModele VARCHAR(255)  NOT NULL,
   fk_Marque VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idModele),
   FOREIGN KEY(fk_Marque) REFERENCES Marque(idMarque)
);

CREATE TABLE Categorie_Marque(
   fk_Categorie VARCHAR(50) ,
   fk_Marque VARCHAR(50) ,
   FOREIGN KEY(fk_Categorie) REFERENCES Categorie(idCategorie),
   FOREIGN KEY(fk_Marque) REFERENCES Marque(idMarque)
);
