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
public class ListSold {
    String Id;
    String sellId;
    String productId;
    String productGId;
    String customerId;
    String customerName;
    String pursesPrice;
    String sellPrice;
    String oldQuantity;
    String quantity;
    String unite;
    String unitemdf;
    String totalPrice;
    String pursrsDate;
    String warrentyVoidDate;
    public String nombrepp;
    
    String sellerID;
    String sellerName;
    String sellDate;
    String productname;
    String dateingredients;

    public ListSold(String Id, String sellId, String productId, String productGId, String customerId, String customerName, String pursesPrice, String sellPrice, String oldQuantity, String quantity, String totalPrice, String pursrsDate, String warrentyVoidDate, String sellerID, String sellerName, String sellDate, String productname, String unite) {
        this.Id = Id;
        this.sellId = sellId;
        this.productId = productId;
        this.productGId = productGId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.oldQuantity = oldQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.pursrsDate = pursrsDate;
        this.warrentyVoidDate = warrentyVoidDate;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellDate = sellDate;
        this.productname=productname;
        this.unite=unite;
        
    }

    public String getUnitemdf() {
        return unitemdf;
    }

    public void setUnitemdf(String unitemdf) {
        this.unitemdf = unitemdf;
    }

    
    
    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getNombrepp() {
        return nombrepp;
    }

    public void setNombrepp(String nombrepp) {
        this.nombrepp = nombrepp;
    }
    
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductGId() {
        return productGId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
    

    public void setProductGId(String productGId) {
        this.productGId = productGId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getDateingredients() {
        return dateingredients;
    }

    public void setDateingredients(String dateingredients) {
        this.dateingredients = dateingredients;
    }
    
    
}
