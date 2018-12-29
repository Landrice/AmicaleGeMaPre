/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app;

import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import gettersSetters.ListUnit;
import gettersSetters.ListUtilisateurs;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class UnitController implements Initializable {

    @FXML
    private BorderPane bpContent;
    @FXML
    private TableView<ListUnit> tableview;
    @FXML
    private TableColumn<Object, Object> unitecell;
    @FXML
    private StackPane spEmployeContent;
    @FXML
    private TextField tf;
    @FXML
    private JFXButton btnAjout;
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    bddConnection dbConnection = new bddConnection();
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    public ObservableList<ListUnit> UnitLists = FXCollections.observableArrayList();
    ListUnit unit;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label lbl;
    @FXML
    private JFXButton btnSauvegarde;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tf.visibleProperty().bind(lbl.visibleProperty());

        anchor.getStylesheets().add("/css/MainStyle.css");
        setunite();
        lbl.setVisible(false);
        btnSauvegarde.setVisible(false);
    }

    @FXML
    private void btnAjoutOnAction(ActionEvent event) {
        tf.setText(null);
        lbl.setVisible(true);
        btnSauvegarde.setVisible(true);
        btnAjout.setVisible(false);
    }

    public void setunite() {
        tableview.getItems().clear();
        tableview.setItems(UnitLists);
        unitecell.setCellValueFactory(new PropertyValueFactory<>("unite"));
        viewBdd();
    }

    public void viewBdd() {
        con = dbConnection.geConnection();
        unit = new ListUnit();

        try {
            pst = con.prepareStatement("select * from " + db + ".unite");
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unite = rs.getString(2);
                UnitLists.addAll(new ListUnit(unit.id, unit.unite));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSauvegardeOnAction(ActionEvent event) {
        if(tf.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Vide");
            a.setHeaderText("Champ Vide");
            a.setContentText("Veuillez remplir le champ de texte");
            a.showAndWait();
        }else{
           add(); 
        }
        
        
        
    }

    public void add() {
        if (!tf.getText().isEmpty()) {
            con = dbConnection.geConnection();
            try {
                pst = con.prepareStatement("insert into " + db + ".unite values(?,?)");
                pst.setString(1, null);
                pst.setString(2, tf.getText().toUpperCase().trim());
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
            setunite();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succes");
            a.setHeaderText("Reussie");
            a.setContentText("Unité ajouté avec succes");
            a.showAndWait();
            lbl.setVisible(false);
        btnAjout.setVisible(true);
        btnSauvegarde.setVisible(false);
            
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Vide");
            a.setHeaderText("Champ Vide");
            a.setContentText("Veuillez remplir le champ de texte");
            a.showAndWait();
        }
        
    }
}
