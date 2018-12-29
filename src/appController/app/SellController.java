/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app;

import appController.app.vente.ViewSellController;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class SellController implements Initializable {

    @FXML
    public AnchorPane acMainSells;
    @FXML
    private Label lblPathInfo;
    @FXML
    public JFXRadioButton ml;
    @FXML
    private ToggleGroup grp;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    @FXML
    private ToggleButton tbtnSell;
    @FXML
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnReports;
    @FXML
    private StackPane spMainContent;
    userNameMedia nameMedia;
    String userId;
    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        tbtnSell.setSelected(true);
        tbtnCustomer.setToggleGroup(group);
        tbtnSell.setToggleGroup(group);
        tbtnReports.setToggleGroup(group);
    }    

    @FXML
    public void tbtnSellOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Achats ou Transferts");
        ViewSellController sellController = new ViewSellController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/vente/ViewSell.fxml").openStream());
        media.setId(userId);
        ViewSellController controller = fXMLLoader.getController();
        
        if(ml.isSelected()==true){
           controller.ml.setSelected(true);
        }else if(gl.isSelected()==true){
            controller.gl.setSelected(true);
        }else if(pt.isSelected()==true){
            controller.pt.setSelected(true);
        }else if(pz.isSelected()==true){
            controller.pz.setSelected(true);
        }else if(bl.isSelected()==true){
            controller.bl.setSelected(true);
        }else{System.out.println("Aucune dépot n'est selectionné");}
        
        controller.setNameMedia(nameMedia);
   /*     controller.viewDetails();*/
        controller.searchall();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) {
        /*lblPathInfo.setText("Acheteurs");
        ViewCustomerController customerController = new ViewCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/gui/app/vente/ViewCustomer.fxml").openStream());
        media.setId(userId);
        ViewCustomerController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());*/
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event) {
    }
    
}
