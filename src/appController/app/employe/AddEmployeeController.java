/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.employe;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.CustomTf;
import gettersSetters.UsersGetWay;
import gettersSetters.userNameMedia;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddEmployeeController implements Initializable {

    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnSave;
    @FXML
    private TextArea taAddress;
    @FXML
    private ImageView imvUsrImg;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfPassword;
    @FXML
    public Button btnClrUsrName;
    @FXML
    public Button btnClrFullName;
    @FXML
    public Button btnClrEmail;
    @FXML
    public Button btnClrPhone;
    @FXML
    public Button btnClrSalary;
    private File file;
    private BufferedImage bufferedImage;
    private Image image;

    private String imagePath;
    private String usrId;
    private userNameMedia nameMedia;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    Utilisateurs users = new Utilisateurs();
    UsersGetWay usersGetway = new UsersGetWay();
    @FXML
    private JFXRadioButton ml;
    @FXML
    private ToggleGroup depotGroup;
    @FXML
    private JFXRadioButton gl;
    @FXML
    private JFXRadioButton pt;
    @FXML
    private JFXRadioButton pz;
    @FXML
    private JFXRadioButton bl;

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CustomTf cTf = new CustomTf();
        cTf.clearTextFieldByButton(tfUserName, btnClrUsrName);
        cTf.clearTextFieldByButton(tfFullName, btnClrFullName);
        cTf.clearTextFieldByButton(tfEmail, btnClrEmail);
        cTf.clearTextFieldByButton(tfPhone, btnClrPhone);
        // cTf.clearTextFieldByButton(tfPassword, btnClrPassword);

        BooleanBinding binding = tfUserName.textProperty().isEmpty()
                .or(tfFullName.textProperty().isEmpty()
                        .or(tfPhone.textProperty().isEmpty())
                        .or(tfPassword.textProperty().isEmpty()));
        btnSave.disableProperty().bind(binding);
    }

    @FXML
    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choisir une image");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imvUsrImg.setImage(image);
            imagePath = file.getAbsolutePath();
        }
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        users.nom = tfUserName.getText();
        users.prenom = tfFullName.getText();
        users.email = tfEmail.getText();
        users.telephone = tfPhone.getText();
        users.addresse = taAddress.getText();
        users.mpd = tfPassword.getText();
        users.imagePath = imagePath;
        users.createurId = usrId;

        if (ml.isSelected() == true) {
            usersGetway.save(users, "dml_utilisateur", "Nom_dml");
            basicPermission("dml_utilisateur", "Id_dml", "Nom_dml", "dml_droitutilisateur", "Id_dml");
        } else if (gl.isSelected() == true) {
            usersGetway.save(users, "dgl_utilisateur", "Nom_dgl");
            basicPermission("dgl_utilisateur", "Id_dgl", "Nom_dgl", "dgl_droitutilisateur", "Id_dgl");
        } else if (pt.isSelected() == true) {
            usersGetway.save(users, "dpt_utilisateur", "Nom_dpt");
            basicPermission("dpt_utilisateur", "Id_dpt", "Nom_dpt", "dpt_droitutilisateur", "Id_dpt");
        } else if (pz.isSelected() == true) {
            usersGetway.save(users, "dpz_utilisateur", "Nom_dpz");
            basicPermission("dpz_utilisateur", "Id_dpz", "Nom_dpz", "dpz_droitutilisateur", "Id_dpz");
        } else if (bl.isSelected() == true) {
            usersGetway.save(users, "dbl_utilisateur", "Nom_dbl");
            basicPermission("dbl_utilisateur", "Id_dbl", "Nom_dbl", "dbl_droitutilisateur", "Id_dbl");
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Alert");
            a.setHeaderText("Aucune dépot n'est selectionné");
            a.setContentText("Veuillez selectionner un dépot ci-dessus");
            a.showAndWait();
        }
    }

    private void basicPermission(String tableUtilisateur, String IdNom, String nom, String tableDroitUtilisateur, String Id) {
        bddConnection dbCon = new bddConnection();
        Connection con;
        ResultSet rs;
        PreparedStatement pst;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Select " + IdNom + " FROM " + db + "." + tableUtilisateur + " where " + nom + "=?");
            pst.setString(1, tfUserName.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into " + db + "." + tableDroitUtilisateur + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setInt(2, 0);
                pst.setInt(3, 0);
                pst.setInt(4, 0);
                pst.setInt(5, 0);
                pst.setInt(6, 0);
                pst.setInt(7, 0);
                pst.setInt(8, 0);
                pst.setInt(9, 0);
                pst.setInt(10, 0);
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setInt(14, 0);
                pst.setInt(15, 0);
                pst.setInt(16, rs.getInt("" + Id + ""));

                pst.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
