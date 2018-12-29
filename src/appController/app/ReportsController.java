/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appController.app;

import appController.AppController;
import bdd.BddPropreties;
import bdd.bddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import gettersSetters.CurrentProductGetway;
import gettersSetters.IngredientsPFinish;
import gettersSetters.ListIngredients;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
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
public class ReportsController implements Initializable {

    @FXML
    private BorderPane bpStore;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    public JFXRadioButton ml;
    @FXML
    private ToggleGroup grp;
    @FXML
    public JFXRadioButton gl;
    @FXML
    public JFXRadioButton pt;
    @FXML
    public JFXRadioButton pz;
    @FXML
    public JFXRadioButton bl;
    @FXML
    private Label lblHeader;
    @FXML
    private JFXButton btnproduct;
    @FXML
    private JFXButton btningredients;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    bddConnection dbCon = new bddConnection();
    BddPropreties dBProperties = new BddPropreties();
    String db = dBProperties.loadPropertiesFile();
    String usrName;
    String id;
    @FXML
    private JFXDatePicker dpdate;
    @FXML
    private Label jobStatus;
    @FXML
    private AnchorPane testprint;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4;
    PreparedStatement pst5;
    ResultSet rs2;
    ResultSet rs3;
    ResultSet rs4;
    ResultSet rs5;
    @FXML
    private TableColumn<Object, Object> clmingredients;
    @FXML
    private TableColumn<Object, Object> clmqtt;
    @FXML
    private TableView<ListIngredients> tableview;
    IngredientsPFinish i = new IngredientsPFinish();
    TablePrinter pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //dpdate.setValue(LocalDate.now());
    }

    @FXML
    private void btnproductOnAction(ActionEvent event) {
        if (ml.isSelected() == true) {
            sortieVenteJounalier("dml", "Fiche de Production Militaire");
        } else if (pt.isSelected() == true) {
            sortieVenteJounalier("dpt", "Fiche de Production Patisserie");
        } else if (gl.isSelected() == true) {
            sortieVenteJounalier("dgl", "Fiche de Production Glacerie");
        } else if (pz.isSelected() == true) {
            sortieVenteJounalier("dpz", "Fiche de Production Pizzeria");
        } else if (bl.isSelected() == true) {
            sortieVenteJounalier("dbl", "Fiche de Production Boulangeria");
        } else {
            System.out.println("Aucune depot selectionné");
        }
    }

    @FXML
    private void btningredientsOnAction(ActionEvent event) {
        TablePrinter.print(tableview);
    }

    public void sortieVenteJounalier(String sigle, String texte) {
        con = dbCon.geConnection();
        try {
            if (dpdate.getValue() != null) {

                HashMap params = new HashMap();
                params.put("date", dpdate.getValue().toString());
                params.put("depot", texte);

                InputStream is = getClass().getResourceAsStream("/reports/sortie_" + sigle + ".jrxml");
                JasperDesign jd = JRXmlLoader.load(is);
                JasperReport report = JasperCompileManager.compileReport(jd);
                JasperPrint print = JasperFillManager.fillReport(report, params, con);
                JasperViewer.viewReport(print, false);
            } else {
                HashMap params = new HashMap();
                params.put("date", "1111-11-11");
                params.put("depot", texte);

                InputStream is = getClass().getResourceAsStream("/reports/sortie_" + sigle + ".jrxml");
                JasperDesign jd = JRXmlLoader.load(is);
                JasperReport report = JasperCompileManager.compileReport(jd);
                JasperPrint print = JasperFillManager.fillReport(report, params, con);
                JasperViewer.viewReport(print, false);
            }
        } catch (JRException e) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Erreur");
            a.setHeaderText("Erreur:");
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
    }

    @FXML
    private void dpdateOnAction(ActionEvent event) {
        String d = dpdate.getValue().toString();
        System.out.println("date is //...: " + d);
        if (ml.isSelected() == true) {
            ParjourIngredients("dml", d);
        } else if (pt.isSelected() == true) {
            ParjourIngredients("dpt", d);
        } else if (gl.isSelected() == true) {
            ParjourIngredients("dgl", d);
        } else if (pz.isSelected() == true) {
            ParjourIngredients("dpz", d);
        } else if (bl.isSelected() == true) {
            ParjourIngredients("dbl", d);
        } else {
            System.out.println("Aucune depot selectionné");
        }
    }

    private void print(Node node) {
        jobStatus.textProperty().unbind();
        jobStatus.setText("Creating a printer job...");

        // Create a printer job for the default printer               
        PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = job.getPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
        JobSettings jobSettings = job.getJobSettings();
        jobSettings.setPageLayout(pageLayout);
        double pgW = pageLayout.getPrintableWidth();
        double pgH = pageLayout.getPrintableHeight();

// Make the Ellipse fit the printable are of the page 
        Ellipse nodes = new Ellipse(pgW / 2, pgH / 2, pgW / 2, pgH / 2);
        nodes.setFill(null);
        nodes.setStroke(Color.BLACK);
        nodes.setStrokeWidth(1);

        if (job != null) {
            // Show the printer job status                        
            jobStatus.textProperty().bind(job.jobStatusProperty().asString());
            // Print the node 

            boolean printed = job.printPage(node);
            if (printed) {
                // End the printer job 
                job.endJob();
            } else {
                jobStatus.textProperty().unbind();
                jobStatus.setText("Printing failed.");
            }
        } else {
            jobStatus.setText("Could not create a printer job.");
        }
    }

    public void ParjourIngredients(String sigle, String date) {
        //i.ingredientsList.clear();
        con = dbCon.geConnection();

        try {
            System.out.println("01");
            pst = con.prepareStatement("SELECT QuantiteIngredients,idIngredients FROM " + db + ".`" + sigle + "_produitsfinies` where Date='1111-11-11'");
            rs = pst.executeQuery();
            System.out.println("02");
            while (rs.next()) {
System.out.println("03");
                pst5 = con.prepareStatement("SELECT Production FROM " + db + ".`" + sigle + "_produitsfinies` where Date='" + date + "'");
                rs5 = pst5.executeQuery();
System.out.println("04");
                while (rs5.next()) {
                    try {
                        Double quantiteingredients = Double.parseDouble(rs.getString(1));
                        System.out.println("05");
                        //String id = rs.getString(2);
                        Double qtt = quantiteingredients * Double.parseDouble(rs5.getString(1));
System.out.println("06");
                        try {
                            pst4 = con.prepareStatement("SELECT Ingredients FROM " + db + ".`" + sigle + "_ingredients` where Id='" + rs.getString(2) + "'");
                            rs4 = pst4.executeQuery();
                            while(rs4.next()){
                            System.out.println("07");
                            try {
                                i.ingredientsList.addAll(new ListIngredients(rs4.getString(1), String.valueOf(qtt)));
                            } catch (SQLException e) {
                                System.err.println("error is : "+e);
                            }
                            
//                            ListIngredients lss = ;
                            System.out.println("avant tbl");

                            System.out.println("apres tbl");
                        }} catch (Exception e) {
                        }

                    } catch (SQLException e) {
                        Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                tableview.setItems(i.ingredientsList);
                clmqtt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                clmingredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
                //clmdt.setCellValueFactory(new PropertyValueFactory<>("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
