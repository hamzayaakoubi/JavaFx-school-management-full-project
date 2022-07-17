/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Entites.Classe;
import Entites.EmploiTemps;
import Entites.Enseignant;
import Entites.Notifications11;
import Services.EnseignantService;
import Services.MatiereService;
import Services.TempService;
import com.mysql.jdbc.EscapeTokenizer;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddEnFXMLController implements Initializable {
    @FXML
    private TextField nom;
      @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField prenom;
    private Label succes;
    @FXML
    private Label error;
    @FXML
    private TableView<Enseignant> ensTable;
    @FXML
    private TableColumn<Enseignant, Integer> idCell;
    @FXML
    private TableColumn<Enseignant, String> nomCell;
    @FXML
    private TableColumn<Enseignant, String> prenomCell;
    @FXML
    private TableColumn<Enseignant, String> emailCell;
        private ObservableList<Enseignant> listEnseignant;
Enseignant en =new Enseignant();
    @FXML
    private Pane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        succes.setVisible(false);
        error.setVisible(false);
        try {
            affichage();
             setcellValue();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AddEnFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddEnFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setcellValue() { //set the value in the cell 

        ensTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                Enseignant l = ensTable.getItems().get(ensTable.getSelectionModel().getSelectedIndex());
                id.setText(Integer.toString(l.getId()));
                nom.setText(l.getNom());
                prenom.setText(l.getPrenom());
                email.setText(l.getEmail());
               

            }
        });
    }
    @FXML
       public void getEnseignatId(ActionEvent e)throws Exception{
                EnseignantService es = new EnseignantService(); 
			String sid=id.getText();
			int id=Integer.parseInt(sid);
			en=es.getById(id);
			nom.setText(en.getNom());
                        prenom.setText(en.getPrenom());
                        email.setText(en.getEmail());
       }
    @FXML
    public void addEnseignant() throws SQLException, ClassNotFoundException, AWTException{
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(email.getText());
        if(nom.getText().equals("")||email.getText().equals("")||prenom.getText().equals("")){
        error.setVisible(true);
       succes.setVisible(false);

        }else if(!controler.matches()) {
            error.setVisible(true);
        error.setText("verifier votre mail");
        }
       else
        {
            error.setVisible(false);
            succes.setVisible(true);

        EnseignantService es = new EnseignantService(); 
        es.addEnseignant(new Enseignant(nom.getText(), prenom.getText(), email.getText()));
        affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Enseignant", "bien insérér");  
         
        }
    }

    private void retour(ActionEvent event) {
        try {
            nom.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("MenuFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            public void affichage() throws SQLException, ClassNotFoundException {
        listEnseignant = FXCollections.observableArrayList();
                for (Enseignant ens : new EnseignantService().getAllEnseignant()) {
                    listEnseignant.add(ens);
                }
        ensTable.setItems(listEnseignant);
        idCell.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("id"));
        nomCell.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nom"));
        prenomCell.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("prenom"));
        emailCell.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("email"));

    }
    @FXML
              public void update(ActionEvent e)throws Exception{
            String sid=id.getText();
	int id=Integer.parseInt(sid);         
     
	String snom=nom.getText();
	
	String pren=prenom.getText();
        String mail=email.getText();
      en.setId(id);
      en.setNom(snom);
	en.setPrenom(pren);
	en.setEmail(mail);
	affichage();
	
        
	   EnseignantService es = new EnseignantService();
	
	int status=es.updateEnseignant(en);
	  
	affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Enseignant", "bien modifier"); 
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Data UPDATED");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data not updated");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	

} 
    @FXML
    public void delete(ActionEvent e)throws Exception{
    
	   EnseignantService es = new EnseignantService();
	String sid=id.getText();
	int id=Integer.parseInt(sid);
	//DBinfo.delete(id);
     
	int status=es.deleteEnseignant(id);
		
	  affichage();
	 Notifications11 n = new Notifications11();
        n.displayTray("Enseignant", "bien supprimer"); 
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Supprission Effectuée");
		alert.setHeaderText("Info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error de Supprission");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
}
}
  
    

