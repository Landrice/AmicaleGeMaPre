/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app;

import appController.app.settings.MyAccountController;
import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class SettingsController implements Initializable {

    @FXML
    public BorderPane bpSettings;
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miBackup;
    @FXML
    public Label lblCurrentStatus;
    @FXML
    private StackPane spSettingContent;
    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    private String userID;

    userNameMedia usrMedia;
    @FXML
    public Label text;
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

    public userNameMedia getUsrMedia() {
        return usrMedia;
    }

    public void setUsrMedia(userNameMedia usrMedia) {
        userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("On est sur " + text.getText());
    }

    @FXML
    public void miMyASccountOnClick(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Mon compte");

        MyAccountController myAccount = new MyAccountController();
        userNameMedia usrIdMedia = new userNameMedia();

        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/app/app/settings/MyAccount.fxml").openStream());

        usrIdMedia.setId(userID);
        MyAccountController mAccount = fxmlload.getController();

        mAccount.setUsrMediaID(usrMedia);

        if (ml.isSelected() == true) {
            mAccount.ml.setSelected(true);
            mAccount.showDetails();
            AnchorPane acPane = fxmlload.getRoot();
            spSettingContent.getChildren().clear();
            spSettingContent.getChildren().add(acPane);
        } else if (gl.isSelected() == true) {
            mAccount.gl.setSelected(true);
            mAccount.showDetails();
            AnchorPane acPane = fxmlload.getRoot();
            spSettingContent.getChildren().clear();
            spSettingContent.getChildren().add(acPane);
        } else if (pt.isSelected() == true) {
            mAccount.pt.setSelected(true);
            mAccount.showDetails();
            AnchorPane acPane = fxmlload.getRoot();
            spSettingContent.getChildren().clear();
            spSettingContent.getChildren().add(acPane);
        } else if (pz.isSelected() == true) {
            mAccount.pz.setSelected(true);
            mAccount.showDetails();
            AnchorPane acPane = fxmlload.getRoot();
            spSettingContent.getChildren().clear();
            spSettingContent.getChildren().add(acPane);
        } else if (bl.isSelected() == true) {
            mAccount.bl.setSelected(true);
            mAccount.showDetails();
            AnchorPane acPane = fxmlload.getRoot();
            spSettingContent.getChildren().clear();
            spSettingContent.getChildren().add(acPane);
        } else {
            System.out.println("Aucune dépot n'est selectionné sur miMyASccountOnClick");
        }

    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {
    }

}
