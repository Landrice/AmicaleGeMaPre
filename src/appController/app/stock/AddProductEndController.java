/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.stock;

import amicalegemapre.AmicaleGeMaPre;
import bdd.BddPropreties;
import bdd.SQLSyntax;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import gettersSetters.Convertion;
import gettersSetters.CurrentProduct;
import gettersSetters.CurrentProductBLL;
import gettersSetters.CurrentProductGetway;
import gettersSetters.IngredientsGetWay;
import gettersSetters.IngredientsPFinish;
import gettersSetters.ListIngredients;
import gettersSetters.userNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class AddProductEndController implements Initializable {

    @FXML
    public Label lblHeader;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductQuantity;
    private TextField tfProductPursesPrice;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    private JFXDatePicker dpDate;
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
    private TextField tfcategorie;
    private String usrId;
    private userNameMedia nameMedia;
    Convertion convert;
    CurrentProduct currentProduct = new CurrentProduct();
    IngredientsPFinish ingredients = new IngredientsPFinish();
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
    public String nomproduit;
    private String supplyerName;
    private String supplyerId;
    private String brandName;
    private String brandId;
    private String catagoryName;
    private String catagoryId;
    private String unitId;
    private String rmaId;
    private JFXComboBox<String> ingrefients;
    @FXML
    private TableView<ListIngredients> tableView;
    @FXML
    private TableColumn<Object, Object> idColumn;
    @FXML
    private TableColumn<Object, Object> ingreColumn;
    @FXML
    private JFXTextField nombreajout;
    @FXML
    private Label lblunite;
    @FXML
    private JFXButton btndeleteingredients;
    @FXML
    private TableColumn<Object, Object> qttColumn;
    @FXML
    private TextField tfIngredientsSearch;
    @FXML
    private TableView<ListIngredients> tblingredientsChoix;
    @FXML
    private TableColumn<Object, Object> clId;
    @FXML
    private TableColumn<Object, Object> clIngredients;
    @FXML
    private MenuButton cmbIngredients;
    String produitid;

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    ObservableList<ListIngredients> preList = FXCollections.observableArrayList();
    IngredientsPFinish currentingredients = new IngredientsPFinish();
    IngredientsGetWay ing = new IngredientsGetWay();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfProductQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    tfProductQuantity.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        nombreajout.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,99}([\\.]\\d{0,4})?")) {
                    nombreajout.setText(oldValue);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (isNotNull()) {

            if (!tableView.getItems().isEmpty()) {
                int indexs = tableView.getItems().size();
                for (int i = 0; i < indexs; i++) {
                    tableView.getSelectionModel().select(i);
                    System.out.println("id is "+tableView.getSelectionModel().getSelectedItem().getId());
                    System.out.println("quantite is "+tableView.getSelectionModel().getSelectedItem().getQuantite());
                    currentProduct.nomProduit = tfProductName.getText().trim();
                    currentProduct.quantite = tfProductQuantity.getText().trim();
                    currentProduct.ingredientsid = tableView.getSelectionModel().getSelectedItem().getId();
                    currentProduct.ingredientsquantite = tableView.getSelectionModel().getSelectedItem().getQuantite();
                    currentProduct.date="1111-11-11";

                    if (ml.isSelected() == true) {
                        currentProductBLL.saveFinish(currentProduct, "dml");
                    } else if (gl.isSelected() == true) {
                        currentProductBLL.saveFinish(currentProduct, "dgl");
                    } else if (pt.isSelected() == true) {
                        currentProductBLL.saveFinish(currentProduct, "dpt");
                    } else if (pz.isSelected() == true) {
                        currentProductBLL.saveFinish(currentProduct, "dpz");
                    } else if (bl.isSelected() == true) {
                        currentProductBLL.saveFinish(currentProduct, "dbl");
                    } else {
                        System.out.println("Aucune categorie n'est selectionné");
                        //currentProductGetway.viewFistTen(productCurrent);
                    }
                }
            }
            clear();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText("succès: Sauvegarde reussi");
        alert.setContentText("Le produit a été ajouté avec succès");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        clearAll();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        btnSave.setDisable(true);
        if (isNotNull()) {

            if (!tableView.getItems().isEmpty()) {
                int indexs = tableView.getItems().size();
                for (int i = 0; i < indexs; i++) {
                    tableView.getSelectionModel().select(i);
                    System.out.println("id is "+tableView.getSelectionModel().getSelectedItem().getId());
                    System.out.println("quantite is "+tableView.getSelectionModel().getSelectedItem().getQuantite());
                    currentProduct.nomProduit = tfProductName.getText().trim();
                    currentProduct.quantite = tfProductQuantity.getText().trim();
                    currentProduct.ingredientsid = tableView.getSelectionModel().getSelectedItem().getId();
                    currentProduct.ingredientsquantite = tableView.getSelectionModel().getSelectedItem().getQuantite();
                    currentProduct.date="1111-11-11";

                    if (ml.isSelected() == true) {
                        currentProductBLL.updatepr(currentProduct, "dml");
                    } else if (gl.isSelected() == true) {
                        currentProductBLL.updatepr(currentProduct, "dgl");
                    } else if (pt.isSelected() == true) {
                        currentProductBLL.updatepr(currentProduct, "dpt");
                    } else if (pz.isSelected() == true) {
                        currentProductBLL.updatepr(currentProduct, "dpz");
                    } else if (bl.isSelected() == true) {
                        currentProductBLL.updatepr(currentProduct, "dbl");
                    } else {
                        System.out.println("Aucune categorie n'est selectionné");
                        //currentProductGetway.viewFistTen(productCurrent);
                    }
                }
            }
            clear();
        }

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private boolean isNotNull() {
        boolean insNotNull = true;
        if (tfProductName.getText().isEmpty()
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

    public void refreshProductList() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/app/app/stock/CurrentStoreEnd.fxml").openStream());
            CurrentStoreEndController currentStoreController = fXMLLoader.getController();
            if (ml.isSelected() == true) {
                currentStoreController.ml.setSelected(true);
            } else if (gl.isSelected() == true) {
                currentStoreController.gl.setSelected(true);
            } else if (pt.isSelected() == true) {
                currentStoreController.pt.setSelected(true);
            } else if (pz.isSelected() == true) {
                currentStoreController.pz.setSelected(true);
            } else if (bl.isSelected() == true) {
                currentStoreController.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné se");
            }
            // currentStoreController.viewDetails();
            //currentStoreController.searchAll();

        } catch (IOException ex) {
            Logger.getLogger(AddProductEndController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearAll() {
        tfProductName.clear();
        tfProductQuantity.clear();
    }

    public void viewSelected() {
        currentProduct.id = id;
        currentProduct.nomProduit = nomproduit;

        if (ml.isSelected() == true) {
            currentProductGetway.selectedViewEnd(currentProduct, "dml");
        } else if (gl.isSelected() == true) {
            currentProductGetway.selectedViewEnd(currentProduct, "dgl");
        } else if (pt.isSelected() == true) {
            currentProductGetway.selectedViewEnd(currentProduct, "dpt");
        } else if (pz.isSelected() == true) {
            currentProductGetway.selectedViewEnd(currentProduct, "dpz");
        } else if (bl.isSelected() == true) {
            currentProductGetway.selectedViewEnd(currentProduct, "dbl");
        } else {
            System.out.println("Aucune categorie n'est selectionné");
            //currentProductGetway.viewFistTen(productCurrent);
        }

        tfProductName.setText(currentProduct.nomProduit);
        tfProductQuantity.setText(currentProduct.nombreunite);
//        tfProductPursesPrice.setText(currentProduct.prix);
//        dpDate.setValue(LocalDate.parse(currentProduct.date));
//        tfcategorie.setText(currentProduct.categorieNom);
        //supplyerId = currentProduct.fournisseurID;
        // brandId = currentProduct.marqueID;
        //catagoryId = currentProduct.categorieID;

    }

    public void ingredientscmb(String sigle) {
        try {
            pst = con.prepareStatement("select * from " + db + "." + sigle + "_ingredients");
            rs = pst.executeQuery();
            while (rs.next()) {
                ingrefients.getItems().addAll(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AmicaleGeMaPre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tableViewMouseCliked(MouseEvent event) {
    }

    @FXML
    private void btndeleteingredientsOnAction(ActionEvent event) {
        if (tableView.getItems().size() != 0) {
            System.out.println("Clicked");
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());

        } else {
            System.out.println("EMPTY");
        }
    }

    @FXML
    private void tfCustomerSearchOnKeyReleased(KeyEvent event) {
        tfIngredientsSearch.setText(tblingredientsChoix.getSelectionModel().getSelectedItem().getIngredients());
        produitid = tblingredientsChoix.getSelectionModel().getSelectedItem().getId();
        System.out.println(produitid);
    }

    @FXML
    private void cmbIngredientsOnClicked(MouseEvent event) {
        currentingredients.ingredientsList.clear();
        currentingredients.id = tfIngredientsSearch.getText().trim();
        tblingredientsChoix.setItems(currentingredients.ingredientsList);
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));

        if (ml.isSelected() == true) {
            ing.view(currentingredients, "dml");
        } else if (gl.isSelected() == true) {
            ing.view(currentingredients, "dgl");
        } else if (pt.isSelected() == true) {
            ing.view(currentingredients, "dpt");
        } else if (bl.isSelected() == true) {
            ing.view(currentingredients, "dbl");
        } else if (pz.isSelected() == true) {
            ing.view(currentingredients, "dpz");
        } else {
            System.out.println("Aucune dépot selectionné");
        }
        
        
    }

    public boolean inNotNull() {
        boolean isNotNull = false;

        if (nombreajout.getText().trim().isEmpty() || tfProductQuantity.getText().isEmpty() || tfProductName.getText().isEmpty()) {
//            Dialogs.create().title("").masthead("ERROR").message("Please fill requere field").styleClass(org.controlsfx.dialog.Dialog.STYLE_CLASS_UNDECORATED).showError();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("ERREUR");
            alert.setContentText("Veuiller remplir les champs la quantite de l'ingredient");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    @FXML
    private void tblIngredientsOnClick(MouseEvent event) {
        if (nombreajout.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("champ necessaire");
            a.setHeaderText("Une champ est necessaire");
            a.setContentText("Veuillez remplir le nombre en premier\n");
            a.showAndWait();

        } else {
            if (inNotNull()) {
                Double qtts=Double.parseDouble(tfProductQuantity.getText());
                Double qt=Double.parseDouble(nombreajout.getText());
                Double qtsurqtts=qt/qtts;
                
                preList.add(new ListIngredients(tblingredientsChoix.getSelectionModel().getSelectedItem().getId(), tblingredientsChoix.getSelectionModel().getSelectedItem().getIngredients(), String.valueOf(qtsurqtts)));
                tableView.setItems(preList);
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                ingreColumn.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
                qttColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                nombreajout.setText("");
                cmbIngredients.hide();
            }
        }

    }

    public void clear() {
        tfProductName.setText(null);
        tfProductQuantity.setText(null);
        nombreajout.setText(null);
        cmbIngredients.setText("Selectionner l'ingredients");
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
    }

}
