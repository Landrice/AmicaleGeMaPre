/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.vente;

import appController.app.stock.AddProductController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.Convertion;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductGetway;
import gettersSetters.CustomTf;
import gettersSetters.Customer;
import gettersSetters.CustomerGetway;
import gettersSetters.ListPreSell;
import gettersSetters.ListProduct;
import gettersSetters.RandomIdGenarator;
import gettersSetters.SellCart;
import gettersSetters.SellCartBLL;
import gettersSetters.SellCartGerway;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class NewSellController implements Initializable {

    @FXML
    private TextField tfProductId;
    @FXML
    private TableView<ListPreSell> tblSellPreList;
    @FXML
    private TableColumn<Object, Object> tblClmSellId;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    @FXML
    private TableColumn<Object, Object> tblClmCustomer;
    @FXML
    private TableColumn<Object, Object> tblClmSolledBy;
    @FXML
    private TableColumn<Object, Object> tblClmWarrentyVoidDate;
    @FXML
    private TextField tfQuantity;
    @FXML
    private Label lblCurrentQuantity;
    @FXML
    private TextField tfSellPrice;
    @FXML
    private Label lblPursesPrice;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblTotalItems;
    @FXML
    private Label lblUnit;
    @FXML
    private TextField tfSupplyer;
    @FXML
    private TextField tfBrand;
    @FXML
    private TextField tfCatagory;
    @FXML
    private TextField tfWarrentVoidDate;
    @FXML
    private Button btnAddToChart;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnAddCustomer;
    @FXML
    private Label lblPursesDate;
    @FXML
    private TextField tfProductName;
    @FXML
    private Button btnClearSelected;
    @FXML
    private MenuButton mbtnselectProduit;
    @FXML
    private TextField tfProduitSearch;
    @FXML
    private TableView<ListProduct> tblProduit;
    @FXML
    private TableColumn<Object, Object> tblClmProduitID;
    @FXML
    private TableColumn<Object, Object> tblClmProduitNom;
    @FXML
    private TextField nbrproduitsNmdf;
    @FXML
    private TextField prixnonMdf;
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
    private JFXRadioButton ml1;
    @FXML
    private ToggleGroup depotGroup1;
    @FXML
    private JFXRadioButton gl1;
    @FXML
    private JFXRadioButton pt1;
    @FXML
    private JFXRadioButton pz1;
    @FXML
    private JFXRadioButton bl1;
    @FXML
    private Label lblSellId;
    @FXML
    private Button btnClose;
    Double quantity;

    userNameMedia nameMedia;
    Convertion convert;
    String userId;
    String customerId;
    String produitid;
    String uniteAconvertir;
    @FXML
    private Label lblNetCost;
    @FXML
    private JFXComboBox<String> unitecmb;
    @FXML
    private TableColumn<Object, Object> tblClmUnity;
    @FXML
    private Label lblunity;
    @FXML
    private TableColumn<Object, Object> tblClmDestination;
    @FXML
    private Label nbr;
    @FXML
    private TableColumn<Object, Object> tblClmunitepp;
    @FXML
    private TableColumn<Object, Object> tblClmnombrepp;
    @FXML
    private Label un;
    @FXML
    private Label oldqtt;
    @FXML
    private Label npm;
    @FXML
    private JFXDatePicker dtt;
    @FXML
    private Label lblnum;
    @FXML
    private Label nombreppm;
    @FXML
    private TableColumn<?, ?> tblClmnbr;
    @FXML
    private TableColumn<?, ?> tblClmnombreppm;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    Customer customer = new Customer();
    CurrentProduct prod = new CurrentProduct();
    CustomerGetway customerGetway = new CustomerGetway();
    CurrentProductGetway produitgetway = new CurrentProductGetway();
    CurrentProduct currrentProduct = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    SellCart sellCart = new SellCart();
    SellCartGerway sellCartGerway = new SellCartGerway();
    SellCartBLL scbll = new SellCartBLL();
    CustomTf ctf = new CustomTf();

    ObservableList<ListPreSell> preList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ml.setDisable(true);
        clearAll();
        dtt.setValue(LocalDate.now());
        uniteAconvertir = unitecmb.getSelectionModel().getSelectedItem();
        tfQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfQuantity.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    @FXML
    private void tfProductIdOnAction(ActionEvent event) {
        completefeild();
    }

    public void disableradio() {
        if (ml.isSelected() == true) {
            ml1.setDisable(true);
        } else if (gl.isSelected() == true) {
            gl1.setDisable(true);
        } else if (pt.isSelected() == true) {
            pt1.setDisable(true);
        } else if (pz.isSelected() == true) {
            pz1.setDisable(true);
        } else if (bl.isSelected() == true) {
            bl1.setDisable(true);
        }
    }

    public void completefeild() {
        if (tfProductId.getText().isEmpty()) {
            clearAll();
        } else {
            currrentProduct.idProduit = tfProductId.getText().trim();
            if (ml.isSelected() == true) {
                currentProductGetway.sView(currrentProduct, "dml");
               // currentProductGetway.sviewcmb(currrentProduct, "dml");
            } else if (pt.isSelected() == true) {
                currentProductGetway.sView(currrentProduct, "dpt");
               // currentProductGetway.sviewcmb(currrentProduct, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductGetway.sView(currrentProduct, "dpz");
               // currentProductGetway.sviewcmb(currrentProduct, "dpz");
            } else if (gl.isSelected() == true) {
                currentProductGetway.sView(currrentProduct, "dgl");
              //  currentProductGetway.sviewcmb(currrentProduct, "dgl");
            } else if (bl.isSelected() == true) {
                currentProductGetway.sView(currrentProduct, "dbl");
              //  currentProductGetway.sviewcmb(currrentProduct, "dbl");
            } else {
                System.out.println("Aucune dépot selectioné");
            }
            un.setText(currrentProduct.unite);
            unitecmb.getItems().clear();
            unitecmb.getItems().addAll(currrentProduct.unite,currrentProduct.unitepp,currrentProduct.uniteppm);
            unitecmb.getSelectionModel().select(0);
            //   lblUnit.setText(currrentProduct.unitName);
            lblCurrentQuantity.setText(currrentProduct.quantite);
            tfQuantity.setText(currrentProduct.quantite);
            oldqtt.setText(currrentProduct.quantite);
            // nbr.setText(currrentProduct.quantite);
            nbrproduitsNmdf.setText(currrentProduct.quantite);
            tfSellPrice.setText(currrentProduct.prix);
            //   lblPursesPrice.setText(currrentProduct.prix);
            tfBrand.setText(currrentProduct.marqueNom);
            tfSupplyer.setText(currrentProduct.fournisseurNom);
            tfCatagory.setText(currrentProduct.categorieNom);
            tfWarrentVoidDate.setText(currrentProduct.garantie);
            lblPursesDate.setText(currrentProduct.date);
            tfProductName.setText(currrentProduct.nomProduit);
            tfSellPrice.setText(currrentProduct.prix);
            lblunity.setText(currrentProduct.unite);
            lblUnit.setText(currrentProduct.unite);
            nombreppm.setText(currrentProduct.nombreppm);
            if(currrentProduct.unitepp.equals("")){
                nbr.setText("1");
            }else{
                nbr.setText(currrentProduct.nombrepp);
            }
            if(currrentProduct.uniteppm.equals("")){
                npm.setText("1");
            }else{
                npm.setText(currrentProduct.nombreppm);
            }
            System.out.println("Unite est: " + currrentProduct.unite);
            unitecmb.getSelectionModel().select(0);
        }
    }

    public void clearAll() {
        tfBrand.clear();
        tfProductId.clear();
        tfCatagory.clear();
        tfSellPrice.clear();
        tfSupplyer.clear();
        nbrproduitsNmdf.clear();
        prixnonMdf.clear();
        // unitecmb.getSelectionModel().select(-1);
        depotGroup1.selectToggle(null);
        tfWarrentVoidDate.clear();
        tfQuantity.clear();
        tfProductName.clear();
        lblCurrentQuantity.setText(null);
        lblNetCost.setText(null);
        lblPursesPrice.setText(null);
        lblUnit.setText(null);
        lblPursesDate.setText(null);
    }

    @FXML
    private void tfQuantityOnAction(KeyEvent event) {
        if (!tfQuantity.getText().isEmpty()) {
            String givenQuentity = tfQuantity.getText();
            Double givenQinInt = Double.parseDouble(givenQuentity);
            String currentQuentity = lblCurrentQuantity.getText();
            Double currentQuentiInt = Double.parseDouble(currentQuentity);
            if (givenQinInt > currentQuentiInt) {
                System.out.println("BIG");
                tfQuantity.clear();
                lblNetCost.setText("0.0");
                prixnonMdf.setText("0.0");
            } else {
                quantity = Double.parseDouble(tfQuantity.getText());
                float sellPrice = Float.parseFloat(tfSellPrice.getText());
                String netPrice = String.valueOf(sellPrice * quantity);
                lblNetCost.setText(netPrice);
                prixnonMdf.setText(netPrice);
            }
        } else {
            lblNetCost.setText("0.0");
            prixnonMdf.setText("0.0");
        }
    }

    @FXML
    private void tfSellPriceOnAction(KeyEvent event) {
        System.out.println("PRESSES");

        if (!tfSellPrice.getText().isEmpty()) {
            String quentity = tfQuantity.getText();
            Double intQuentity = Double.parseDouble(quentity);
            String sellPrice = tfSellPrice.getText();
            Double fSellPrice = Double.parseDouble(sellPrice);
            String sTotalPrice = String.valueOf(intQuentity * fSellPrice);
            lblNetCost.setText(sTotalPrice);
            System.out.println(sTotalPrice);
        } else {
            lblNetCost.setText("0.0");
        }
    }

    @FXML
    private void btnAddToChartOnAction(ActionEvent event) {
        if (inNotNull()) {
            
            if (ml1.isSelected() == true) {
                customerId = "dml";
                preList.add(new ListPreSell(currrentProduct.id, currrentProduct.idProduit, customerId, currrentProduct.prix, tfSellPrice.getText(), oldqtt.getText(), tfQuantity.getText(), lblNetCost.getText(), currrentProduct.date, dtt.getValue().toString(), userId, dtt.getValue().toString(), un.getText(),unitecmb.getSelectionModel().getSelectedItem(),currrentProduct.nombrepp,lblCurrentQuantity.getText(),lblnum.getText(),nombreppm.getText(),unitecmb.getSelectionModel().getSelectedItem(),tfProductName.getText()));
                viewAll();
                sumTotalCost();
                clearAll();
            } else if (gl1.isSelected() == true) {
                customerId = "dgl";
                preList.add(new ListPreSell(currrentProduct.id, currrentProduct.idProduit, customerId, currrentProduct.prix, tfSellPrice.getText(), oldqtt.getText(), tfQuantity.getText(), lblNetCost.getText(), currrentProduct.date, dtt.getValue().toString(), userId, dtt.getValue().toString(), un.getText(),unitecmb.getSelectionModel().getSelectedItem(),currrentProduct.nombrepp,lblCurrentQuantity.getText(),lblnum.getText(),nombreppm.getText(),unitecmb.getSelectionModel().getSelectedItem(),tfProductName.getText()));
                viewAll();
                sumTotalCost();
                clearAll();
            } else if (pt1.isSelected() == true) {
                customerId = "dpt";
                preList.add(new ListPreSell(currrentProduct.id, currrentProduct.idProduit, customerId, currrentProduct.prix, tfSellPrice.getText(), oldqtt.getText(), tfQuantity.getText(), lblNetCost.getText(), currrentProduct.date, dtt.getValue().toString(), userId, dtt.getValue().toString(), un.getText(),unitecmb.getSelectionModel().getSelectedItem(),currrentProduct.nombrepp,lblCurrentQuantity.getText(),lblnum.getText(),nombreppm.getText(),unitecmb.getSelectionModel().getSelectedItem(),tfProductName.getText()));
                viewAll();
                sumTotalCost();
                clearAll();
            } else if (pz1.isSelected() == true) {
                customerId = "dpz";
                preList.add(new ListPreSell(currrentProduct.id, currrentProduct.idProduit, customerId, currrentProduct.prix, tfSellPrice.getText(), oldqtt.getText(), tfQuantity.getText(), lblNetCost.getText(), currrentProduct.date, dtt.getValue().toString(), userId, dtt.getValue().toString(), un.getText(),unitecmb.getSelectionModel().getSelectedItem(),currrentProduct.nombrepp,lblCurrentQuantity.getText(),lblnum.getText(),nombreppm.getText(),unitecmb.getSelectionModel().getSelectedItem(),tfProductName.getText()));
                viewAll();
                sumTotalCost();
                clearAll();
            } else if (bl1.isSelected() == true) {
                customerId = "dbl";
                preList.add(new ListPreSell(currrentProduct.id, currrentProduct.idProduit, customerId, currrentProduct.prix, tfSellPrice.getText(), oldqtt.getText(), tfQuantity.getText(), lblNetCost.getText(), currrentProduct.date, dtt.getValue().toString(), userId, dtt.getValue().toString(), un.getText(),unitecmb.getSelectionModel().getSelectedItem(),currrentProduct.nombrepp,lblCurrentQuantity.getText(),lblnum.getText(),nombreppm.getText(),unitecmb.getSelectionModel().getSelectedItem(),tfProductName.getText()));
                viewAll();
                sumTotalCost();
                clearAll();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERREUR");
                alert.setHeaderText("ERREUR");
                alert.setContentText("Veuiller selectionner le destinataire");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }
            unitecmb.getItems().clear();
        }
    }

    public boolean inNotNull() {
        boolean isNotNull = false;

        if (tfQuantity.getText().trim().matches("")) {
//            Dialogs.create().title("").masthead("ERROR").message("Please fill requere field").styleClass(org.controlsfx.dialog.Dialog.STYLE_CLASS_UNDECORATED).showError();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("ERREUR");
            alert.setContentText("Veuiller remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    public void viewAll() {
        tblSellPreList.setItems(preList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmUnity.setCellValueFactory(new PropertyValueFactory<>("unite"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tblClmWarrentyVoidDate.setCellValueFactory(new PropertyValueFactory<>("warrentyVoidDate"));
        tblClmDestination.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tblClmunitepp.setCellValueFactory(new PropertyValueFactory<>("unitepp"));
        tblClmnombrepp.setCellValueFactory(new PropertyValueFactory<>("nombrepp"));
        tblClmnbr.setCellValueFactory(new PropertyValueFactory<>("numeropp"));
        tblClmnombreppm.setCellValueFactory(new PropertyValueFactory<>("nombreppm"));
        
    }

    private void sumTotalCost() {
        tblSellPreList.getSelectionModel().selectFirst();
        Double sum = 0.0;
        int items = tblSellPreList.getItems().size();

        for (int i = 0; i < items; i++) {
            tblSellPreList.getSelectionModel().select(i);
            String selectedItem = tblSellPreList.getSelectionModel().getSelectedItem().getTotalPrice();
            Double newFloat = Double.parseDouble(selectedItem);
            sum = sum + newFloat;
        }
        String totalCost = String.valueOf(sum);
        lblTotal.setText(totalCost);
        System.out.println("Total:" + sum);
        String totalItem = String.valueOf(items);
        lblTotalItems.setText(totalItem);
    }

    @FXML
    private void btnSellOnAction(ActionEvent event) {
        if (!tblSellPreList.getItems().isEmpty()) {
            System.out.println(lblSellId.getText());
            int indexs = tblSellPreList.getItems().size();
            for (int i = 0; i < indexs; i++) {
                tblSellPreList.getSelectionModel().select(i);
                sellCart.Id = tblSellPreList.getSelectionModel().getSelectedItem().getId();
                sellCart.productID = tblSellPreList.getSelectionModel().getSelectedItem().getProductID();
                sellCart.sellID = lblSellId.getText();
                sellCart.customerID = customerId;
                sellCart.pursesPrice = tblSellPreList.getSelectionModel().getSelectedItem().getPursesPrice();
                sellCart.sellPrice = tblSellPreList.getSelectionModel().getSelectedItem().getSellPrice();
                sellCart.customerID=tblSellPreList.getSelectionModel().getSelectedItem().getCustomerID();
                sellCart.unite=tblSellPreList.getSelectionModel().getSelectedItem().getUnitepp();
                sellCart.unitemdf=tblSellPreList.getSelectionModel().getSelectedItem().getUnitemdf();
                sellCart.quantity = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                sellCart.oldQuentity = tblSellPreList.getSelectionModel().getSelectedItem().getOldQuantity();
                //     sellCart.quantity = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                //     sellCart.unite=tblSellPreList.getSelectionModel().getSelectedItem().getUnite();
                sellCart.totalPrice = tblSellPreList.getSelectionModel().getSelectedItem().getTotalPrice();
                sellCart.warrentyVoidDate = tblSellPreList.getSelectionModel().getSelectedItem().getWarrentyVoidDate();
                sellCart.sellerID = userId;
                sellCart.nombrepp=tblSellPreList.getSelectionModel().getSelectedItem().getNombrepp();
                sellCart.sellDate=tblSellPreList.getSelectionModel().getSelectedItem().getWarrentyVoidDate();
                sellCart.sellnom=tblSellPreList.getSelectionModel().getSelectedItem().getNomproduit();
                
                
                if(tblSellPreList.getSelectionModel().getSelectedItem().getUnite().equals(tblSellPreList.getSelectionModel().getSelectedItem().getUnitepp())){
                    sellCart.quantity = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                    sellCart.quantitymdf = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                }
                else{
                    if(tblSellPreList.getSelectionModel().getSelectedItem().getNumeropp().equals("1")){
                    sellCart.quantity = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                    Double nbre=Double.parseDouble(tblSellPreList.getSelectionModel().getSelectedItem().getNombrepp());
                    Double nbra=Double.parseDouble(tblSellPreList.getSelectionModel().getSelectedItem().getQuantity());
                    Double rstl=nbra/nbre;
                    sellCart.quantitymdf=rstl.toString();
                    
                    
                    }else if(tblSellPreList.getSelectionModel().getSelectedItem().getNumeropp().equals("2")){
                        sellCart.quantity = tblSellPreList.getSelectionModel().getSelectedItem().getQuantity();
                    Double nbre=Double.parseDouble(tblSellPreList.getSelectionModel().getSelectedItem().getNombrepp());
                    Double nbra=Double.parseDouble(tblSellPreList.getSelectionModel().getSelectedItem().getQuantity());
                    Double nbrem=Double.parseDouble(tblSellPreList.getSelectionModel().getSelectedItem().getNombreppm());
                    Double rstl=nbra/nbrem;                             
                    Double rstlm=rstl/nbre;
                    sellCart.quantitymdf=rstlm.toString();
                    }
                    
                }

                if (ml.isSelected() == true) {
                    if (sellCart.customerID.equals("dml")) {
                        scbll.sell(sellCart, "dml","dml");
                    } else if (sellCart.customerID.equals("dgl")) {
                        scbll.sell(sellCart, "dml","dgl");
                    } else if (sellCart.customerID.equals("dpt")) {
                        System.out.println("test sur patisserie 01");
                        scbll.sell(sellCart, "dml","dpt");
                        System.out.println("test sur patisserie 02");
                    } else if (sellCart.customerID.equals("dpz")) {
                        scbll.sell(sellCart, "dml","dpz");
                    } else if (sellCart.customerID.equals("dbl")) {
                        scbll.sell(sellCart, "dml","dbl");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune destinataire selectionné dans le variable");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                    
                } else if (gl.isSelected() == true) {
                    if (sellCart.customerID.equals("dml")) {
                        scbll.sell(sellCart, "dgl","dml");
                    } else if (sellCart.customerID.equals("dgl")) {
                        scbll.sell(sellCart, "dgl","dgl");
                    } else if (sellCart.customerID.equals("dpt")) {
                        scbll.sell(sellCart, "dgl","dpt");
                    } else if (sellCart.customerID.equals("dpz")) {
                        scbll.sell(sellCart, "dgl","dpz");
                    } else if (sellCart.customerID.equals("dbl")) {
                        scbll.sell(sellCart, "dgl","dbl");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune destinataire selectionné dans le variable");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                   // scbll.sell(sellCart, "dgl");
                } else if (pt.isSelected() == true) {
                   if (sellCart.customerID.equals("dml")) {
                        scbll.sell(sellCart, "dpt","dml");
                    } else if (sellCart.customerID.equals("dgl")) {
                        scbll.sell(sellCart, "dpt","dgl");
                    } else if (sellCart.customerID.equals("dpt")) {
                        scbll.sell(sellCart, "dpt","dpt");
                    } else if (sellCart.customerID.equals("dpz")) {
                        scbll.sell(sellCart, "dpt","dpz");
                    } else if (sellCart.customerID.equals("dbl")) {
                        scbll.sell(sellCart, "dpt","dbl");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune destinataire selectionné dans le variable");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                    //scbll.sell(sellCart, "dpt");
                } else if (pz.isSelected() == true) {
                    if (sellCart.customerID.equals("dml")) {
                        scbll.sell(sellCart, "dpz","dml");
                    } else if (sellCart.customerID.equals("dgl")) {
                        scbll.sell(sellCart, "dpz","dgl");
                    } else if (sellCart.customerID.equals("dpt")) {
                        scbll.sell(sellCart, "dpz","dpt");
                    } else if (sellCart.customerID.equals("dpz")) {
                        scbll.sell(sellCart, "dpz","dpz");
                    } else if (sellCart.customerID.equals("dbl")) {
                        scbll.sell(sellCart, "dpz","dbl");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune destinataire selectionné dans le variable");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                    //scbll.sell(sellCart, "dpz");
                } else if (bl.isSelected() == true) {
                    if (sellCart.customerID.equals("dml")) {
                        scbll.sell(sellCart, "dbl","dml");
                    } else if (sellCart.customerID.equals("dgl")) {
                        scbll.sell(sellCart, "dbl","dgl");
                    } else if (sellCart.customerID.equals("dpt")) {
                        scbll.sell(sellCart, "dbl","dpt");
                    } else if (sellCart.customerID.equals("dpz")) {
                        scbll.sell(sellCart, "dbl","dpz");
                    } else if (sellCart.customerID.equals("dbl")) {
                        scbll.sell(sellCart, "dbl","dbl");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune destinataire selectionné dans le variable");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                   // scbll.sell(sellCart, "dbl");
                } else {
                    System.out.println("Aucune dépot n'est selectionné");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Aucune dépot n'est selectionné");
                        //alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                }

                System.out.println("Old Quentity:" + tblSellPreList.getSelectionModel().getSelectedItem().getOldQuantity());
            }
            refreshProductList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Succès");
            alert.setContentText("Transfert reussi");
            //alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            tblSellPreList.getItems().clear();
            lblTotal.setText(null);

            System.out.println("Customer ID: " + customerId);
        } else {
            System.out.println("EMPTY");
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Vide");
            a.setHeaderText("La carte est vide");
            a.setContentText("veuiller ajouter le produit à la carte");
            a.showAndWait();
        }
    }

    @FXML
    private void btnAddCustomerOnAction(ActionEvent event) {
    }

    @FXML
    private void btnClearSelectedOnAction(ActionEvent event) {
        if (tblSellPreList.getItems().size() != 0) {
            System.out.println("Clicked");
            tblSellPreList.getItems().removeAll(tblSellPreList.getSelectionModel().getSelectedItems());
            sumTotalCost();
        } else {
            System.out.println("EMPTY");
        }
    }

    @FXML
    private void tfProduitrecherche(KeyEvent event) {
        tfProductId.setText(tblProduit.getSelectionModel().getSelectedItem().getIdProduit());
        produitid = tblProduit.getSelectionModel().getSelectedItem().getId();
        System.out.println(produitid);
    }

    @FXML
    private void tblProduitOnClick(MouseEvent event) {
        tfProductId.setText(tblProduit.getSelectionModel().getSelectedItem().getIdProduit());
        produitid = tblProduit.getSelectionModel().getSelectedItem().getId();
        System.out.println(produitid);
        ListProduct lst = tblProduit.getSelectionModel().getSelectedItem();
        String items = lst.getIdProduit();
        //int itemsInt = Integer.parseInt(items);
        tfProductId.setText(items);
        completefeild();
        mbtnselectProduit.hide();
    }

    @FXML
    private void mbtnProduitOnactionselect(MouseEvent event) {
        prod.nomProduit = tfProduitSearch.getText().trim();
        tblProduit.setItems(prod.currentProductList);
        tblClmProduitID.setCellValueFactory(new PropertyValueFactory<>("IdProduit"));
        tblClmProduitNom.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));

        if (ml.isSelected() == true) {
            produitgetway.searchView(prod, "dml");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (gl.isSelected() == true) {
            produitgetway.searchViewm(prod, "dgl");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (pt.isSelected() == true) {
            produitgetway.searchViewm(prod, "dpt");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (pz.isSelected() == true) {
            produitgetway.searchViewm(prod, "dpz");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (bl.isSelected() == true) {
            produitgetway.searchViewm(prod, "dbl");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else {
            System.out.println("Aucune dépot n'est selectionné ");
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void genarateSellID() {
        String id = RandomIdGenarator.randomstring();
        if (id.matches("001215")) {
            String nId = RandomIdGenarator.randomstring();
            lblSellId.setText(nId);
        } else {
            lblSellId.setText(id);
        }

    }

    @FXML
    private void unitecmOnAction(ActionEvent event) {
        convert = new Convertion();
        //uniteAconvertir = lblunity.getText();
        Double aconvertir = Double.parseDouble(nbrproduitsNmdf.getText());
        Double piece=Double.parseDouble(nbr.getText());
        Double resultatnbr=aconvertir*piece;
        Double prixaconvertir = Double.parseDouble(tfSellPrice.getText());
        Double pr=prixaconvertir/piece;
        
        Double aconvertirm = resultatnbr;
        Double piecem=Double.parseDouble(npm.getText());
        Double resultatnbrm=aconvertirm*piecem;
        Double prixaconvertirm = prixaconvertir;
        Double prm=prixaconvertirm/piecem;

        if (unitecmb.getSelectionModel().getSelectedIndex()==0) {
            tfQuantity.setText(currrentProduct.quantite);
            nbrproduitsNmdf.setText(currrentProduct.quantite);
            lblCurrentQuantity.setText(currrentProduct.quantite);
            tfSellPrice.clear();
            tfSellPrice.setText(currrentProduct.prix);
            System.out.println(currrentProduct.prix);
            lblunity.setText(unitecmb.getSelectionModel().getSelectedItem());
            lblUnit.setText(unitecmb.getSelectionModel().getSelectedItem());
        } else if (unitecmb.getSelectionModel().getSelectedIndex()==1) {
            tfQuantity.setText(resultatnbr.toString());
            nbrproduitsNmdf.setText(resultatnbr.toString());
            lblCurrentQuantity.setText(resultatnbr.toString());
            tfSellPrice.clear();
            tfSellPrice.setText(pr.toString());
            System.out.println(pr.toString());
            lblunity.setText(unitecmb.getSelectionModel().getSelectedItem());
            lblUnit.setText(unitecmb.getSelectionModel().getSelectedItem());
            lblnum.setText("1");
        }   else if (unitecmb.getSelectionModel().getSelectedIndex()==2) {
            tfQuantity.setText(resultatnbrm.toString());
            nbrproduitsNmdf.setText(resultatnbrm.toString());
            lblCurrentQuantity.setText(resultatnbrm.toString());
            tfSellPrice.clear();
            tfSellPrice.setText(prm.toString());
            System.out.println(prm.toString());
            lblunity.setText(unitecmb.getSelectionModel().getSelectedItem());
            lblUnit.setText(unitecmb.getSelectionModel().getSelectedItem());
            lblnum.setText("2");
        }
        else {
            System.out.println("Aucune unité a convertir selectionné");
        }
    }

    public void refreshProductList() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/app/app/vente/ViewSell.fxml").openStream());
            ViewSellController currentStoreController = fXMLLoader.getController();
            currentStoreController.searchall();
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
