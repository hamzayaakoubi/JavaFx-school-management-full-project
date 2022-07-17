/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Notifications;
import Entites.User;
import Services.UserService;
import Utils.DataBase;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class ProfilEnseignantController implements Initializable {

       
    Statement ste;

    Connection c = DataBase.getInstance().getConnection();
 public ProfilEnseignantController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField username;
    @FXML
    private Label nom1;
    @FXML
    private Label prenom1;
    @FXML
    private Label mail1;
    @FXML
    private Label cin1;
    @FXML
    private Label username1;
    @FXML
    private Label password1;
      @FXML
    private Pane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cin.setDisable(true);
          String req = "select nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
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
                   System.out.println(rs1.getString("cin"));
                    System.out.println(rs1.getString("username"));
                     System.out.println(rs1.getString("password"));
                System.out.println("yeziiii");

            // attempt to put it in a textfield
           nom.setText(rs1.getString("nom"));
           prenom.setText(rs1.getString("prenom"));
           adresse.setText(rs1.getString("email"));
           cin.setText(rs1.getString("cin"));
           username.setText(rs1.getString("username"));
           password.setText(rs1.getString("password"));
           

           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
    }    
 @FXML
     private void modifierprofil(ActionEvent event) throws SQLException, IOException, AWTException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("modif");
        
        User p = new User();
        
     
        p.setUsername(username.getText());
        p.setPassword(password.getText());
        p.setCin(cin.getText());
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setEmail(adresse.getText());
       
      
        
       
        UserService sp = new UserService();
        sp.modifierUser(p);
          Notifications n = new Notifications();
                n.displayTray("enseignant", "bien modifié");
          System.err.println("insertion effectue");
       
      
    } 

    @FXML
    private void reclamer(ActionEvent event) throws IOException {
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/recmembre.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
        
        
    }
    
}
