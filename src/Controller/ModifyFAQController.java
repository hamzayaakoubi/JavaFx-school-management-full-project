/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Services.FAQCrud;
import com.jfoenix.controls.JFXTextArea;
import Entites.categorieFAQ;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Entites.faq;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class ModifyFAQController implements Initializable {

    private int id;
    
    @FXML
    private JFXTextArea ModQuestion;
    @FXML
    private JFXTextArea ModReponse;
    @FXML
    private JFXButton Modify;
    @FXML
    private ComboBox<categorieFAQ> modCategorie;
    
     @FXML
    private Pane mainpane;
    
   
    public JFXButton getModify() {
        return Modify;
    }

    public void setModQuestion(String ModQuestion) {
        this.ModQuestion.setText(ModQuestion);
    }

     public void setModReponse(String ModReponse) {
        this.ModReponse.setText(ModReponse);
    }

    public void setModify(JFXButton Modify) {
        this.Modify = Modify;
    }

    public JFXTextArea getModQuestion() {
        return ModQuestion;
    }

    public JFXTextArea getModReponse() {
        return ModReponse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModCategorie(categorieFAQ modCategorie) {
        this.modCategorie.setValue(modCategorie);
    }

    public ComboBox<categorieFAQ> getModCategorie() {
        return modCategorie;
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
           modCategorie.getItems().add(L.get(i));
       }
    }    

    @FXML
    private void Change(ActionEvent event) throws IOException {
        faq f=new faq();
            f.setQuestion(getModQuestion().getText());
            f.setReponse(getModReponse().getText());
            f.setCategorie(getModCategorie().getValue());
            FAQCrud p=new FAQCrud();
            p.modifierFAQ(f,id);
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("info Dialog");
            alert.setHeaderText(null);
            alert.setContentText("FAQ modifié!");
            Optional <ButtonType> action = alert.showAndWait();
           
            
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
        
    }    
            
          
    }
    

