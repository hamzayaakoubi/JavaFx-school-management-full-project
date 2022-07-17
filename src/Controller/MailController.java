/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataBase.Mail;
import DataBase.TrayIconDemo;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class MailController implements Initializable {

    @FXML
    TextField des;
    @FXML
    TextArea desc;
    @FXML
    private Button mail;
    @FXML
    private TextField suj;
    @FXML
    private Button annuler;
    
    
   @FXML
    private Pane mainpane;
    
    
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    } 
    
    Mail u=new Mail();
    
    String[]args21 = null;

       

    @FXML
    private void send(ActionEvent event) {
        String[]args21 = null;

       try {
            TrayIconDemo.main(args21);
                    
        } catch (AWTException ex) {
           Logger.getLogger(TrayIconDemo.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
        u.sendMail(des.getText(),suj.getText(),desc.getText());
        
        
        
        
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        
          mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/BReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
        
        
    }
    
    
    
    
    
    
    
    
}
