/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.stock;

import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.ConvertierMontantEnLettre;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.ListProduct;
import gettersSetters.ListProduitsFinies;
import gettersSetters.ProduitFinishGetway;
import gettersSetters.ProduitsFinies;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class CurrentStoreEndController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    public AnchorPane apCombobox;
    @FXML
    public JFXRadioButton ml;
    @FXML
    private ToggleGroup btncategorieGroup;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    @FXML
    public Button btnAddNew;
    @FXML
    private Button btnUpdate;
    private Button btnDelete;
    private TableView<ListProduct> tblViewCurrentStore;
    private MenuItem miSellSelected;
    private TableColumn<Object, Object> tblClmProductId;
    private TableColumn<Object, Object> tblClmProductName;
    private TableColumn<Object, Object> tblClmProductquantity;
    private TableColumn<Object, Object> tblClmProductCatagory;
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    private TableColumn<Object, Object> tblClmProductdate;
    private TableColumn<Object, Object> tblClmProductAddBy;
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
    private Button btnAddNew1;
    @FXML
    private JFXDatePicker dpDate;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView<ListProduitsFinies> tableview;
    @FXML
    private TableColumn<Object, Object> clmproduits;
    @FXML
    private TextField tfreport;
    @FXML
    private TextField tfnbunite;
    @FXML
    private TextField tfproduction;
    @FXML
    private TextField tfamical;
    @FXML
    private TextField tfstock;
    @FXML
    private TextField tfingredients;
    @FXML
    private Label lblproduits;
    @FXML
    private TextField tFUnite;
    @FXML
    private JFXDatePicker dpdatejour;
    @FXML
    private TextField sortie;
    @FXML
    private Button btnmodifproduitjour;
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }
    private String id;
    ProduitsFinies users=new ProduitsFinies();
    ProduitFinishGetway usersGetway=new ProduitFinishGetway();
    CurrentProduct currentProduct = new CurrentProduct();
    bddConnection dbCon = new bddConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    ConvertierMontantEnLettre test;
    String alertseuil;
    String idproduitAlert;
    ListProduct lstpr;
    bddConnection dbConnection = new bddConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        //System.out.println("date: "+dpDate.getValue().toString());
        tfnbunite.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfnbunite.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
//        tFUnite.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
//                    tFUnite.setText(oldValue);
//                }
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
        
