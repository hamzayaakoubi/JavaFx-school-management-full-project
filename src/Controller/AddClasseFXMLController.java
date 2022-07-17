package Controller;

import Entites.Classe;
import Entites.Enseignant;
import Entites.Notifications11;
import Services.ClasseService;
import Services.EnseignantService;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class AddClasseFXMLController implements Initializable {
    @FXML
    private ComboBox<String> annee;
    @FXML
    private ComboBox<String> specialite;
    @FXML
    private TextField groupe;
    private TextField spec;
    @FXML
    private TextField id;
    @FXML
    private Label erroe;
    private Label success;
           @FXML
    private Button modifier;
 @FXML
    private Button ajouter;
   @FXML
    private Button supprimer;
       @FXML
    private TableView<Classe> ensTable;
    @FXML
    private TableColumn<Classe, Integer> idCel;
    @FXML
    private TableColumn<Classe, String> anneeCel;
    @FXML
    private TableColumn<Classe, String> specialiteCel;
    @FXML
    private TableColumn<Classe, String> groupeCel;
        private ObservableList<Classe> listClasse;
         private ObservableList<String> list=FXCollections.observableArrayList();
          private ObservableList<String> liste=FXCollections.observableArrayList();
      Classe cct =new Classe();
    @FXML
    private Pane mainpane;
    /**
     * Initializes the controller class.
     */
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    annee.getItems().addAll("2020", "2021", "2022", "2023", "2024", "2025");
    specialite.getItems().addAll("technique", "economie", "gestion", "lettre", "science", "informatique");
    id.setDisable(true);
 
         try {
            affichage();
            setcellValue();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     private void setcellValue() { //set the value in the cell 

        ensTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                Classe l = ensTable.getItems().get(ensTable.getSelectionModel().getSelectedIndex());
                id.setText(Integer.toString(l.getId()));
                annee.setValue(l.getAnnee());
                specialite.setValue(l.getSpecialite());
                groupe.setText(l.getGroupe());
               

            }
        });
    }
      
    @FXML
    public void getClasseId(ActionEvent e)throws Exception{
                ClasseService cs = new ClasseService();
			String sid=id.getText();
			int id=Integer.parseInt(sid);
			cct =cs.getById(id);
			
                        annee.setValue(cct.getAnnee());
                        spec.setText(cct.getSpecialite());
                        groupe.setText(cct.getGroupe());
	
	
		}
   
    @FXML
    public void addClasse() throws SQLException, ClassNotFoundException, AWTException{
        if(annee.getValue().equals("")|| groupe.getText().equals("")|| specialite.getValue().equals("")){
                erroe.setVisible(true);
        }else{
               erroe.setVisible(false);
         success.setVisible(true);
        ClasseService cs = new ClasseService();
        cs.addClasse(new Classe(annee.getValue(),specialite.getValue(), groupe.getText()));
       affichage();
        Notifications11 n = new Notifications11();
        n.displayTray("Classe", "bien insérér");  
         
    }
    }
   public void affichage() throws SQLException, ClassNotFoundException {
        listClasse = FXCollections.observableArrayList();
                for (Classe cs : new ClasseService().getAllClasses()) {
                    listClasse.add(cs);
                }
        ensTable.setItems(listClasse);
        idCel.setCellValueFactory(new PropertyValueFactory<Classe, Integer>("id"));
        anneeCel.setCellValueFactory(new PropertyValueFactory<Classe, String>("annee"));
        specialiteCel.setCellValueFactory(new PropertyValueFactory<Classe, String>("specialite"));
        groupeCel.setCellValueFactory(new PropertyValueFactory<Classe, String>("groupe"));
      }

    @FXML
                    public void update(ActionEvent e)throws Exception{
                     
     
	String sid=id.getText();
	int id=Integer.parseInt(sid);
	String an=annee.getValue();
String group=groupe.getText();
String spect=specialite.getValue();

      cct.setId(id);
	cct.setAnnee(an);
	cct.setGroupe(group);
	cct.setSpecialite(spect);
	
        
	   ClasseService cs = new ClasseService();
	
	int status=cs.updateClasse(cct);
	  
	affichage();
          Notifications11 n = new Notifications11();
        n.displayTray("Classe", "bien modifier");  
	if(status>0) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("classe modifie");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setTitle("salle non modifie");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	

}
    @FXML
    public void delete(ActionEvent e)throws Exception{
    ClasseService cs = new ClasseService();
	String sid=id.getText();
	int id=Integer.parseInt(sid);
	//DBinfo.delete(id);
	int status=cs.deleteClasse(id);
		
	  affichage();
	  Notifications11 n = new Notifications11();
        n.displayTray("Classe", "bien supprimer");  
	if(status>0) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Supprission Effectuée");
		alert.setHeaderText("Info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setTitle("Error de Supprission");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
}

    @FXML
    private void retour(ActionEvent event) {
    }
          
    
}
