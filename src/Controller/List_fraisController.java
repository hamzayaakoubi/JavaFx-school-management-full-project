/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import static com.jfoenix.svg.SVGGlyphLoader.clear;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entites.Frais;
import Entites.Paiment_eleve;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import service.EmailService;

import Services.ServiceFrais;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class List_fraisController implements Initializable {
@FXML
    private TableView<Frais> table;
    @FXML
    private TableColumn<Frais, String> frais;
    @FXML
    private TableColumn<Frais, Float> montant;
    @FXML
    private TableColumn<Frais, Integer> id;
    @FXML
    private TextField tfrais;
      @FXML
    private TextField tmontant;
           @FXML
    private TextField tid;
        @FXML
    private Button modifier;
 @FXML
    private Button ajouter;
   @FXML
    private Button supprimer
           ;
    private Stage stage;
    private Desktop desktop = Desktop.getDesktop();
      @FXML
    private Label msg;
    private FileChooser fileChooser;
    private File file;
    private AnchorPane anchorPane;
    @FXML
    private Pane mainpane;
    /**
     * Initializes the controller class.
     */
   Frais f=new Frais();
      ServiceFrais crudData = new ServiceFrais();
    ObservableList<Frais> listData;
    private String statusCode,statusClick;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        supprimer.setDisable(true);
        modifier.setDisable(true);
        frais.setCellValueFactory((TableColumn.CellDataFeatures<Frais, String> cellData) ->
                        cellData.getValue().fraisProperty());
           montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
      listData=crudData.getAll();
      table.setItems(listData);
     
                      
        
    
table.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
            supprimer.setDisable(false);
        modifier.setDisable(false);
        
    }
});
    }
public void onEdit() {
    // check the table's selected item and get selected item
    if (table.getSelectionModel().getSelectedItem() != null) {
        Frais selectedPerson = table.getSelectionModel().getSelectedItem();
        tfrais.setText(selectedPerson.getType_frais());
         String str1 = Double.toString(selectedPerson.getMontant());
      tmontant.setText(str1);
        String str1l = Integer.toString(selectedPerson.getId());

      tid.setText(str1l);
       
    } } 

@FXML
     public void InsertClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        if (isEmpty()){
        return;}
        else{
        crudData.InsertClub(tfrais.getText(),Float.parseFloat(tmontant.getText()));
        clear();
           
            actionRefresh(event);
        Notification n = new Notification();
        n.displayTray("Frais", "bien insérér");}
    }
@FXML
 public void update(ActionEvent event)throws ClassNotFoundException, SQLException, AWTException {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK ) try {
              
     	String sidd=tid.getText();
	int idt=Integer.parseInt(sidd);
	String sid=tmontant.getText();
	float idp=Float.parseFloat(sid);
	String type_frais=tfrais.getText();

f.setId(idt);
f.setFris(type_frais);
f.setMontant(idp);
        
	  crudData.updateFrais(f);
                populate();
                clear();
            
        modifier.setDisable(true);
         supprimer.setDisable(true);
  
                actionRefresh(event);
                Notification n = new Notification();
                n.displayTray("Frais", "bien modifié");
                populate();
               
            }     catch (Exception ex){JOptionPane.showMessageDialog(null,"Veuillez sélectionner une ligne du table");
            }	

}
     public void clear(){
        tid.clear();
        tmontant.clear();
        tfrais.clear();

        
        statusCode = "0";
    }
   
    public void showData(){
        listData =crudData.getAll();
        table.setItems(listData);
    }
@FXML
    public void actionRefresh(ActionEvent event) {
        clear();
        showData();
        //autoId();
    }

    public void populate() {
        String sql = "select * from frais";
        Connection con = MyDB.getInstance().getConnexion();
        listData.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                listData.add(new Frais(rs.getInt("id"), rs.getString("type_frais"),rs.getFloat("montant")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(List_fraisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(listData);
    }
    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }
    
    private void handleBrowser(ActionEvent event) {
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        try {
            desktop.open(file);
        } catch (IOException ex) {
            
        }
    }

@FXML
    public void Supp(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            crudData.deleteFrais((Integer.parseInt(tid.getText())));

            populate();
            clear();
             supprimer.setDisable(true);
      
            Notification n = new Notification();
            n.displayTray("Frais", "bien supprimé");
            populate();
            actionRefresh(event);
        }

    }
        private boolean validatorInt(String s) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le champ id n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }
                private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Frais invalide!");
            alert.showAndWait();
            return false;
        }

    }
        private boolean isEmpty() {
        if (validatorInt(tid.getText()) == false) {
            warning("Veuillez donner un id du frais valide !");
            return true;
        }
        if (validatorString(tfrais.getText())==false) {
            return true;
        }

//        if (dateCreation.getValue() == null || dateCreation.getValue().isBefore(LocalDate.now())) {
//            warning("Veuillez saisir une date de création valide  pour l'evenement!");
//            return true;
//        }
        if (validatorInt(tmontant.getText()) == false) {
            return true;
        }
        return false;
    }

        public void retourVersListe(ActionEvent event) throws IOException {
      mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/views/FXMLDocument.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }
            public void refresh() {
        table.setItems(crudData.getAll());
        System.out.println("Liste des paiments rafraichie.");
    }
      

    
             @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Admin\\Desktop\\frais.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(6);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("id_F");
       table.addCell("Frais");
       table.addCell("Montant");
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des Frais");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\Admin\\Documents\\NetBeansProjects\\BEFOREHOUSSEMrar\\123456\\src\\Images\\bank.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT `id`, `type_frais`, `montant` FROM `frais`");
       while(rs.next()){
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("type_frais"));

                   table.addCell(rs.getString("montant"));

       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }
         EmailService u=new EmailService();
    void sendaction(ActionEvent event) {
      //  u.sendEmail(email.getText(), sujet.getText(), message.getText());

    }  
}


    

