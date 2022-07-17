/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Services.ServiceAbsence ; 
import Entites.Absence;
import Entites.Note;
import Services.ServiceNote;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class AfficherAbsenceController implements Initializable {
        private Connection con = Connexion.getInstance().getCnx();
            private ObservableList<Absence> Oblist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> red_id;
    @FXML
    private TableColumn<?, ?> ref_nom;
    @FXML
    private TableColumn<?, ?> ref_prenom;
    @FXML
    private TableColumn<?, ?> ref_matiere;
    @FXML
    private TableColumn<?, ?> ref_date;
    @FXML
    private TableColumn<?, ?> ref_nbabsence;
   
    @FXML
    private TableView<Absence> Table;
    @FXML
    private TableColumn<?, ?> action; 
    @FXML
    private Button delete_absence;
    @FXML
    private Button mail;
 @FXML
 private Pane mainpane;
    @FXML
    private TextField filter;
    @FXML
    private Button buttonrecherche;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         red_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            ref_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ref_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            ref_matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            
           ref_date.setCellValueFactory(new PropertyValueFactory<>("date"));
             ref_nbabsence.setCellValueFactory(new PropertyValueFactory<>("nbAbsence"));
          
              action.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
              
                 // tableAFF.setItems(dataList);  
                  Table.setItems(Oblist); 
                      this.absence_table() ; 
        // TODO
    }    
    
    
     public void absence_table() {
          
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("select * from absence");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

             
              
       Oblist.add(new Absence(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"),rs.getString("matiere"),rs.getDate("date"), rs.getInt("nbAbsence")));

                
                  
                
            }            
            
        
                
                                
                
                 
          
        }
   
        catch (SQLException ex) {
        }
          
              
    }

    @FXML
    private void add_absence_btn(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
           Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AjouterAbsence.fxml"));
           mainpane.getChildren().add(parent);
           mainpane.toFront();
    }
 
    

    @FXML
    private void delete(ActionEvent event) {
        
        ObservableList<Absence>Oblist = FXCollections.observableArrayList();
        ServiceAbsence gp = new ServiceAbsence();
        for (Absence p   : Table.getItems()) {
            if (p.getCh_box().isSelected()) {
                Oblist.add(p);
            }

        }
        for (Absence p  : Oblist) {
            gp.supprimerAbsence(p.getId());
        }

        Table.getItems().clear();
        this.absence_table();
        
        
        
    }
     public Absence get_selected_row(){
         Absence a1=null;
            for (Absence a  : Table.getItems()) {
            if (a.getCh_box().isSelected()) {
                a1=a;
            }
            }
            return a1;
    
    } 

    @FXML
    private void update_absence_button(ActionEvent event) {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/ModifierAbsence.fxml"));
        try {
            Absence a1=get_selected_row();
            
            Parent parent=loader.load();
            ModifierAbsenceController upc=loader.getController(); 

            upc.setFields(a1);
        mainpane.getChildren().clear();
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
        } 
        catch (IOException ex) {
            Logger.getLogger(AfficherAbsenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void sendmail(ActionEvent event) {
        
         String s="" ; 
           ObservableList<Absence>Oblist = FXCollections.observableArrayList();
        for (Absence a  : Table.getItems()) {
            if (a.getCh_box().isSelected()) {
                Oblist.add(a);
                s=a.toString();
            }

        }
           try {
            JavaMainUtil.sendMail("houssem.hamdi@esprit.tn",s);
        } catch (Exception ex) {
            Logger.getLogger(AfficherNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void stat(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/statabsence.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
             ObservableList<Absence> Oblistt = FXCollections.observableArrayList();
             int i =-1; 
             String s =filter.getText() ; 
             try {
              i = Integer.parseInt(s) ; }
             catch (Exception e ){}
             PreparedStatement pt ; 
                  pt = con.prepareStatement("select * from absence where nom like '%"+s+"%' or nbAbsence ="+i);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) 
            {
            
       Oblistt.add(new Absence(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"),rs.getString("matiere"),rs.getDate("date"), rs.getInt("nbAbsence")));

            
            
            }
            Table.getItems().clear(); 
            Table.setItems(Oblistt);
             
             
             
             
        
              }
    
    
    
    
    
    
}
