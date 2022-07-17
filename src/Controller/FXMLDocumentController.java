/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.LoginController.test;
import Entites.User;
import Services.UserService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author mazen
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Pane mainpane;
    @FXML
    private JFXButton etudbtn;
    @FXML
    private JFXButton deco;
    @FXML
    private JFXButton btnEnseig;
    @FXML
    private JFXButton prnt;
    @FXML
    private AnchorPane ap2;
    @FXML
    private JFXButton cantinee;
    private Label nom;
    private Label nomm;
    @FXML
    private JFXButton eventbtn;
    @FXML
    private JFXButton clubbtn;
    @FXML
    private JFXButton foyer;
    @FXML
    private JFXButton bel;
    @FXML
    private JFXButton note;
    @FXML
    private JFXButton absence;
    @FXML
    private JFXButton finance;
    @FXML
    private JFXButton emploi;
    @FXML
    private JFXButton faq;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
     public void MyLogin(String text)
    { 
       
    }

    @FXML
    private void etudbtn(ActionEvent event) throws IOException, SQLException {
         int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("a:1:{i:0;s:10:\"ROLE_ELEVE\";}") || test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
        
  }

    @FXML
    private void btnEnseig(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherEnseignant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilEnseignant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
        
        
        
    }
    
   /* public void MyLogin(String text)
    {  nom.setText(text);
       
    }*/
    //@FXML
 /*  private void logout(ActionEvent event) throws IOException {
       
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Login.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
    */

    @FXML
    private void prnt(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherParent.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilParent.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
    }

    
    Connection c = DataBase.getInstance().getConnection();
       
    Statement ste;
    
    @FXML
    private void deco(ActionEvent event) throws IOException, SQLException {
        
       Stage stage = (Stage) ap2.getScene().getWindow();
          
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Main.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
         String query = "update fos_user SET demande=null";
        
                ste = c.createStatement();
                

                ste.executeUpdate(query);
    
    }

    @FXML
    private void gosee(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
           mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherAbonne.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
         else if (test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Menu.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }

   
    

    
}

     @FXML
    private void clubbtn(ActionEvent event) throws IOException {
        
 
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/club.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        else if(test.equals("President")){
            
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/InfoClub.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        }
        
          else  {
              
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Interdit.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
    }

    @FXML
    private void eventbtn(ActionEvent event) throws IOException {



        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/evenement.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        else if(test.equals("President")){
            
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/evenementPresident.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        }
        
          else  {
              
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Interdit.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
    }

    @FXML
    private void bel(ActionEvent event) throws IOException {
          int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("/Fxml/ajouterReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        }   
    }

    @FXML
    private void foyer(ActionEvent event) throws IOException {
        
           int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {    
        mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("/Fxml/chambre.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        }
    }

    @FXML
    private void note(ActionEvent event) throws IOException {
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherNote.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("a:1:{i:0;s:10:\"ROLE_ELEVE\";}") || test.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}") || test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/affnoteeleve.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
    }

    @FXML
    private void absence(ActionEvent event) throws IOException {
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherAbsence.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("a:1:{i:0;s:10:\"ROLE_ELEVE\";}") || test.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}") || test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/affabsenceeleve.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

   }

  }

    @FXML
    private void finance(ActionEvent event) throws IOException {
        
    mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/MenuDhoha.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }

    @FXML
    private void emploi(ActionEvent event) throws IOException {
         int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {   
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/MenuRabiaaFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }
        else if (test.equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Emloie.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
        else{
                 mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/EmploiFrintFXML.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        }
            
        }
    

      @FXML
    private void faq(ActionEvent event) throws IOException {
          mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/faq.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

}

    @FXML
    private void gorate(ActionEvent event) throws IOException {
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/rateapp.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    
    
    
}