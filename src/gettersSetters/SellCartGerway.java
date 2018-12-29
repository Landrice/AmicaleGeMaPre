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
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Ralande
 */
public class SellCartGerway {

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    Connection con1;
    PreparedStatement pst1;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void save(SellCart sellCart, String sigle) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into " + db + "." + sigle + "_vente values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, sellCart.sellID);
            pst.setString(3, sellCart.customerID);
            pst.setString(4, sellCart.productID);
            pst.setString(5, sellCart.quantity);
            pst.setString(6, sellCart.totalPrice);
            pst.setString(7, sellCart.sellDate);
            //         pst.setString(10, sellCart.sellerID);
            pst.setString(8, sellCart.warrentyVoidDate);
            pst.setString(9, sellCart.unite);
            pst.executeUpdate();
            pst.close();
            con.close();

//            Dialogs.create().title(null).masthead("Soled").message("Soled Sucessfuly").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showInformation();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }

    public void saveIngredients(SellCart sellCart, String sigle) {
        con = dbCon.geConnection();
        try {

            pst = con.prepareStatement("select * from " + db + "." + sigle + "_ingredients where Ingredients=?");
            pst.setString(1, sellCart.sellnom);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("ingredients already exist");
            } else {
                pst1 = con.prepareStatement("insert into " + db + "." + sigle + "_ingredients values(?,?,?,?)");
                pst1.setString(1, null);
                pst1.setString(2, sellCart.sellnom);
                pst1.setString(3, sellCart.unite);
                pst1.setString(4, "1111-11-11");
                pst1.executeUpdate();                
                pst1.close();
                pst.close();
                con.close();
                System.out.println("Ajout ingredients succès");
            }

//            Dialogs.create().title(null).masthead("Soled").message("Soled Sucessfuly").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showInformation();
        } catch (SQLException ex) {
            System.out.println("Error adding ingredients to table ...");
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void view(SellCart sellCart, String sigle) {
        con = dbCon.geConnection();
        SQLSyntax sql = new SQLSyntax();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_Vente");
            rs = pst.executeQuery();
            while (rs.next()) {
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.customerName = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.quantity = rs.getString(5);
                sellCart.totalPrice = rs.getString(6);
                sellCart.warrentyVoidDate = rs.getString(7);
                sellCart.sellDate = rs.getString(8);
                sellCart.unite = rs.getString(9);
                sellCart.givenProductID = sql.getNameR(sellCart.productID, sellCart.givenProductID, "" + sigle + "_produits", sigle);
                sellCart.productname = sql.getName3a(sellCart.productID, sellCart.productname, "" + sigle + "_produits", sigle);
                sellCart.sellerName = sql.getNameR(sellCart.sellerID, sellCart.sellerName, "" + sigle + "_vente", sigle);
                // sellCart.customerName = sql.getName3(sellCart.sellerID, sellCart.sellerName, ""+sigle+"_vente",sigle);

                System.out.println("Numero 01 " + sellCart.givenProductID);
                System.out.println("Numero 02 " + sellCart.sellerName);
                System.out.println("Numero 03 " + sellCart.customerName);
                System.out.println("Numero 04 " + sellCart.productID);

                sellCart.soldList.addAll(new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate, sellCart.productname, sellCart.unite));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void firstTenView(SellCart sellCart, String sigle) {
        con = dbCon.geConnection();
        SQLSyntax sql = new SQLSyntax();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_Vente limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.quantity = rs.getString(5);
                sellCart.totalPrice = rs.getString(6);
                sellCart.warrentyVoidDate = rs.getString(7);
                sellCart.sellDate = rs.getString(8);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "" + sigle + "_Produits");
                sellCart.productname = sql.getNameR(sellCart.productID, sellCart.givenProductID, "" + sigle + "_produits", sigle);
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "" + sigle + "_Utilisateur");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Acheteur");

                sellCart.soldList.addAll(new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate, sellCart.productname, sellCart.unite));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(SellCart sellCart, String sigle) {
        con = dbCon.geConnection();
        sellCart.carts.clear();
        SQLSyntax sql = new SQLSyntax();
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_Vente where VenteId_" + sigle + " like ?");
            pst.setString(1, "%" + sellCart.sellID + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.quantity = rs.getString(5);
                sellCart.totalPrice = rs.getString(6);
                sellCart.warrentyVoidDate = rs.getString(7);
                sellCart.sellDate = rs.getString(8);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "" + sigle + "_Produits");
                sellCart.productname = sql.getNameR(sellCart.productID, sellCart.givenProductID, "" + sigle + "_produits", sigle);
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "" + sigle + "_Utilisateur");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "" + sigle + "_Acheteur");

                sellCart.soldList.addAll(new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate, sellCart.productname, sellCart.unite));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
