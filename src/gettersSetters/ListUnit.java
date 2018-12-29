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
public class ListUnit {
    public String id;
    public String unite;
    
public ListUnit(){
    
}

    public ListUnit(String id, String unite) {
        this.id = id;
        this.unite = unite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
}
