/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Evenement;
import Entites.Note;
import Utils.Connexion;
import Utils.Database2;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class AffnoteeleveController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populate();
        ref_cin.setCellValueFactory(new PropertyValueFactory<>("id"));
            ref_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ref_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            ref_matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
            ref_note.setCellValueFactory(new PropertyValueFactory<>("note"));
           // action.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
                 // tableAFF.setItems(dataList);  
                  TableAff.setItems(Oblist); 
            
//               
            
                            populate();
 
//               
              
                
        
  
         
         
                   
    }
    
//         public void note_table() {
//          
//        PreparedStatement pt;
//        try {
//            pt = con.prepareStatement("select * from notes");
//            ResultSet rs = pt.executeQuery();
//            while (rs.next()) {
//
//             
//               Oblist.add(new Note(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"), rs.getString("matiere"), rs.getInt("note")));
//                
//                
//                  
//                
//            }            
//            
//        
//                
//                                
//                
//                 
//          
//        }
//   
//        catch (SQLException ex) {
//        }
//          
//              
//    
//    }
             public void populate() {
        String sql = "SELECT * FROM notes WHERE idEleve  in (select id from fos_user where demande='connected');";
        Connection con = Database2.connect();
        Oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Oblist.add(new Note(rs.getInt("IDD"), rs.getString("nom"), rs.getString("prenom"), rs.getString("matiere"), rs.getInt("note")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableAff.setItems(Oblist);
    }
    
}
