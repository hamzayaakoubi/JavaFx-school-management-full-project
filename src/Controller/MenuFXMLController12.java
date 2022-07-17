/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuFXMLController12 implements Initializable {
    @FXML
    private Button addseance;
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
    private void addSeance(ActionEvent event) throws IOException {
                    //addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("AddSeanceFXML.fxml")));
                     mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/AddSeanceFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

    }

    @FXML
    private void addMatiere(ActionEvent event) throws IOException {
                            //addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("addMatiereFXML.fxml")));
                            mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/addMatiereFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    

    private void addEnseignant(ActionEvent event) throws IOException {
                           // addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("AddEnseignantFXML.fxml")));
                            mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/AddEnseignantFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

    }

    @FXML
    private void addSalle(ActionEvent event) throws IOException {
                            //addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("AddSalleFXML.fxml")));
                             mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/AddSalleFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    

    @FXML
    private void addClasse(ActionEvent event) throws IOException {
                            //addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("AddClasseFXML.fxml")));
                             mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/AddClasseFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

    }

    @FXML
    private void listSeance(ActionEvent event) throws IOException {
                           // addseance.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("EmploiAdminXML.fxml")));
                            mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/EmploiAdminXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

    }
    
}
