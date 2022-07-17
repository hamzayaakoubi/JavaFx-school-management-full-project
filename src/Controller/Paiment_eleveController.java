/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Entites.Paiment_eleve;
import Entites.User;
import java.awt.AWTException;
import java.io.IOException;
//import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Properties;
//import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.EmailService;

import Services.ServicePaiment_eleve;
import Services.serviceuser;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Paiment_eleveController implements Initializable {

    /**
     * Initializes the controller class.
     */
           Paiment_eleve p=new Paiment_eleve();
    ServicePaiment_eleve sv =new ServicePaiment_eleve();
 
 @FXML
    private TextField num_recu;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField remise;
    @FXML
    private ComboBox cin;
      @FXML
    private TextField montant;
         @FXML
    private ComboBox <String> mode_paiment;
     
               @FXML
    private ComboBox <String> mode_reglement;
                 @FXML
    private ComboBox type_frais;
                  @FXML
    private ComboBox <String> mois;
      @FXML
    private TextField reste;
       @FXML
    private Pane mainpane;
        @FXML
    private TextField email;
     int oblist;
    //ObservableList<String> oblist;
    @FXML
    private Button ajouterPaiment;
      @FXML
    private Button retour;
// String value = (String) mode_reglement.getValue();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         serviceuser pdao = new serviceuser();
             String sql5 = "select cin,nom from fos_user where roles='etudiant'";
        Connection con1 = MyDB.getInstance().getConnexion();
        
        try {
            ResultSet rs = con1.createStatement().executeQuery(sql5);
            while (rs.next()) {

                cin.getItems().add(rs.getString("cin"));
           

            }
        } catch (SQLException ex) {
           
        }
       //oblist = pdao.gid();
       // System.out.println(oblist);
        //cin.setItems(oblist);
              //  mode_reglement.setItems(options);
         mode_reglement.getItems().add("espece");
   mode_reglement.getItems().add("cheque");
   String value = (String) mode_reglement.getValue();
         mode_reglement.setEditable(true);
     mode_paiment.getItems().add("par mois");
   mode_paiment.getItems().add("Annuelle");
   String value1 = (String) mode_paiment.getValue();
         mode_paiment.setEditable(true);
                 String sql = "select type_frais from frais";
        Connection con = MyDB.getInstance().getConnexion();
        
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {

                type_frais.getItems().add(rs.getString("type_frais"));
           

            }
        } catch (SQLException ex) {
           
        }
  
                   mois.getItems().addAll("janvier",
"février",
"mars",
"avril",
"mai",
"juin",
"juillet",
"août",
"septembre",
"octobre",
"novembre",
"décembre");
                      String value13 = (String) mois.getValue();
         mois.setEditable(true);
        
    }  
      public static void sendEmail(String emailAddress,String sub){
            Paiment_eleve p=new Paiment_eleve();
     String to = emailAddress;//change accordingly  
      //String subject = emailSubject;
        //String msg = emailMessage;
      
      String host = "smtp.gmail.com";//or IP address  
      final String username = "elkameldhoha@gmail.com";
    final String password = "pcasus2019";
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", host);  
       props.put("mail.smtp.starttls.enable","true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");      
            // props.put("mail.smtp.user", username);
      //  props.put("mail.smtp.password", password);
       
        props.put("mail.smtp.port", "587");
     Session session = Session.getDefaultInstance(props, 
      new javax.mail.Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
       /* String[] attachFiles = new String[1];
        attachFiles[0] = "";
     //compose the message  */
      try{  
         MimeMessage message = new MimeMessage(session);
         /*Multipart multipart = new MimeMultipart();
 
  MimeBodyPart attachPart = new MimeBodyPart();
String attachFile = "C:\\Users\\Dhoha\\Documents\\NetBeansProjects\\Pi_dev\\src\\pdf\\Paiment_eleve.pdf";
 
DataSource source = new FileDataSource(attachFile);
attachPart.setDataHandler(new DataHandler(source));
attachPart.setFileName(new File(attachFile).getName());
 
multipart.addBodyPart(attachPart);*/
         message.setFrom(new InternetAddress(username));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject(" ISchool");  
         message.setText("les paiment de l'ecole sont effectuées de mois '"+sub+"'");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
}


 public void getE(ActionEvent e)throws IOException,ParseException{
			String sid=cin.getValue().toString();
			//int id=Integer.parseInt(sid);
			User emp=MyDB.getEmpoyeeId(sid);
		nom.setText(emp.getNom());
	        prenom.setText(emp.getPrenom());
                email.setText(emp.getEmail());
 }
 
    public void addProjet(ActionEvent event) throws IOException, AWTException {
  if (isEmpty()) {
            return;
        } else {
String ci=cin.getValue().toString();
String r=remise.getText();
String rr=nom.getText();
String rrr=prenom.getText();
String oo=email.getText();
//int cinn=Integer.parseInt(ci);
int remis=Integer.parseInt(r);
//int cinn=Integer.parseInt(ci);
  Paiment_eleve p=new Paiment_eleve();
  p.setMode_paiment(mode_paiment.getValue());
  p.setMois(mois.getValue());
  
  p.setEmail(oo);
 p.setCin(ci);
  p.setNom(rr);
  p.setPrenom(rrr);

  p.setMode_reglement(mode_reglement.getValue());
  p.setRemise(remis);
  p.setType_frais(type_frais.getValue().toString());

       sendEmail(email.getText(),mois.getValue());
  int status=sv.ajouterPaiement(p);
       // EmailService u=new EmailService();

  
    }
	  Notification n = new Notification();
        n.displayTray("Paiment", "bien insérér");
        clearFields();

   mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Liste_paiment.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
	
    
        public void clearFields() {
       
        nom.clear();
       email.clear();
        prenom.clear();
        remise.clear();

    }

    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
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
        Pattern p = Pattern.compile("[1-9]+[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le remise n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }

         private boolean isEmpty() {
        if (mode_paiment.getValue() == null) {
            warning("Veuillez selectionner!");
            return true;
        }
         if (mode_reglement.getValue() == null) {
            warning("Veuillez selectionner!");
            return true;
        }
          if (type_frais.getValue() == null) {
            warning("Veuillez selectionner!");
            return true;
        }
      
        if (validatorInt(remise.getText())==false) {

            return true;
        }
       if (validatorMail(email.getText())==false) {

            return true;
        }
       

        return false;
    }
   

}
