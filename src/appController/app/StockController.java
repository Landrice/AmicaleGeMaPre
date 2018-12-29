/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app;

import appController.app.stock.CurrentStoreController;
import appController.app.stock.CurrentStoreEndController;
import appController.app.stock.ViewBrandController;
import appController.app.stock.ViewCatagoryController;
import appController.app.stock.ViewSupplierController;
import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class StockController implements Initializable {

    @FXML
    public BorderPane bpStore;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private JFXToggleButton btnStock;
    @FXML
    private ToggleGroup btnGup;
    @FXML
    public JFXToggleButton btnSupplyer;
    @FXML
    public JFXToggleButton btnBrands;
    @FXML
    public JFXToggleButton btnCatagory;
    @FXML
    public Label lblHeader;
    @FXML
    private StackPane spMainContent;
    private String usrId;

    private userNameMedia userId;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
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

    public userNameMedia getUserId() {
        return userId;
    }

    public void setUserId(userNameMedia userId) {
        usrId = userId.getId();
        this.userId = userId;
    }

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnStock.setSelected(true);
    }

    @FXML
    public void btnStockOnAction(ActionEvent event) throws IOException {
        
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/stock/CurrentStore.fxml").openStream());
        media.setId(usrId);
        CurrentStoreController currentStoreController = fXMLLoader.getController();
        if (ml.isSelected() == true) {
            currentStoreController.ml.setSelected(true);
            currentStoreController.settingPermission("dml_droitutilisateur", "Id_dml");
        } else if (gl.isSelected() == true) {
            currentStoreController.gl.setSelected(true);
            currentStoreController.settingPermission("dgl_droitutilisateur", "Id_dgl");
            currentStoreController.btnAddNew.setDisable(true);
            currentStoreController.btnAddNew.setVisible(false);
        } else if (pt.isSelected() == true) {
            currentStoreController.pt.setSelected(true);
            currentStoreController.settingPermission("dpt_droitutilisateur", "Id_dpt");
            currentStoreController.btnAddNew.setDisable(true);
            currentStoreController.btnAddNew.setVisible(false);
        } else if (pz.isSelected() == true) {
            currentStoreController.pz.setSelected(true);
            currentStoreController.settingPermission("dpz_droitutilisateur", "Id_dpz");
            currentStoreController.btnAddNew.setDisable(true);
            currentStoreController.btnAddNew.setVisible(false);
        } else if (bl.isSelected() == true) {
            currentStoreController.bl.setSelected(true);
            currentStoreController.settingPermission("dbl_droitutilisateur", "Id_dbl");
            currentStoreController.btnAddNew.setDisable(true);
            currentStoreController.btnAddNew.setVisible(false);
        } else {
            System.out.println("Aucune dépot n'est selectionné sur btnStockOnAction(ActionEvent event)");
        }
        currentStoreController.setMedia(userId);
        currentStoreController.searchAll();
        //currentStoreController.btnUpdateOnAction(event);
        currentStoreController.apCombobox.getStylesheets().add("/css/StoreCombobox.css");

        
        StackPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSupplyerOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Fournisseur");
        ViewSupplierController vsc = new ViewSupplierController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/stock/ViewSupplier.fxml").openStream());
        media.setId(usrId);
        ViewSupplierController viewSupplyerController = fXMLLoader.getController();

        if (ml.isSelected() == true) {
            viewSupplyerController.ml.setSelected(true);
        } else if (gl.isSelected() == true) {
            viewSupplyerController.gl.setSelected(true);
        } else if (pt.isSelected() == true) {
            viewSupplyerController.pt.setSelected(true);
        } else if (pz.isSelected() == true) {
            viewSupplyerController.pz.setSelected(true);
        } else if (bl.isSelected() == true) {
            viewSupplyerController.bl.setSelected(true);
        } else {
            System.out.println("Aucune dépot n'est selectionné");
        }

        viewSupplyerController.setMedia(userId);
        viewSupplyerController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnBrandsOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Marque");
        ViewBrandController vbc = new ViewBrandController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/stock/ViewBrand.fxml").openStream());
        media.setId(usrId);
        ViewBrandController viewBrandController = fXMLLoader.getController();
        if (ml.isSelected() == true) {
            viewBrandController.ml.setSelected(true);
        } else if (gl.isSelected() == true) {
            viewBrandController.gl.setSelected(true);
        } else if (pt.isSelected() == true) {
            viewBrandController.pt.setSelected(true);
        } else if (pz.isSelected() == true) {
            viewBrandController.pz.setSelected(true);
        } else if (bl.isSelected() == true) {
            viewBrandController.bl.setSelected(true);
        } else {
            System.out.println("Aucune dépot n'est selectionné");
        }
        viewBrandController.setMedia(userId);
        viewBrandController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCatagoryOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Categories");
        ViewCatagoryController vcc = new ViewCatagoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/stock/ViewCatagory.fxml").openStream());
        media.setId(usrId);
        ViewCatagoryController viewCatagoryController = fXMLLoader.getController();
        if (ml.isSelected() == true) {
            viewCatagoryController.ml.setSelected(true);
        } else if (gl.isSelected() == true) {
            viewCatagoryController.gl.setSelected(true);
        } else if (pt.isSelected() == true) {
            viewCatagoryController.pt.setSelected(true);
        } else if (pz.isSelected() == true) {
            viewCatagoryController.pz.setSelected(true);
        } else if (bl.isSelected() == true) {
            viewCatagoryController.bl.setSelected(true);
        } else {
            System.out.println("Aucune dépot n'est selectionné");
        }
        
        viewCatagoryController.setMedia(userId);
        viewCatagoryController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    public void settingPermission(String tabledroitutilisateur, String id) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + "." + tabledroitutilisateur + " where " + id + "=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(2) == 0 && rs.getInt(9) == 0) {
                    btnSupplyer.setDisable(true);
                }
                if (rs.getInt(4) == 0 && rs.getInt(10) == 0) {
                    btnBrands.setDisable(true);
                }
                if (rs.getInt(5) == 0 && rs.getInt(11) == 0) {
                    btnCatagory.setDisable(true);
                }/*if(rs.getInt(6) == 0 && rs.getInt(12) == 0){
                    btnUnit.setDisable(true);
                }if(rs.getInt(14) == 0){
                    btnRma.setDisable(true);
                }*/ else {

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void finishproducts(ActionEvent event) throws IOException{
        Tooltip tp=new Tooltip();
        tp.setText("Vous n'avez pas le droit pour ajouter le produits");
        
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/stock/CurrentStoreEnd.fxml").openStream());
        media.setId(usrId);
        CurrentStoreEndController currentStoreController = fXMLLoader.getController();
        if (ml.isSelected() == true) {
            currentStoreController.ml.setSelected(true);
            currentStoreController.settingPermission("dml_droitutilisateur", "Id_dml");
        } else if (gl.isSelected() == true) {
            currentStoreController.gl.setSelected(true);
            currentStoreController.settingPermission("dgl_droitutilisateur", "Id_dgl");
           // currentStoreController.btnAddNew.setDisable(true);
           // currentStoreController.btnAddNew.setTooltip(tp);
         //   currentStoreController.btnAddNew.setVisible(false);
        } else if (pt.isSelected() == true) {
            currentStoreController.pt.setSelected(true);
            currentStoreController.settingPermission("dpt_droitutilisateur", "Id_dpt");
//            currentStoreController.btnAddNew.setDisable(true);
//            currentStoreController.btnAddNew.setTooltip(tp);
          //  currentStoreController.btnAddNew.setVisible(false);
        } else if (pz.isSelected() == true) {
            currentStoreController.pz.setSelected(true);
            currentStoreController.settingPermission("dpz_droitutilisateur", "Id_dpz");
//            currentStoreController.btnAddNew.setDisable(true);
//            currentStoreController.btnAddNew.setTooltip(tp);
          //  currentStoreController.btnAddNew.setVisible(false);
        } else if (bl.isSelected() == true) {
            currentStoreController.bl.setSelected(true);
            currentStoreController.settingPermission("dbl_droitutilisateur", "Id_dbl");
//            currentStoreController.btnAddNew.setDisable(true);
//            currentStoreController.btnAddNew.setTooltip(tp);
            //currentStoreController.btnAddNew.setVisible(false);
        } else {
            System.out.println("Aucune dépot n'est selectionné sur btnStockOnAction(ActionEvent event)");
        }
        currentStoreController.setMedia(userId);
        currentStoreController.btnRefreshOnACtion(event);
        //currentStoreController.btnUpdateOnAction(event);
        currentStoreController.apCombobox.getStylesheets().add("/css/StoreCombobox.css");

        
        StackPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
}
