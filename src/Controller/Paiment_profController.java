/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.jfoenix.controls.JFXTextField;
import Entites.Frais;
import Entites.Paie_Prof;
import Entites.Paiment_eleve;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Services.ServiceFinanciere;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Paiment_profController implements Initializable {
 @FXML
 private Pane mainpane;
    @FXML
    private TextField cin;
    @FXML
    private TextField montant;
     
    @FXML
    private TextField nbre_heure;
     @FXML
    private TextField salaire_heure;
    @FXML
    private Button save;
    @FXML
    private TableView<Paie_Prof> table;
    @FXML
    private TableColumn<Paie_Prof, Integer> tid;
    @FXML
    private TableColumn<Paie_Prof, String> tcin;
    @FXML
    private TableColumn<Paie_Prof, String> nom;
    @FXML
    private TableColumn<Paie_Prof, String> prenom;
     @FXML
    private TableColumn<Paie_Prof, Float> tnbre_heure;
      @FXML
    private TableColumn<Paie_Prof, Float> tsalaire_heure;
       @FXML
    private TableColumn<Paie_Prof, Double> tmontant;
           @FXML
    private TableColumn<Paie_Prof, String> date;
    @FXML
    private TableColumn action;
    @FXML
    private TextField serch;
   
    ServiceFinanciere sf=new ServiceFinanciere();
    ObservableList<Paie_Prof> listData= FXCollections.observableArrayList();
    private String statusCode,statusClick;
    ObservableList<Paie_Prof> listDelete;
    @FXML
    private Button refrech;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         save.setDisable(true);
        
         tid.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Integer>("num_paiment"));
	tcin.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Cin"));
	nom.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Nom"));
	prenom.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Prenom"));
	tnbre_heure.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Float>("nbre_heure"));
	tsalaire_heure.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Float>("salaire_heure"));
   tmontant.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Double>("Montant"));
   date.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("date"));
    action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        action.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(table);
            }
        });
 listData=sf.getListePaiement();
        statusCode = "0";
        statusClick = "0";
        showData();
        autoId();
  
     FilteredList<Paie_Prof> filteredData = new FilteredList<>(listData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        serch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }else if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paie_Prof> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
       //  mainpane.getChildren().add(table);
        table.setItems(sortedData);
    
    }
    public void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
   
    public void clear(){
        cin.clear();
        montant.clear();
        nbre_heure.clear();
        salaire_heure.clear();
        
        statusCode = "0";
    }
   
    public void showData(){
        listData = sf.getListePaiement();
        table.setItems(listData);
    }
   
    public void autoId(){
       Paie_Prof m = new Paie_Prof();
    }

    
    @FXML
    public void actionSave(ActionEvent event) throws AWTException {
        Paie_Prof m = new Paie_Prof();
          String s=cin.getText();
           String e=nbre_heure.getText();
           String ee=salaire_heure.getText();
           String eee=montant.getText();
int es=Integer.parseInt(s);
float p=Float.parseFloat(e);
 float pp=Float.parseFloat(ee); 
  Double ppp=Double.parseDouble(eee);
  m.setNum_paiment(es);
      m.setNbre_heure(p);
      m.setSalaire_heure(pp);
      m.setMontant(ppp);
     sf.Update(m);
        dialog(Alert.AlertType.INFORMATION, "Data has saved");
        showData();
         save.setDisable(true);
      
        clear();
        autoId();
         Notification n = new Notification();
        n.displayTray("Paiment", "bien Modifier");
       
    }

  
   public void tableDataClick() {
   if (table.getSelectionModel().getSelectedItem() != null) {
        Paie_Prof selectedPerson = table.getSelectionModel().getSelectedItem();
         String str1 = Double.toString(selectedPerson.getMontant());
      montant.setText(str1);
      String nb = Float.toString(selectedPerson.getNbre_heure());
      nbre_heure.setText(nb);
      String h =Float.toString(selectedPerson.getSalaire_heure());
      salaire_heure.setText(h);
      String c = Integer.toString(selectedPerson.getNum_paiment());
      cin.setText(c);
   
   }
   }
    public void actionSearch(KeyEvent event) {
        listData = sf.likeByName(serch.getText());
        table.setItems(listData);
    }

    @FXML
    public void actionRefresh(ActionEvent event) {
        clear();
        showData();
        //autoId();
    }
   
    public class ButtonCell  extends TableCell<Object, Boolean>  {
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);

            cellButtonDelete.setOnAction((ActionEvent t) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                table.getSelectionModel().select(row);
             String i=  tid.getText();
             int id=Integer.parseInt(i);
             sf.supprimerPaiement(id);
               showData();
                clear();
                autoId();
                Notification n = new Notification();
                try {
                    n.displayTray("Paiment", "bien Supprimer");
                } catch (AWTException ex) {
                    Logger.getLogger(Paiment_profController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
            //cell edit
            cellButtonEdit.setOnAction((ActionEvent eventt) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                table.getSelectionModel().select(row);
                 save.setDisable(false);
        
              
        tableDataClick();
});  
                statusClick = "0";
        }

        
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
    @FXML
        public void add(ActionEvent e) throws IOException {
	  mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/Add_paie_prof.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
}
    @FXML
               public void stat(ActionEvent e) throws IOException {
	  mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/Pie_prof.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
}
    

   
  
   
  
   
}
 
 
    

