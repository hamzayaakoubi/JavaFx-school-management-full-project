/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.UpvoteCategorieController;
import Services.FAQCrud;
import com.jfoenix.controls.JFXButton;
import Entites.categorieFAQ;
import Entites.categorieProposees;
import Entites.faq;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class ListeCategorieProposeesController implements Initializable {

    @FXML
    private TableView<categorieProposees> listCategorie;
    @FXML
    private JFXButton Retour;
     @FXML
    private Pane mainpane;
    @FXML
    private JFXButton Retour1;
  
            
           

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TableColumn nomColumn = new TableColumn("Nom");
       nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       TableColumn upvotesColumn = new TableColumn("Upvotes");
       upvotesColumn.setCellValueFactory(new PropertyValueFactory<>("Upvotes"));
       
       upvotesColumn.setStyle("-fx-alignment: CENTER;");
       nomColumn.setStyle("-fx-alignment: CENTER;");
       
       listCategorie.getColumns().addAll(nomColumn, upvotesColumn);
       listCategorie.setEditable(false);

       FAQCrud c=new FAQCrud();
       List<categorieProposees> L = new ArrayList();
       L=c.afficherCategorie();
       for(int i=0;i<L.size();i++)
       {
           if(L.get(i).getUpvotes()>=1)
           {
               categorieFAQ f=new categorieFAQ();
               f.setNom(L.get(i).getNom());
               c.ajouterCategorie(f,L.get(i).getId());
           }
           else
               listCategorie.getItems().add(L.get(i));
           
       }
        
    }    

    @FXML
    private void Retour(ActionEvent event) throws IOException {
       
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
        
    }

    @FXML
    private void Upvote(MouseEvent event) throws IOException {
        
        mainpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpvoteCategorie.fxml"));
            Parent root = loader.load();
             mainpane.getChildren().add(root);
        mainpane.toFront();
            UpvoteCategorieController uc = loader.getController();
            FAQCrud p=new FAQCrud();
            uc.setId(listCategorie.getSelectionModel().getSelectedItem().getId());
            uc.setCategorie(listCategorie.getSelectionModel().getSelectedItem().getNom());
            uc.setUpvotes(listCategorie.getSelectionModel().getSelectedItem().getUpvotes());
            Retour.getScene().setRoot(root);
        
    }

    @FXML
    private void Retour1(ActionEvent event) throws IOException {
        
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
        
        
        
        
        
    }
        
        
           
        
    
    
    
    
    
    
}
