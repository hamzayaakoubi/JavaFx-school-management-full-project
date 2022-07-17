/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Dhoha
 */

public class EmailService {
    public static void sendEmail(String emailAddress){
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
         message.setSubject("Rappel pour le paiment");  
         message.setText("Hello,  les frais de l'ecole sont payées");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
}
}