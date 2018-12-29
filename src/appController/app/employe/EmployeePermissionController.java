/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app.employe;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import gettersSetters.userNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class EmployeePermissionController implements Initializable {

    @FXML
    private CheckBox cbStockManegement;
    @FXML
    private CheckBox cbAddProduct;
    @FXML
    private CheckBox cbAddSupplyer;
    @FXML
    private CheckBox cbAddBrand;
    @FXML
    private CheckBox cbAddCatagory;
    @FXML
    private CheckBox cbUpdateProduct;
    @FXML
    private CheckBox cbUpdateSupplyert;
    @FXML
    private CheckBox cbUpdateBrands;
    @FXML
    private CheckBox cbUpdateCatagory;
    @FXML
    private CheckBox cbAddUnit;
    @FXML
    private CheckBox cbEmployeeManegement;
    @FXML
    private CheckBox cbAddEmployee;
    @FXML
    private CheckBox cbUpdateEmployee;
    @FXML
    private CheckBox cbEmployePassChange;
    @FXML
    private CheckBox cbUser;
    @FXML
    private CheckBox cbChangePassword;
    @FXML
    private Button btnUpdate;
    @FXML
    private JFXButton btnClose;

    private String id;

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private userNameMedia media;
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    @FXML
     JFXToggleButton ml;
    @FXML
    private ToggleGroup butongroup;
    @FXML
     JFXToggleButton gl;
    @FXML
     JFXToggleButton pt;
    @FXML
     JFXToggleButton pz;
    @FXML
     JFXToggleButton bl;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        id = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ml.setSelected(true);
    }

    @FXML
    private void cbSotckMangeOnClick(MouseEvent event) {
    }

    @FXML
    private void cbStockOnAction(ActionEvent event) {
        stockManeCbOperation();
    }

    @FXML
    private void cbEmployeeManegementOnAction(ActionEvent event) {
        if (cbEmployeeManegement.isSelected()) {
            cbAddEmployee.setSelected(true);
            cbEmployePassChange.setSelected(true);
            cbUpdateEmployee.setSelected(true);
        } else if (!cbEmployeeManegement.isSelected()) {
            cbAddEmployee.setSelected(true);
            cbEmployePassChange.setSelected(true);
            cbUpdateEmployee.setSelected(true);
        }
    }

    @FXML
    private void cbUserOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {

//        int x = (args.length > 0) ? 1 : 2;
        int addProduct = (cbAddProduct.isSelected()) ? 1 : 0;
        int addSupplyer = (cbAddSupplyer.isSelected()) ? 1 : 0;
        int addBrand = (cbAddBrand.isSelected()) ? 1 : 0;
        int AddCatagory = (cbAddCatagory.isSelected()) ? 1 : 0;
        int AddUnit = (cbAddUnit.isSelected()) ? 1 : 0;
        int UpdateProduct = (cbUpdateProduct.isSelected()) ? 1 : 0;
        int UpdateSupplyer = (cbUpdateSupplyert.isSelected()) ? 1 : 0;
        int UpdateBrand = (cbUpdateBrands.isSelected()) ? 1 : 0;
        int UpdateCatagory = (cbUpdateCatagory.isSelected()) ? 1 : 0;
        int EmployeManage = (cbEmployeeManegement.isSelected()) ? 1 : 0;
        int ChangeOwnPass = (cbChangePassword.isSelected()) ? 1 : 0;

        String requete;
        if (ml.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("UPDATE " + db + ".dml_droitutilisateur SET AjoutProduit_dml=?,AjoutFournisseur_dml=?,AjoutMarque_dml=?,AjoutCategory_dml=?,AjoutQuantite_dml=?,AjoutClient_dml=?,MisJourProduit_dml=?,MisJourFournisseur_dml=?,MisJourMarque_dml=?,MisJourCategory_dml=?,MisJourQuantite_dml=?,MisJourClient_dml=?,VendreProduit_dml=?,GererUtilisateur_dml=? WHERE UtilisateurId_dml=?");
//            pst.setString(1, id);
                pst.setInt(1, addProduct);
                pst.setInt(2, addSupplyer);
                pst.setInt(3, addBrand);
                pst.setInt(4, AddCatagory);
                pst.setInt(5, AddUnit);
                pst.setInt(6, UpdateProduct);
                pst.setInt(7, UpdateSupplyer);
                pst.setInt(8, UpdateBrand);
                pst.setInt(9, UpdateCatagory);
                pst.setInt(10, EmployeManage);
                pst.setInt(11, ChangeOwnPass);
                pst.setString(12, id);

                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess");
                alert.setContentText("Privilèges modifié avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (gl.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("UPDATE " + db + ".dgl_droitutilisateur SET AjoutProduit_dgl=?,AjoutFournisseur_dgl=?,AjoutMarque_dgl=?,AjoutCategory_dgl=?,AjoutQuantite_dgl=?,AjoutClient_dgl=?,MisJourProduit_dgl=?,MisJourFournisseur_dgl=?,MisJourMarque_dgl=?,MisJourCategory_dgl=?,MisJourQuantite_dgl=?,MisJourClient_dgl=?,VendreProduit_dgl=?,GererUtilisateur_dgl=? WHERE UtilisateurId_dgl=?");
//            pst.setString(1, id);
                pst.setInt(1, addProduct);
                pst.setInt(2, addSupplyer);
                pst.setInt(3, addBrand);
                pst.setInt(4, AddCatagory);
                pst.setInt(5, AddUnit);
                pst.setInt(6, UpdateProduct);
                pst.setInt(7, UpdateSupplyer);
                pst.setInt(8, UpdateBrand);
                pst.setInt(9, UpdateCatagory);
                pst.setInt(10, EmployeManage);
                pst.setInt(11, ChangeOwnPass);
                pst.setString(12, id);

                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess");
                alert.setContentText("Privilèges modifié avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (pt.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("UPDATE " + db + ".dpt_droitutilisateur SET AjoutProduit_dpt=?,AjoutFournisseur_dpt=?,AjoutMarque_dpt=?,AjoutCategory_dpt=?,AjoutQuantite_dpt=?,AjoutClient_dpt=?,MisJourProduit_dpt=?,MisJourFournisseur_dpt=?,MisJourMarque_dpt=?,MisJourCategory_dpt=?,MisJourQuantite_dpt=?,MisJourClient_dpt=?,VendreProduit_dpt=?,GererUtilisateur_dpt=? WHERE UtilisateurId_dpt=?");
//            pst.setString(1, id);
                pst.setInt(1, addProduct);
                pst.setInt(2, addSupplyer);
                pst.setInt(3, addBrand);
                pst.setInt(4, AddCatagory);
                pst.setInt(5, AddUnit);
                pst.setInt(6, UpdateProduct);
                pst.setInt(7, UpdateSupplyer);
                pst.setInt(8, UpdateBrand);
                pst.setInt(9, UpdateCatagory);
                pst.setInt(10, EmployeManage);
                pst.setInt(11, ChangeOwnPass);
                pst.setString(12, id);

                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess");
                alert.setContentText("Privilèges modifié avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (pz.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("UPDATE " + db + ".dpz_droitutilisateur SET AjoutProduit_dpz=?,AjoutFournisseur_dpz=?,AjoutMarque_dpz=?,AjoutCategory_dpz=?,AjoutQuantite_dpz=?,AjoutClient_dpz=?,MisJourProduit_dpz=?,MisJourFournisseur_dpz=?,MisJourMarque_dpz=?,MisJourCategory_dpz=?,MisJourQuantite_dpz=?,MisJourClient_dpz=?,VendreProduit_dpz=?,GererUtilisateur_dpz=? WHERE UtilisateurId_dpz=?");
//            pst.setString(1, id);
                pst.setInt(1, addProduct);
                pst.setInt(2, addSupplyer);
                pst.setInt(3, addBrand);
                pst.setInt(4, AddCatagory);
                pst.setInt(5, AddUnit);
                pst.setInt(6, UpdateProduct);
                pst.setInt(7, UpdateSupplyer);
                pst.setInt(8, UpdateBrand);
                pst.setInt(9, UpdateCatagory);
                pst.setInt(10, EmployeManage);
                pst.setInt(11, ChangeOwnPass);
                pst.setString(12, id);

                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess");
                alert.setContentText("Privilèges modifié avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (bl.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("UPDATE " + db + ".dbl_droitutilisateur SET AjoutProduit_dbl=?,AjoutFournisseur_dbl=?,AjoutMarque_dbl=?,AjoutCategory_dbl=?,AjoutQuantite_dbl=?,AjoutClient_dbl=?,MisJourProduit_dbl=?,MisJourFournisseur_dbl=?,MisJourMarque_dbl=?,MisJourCategory_dbl=?,MisJourQuantite_dbl=?,MisJourClient_dbl=?,VendreProduit_dbl=?,GererUtilisateur_dbl=? WHERE UtilisateurId_dbl=?");
//            pst.setString(1, id);
                pst.setInt(1, addProduct);
                pst.setInt(2, addSupplyer);
                pst.setInt(3, addBrand);
                pst.setInt(4, AddCatagory);
                pst.setInt(5, AddUnit);
                pst.setInt(6, UpdateProduct);
                pst.setInt(7, UpdateSupplyer);
                pst.setInt(8, UpdateBrand);
                pst.setInt(9, UpdateCatagory);
                pst.setInt(10, EmployeManage);
                pst.setInt(11, ChangeOwnPass);
                pst.setString(12, id);

                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess");
                alert.setContentText("Privilèges modifié avec succès");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void btnCloseOnClick(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void checqPermission() {

        if (ml.isSelected() == true) {
            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("select * from " + db + ".dml_utilisateur where Id_dml=?");
                pst.setString(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(2) == 1) {
                        cbAddProduct.setSelected(true);
                    } else {
                        cbAddProduct.setSelected(false);
                    }
                    if (rs.getInt(3) == 1) {
                        cbAddSupplyer.setSelected(true);
                    } else {
                        cbAddSupplyer.setSelected(false);
                    }
                    if (rs.getInt(4) == 1) {
                        cbAddBrand.setSelected(true);
                    } else {
                        cbAddBrand.setSelected(false);
                    }
                    if (rs.getInt(5) == 1) {
                        cbAddCatagory.setSelected(true);
                    } else {
                        cbAddCatagory.setSelected(false);
                    }
                    if (rs.getInt(6) == 1) {
                        cbAddUnit.setSelected(true);
                    } else {
                        cbAddUnit.setSelected(false);
                    }

                    if (rs.getInt(7) == 1) {
                        cbUpdateProduct.setSelected(true);
                    } else {
                        cbUpdateProduct.setSelected(false);
                    }
                    if (rs.getInt(8) == 1) {
                        cbUpdateSupplyert.setSelected(true);
                    } else {
                        cbUpdateSupplyert.setSelected(false);
                    }
                    if (rs.getInt(9) == 1) {
                        cbUpdateBrands.setSelected(true);
                    } else {
                        cbUpdateBrands.setSelected(false);
                    }
                    if (rs.getInt(10) == 1) {
                        cbUpdateCatagory.setSelected(true);
                    } else {
                        cbUpdateCatagory.setSelected(false);
                    }

                    if (rs.getInt(11) == 1) {
                        cbEmployeeManegement.setSelected(true);
                        cbAddEmployee.setSelected(true);
                        cbEmployePassChange.setSelected(true);
                        cbUpdateEmployee.setSelected(true);
                    } else {
                        cbEmployeeManegement.setSelected(false);
                        cbAddEmployee.setSelected(false);
                        cbEmployePassChange.setSelected(false);
                        cbUpdateEmployee.setSelected(false);
                    }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                    if (rs.getInt(12) == 1) {
                        cbChangePassword.setSelected(true);
                    } else {
                        cbChangePassword.setSelected(false);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (gl.isSelected() == true) {

            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("select * from " + db + ".dgl_utilisateur where Id_dgl=?");
                pst.setString(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(2) == 1) {
                        cbAddProduct.setSelected(true);
                    } else {
                        cbAddProduct.setSelected(false);
                    }
                    if (rs.getInt(3) == 1) {
                        cbAddSupplyer.setSelected(true);
                    } else {
                        cbAddSupplyer.setSelected(false);
                    }
                    if (rs.getInt(4) == 1) {
                        cbAddBrand.setSelected(true);
                    } else {
                        cbAddBrand.setSelected(false);
                    }
                    if (rs.getInt(5) == 1) {
                        cbAddCatagory.setSelected(true);
                    } else {
                        cbAddCatagory.setSelected(false);
                    }
                    if (rs.getInt(6) == 1) {
                        cbAddUnit.setSelected(true);
                    } else {
                        cbAddUnit.setSelected(false);
                    }

                    if (rs.getInt(7) == 1) {
                        cbUpdateProduct.setSelected(true);
                    } else {
                        cbUpdateProduct.setSelected(false);
                    }
                    if (rs.getInt(8) == 1) {
                        cbUpdateSupplyert.setSelected(true);
                    } else {
                        cbUpdateSupplyert.setSelected(false);
                    }
                    if (rs.getInt(9) == 1) {
                        cbUpdateBrands.setSelected(true);
                    } else {
                        cbUpdateBrands.setSelected(false);
                    }
                    if (rs.getInt(10) == 1) {
                        cbUpdateCatagory.setSelected(true);
                    } else {
                        cbUpdateCatagory.setSelected(false);
                    }

                    if (rs.getInt(11) == 1) {
                        cbEmployeeManegement.setSelected(true);
                        cbAddEmployee.setSelected(true);
                        cbEmployePassChange.setSelected(true);
                        cbUpdateEmployee.setSelected(true);
                    } else {
                        cbEmployeeManegement.setSelected(false);
                        cbAddEmployee.setSelected(false);
                        cbEmployePassChange.setSelected(false);
                        cbUpdateEmployee.setSelected(false);
                    }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                    if (rs.getInt(12) == 1) {
                        cbChangePassword.setSelected(true);
                    } else {
                        cbChangePassword.setSelected(false);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (pt.isSelected() == true) {

            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("select * from " + db + ".dpt_utilisateur where Id_dpt=?");
                pst.setString(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(2) == 1) {
                        cbAddProduct.setSelected(true);
                    } else {
                        cbAddProduct.setSelected(false);
                    }
                    if (rs.getInt(3) == 1) {
                        cbAddSupplyer.setSelected(true);
                    } else {
                        cbAddSupplyer.setSelected(false);
                    }
                    if (rs.getInt(4) == 1) {
                        cbAddBrand.setSelected(true);
                    } else {
                        cbAddBrand.setSelected(false);
                    }
                    if (rs.getInt(5) == 1) {
                        cbAddCatagory.setSelected(true);
                    } else {
                        cbAddCatagory.setSelected(false);
                    }
                    if (rs.getInt(6) == 1) {
                        cbAddUnit.setSelected(true);
                    } else {
                        cbAddUnit.setSelected(false);
                    }

                    if (rs.getInt(7) == 1) {
                        cbUpdateProduct.setSelected(true);
                    } else {
                        cbUpdateProduct.setSelected(false);
                    }
                    if (rs.getInt(8) == 1) {
                        cbUpdateSupplyert.setSelected(true);
                    } else {
                        cbUpdateSupplyert.setSelected(false);
                    }
                    if (rs.getInt(9) == 1) {
                        cbUpdateBrands.setSelected(true);
                    } else {
                        cbUpdateBrands.setSelected(false);
                    }
                    if (rs.getInt(10) == 1) {
                        cbUpdateCatagory.setSelected(true);
                    } else {
                        cbUpdateCatagory.setSelected(false);
                    }

                    if (rs.getInt(11) == 1) {
                        cbEmployeeManegement.setSelected(true);
                        cbAddEmployee.setSelected(true);
                        cbEmployePassChange.setSelected(true);
                        cbUpdateEmployee.setSelected(true);
                    } else {
                        cbEmployeeManegement.setSelected(false);
                        cbAddEmployee.setSelected(false);
                        cbEmployePassChange.setSelected(false);
                        cbUpdateEmployee.setSelected(false);
                    }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                    if (rs.getInt(12) == 1) {
                        cbChangePassword.setSelected(true);
                    } else {
                        cbChangePassword.setSelected(false);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (pz.isSelected() == true) {

            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("select * from " + db + ".dpz_utilisateur where Id_dpz=?");
                pst.setString(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(2) == 1) {
                        cbAddProduct.setSelected(true);
                    } else {
                        cbAddProduct.setSelected(false);
                    }
                    if (rs.getInt(3) == 1) {
                        cbAddSupplyer.setSelected(true);
                    } else {
                        cbAddSupplyer.setSelected(false);
                    }
                    if (rs.getInt(4) == 1) {
                        cbAddBrand.setSelected(true);
                    } else {
                        cbAddBrand.setSelected(false);
                    }
                    if (rs.getInt(5) == 1) {
                        cbAddCatagory.setSelected(true);
                    } else {
                        cbAddCatagory.setSelected(false);
                    }
                    if (rs.getInt(6) == 1) {
                        cbAddUnit.setSelected(true);
                    } else {
                        cbAddUnit.setSelected(false);
                    }

                    if (rs.getInt(7) == 1) {
                        cbUpdateProduct.setSelected(true);
                    } else {
                        cbUpdateProduct.setSelected(false);
                    }
                    if (rs.getInt(8) == 1) {
                        cbUpdateSupplyert.setSelected(true);
                    } else {
                        cbUpdateSupplyert.setSelected(false);
                    }
                    if (rs.getInt(9) == 1) {
                        cbUpdateBrands.setSelected(true);
                    } else {
                        cbUpdateBrands.setSelected(false);
                    }
                    if (rs.getInt(10) == 1) {
                        cbUpdateCatagory.setSelected(true);
                    } else {
                        cbUpdateCatagory.setSelected(false);
                    }

                    if (rs.getInt(11) == 1) {
                        cbEmployeeManegement.setSelected(true);
                        cbAddEmployee.setSelected(true);
                        cbEmployePassChange.setSelected(true);
                        cbUpdateEmployee.setSelected(true);
                    } else {
                        cbEmployeeManegement.setSelected(false);
                        cbAddEmployee.setSelected(false);
                        cbEmployePassChange.setSelected(false);
                        cbUpdateEmployee.setSelected(false);
                    }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                    if (rs.getInt(12) == 1) {
                        cbChangePassword.setSelected(true);
                    } else {
                        cbChangePassword.setSelected(false);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (bl.isSelected() == true) {

            con = dbCon.geConnection();
            try {
                pst = con.prepareStatement("select * from " + db + ".dbl_utilisateur where Id_dbl=?");
                pst.setString(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(2) == 1) {
                        cbAddProduct.setSelected(true);
                    } else {
                        cbAddProduct.setSelected(false);
                    }
                    if (rs.getInt(3) == 1) {
                        cbAddSupplyer.setSelected(true);
                    } else {
                        cbAddSupplyer.setSelected(false);
                    }
                    if (rs.getInt(4) == 1) {
                        cbAddBrand.setSelected(true);
                    } else {
                        cbAddBrand.setSelected(false);
                    }
                    if (rs.getInt(5) == 1) {
                        cbAddCatagory.setSelected(true);
                    } else {
                        cbAddCatagory.setSelected(false);
                    }
                    if (rs.getInt(6) == 1) {
                        cbAddUnit.setSelected(true);
                    } else {
                        cbAddUnit.setSelected(false);
                    }

                    if (rs.getInt(7) == 1) {
                        cbUpdateProduct.setSelected(true);
                    } else {
                        cbUpdateProduct.setSelected(false);
                    }
                    if (rs.getInt(8) == 1) {
                        cbUpdateSupplyert.setSelected(true);
                    } else {
                        cbUpdateSupplyert.setSelected(false);
                    }
                    if (rs.getInt(9) == 1) {
                        cbUpdateBrands.setSelected(true);
                    } else {
                        cbUpdateBrands.setSelected(false);
                    }
                    if (rs.getInt(10) == 1) {
                        cbUpdateCatagory.setSelected(true);
                    } else {
                        cbUpdateCatagory.setSelected(false);
                    }

                    if (rs.getInt(11) == 1) {
                        cbEmployeeManegement.setSelected(true);
                        cbAddEmployee.setSelected(true);
                        cbEmployePassChange.setSelected(true);
                        cbUpdateEmployee.setSelected(true);
                    } else {
                        cbEmployeeManegement.setSelected(false);
                        cbAddEmployee.setSelected(false);
                        cbEmployePassChange.setSelected(false);
                        cbUpdateEmployee.setSelected(false);
                    }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                    if (rs.getInt(12) == 1) {
                        cbChangePassword.setSelected(true);
                    } else {
                        cbChangePassword.setSelected(false);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void stockManeCbOperation() {

        if (cbStockManegement.isSelected()) {
            cbAddProduct.setSelected(true);
            cbAddBrand.setSelected(true);
            cbAddCatagory.setSelected(true);
            cbAddSupplyer.setSelected(true);
            cbAddUnit.setSelected(true);
            cbUpdateBrands.setSelected(true);
            cbUpdateCatagory.setSelected(true);
            cbUpdateSupplyert.setSelected(true);
            cbUpdateProduct.setSelected(true);

        } else if (!cbStockManegement.isSelected()) {
            cbAddProduct.setSelected(false);
            cbAddBrand.setSelected(false);
            cbAddCatagory.setSelected(false);
            cbAddSupplyer.setSelected(false);
            cbAddUnit.setSelected(false);
            cbUpdateBrands.setSelected(false);
            cbUpdateCatagory.setSelected(false);
            cbUpdateSupplyert.setSelected(false);
            cbUpdateProduct.setSelected(false);

        }

    }
}
