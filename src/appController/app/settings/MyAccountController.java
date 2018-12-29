/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.settings;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXToggleButton;
import gettersSetters.UsersGetWay;
import gettersSetters.userNameMedia;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class MyAccountController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfContractNumber;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextArea taAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Hyperlink hlChangePassword;
    @FXML
    private Rectangle retImage;
    @FXML
    private Button attachImage;

    private Image image;

    private File file;

    private FileInputStream fileInput;

    private FileOutputStream fileOutput;

    private byte[] userImage;

    private String imgPath;

    Utilisateurs users = new Utilisateurs();
    UsersGetWay usersGetway = new UsersGetWay();

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    private String userID;
    private userNameMedia usrMediaID;
    @FXML
    public JFXToggleButton ml;
    @FXML
    private ToggleGroup groupbtntt;
    @FXML
    public JFXToggleButton gl;
    @FXML
    public JFXToggleButton pz;
    @FXML
    public JFXToggleButton bl;
    @FXML
    public JFXToggleButton pt;

    public userNameMedia getUsrMediaID() {
        return usrMediaID;
    }

    public void setUsrMediaID(userNameMedia usrMediaID) {
        userID = usrMediaID.getId();
        this.usrMediaID = usrMediaID;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BooleanBinding boolenBinding = tfFullName.textProperty().isEmpty()
                .or(tfEmailAddress.textProperty().isEmpty()
                        .or(tfContractNumber.textProperty().isEmpty()));

        btnSave.disableProperty().bind(boolenBinding);
        disabletoogle();
    }

    public void disabletoogle() {
        ml.setDisable(true);
        gl.setDisable(true);
        pt.setDisable(true);
        pz.setDisable(true);
        bl.setDisable(true);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        users.nom = tfUserName.getText();
        users.prenom = tfFullName.getText();
        users.email = tfEmailAddress.getText();
        users.addresse = taAddress.getText();
        users.telephone = tfContractNumber.getText();
        users.imagePath = imgPath;
        users.image = image;
        if (ml.isSelected() == true) {
            usersGetway.update(users, "UPDATE " + db + ".dml_utilisateur SET Prenom_dml=?, Email_dml=?,Telephone_dml=?,Addresse_dml=?,Mdp_dml=COALESCE(?, Mdp_dml), photo_dml=COALESCE(?, photo_dml) WHERE Nom_dml=?");
        } else if (gl.isSelected() == true) {
            usersGetway.update(users, "UPDATE " + db + ".dgl_utilisateur SET Prenom_dgl=?, Email_dgl=?,Telephone_dgl=?,Addresse_dgl=?,Mdp_dgl=COALESCE(?, Mdp_dgl), photo_dgl=COALESCE(?, photo_dgl) WHERE Nom_dgl=?");
        } else if (pt.isSelected() == true) {
            usersGetway.update(users, "UPDATE " + db + ".dpt_utilisateur SET Prenom_dpt=?, Email_dpt=?,Telephone_dpt=?,Addresse_dpt=?,Mdp_dpt=COALESCE(?, Mdp_dpt), photo_dpt=COALESCE(?, photo_dpt) WHERE Nom_dpt=?");
        } else if (pz.isSelected() == true) {
            usersGetway.update(users, "UPDATE " + db + ".dpz_utilisateur SET Prenom_dpz=?, Email_dpz=?,Telephone_dpz=?,Addresse_dpz=?,Mdp_dpz=COALESCE(?, Mdp_dpz), photo_dpz=COALESCE(?, photo_dpz) WHERE Nom_dpz=?");
        } else if (bl.isSelected() == true) {
            usersGetway.update(users, "UPDATE " + db + ".dbl_utilisateur SET Prenom_dbl=?, Email_dbl=?,Telephone_dbl=?,Addresse_dbl=?,Mdp_dbl=COALESCE(?, Mdp_dbl), photo_dbl=COALESCE(?, photo_dbl) WHERE Nom_dbl=?");
        }
    }

    @FXML
    private void hlChangePasswordOnClick(ActionEvent event) throws IOException {
        PassChangeController pcc = new PassChangeController();
        userNameMedia nameMedia = new userNameMedia();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/app/app/settings/PassChange.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        PassChangeController passChangeController = loader.getController();
        nameMedia.setId(userID);

        if (ml.isSelected() == true) {
            passChangeController.textmdp.setText("ml");
            passChangeController.ml.setSelected(true);
        } else if (gl.isSelected() == true) {
            passChangeController.textmdp.setText("gl");
            passChangeController.gl.setSelected(true);
        } else if (pt.isSelected() == true) {
            passChangeController.textmdp.setText("pt");
            passChangeController.pt.setSelected(true);
        } else if (pz.isSelected() == true) {
            passChangeController.textmdp.setText("pz");
            passChangeController.pz.setSelected(true);
        } else if (bl.isSelected() == true) {
            passChangeController.textmdp.setText("bl");
            passChangeController.bl.setSelected(true);
        }

        passChangeController.setNameMedia(nameMedia);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Gestion des Utilisateurs");
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }

    @FXML
    private void attachImageOnAction(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                retImage.setFill(new ImagePattern(image));
                imgPath = file.getAbsolutePath();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Refusé");
                alert.setHeaderText("Taille de image");
                alert.setContentText("Votre image est volumineux \nVeuiller choisir une autre image");
                alert.initStyle(StageStyle.UNDECORATED);

            }

        }
    }

    public void showDetails() {
        if (ml.isSelected() == true) {
            users.id = userID;
            usersGetway.selectedView(users, "dml_utilisateur", "Id_dml");
            tfUserName.setText(users.nom);
            tfFullName.setText(users.prenom);
            tfContractNumber.setText(users.telephone);
            tfEmailAddress.setText(users.email);
            taAddress.setText(users.addresse);
            image = users.image;
            retImage.setFill(new ImagePattern(image));
        } else if (gl.isSelected() == true) {
            users.id = userID;
            usersGetway.selectedView(users, "dgl_utilisateur", "Id_dgl");
            tfUserName.setText(users.nom);
            tfFullName.setText(users.prenom);
            tfContractNumber.setText(users.telephone);
            tfEmailAddress.setText(users.email);
            taAddress.setText(users.addresse);
            image = users.image;
            retImage.setFill(new ImagePattern(image));
        } else if (pt.isSelected() == true) {
            users.id = userID;
            usersGetway.selectedView(users, "dpt_utilisateur", "Id_dpt");
            tfUserName.setText(users.nom);
            tfFullName.setText(users.prenom);
            tfContractNumber.setText(users.telephone);
            tfEmailAddress.setText(users.email);
            taAddress.setText(users.addresse);
            image = users.image;
            retImage.setFill(new ImagePattern(image));
        } else if (pz.isSelected() == true) {
            users.id = userID;
            usersGetway.selectedView(users, "dpz_utilisateur", "Id_dpz");
            tfUserName.setText(users.nom);
            tfFullName.setText(users.prenom);
            tfContractNumber.setText(users.telephone);
            tfEmailAddress.setText(users.email);
            taAddress.setText(users.addresse);
            image = users.image;
            retImage.setFill(new ImagePattern(image));
        } else if (bl.isSelected() == true) {
            users.id = userID;
            usersGetway.selectedView(users, "dbl_utilisateur", "Id_dbl");
            tfUserName.setText(users.nom);
            tfFullName.setText(users.prenom);
            tfContractNumber.setText(users.telephone);
            tfEmailAddress.setText(users.email);
            taAddress.setText(users.addresse);
            image = users.image;
            retImage.setFill(new ImagePattern(image));
        } else {
            System.out.println("Aucune dépot n'est selectionné sur MyAccountController showDetails()");
        }

    }

    public void accountPermission() {
        con = dbCon.geConnection();
        try {

            if (ml.isSelected() == true) {
                pst = con.prepareStatement("select * from " + db + ".dml_droitutilisateur where UserId_dml=?");
            } else if (gl.isSelected() == true) {
                pst = con.prepareStatement("select * from " + db + ".dgl_droitutilisateur where UserId_dgl=?");
            } else if (pt.isSelected() == true) {
                pst = con.prepareStatement("select * from " + db + ".dpt_droitutilisateur where UserId_dpt=?");
            } else if (pz.isSelected() == true) {
                pst = con.prepareStatement("select * from " + db + ".dpz_droitutilisateur where UserId_dpz=?");
            } else if (bl.isSelected() == true) {
                pst = con.prepareStatement("select * from " + db + ".dbl_droitutilisateur where UserId_dbl=?");
            } else {
                System.out.println("Aucune dépot n'est selectionné sur MyAccountController accountpermission()");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean nullCheck() {
        boolean notNull;

        if (tfFullName.getText().trim().length() == 0
                || tfContractNumber.getText().trim().length() == 0
                || tfEmailAddress.getText().trim().length() == 0) {
            notNull = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("ERREUR ");
            alert.setContentText("Veuiller remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
        } else {
            notNull = true;
            System.out.println("Not Null");
        }
        return notNull;
    }
}
