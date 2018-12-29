/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import bdd.BddPropreties;
import bdd.bddConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ralande
 */
public class SellCartBLL {

    SellCartGerway sellCartGerway = new SellCartGerway();

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void sell(SellCart sellCart, String sigle, String sigledestinateur) {

        updateCurrentQuentity(sellCart, sigle);
        updateCurrentQuentityToSell(sellCart, sigledestinateur);
        sellCartGerway.save(sellCart, sigle);
        sellCartGerway.saveIngredients(sellCart, sigledestinateur);

    }

    public void updateCurrentQuentity(SellCart sellCart, String sigle) {
        System.out.println("In Update");
        Double oQ = Double.parseDouble(sellCart.oldQuentity);
        Double nQ = Double.parseDouble(sellCart.quantitymdf);
        Double nbpp = Double.parseDouble(sellCart.nombrepp);
        Double uQ = (oQ - nQ);
        Double vf=uQ/nbpp;
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        sellCart.sellDate=String.valueOf(LocalDate.now());
        try {
            System.out.println("Mise à jour en cours");
            pst = con.prepareStatement("update " + db + "." + sigle + "_produits set Quantite_" + sigle + "=?, Date_"+sigle+"=? where Id_" + sigle + "=?");
            pst.setString(1, updatedQuentity);
            pst.setString(2, sellCart.sellDate);
            pst.setString(3, sellCart.Id);
            pst.executeUpdate();
            System.out.println("Update - Complate");
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCurrentQuentityToSell(SellCart sellCart, String sigle) {
        System.out.println("In Update");
        
        try {
            System.out.println("Mise à jour en cours");
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_produits where IdProduit_" + sigle + "='" + sellCart.productID + "'");
            rs = pst.executeQuery();
            sellCart.sellDate=String.valueOf(LocalDate.now());
            while (rs.next()) {
                Double oQ = Double.parseDouble(rs.getString(4));
                Double nQ = Double.parseDouble(sellCart.quantity);
                Double uQ = (oQ + nQ);
                System.out.println("NOW QUENTITY IS: " + uQ);
                String updatedQuentity = String.valueOf(uQ);
                pst2 = con.prepareStatement("update " + db + "." + sigle + "_produits set Quantite_" + sigle + "=?, Date_"+sigle+"=?,Unite_"+sigle+"=? where IdProduit_" + sigle + "=?");
                pst2.setString(1, updatedQuentity);
                pst2.setString(2, sellCart.sellDate);
                pst2.setString(3, sellCart.unitemdf);
                pst2.setString(4, sellCart.productID);                
                pst2.executeUpdate();
                System.out.println("Update + Complate");
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
