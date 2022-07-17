package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DataBase.connection;
import Entites.Notifications;
import Services.Reservation;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class AffichReservController implements Initializable {

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
    @FXML
    private Label Result;

    
    
    ObservableList<Reservation> oblist = FXCollections.observableArrayList();
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
    private void ModifEmployer(ActionEvent event) throws SQLException, AWTException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reservation.ModifReservation(nom.getText(), prenom.getText(), adresse.getText(), Integer.parseInt(cin.getText()), Integer.parseInt(choix.getText()),Integer.parseInt(id.getText()));
            populate();
            Result.setText("Reservation Modifié");
              Notifications n = new Notifications();
                n.displayTray("reservation", "bien modifié");
        }
    }
        
        
        
        
    

    @FXML
    private void SuppEmployer(ActionEvent event) throws SQLException, AWTException {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reservation.SuppReservation(Integer.parseInt(id.getText()));
            populate();
            Result.setText("Reservation Supprimé");
             Notifications n = new Notifications();
                n.displayTray("reservation", "bien supprimé");
        }
        
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void InsertEmployer(ActionEvent event) throws SQLException, AWTException {
        
        Reservation.InsertReservation(nom.getText(), prenom.getText(), adresse.getText(), Integer.parseInt(cin.getText()), Integer.parseInt(choix.getText()));
        populate();
        Result.setText("Réservation  ajouté");
         Notifications n = new Notifications();
                n.displayTray("reservation", "bien ajouté");
        
        
        
        
        
        
    }
    
}
