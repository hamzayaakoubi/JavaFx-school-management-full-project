/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Paiment_eleve;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.ServicePaiment_eleve;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Modifier_paiment_eleveController implements Initializable {
        Paiment_eleve p=new Paiment_eleve();
    ServicePaiment_eleve sv =new ServicePaiment_eleve();
 
 @FXML
    private ComboBox num_recu;
    @FXML
    private TextField nbre_mois;
    @FXML
    private TextField montant_totale;
    @FXML
    private TextField remise;
    @FXML
    private TextField cin;
      @FXML
    private TextField montant;
         @FXML
    private ComboBox<String> mode_paiment;
         @FXML
    private Pane mainpane;
         @FXML
    private ComboBox<String> mois;
               @FXML
    private ComboBox<String> mode_reglement;
                 @FXML
    private ComboBox<String> type_frais;
                          @FXML
    private TextField reste;
       @FXML
    private ComboBox<String> ch;
    
    //ObservableList<String> oblist;
    @FXML
    private Button update;
 @FXML
    private Button recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         mode_paiment.getItems().addAll("Par Mois","Anuelle");
          String value4 = (String) mode_paiment.getValue();
         mode_paiment.setEditable(true);
          mode_reglement.getItems().add("espece");
   mode_reglement.getItems().add("cheque");
   String value = (String) mode_reglement.getValue();
         mode_reglement.setEditable(true);
           type_frais.getItems().addAll("foyer","cantine","Assurence");
   String value1 = (String) type_frais.getValue();
         type_frais.setEditable(true);
           mois.getItems().addAll("janvier",
"février",
"mars",
"avril",
"mai",
"juin",
"juillet",
"août",
"septembre",
"octobre",
"novembre",
"décembre");
   String value12 = (String) mois.getValue();
         mois.setEditable(true);
                 String sql = "select * from finance ";
        Connection con = MyDB.getInstance().getConnexion();
        
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {

                num_recu.getItems().add(rs.getInt("num_recu"));
           

            }
        } catch (SQLException ex) {
           
        }
    }    
   		public void getPaimentId(ActionEvent e)throws IOException,ParseException{
			String sid=num_recu.getValue().toString();
			int id=Integer.parseInt(sid);
			p =sv.getPaimentParId(id);
			mode_reglement.setValue(p.getMode_reglement());
			montant_totale.setText(String.valueOf(p.getMontant_total()));
			remise.setText(String.valueOf(p.getRemise()));
			cin.setText(p.getCin());
			type_frais.setValue(p.getType_frais());
			montant.setText(String.valueOf(p.getMontant()));
			mois.setValue(p.getMois());
			mode_paiment.setValue(p.getMode_paiment());
			reste.setText(String.valueOf(p.getReste()));
		}
                
                public void update(ActionEvent e)throws IOException,ParseException, AWTException{
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK ) {
	String sid=num_recu.getValue().toString();
	int id=Integer.parseInt(sid);
	//String mode_reglemen=mode_reglement.getText();
//String type_frai=type_frais.getText();
//String mode_paimen=mode_paiment.getText();
String ci=cin.getText();
String montant_total=montant_totale.getText();
String r=remise.getText();
String res=reste.getText();
//String moi=mois.getText();
	//int cinn=Integer.parseInt(ci);
int remise=Integer.parseInt(r);
float m=Float.parseFloat(montant_total);
String mo=montant.getText();
Double montant=Double.parseDouble(mo);
float reste=Float.parseFloat(res);
	p.setNum_recu(id);
	p.setMode_reglement(mode_reglement.getValue());
	p.setMontant_total(m);
	p.setType_frais(type_frais.getValue());
	p.setRemise(remise);
	p.setMontant(montant);
	p.setCin(ci);
	p.setMois(mois.getValue());
	p.setMode_paiment(mode_paiment.getValue());
	p.setReste(reste);
        sv.Update(p);
          
                Notification n = new Notification();
                n.displayTray("Paiment", "bien modifié");
                   mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Liste_paiment.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
               
            }//  }     catch (Exception ev){    JOptionPane.showMessageDialog(null,"Veuillez sélectionner");}
                }
	

                
public void delete(ActionEvent e)throws IOException,ParseException, AWTException{
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

	sv.supprimerPaiement(Integer.parseInt(num_recu.getValue().toString()));
            
            Notification n = new Notification ();
            n.displayTray("Paiment", "bien supprimé");
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Liste_paiment.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        }
	
}
         public void retourVersListe(ActionEvent event) throws IOException {
       mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Liste_paiment.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }
}
