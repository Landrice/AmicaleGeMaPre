/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class HomeController implements Initializable {

    @FXML
    public Label name;
    @FXML
    public AnchorPane dml;
    @FXML
    public AnchorPane dgl;
    @FXML
    public AnchorPane dpt;
    @FXML
    public AnchorPane dpz;
    @FXML
    public AnchorPane dbl;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
