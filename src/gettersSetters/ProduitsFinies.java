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
public class ProduitsFinies {
     public String id;
    public String produit;
    public String report;
    public String nombreunite;
    public String production;
    public String unite;
    public String amicale;
    public String stock;
    public String date;
    public String quattiteingredients;
    public String idingredients;
    public ObservableList<String> allpr = FXCollections.observableArrayList();
    public ObservableList<ListProduitsFinies> prLists = FXCollections.observableArrayList();
}
