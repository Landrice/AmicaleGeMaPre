/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.stock;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.Catagory;
import gettersSetters.CatagoryBLL;
import gettersSetters.CatagoryGetway;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddCatagoryController implements Initializable {

    @FXML
    private ComboBox<String> cbBrandName;
    @FXML
    private TextField tfCatagoryName;
    @FXML
    private TextArea taCatagoryDescription;
    @FXML
    public Button btnSave;
    @FXML
    private ComboBox<String> cbSupplyerName;
    @FXML
    private Button btnAddSupplyer;
    @FXML
    private Button btnAddBrand;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeaderContent;
    @FXML
    private Button btnClose;
    @FXML
    public JFXRadioButton ml;
    @FXML
    public ToggleGroup grpp;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    private String userId;
    private String brandId;
    private String brnadName;
    public String supplyerId;
    public String supplyerName;
    public String catagoryId;

    Catagory catagory = new Catagory();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    SQLSyntax sql = new SQLSyntax();

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    private userNameMedia media;

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        userId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cbBrandNameOnClick(MouseEvent event) throws SQLException {
        
        cbBrandName.getItems().clear();
            supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            supplyerId = sql.getIdNo(supplyerName, supplyerId, "fournisseur", "NomFournisseur");

            pst = con.prepareStatement("select * from " + db + ".marque where FournisseurID=?");
            pst.setString(1, supplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbBrandName.getItems().add(rs.getString(2));
            } 
    }

    @FXML
    private void btnSaveCatagory(ActionEvent event) {
        if (isNotNull()) {
            catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagory.creatorId = userId;
            catagoryBLL.save(catagory);
            
        }
    }

    @FXML
    private void cbSupplyerNameOnClick(MouseEvent event) {
        cbBrandName.getItems().clear();
        cbBrandName.setPromptText(null);
        
        try {
            pst = con.prepareStatement("select * from " + db + ".fournisseur");
                rs = pst.executeQuery();
                while (rs.next()) {
                    supplyerName = rs.getString(2);
                    cbSupplyerName.getItems().remove(supplyerName);
                    cbSupplyerName.getItems().add(supplyerName);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddSupplyerOnAction(ActionEvent event) {
        AddSupplierController addSupplyerController = new AddSupplierController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddSupplierController supplyerController = fxmlLoader.getController();
            if (ml.isSelected() == true) {
                supplyerController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                supplyerController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                supplyerController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                supplyerController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                supplyerController.bl.setSelected(true);
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Ajout fournisseur");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            supplyerController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        AddBrandController addSupplyerController = new AddBrandController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddBrand.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddBrandController supplyerController = fxmlLoader.getController();
            if (ml.isSelected() == true) {
                supplyerController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                supplyerController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                supplyerController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                supplyerController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                supplyerController.bl.setSelected(true);
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblHeader.setText("Ajout Marque");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println("Clicked");
        if (isNotNull()) {
            catagory.id = catagoryId;
            if (!cbBrandName.getSelectionModel().isEmpty()) {
                catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            } else if (!cbBrandName.getPromptText().isEmpty()) {
                catagory.brandName = cbBrandName.getPromptText();
            }
            if (!cbSupplyerName.getSelectionModel().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            } else if (!cbSupplyerName.getPromptText().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getPromptText();
            }
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagoryBLL.update(catagory);
        } else {

        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    
    public boolean isNotNull() {
        boolean isNotNull;
        if (tfCatagoryName.getText().trim().isEmpty()
                || cbSupplyerName.getSelectionModel().isEmpty()
                && cbSupplyerName.getPromptText().isEmpty()
                || cbBrandName.getSelectionModel().isEmpty()
                && cbBrandName.getPromptText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setHeaderText("ERREUR: champ vide ");
            alert.setContentText("veuiller remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            

            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }
     
     public void showDetails() {
        catagory.id = catagoryId;
        
         if (ml.isSelected() == true) {
               catagoryGetway.selectedView(catagory,"dml");
            } else if (gl.isSelected() == true) {
                catagoryGetway.selectedView(catagory,"dgl");
            } else if (pt.isSelected() == true) {
                catagoryGetway.selectedView(catagory,"dpt");
            } else if (pz.isSelected() == true) {
                catagoryGetway.selectedView(catagory,"dpz");
            } else if (bl.isSelected() == true) {
                catagoryGetway.selectedView(catagory,"dbl");
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }
        
                
        tfCatagoryName.setText(catagory.catagoryName);
        taCatagoryDescription.setText(catagory.catagoryDescription);
        cbBrandName.setPromptText(catagory.brandName);
        cbSupplyerName.setPromptText(catagory.supplyerName);
    }
}
