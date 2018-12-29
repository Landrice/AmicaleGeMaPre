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

/**
 *
 * @author Ralande
 */
public class ProduitFinishGetway {

    bddConnection dbConnection = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public void view(ProduitsFinies users, String sigle, String date) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select DISTINCT Produit from "+db+"."+sigle+"_produitsfinies where Date='"+date+"' order by Produit");
            rs = pst.executeQuery();
            while (rs.next()) {
                //users.id = rs.getString(1);
                users.produit = rs.getString(1);
//                users.report = rs.getString(3);
//                users.nombreunite = rs.getString(4);
//                users.production = rs.getString(5);
//                users.amicale = rs.getString(6);
//                users.stock = rs.getString(7);
//                users.date = rs.getString(8);
//                users.quattiteingredients = rs.getString(9);
//                users.idingredients = rs.getString(10);
                users.prLists.addAll(new ListProduitsFinies(users.produit));
                //users.prLists.addAll(new ListProduitsFinies(users.id, users.produit, users.report, users.nombreunite, users.production, users.amicale, users.stock, users.date, users.quattiteingredients, users.idingredients));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void selectedview(ProduitsFinies users, String sigle) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+sigle+"_produitsfinies where Produit=? order by Produit");
            pst.setString(1, users.produit);
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.produit = rs.getString(2);
                users.report = rs.getString(3);
                users.nombreunite = rs.getString(4);
                users.unite = rs.getString(5);
                users.production = rs.getString(6);
                users.amicale = rs.getString(7);
                users.stock = rs.getString(8);
                users.date = rs.getString(9);
                users.quattiteingredients = rs.getString(10);
                users.idingredients = rs.getString(11);
                
                //users.prLists.addAll(new ListProduitsFinies(users.id, users.produit, users.report, users.nombreunite, users.production, users.amicale, users.stock, users.date, users.quattiteingredients, users.idingredients));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
