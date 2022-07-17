/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class MenuController_1 implements Initializable {
 @FXML
    private Button eleve;
    @FXML
    private Button prof;
        @FXML
    private Button frais;
         @FXML
         private Pane mainpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }   
 @FXML
    public void eleve(ActionEvent e) throws IOException {

        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Liste_paiment.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
}
           public void cc(ActionEvent e) throws IOException {
	
    
}
 @FXML
    public void prof(ActionEvent e) throws IOException {
	
          mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/paiment_prof.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
	
}
 @FXML
    public void frais(ActionEvent e) throws IOException {

          mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/List_frais.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
       
	
}
    
}
