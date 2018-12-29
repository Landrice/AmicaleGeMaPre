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
 * @author Admin
 */
public class CurrentProduct {
    public String id;
    public String idProduit;
    public String nomProduit;
    public String quantite;
    public String description;
    public String fournisseurID;
    public String marqueID;
    public String categorieID;
    public String prix;
    public String utilisateurID;
    public String date;
    
    public String garantie;
    public String fournisseurNom;
    public String marqueNom;
    public String categorieNom;
    public String nom;
    public String fournisseurList;
    public String unite;
    public String nombrepp;
    public String unitepp;
    public String nombreppm;
    public String uniteppm;
    
    public String ingredientsid;
    public String ingredientsnom;
    public String ingredientsquantite;
    
    public String nombreunite;
    public String production;
    public String uniteproduit;
    public String amical;
    public String stock;
    public String report;
    
    public ObservableList<ListProduct> currentProductList = FXCollections.observableArrayList();
}
