/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField identifiantfield;
    @FXML
    private JFXPasswordField passfield;
    @FXML
    private JFXButton seconnecter;
    @FXML
    private JFXToggleButton militairedepot;
    @FXML
    private ToggleGroup selectionDepot;
    @FXML
    private JFXToggleButton glaceDepot;
    @FXML
    private JFXToggleButton patDepot;
    @FXML
    private JFXToggleButton pizzaDepot;
    @FXML
    private JFXToggleButton boulDepot;
    @FXML
    private JFXButton newuser;
    @FXML
    private Hyperlink bddconfig;
    private Connection con;
    private PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane panelogin;
    @FXML
    private VBox box;
    @FXML
    private Label amicale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        militairedepot.setSelected(true);
        depot();
    }

    @FXML
    private void connecterOnAction(ActionEvent event) throws IOException {
        bddConnection dbCon = new bddConnection();
        con = dbCon.geConnection();
        if (con != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/app/App.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            if (isValidCondition()) {
                try {

                    if (militairedepot.isSelected() == true) {
                        pst = con.prepareStatement("select * from " + db + ".dml_utilisateur where Nom_dml=? and Mdp_dml=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dml_droitutilisateur", "UtilisateurId_dml");
                            apControl.viewDetails("dml_utilisateur", "Id_dml");
                            apControl.addtexapp("dml");
                            apControl.btnproducts.setDisable(true);
                            apControl.btnlougout.setDisable(true);
                            
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("mlUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                              apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nAssurez vous être dans le dépot militaire");
                            // alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("Militaire depot selected");

                    } else if (glaceDepot.isSelected() == true) {
                        pst = con.prepareStatement("select * from " + db + ".dgl_utilisateur where Nom_dgl=? and Mdp_dgl=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dgl_droitutilisateur", "UtilisateurId_dgl");
                            apControl.viewDetails("dgl_utilisateur", "Id_dgl");
                            apControl.addtexapp("dgl");
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("glUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                              apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nAssurez vous être dans le dépot du glacerie");
                            //  alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("glacerie depot selected");
                    } else if (patDepot.isSelected() == true) {
                        pst = con.prepareStatement("select * from " + db + ".dpt_utilisateur where Nom_dpt=? and Mdp_dpt=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dpt_droitutilisateur", "UtilisateurId_dpt");
                            apControl.viewDetails("dpt_utilisateur", "Id_dpt");
                            apControl.addtexapp("dpt");
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("ptUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                            apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nAssurez vous être dans le dépot du patisserie");
                            // alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("patisserie depot selected");
                    } else if (pizzaDepot.isSelected() == true) {
                        pst = con.prepareStatement("select * from " + db + ".dpz_utilisateur where Nom_dpz=? and Mdp_dpz=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dpz_droitutilisateur", "UtilisateurId_dpz");
                            apControl.viewDetails("dpz_utilisateur", "Id_dpz");
                            apControl.addtexapp("dpz");
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("pzUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                            apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nAssurez vous être dans le dépot du pizzeria");
                            // alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("pizzeria depot selected");
                    } else if (boulDepot.isSelected() == true) {
                        pst = con.prepareStatement("select * from " + db + ".dbl_utilisateur where Nom_dbl=? and Mdp_dbl=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dbl_droitutilisateur", "UtilisateurId_dbl");
                            apControl.viewDetails("dbl_utilisateur", "Id_dbl");
                            apControl.addtexapp("dbl");
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("blUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                            apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nAssurez vous être dans le dépot du boulangerie");
                            // alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("boulangeria depot selected");
                    } else {
                        pst = con.prepareStatement("select * from " + db + ".dml_utilisateur where Nom_dml=? and Mdp_dml=? ");
                        pst.setString(1, identifiantfield.getText());
                        pst.setString(2, passfield.getText());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                            AppController apControl = loader.getController();
                            apControl.setUsrNameMedia(usrNameMedia);
                            apControl.permission("dml_droitutilisateur", "UtilisateurId_dml");
                            apControl.viewDetails("dml_utilisateur", "Id_dml");
                            apControl.addtexapp("dml");
                            apControl.addtexapp1(identifiantfield.getText());
                            adminPanelStage.setScene(adminPanelScene);
                            adminPanelStage.setTitle("mlUtilisateur " + rs.getString(3));
                            //adminPanelStage.getIcons().add(new Image("/image/OM.png"));
                            adminPanelStage.show();
                            apControl.btnHomeOnClick(event);

                            Stage stage = (Stage) seconnecter.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println("Password Not Match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Mot de passe incorect");
                            alert.setHeaderText("Erreur: Login ou le mot de passe est incorrect");
                            alert.setContentText("Verifier le mot de passe ou le nom d'utilisateur \nVeuillez ressayer");
                            // alert.initStyle(StageStyle.UNDECORATED);
                            alert.showAndWait();
                        }
                        System.out.println("Aucun dépot n'est selectionné ");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur : Serveur Introuvable");
            alert.setContentText("Assurer que le serveur est en marche, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    @FXML
    private void depotOnaction(ActionEvent event) {
        depot();
    }

    public void depot() {
        identifiantfield.setText(null);
        passfield.setText(null);
        panelogin.setOpacity(0.9);
        box.setOpacity(0.9);
        if (militairedepot.isSelected() == true) {
            System.out.println("Militaire depot selected");
            apMother.setStyle("-fx-background-image: url(\"/img/ml.jpg\");");
            amicale.setStyle("-fx-text-fill:white;");
        } else if (glaceDepot.isSelected() == true) {
            System.out.println("glacerie depot selected");
            apMother.setStyle("-fx-background-image: url(\"/img/gl.png\");");
            amicale.setStyle("-fx-text-fill:black;");
        } else if (patDepot.isSelected() == true) {
            System.out.println("patisserie depot selected");
            apMother.setStyle("-fx-background-image: url(\"/img/pt.jpg\");");
            amicale.setStyle("-fx-text-fill:black;");
        } else if (pizzaDepot.isSelected() == true) {
            System.out.println("pizzeria depot selected");
            apMother.setStyle("-fx-background-image: url(\"/img/pz.jpg\");");
            amicale.setStyle("-fx-text-fill:white;");
        } else if (boulDepot.isSelected() == true) {
            System.out.println("boulangeria depot selected");
            apMother.setStyle("-fx-background-image: url(\"/img/bl.jpg\") ;");
            amicale.setStyle("-fx-text-fill:white;");
        } else {
            System.out.println("Aucun dépot n'est selectionné ");
        }
    }

    @FXML
    private void newuserOnaction(ActionEvent event) {
        bddConnection dbCon = new bddConnection();
        con = dbCon.geConnection();
        if (con != null) {
            try {
                pst = con.prepareStatement("SELECT Id_dml FROM " + db + ".dml_utilisateur ORDER BY Id_dml ASC LIMIT 1");
                rs = pst.executeQuery();
                if (rs.next()) {
                    apMother.setOpacity(0.7);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur");
                    alert.setContentText("Vous ne pouvez pas creer un compte sans \n la permission de l'Administrateur");
                    alert.initStyle(StageStyle.UNDECORATED);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        apMother.setOpacity(1.0);
                    }
                    return;
                }
                con.close();
                pst.close();
                rs.close();
                loadRegistration();

            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreu");
            alert.setHeaderText("Erreur : Serveur Introuvable");
            alert.setContentText("Assurez vous que votre Mysql est activé, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }

    }

    @FXML
    private void bddconfigOnAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/app/bddConfig.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Status du Serveur");
            //  nStage.getIcons().add(new Image("/image/OM.png"));
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRegistration() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/app/NewUser.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(true);
            nStage.setTitle("Inscription");
            // nStage.getIcons().add(new Image("/image/OM.png"));
            nStage.show();
            Stage stage = (Stage) bddconfig.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if (identifiantfield.getText().trim().isEmpty()
                && passfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement :");
            alert.setHeaderText("Avertissement !!");
            alert.setContentText("Veuiller entrer votre nom et le mot de passe");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }
}
