/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.settings;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.CustomPf;
import gettersSetters.userNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class PassChangeController implements Initializable {

    @FXML
    private PasswordField pfCurrentPass;
    @FXML
    private PasswordField pfNewPass;
    @FXML
    private PasswordField pfRePass;
    @FXML
    private Button btnClrCurrentPf;
    @FXML
    private Button btnClrRePass;
    @FXML
    private Button btnClrNewPass;
    @FXML
    private ImageView imgCurrentPassMatch;
    @FXML
    private ImageView imgNewPassMatch;
    @FXML
    private Button btnChangePass;
    @FXML
    private JFXButton btnClose;
    Utilisateurs users = new Utilisateurs();
    private String userId;
    private String userName;
    private userNameMedia nameMedia;
    @FXML
    Label textmdp;
    @FXML
    public JFXRadioButton ml;
    @FXML
    private ToggleGroup grpp;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        userName = nameMedia.getNom();
        this.nameMedia = nameMedia;
    }

    CustomPf customPf = new CustomPf();

    bddConnection dbCon = new bddConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customPf.clearPassFieldByButton(pfCurrentPass, btnClrCurrentPf);
        customPf.clearPassFieldByButton(pfNewPass, btnClrNewPass);
        customPf.clearPassFieldByButton(pfRePass, btnClrRePass);

        BooleanBinding binding = pfCurrentPass.textProperty().isEmpty()
                .or(pfNewPass.textProperty().isEmpty()
                        .or(pfRePass.textProperty().isEmpty()));

        btnChangePass.disableProperty().bind(binding);
    }

    @FXML
    private void pfOnAction(ActionEvent event) {
        btnChangePassOnAction(event);
    }

    @FXML
    private void pfNewPassWordMatch(KeyEvent event) {
        if (pfNewPass.getText().matches(pfRePass.getText())) {
            System.out.println("Match");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("ERREUR ");
            alert.setContentText("Mot de passe invalide");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    @FXML
    private void btnChangePassOnAction(ActionEvent event) {
        if (isCurrentPasswordChecqOk()) {
            if (isPasswordMatch()) {
                updatePassword();
            }

        } else {
            System.out.println("ddd");
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private boolean isCurrentPasswordChecqOk() {
        boolean conDitionValid = true;
        con = dbCon.geConnection();
        try {
            if (ml.isSelected()==true) {
                pst = con.prepareStatement("select * from dml_utilisateur where Id_dml=? and Mdp_dml=?");
                pst.setString(1, userId);
                pst.setString(2, pfCurrentPass.getText());
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("Old Password Match");
                    return conDitionValid;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR ");
                alert.setContentText("Mot de passe invalide");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                conDitionValid = false;
            } else if (gl.isSelected()==true) {
                pst = con.prepareStatement("select * from dgl_utilisateur where Id_dgl=? and Mdp_dgl=?");
                pst.setString(1, userId);
                pst.setString(2, pfCurrentPass.getText());
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("Old Password Match");
                    return conDitionValid;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR ");
                alert.setContentText("Mot de passe invalide");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                conDitionValid = false;
            } else if (pt.isSelected()==true) {
                pst = con.prepareStatement("select * from dpt_utilisateur where Id_dpt=? and Mdp_dpt=?");
                pst.setString(1, userId);
                pst.setString(2, pfCurrentPass.getText());
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("Old Password Match");
                    return conDitionValid;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR ");
                alert.setContentText("Mot de passe invalide");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                conDitionValid = false;
            } else if (pz.isSelected()==true) {
                pst = con.prepareStatement("select * from dpz_utilisateur where Id_dpz=? and Mdp_dpz=?");
                pst.setString(1, userId);
                pst.setString(2, pfCurrentPass.getText());
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("Old Password Match");
                    return conDitionValid;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR ");
                alert.setContentText("Mot de passe invalide");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                conDitionValid = false;
            } else if (bl.isSelected()==true) {
                pst = con.prepareStatement("select * from dbl_utilisateur where Id_dbl=? and Mdp_dbl=?");
                pst.setString(1, userId);
                pst.setString(2, pfCurrentPass.getText());
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("Old Password Match");
                    return conDitionValid;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR ");
                alert.setContentText("Mot de passe invalide");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                conDitionValid = false;
            } else {
                System.out.println("Aucune dépot n'est selectioné sur le PassChangeController");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conDitionValid;
    }

    private boolean isPasswordMatch() {
        boolean passMatch;
        if (pfNewPass.getText().matches(pfRePass.getText())) {
            System.out.println("New Password match");
            passMatch = true;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("ERREUR ");
            alert.setContentText("Les nouveaux mot de passe ne sont pas identiques");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            passMatch = false;
        }
        return passMatch;
    }

    private void updatePassword() {

        con = dbCon.geConnection();
        try {
            if (textmdp.equals("ml")) {
                pst = con.prepareStatement("Update " + db + ".dml_utilisateur set Mdp_dml=? where Id_dml=?");
                pst.setString(1, pfNewPass.getText());
                pst.setString(2, userId);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess ");
                alert.setContentText("Modification du mot de passe avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } else if (textmdp.equals("gl")) {
                pst = con.prepareStatement("Update " + db + ".dgl_utilisateur set Mdp_dgl=? where Id_dgl=?");
                pst.setString(1, pfNewPass.getText());
                pst.setString(2, userId);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess ");
                alert.setContentText("Modification du mot de passe avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } else if (textmdp.equals("pt")) {
                pst = con.prepareStatement("Update " + db + ".dpt_utilisateur set Mdp_dpt=? where Id_dpt=?");
                pst.setString(1, pfNewPass.getText());
                pst.setString(2, userId);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess ");
                alert.setContentText("Modification du mot de passe avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } else if (textmdp.equals("pz")) {
                pst = con.prepareStatement("Update " + db + ".dpz_utilisateur set Mdp_dpz=? where Id_dpz=?");
                pst.setString(1, pfNewPass.getText());
                pst.setString(2, userId);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess ");
                alert.setContentText("Modification du mot de passe avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } else if (textmdp.equals("bl")) {
                pst = con.prepareStatement("Update " + db + ".dbl_utilisateur set Mdp_dbl=? where Id_dbl=?");
                pst.setString(1, pfNewPass.getText());
                pst.setString(2, userId);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess ");
                alert.setContentText("Modification du mot de passe avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
