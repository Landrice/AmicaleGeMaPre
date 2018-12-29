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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class CurrentProductBLL {

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    SQLSyntax sql = new SQLSyntax();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();

    public void save(CurrentProduct currentProduct, String sigle) {
        if (isUniqName(currentProduct, sigle)) {
            currentProductGetway.save(currentProduct, sigle);
        }

    }

    public void saveFinish(CurrentProduct currentProduct, String sigle) {
        if (isUniqNameFinish(currentProduct, sigle)) {
            currentProductGetway.saveFinsh(currentProduct, sigle);
        }

    }
    public void saveToday(CurrentProduct currentProduct, String sigle) {
        if (isUniqNameFinish(currentProduct, sigle)) {
            currentProductGetway.reduction(currentProduct, sigle);
            currentProductGetway.saveToday(currentProduct, sigle);                       
        }
    }
    public void updateToday(CurrentProduct currentProduct, String sigle) {            
            currentProductGetway.updateToday(currentProduct, sigle);                       
        }

    public void updatepr(CurrentProduct currentProduct, String sigle){
        try {
            pst = con.prepareStatement("delete from " + db + "." + sigle + "_produitsfinies where Date='1111-11-11'");
            pst.executeUpdate();
            pst.close();
            con.close();
            currentProductGetway.saveFinsh(currentProduct, sigle);
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(CurrentProduct currentProduct, String sigle) {
        if (isNotNull(currentProduct)) {
            if (isUpdate(currentProduct, sigle)) {
                if (checkUpdateCondition(currentProduct, sigle)) {
                    currentProductGetway.update(currentProduct, sigle);
                } else if (isUniqName(currentProduct, sigle)) {
                    currentProductGetway.update(currentProduct, sigle);
                }
            } else {
                System.out.println("Erreur: else ici");
            }
        } else {
            System.out.println("Erreur, else de ifNotNull");
        }
    }

    public void updateFinish(CurrentProduct currentProduct, String sigle) {
      //  -- -- -- -- -- --
        if (isUpdateFinish(currentProduct, sigle)) {
            if (checkUpdateConditionFinish(currentProduct, sigle)) {
                currentProductGetway.updateFinish(currentProduct, sigle);
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Sucess");
                aler.setHeaderText("Mise à Jour : Mise à jour réussi");
                aler.setContentText("Produits à jour");
                aler.initStyle(StageStyle.UNDECORATED);
                aler.showAndWait();
            } else {
                currentProductGetway.updateFinish(currentProduct, sigle);
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Sucess");
                aler.setHeaderText("Mise à Jour : Mise à jour réussi");
                aler.setContentText("Produits à jour");
                aler.initStyle(StageStyle.UNDECORATED);
                aler.showAndWait();
            }
        }
    }

    public boolean isUniqName(CurrentProduct currentProduct, String sigle) {
        System.out.println("WE ARE IS IS UNIT NAME");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where IdProduit_" + sigle + "=?");
            pst.setString(1, currentProduct.idProduit);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur: Non Unique");
                alert.setContentText("Produit id" + "  '" + currentProduct.idProduit + "' " + "existant");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e);
        }
        return isUniqname;
    }

    public boolean isUniqNameFinish(CurrentProduct currentProduct, String sigle) {
        System.out.println("WE ARE IS IS UNIT NAME");
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Id_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur: Non Unique");
                alert.setContentText("Produit existant, veuillez modifier le produit");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e);
        }
        return true;
    }

    public boolean isUpdate(CurrentProduct currentProduct, String sigle) {
        System.out.println("WE ARE IS IS UPDTE");
        boolean isUpdate = false;
        try {
            System.out.println("WE ARE IS IS UPDTE ---01");
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where Id_" + sigle + "=? and IdProduit_" + sigle + "=? and NomProduit_" + sigle + "=? and Quantite_" + sigle + "=? and Description_" + sigle + "=? and FounisseurId=? and MarqueId=? "
                    + "and CategorieId=? and Prix_" + sigle + " =? and UtilisateurId_" + sigle + "=? and Date_" + sigle + "=? and Unite_" + sigle + "=?");
            pst.setString(1, currentProduct.id);
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
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return isUpdate;

    }

    public boolean isUpdateFinish(CurrentProduct currentProduct, String sigle) {
        System.out.println("WE ARE IS IS UPDTE");
        boolean isUpdate = false;
        try {
            System.out.println("WE ARE IS IS UPDTE ---01");
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where Id_" + sigle + "=? and NomProduit_" + sigle + "=? and Quantite_" + sigle + "=? "
                    + "and Categorie_" + sigle + "=? and Prix_" + sigle + " =? and UtilisateurId_" + sigle + "=? and Date_" + sigle + "=? ");
            pst.setString(1, currentProduct.id);
            pst.setString(2, currentProduct.nomProduit);
            pst.setString(3, currentProduct.quantite);
            pst.setString(4, currentProduct.categorieNom);
            pst.setString(5, currentProduct.prix);
            pst.setString(6, currentProduct.utilisateurID);
            pst.setString(7, currentProduct.date);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return isUpdate;

    }

    public boolean checkUpdateCondition(CurrentProduct currentProduct, String sigle) {
        boolean isTrueUpdate = false;
        if (isUpdate(currentProduct, sigle)) {
            try {
                pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where id_" + sigle + "=? and IdProduit_" + sigle + "=?");
                pst.setString(1, currentProduct.id);
                pst.setString(2, currentProduct.idProduit);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("else currentProductBLL");
        }
        return isTrueUpdate;
    }

    public boolean checkUpdateConditionFinish(CurrentProduct currentProduct, String sigle) {
        boolean isTrueUpdate = false;
        if (isUpdateFinish(currentProduct, sigle)) {
            try {
                pst = con.prepareStatement("select * from " + db + "." + sigle + "_produitsfinies where id_" + sigle + "=? and NomProduit_" + sigle + "=?");
                pst.setString(1, currentProduct.id);
                pst.setString(2, currentProduct.nomProduit);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("else currentProductBLL");
        }
        return isTrueUpdate;
    }

    public boolean isNotNull(CurrentProduct currentProduct) {

        boolean isNotNull = false;
        if (currentProduct.idProduit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("ERREUR : Null");
            alert.setContentText("Veuillez remplir les champs");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            return isNotNull;
        } else {
            System.out.println("Ato izy");
            isNotNull = true;
        }
        return isNotNull;
    }

    public Object delete(CurrentProduct currentProduct, String sigle) {
        if (currentProductGetway.isNotSoled(currentProduct, sigle)) {
            currentProductGetway.delete(currentProduct, sigle);
        } else {
            //noting
        }
        return currentProduct;
    }

    public Object deleteFinish(CurrentProduct currentProduct, String sigle) {
        if (currentProductGetway.isNotSoledFinish(currentProduct, sigle)) {
            currentProductGetway.deleteFinish(currentProduct, sigle);
        } else {
            //noting
        }
        return currentProduct;
    }
}
