/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Entites.Classe;
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
import javafx.scene.control.ComboBox;
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
public class AddSalleFXMLController implements Initializable {
    @FXML
    private ComboBox<String> bloc;
    @FXML
    private TextField num;
    @FXML
    private TextField id;
       @FXML
    private TableView<Salle> table;
    @FXML
    private TableColumn<Salle, Integer> idC;
    @FXML
    private TableColumn<Salle,Integer> numC;
    @FXML
    private TableColumn<Salle, String> blocC;

        private ObservableList<Salle> listSalle;
           @FXML
    private Button modifier;
 @FXML
    private Button ajouter;
   @FXML
    private Button supprimer;
   
 @FXML
    private Label erroe;
    @FXML
    private Label success;
    /**
     * Initializes the controller class.
     */
     Salle cst =new Salle();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bloc.getItems().addAll("A", "B", "C", "D", "E", "F");
/*         success.setVisible(false);
        erroe.setVisible(false);*/
       
        try {
            affichage();
             setcellValue();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AddSalleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddSalleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    } 
    
     public void getSalleId(ActionEvent e)throws Exception{
                SalleService sc = new SalleService();
			String sid=id.getText();
			int id=Integer.parseInt(sid);
			cst =sc.getById(id);
			bloc.setValue(cst.getBloc());
                        String snum=num.getText();
			int num=Integer.parseInt(snum);
                  
     }
  
    
    @FXML
    public void add() throws SQLException, ClassNotFoundException, AWTException{
        SalleService ss = new SalleService() ; 
      ss.addSalle(new Salle(bloc.getValue(),(Integer.parseInt(num.getText() ))));
        /*System.out.println("ok");*/
      affichage();
       Notifications11 n = new Notifications11();
        n.displayTray("Salle", "bien insérér"); 
    }
         public void affichage() throws SQLException, ClassNotFoundException {
        listSalle = FXCollections.observableArrayList();
                for (Salle se: new SalleService().getAll()) {
                    listSalle.add(se);
                }
        table.setItems(listSalle);
             System.out.println(listSalle);
        idC.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("id"));
        numC.setCellValueFactory(new PropertyValueFactory<Salle,Integer>("num"));
        blocC.setCellValueFactory(new PropertyValueFactory<Salle, String>("bloc"));
       
    }

  
          
     public void update(ActionEvent e)throws Exception{
            String sid=id.getText();
	int id=Integer.parseInt(sid);         
     
	String sbloc=bloc.getValue();
	  String snum=num.getText();
	int num=Integer.parseInt(snum); 
	
      cst.setId(id);
      cst.setBloc(sbloc);
	cst.setNum(num);
	
	
	
        
	 SalleService sc = new SalleService();
	
	int status=sc.updateSalle(cst);
	  affichage();
	 Notifications11 n = new Notifications11();
        n.displayTray("Salle", "bien modifier"); 
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Salle modifie");
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
      private void setcellValue() { //set the value in the cell 

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                Salle l = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                id.setText(Integer.toString(l.getId()));
                num.setText(Integer.toString(l.getNum()));
                bloc.setValue(l.getBloc());
               

            }
        });
    }
     public void delete(ActionEvent e)throws Exception{
    
	   	 SalleService sc = new SalleService();
	
	String sid=id.getText();
	int id=Integer.parseInt(sid);
	//DBinfo.delete(id);
	int status=sc.deleteSalle(id);
		
	  
	affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Salle", "bien supprimer"); 
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
