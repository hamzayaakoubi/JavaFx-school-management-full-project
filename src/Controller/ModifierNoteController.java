/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Note;
import Services.ServiceNote;
import java.io.IOException;
import java.net.URL;
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
public class ModifierNoteController implements Initializable {

    @FXML
    private Label ref_prenom;
    @FXML
    private Label ref_matiere;
    @FXML
    private Label ref_note;
    @FXML
    private TextField ref_nom_field;
    @FXML
    private TextField ref_prenom_field;
    @FXML
    private TextField ref_matiere_field;
    @FXML
    private TextField ref_note_field;
     public static Note n;
      @FXML
     private Pane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setFields(Note n) {
        
        ref_nom_field.setText(n.getNom());
        ref_prenom_field.setText(n.getPrenom());
        ref_matiere_field.setText(n.getMatiere());
        ref_note_field.setText(String.valueOf(n.getNote()));
      

        this.n = n;
    }

    private void newprod() {
         
        n.setNom(ref_nom_field.getText());
        n.setPrenom(ref_prenom_field.getText());
        n.setMatiere(ref_matiere_field.getText());
        n.setNote(Integer.parseInt(ref_note_field.getText()));
        
    }

    @FXML
    private void update_note_button(ActionEvent event) throws IOException {
        if(!valide()){} 
        else  {
        
      //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AfficherAbsence.fxml"));
        //  mainpane.getChildren().clear();
        newprod();
        ServiceNote Gp = new ServiceNote();
        Gp.modifier(n);
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherNote.fxml"));
        mainpane.getChildren().clear(); 
        mainpane.getChildren().add(parent);
        mainpane.toFront();
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("La modification fait avec succee yé jhonnnnnnn !");
            alert.showAndWait();}
      
//        AfficherNoteController AF= loader.getController();
//        Parent root=loader.load();
        
       
       /* try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
        
    }
    private boolean  validate(String s)
    
    
    {
    
    int i = -1  ; 
    try {
      i = Integer.parseInt(s) ;
      
      
    
    }
    catch (Exception e ) {} 
    if ( i == -1 ) {return false  ; } 
    if ( i>=0 && i<=20) {return true  ; }
    
    
    return false ; 
    
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
            alert.setContentText("Le champ note n'est pas valide !");
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
              if (validate(ref_note_field.getText()) == false) {
            warning("Veuillez donner une note  valide !");
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
