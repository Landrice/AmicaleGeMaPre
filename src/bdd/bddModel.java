package bdd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ralande
 */
public class bddModel {

    Properties properties = new Properties();
    InputStream inputStream;
    String db;

    public void loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("bdd.config");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("Erreur du Creation/lecture du fichier" + e);
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        bddConnection con = new bddConnection();
        
        try {
            long depart=System.currentTimeMillis();
            pst = con.mkDataBase().prepareStatement("create database if not exists " + db + " DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("START TRANSACTION;");
            pst.execute();

            //////////////////////////////////
            //    DEPOT MILITAIRE           //
            //////////////////////////////////
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dml_produits` (\n"
                    + "  `Id_dml` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `IdProduit_dml` varchar(20) NOT NULL,\n"
                    + "  `NomProduit_dml` varchar(150) NOT NULL,\n"
                    + "  `Quantite_dml` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description_dml` text ,\n"
                    + "  `FounisseurId` varchar(11) NOT NULL,\n"
                    + "  `MarqueId` varchar(11),\n"
                    + "  `CategorieId` varchar(11) NOT NULL,\n"
                    + "  `Prix_dml` varchar(100) ,\n"
                    + "  `UtilisateurId_dml` varchar(11) NOT NULL,\n"
                    + "  `Date_dml` date,\n"
                    + "  `Unite_dml` varchar(30) NOT NULL, \n"
                    + "  `nombrepp_dml` varchar(50) , \n"
                    + "  `unitepp_dml` varchar(50) , \n"
                    + "  `nombreppm_dml` varchar(50) , \n"
                    + "  `uniteppm_dml` varchar(50) , \n"
                    + "  PRIMARY KEY (`Id_dml`),\n"
                    + "  UNIQUE INDEX `Id_dml` (`Id_dml` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dml_utilisateur` (\n"
                    + "  `Id_dml` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nom_dml` VARCHAR(20) NOT NULL,\n"
                    + "  `Prenom_dml` VARCHAR(100) ,\n"
                    + "  `Email_dml` VARCHAR(100) ,\n"
                    + "  `Telephone_dml` VARCHAR(100) ,\n"
                    + "  `Addresse_dml` text,\n"
                    + "  `Mdp_dml` VARCHAR(45),\n"
                    + "  `photo_dml` mediumblob,\n"
                    + "  `CreateurID_dml` int(11),\n"
                    + "  PRIMARY KEY (`Id_dml`),\n"
                    + "  UNIQUE INDEX `Id_dml` (`Id_dml` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dml_droitUtilisateur` (\n"
                    + "  `Id_dml` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AjoutProduit_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutFournisseur_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutMarque_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutCategory_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutQuantite_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutClient_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourProduit_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourFournisseur_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourMarque_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourCategory_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourQuantite_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourClient_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `VendreProduit_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `GererUtilisateur_dml` tinyint(1) DEFAULT NULL,\n"
                    + "  `UtilisateurId_dml` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dml`),\n"
                    + "  UNIQUE INDEX `Id_dml` (`Id_dml` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dml_Vente` (\n"
                    + "  `Id_dml` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VenteId_dml` varchar(10) NOT NULL,\n"
                    + "  `AcheteurId_dml` varchar(11) ,\n"
                    + "  `ProduitId_dml` varchar(11) NOT NULL,\n"
                    + "  `Quantite_dml` int(10) NOT NULL,\n"
                    + "  `TotalPrix_dml` double NOT NULL,\n"
                    + "  `Garantie_dml` varchar(20),\n"
                    + "  `DateDVente_dml` date NOT NULL,\n"
                    + "  `Unite_dml` varchar(30) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dml`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();

            //////////////////////////////////
            //    DEPOT GLACE               //
            //////////////////////////////////
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dgl_produits` (\n"
                    + "  `Id_dgl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `IdProduit_dgl` varchar(20) NOT NULL,\n"
                    + "  `NomProduit_dgl` varchar(150) NOT NULL,\n"
                    + "  `Quantite_dgl` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description_dgl` text ,\n"
                    + "  `FounisseurId` varchar(11) NOT NULL,\n"
                    + "  `MarqueId` varchar(11),\n"
                    + "  `CategorieId` varchar(11) NOT NULL,\n"
                    + "  `Prix_dgl` varchar(100) ,\n"
                    + "  `UtilisateurId_dgl` varchar(11) NOT NULL,\n"
                    + "  `Date_dgl` date,\n"
                    + "  `Unite_dgl` varchar(30) NOT NULL, \n"
                    + "  `nombrepp_dgl` varchar(50) , \n"
                    + "  `unitepp_dgl` varchar(50) , \n"
                    + "  `nombreppm_dgl` varchar(50) , \n"
                    + "  `uniteppm_dgl` varchar(50) , \n"
                    + "  PRIMARY KEY (`Id_dgl`),\n"
                    + "  UNIQUE INDEX `Id_dgl` (`Id_dgl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dgl_utilisateur` (\n"
                    + "  `Id_dgl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nom_dgl` VARCHAR(20) NOT NULL,\n"
                    + "  `Prenom_dgl` VARCHAR(100) ,\n"
                    + "  `Email_dgl` VARCHAR(100) ,\n"
                    + "  `Telephone_dgl` VARCHAR(100) ,\n"
                    + "  `Addresse_dgl` text,\n"
                    + "  `Mdp_dgl` VARCHAR(45),\n"
                    + "  `photo_dgl` mediumblob,\n"
                    + "  `CreateurID_dgl` int(11),\n"
                    + "  PRIMARY KEY (`Id_dgl`),\n"
                    + "  UNIQUE INDEX `Id_dgl` (`Id_dgl` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dgl_droitUtilisateur` (\n"
                    + "  `Id_dgl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AjoutProduit_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutFournisseur_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutMarque_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutCategory_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutQuantite_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutClient_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourProduit_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourFournisseur_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourMarque_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourCategory_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourQuantite_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourClient_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `VendreProduit_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `GererUtilisateur_dgl` tinyint(1) DEFAULT NULL,\n"
                    + "  `UtilisateurId_dgl` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dgl`),\n"
                    + "  UNIQUE INDEX `Id_dgl` (`Id_dgl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dgl_Vente` (\n"
                    + "  `Id_dgl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VenteId_dgl` varchar(10) NOT NULL,\n"
                    + "  `AcheteurId_dgl` varchar(11) ,\n"
                    + "  `ProduitId_dgl` varchar(11) NOT NULL,\n"
                    + "  `Quantite_dgl` int(10) NOT NULL,\n"
                    + "  `TotalPrix_dgl` double NOT NULL,\n"
                    + "  `Garantie_dgl` varchar(20),\n"
                    + "  `DateDVente_dgl` date NOT NULL,\n"
                    + "  `Unite_dgl` varchar(30) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dgl`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();

            ///////////////////////////////////
            //    DEPOT PATISSERIE           //
            ///////////////////////////////////
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpt_produits` (\n"
                    + "  `Id_dpt` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `IdProduit_dpt` varchar(20) NOT NULL,\n"
                    + "  `NomProduit_dpt` varchar(150) NOT NULL,\n"
                    + "  `Quantite_dpt` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description_dpt` text ,\n"
                    + "  `FounisseurId` varchar(11) NOT NULL,\n"
                    + "  `MarqueId` varchar(11),\n"
                    + "  `CategorieId` varchar(11) NOT NULL,\n"
                    + "  `Prix_dpt` varchar(100) ,\n"
                    + "  `UtilisateurId_dpt` varchar(11) NOT NULL,\n"
                    + "  `Date_dpt` date,\n"
                    + "  `Unite_dpt` varchar(30) NOT NULL, \n"
                    + "  `nombrepp_dpt` varchar(50) , \n"
                    + "  `unitepp_dpt` varchar(50) , \n"
                    + "  `nombreppm_dpt` varchar(50) , \n"
                    + "  `uniteppm_dpt` varchar(50) , \n"
                    + "  PRIMARY KEY (`Id_dpt`),\n"
                    + "  UNIQUE INDEX `Id_dpt` (`Id_dpt` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dpt_utilisateur` (\n"
                    + "  `Id_dpt` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nom_dpt` VARCHAR(20) NOT NULL,\n"
                    + "  `Prenom_dpt` VARCHAR(100) ,\n"
                    + "  `Email_dpt` VARCHAR(100) ,\n"
                    + "  `Telephone_dpt` VARCHAR(100) ,\n"
                    + "  `Addresse_dpt` text,\n"
                    + "  `Mdp_dpt` VARCHAR(45),\n"
                    + "  `photo_dpt` mediumblob,\n"
                    + "  `CreateurID_dpt` int(11),\n"
                    + "  PRIMARY KEY (`Id_dpt`),\n"
                    + "  UNIQUE INDEX `Id_dpt` (`Id_dpt` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dpt_droitUtilisateur` (\n"
                    + "  `Id_dpt` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AjoutProduit_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutFournisseur_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutMarque_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutCategory_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutQuantite_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutClient_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourProduit_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourFournisseur_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourMarque_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourCategory_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourQuantite_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourClient_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `VendreProduit_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `GererUtilisateur_dpt` tinyint(1) DEFAULT NULL,\n"
                    + "  `UtilisateurId_dpt` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dpt`),\n"
                    + "  UNIQUE INDEX `Id_dpt` (`Id_dpt` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpt_Vente` (\n"
                    + "  `Id_dpt` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VenteId_dpt` varchar(10) NOT NULL,\n"
                    + "  `AcheteurId_dpt` varchar(11) ,\n"
                    + "  `ProduitId_dpt` varchar(11) NOT NULL,\n"
                    + "  `Quantite_dpt` int(10) NOT NULL,\n"
                    + "  `TotalPrix_dpt` double NOT NULL,\n"
                    + "  `Garantie_dpt` varchar(20),\n"
                    + "  `DateDVente_dpt` date NOT NULL,\n"
                    + "  `Unite_dpt` varchar(30) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dpt`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();

            ///////////////////////////////////
            //    DEPOT PIZZERIA             //
            ///////////////////////////////////
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpz_produits` (\n"
                    + "  `Id_dpz` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `IdProduit_dpz` varchar(20) NOT NULL,\n"
                    + "  `NomProduit_dpz` varchar(150) NOT NULL,\n"
                    + "  `Quantite_dpz` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description_dpz` text ,\n"
                    + "  `FounisseurId` varchar(11) NOT NULL,\n"
                    + "  `MarqueId` varchar(11),\n"
                    + "  `CategorieId` varchar(11) NOT NULL,\n"
                    + "  `Prix_dpz` varchar(100) ,\n"
                    + "  `UtilisateurId_dpz` varchar(11) NOT NULL,\n"
                    + "  `Date_dpz` date,\n"
                    + "  `Unite_dpz` varchar(30) NOT NULL, \n"
                    + "  `nombrepp_dpz` varchar(50) , \n"
                    + "  `unitepp_dpz` varchar(50) , \n"
                    + "  `nombreppm_dpz` varchar(50) , \n"
                    + "  `uniteppm_dpz` varchar(50) , \n"
                    + "  PRIMARY KEY (`Id_dpz`),\n"
                    + "  UNIQUE INDEX `Id_dpz` (`Id_dpz` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dpz_utilisateur` (\n"
                    + "  `Id_dpz` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nom_dpz` VARCHAR(20) NOT NULL,\n"
                    + "  `Prenom_dpz` VARCHAR(100) ,\n"
                    + "  `Email_dpz` VARCHAR(100) ,\n"
                    + "  `Telephone_dpz` VARCHAR(100) ,\n"
                    + "  `Addresse_dpz` text,\n"
                    + "  `Mdp_dpz` VARCHAR(45),\n"
                    + "  `photo_dpz` mediumblob,\n"
                    + "  `CreateurID_dpz` int(11),\n"
                    + "  PRIMARY KEY (`Id_dpz`),\n"
                    + "  UNIQUE INDEX `Id_dpz` (`Id_dpz` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dpz_droitUtilisateur` (\n"
                    + "  `Id_dpz` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AjoutProduit_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutFournisseur_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutMarque_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutCategory_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutQuantite_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutClient_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourProduit_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourFournisseur_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourMarque_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourCategory_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourQuantite_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourClient_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `VendreProduit_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `GererUtilisateur_dpz` tinyint(1) DEFAULT NULL,\n"
                    + "  `UtilisateurId_dpz` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dpz`),\n"
                    + "  UNIQUE INDEX `Id_dpz` (`Id_dpz` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpz_Vente` (\n"
                    + "  `Id_dpz` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VenteId_dpz` varchar(10) NOT NULL,\n"
                    + "  `AcheteurId_dpz` varchar(11) ,\n"
                    + "  `ProduitId_dpz` varchar(11) NOT NULL,\n"
                    + "  `Quantite_dpz` int(10) NOT NULL,\n"
                    + "  `TotalPrix_dpz` double NOT NULL,\n"
                    + "  `Garantie_dpz` varchar(20),\n"
                    + "  `DateDVente_dpz` date NOT NULL,\n"
                    + "  `Unite_dpz` varchar(30) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dpz`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();

            ///////////////////////////////////
            //      DEPOT BOULANGERIE        //
            ///////////////////////////////////
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dbl_produits` (\n"
                    + "  `Id_dbl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `IdProduit_dbl` varchar(20) NOT NULL,\n"
                    + "  `NomProduit_dbl` varchar(150) NOT NULL,\n"
                    + "  `Quantite_dbl` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description_dbl` text ,\n"
                    + "  `FounisseurId` varchar(11) NOT NULL,\n"
                    + "  `MarqueId` varchar(11),\n"
                    + "  `CategorieId` varchar(11) NOT NULL,\n"
                    + "  `Prix_dbl` varchar(100) ,\n"
                    + "  `UtilisateurId_dbl` varchar(11) NOT NULL,\n"
                    + "  `Date_dbl` date,\n"
                    + "  `Unite_dbl` varchar(30) NOT NULL, \n"
                    + "  `nombrepp_dbl` varchar(50) , \n"
                    + "  `unitepp_dbl` varchar(50) , \n"
                    + "  `nombreppm_dbl` varchar(50) , \n"
                    + "  `uniteppm_dbl` varchar(50) , \n"
                    + "  PRIMARY KEY (`Id_dbl`),\n"
                    + "  UNIQUE INDEX `Id_dbl` (`Id_dbl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dbl_utilisateur` (\n"
                    + "  `Id_dbl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nom_dbl` VARCHAR(20) NOT NULL,\n"
                    + "  `Prenom_dbl` VARCHAR(100) ,\n"
                    + "  `Email_dbl` VARCHAR(100) ,\n"
                    + "  `Telephone_dbl` VARCHAR(100) ,\n"
                    + "  `Addresse_dbl` text,\n"
                    + "  `Mdp_dbl` VARCHAR(45),\n"
                    + "  `photo_dbl` mediumblob,\n"
                    + "  `CreateurID_dbl` int(11),\n"
                    + "  PRIMARY KEY (`Id_dbl`),\n"
                    + "  UNIQUE INDEX `Id_dbl` (`Id_dbl` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`dbl_droitUtilisateur` (\n"
                    + "  `Id_dbl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AjoutProduit_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutFournisseur_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutMarque_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutCategory_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutQuantite_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `AjoutClient_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourProduit_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourFournisseur_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourMarque_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourCategory_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourQuantite_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `MisJourClient_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `VendreProduit_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `GererUtilisateur_dbl` tinyint(1) DEFAULT NULL,\n"
                    + "  `UtilisateurId_dbl` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dbl`),\n"
                    + "  UNIQUE INDEX `Id_dbl` (`Id_dbl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dbl_Vente` (\n"
                    + "  `Id_dbl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VenteId_dbl` varchar(10) NOT NULL,\n"
                    + "  `AcheteurId_dbl` varchar(11) ,\n"
                    + "  `ProduitId_dbl` varchar(11) NOT NULL,\n"
                    + "  `Quantite_dbl` int(10) NOT NULL,\n"
                    + "  `TotalPrix_dbl` double NOT NULL,\n"
                    + "  `Garantie_dbl` varchar(20),\n"
                    + "  `DateDVente_dbl` date NOT NULL,\n"
                     + "  `Unite_dbl` varchar(30) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id_dbl`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Fournisseur` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `NomFournisseur` varchar(100) DEFAULT NULL,\n"
                    + "  `TelFournisseur` text DEFAULT NULL,\n"
                    + "  `AdressFournisseur` text DEFAULT NULL,\n"
                    + "  `DescripFournisseur` text DEFAULT NULL,\n"
                    + "  `CreateurID` int(11) DEFAULT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`unite` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `unite` varchar(70) DEFAULT NULL,\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Marque` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `NomMarque` varchar(70) DEFAULT NULL,\n"
                    + "  `Description` text DEFAULT NULL,\n"
                    + "  `FournisseurID` varchar(20)  DEFAULT NULL,\n"
                    + "  `CreateurId` int DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists " + db + ".`Categorie` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `NomCategory` varchar(70) DEFAULT NULL,\n"
                    + "  `DescriptionCategory` text DEFAULT NULL,\n"
                    + "  `MarqueId` varchar(20) DEFAULT NULL,\n"
                    + "  `FournisseurId` int(11) DEFAULT NULL,\n"
                    + "  `CreateurId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dml_produitsfinies` (\n"
                    + "  `Id_dml` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Produit` varchar(150) NOT NULL,\n"
                    + "  `Report` varchar(250) , \n"
                    + "  `NombreUnite` varchar(250) NOT NULL,\n"
                    + "  `Unite` varchar(100) ,\n"
                    + "  `Production` varchar(100) ,\n"
                    + "  `Amical` varchar(250),\n"
                    + "  `Stock` varchar(250),\n"
                    + "  `Date` date,\n"
                    + "  `QuantiteIngredients` varchar(250) ,\n"
                    + "  `idIngredients` varchar(11),\n"
                    + "  PRIMARY KEY (`Id_dml`),\n"
                    + "  UNIQUE INDEX `Id_dml` (`Id_dml` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dgl_produitsfinies` (\n"
                    + "  `Id_dgl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Produit` varchar(150) NOT NULL,\n"
                     + "  `Report` varchar(250) , \n"
                    + "  `NombreUnite` varchar(250) NOT NULL,\n"
                    + "  `Unite` varchar(100) ,\n"
                    + "  `Production` varchar(100) ,\n"
                   + "  `Amical` varchar(250),\n"
                    + "  `Stock` varchar(250),\n"
                    + "  `Date` date,\n"
                    + "  `QuantiteIngredients` varchar(250) ,\n"
                    + "  `idIngredients` varchar(11) ,\n"
                    + "  PRIMARY KEY (`Id_dgl`),\n"
                    + "  UNIQUE INDEX `Id_dgl` (`Id_dgl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dbl_produitsfinies` (\n"
                   + "  `Id_dbl` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Produit` varchar(150) NOT NULL,\n"
                    + "  `Report` varchar(250) , \n"
                    + "  `NombreUnite` varchar(250) NOT NULL,\n"
                    + "  `Unite` varchar(100) ,\n"
                    + "  `Production` varchar(100) ,\n"
                   + "  `Amical` varchar(250),\n"
                    + "  `Stock` varchar(250),\n"
                    + "  `Date` date,\n"
                    + "  `QuantiteIngredients` varchar(250) ,\n"
                    + "  `idIngredients` varchar(11),\n"
                    + "  PRIMARY KEY (`Id_dbl`),\n"
                    + "  UNIQUE INDEX `Id_dbl` (`Id_dbl` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpt_produitsfinies` (\n"
                   + "  `Id_dpt` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Produit` varchar(150) NOT NULL,\n"
                    + "  `Report` varchar(250) , \n"
                    + "  `NombreUnite` varchar(250) NOT NULL,\n"
                    + "  `Unite` varchar(100) ,\n"
                    + "  `Production` varchar(100) ,\n"
                   + "  `Amical` varchar(250),\n"
                    + "  `Stock` varchar(250),\n"
                    + "  `Date` date,\n"
                    + "  `QuantiteIngredients` varchar(250) ,\n"
                    + "  `idIngredients` varchar(11),\n"
                    + "  PRIMARY KEY (`Id_dpt`),\n"
                    + "  UNIQUE INDEX `Id_dpt` (`Id_dpt` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpz_produitsfinies` (\n"
                   + "  `Id_dpz` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Produit` varchar(150) NOT NULL,\n"
                    + "  `Report` varchar(250) , \n"
                    + "  `NombreUnite` varchar(250) NOT NULL,\n"
                    + "  `Production` varchar(100) ,\n"
                    + "  `Amical` varchar(250),\n"
                    + "  `Stock` varchar(250),\n"
                    + "  `Date` date,\n"
                    + "  `QuantiteIngredients` varchar(250) ,\n"
                    + "  `idIngredients` varchar(11),\n"
                    + "  PRIMARY KEY (`Id_dpz`),\n"
                    + "  UNIQUE INDEX `Id_dpz` (`Id_dpz` ASC));");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dml_ingredients` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingredients` varchar(10) NOT NULL,\n"                    
                    + "  `Unite` varchar(250) NOT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`))");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpt_ingredients` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingredients` varchar(10) NOT NULL,\n"
                    + "  `Unite` varchar(250) NOT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`))");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dgl_ingredients` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingredients` varchar(10) NOT NULL,\n"
                    + "  `Unite` varchar(250) NOT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`))");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dpz_ingredients` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingredients` varchar(10) NOT NULL,\n"
                    + "  `Unite` varchar(11) NOT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`))");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS " + db + ".`dbl_ingredients` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingredients` varchar(10) NOT NULL,\n"
                    + "  `Unite` varchar(250) NOT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`))");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("COMMIT;");
            pst.execute();
            long arrive=System.currentTimeMillis();
            System.out.println("Bdd crée avec succes dans " + ((arrive-depart)/1000) + " seconde");

        } catch (SQLException e) {
            System.err.println("Ato ai: " + e);
            Logger.getLogger(bddModel.class.getName()).log(Level.SEVERE, null, e);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/app/bddConfig.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Config BDD Amicale");
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(bddModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
