/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.stock;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.Convertion;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
public class AddProductController implements Initializable {

    @FXML
    Label lblHeader;
    @FXML
    private RadioButton rbStatic;
    @FXML
    private RadioButton rbSeq;
    @FXML
    private TextField tfProductId;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductQuantity;
    @FXML
    private TextField tfProductPursesPrice;
    @FXML
    private TextArea taProductDescription;
    @FXML
    private Button btnAddSupplier;
    @FXML
    private Button btnAddBrand;
    @FXML
    private Button btnAddCatagory;
    @FXML
    Button btnSave;
    @FXML
    private TextField tfValue;
    @FXML
    private ComboBox<String> cbSupplyer;
    @FXML
    private ComboBox<String> cmbBrand;
    @FXML
    private ComboBox<String> cmbCatagory;
    @FXML
    public Button btnUpdate;
    @FXML
    private JFXDatePicker dpDate;
    private TextField seuiStock;
    @FXML
    private ComboBox<String> categoriestatic;
    @FXML
    private Button btnClose;
    private String usrId;
    private userNameMedia nameMedia;
    Convertion convert;
    CurrentProduct currentProduct = new CurrentProduct();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    SQLSyntax sql = new SQLSyntax();

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    public String id;
    private String supplyerName;
    private String supplyerId;
    private String brandName;
    private String brandId;
    private String catagoryName;
    private String catagoryId;
    private String unitId;
    private String rmaId;
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
    @FXML
    private JFXComboBox<String> unite;
    @FXML
    private TextField tfProductQuantityCategory;
    @FXML
    private JFXComboBox<String> unitePiece;
    @FXML
    private Button btnAddUnit;
    TextInputDialog dialog;
    bddConnection dbConnection = new bddConnection();
    @FXML
    private TextField tfProductQuantityCategory1;
    @FXML
    private JFXComboBox<String> uniteMiniPiece;

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
//        unite.getItems().addAll("SAC", "KILOGRAMME", "GRAMME", "LITRE", "BOUTEILLE","");
//        unite.getSelectionModel().select(1);
        ToggleGroup firstRedioBtn = new ToggleGroup();
        rbSeq.setToggleGroup(firstRedioBtn);
        rbStatic.setSelected(true);
        rbStatic.setToggleGroup(firstRedioBtn);
        tfValue.setVisible(false);
        dpDate.setValue(LocalDate.now());
        tfProductQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfProductQuantity.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        tfProductQuantityCategory.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfProductQuantityCategory.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        tfProductQuantityCategory1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfProductQuantityCategory1.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        tfProductPursesPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,99}([\\.]\\d{0,4})?")) {
                    tfProductPursesPrice.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
       /* seuiStock.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    seuiStock.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });*/
    }

    @FXML
    private void rbStaticOnClicked(MouseEvent event) {
        if (rbStatic.isSelected()) {
            tfValue.setVisible(false);
            tfValue.clear();
        } else if (!rbStatic.isSelected()) {
            tfValue.setVisible(true);
        }
    }

    @FXML
    private void rbStaticOnAction(ActionEvent event) {
    }

    @FXML
    private void rbSeqOnClick(MouseEvent event) {
        if (rbSeq.isSelected()) {
            tfValue.setVisible(true);
        } else if (!rbSeq.isSelected()) {
            tfValue.setVisible(false);
        }
    }

    @FXML
    private void rbSeqOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddSupplierOnAction(ActionEvent event) {
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

            media.setId(usrId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Ajout Fournisseur");
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

            media.setId(usrId);
            supplyerController.setMedia(media);
            supplyerController.lblHeader.setText("Ajouter Marque");
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
    private void btnAddCatagoryOnAction(ActionEvent event) {
        AddCatagoryController addCatagoryController = new AddCatagoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddCatagory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCatagoryController catagoryController = fxmlLoader.getController();
            if (ml.isSelected() == true) {
                catagoryController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                catagoryController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                catagoryController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                catagoryController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                catagoryController.bl.setSelected(true);
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }
            media.setId(usrId);
            catagoryController.setMedia(media);
            catagoryController.lblHeaderContent.setText("Ajout");
            catagoryController.btnUpdate.setVisible(false);
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
    private void btnSaveOnAction(ActionEvent event) {
        if (tfProductPursesPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Alerte: Champ vide");
            alert.setContentText("Le produit doit avoir un prix");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } else {
            System.out.println("Presesd");
            if (isNotNull()) {
                if (!tfValue.getText().trim().isEmpty()) {
                    String value = tfValue.getText();
                    int foo = Integer.parseInt(value);
                    for (int i = 1; i <= foo; ++i) {
                        convert = new Convertion();
                        currentProduct.idProduit = tfProductId.getText().trim() + i;
                        currentProduct.nomProduit = tfProductName.getText().trim();
                        currentProduct.unite = unite.getSelectionModel().getSelectedItem();
                        currentProduct.quantite = tfProductQuantity.getText().trim();
                        currentProduct.description = taProductDescription.getText().trim();
                        currentProduct.prix = tfProductPursesPrice.getText().trim();
                        currentProduct.fournisseurID = supplyerId;
                        currentProduct.marqueID = brandId;
                        currentProduct.categorieID = catagoryId;
                        currentProduct.utilisateurID = usrId;
                        currentProduct.date = dpDate.getValue().toString();
                        currentProduct.nombrepp = tfProductQuantityCategory.getText().trim();
                        currentProduct.unitepp = unitePiece.getSelectionModel().getSelectedItem();
                        currentProduct.nombreppm = tfProductQuantityCategory1.getText().trim();
                        currentProduct.uniteppm = uniteMiniPiece.getSelectionModel().getSelectedItem();

                        if (ml.isSelected() == true) {
                            currentProductBLL.save(currentProduct, "dml");
                            currentProduct.quantite = "0";
                            currentProductBLL.save(currentProduct, "dgl");
                            currentProduct.quantite = tfProductQuantity.getText().trim();
                            currentProduct.quantite = "0";
                            currentProductBLL.save(currentProduct, "dpt");
                            currentProduct.quantite = tfProductQuantity.getText().trim();
                            currentProduct.quantite = "0";
                            currentProductBLL.save(currentProduct, "dpz");
                            currentProduct.quantite = tfProductQuantity.getText().trim();
                            currentProduct.quantite = "0";
                            currentProductBLL.save(currentProduct, "dbl");
                            currentProduct.quantite = tfProductQuantity.getText().trim();

                        } else if (gl.isSelected() == true) {

                        } else if (pt.isSelected() == true) {

                        } else if (pz.isSelected() == true) {

                        } else if (bl.isSelected() == true) {

                        } else {
                            System.out.println("Aucune categorie n'est selectionné");
                            //currentProductGetway.viewFistTen(productCurrent);
                        }
                    }
                } else {
                    convert = new Convertion();
                    System.out.println(" Second add to else");
                    currentProduct.idProduit = tfProductId.getText().trim();
                    currentProduct.nomProduit = tfProductName.getText().trim();
                    currentProduct.unite = unite.getSelectionModel().getSelectedItem();
                    currentProduct.quantite = tfProductQuantity.getText().trim();
                    currentProduct.description = taProductDescription.getText().trim();
                    currentProduct.prix = tfProductPursesPrice.getText().trim();
                    currentProduct.fournisseurID = supplyerId;
                    currentProduct.marqueID = brandId;
                    currentProduct.categorieID = catagoryId;
                    currentProduct.utilisateurID = usrId;
                    currentProduct.date = dpDate.getValue().toString();
                    currentProduct.nombrepp = tfProductQuantityCategory.getText().trim();
                    currentProduct.unitepp = unitePiece.getSelectionModel().getSelectedItem();
                    currentProduct.nombreppm = tfProductQuantityCategory1.getText().trim();
                    currentProduct.uniteppm = uniteMiniPiece.getSelectionModel().getSelectedItem();

                    if (ml.isSelected() == true) {
                        currentProductBLL.save(currentProduct, "dml");
                        currentProduct.quantite = "0";
                        currentProductBLL.save(currentProduct, "dgl");
                        currentProduct.quantite = tfProductQuantity.getText().trim();
                        currentProduct.quantite = "0";
                        currentProductBLL.save(currentProduct, "dpt");
                        currentProduct.quantite = tfProductQuantity.getText().trim();
                        currentProduct.quantite = "0";
                        currentProductBLL.save(currentProduct, "dpz");
                        currentProduct.quantite = tfProductQuantity.getText().trim();
                        currentProduct.quantite = "0";
                        currentProductBLL.save(currentProduct, "dbl");
                        currentProduct.quantite = tfProductQuantity.getText().trim();

                    } else if (gl.isSelected() == true) {

                    } else if (pt.isSelected() == true) {

                    } else if (pz.isSelected() == true) {

                    } else if (bl.isSelected() == true) {

                    } else {
                        System.out.println("Aucune categorie n'est selectionné");
                        //currentProductGetway.viewFistTen(productCurrent);
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succès");
            alert.setHeaderText("succès: Sauvegarde reussi");
            alert.setContentText("Le produit a été ajouté avec succès");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    @FXML
    private void cbSupplyerOnClicked(MouseEvent event) {
        cbSupplyer.getSelectionModel().clearSelection();
        cbSupplyer.getItems().clear();
        cmbBrand.getSelectionModel().clearSelection();
        cmbBrand.getItems().clear();
        cmbBrand.getItems().removeAll();
        try {
            pst = con.prepareStatement("select * from " + db + ".fournisseur");
            rs = pst.executeQuery();
            while (rs.next()) {
                cbSupplyer.getItems().addAll(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbSupplyerOnAction(ActionEvent event) {
        cbSupplyer.getSelectionModel().getSelectedItem();

        try {
            pst = con.prepareStatement("select * from " + db + ".fournisseur where NomFournisseur=?");
            pst.setString(1, cbSupplyer.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyerId = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbBrandOnClick(MouseEvent event) {
        cmbBrand.getItems().clear();
        cmbCatagory.getItems().clear();
        cmbCatagory.getItems().removeAll();
        cmbCatagory.setPromptText(null);
        try {

            pst = con.prepareStatement("select * from " + db + ".marque where FournisseurID=?");
            pst.setString(1, supplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbBrand.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbBrandOnAction(ActionEvent event) {
        System.out.println("AAAZZZZZ0a");
        cmbBrand.getSelectionModel().getSelectedItem();
        System.out.println("AAAZZZZZ0b");
        try {

            pst = con.prepareStatement("select * from " + db + ".marque where NomMarque=? and FournisseurID=?");
            pst.setString(1, cmbBrand.getSelectionModel().getSelectedItem());
            pst.setString(2, supplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                brandId = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbCatagoryOnClick(MouseEvent event) {
        cmbCatagory.getItems().clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".categorie where FournisseurId=? and MarqueId=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbCatagory.getItems().addAll(rs.getString(2));
                catagoryId = rs.getString(1);
                System.out.println(catagoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cmbCatagoryOnAction(ActionEvent event) {
        cmbCatagory.getSelectionModel().getSelectedItem();
        try {
            pst = con.prepareStatement("select * from " + db + ".categorie where FournisseurId=? and MarqueId=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                catagoryId = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {

        if (isNotNull()) {
            System.out.println(supplyerId + brandId + usrId);

            currentProduct.idProduit = tfProductId.getText();
            currentProduct.nomProduit = tfProductName.getText();
            currentProduct.quantite = tfProductQuantity.getText();
            currentProduct.prix = tfProductPursesPrice.getText();
            currentProduct.fournisseurID = supplyerId;
            currentProduct.marqueID = brandId;
            currentProduct.categorieID = catagoryId;
            currentProduct.description = taProductDescription.getText();
            //currentProduct.prix = unitId;
            currentProduct.id = id;
            currentProduct.date = dpDate.getValue().toString();
            currentProduct.utilisateurID = usrId;
            currentProduct.quantite = tfProductQuantity.getText().trim();
            currentProduct.unite = unite.getSelectionModel().getSelectedItem();
            if (ml.isSelected() == true) {
                currentProductBLL.update(currentProduct, "dml");
            } else if (gl.isSelected() == true) {
                currentProductBLL.update(currentProduct, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductBLL.update(currentProduct, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductBLL.update(currentProduct, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductBLL.update(currentProduct, "dbl");
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }
            refreshProductList();
        }
        Alert aler = new Alert(Alert.AlertType.INFORMATION);
        aler.setTitle("Sucess");
        aler.setHeaderText("Mise à Jour : Mise à jour réussi");
        aler.setContentText("Categorie" + "  '" + currentProduct.idProduit + "' " + "a été mise à jour");
        aler.initStyle(StageStyle.UNDECORATED);
        aler.showAndWait();
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private boolean isNotNull() {
        boolean insNotNull = false;
        if (cbSupplyer.getSelectionModel().getSelectedItem() == null
                && cbSupplyer.getPromptText().isEmpty()
                || cmbBrand.getSelectionModel().getSelectedItem() == null
                && cmbBrand.getPromptText().isEmpty()
                || cmbCatagory.getSelectionModel().getSelectedItem() == null
                && cmbCatagory.getPromptText().isEmpty()
                || tfProductId.getText().isEmpty()
                || tfProductName.getText().isEmpty()
                || tfProductQuantity.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("ERREUR: Le champ ne peut pas être vide");
            alert.setContentText("Veuillez remplir les champs necessaires");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            insNotNull = false;
        } else {
            insNotNull = true;
        }
        return insNotNull;
    }

    public void viewSelected() {
        currentProduct.id = id;

        if (ml.isSelected() == true) {
            currentProductGetway.selectedView(currentProduct, "dml");
        } else if (gl.isSelected() == true) {
            currentProductGetway.selectedView(currentProduct, "dgl");
        } else if (pt.isSelected() == true) {
            currentProductGetway.selectedView(currentProduct, "dpt");
        } else if (pz.isSelected() == true) {
            currentProductGetway.selectedView(currentProduct, "dpz");
        } else if (bl.isSelected() == true) {
            currentProductGetway.selectedView(currentProduct, "dbl");
        } else {
            System.out.println("Aucune categorie n'est selectionné");
            //currentProductGetway.viewFistTen(productCurrent);
        }

        tfProductId.setText(currentProduct.idProduit);
        tfProductName.setText(currentProduct.nomProduit);
        tfProductQuantity.setText(currentProduct.quantite);
        tfProductPursesPrice.setText(currentProduct.prix);
        taProductDescription.setText(currentProduct.description);
        // dpDate.setValue(LocalDate.parse(currentProduct.date));
        //dpDate.setValue(LocalDate.now());
        supplyerId = currentProduct.fournisseurID;
        brandId = currentProduct.marqueID;
        catagoryId = currentProduct.categorieID;
        cbSupplyer.setPromptText(currentProduct.fournisseurNom);
        cmbBrand.setPromptText(currentProduct.marqueNom);
        cmbCatagory.setPromptText(currentProduct.categorieNom);
        //unite.setPromptText(unite.getSelectionModel().getSelectedItem());

    }

    public void refreshProductList() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/app/app/stock/CurrentStore.fxml").openStream());
            CurrentStoreController currentStoreController = fXMLLoader.getController();
            currentStoreController.viewDetails();
            currentStoreController.searchAll();
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void uniteOnaction(ActionEvent event) {
    }

    @FXML
    private void cmbunitecliked(MouseEvent event) {
        unite.getSelectionModel().clearSelection();
        unite.getItems().clear();
        unite.getSelectionModel().clearSelection();
        unite.getItems().clear();
        unite.getItems().removeAll();
        try {
            con=dbCon.geConnection();
            pst = con.prepareStatement("select * from " + db + ".unite");
            rs = pst.executeQuery();
            while (rs.next()) {
                unite.getItems().addAll(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cmbunitePiececliked(MouseEvent event) {
        unitePiece.getSelectionModel().clearSelection();
        unitePiece.getItems().clear();
        unitePiece.getSelectionModel().clearSelection();
        unitePiece.getItems().clear();
        unitePiece.getItems().removeAll();
        try {
            pst = con.prepareStatement("select * from " + db + ".unite");
            rs = pst.executeQuery();
            while (rs.next()) {
                unitePiece.getItems().addAll(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void unitePieceOnaction(ActionEvent event) {
    }

    @FXML
    private void btnAddUnitOnAction(ActionEvent event) {
        dialog = new TextInputDialog();

        dialog.setTitle("Ajout");
        dialog.setHeaderText("Ajout d'une nouvelle Unité, \nl'unité doit être en majuscule");
        dialog.setContentText("Unité:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            con = dbConnection.geConnection();
            String unitemaj = name.toUpperCase().trim();
            try {
                pst = con.prepareStatement("insert into " + db + ".unite values(?,?)");
                pst.setString(1, null);
                pst.setString(2, unitemaj);
                pst.executeUpdate();
                pst.close();
                con.close();

            } catch (SQLException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText("Erreur");
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succes");
            a.setHeaderText("Reussie");
            a.setContentText("Unité ajouté avec succes");
            a.showAndWait();
        });
    }

    @FXML
    private void uniteMiniPieceOnaction(ActionEvent event) {
    }

    @FXML
    private void cmbunitePiececliked1(MouseEvent event) {
        uniteMiniPiece.getSelectionModel().clearSelection();
        uniteMiniPiece.getItems().clear();
        uniteMiniPiece.getSelectionModel().clearSelection();
        uniteMiniPiece.getItems().clear();
        uniteMiniPiece.getItems().removeAll();
        try {
            pst = con.prepareStatement("select * from " + db + ".unite");
            rs = pst.executeQuery();
            while (rs.next()) {
                uniteMiniPiece.getItems().addAll(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
