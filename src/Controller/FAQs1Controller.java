/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.DetailsController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import Services.FAQCrud;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Entites.faq;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class FAQs1Controller implements Initializable {

    private AnchorPane test;
    @FXML
    private TableView<faq> listView;

    
       
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXButton Stats;
    @FXML
    private JFXButton Lcat;
 @FXML
    private Pane mainpane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       TableColumn faqColumn = new TableColumn("Question");
       faqColumn.setCellValueFactory(new PropertyValueFactory<>("Question"));
       TableColumn categorieColumn = new TableColumn("Categorie");
       categorieColumn.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
       TableColumn viewsColumn = new TableColumn("Views");
       viewsColumn.setCellValueFactory(new PropertyValueFactory<>("Views"));
       faqColumn.setMinWidth(380.0);
       viewsColumn.setSortable(false);
       faqColumn.setSortable(false);
       categorieColumn.setSortable(false);
       
       viewsColumn.setStyle("-fx-alignment: CENTER;");
       categorieColumn.setStyle("-fx-alignment: CENTER;");
       
       listView.getColumns().addAll(faqColumn, categorieColumn, viewsColumn);
       listView.setEditable(false);

       FAQCrud c=new FAQCrud();
       List<faq> L = new ArrayList();
       L=c.afficherFAQ();
       for(int i=0;i<L.size();i++)
       {
           listView.getItems().add(L.get(i));
       }

               }

    @FXML
    private void Show(MouseEvent event) throws IOException {
       mainpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Details.fxml"));
            Parent root = loader.load();
            
            mainpane.getChildren().add(root);
        mainpane.toFront();
        
            DetailsController dc = loader.getController();
            FAQCrud p=new FAQCrud();
            p.AddViewFAQ(listView.getSelectionModel().getSelectedItem(), listView.getSelectionModel().getSelectedItem().getId());
            dc.setQuestion(listView.getSelectionModel().getSelectedItem().getQuestion());
            dc.setReponse(listView.getSelectionModel().getSelectedItem().getReponse());
            dc.setViews(listView.getSelectionModel().getSelectedItem().getViews()+1);
            dc.setId(listView.getSelectionModel().getSelectedItem().getId());
            dc.setCategorie(listView.getSelectionModel().getSelectedItem().getCategorie().getNom());
            test.getScene().setRoot(root);
        
        
        
        
        
    }

   /* private void Add(ActionEvent event) throws IOException {
        
           
           
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/AddFAQ.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
           
      
           
           
    }*/

    private void TopFAQs(ActionEvent event) throws IOException {
       
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AddFAQ.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
            }
        
     


    @FXML
    private void Recherche() {
        
        
       FAQCrud c=new FAQCrud();
       List<faq> L = new ArrayList(); 
       L=c.afficherFAQ();
       ObservableList<faq> data=FXCollections.observableArrayList();
       for(int i=0;i<L.size();i++)
       {
           listView.getItems().add(L.get(i));
           data.add(L.get(i));
       }

       FilteredList<faq> filteredData=new FilteredList<>(data,e->true); 
       recherche.textProperty().addListener((observableValue,oldValue,newValue)->{
			filteredData.setPredicate((Predicate<? super faq>)f->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(f.getCategorie().getNom().toLowerCase().contains(lowerCaseFilter)){
					return true;
                                }
				return false;
			});
		});
		SortedList<faq> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(listView.comparatorProperty());
                
		listView.setItems(sortedData);
	}

    @FXML
    private void Stats(ActionEvent event) throws IOException {
       
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/PieChart.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
        
    }

    private void ProposerCategorie(ActionEvent event) throws IOException {
       
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/ProposerCategorie.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
    }

    @FXML
    private void ListeCategorieProposees(ActionEvent event) throws IOException {
        
         
            mainpane.getChildren().clear();
           Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/ListeCategorieProposees.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
      
         
    }
}