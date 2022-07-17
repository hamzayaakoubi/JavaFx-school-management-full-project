/*/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class FaqController implements Initializable {
    
    Connection c = DataBase.getInstance().getConnection();
    @FXML
    private AnchorPane mainpane;
    
    public FaqController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    Statement ste;
    
    @FXML
    private Label rep;
    @FXML
    private JFXTextField qst;
    @FXML
    private JFXButton emchi;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }
     @FXML
    public void repp() throws SQLException 
            
    {            

        String souel=qst.getText();
         String souel2=qst.getText();
        
         //String jaweb="";
        //String is2l="";
        Statement stm = c.createStatement();
          String req = "SELECT `description`,`qst` FROM faqq where qst Like '%"+souel+"%'";  
         
          //ste = c.createStatement();
// ResultSet rst = ste  .executeQuery(req);
        
            
         //String ahya = rst.getString("description");
              // jaweb= rst.getString(1);
                //is2l= rst.getString(2);       
                
                
                //System.err.println("ahya");
                
                
                
       
        ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
            //int iid= resultat.getInt("id");
            String dsc = resultat.getString("description");
            String qs = resultat.getString("qst");
                
            System.err.println(dsc);     
             rep.setText(dsc);
                
                
           
                
         }         
                
                
    }
    

    /*private void hear(ActionEvent event) throws IOException {
        
    }*/

    //@FXML
   /* private void rateus(ActionEvent event) throws IOException {
        
        
    }*/
    
    
    
}