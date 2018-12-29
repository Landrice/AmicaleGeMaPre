/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class CurrentProductGetway {

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4;
    PreparedStatement pst5;
    ResultSet rs;
    ResultSet rs2;
    ResultSet rs3;
    ResultSet rs4;
    ResultSet rs5;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    

    SQLSyntax sql = new SQLSyntax();

    public void save(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into " + db + "." + sigle + "_produits values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, currentProduct.idProduit);
            pst.setString(3, currentProduct.nomProduit);
            pst.setString(4, currentProduct.quantite);
            pst.setString(5, currentProduct.description);
            pst.setString(6, currentProduct.fournisseurID);
            pst.setString(7, currentProduct.marqueID);
            pst.setString(8, currentProduct.categorieID);
            pst.setString(9, currentProduct.prix);
            pst.setString(10, currentProduct.utilisateurID);
            pst.setString(11, currentProduct.date);
            pst.setString(12, currentProduct.unite);
            pst.setString(13, currentProduct.nombrepp);
            pst.setString(14, currentProduct.unitepp);
            pst.setString(15, currentProduct.nombreppm);
            pst.setString(16, currentProduct.uniteppm);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");

        }

    }

    public void saveFinsh(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into " + db + "." + sigle + "_produitsfinies values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, currentProduct.nomProduit);
            pst.setString(3, null);
            pst.setString(4, currentProduct.quantite);
            pst.setString(5, null);
            pst.setString(6, null);
            pst.setString(7, null);
            pst.setString(8, null);
            pst.setString(9, currentProduct.date);
            pst.setString(10, currentProduct.ingredientsquantite);
            pst.setString(11, currentProduct.ingredientsid);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");

        }

    }

    public void reduction(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("SELECT QuantiteIngredients,idIngredients FROM " + db + ".`" + sigle + "_produitsfinies` where Produit='" + currentProduct.nomProduit + "' and Date='1111-11-11'");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("riz erreur :" + rs.getString(1));
                Double quantiteingredients = Double.parseDouble(rs.getString(1));
                String id = rs.getString(2);
                Double qtt = quantiteingredients * Double.parseDouble(currentProduct.production);
                System.out.println("reduction rs eto");
                try {

                    pst4 = con.prepareStatement("SELECT Ingredients FROM " + db + ".`" + sigle + "_ingredients` where Id='" + rs.getString(2) + "'");
                    rs4 = pst4.executeQuery();
                    while (rs4.next()) {
                        try {
                            pst2 = con.prepareStatement("SELECT Quantite_" + sigle + " FROM " + db + ".`" + sigle + "_produits` where NomProduit_" + sigle + "='" + rs4.getString(1) + "'");
                            rs2 = pst2.executeQuery();
                            while (rs2.next()) {
                                Double qttcurent = Double.parseDouble(rs2.getString(1));
                                System.out.println("resultat rs2 is: " + rs2.getString(1));
                                Double qttResult = qttcurent - qtt;
                                System.out.println("reduction rs2 eto");
                                try {
                                    pst3 = con.prepareStatement("UPDATE " + db + "." + sigle + "_produits SET Quantite_" + sigle + "=? where NomProduit_" + sigle + "='" + rs4.getString(1) + "'");
                                    pst3.setString(1, String.valueOf(qttResult));
                                    System.out.println("produits is :" + rs4.getString(1));
                                    System.out.println("resultat est is: " + String.valueOf(qttResult));
                                    System.out.println("reduction rs3 eto avec nom =" + currentProduct.nomProduit);
                                    pst3.executeUpdate();

                                } catch (SQLException e) {
                                    Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }
                        } catch (NumberFormatException | SQLException e) {
                            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }

                } catch (SQLException e) {
                    Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public void saveToday(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into " + db + "." + sigle + "_produitsfinies values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, currentProduct.nomProduit);
            pst.setString(3, currentProduct.report);
            pst.setString(4, currentProduct.nombreunite);
            pst.setString(5, currentProduct.uniteproduit);
            pst.setString(6, currentProduct.production);
            pst.setString(7, currentProduct.amical);
            pst.setString(8, currentProduct.stock);
            pst.setString(9, currentProduct.date);
            pst.setString(10, currentProduct.ingredientsquantite);
            pst.setString(11, currentProduct.ingredientsid);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");

        }

    }

    public void updateToday(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update " + db + "." + sigle + "_produitsfinies set Report=?,NombreUnite=?,Unite=?,Production=?,Amical=?,Stock=? where Produit=? and Date=? ");
            pst.setString(1, currentProduct.report);
            pst.setString(2, currentProduct.nombreunite);
            pst.setString(3, currentProduct.uniteproduit);
            pst.setString(4, currentProduct.production);
            pst.setString(5, currentProduct.amical);
            pst.setString(6, currentProduct.stock);
            pst.setString(7, currentProduct.nomProduit);
            pst.setString(8, currentProduct.date);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Too Many Connection");

        }

    }

    public void view(CurrentProduct currentProduct, String sigle) {
        currentProduct.currentProductList.clear();
        con = dbCon.geConnection();
        String requete;
        if (sigle.equals("dml")) {
            requete = "SELECT SQL_NO_CACHE * FROM " + db + "." + sigle + "_produits ORDER BY Quantite_" + sigle + "";
        } else {
            requete = "SELECT SQL_NO_CACHE * FROM " + db + "." + sigle + "_produits WHERE !Quantite_" + sigle + "='0' AND !Quantite_" + sigle + "='0.0' ORDER BY Quantite_" + sigle + "";
        }

        try {
            pst = con.prepareStatement(requete);
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewFinishProduct(CurrentProduct currentProduct, String sigle) {
        currentProduct.currentProductList.clear();
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("SELECT SQL_NO_CACHE * FROM " + db + "." + sigle + "_produitsfinies");
            rs = pst.executeQuery();
            while (rs.next()) {

//                currentProduct.id = rs.getString(1);
//                currentProduct.nomProduit = rs.getString(2);
//                currentProduct.quantite = rs.getString(3);
//                currentProduct.categorieID = rs.getString(4);
//                currentProduct.prix = rs.getString(5);
//                currentProduct.utilisateurID = rs.getString(6);
//                currentProduct.date = rs.getString(7);
//                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
//                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieID, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
//            
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error to display productFinish");
            System.err.println(e);
        }

    }

    public void selectedView(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
//                id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedViewEnd(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Produit=? and Date='1111-11-11'");
            pst.setString(1, currentProduct.nomProduit);
            rs = pst.executeQuery();
            while (rs.next()) {
//                id = rs.getString(1);
                currentProduct.nomProduit = rs.getString(2);
//                currentProduct.quantite = rs.getString(3);
                currentProduct.nombreunite = rs.getString(4);
                currentProduct.ingredientsid = rs.getString(11);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewFistTen(CurrentProduct currentProduct, String tableproduits, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();

        String requete;
        if (sigle.equals("dml")) {
            requete = "SELECT SQL_NO_CACHE * FROM " + db + "." + sigle + "_produits ORDER BY Quantite_" + sigle + "";
        } else {
            requete = "SELECT SQL_NO_CACHE * FROM " + db + "." + sigle + "_produits WHERE !Quantite_" + sigle + "='0' AND !Quantite_" + sigle + "='0.0' ORDER BY Quantite_" + sigle + "";
        }

        try {
            pst = con.prepareStatement(requete);
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getNameR(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur", sigle);
                currentProduct.marqueNom = sql.getNameR(currentProduct.marqueID, currentProduct.marqueNom, "marque", sigle);
                currentProduct.categorieNom = sql.getNameR(currentProduct.categorieID, currentProduct.categorieNom, "categorie", sigle);
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* public void viewFistTenOrderByCategorie(CurrentProduct currentProduct, String s) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".produits, " + db + ".categorietpa WHERE categorietpa.categorieTPA='"+s+"' and categorietpa.IdProduit=produits.IdProduit");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur",sigle);
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
 /*   public void viewFistTenOrderByAlert(CurrentProduct currentProduct) {
        currentProduct.currentProductList.clear();
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement("SELECT * FROM " + db + ".produits, " + db + ".alert WHERE " + db + ".alert.IdProduit = " + db + ".produits.IdProduit");
            rs = pst.executeQuery();
            while (rs.next()) {
                
               /* String idproduitBase=rs.getString(1);
                int idproduitBasein=Integer.parseInt(idproduitBase);

                if (Integer.parseInt(rs.getString(4)) < Integer.parseInt(rs.getString(14))) {
                
                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite));
            
           // pst.close();
           // con.close();
           // rs.close();
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void searchView(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where IdProduit_" + sigle + " like ? or NomProduit_" + sigle + " like ? ");
            pst.setString(1, "%" + currentProduct.idProduit + "%");
            pst.setString(2, "%" + currentProduct.nomProduit + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchViewm(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where IdProduit_" + sigle + " like ? or NomProduit_" + sigle + " like ? AND !Quantite_" + sigle + "='0' AND !Quantite_" + sigle + "='0.0'");
            pst.setString(1, "%" + currentProduct.idProduit + "%");
            pst.setString(2, "%" + currentProduct.nomProduit + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "categorie");
                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchViewFinishProduct(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Id_" + sigle + " like ? or NomProduit_" + sigle + " like ?");
            pst.setString(1, "%" + currentProduct.id + "%");
            pst.setString(2, "%" + currentProduct.nomProduit + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.nomProduit = rs.getString(2);
                currentProduct.quantite = rs.getString(3);
                currentProduct.categorieID = rs.getString(4);
                currentProduct.prix = rs.getString(5);
                currentProduct.utilisateurID = rs.getString(6);
                currentProduct.date = rs.getString(7);
                currentProduct.nom = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur", sigle);
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombrepp, currentProduct.unitepp));

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBySupplyer(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.fournisseurID = sql.getIdNo(currentProduct.fournisseurNom, currentProduct.fournisseurID, "fournisseur", "NomFournisseur");
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where FounisseurId=?");
            pst.setString(1, currentProduct.fournisseurID);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getNameR(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur", sigle);
                currentProduct.marqueNom = sql.getNameR(currentProduct.marqueID, currentProduct.marqueNom, "marque", sigle);
                currentProduct.categorieNom = sql.getNameR(currentProduct.categorieID, currentProduct.categorieNom, "Categorie", sigle);
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByBrand(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.fournisseurID = sql.getIdNo(currentProduct.fournisseurNom, currentProduct.fournisseurID, "fournisseur", "NomFournisseur");
        currentProduct.fournisseurID = sql.getBrandID(currentProduct.fournisseurID, currentProduct.marqueID, currentProduct.marqueNom);
        System.out.println("Brand ID: " + currentProduct.marqueID);

        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where MarqueId=?");
            pst.setString(1, currentProduct.marqueID);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getNameR(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur", sigle);
                currentProduct.marqueNom = sql.getNameR(currentProduct.marqueID, currentProduct.marqueNom, "marque", sigle);
                currentProduct.categorieNom = sql.getNameR(currentProduct.categorieID, currentProduct.categorieNom, "categorie", sigle);
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByCatagory(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.fournisseurID = sql.getIdNo(currentProduct.fournisseurNom, currentProduct.fournisseurID, "fournisseur", "NomFournisseur");
        currentProduct.marqueID = sql.getBrandID(currentProduct.fournisseurID, currentProduct.marqueID, currentProduct.marqueNom);
        currentProduct.categorieID = sql.getCatagoryId(currentProduct.fournisseurID, currentProduct.marqueID, currentProduct.categorieID, currentProduct.categorieNom);
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where CategorieId=?");
            pst.setString(1, currentProduct.categorieID);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getNameR(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur", sigle);
                currentProduct.marqueNom = sql.getNameR(currentProduct.marqueID, currentProduct.marqueNom, "marque", sigle);
                currentProduct.categorieNom = sql.getNameR(currentProduct.categorieID, currentProduct.categorieNom, "categorie", sigle);
                currentProduct.nom = sql.getName(currentProduct.utilisateurID, currentProduct.nom, "" + sigle + "_utilisateur");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.idProduit, currentProduct.nomProduit, currentProduct.quantite, currentProduct.description, currentProduct.fournisseurNom, currentProduct.marqueNom, currentProduct.categorieNom, currentProduct.prix, currentProduct.nom, currentProduct.date, currentProduct.unite, currentProduct.nombrepp, currentProduct.unitepp, currentProduct.nombreppm, currentProduct.uniteppm));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sviewcmb(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select Unite_" + sigle + ",unitepp_" + sigle + ",nombrepp_" + sigle + " from" + db + "." + sigle + "_produits where IdProduit_" + sigle + "=?");
            pst.setString(1, currentProduct.idProduit);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.unite = rs.getString(1);
                currentProduct.unitepp = rs.getString(2);
                currentProduct.nombrepp = rs.getString(3);
            }
        } catch (Exception e) {
        }
    }

    public void sView(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where IdProduit_" + sigle + "=?");
            pst.setString(1, currentProduct.idProduit);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.idProduit = rs.getString(2);
                currentProduct.nomProduit = rs.getString(3);
                currentProduct.quantite = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.fournisseurID = rs.getString(6);
                currentProduct.marqueID = rs.getString(7);
                currentProduct.categorieID = rs.getString(8);
                currentProduct.prix = rs.getString(9);
                currentProduct.utilisateurID = rs.getString(10);
                currentProduct.date = rs.getString(11);
                currentProduct.unite = rs.getString(12);
                currentProduct.nombrepp = rs.getString(13);
                currentProduct.unitepp = rs.getString(14);
                currentProduct.nombreppm = rs.getString(15);
                currentProduct.uniteppm = rs.getString(16);
                currentProduct.fournisseurNom = sql.getName(currentProduct.fournisseurID, currentProduct.fournisseurNom, "fournisseur");
                currentProduct.marqueNom = sql.getName(currentProduct.marqueID, currentProduct.marqueNom, "marque");
                currentProduct.categorieNom = sql.getName(currentProduct.categorieID, currentProduct.categorieNom, "Categorie");
                currentProduct.utilisateurID = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, sigle + "_utilisateur", sigle);
                /*  currentProduct.rmaDayesss = sql.getDayes(currentProduct.rmaDayesss, currentProduct.rmaId);
                long dateRMA = Long.parseLong(currentProduct.rmaDayesss);

                currentProduct.garantie = LocalDate.now().plusDays(dateRMA).toString();*/

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sViewFinish(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.nomProduit = rs.getString(2);
                currentProduct.quantite = rs.getString(3);
                currentProduct.categorieNom = rs.getString(4);
                currentProduct.prix = rs.getString(5);
                currentProduct.utilisateurID = rs.getString(6);
                currentProduct.date = rs.getString(7);
                currentProduct.utilisateurID = sql.getNameR(currentProduct.utilisateurID, currentProduct.nom, sigle + "_utilisateur", sigle);
                /*  currentProduct.rmaDayesss = sql.getDayes(currentProduct.rmaDayesss, currentProduct.rmaId);
                long dateRMA = Long.parseLong(currentProduct.rmaDayesss);

                currentProduct.garantie = LocalDate.now().plusDays(dateRMA).toString();*/

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cbSupplyer(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from " + db + ".fournisseur");
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.fournisseurList = rs.getString(2);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update " + db + "." + sigle + "_produits set IdProduit_" + sigle + "=?, NomProduit_" + sigle + "=?, Quantite_" + sigle + "=?, Description_" + sigle + "=?, "
                    + "FounisseurId=?, MarqueId=?, CategorieId=?,"
                    + " Prix_" + sigle + "=?, UtilisateurId_" + sigle + "=?, Date_" + sigle + "=?, Unite_" + sigle + "=?,nombrepp_" + sigle + ",unitepp_" + sigle + "  where Id_" + sigle + "=?");
            pst.setString(1, currentProduct.idProduit);
            pst.setString(2, currentProduct.nomProduit);
            pst.setString(3, currentProduct.quantite);
            pst.setString(4, currentProduct.description);
            pst.setString(5, currentProduct.fournisseurID);
            pst.setString(6, currentProduct.marqueID);
            pst.setString(7, currentProduct.categorieID);
            pst.setString(8, currentProduct.prix);
            pst.setString(9, currentProduct.utilisateurID);
            pst.setString(10, currentProduct.date);
            pst.setString(11, currentProduct.unite);
            pst.setString(12, currentProduct.nombrepp);
            pst.setString(13, currentProduct.unitepp);
            pst.setString(14, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            //    rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ato erreur sql 01: " + ex);
        }
    }

    public void updateFinish(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update " + db + "." + sigle + "_produitsfinies set NomProduit_" + sigle + "=?, Quantite_" + sigle + "=?, Categorie_" + sigle + "=?, "
                    + " Prix_" + sigle + "=?, UtilisateurId_" + sigle + "=?, Date_" + sigle + "=?  where Id_" + sigle + "=?");
            pst.setString(1, currentProduct.nomProduit);
            pst.setString(2, currentProduct.quantite);
            pst.setString(3, currentProduct.categorieNom);
            pst.setString(4, currentProduct.prix);;
            pst.setString(5, currentProduct.utilisateurID);
            pst.setString(6, currentProduct.date);
            pst.setString(7, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            //    rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ato erreur sql 01: " + ex);
        }
    }

    public void delete(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from " + db + "." + sigle + "_produits where id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteFinish(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from " + db + "." + sigle + "_produitsfinies where id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNotSoled(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        boolean isNotSoled = false;
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_vente where ProduitId_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("AVERTISSMENT : ");
                alert.setContentText("Le produit est figé, vous ne pouvez pas le supprimer");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return isNotSoled;
            }
            rs.close();
            pst.close();
            con.close();
            isNotSoled = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotSoled;
    }

    public boolean isNotSoledFinish(CurrentProduct currentProduct, String sigle) {
        con = dbCon.geConnection();
        boolean isNotSoled = false;
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("AVERTISSMENT : ");
                alert.setContentText("Le produit est figé, vous ne pouvez pas le supprimer");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return isNotSoled;
            }
            rs.close();
            pst.close();
            con.close();
            isNotSoled = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotSoled;
    }

    public void ajoutproduit(String id, String quantite, String date, String sigle) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update " + db + "." + sigle + "_produitsfinies set Quantite_" + sigle + "=?,Date_" + sigle + "=?  where Id_" + sigle + "=?");
            pst.setString(1, quantite);
            pst.setString(2, date);
            pst.setString(3, id);
            pst.executeUpdate();
            pst.close();
            con.close();
            //    rs.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText("REUSSI : ");
            alert.setContentText("Ajout Nombre produits reussi");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ato erreur sql 01: " + ex);
        }
    }
}
