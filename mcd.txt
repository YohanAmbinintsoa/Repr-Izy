CREATE TABLE Categorie(
   idCategorie VARCHAR(50) ,
   nomCategorie VARCHAR(255)  NOT NULL,
   PRIMARY KEY(idCategorie),
   UNIQUE(nomCategorie)
);

CREATE TABLE Type(
   idType VARCHAR(50) ,
   nomType VARCHAR(255)  NOT NULL,
   idCategorie VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idType),
   UNIQUE(nomType),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie)
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
   image TEXT,
   idPays VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idMarque),
   FOREIGN KEY(idPays) REFERENCES Pays(idPays)
);

CREATE TABLE Modele(
   idModele VARCHAR(50) ,
   nomModele VARCHAR(255)  NOT NULL,
   idMarque VARCHAR(50)  NOT NULL,
   PRIMARY KEY(idModele),
   FOREIGN KEY(idMarque) REFERENCES Marque(idMarque)
);

CREATE TABLE Categorie_Marque(
   idCategorie VARCHAR(50) ,
   idMarque VARCHAR(50) ,
   PRIMARY KEY(idCategorie, idMarque),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie),
   FOREIGN KEY(idMarque) REFERENCES Marque(idMarque)
);
