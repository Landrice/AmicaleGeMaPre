/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.vente;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.ListSold;
import gettersSetters.SellCart;
import gettersSetters.SellCartGerway;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class ViewSellController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    public JFXRadioButton ml;
    @FXML
    private ToggleGroup depotGroup;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    @FXML
    private Button btnSellOrder;
    @FXML
    private TableView<ListSold> tblSellView;
    @FXML
    private TableColumn<Object, Object> tblClmSoldBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmSellId;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerName;
    @FXML
    private TableColumn<Object, Object> tblClmSoldDate;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    userNameMedia nameMedia;

    SellCart sellCart = new SellCart();
    SellCartGerway sellCartGerway = new SellCartGerway();

    String userId;
    @FXML
    private TableColumn<Object, Object> tblClmUnite;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    private Connection con;
    bddConnection dbcon;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst3;
    PreparedStatement pst4;
    PreparedStatement pst31;
    PreparedStatement pst41;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        tblSellView.getItems().clear();
        sellCart.sellID = tfSearch.getText();

        if (ml.isSelected() == true) {
            sellCartGerway.searchView(sellCart, "dml");
        } else if (gl.isSelected() == true) {
            sellCartGerway.searchView(sellCart, "dgl");
        } else if (pt.isSelected() == true) {
            sellCartGerway.searchView(sellCart, "dpt");
        } else if (pz.isSelected() == true) {
            sellCartGerway.searchView(sellCart, "dpz");
        } else if (bl.isSelected() == true) {
            sellCartGerway.searchView(sellCart, "dbl");
        } else {
            System.out.println("Aucune dépot n'est selectionné");
        }

    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        searchall();
    }

    public void searchall(){
        tblSellView.getItems().clear();
        tfSearch.clear();
        sellCart.carts.clear();
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory<>("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory<>("sellDate"));
        //  tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory<>("pursesPrice"));
        //  tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        //  tblClmWarrenty.setCellValueFactory(new PropertyValueFactory<>("warrentyVoidDate"));
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory<>("productname"));
        tblClmUnite.setCellValueFactory(new PropertyValueFactory<>("unite"));

        if (ml.isSelected() == true) {
            sellCartGerway.view(sellCart, "dml");
        } else if (gl.isSelected() == true) {
            sellCartGerway.view(sellCart, "dgl");
        } else if (pt.isSelected() == true) {
            sellCartGerway.view(sellCart, "dpt");
        } else if (pz.isSelected() == true) {
            sellCartGerway.view(sellCart, "dpz");
        } else if (bl.isSelected() == true) {
            sellCartGerway.view(sellCart, "dbl");
        } else {
            System.out.println("Aucune dépot n'est selectionné");
        }
    }
    
    @FXML
    private void btnSellOrderOnAction(ActionEvent event) {
        System.out.println(userId);
        NewSellController acc = new NewSellController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/app/app/vente/NewSell.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            NewSellController newSellController = fXMLLoader.getController();
            if (ml.isSelected() == true) {
                newSellController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                newSellController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                newSellController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                newSellController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                newSellController.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné");
            }
            media.setId(userId);
            newSellController.setNameMedia(nameMedia);
            newSellController.genarateSellID();
            newSellController.disableradio();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewSellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
