/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

/**
 *
 * @author Ralande
 */
public class ListProduitsFinies {
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

    public ListProduitsFinies(String produit) {
        this.produit = produit;
    }
    public ListProduitsFinies(String id, String produit) {
        this.id = id;
        this.produit = produit;
    }

    public ListProduitsFinies(String id, String produit, String quattiteingredients, String idingredients) {
        this.id = id;
        this.produit = produit;
        this.quattiteingredients = quattiteingredients;
        this.idingredients = idingredients;
    }

    public ListProduitsFinies(String id, String produit, String report, String nombreunite, String production, String amicale, String stock, String date, String quattiteingredients, String idingredients) {
        this.id = id;
        this.produit = produit;
        this.report = report;
        this.nombreunite = nombreunite;
        this.production = production;
        this.amicale = amicale;
        this.stock = stock;
        this.date = date;
        this.quattiteingredients = quattiteingredients;
        this.idingredients = idingredients;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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

    public String getAmicale() {
        return amicale;
    }

    public void setAmicale(String amicale) {
        this.amicale = amicale;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuattiteingredients() {
        return quattiteingredients;
    }

    public void setQuattiteingredients(String quattiteingredients) {
        this.quattiteingredients = quattiteingredients;
    }

    public String getIdingredients() {
        return idingredients;
    }

    public void setIdingredients(String idingredients) {
        this.idingredients = idingredients;
    }
    
}
