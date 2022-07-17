/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.ModifyFAQController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Services.FAQCrud;
import com.jfoenix.controls.JFXTextArea;
import Entites.categorieFAQ;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class DetailsController implements Initializable {

    private int id;
    
    private AnchorPane details;
    @FXML
    private Label question;
    @FXML
    private Label reponse;
    @FXML
    private Label views;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton ModifyButton;
    @FXML
    private Label categorie;
    @FXML
    private JFXButton retour;
    @FXML
    private Pane mainpane;

    public void setQuestion(String question) {
        this.question.setText(question);
    }

    public void setReponse(String reponse) {
        this.reponse.setText(reponse);
    }

    public void setViews(int views) {
        this.views.setText(Integer.toString(views));
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategorie(String categorie) {
        this.categorie.setText(categorie);
    }

    public Label getCategorie() {
        return categorie;
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        question.setMaxWidth(200);
        question.setWrapText(true);
        reponse.setMaxWidth(200);
        reponse.setWrapText(true);
    }    

   @FXML
    private void Modify(ActionEvent event) throws IOException {
       mainpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ModifyFAQ.fxml"));
            Parent root = loader.load();
             mainpane.getChildren().add(root);
        mainpane.toFront();
            ModifyFAQController mc = loader.getController();
            mc.setModQuestion(question.getText());
            mc.setModReponse(reponse.getText());
            categorieFAQ t=new categorieFAQ(categorie.getText());
            mc.setModCategorie(t);
            mc.setId(getId());
            details.getScene().setRoot(root);
 
            
        
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        FAQCrud p=new FAQCrud();
        p.supprimerFAQ(id);
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("info Dialog");
        alert.setHeaderText(null);
        alert.setContentText("FAQ supprimé!");
        Optional <ButtonType> action = alert.showAndWait();
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
     
    
    
     mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    
    
    
}}
