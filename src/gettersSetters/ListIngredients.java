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
public class ListIngredients {
    String id;
    String ingredients;
    String unite;
    String quantite;
    String date;

    public ListIngredients(String id, String ingredients, String quantite) {
        this.id = id;
        this.ingredients = ingredients;
        this.quantite = quantite;
    }

    public ListIngredients(String ingredients, String quantite) {
        this.ingredients = ingredients;
        this.quantite = quantite;
    }    

    public ListIngredients(String id, String ingredients, String unite, String quantite, String date) {
        this.id = id;
        this.ingredients = ingredients;
        this.unite = unite;
        this.quantite = quantite;
        this.date = date;
    }

    

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
