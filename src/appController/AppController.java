/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController;

import appController.app.EmployeController;
import appController.app.ReportsController;
import appController.app.SellController;
import appController.app.SettingsController;
import appController.app.StockController;
import appController.home.HomeController;
import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import gettersSetters.UsersGetWay;
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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import list.Utilisateurs;

/**
 * FXML Controller class
 *
 * @author Ralande
 */
public class AppController implements Initializable {

    @FXML
    public AnchorPane acMain;
    @FXML
    private BorderPane ancprincipal;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnStore;
    @FXML
    private JFXButton btnSell;
    @FXML
    private JFXButton btnEmplopye;
    @FXML
    private JFXButton btnSettings;
    @FXML
    public JFXButton btnlougout;
    @FXML
    private BorderPane appcontent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private StackPane acContent;

    bddConnection dbCon = new bddConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    String usrName;
    String id;

    HamburgerSlideCloseTransition hamb;
    private userNameMedia usrNameMedia;

    Utilisateurs users = new Utilisateurs();
    UsersGetWay usersGetway = new UsersGetWay();
    @FXML
    private Label textapp;
    @FXML
    private Label textapp1;
    @FXML
    public JFXButton btnproducts;
    @FXML
    private JFXButton btnlougout1;
    @FXML
    private JFXButton btnUnite;

    public userNameMedia getUsrNameMedia() {
        return usrNameMedia;
    }

