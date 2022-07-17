/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Club;
import Entites.Club_Eleve;
import Entites.Notifications;
import Services.ServiceClub;
import Services.ServiceClubEleve;
import Utils.Database2;
import com.jfoenix.controls.JFXButton;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class MembreController implements Initializable {

    @FXML
    private Pane mainpane;
    @FXML
    private TableView<Club_Eleve> clubTable;
    @FXML
    private Label msg;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField nomClub;
    @FXML
    private TableColumn<Club_Eleve, Integer> idmembrecol;
    @FXML
    private TableColumn<Club_Eleve, Integer> etatcol;
    @FXML
    private TextField idMembre;
    ObservableList<Club_Eleve> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField idClub;
    @FXML
    private TableColumn<Club_Eleve, Integer> etatcol1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        supprimer.setDisable(true);
        modifier.setDisable(true);
        idMembre.setDisable(true);
        idClub.setVisible(false);
        populate();
          idmembrecol.setCellValueFactory(new PropertyValueFactory<>("id_User"));

            etatcol1.setCellValueFactory(new PropertyValueFactory<>("idClub"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
 
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nomClub.setText(clubTable.getSelectionModel().getSelectedItem().getEtat());
                idMembre.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId_User()));
                idClub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdClub()));
                supprimer.setDisable(false);
                modifier.setDisable(false);

            }

        });
        // TODO
    }    


    @FXML
    private void SuppClub(ActionEvent event) throws SQLException, AWTException {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ServiceClubEleve.SuppClub1(Integer.parseInt(idClub.getText()),Integer.parseInt(idMembre.getText()));
    

            populate();
            Notifications n = new Notifications();
            n.displayTray("Membre", "bien supprimé");
            populate();
                   modifier.setDisable(true);
                supprimer.setDisable(true);
        }
    }
 public void clearFields() {
nomClub.clear();      

    }    @FXML
    private void ModifClub(ActionEvent event) throws AWTException, SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK ) try {
                ServiceClubEleve.ModifMembre(nomClub.getText(),Integer.parseInt(idClub.getText()),Integer.parseInt(idMembre.getText()));
             


                populate();
                       modifier.setDisable(true);
                supprimer.setDisable(true);
                clearFields();
                Notifications n = new Notifications();
                n.displayTray("Membre", "bien modifié");
                populate();
       
    }catch (Exception e){    System.out.println("Erreur de suppression" + e);
            throw e;}}
    
    
    
    
    
    
    
 public void populate() {
        String sql = "select * from club_eleve where idclub in (SELECT idClub FROM club WHERE idPresident in (select id from fos_user where demande='connected'));";  
   Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Club_Eleve(rs.getInt("idClub"), rs.getInt("idUser"), rs.getString("etat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clubTable.setItems(oblist);
 
 
 }
    
}
