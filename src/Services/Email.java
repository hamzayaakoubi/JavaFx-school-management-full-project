/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
 /**
 *
 * @author pablo
 */
public class Email {
    public static void sendMail(String recipient,String msg,String subject) throws Exception 
    
     {  
     /* 
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
         Message message = prepareMessage (session ,myaccount,recipient,subject,msg) ;  
         Transport.send(message);
         System.out.println("message sent successfully "); 
       
     }

    private  static Message prepareMessage(Session session, String myaccount, String recipient,String subject,String msg) {
  
        Message message = new MimeMessage(session) ; 
        try {
            
        //  pt = con.prepareStatement("select note , nom  from notes") ; 
           //ResultSet rs = pt.executeQuery() ; 
                    
            
             
            message.setFrom(new InternetAddress(myaccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(msg);
            return message ; 
        } catch (Exception ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ; 
    
*/
}
    
}

