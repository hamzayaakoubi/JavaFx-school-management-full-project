/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Services.FAQCrud;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import Entites.categorieFAQ;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Entites.faq;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class AddFAQController implements Initializable {

    @FXML
    private JFXTextArea question_add;
    @FXML
    private JFXTextArea reponse_add;
    @FXML
    private JFXButton add;
    @FXML
    private JFXComboBox<categorieFAQ> categorieBox;
    @FXML
    private JFXButton Retour;
    @FXML
    private Pane mainpane;

    public JFXTextArea getQuestion_add() {
        return question_add;
    }

    public JFXTextArea getReponse_add() {
        return reponse_add;
    }

    public JFXButton getAdd() {
        return add;
    }

    public JFXComboBox<categorieFAQ> getCategorieBox() {
        return categorieBox;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       FAQCrud p=new FAQCrud();
       List<categorieFAQ> L = new ArrayList();
       L=p.afficherCategorieFAQ();
       
       for(int i=0;i<L.size();i++)
       {
           categorieBox.getItems().add(L.get(i));
       }
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        
            faq f=new faq();
            if (question_add.getText() == null || question_add.getText().trim().isEmpty() || reponse_add.getText() == null || reponse_add.getText().trim().isEmpty() || categorieBox.getSelectionModel().isEmpty())
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir les champs");

                alert.showAndWait();
            }
            else
            {
                f.setQuestion(getQuestion_add().getText());
                f.setReponse(getReponse_add().getText());
                f.setCategorie(getCategorieBox().getValue());
                FAQCrud p=new FAQCrud();
                p.ajouterFAQ(f);
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info Dialog");
                alert.setHeaderText(null);
                alert.setContentText("FAQ ajouté!");
                Optional <ButtonType> action = alert.showAndWait();
                 mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
            }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
            }
    }
    

