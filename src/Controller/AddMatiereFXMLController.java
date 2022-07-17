/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Entites.Enseignant;
import Entites.Matiere;
import Entites.Notifications11;
import Entites.Salle;
import Services.ClasseService;
import Services.EnseignantService;
import Services.MatiereService;
import Services.SalleService;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddMatiereFXMLController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField coef;
      @FXML
    private TableView<Matiere> table;
    @FXML
    private TableColumn<Matiere, Integer> idCele;
    @FXML
    private TableColumn<Matiere,String> nomCele;
    @FXML
    private TableColumn<Matiere, Integer> coefCele;

        private ObservableList<Matiere> listMatiere;
             @FXML
    private Button modifier;
 @FXML
    private Button ajouter;
   @FXML
    private Button supprimer;
    private Label succes;
    @FXML
    private Label error;
Matiere mss =new Matiere();
    /**
     * Initializes the controller class.    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
        try {
            affichage();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AddMatiereFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddMatiereFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
    @FXML
  public void getMatireId(ActionEvent e)throws Exception{
                MatiereService cs = new MatiereService();
			String sid=id.getText();
			int id=Integer.parseInt(sid);
			mss =cs.getById(id);
			nom.setText(mss.getNom());
                     String s=Double.toString(mss.getCoef());
                     coef.setText(s);
	//Double coef=Double.parseDouble(scoef);
   }
  
  
    
	
    private void retour(ActionEvent event) {
        try {
            nom.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("MenuFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          
    @FXML
    public void addMatiere() throws SQLException, ClassNotFoundException, AWTException{
        if(nom.getText().equals("")||coef.getText().equals("")){
            error.setVisible(true);
            succes.setVisible(false);
        }
        else{
            error.setVisible(false);
            succes.setVisible(true);
        MatiereService ms =new MatiereService(); 
        ms.addMatiere(new Matiere(nom.getText(),Integer.parseInt(coef.getText()) ));
        	
        affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Matiere", "bien insérér"); 
        }
    }
        
                
      public void affichage() throws SQLException, ClassNotFoundException {
        listMatiere = FXCollections.observableArrayList();
                for (Matiere me: new MatiereService().getAllMatiere()) {
                    listMatiere.add(me);
                }
        table.setItems(listMatiere);
        idCele.setCellValueFactory(new PropertyValueFactory<Matiere, Integer>("id"));
        nomCele.setCellValueFactory(new PropertyValueFactory<Matiere, String>("nom"));
        coefCele.setCellValueFactory(new PropertyValueFactory<Matiere, Integer>("coef"));
          table.setOnMouseClicked((MouseEvent event)->{
          if(event.getClickCount()>1){
              onEdit();
          }
      });

    }
        public void onEdit() {
    // check the table's selected item and get selected item
    if (table.getSelectionModel().getSelectedItem() != null) {
        Matiere selectedPerson = table.getSelectionModel().getSelectedItem();
      
         String str1 = Double.toString(selectedPerson.getCoef());
      coef.setText(str1);
        String str1l = Integer.toString(selectedPerson.getId());
     nom.setText(selectedPerson.getNom());
      id.setText(str1l);
    } } 
    public void clear(){
        id.clear();
        nom.clear();
        coef.clear();
    }
    public void showData() throws Exception{
         MatiereService cs = new MatiereService();
        listMatiere=cs.getAllMatiere();
        table.setItems(listMatiere);
    }
    public void refrech(ActionEvent event) throws Exception {
        clear();
        
        showData();
    }
          
          
    @FXML
     public void update(ActionEvent e)throws Exception{
            String sid=id.getText();
	int id=Integer.parseInt(sid);         
     
	String snom=nom.getText();
	  String scoef=coef.getText();
	Double coeff=Double.parseDouble(scoef);
	
      mss.setId(id);
      mss.setNom(snom);
	mss.setCoef(coeff);
	
	
	
        
	 MatiereService ms =new MatiereService();
	
	int status=ms.updateMatiere(mss);
	  
	affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Matiere", "bien modifier"); 
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Matiere modifie");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur de modification");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	

} 
    @FXML
     public void delete(ActionEvent e)throws Exception{
    
	   MatiereService ms =new MatiereService(); 
	
	String sid=id.getText();
	int id=Integer.parseInt(sid);
	//DBinfo.delete(id);
	int status=ms.deleteMatiere(id);
		
	  affichage();
	 Notifications11 n = new Notifications11();
        n.displayTray("Matiere", "bien supprimer"); 
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Supprission Effectuée");
		alert.setHeaderText("Info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur de Supprission");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
}
    
    
}
