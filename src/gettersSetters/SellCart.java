/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ralande
 */
public class SellCart {
     public String Id;
    public String sellID;
    public String customerID;
    public String productID;
    public String pursesPrice;
    public String sellPrice;
    public String quantity;
    public String quantitymdf;
    public String unite;
    public String unitemdf;
    public String totalPrice;
    public String pursrsDate;
    public String warrentyVoidDate;
    public String sellerID;
    public String sellDate;
    public String oldQuentity;
    public String nombrepp;
    public String sellnom;
    public String dateingredients;
    //Names
    
    public String customerName;
    public String sellerName;
    public String givenProductID;
        public String productname;
    
    
    
    public ObservableList<ListPreSell> carts = FXCollections.observableArrayList();
    public ObservableList<ListSold> soldList = FXCollections.observableArrayList();
}
