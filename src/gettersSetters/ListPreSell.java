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
public class ListPreSell {
    String Id;
    String productID;
    String customerID;
    String pursesPrice;
    String sellPrice;
    String oldQuantity;
    String quantity;
    String quantitymdf;
    String unite;
    String totalPrice;
    String pursrsDate;
    String warrentyVoidDate;
    String sellerID;
    String sellDate;
    String unitepp;
    String unitemdf;
    String nombrepp;
    String numeropp;
    String nombreppm;
    String nomproduit;

    public ListPreSell(String Id, String productID, String customerID, String pursesPrice, String sellPrice, String oldQuantity, String quantity, String totalPrice, String pursrsDate, String warrentyVoidDate, String sellerID, String sellDate, String unite, String unitepp, String nombrepp, String quantitymdf, String numeropp, String nombreppm, String unitemdf,String nomproduit) {
        this.Id = Id;
        this.productID = productID;
        this.customerID = customerID;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.oldQuantity = oldQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.pursrsDate = pursrsDate;
        this.warrentyVoidDate = warrentyVoidDate;
        this.sellerID = sellerID;
        this.sellDate = sellDate;
        this.unite=unite;
        this.unitepp=unitepp;
        this.nombrepp=nombrepp;
        this.quantitymdf=quantitymdf;
        this.numeropp=numeropp;
        this.nombreppm=nombreppm;
        this.unitemdf=unitemdf;
        this.nomproduit=nomproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }    
    
    public String getUnitemdf() {
        return unitemdf;
    }

    public void setUnitemdf(String unitemdf) {
        this.unitemdf = unitemdf;
    }    
    
    public String getQuantitymdf() {
        return quantitymdf;
    }

    public void setQuantitymdf(String quantitymdf) {
        this.quantitymdf = quantitymdf;
    } 
    
    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPursesPrice() {
        return pursesPrice;
    }

    public void setPursesPrice(String pursesPrice) {
        this.pursesPrice = pursesPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(String oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPursrsDate() {
        return pursrsDate;
    }

    public void setPursrsDate(String pursrsDate) {
        this.pursrsDate = pursrsDate;
    }

    public String getWarrentyVoidDate() {
        return warrentyVoidDate;
    }

    public void setWarrentyVoidDate(String warrentyVoidDate) {
        this.warrentyVoidDate = warrentyVoidDate;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getUnitepp() {
        return unitepp;
    }

    public void setUnitepp(String unitepp) {
        this.unitepp = unitepp;
    }

    public String getNombrepp() {
        return nombrepp;
    }

    public void setNombrepp(String nombrepp) {
        this.nombrepp = nombrepp;
    }

    public String getNumeropp() {
        return numeropp;
    }

    public void setNumeropp(String numeropp) {
        this.numeropp = numeropp;
    }

    public String getNombreppm() {
        return nombreppm;
    }

    public void setNombreppm(String nombreppm) {
        this.nombreppm = nombreppm;
    }

    
}