    public void setUsrNameMedia(userNameMedia usrNameMedia) {
        //lblUserId.setText(usrNameMedia.getId());
        //   lblUsrName.setText(usrNameMedia.getNom());
        id = usrNameMedia.getId();
        usrName = usrNameMedia.getNom();

        this.usrNameMedia = usrNameMedia;
    }

    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hamb = new HamburgerSlideCloseTransition(hamburger);
        hamb.setRate(1);
    }

    public void addtexapp(String text) {
        textapp.setText(text);
    }

    public void addtexapp1(String text) {
        textapp1.setText(text);
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/app/home/home.fxml").openStream());
            HomeController hc = fxmlLoader.getController();
            hc.name.setText(textapp1.getText());
            if (textapp.getText().equals("dml")) {
                hc.dgl.setOpacity(0.5);
                hc.dgl.setStyle("-fx-background-color:gray;");
                hc.dpt.setOpacity(0.5);
                hc.dpt.setStyle("-fx-background-color:gray;");
                hc.dpz.setOpacity(0.5);
                hc.dpz.setStyle("-fx-background-color:gray;");
                hc.dbl.setOpacity(0.5);
                hc.dbl.setStyle("-fx-background-color:gray;");
            } else if (textapp.getText().equals("dgl")) {
                hc.dml.setOpacity(0.5);
                hc.dml.setStyle("-fx-background-color:gray;");
                hc.dpt.setOpacity(0.5);
                hc.dpt.setStyle("-fx-background-color:gray;");
                hc.dpz.setOpacity(0.5);
                hc.dpz.setStyle("-fx-background-color:gray;");
                hc.dbl.setOpacity(0.5);
                hc.dbl.setStyle("-fx-background-color:gray;");
            } else if (textapp.getText().equals("dpt")) {
                hc.dml.setOpacity(0.5);
                hc.dml.setStyle("-fx-background-color:gray;");
                hc.dpz.setOpacity(0.5);
                hc.dpz.setStyle("-fx-background-color:gray;");
                hc.dbl.setOpacity(0.5);
                hc.dbl.setStyle("-fx-background-color:gray;");
                hc.dgl.setOpacity(0.5);
                hc.dgl.setStyle("-fx-background-color:gray;");
            } else if (textapp.getText().equals("dpz")) {
                hc.dml.setOpacity(0.5);
                hc.dml.setStyle("-fx-background-color:gray;");
                hc.dpt.setOpacity(0.5);
                hc.dpt.setStyle("-fx-background-color:gray;");
                hc.dgl.setOpacity(0.5);
                hc.dgl.setStyle("-fx-background-color:gray;");
                hc.dbl.setOpacity(0.5);
                hc.dbl.setStyle("-fx-background-color:gray;");
            } else if (textapp.getText().equals("dbl")) {
                hc.dml.setOpacity(0.5);
                hc.dml.setStyle("-fx-background-color:gray;");
                hc.dpt.setOpacity(0.5);
                hc.dpt.setStyle("-fx-background-color:gray;");
                hc.dpz.setOpacity(0.5);
                hc.dpz.setStyle("-fx-background-color:gray;");
                hc.dgl.setOpacity(0.5);
                hc.dgl.setStyle("-fx-background-color:gray;");
            } else {
                System.out.println("Aucune dépot n'est selectionné");
            }
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
    }

    @FXML
    private void btnStoreOnClick(ActionEvent event) {
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        FXMLLoader fx = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/app/app/Stock.fxml").openStream());
            //fx.load(getClass().getResource("/app/app/stock/CurrentStore.fxml").openStream());
            nm.setId(id);
            StockController stockController = fXMLLoader.getController();

            if (textapp.getText().equals("dml")) {
                stockController.settingPermission("dml_droitutilisateur", "Id_dml");
                stockController.ml.setSelected(true);
                // crc.ml.setSelected(true);
            } else if (textapp.getText().equals("dgl")) {
                stockController.settingPermission("dgl_droitutilisateur", "Id_dgl");
                stockController.gl.setSelected(true);
                // crc.gl.setSelected(true);
            } else if (textapp.getText().equals("dpt")) {
                stockController.settingPermission("dpt_droitutilisateur", "Id_dpt");
                stockController.pt.setSelected(true);
                // crc.pt.setSelected(true);
            } else if (textapp.getText().equals("dpz")) {
                stockController.settingPermission("dpz_droitutilisateur", "Id_dpz");
                stockController.pz.setSelected(true);
                // crc.pz.setSelected(true);
            } else if (textapp.getText().equals("dbl")) {
                stockController.settingPermission("dbl_droitutilisateur", "Id_dbl");
                stockController.bl.setSelected(true);
                //  crc.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné sur btnStoreOnClick(ActionEvent event)");
            }

            // CurrentStoreController crc = fx.getController();
            stockController.bpStore.getStylesheets().add("/css/MainStyle.css");
            stockController.setUserId(usrNameMedia);
            stockController.btnStockOnAction(event);
            stockController.lblHeader.setText("Matières Premières");

            AnchorPane acPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(acPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Notre erreur est sur: " + ex);
        }
    }

    @FXML
    private void btnSellOnClick(ActionEvent event) {
        SellController controller = new SellController();
        userNameMedia nm = new userNameMedia();
        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/app/app/Sell.fxml").openStream());
            nm.setId(id);
            SellController sellController = fXMLLoader.getController();

            if (textapp.getText().equals("dml")) {
                sellController.ml.setSelected(true);
            } else if (textapp.getText().equals("dgl")) {
                sellController.gl.setSelected(true);
            } else if (textapp.getText().equals("dpt")) {
                sellController.pt.setSelected(true);
            } else if (textapp.getText().equals("dpz")) {
                sellController.pz.setSelected(true);
            } else if (textapp.getText().equals("dbl")) {
                sellController.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné sur btnStoreOnClick(ActionEvent event)");
            }

            sellController.setNameMedia(usrNameMedia);
            sellController.acMainSells.getStylesheets().add("/css/MainStyle.css");
            sellController.tbtnSellOnAction(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void employeeActive() {
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(defultStyle);
        btnEmplopye.setStyle(activeStyle);
        btnSettings.setStyle(defultStyle);
    }

    @FXML
    private void btnEmplopyeOnClick(ActionEvent event) throws IOException {
        employeeActive();
        EmployeController ec = new EmployeController();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/Employe.fxml").openStream());
        nm.setId(id);
        EmployeController employeController = fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/css/MainStyle.css");
        employeController.setNameMedia(usrNameMedia);
        employeController.btnViewEmployeeOnAction(event);

        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {

        //inithilize Setting Controller
        SettingsController settingController = new SettingsController();
        //inithilize UserNameMedia
        userNameMedia usrMedia = new userNameMedia();
        // Define a loader to inithilize Settings.fxml controller
        FXMLLoader settingLoader = new FXMLLoader();
        //set the location of Settings.fxml by fxmlloader
        settingLoader.load(getClass().getResource("/app/app/Settings.fxml").openStream());

        //Send id to userMedia
        usrMedia.setId(id);
        //take control of settingController elements or node
        SettingsController settingControl = settingLoader.getController();
        settingControl.bpSettings.getStylesheets().add("/css/MainStyle.css");

        settingControl.setUsrMedia(usrMedia);
        settingControl.lblCurrentStatus.setText("Mon compte");

        if (textapp.getText().equals("dml")) {
            settingControl.ml.setSelected(true);
            System.out.println("dml sur le texte");
            settingControl.miMyASccountOnClick(event);
        } else if (textapp.getText().equals("dgl")) {
            settingControl.gl.setSelected(true);
            System.out.println("dgl sur le texte");
            settingControl.miMyASccountOnClick(event);
        } else if (textapp.getText().equals("dpt")) {
            settingControl.pt.setSelected(true);
            System.out.println("dpt sur le texte");
            settingControl.miMyASccountOnClick(event);
        } else if (textapp.getText().equals("dpz")) {
            settingControl.pz.setSelected(true);
            System.out.println("dpz sur le texte");
            settingControl.miMyASccountOnClick(event);
        } else if (textapp.getText().equals("dbl")) {
            settingControl.bl.setSelected(true);
            System.out.println("dbl sur le texte");
            settingControl.miMyASccountOnClick(event);
        } else {
            System.out.println("Aucune dépot n'est selectionné sur btnSettingsOnClick(ActionEvent event)");
        }
        //settingControl.settingPermission();
        AnchorPane acPane = settingLoader.getRoot();
        //acContent clear and make useul for add next node
        acContent.getChildren().clear();
        //add a node call "acPane" to acContent
        acContent.getChildren().add(acPane);
    }

    @FXML
    private void btnlougout_act(ActionEvent event) throws IOException {
    /*    acContent.getChildren().clear();
        acContent.getChildren().add(FXMLLoader.load(getClass().getResource("/app/login.fxml")));
        acDashBord.getChildren().clear();
        acHead.getChildren().clear();
        acContent.setMaxSize(800, 500);
        acHead.setMaxHeight(0.0);
        //acMain.setMaxSize(800, 500);*/

        ((Node) (event.getSource())).getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/app/login.fxml"));
        loader.load();
        Parent parenthome = loader.getRoot();

        Stage stage = new Stage();
        // stage.getIcons().add(new Image("/image/lg.png"));
        Scene scene = new Scene(parenthome);
        stage.setScene(scene);
        stage.setTitle("Se Connecter sur Amical");
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void hamburger_act(MouseEvent event) {
        hamb.setRate(hamb.getRate() * -1);
        TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
        hamb.play();
        if (hamb.getRate() == -1) {
            sideMenu.setByX(-200);
            sideMenu.play();
            acDashBord.getChildren().clear();

        } else {
            sideMenu.setByX(200);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPan);
        }
    }

    public void permission(String tabledroit, String UtilisateurId) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + "." + tabledroit + " where " + UtilisateurId + "=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(15) == 0) {
                    btnEmplopye.setDisable(true);
                    btnUnite.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewDetails(String table, String id) {
        users.id = id;
        usersGetway.selectedView(users, table, id);
        // image = users.image;
        //  circleImgUsr.setFill(new ImagePattern(image));
        // imgUsrTop.setFill(new ImagePattern(image));
        // lblFullName.setText(users.fullName);
        // lblUsrNamePopOver.setText(users.userName);
    }

    @FXML
    private void btnSProductOnClick(ActionEvent event) {

        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        FXMLLoader fx = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/app/app/Stock.fxml").openStream());
            //fx.load(getClass().getResource("/app/app/stock/CurrentStore.fxml").openStream());
            nm.setId(id);
            StockController stockController = fXMLLoader.getController();

            if (textapp.getText().equals("dml")) {
                stockController.settingPermission("dml_droitutilisateur", "Id_dml");
                stockController.ml.setSelected(true);
                // crc.ml.setSelected(true);
            } else if (textapp.getText().equals("dgl")) {
                stockController.settingPermission("dgl_droitutilisateur", "Id_dgl");
                stockController.gl.setSelected(true);
                // crc.gl.setSelected(true);
            } else if (textapp.getText().equals("dpt")) {
                stockController.settingPermission("dpt_droitutilisateur", "Id_dpt");
                stockController.pt.setSelected(true);
                // crc.pt.setSelected(true);
            } else if (textapp.getText().equals("dpz")) {
                stockController.settingPermission("dpz_droitutilisateur", "Id_dpz");
                stockController.pz.setSelected(true);
                // crc.pz.setSelected(true);
            } else if (textapp.getText().equals("dbl")) {
                stockController.settingPermission("dbl_droitutilisateur", "Id_dbl");
                stockController.bl.setSelected(true);
                //  crc.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné sur btnStoreOnClick(ActionEvent event)");
            }

            // CurrentStoreController crc = fx.getController();
            stockController.bpStore.getStylesheets().add("/css/MainStyle.css");
            stockController.setUserId(usrNameMedia);
            stockController.finishproducts(event);
            stockController.lblHeader.setText("Produits Finies");
            stockController.btnSupplyer.setDisable(true);
            stockController.btnBrands.setDisable(true);
            stockController.btnCatagory.setDisable(true);

            AnchorPane acPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(acPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Notre erreur est sur: " + ex);
        }
    }

    @FXML
    private void btnReportsAct(ActionEvent event) throws SQLException {
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        FXMLLoader fx = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/app/app/Reports.fxml").openStream());
            //fx.load(getClass().getResource("/app/app/stock/CurrentStore.fxml").openStream());
            nm.setId(id);
            ReportsController stockController = fXMLLoader.getController();

            if (textapp.getText().equals("dml")) {                
                stockController.ml.setSelected(true);
                // crc.ml.setSelected(true);
            } else if (textapp.getText().equals("dgl")) {                
                stockController.gl.setSelected(true);
                // crc.gl.setSelected(true);
            } else if (textapp.getText().equals("dpt")) {
                stockController.pt.setSelected(true);
                // crc.pt.setSelected(true);
            } else if (textapp.getText().equals("dpz")) {
                stockController.pz.setSelected(true);
                // crc.pz.setSelected(true);
            } else if (textapp.getText().equals("dbl")) {
                stockController.bl.setSelected(true);
                //  crc.bl.setSelected(true);
            } else {
                System.out.println("Aucune dépot n'est selectionné sur btnStoreOnClick(ActionEvent event)");
            }
            
            AnchorPane acPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(acPane);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Notre erreur est sur: " + ex);
        }
    }



    @FXML
    private void btnUniteOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/app/app/Unit.fxml").openStream());

        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }
}
