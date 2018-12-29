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
public class IngredientsGetWay {
    bddConnection dbConnection = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    
    public void view(IngredientsPFinish ingredients, String sigle) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+sigle+"_ingredients");
            rs = pst.executeQuery();
            while (rs.next()) {
                ingredients.id = rs.getString(1);
                ingredients.ingredients = rs.getString(2);
                ingredients.unite = rs.getString(3);
                ingredients.date = rs.getString(4);
                ingredients.ingredientsList.addAll(new ListIngredients(ingredients.id, ingredients.ingredients, ingredients.unite));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        

    }
   
}
