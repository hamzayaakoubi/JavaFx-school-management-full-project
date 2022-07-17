/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Club;
import Entites.Notifications;
import Services.ServiceClub;
import Utils.Database2;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class InfoController implements Initializable {

    @FXML
    private Pane mainpane;
    @FXML
    private Label msg;
    @FXML
    private TextField emailClub;
    @FXML
    private TextField imageClub;
    @FXML
    private Button modifier;
    @FXML
    private TextField nomClub;
    @FXML
    private TextField idClub;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private TextField idPresident;
    @FXML
    private ImageView imgview;
    @FXML
    private JFXButton choose;
    @FXML
    private Button membre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
        
        idClub.setDisable(true);
idPresident.setDisable(true);
        // TODO
    }    

  public void populate() {
        String sql = "SELECT * FROM club WHERE idPresident in (select id from fos_user where demande='connected');";
        Connection con = Database2.connect();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                idClub.setText(Integer.toString(rs.getInt("idClub")));
                nomClub.setText(rs.getString("nomClub")); 
                dateCreation.setValue(rs.getDate("dateCreation").toLocalDate());
                emailClub.setText(rs.getString("emailClub"));
                idPresident.setText(rs.getString("idPresident"));
                imageClub.setText(rs.getString("imageClub"));
                Image image = new Image(imageClub.getText());
                imgview.setImage(image);

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void ModifClub(ActionEvent event) {
           if (isEmpty()) {
            return;
        } else { 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK ) try {
                ServiceClub.ModifClub(Integer.parseInt(idClub.getText()), nomClub.getText(), java.sql.Date.valueOf(dateCreation.getValue()).toString(), emailClub.getText(), Integer.parseInt(idPresident.getText()), imageClub.getText());
                ServiceClub.ModifUser(Integer.parseInt(idPresident.getText()),"");
//                ServiceClub.ModifUser2(id_president(), "");
                
                populate();
                Notifications n = new Notifications();
                n.displayTray("Club", "bien modifié");
                populate();
            }     catch (Exception e){    JOptionPane.showMessageDialog(null,"Veuillez sélectionner une ligne du table");}
 
            
        
    }
    }
     private int id_president() {
        Connection con = Database2.connect();

        int k = 0;

        try {
            PreparedStatement pt = con.prepareStatement("select id from fos_user where username=?");

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                k = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }

    @FXML
    private void importerimage(ActionEvent event) {
         FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {

            imageClub.setText(selectedfile.toURI().toString());
            Image image = new Image(imageClub.getText());
            imgview.setImage(image);

        }

    }
    
    
    
    private boolean validatorMail(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le champ Mail n'est pas valide !");
            alert.showAndWait();
            return false;
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
            alert.setContentText("Le champ idClub n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }
        
     private boolean validatorInt2(String s) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("id president invalide !");
            alert.showAndWait();
            return false;
        }

    }
     
     
    
          private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z é]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("nom club invalide!");
            alert.showAndWait();
            return false;
        }

    }
          

    private boolean isEmpty() {
        if (validatorInt(idClub.getText()) == false) {
            warning("Veuillez donner un id du club valide !");
            return true;
        }
        if (validatorString(nomClub.getText())==false) {
            return true;
        }

//        if (dateCreation.getValue() == null || dateCreation.getValue().isBefore(LocalDate.now())) {
//            warning("Veuillez saisir une date de création valide  pour l'evenement!");
//            return true;
//        }
        if (validatorMail(emailClub.getText()) == false) {
            return true;
        }
        if (validatorInt2(idPresident.getText())==false ) {
            
            return true;
        }

        if (imageClub.getText() == null || imageClub.getText().trim().isEmpty()) {
            warning("Veuillez choisir une image pour le club !");
            return true;
        }

        return false;
    }
   private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }

    @FXML
    private void membre(ActionEvent event) throws IOException {
        
        
          mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/ListeMembreClub.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
}
