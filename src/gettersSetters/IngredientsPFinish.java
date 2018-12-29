/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

import java.sql.Blob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author Ralande
 */
public class IngredientsPFinish {
    public ObservableList<String> allIngredients = FXCollections.observableArrayList();

    public String id;
    public String ingredients;
    public String unite;
    public String date; 
    public String quantite;

    public ObservableList<ListIngredients> ingredientsList = FXCollections.observableArrayList();
}
