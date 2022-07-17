/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.FAQCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entites.categorieProposees;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class ProposerCategorieController implements Initializable {

    @FXML
    private JFXTextField propcat;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXButton retour;
 @FXML
    private Pane mainpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ValiderCategorie(ActionEvent event) {
        
        categorieProposees c=new categorieProposees();
        c.setNom(propcat.getText());
        FAQCrud p=new FAQCrud();
               p.ajouterCategorieProposees(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La nouvelle categorie sera ajoutée lorsque elle accumule plus que 1 upvotes!");

                alert.showAndWait();
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
      
        
     mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs1.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
        
        
    }
    
}
