/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.PasswordAuthentication ; 
import javax.mail.Message ; 
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Utils.Connexion ; 
import java.sql.DriverManager;
         


/**
 *
 * @author pablo
 */
public class JavaMainUtil {
    
        private Connection con = Connexion.getInstance().getCnx();

       
    public static void sendMail(String recipient,String msg) throws Exception 
    
     {
         
      
       System.out.println("preparing to send email ");    
      
       
       
         Properties properties = new Properties() ; 
         properties.put("mail.smtp.auth","true") ; 
         properties.put("mail.smtp.starttls.enable","true") ; 
         properties.put("mail.smtp.host", "smtp.gmail.com") ;
         properties.put("mail.smtp.port", "587") ;
         
         String myaccount = "houssem.hamdi@esprit.tn" ; 
         String password = "cooler master";
         
         Session session = Session.getInstance(properties, new Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return  new PasswordAuthentication(myaccount, password) ; 
             }
                                                   
           }
                  
       ) ; 
         Message message = prepareMessage (session ,myaccount,recipient,msg) ;  
         
         
         
         Multipart emailContent = new MimeMultipart();
			
			 
//        MimeBodyPart textBodyPart = new MimeBodyPart();
//	textBodyPart.setText("Notes POUR LA 1ere SEMESTRE");
//			
//	MimeBodyPart pdfAttachment = new MimeBodyPart();
//	pdfAttachment.attachFile("C:\\Users\\pablo\\Desktop\\dia_clss.PNG");
//			
//	emailContent.addBodyPart(textBodyPart);
//	emailContent.addBodyPart(pdfAttachment);
//			
//			 
//	message.setContent(emailContent); 
         
         
         
         
         
         
         
         
         Transport.send(message);
         System.out.println("message sent successfully "); 
       
     }

    private  static Message prepareMessage(Session session, String myaccount, String recipient,String msg) {
        PreparedStatement pt  ; 
        Message message = new MimeMessage(session) ; 
        try {
            
        //  pt = con.prepareStatement("select note , nom  from notes") ; 
           //ResultSet rs = pt.executeQuery() ; 
                    
            
               Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/mydb", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT IDD,nom,prenom,matiere,note FROM `notes`");
            message.setFrom(new InternetAddress(myaccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Notes");
            message.setText(msg);
            return message ; 
        } catch (Exception ex) {
            Logger.getLogger(JavaMainUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ; 
    }
    
}
