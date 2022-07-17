/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.FAQCrud;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class UpvoteCategorieController implements Initializable {

    @FXML
    private JFXButton AddButton;
    @FXML
    private Label categorie;
    @FXML
    private Label upvotes;
    @FXML
    private JFXButton Retour;
     @FXML
    private Pane mainpane;
    
    private int id;
    @FXML
    private Label categorie1;

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void setCategorie(String categorie) {
        this.categorie.setText(categorie);;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes.setText(Integer.toString(upvotes));
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Add(ActionEvent event) {
        FAQCrud p=new FAQCrud();
        p.UpvoteCategorie(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Upvote Success!");
                alert.showAndWait();
               
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/ListeCategorieProposees.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();  
         
         
        
    }
    
}
