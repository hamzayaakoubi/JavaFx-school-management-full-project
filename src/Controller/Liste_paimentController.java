/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entites.Paie_Prof;
import Entites.Paiment_eleve;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import Services.FAQ1Crud;


import Services.ServicePaiment_eleve;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Liste_paimentController implements Initializable {
@FXML
private TableView<Paiment_eleve>table;
@FXML
private TableColumn<Paiment_eleve,Integer>num_recu;
@FXML
private TableColumn<Paiment_eleve,String>nom;
@FXML
private TableColumn<Paiment_eleve,String>prenom;
@FXML
private TableColumn<Paiment_eleve,Float>remise;
@FXML
private TableColumn<Paiment_eleve,String>mode_reglement;
@FXML
private TableColumn<Paiment_eleve,String>frais;
@FXML
private TableColumn<Paiment_eleve,Double>montant;
@FXML
private TableColumn<Paiment_eleve,Integer>reste;
@FXML
private TableColumn<Paiment_eleve,Integer>cin;
@FXML
private TableColumn<Paiment_eleve,String>date;
@FXML
private TableColumn<Paiment_eleve,String>mois;
  @FXML
    private Button ajouter;
    @FXML
    private TextField recherche;
       @FXML
    private Pane mainpane;
 private ServicePaiment_eleve paimentService=new ServicePaiment_eleve();
public ObservableList<Paiment_eleve>data;
    @FXML
    private Button modifier;
    @FXML
    private Button refrech;
    /**
     * Initializes the controller class.
     */
    @Override
  public void initialize(java.net.URL location, ResourceBundle resources) {
	      
         num_recu.setCellValueFactory(new PropertyValueFactory<>("num_recu"));
	//num_recu.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("Num_Reçu"));
	mode_reglement.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("mode_reglement"));
	frais.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("type_frais"));
	remise.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Float>("remise"));
	montant.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Double>("montant"));
	cin.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("cin"));
	
	mois.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("mois"));
	reste.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("reste"));
	date.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("Date"));
	nom.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("prenom"));
        
        
    
  data=paimentService.getListePaiements();
       FilteredList<Paiment_eleve> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getMode_paiment().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
             
                        }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paiment_eleve> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
       //  mainpane.getChildren().add(table);
        table.setItems(sortedData);
//table.setItems(data);

	
}
    @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Admin\\Desktop\\paiementEleve.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(11);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("Num_recu");
       table.addCell("Cin");
       table.addCell("Nom");
       table.addCell("Prenom");
       table.addCell("Mode Reglement");
       table.addCell("Remise");
       table.addCell("Frais");
       table.addCell("Montant");
       table.addCell("Reste");
       table.addCell("Date");
       table.addCell("Mois");
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des Paiments Eleve");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\Admin\\Documents\\NetBeansProjects\\BEFOREHOUSSEMrar\\123456\\src\\Images\\bank.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT * FROM `finance`");
       while(rs.next()){
       table.addCell(rs.getString("num_recu"));
            table.addCell(rs.getString("cin"));

                   table.addCell(rs.getString("nom"));
table.addCell(rs.getString("prenom"));
table.addCell(rs.getString("mode_reglement"));
table.addCell(rs.getString("remise"));
table.addCell(rs.getString("type_frais"));
table.addCell(rs.getString("montant"));
table.addCell(rs.getString("reste"));
table.addCell(rs.getString("date"));
table.addCell(rs.getString("mois"));

       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }

       
    @FXML
    public void refresh() {
        table.setItems(paimentService.getListePaiements());
        System.out.println("Liste des paiments rafraichie.");
    }

     
          public void afficherResultatRecherche(KeyEvent event) {
        if (recherche.getText() != "") {
            table.setItems(paimentService.recherchePaiment(recherche.getText()));
        }
    }
              public void actionSearch(KeyEvent event) {
        data = paimentService.recherchePaiment(recherche.getText());
        table.setItems(data);
    }
    @FXML
          public void insertPanel(ActionEvent e) throws IOException {
	mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/Paiment_eleve.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
}
              public void retourVersListe(ActionEvent event) throws IOException {
 mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/FXMLDocument.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }

                           public void actionRefresh(ActionEvent event) {
      /*  clear();
        showData();
        autoId();
*/
    }
    @FXML
       public void u(ActionEvent e) throws IOException {
         //  FXMLLoader loader =new FXMLLoader(getClass().getResource("/views/Update.fxml"));
          //Modifier_paiment_eleveController u=loader.getController();
          
	  mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/Update.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
}
    @FXML
         public void stat(ActionEvent e) throws Exception{
        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/PieChart2.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
         }
    
}
