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
import gettersSetters.ConvertierMontantEnLettre;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.ListProduct;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class CurrentStoreController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    public AnchorPane apCombobox;
    private JFXRadioButton produitsPeintures;
    @FXML
    private ToggleGroup btncategorieGroup;
//    private JFXRadioButton produitsTous;
//    private JFXRadioButton produitsToles;
//    private JFXRadioButton ProduitsAutres;
//    private JFXRadioButton stockMin;
    @FXML
    private ComboBox<String> cbSoteViewSupplyer;
    @FXML
    private ComboBox<String> cbSoteViewBrands;
    @FXML
    private ComboBox<String> cbSoteViewCatagory;
    @FXML
    public Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListProduct> tblViewCurrentStore;
    @FXML
    private MenuItem miSellSelected;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplyer;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCatagory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductdate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductdescription;
    CurrentProduct productCurrent = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();

    private String usrId;

    private userNameMedia media;
    String suplyerId;
    String suplyerName;
    String brandId;
    String brandName;
    String catagoryId;
    String catagoryName;
    String rmaID;
    String rmaName;
    SQLSyntax sql = new SQLSyntax();
    @FXML
    public JFXRadioButton ml;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantityUnity;
    @FXML
    private Button btnReports;
    Window owner;
    @FXML
    private TableColumn<Object, Object> tblClmcat;
    @FXML
    private TableColumn<Object, Object> colqttpp;
    @FXML
    private TableColumn<Object, Object> colunitepp;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    ConvertierMontantEnLettre test;
    String alertseuil;
    String idproduitAlert;
    ListProduct lstpr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchAll();
    }

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        productCurrent.idProduit = tfSearch.getText();
        productCurrent.nomProduit = tfSearch.getText();

        if (ml.isSelected() == true) {
            currentProductGetway.searchView(productCurrent, "dml");
        } else if (gl.isSelected() == true) {
            currentProductGetway.searchView(productCurrent, "dgl");
        } else if (pt.isSelected() == true) {
            currentProductGetway.searchView(productCurrent, "dpt");
        } else if (pz.isSelected() == true) {
            currentProductGetway.searchView(productCurrent, "dpz");
        } else if (bl.isSelected() == true) {
            currentProductGetway.searchView(productCurrent, "dbl");
        } else {
            System.out.println("Aucune dépot n'est selectionné ");
        }
    }

    public void searchAll() {
        productCurrent.currentProductList.clear();
        tfSearch.clear();
        cbSoteViewSupplyer.getItems().clear();
        cbSoteViewBrands.getItems().clear();
        cbSoteViewCatagory.getItems().clear();
        cbSoteViewSupplyer.setPromptText("Fournisseur");
        cbSoteViewBrands.setPromptText("Marque");
        cbSoteViewCatagory.setPromptText("Categorie");

        tblViewCurrentStore.setItems(productCurrent.currentProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("IdProduit"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        tblClmProductquantityUnity.setCellValueFactory(new PropertyValueFactory<>("unite"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory<>("fournisseurID"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("marqueID"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("categorieID"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        tblClmProductdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colunitepp.setCellValueFactory(new PropertyValueFactory<>("unitepp"));
        colqttpp.setCellValueFactory(new PropertyValueFactory<>("nombrepp"));

        if (ml.isSelected() == true) {
            currentProductGetway.view(productCurrent, "dml");
        } else if (gl.isSelected() == true) {
            currentProductGetway.view(productCurrent, "dgl");
        } else if (pt.isSelected() == true) {
            currentProductGetway.view(productCurrent, "dpt");
        } else if (pz.isSelected() == true) {
            currentProductGetway.view(productCurrent, "dpz");
        } else if (bl.isSelected() == true) {
            currentProductGetway.view(productCurrent, "dbl");
        } else {
            System.out.println("Aucune dépot n'est selectionné ");
        }
    }

    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        searchAll();
    }

    @FXML
    private void selectectionCategorie(ActionEvent event) {
        viewDetails();
    }

    @FXML
    private void cbSoteViewSupplyerOnClick(MouseEvent event) {
        con = dbCon.geConnection();
        cbSoteViewSupplyer.getItems().clear();
        cbSoteViewBrands.setPromptText("Selectionner la marque");
        cbSoteViewCatagory.setPromptText("Selectionner le categorie");
        try {
            pst = con.prepareStatement("select * from " + db + ".fournisseur");
            rs = pst.executeQuery();
            while (rs.next()) {
                cbSoteViewSupplyer.getItems().remove(rs.getString(2));
                cbSoteViewSupplyer.getItems().add(rs.getString(2));
            }
            rs.close();
            con.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbSoteViewSupplyerOnAction(ActionEvent event) {
        if (cbSoteViewSupplyer.getSelectionModel().getSelectedItem() == null) {

        } else {
            suplyerName = cbSoteViewSupplyer.getSelectionModel().getSelectedItem();
            productCurrent.fournisseurNom = suplyerName;

            if (ml.isSelected() == true) {
                currentProductGetway.searchBySupplyer(productCurrent, "dml");
            } else if (gl.isSelected() == true) {
                currentProductGetway.searchBySupplyer(productCurrent, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductGetway.searchBySupplyer(productCurrent, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductGetway.searchBySupplyer(productCurrent, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductGetway.searchBySupplyer(productCurrent, "dbl");
            } else {
                System.out.println("Aucune dépot n'est selectionné ");
            }
        }
    }

    @FXML
    private void cbSoteViewBrandOnClick(MouseEvent event) {

        con = dbCon.geConnection();
        cbSoteViewBrands.getItems().clear();
        suplyerName = cbSoteViewSupplyer.getSelectionModel().getSelectedItem();
        suplyerId = sql.getIdNo(suplyerName, suplyerId, "fournisseur", "NomFournisseur");

        try {
            pst = con.prepareStatement("select * from " + db + ".marque where FournisseurID=?");
            pst.setString(1, suplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbSoteViewBrands.getItems().add(rs.getString(2));
            }
            rs.close();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbSoteViewBrandOnAction(ActionEvent event) {
        if (cbSoteViewBrands.getSelectionModel().getSelectedItem() == null) {

        } else {
            brandName = cbSoteViewBrands.getSelectionModel().getSelectedItem();
            suplyerName = cbSoteViewSupplyer.getPromptText();
            productCurrent.fournisseurNom = suplyerName;
            productCurrent.marqueNom = brandName;

            if (ml.isSelected() == true) {
                currentProductGetway.searchByBrand(productCurrent, "dml");
            } else if (gl.isSelected() == true) {
                currentProductGetway.searchByBrand(productCurrent, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductGetway.searchByBrand(productCurrent, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductGetway.searchByBrand(productCurrent, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductGetway.searchByBrand(productCurrent, "dbl");
            } else {
                System.out.println("Aucune dépot n'est selectionné ");
            }

        }
    }

    @FXML
    private void cbSoteViewCatagoryOnClick(MouseEvent event) {

        con = dbCon.geConnection();
        cbSoteViewCatagory.getItems().clear();
        suplyerName = cbSoteViewSupplyer.getSelectionModel().getSelectedItem();
        suplyerId = sql.getIdNo(suplyerName, suplyerId, "fournisseur", "NomFournisseur");
        brandId = sql.getBrandID(suplyerId, brandId, brandName);
        try {
            pst = con.prepareStatement("select * from " + db + ".categorie where FournisseurId=? and MarqueId=?");
            pst.setString(1, suplyerId);
            pst.setString(2, brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbSoteViewCatagory.getItems().add(rs.getString(2));
            }
            rs.close();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbSoteViewCatagoryOnAction(ActionEvent event) {
        if (cbSoteViewCatagory.getSelectionModel().getSelectedItem() == null) {

        } else {
            brandName = cbSoteViewBrands.getSelectionModel().getSelectedItem();
            suplyerName = cbSoteViewSupplyer.getPromptText();
            catagoryName = cbSoteViewCatagory.getSelectionModel().getSelectedItem();
            productCurrent.fournisseurNom = suplyerName;
            productCurrent.marqueNom = brandName;
            productCurrent.categorieNom = catagoryName;

            if (ml.isSelected() == true) {
                currentProductGetway.searchByCatagory(productCurrent, "dml");
            } else if (gl.isSelected() == true) {
                currentProductGetway.searchByCatagory(productCurrent, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductGetway.searchByCatagory(productCurrent, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductGetway.searchByCatagory(productCurrent, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductGetway.searchByCatagory(productCurrent, "dbl");
            } else {
                System.out.println("Aucune dépot n'est selectionné ");
            }
        }
    }

    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddProductController apc = new AddProductController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddProduct.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductController addProductController = fxmlLoader.getController();

            if (ml.isSelected() == true) {
                addProductController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                addProductController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                addProductController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                addProductController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                addProductController.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné sur btnAddNewOnAction() ");
            }

            media.setId(usrId);
            addProductController.setNameMedia(media);
            addProductController.lblHeader.setText("Ajouter Produit");
            addProductController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            //          addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");

        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous supprimer l'élement selectionné? \n cliquer sur OK pour confirmer");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            System.out.println("Product id" + item);
            productCurrent.id = item;

            if (ml.isSelected() == true) {
                currentProductBLL.delete(productCurrent, "dml");
            } else if (gl.isSelected() == true) {
                currentProductBLL.delete(productCurrent, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductBLL.delete(productCurrent, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductBLL.delete(productCurrent, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductBLL.delete(productCurrent, "dbl");
            } else {
                System.out.println("Aucune dépot n'est selectionné ");
            }

            btnRefreshOnACtion(event);
        }
    }

    @FXML
    private void miSellSelectedOnAction(ActionEvent event) {
    }

    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblViewCurrentStore.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblViewCurrentStore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }

    @FXML
    private void keyreleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
        } // fleche bas
        else if (event.getCode().equals(KeyCode.DOWN)) {
            String iyyy = tblViewCurrentStore.getSelectionModel().getSelectedItem().getIdProduit();
            System.out.println(iyyy);
        }
    }

    private void viewSelected() {
        AddProductController apc = new AddProductController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddProduct.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductController addProductController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addProductController.setNameMedia(userMedia);
            addProductController.lblHeader.setText("DETAILS DES PRODUITS");
            addProductController.btnUpdate.setVisible(true);
            addProductController.btnSave.setVisible(false);
            addProductController.id = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();

            if (ml.isSelected() == true) {
                addProductController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                addProductController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                addProductController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                addProductController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                addProductController.bl.setSelected(true);
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                //currentProductGetway.viewFistTen(productCurrent);
            }

            addProductController.viewSelected();
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void settingPermission(String tabledroit, String id) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + "." + tabledroit + " where " + id + "=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(8) == 0) {
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                }
                if (rs.getInt(3) == 0) {
                    btnAddNew.setDisable(true);
                }
                if (rs.getInt("SellProduct") == 0) {
                    miSellSelected.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            // Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewDetails() {
        //viewFistTenOrderByCategorie

        System.out.println("CLCKED");
        tblViewCurrentStore.setItems(productCurrent.currentProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("IdProduit"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        tblClmProductquantityUnity.setCellValueFactory(new PropertyValueFactory<>("unite"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory<>("fournisseurID"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("marqueID"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("categorieID"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        tblClmProductdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colunitepp.setCellValueFactory(new PropertyValueFactory<>("unitepp"));
        colqttpp.setCellValueFactory(new PropertyValueFactory<>("nombrepp"));

        if (ml.isSelected() == true) {
            System.out.println("Categorie militaire selectionné");
            currentProductGetway.viewFistTen(productCurrent, "dml_produits", "dml");
        } else if (gl.isSelected() == true) {
            System.out.println("Categorie glace selectionné");
            currentProductGetway.viewFistTen(productCurrent, "dgl_produits", "dgl");
        } else if (pt.isSelected() == true) {
            System.out.println("Categorie patisserie selectionné");
            currentProductGetway.viewFistTen(productCurrent, "dpt_produits", "dpt");
        } else if (pz.isSelected() == true) {
            System.out.println("Categorie pizza selectionné");
            currentProductGetway.viewFistTen(productCurrent, "dpz_produits", "dpz");
        } else if (bl.isSelected() == true) {
            System.out.println("Categorie boulangerie selectionné");
            currentProductGetway.viewFistTen(productCurrent, "dbl_produits", "dbl");
        } else {
            System.out.println("Aucune categorie n'est selectionné");
            //currentProductGetway.viewFistTen(productCurrent);
        }

    }

    @FXML
    private void btnReportsOnAction(ActionEvent event) throws SQLException {
        try {
            sortieRapportJounalier();
        } catch (JRException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Vide");
            a.setHeaderText("La date selectionné ne contient aucune vente");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }

    }

    public void sortieRapportJounalier() throws SQLException, JRException {

        try {
            InputStream is = getClass().getResourceAsStream("/reports/sortie.jrxml");
            JasperDesign jd = JRXmlLoader.load(is);
            JasperReport report = JasperCompileManager.compileReport(jd);
            JasperPrint print = JasperFillManager.fillReport(report, null, con);
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur:");
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
    }
}
