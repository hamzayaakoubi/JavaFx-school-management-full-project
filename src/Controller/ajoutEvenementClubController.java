/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Evenement;
import Entites.Notifications;
import Services.ServiceEvenement;
import Utils.DataBase;
import Utils.Database2;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class ajoutEvenementClubController implements Initializable {

    @FXML
    private TextField desi;
    @FXML
    private TextField type;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker dateDb;
    @FXML
    private DatePicker dateFi;
    @FXML
    private TextField nbrPlaces;
    @FXML
    private TextField etatEv;
    @FXML
    private TextArea desc;
    @FXML
    private TextField id;
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    

Connection c = DataBase.getInstance().getConnection();
 public ajoutEvenementClubController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
          Statement ste;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setVisible(false);
             String req = "select idClub from club where idPresident in (select id from fos_user where demande='connected') ";
       
        try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);

            while (rs1.next())
            {
                
                      id.setText(rs1.getString("idClub"));


           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }

        dateDb.setValue(LocalDate.now());
        dateFi.setValue(LocalDate.now());
        etatEv.setDisable(true);
        etatEv.setText("Pas encore accepté");

    }



    @FXML
    private void InsertEvent(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        if (isEmpty()) {
            return;
        } else {

            ServiceEvenement.InsertEvenement(Integer.parseInt(id.getText()), desi.getText(), Integer.parseInt(nbrPlaces.getText()), "",
                    java.sql.Date.valueOf(dateDb.getValue()).toString(), java.sql.Date.valueOf(dateFi.getValue()).toString(), desc.getText(), type.getText());
        }
        Notifications n = new Notifications();
        n.displayTray("Evenement", "bien insérér");
        clearFields();

    }

    public void clearFields() {
        desi.clear();
        nbrPlaces.clear();
        dateDb.setValue(LocalDate.now());
        dateFi.setValue(LocalDate.now());
        desc.clear();
        type.clear();

    }

    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }
    
    
    private boolean validatorInt(String s) {
        Pattern p = Pattern.compile("[1-9]+[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le nombres de place n'est pas valide !");
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
            alert.setContentText("designation invalide!");
            alert.showAndWait();
            return false;
        }

    }
  private boolean validatorString2(String s) {
        Pattern p = Pattern.compile("[a-zA-Z. _-]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("description invalide!");
            alert.showAndWait();
            return false;
        }

    }
    private boolean validatorString3(String s) {
        Pattern p = Pattern.compile("[a-zA-Z._-]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("type invalide!");
            alert.showAndWait();
            return false;
        }

    }
    private boolean isEmpty() {
      
        if (validatorString(desi.getText()) == false) {
            
            return true;
        }
        if (validatorInt(nbrPlaces.getText())==false) {

            return true;
        }
        if (dateDb.getValue() == null || dateDb.getValue().isBefore(LocalDate.now())) {
            warning("Veuillez saisir une date de début valide  pour l'evenement!");
            return true;
        }
        if (dateFi.getValue() == null || dateFi.getValue().isBefore(dateDb.getValue())) {
            warning("Veuillez saisir une date de fin valide  pour l'evenement!");
            return true;
        }
        if (validatorString2(desc.getText()) == false) {
            return true;
        }
        if (validatorString3(type.getText()) == false) {
            return true;
        }

        return false;
    }

}
