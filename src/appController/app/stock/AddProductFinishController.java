/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.stock;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductGetway;
import gettersSetters.CustomerGetway;
import gettersSetters.EffectUtility;
import gettersSetters.ListProduct;
import gettersSetters.Supplyer;
import gettersSetters.SupplyerGetway;
import gettersSetters.userNameMedia;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AddProductFinishController implements Initializable {

    @FXML
    private AnchorPane apContent;
    @FXML
    private Button btnSave;
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
    private TextField produitsRestants;
    @FXML
    private TextField produitsAJouter;
    @FXML
    private JFXDatePicker date;
    @FXML
    private Label lblCaption;
    @FXML
    private Button btnClose;
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

    private String usrId;
    CurrentProduct prod = new CurrentProduct();
    CustomerGetway customerGetway = new CustomerGetway();
    CurrentProductGetway produitgetway = new CurrentProductGetway();
    CurrentProduct currrentProduct = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    public String supplyerId;
    private Stage primaryStage;
    private userNameMedia media;
    @FXML
    private TextField tfProductId;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }
    userNameMedia nameMedia;
    String userId;
    String customerId;
    String produitid;

    Supplyer oSupplier = new Supplyer();
    SupplyerGetway supplyerGetway = new SupplyerGetway();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date.setValue(LocalDate.now());
        produitsAJouter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    produitsAJouter.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (isNotNull()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez vous conserver les produits restants ?");
            alert.setContentText("Cliquer sur Oui pour sonserver, Non pour les jeter");

            ButtonType yesButton = new ButtonType("Oui");
            ButtonType noButton = new ButtonType("Non");
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                int restant = Integer.parseInt(produitsRestants.getText());
                int ajout = Integer.parseInt(produitsAJouter.getText());
                int quantiteajout = restant + ajout;
                String qtt = String.valueOf(quantiteajout);
                if (ml.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dml");
                } else if (gl.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dgl");
                } else if (pt.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dpt");
                } else if (pz.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dpz");
                } else if (bl.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dbl");
                } else {
                    System.out.println("Aucune dépot n'est selectionné ");
                }
            } else if (result.get() == noButton) {
                String qtt = produitsAJouter.getText().trim();
                if (ml.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dml");
                } else if (gl.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dgl");
                } else if (pt.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dpt");
                } else if (pz.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dpz");
                } else if (bl.isSelected() == true) {
                    produitgetway.ajoutproduit(tfProductId.getText().trim(), qtt, date.getValue().toString(), "dbl");
                } else {
                    System.out.println("Aucune dépot n'est selectionné ");
                }
            }
            clearAll();
        }
    }

    @FXML
    private void tfProduitrecherche(KeyEvent event) {
        tfProductId.setText(tblProduit.getSelectionModel().getSelectedItem().getId());
        produitid = tblProduit.getSelectionModel().getSelectedItem().getId();
        System.out.println(produitid);
    }

    @FXML
    private void tblProduitOnClick(MouseEvent event) {
        tfProductId.setText(tblProduit.getSelectionModel().getSelectedItem().getId());
        produitid = tblProduit.getSelectionModel().getSelectedItem().getId();
        System.out.println(produitid);
        ListProduct lst = tblProduit.getSelectionModel().getSelectedItem();
        String items = lst.getId();
        //int itemsInt = Integer.parseInt(items);
        tfProductId.setText(items);
        completefeild();
        mbtnselectProduit.hide();
    }

    @FXML
    private void mbtnProduitOnactionselect(MouseEvent event) {
        prod.nomProduit = tfProduitSearch.getText().trim();
        tblProduit.setItems(prod.currentProductList);
        tblClmProduitID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblClmProduitNom.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        if (ml.isSelected() == true) {
            produitgetway.searchViewFinishProduct(prod, "dml");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (gl.isSelected() == true) {
            produitgetway.searchViewFinishProduct(prod, "dgl");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (pt.isSelected() == true) {
            produitgetway.searchViewFinishProduct(prod, "dpt");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (pz.isSelected() == true) {
            produitgetway.searchViewFinishProduct(prod, "dpz");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else if (bl.isSelected() == true) {
            produitgetway.searchViewFinishProduct(prod, "dbl");
            System.out.println("pour" + prod.id + " adnd " + prod.currentProductList);
        } else {
            System.out.println("Aucune dépot n'est selectionné ");
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void apOnMouseDragged(MouseEvent event) {
    }

    @FXML
    private void apOnMousePressed(MouseEvent event) {
    }

    public void addSupplyerStage(Stage stage) {
        EffectUtility.makeDraggable(stage, apContent);
    }

    @FXML
    private void tfProductIdOnAction(ActionEvent event) {
        try {
            completefeild();
        } catch (Exception e) {
            System.out.println(e);
            System.err.println(e);
        }

    }

    public void completefeild() {
        if (tfProductId.getText().isEmpty()) {
            clearAll();
        } else {
            currrentProduct.id = tfProductId.getText().trim();
            if (ml.isSelected() == true) {
                currentProductGetway.sViewFinish(currrentProduct, "dml");
            } else if (pt.isSelected() == true) {
                currentProductGetway.sViewFinish(currrentProduct, "dpt");
            } else if (pz.isSelected() == true) {
                currentProductGetway.sViewFinish(currrentProduct, "dpz");
            } else if (gl.isSelected() == true) {
                currentProductGetway.sViewFinish(currrentProduct, "dgl");
            } else if (bl.isSelected() == true) {
                currentProductGetway.sViewFinish(currrentProduct, "dbl");
            } else {
                System.out.println("Aucune dépot selectioné");
            }
            produitsRestants.setText(currrentProduct.quantite);
        }
    }

    public void clearAll() {
        produitsRestants.setText(null);
        produitsAJouter.setText(null);
        date.setValue(LocalDate.now());
        tfProductId.setText(null);
    }

    public boolean isNotNull() {
        if (produitsRestants.getText().isEmpty() && produitsAJouter.getText().isEmpty() && tfProductId.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERREUR");
            a.setHeaderText("Champs obligatoires");
            a.setContentText("Certains champs sont vides, veuillez remplir les champs");
            a.showAndWait();
            return false;
        }
        return true;
    }
}
