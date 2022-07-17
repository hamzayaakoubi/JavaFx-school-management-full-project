package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DataBase.connection;
import Services.Reservation;
import Utils.DataBase;
import Utils.Database2;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;import javafx.scene.layout.Pane;
;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TableView<Reservation> EmployerTable;
    @FXML
    private TableColumn<Reservation, String> colnom;
    @FXML
    private TableColumn<Reservation, String> colprenom;
    @FXML
    private TableColumn<Reservation, String> coladr;
    @FXML
    private TableColumn<Reservation, Integer> colcin;
    @FXML
    private TableColumn<Reservation, Integer> colserv;
     @FXML
    private TableColumn<Reservation, Integer> colid;
    
      @FXML
    private TextField id;
     
     @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField cin;
    @FXML
    private TextField choix;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifer;
    @FXML
    private Button supprimer;
    private Label Result;

    Connection c = DataBase.getInstance().getConnection();
    @FXML
    private Button choixx;
public AjouterReservationController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       
    Statement ste;
    
    ObservableList<Reservation> oblist = FXCollections.observableArrayList();
    @FXML
    private Pane mainpane;
    @FXML
    private Button annuler;
    public void populate() {
        String sql = "select * from reserv";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Reservation(rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"),
                        rs.getInt("cin"), rs.getInt("choix"),rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichReservController.class.getName()).log(Level.SEVERE, null, ex);
        }
        EmployerTable.setItems(oblist);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        cin.setDisable(true);
        
       String req = "select id,nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
        System.out.println("zdzdzddz");
         try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);
            System.out.println("erreeeee");

            while (rs1.next()) //  list.add(new Talentueux(rs.getString("Talent"),rs.getInt("NumTel"), rs.getString("Email"), rs.getString("DateNaissance")); //soit le nom de la colonne soit l'indice
            {
                 System.out.println(rs1.getString("nom"));
                 System.out.println(rs1.getString("prenom"));
                  System.out.println(rs1.getString("email"));
                                     System.out.println(rs1.getString("id"));

                   System.out.println(rs1.getString("cin"));
                    System.out.println(rs1.getString("username"));
                     System.out.println(rs1.getString("password"));
                System.out.println("yeziiii");

            // attempt to put it in a textfield
           nom.setText(rs1.getString("nom"));
           prenom.setText(rs1.getString("prenom"));
           adresse.setText(rs1.getString("email"));
           cin.setText(rs1.getString("id"));
     
           

           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
        populate();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coladr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colserv.setCellValueFactory(new PropertyValueFactory<>("choix"));
                colid.setCellValueFactory(new PropertyValueFactory<>("id"));


        EmployerTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
    nom.setText(EmployerTable.getSelectionModel().getSelectedItem().getNom());
                prenom.setText(EmployerTable.getSelectionModel().getSelectedItem().getPrenom());
                adresse.setText(EmployerTable.getSelectionModel().getSelectedItem().getAdresse());
                cin.setText(Integer.toString(EmployerTable.getSelectionModel().getSelectedItem().getCin()));
                choix.setText(Integer.toString(EmployerTable.getSelectionModel().getSelectedItem().getChoix()));
                               id.setText(Integer.toString(EmployerTable.getSelectionModel().getSelectedItem().getId()));


              }

        });

    }
       
       
        
  
    

    @FXML
    private void ModifEmployer(ActionEvent event) throws SQLException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reservation.ModifReservation(nom.getText(), prenom.getText(), adresse.getText(), Integer.parseInt(cin.getText()), Integer.parseInt(choix.getText()),Integer.parseInt(id.getText()));
            populate();
            Result.setText("Reservation Modifié");
        }
    }
        
        
        
        
    

    @FXML
    private void SuppEmployer(ActionEvent event) throws SQLException {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reservation.SuppReservation(Integer.parseInt(id.getText()));
            populate();
            Result.setText("Reservation Supprimé");
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void InsertEmployer(ActionEvent event) throws SQLException {
        
        Reservation.InsertReservation(nom.getText(), prenom.getText(), adresse.getText(), Integer.parseInt(cin.getText()), Integer.parseInt(choix.getText()));
        populate();
        Result.setText("Réservation  ajouté");
        
        
       

        
        
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        
        
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
       
        
        
        
        
        
        
        
        
        
        
      
        
        
        
        
    }

    @FXML
    private void choix(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("/Fxml/choixchambre.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
    
}
