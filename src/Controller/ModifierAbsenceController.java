/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Absence;
import Entites.Note;
import Services.ServiceAbsence;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class ModifierAbsenceController implements Initializable {

    @FXML
    private Label ref_nom;
    @FXML
    private Label ref_prenom;
    @FXML
    private Label ref_matiere;
    @FXML
    private Label ref_NbAbsence;
    @FXML
    private TextField ref_nom_field;
    @FXML
    private TextField ref_prenom_field;
    @FXML
    private TextField ref_matiere_field;
    @FXML
    private TextField ref_date_field;
    @FXML
    private TextField ref_NbAbsence_field;
    @FXML
    private Button updateAbsence;
     public static Absence a;
      @FXML
       private Pane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ref_date_field.setDisable(true);
        
        // TODO
    }    

    @FXML
    private void update_absence_button(ActionEvent event) throws IOException {
           //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AfficherAbsence.fxml"));
     //   AfficherAbsenceController AF= loader.getController();
      if(!valide()){} 
      else {
       newprod();
        ServiceAbsence Gp = new ServiceAbsence();
        Gp.modifierAbsence(a);
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherAbsence.fxml"));
        mainpane.getChildren().clear(); 
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
      }
    }
     
     
//     if (valide()) {} 
//     else { 
//        Parent root;
//        newprod();
//        ServiceAbsence Gp = new ServiceAbsence();
//        Gp.modifierAbsence(a);
//        try {
//           //root = loader.load();
//            Parent parent =  FXMLLoader.load(getClass().getResource("/Views/AfficherAbsence.fxml"));
//              mainpane.getChildren().clear();
//        mainpane.getChildren().add(parent);
//        mainpane.toFront();
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherAbsenceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }
        
        
        
    
    
     public void setFields(Absence a) {
        
        ref_nom_field.setText(a.getNom());
        ref_prenom_field.setText(a.getPrenom());
        ref_matiere_field.setText(a.getMatiere());
        ref_date_field.setText(a.getDate().toString());
         
        ref_NbAbsence_field.setText(String.valueOf(a.getNbAbsence()));
      

        this.a = a;
    }

    private void newprod() {
         
        a.setNom(ref_nom_field.getText());
        a.setPrenom(ref_prenom_field.getText());
          a.setMatiere(ref_matiere_field.getText());
          a.setDate(Date.valueOf(ref_date_field.getText()));
       
        a.setNbAbsence(Integer.parseInt(ref_NbAbsence_field.getText()));
        
    }
    
    private boolean validatorInt(String s) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le champ nbAbsence n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }
                private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("nom invalide!");
            alert.showAndWait();
            return false;
        }

    }
                      private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }
          private boolean validatorString1(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("prenom invalide!");
            alert.showAndWait();
            return false;
        }

    }
          private boolean  validate(String s)
    
    
    {
    
    int i = -1  ; 
    try {
      i = Integer.parseInt(s) ;
      
      
    
    }
    catch (Exception e ) {} 
    if ( i == -1 ) {return false  ; } 
    if ( i>=0 ) {return true  ; }
    
    
    return false ; 
    
    }
                    private boolean validatorString2(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("matiere invalide!");
            alert.showAndWait();
            return false;
        }

    }
                    private boolean valide() {
              if (validate(ref_NbAbsence_field.getText()) == false) {
            warning("Veuillez donner une nombre  valide !");
            return false;
        }
        if (validatorString(ref_nom_field.getText())==false) {
            warning("Veuillez donner un nom  valide !");
            return false;
        }
          if (validatorString1(ref_prenom_field.getText())==false) {
              warning("Veuillez donner un prenom  valide !");
            return false;
        }
            if (validatorString(ref_matiere_field.getText())==false) {
                warning("Veuillez donner une matiere  valide !");
            return false;
        }

        return true;
    }

    
    
    
}