//        BooleanBinding binding=tfnbunite.textProperty().isEmpty();
//        tFUnite.editableProperty().bind(binding);
    }    

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
         productCurrent.id = tfSearch.getText();
        productCurrent.nomProduit = tfSearch.getText();

        if (ml.isSelected() == true) {
            currentProductGetway.searchViewFinishProduct(productCurrent, "dml");
        } else if (gl.isSelected() == true) {
            currentProductGetway.searchViewFinishProduct(productCurrent, "dgl");
        } else if (pt.isSelected() == true) {
            currentProductGetway.searchViewFinishProduct(productCurrent, "dpt");
        } else if (pz.isSelected() == true) {
            currentProductGetway.searchViewFinishProduct(productCurrent, "dpz");
        } else if (bl.isSelected() == true) {
            currentProductGetway.searchViewFinishProduct(productCurrent, "dbl");
        } else {
            System.out.println("Aucune dépot n'est selectionné ");
        }
    }

    @FXML
    public void btnRefreshOnACtion(ActionEvent event) {
        if (ml.isSelected() == true) {
                buildData("dml");
            } else if (gl.isSelected() == true) {
                buildData("dgl");
            } else if (pt.isSelected() == true) {
                buildData("dpt");
            } else if (pz.isSelected() == true) {
                buildData("dpz");
            } else if (bl.isSelected() == true) {
                buildData("dbl");
            } else {
                System.out.println("Aucune categorie n'est selectionné sur CurrentStoreEndController");
                //currentProductGetway.viewFistTen(productCurrent);
            }
    }

    @FXML
    private void selectectionCategorie(ActionEvent event) {
    }

    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        
        AddProductController apc = new AddProductController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddProductEnd.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductEndController addProductController = fxmlLoader.getController();

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
            //addProductController.lblHeader.setText("Ajouter Produits");
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
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");

        }
    }

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
                currentProductBLL.deleteFinish(productCurrent, "dml");
            } else if (gl.isSelected() == true) {
                currentProductBLL.deleteFinish(productCurrent, "dgl");
            } else if (pt.isSelected() == true) {
                currentProductBLL.deleteFinish(productCurrent, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductBLL.deleteFinish(productCurrent, "dpz");
            } else if (bl.isSelected() == true) {
                currentProductBLL.deleteFinish(productCurrent, "dbl");
            } else {
                System.out.println("Aucune dépot n'est selectionné ");
            }

            btnRefreshOnACtion(event);
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
private boolean isNotNull() {
        boolean insNotNull = true;
        if (tfreport.getText().isEmpty()
                || tfnbunite.getText().isEmpty()
                || tFUnite.getText().isEmpty()
                || tfproduction.getText().isEmpty()
                || tfnbunite.getText().isEmpty()
                || tfamical.getText().isEmpty()
                || tfstock.getText().isEmpty()
                || sortie.getText().isEmpty()
                || dpdatejour.getValue()==null
                ) {

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
    @FXML
    private void btnAddNew1OnAction(ActionEvent event) {
        if(isNotNull()){
            currentProduct.nomProduit = lblproduits.getText().trim();
                    currentProduct.report = tfreport.getText().trim();
                    currentProduct.nombreunite = tfnbunite.getText().trim();
                    currentProduct.uniteproduit = tFUnite.getText().trim();
                    currentProduct.production = tfproduction.getText().trim();
                    currentProduct.amical = tfamical.getText().trim();
                    currentProduct.stock = tfstock.getText().trim();
                    currentProduct.date =dpdatejour.getValue().toString();

                    if (ml.isSelected() == true) {
                        currentProductBLL.saveToday(currentProduct, "dml");
                    } else if (gl.isSelected() == true) {
                        currentProductBLL.saveToday(currentProduct, "dgl");
                    } else if (pt.isSelected() == true) {
                        currentProductBLL.saveToday(currentProduct, "dpt");
                    } else if (pz.isSelected() == true) {
                        currentProductBLL.saveToday(currentProduct, "dpz");
                    } else if (bl.isSelected() == true) {
                        currentProductBLL.saveToday(currentProduct, "dbl");
                    } else {
                        System.out.println("Aucune categorie n'est selectionné");
                        //currentProductGetway.viewFistTen(productCurrent);
                    }
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText("succès: Sauvegarde reussi");
        alert.setContentText("Le produit du jour a été ajouté avec succès");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        clearAll();
        }
        

    }
    private void viewSelected() {
        AddProductController apc = new AddProductController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/app/app/stock/AddProductEnd.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductEndController addProductController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addProductController.setNameMedia(userMedia);
            addProductController.lblHeader.setText("DETAILS DES PRODUITS");
            addProductController.btnUpdate.setVisible(true);
            addProductController.btnSave.setVisible(false);
            addProductController.id = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            addProductController.nomproduit = tblViewCurrentStore.getSelectionModel().getSelectedItem().getNomProduit();

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
            System.err.println(e);
        }
    }
    
    
    public void buildData(String sigle){
        String date;
        if(dpDate.getValue()!=null){
            date=dpDate.getValue().toString();
            
        }else{
            date="1111-11-11";
        }
        users.prLists.clear();
        tableview.setItems(users.prLists);
        clmproduits.setCellValueFactory(new PropertyValueFactory<>("produit"));
        usersGetway.view(users,sigle,date);
        
      }

    @FXML
    private void tableviewOnClick(MouseEvent event) {
        if (ml.isSelected() == true) {
                setselectedview("dml");
            } else if (gl.isSelected() == true) {
                 setselectedview("dgl");
            } else if (pt.isSelected() == true) {
                 setselectedview("dpt");
            } else if (pz.isSelected() == true) {
                 setselectedview("dpz");
            } else if (bl.isSelected() == true) {
                 setselectedview("dbl");
            } else {
                System.out.println("Aucune categorie n'est selectionné");
                
            }
    }    
    
    public void setselectedview(String sigle){
        
        clearAll();
        ListProduitsFinies lst=tableview.getSelectionModel().getSelectedItem();
        
        if(lst!=null){
            users.produit=lst.getProduit();
            usersGetway.selectedview(users, sigle);
             //id = users.id;
            tfreport.setText(users.report);
            tfnbunite.setText(users.nombreunite);
            tfproduction.setText(users.nombreunite);
            tfamical.setText(users.amicale);
            tfstock.setText(users.stock);
            tFUnite.setText(users.unite);
            lblproduits.setText(users.produit);                       
        }        

    }
    private void clearAll() {
        tfreport.clear();
        tfnbunite.clear();
        tfproduction.clear();
        tfamical.clear();
        tfstock.clear();
        tfingredients.clear();
        tFUnite.clear();
        lblproduits.setText(null);
    }

    @FXML
    private void dpDateOnAction(ActionEvent event) {
        System.out.println("date: "+dpDate.getValue().toString());
        if (ml.isSelected() == true) {
                buildData("dml");
            } else if (gl.isSelected() == true) {
                buildData("dgl");
            } else if (pt.isSelected() == true) {
                buildData("dpt");
            } else if (pz.isSelected() == true) {
                buildData("dpz");
            } else if (bl.isSelected() == true) {
                buildData("dbl");
            } else {
                System.out.println("Aucune categorie n'est selectionné sur CurrentStoreEndController");
                //currentProductGetway.viewFistTen(productCurrent);
            }
    }

    @FXML
    private void unitetyped(KeyEvent event) {
        Double npu=Double.valueOf(tfnbunite.getText());
        Double nu= Double.valueOf(tfproduction.getText());
        Double resultat=nu/npu;
        tFUnite.setText(String.valueOf(resultat));
    }

    @FXML
    private void unitetypedStock(KeyEvent event) {
        double report=Double.valueOf(tfreport.getText());
        double production=Double.valueOf(tfproduction.getText());
        double amical=Double.valueOf(tfamical.getText());
        double srt=Double.valueOf(sortie.getText());
        double resultats=(report+production)-(amical+srt);
        tfstock.setText(String.valueOf(resultats));
    }

    @FXML
    private void btnMfdPJourOnAction(ActionEvent event) {
        
        if(isNotNull()){
            currentProduct.nomProduit = lblproduits.getText().trim();
                    currentProduct.report = tfreport.getText().trim();
                    currentProduct.nombreunite = tfnbunite.getText().trim();
                    currentProduct.uniteproduit = tFUnite.getText().trim();
                    currentProduct.production = tfproduction.getText().trim();
                    currentProduct.amical = tfamical.getText().trim();
                    currentProduct.stock = tfstock.getText().trim();
                    currentProduct.date =dpdatejour.getValue().toString();

                    if (ml.isSelected() == true) {
                        currentProductBLL.updateToday(currentProduct, "dml");
                    } else if (gl.isSelected() == true) {
                        currentProductBLL.updateToday(currentProduct, "dgl");
                    } else if (pt.isSelected() == true) {
                        currentProductBLL.updateToday(currentProduct, "dpt");
                    } else if (pz.isSelected() == true) {
                        currentProductBLL.updateToday(currentProduct, "dpz");
                    } else if (bl.isSelected() == true) {
                        currentProductBLL.updateToday(currentProduct, "dbl");
                    } else {
                        System.out.println("Aucune categorie n'est selectionné");
                        //currentProductGetway.viewFistTen(productCurrent);
                    }
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText("succès: Mise à jour reussi");
        alert.setContentText("Le produit du jour a été modifié avec succès");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        clearAll();
        }
    }
}
