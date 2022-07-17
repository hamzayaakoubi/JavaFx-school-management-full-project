
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entites.Note;
import Entites.Absence;
 
import Services.ServiceNote;
import Services.ServiceAbsence;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Utils.Connexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author pablo
 */
public class AfficherNoteController implements Initializable {

    /**
     * Initializes the controller class.
     */
        private Connection con = Connexion.getInstance().getCnx();
            private ObservableList<Note> Oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Note> TableAff;
    @FXML
    private TableColumn<?, ?> ref_cin;
    @FXML
    private TableColumn<?, ?> ref_nom;
    @FXML
    private TableColumn<?, ?> ref_prenom;
    @FXML
    private TableColumn<?, ?> ref_matiere;
    @FXML
    private TableColumn<?, ?> ref_note;
    @FXML
    private TableColumn<?, ?> action;
    @FXML
    private Button add_note_btn;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField filter;
    @FXML
    private Button mail;
    @FXML
    private Pane mainpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 ref_cin.setCellValueFactory(new PropertyValueFactory<>("id"));
            ref_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ref_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            ref_matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            ref_note.setCellValueFactory(new PropertyValueFactory<>("note"));
            action.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
                 // tableAFF.setItems(dataList);  
                  TableAff.setItems(Oblist); 
            
//               
            
                            this.note_table() ; 
 
//               
              
                
        
  
         
         
                   
    }
    
         public void note_table() {
          
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("select * from notes");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

             
               Oblist.add(new Note(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"), rs.getString("matiere"), rs.getInt("note")));
                
                
                  
                
            }            
            
        
                
                                
                
                 
          
        }
   
        catch (SQLException ex) {
        }
          
              
    }
           @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Admin\\Desktop\\note.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(5);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("id_Note");
       table.addCell("Nom");
       table.addCell("Prenom");
              table.addCell("Matiere");
                     table.addCell("Note");
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des Notes");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                  // document.add(com.itextpdf.text.Image.getInstance("C:/Users/pablo/Documents/NetBeansProjects/Pi_dev/src/Images/bank.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT IDD,nom,prenom,matiere,note FROM `notes`");
       while(rs.next()){
       table.addCell(rs.getString("IDD"));
       table.addCell(rs.getString("nom"));

       table.addCell(rs.getString("prenom"));
       table.addCell(rs.getString("matiere"));
       table.addCell(rs.getString("note"));
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
        // TODO

    @FXML
    private void add_note_button(ActionEvent event) throws IOException {
           mainpane.getChildren().clear();
           Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AjouterNote.fxml"));
           mainpane.getChildren().add(parent);
           mainpane.toFront();


    }
           @FXML
    private void stat(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/statnote.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }


    @FXML
    private void update_note_btn(ActionEvent event) {
        
        
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/ModifierNote.fxml"));
        try {
            Note n1=get_selected_row();
            
            Parent root=loader.load();
            ModifierNoteController upc=loader.getController(); 
            upc.setFields(n1);
         mainpane.getChildren().clear();
        mainpane.getChildren().add(root);
        mainpane.toFront();
          /*  Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();*/
        } catch (IOException ex) {
            Logger.getLogger(AfficherNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void delete_note(ActionEvent event) {
        
        ObservableList<Note>Oblist = FXCollections.observableArrayList();
        ServiceNote gp = new ServiceNote();
        for (Note n  : TableAff.getItems()) {
            if (n.getCh_box().isSelected()) {
                Oblist.add(n);
            }

        }
        for (Note n : Oblist) {
            gp.supprimerNote(n.getId());
        }

        TableAff.getItems().clear();
        this.note_table();
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("La suppresion fait avec succee yé jhonnnnnnn !");
            alert.showAndWait();
        
        
        
    }
          public Note get_selected_row(){
         Note n1=null;
            for (Note n  : TableAff.getItems()) {
            if (n.getCh_box().isSelected()) {
                n1=n;
            }
            }
            return n1;
    
    } 

    @FXML
    private void sendmail(ActionEvent event) {
        String s="" ; 
           ObservableList<Note>Oblist = FXCollections.observableArrayList();
        for (Note n  : TableAff.getItems()) {
            if (n.getCh_box().isSelected()) {
                Oblist.add(n);
                s=n.toString();
            }

        }
          try {
            JavaMainUtil.sendMail("houssem.hamdi@esprit.tn",s);
        } catch (Exception ex) {
            Logger.getLogger(AfficherNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("email envoyeé avec succe  yé jhonnnnnnn !");
            alert.showAndWait();
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
             ObservableList<Note> Oblistt = FXCollections.observableArrayList();
             int i =-1; 
             String s =filter.getText() ; 
             try {
              i = Integer.parseInt(s) ; }
             catch (Exception e ){}
             PreparedStatement pt ; 
                  pt = con.prepareStatement("select * from notes where nom like '%"+s+"%' or note = "+i);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) 
            {
            
                           Oblistt.add(new Note(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"), rs.getString("matiere"), rs.getInt("note")));

            
            
            }
            TableAff.getItems().clear(); 
            TableAff.setItems(Oblistt);
             
             
             
             
        
              }
  

      
    

 }
