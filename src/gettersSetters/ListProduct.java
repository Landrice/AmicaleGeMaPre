/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

/**
 *
 * @author Admin
 */
public class ListProduct {
    public String id;
    public String idProduit;
    public String nomProduit;
    public String quantite;
    public String description;
    public String fournisseurID;
    public String marqueID;
    public String categorieID;
    public String prix;
    
    public String discountInCash;
    public String discountInPersent;
    
    public String utilisateur;
    public String date;
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

    public ListProduct(String id, String idProduit, String nomProduit, String quantite, String description, String fournisseurID, String marqueID, String categorieID, String prix, String utilisateur, String date, String unite,String nombrepp, String unitepp,String nombreppm, String uniteppm) {
        this.id = id;
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.description = description;
        this.fournisseurID = fournisseurID;
        this.marqueID = marqueID;
        this.categorieID = categorieID;
        this.prix = prix;
        this.utilisateur = utilisateur;
        this.date = date;
        this.unite=unite;
        this.nombrepp=nombrepp;
        this.unitepp=unitepp;
        this.nombrepp=nombreppm;
        this.unitepp=uniteppm;
    }

    public String getId() {
        return id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getDescription() {
        return description;
    }

    public String getFournisseurID() {
        return fournisseurID;
    }

    public String getMarqueID() {
        return marqueID;
    }

    public String getCategorieID() {
        return categorieID;
    }

    public String getPrix() {
        return prix;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnite() {
        return unite;
    }    

    public String getNombreppm() {
        return nombreppm;
    }

    public void setNombreppm(String nombreppm) {
        this.nombreppm = nombreppm;
    }

    public String getUniteppm() {
        return uniteppm;
    }

    public void setUniteppm(String uniteppm) {
        this.uniteppm = uniteppm;
    }

    
    
    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFournisseurID(String fournisseurID) {
        this.fournisseurID = fournisseurID;
    }

    public void setMarqueID(String marqueID) {
        this.marqueID = marqueID;
    }

    public void setCategorieID(String categorieID) {
        this.categorieID = categorieID;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }


    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDiscountInCash() {
        return discountInCash;
    }

    public void setDiscountInCash(String discountInCash) {
        this.discountInCash = discountInCash;
    }

    public String getDiscountInPersent() {
        return discountInPersent;
    }

    public void setDiscountInPersent(String discountInPersent) {
        this.discountInPersent = discountInPersent;
    }

    public String getNombrepp() {
        return nombrepp;
    }

    public void setNombrepp(String nombrepp) {
        this.nombrepp = nombrepp;
    }

    public String getUnitepp() {
        return unitepp;
    }

    public void setUnitepp(String unitepp) {
        this.unitepp = unitepp;
    }

    public String getIngredientsid() {
        return ingredientsid;
    }

    public void setIngredientsid(String ingredientsid) {
        this.ingredientsid = ingredientsid;
    }

    public String getIngredientsnom() {
        return ingredientsnom;
    }

    public void setIngredientsnom(String ingredientsnom) {
        this.ingredientsnom = ingredientsnom;
    }

    public String getIngredientsquantite() {
        return ingredientsquantite;
    }

    public void setIngredientsquantite(String ingredientsquantite) {
        this.ingredientsquantite = ingredientsquantite;
    }

    public String getNombreunite() {
        return nombreunite;
    }

    public void setNombreunite(String nombreunite) {
        this.nombreunite = nombreunite;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getUniteproduit() {
        return uniteproduit;
    }

    public void setUniteproduit(String uniteproduit) {
        this.uniteproduit = uniteproduit;
    }

    public String getAmical() {
        return amical;
    }

    public void setAmical(String amical) {
        this.amical = amical;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    
}
