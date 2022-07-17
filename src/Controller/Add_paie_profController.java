/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import Entites.Paie_Prof;
import Entites.Paiment_eleve;
import Entites.User;
import java.awt.AWTException;
import java.awt.event.TextEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONException;
import org.json.JSONObject;
import Services.CreateOrder;
import Services.ServiceFinanciere;
import com.paypal.orders.ShippingDetail;
import Utils.MyDB;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Add_paie_profController extends PayPalClient implements Initializable {
      @FXML
    private ComboBox cin;
      @FXML
    private TextField nom;
          @FXML
    private TextField prenom;
      @FXML
    private TextField nbre_heure;
          @FXML
    private TextField salaire_heure;
           @FXML
    private TextField email;
    
      @FXML
    private ComboBox <String> mode_reglement;
      private Pane mainpane;
      ServiceFinanciere sf=new ServiceFinanciere();
      Paie_Prof p=new Paie_Prof();
    @FXML
    private Button add;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   nbre_heure.setOnAction(event -> System.out.println(nbre_heure.getText()));
      
        String sql = "select cin,nom,email from fos_user where roles='enseignant'";
        Connection con = MyDB.getInstance().getConnexion();
        
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {

                cin.getItems().add(rs.getInt("cin"));
           

            }
        } catch (SQLException ex) {
           
        }


   mode_reglement.getItems().add("espece");
   mode_reglement.getItems().add("cheque");
   String value = (String) mode_reglement.getValue();
         mode_reglement.setEditable(true);
  
    }  
    @FXML
         public void getEmployeeId(ActionEvent e)throws IOException,ParseException{
             
			String sid=cin.getValue().toString();
			//int id=Integer.parseInt(sid);
			User emp=MyDB.getEmpoyeeId(sid);
                       
		nom.setText(emp.getNom());
	        prenom.setText(emp.getPrenom());
                email.setText(emp.getEmail());
     
			
		}
               public static void sendEmai(String emailAddress,String sub){
          
     String to = emailAddress;
      String host = "smtp.gmail.com";//or IP address  
      final String username = "elkameldhoha@gmail.com";
    final String password = "pcasus2019";
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", host);  
       props.put("mail.smtp.starttls.enable","true");
        props.setProperty("mail.smtp.ssl.trust", host);
       
        props.put("mail.smtp.port", "587");
     Session session = Session.getDefaultInstance(props, 
      new javax.mail.Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
      try{  
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("ISchool");  
         message.setText("les paiment des salaire sont effectuées merci de consulter votre compte .Votre salaire se ce mois est  :'"+sub+"'DT");  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
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
     
     
      try{  
         MimeMessage message = new MimeMessage(session);
   
         message.setFrom(new InternetAddress(username));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
              message.setSubject("ISchool");  
         message.setText("les paiment des salaire sont effectuées merci de consulter votre compte .Votre salaire se ce mois est  :'"+sub+"'DT");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
}
    @FXML
  public void add(ActionEvent event) throws IOException, AWTException {
  if (isEmpty()) {
            return;
        } else {

String c=cin.getValue().toString();
String o=salaire_heure.getText();
String oo=nbre_heure.getText();
//String e=
String rr=nom.getText();
String rrr=prenom.getText();
String em=email.getText();
//int cinn=Integer.parseInt(ci);
//int r=Integer.parseInt(c);
int pp=Integer.parseInt(o);
int ppp=Integer.parseInt(oo);
//String an=String.valueOf(ppp*pp);
//label.setText(an);
  Paie_Prof p=new Paie_Prof();
 //p.setNum_paiment(cinn);

p.setCin(c);
  p.setNom(rr);
  p.setPrenom(rrr);
    p.setMode_reg(mode_reglement.getValue());
  p.setNbre_heure(ppp);
  p.setSalaire_heure(pp);
p.calculSalair(pp, ppp);
p.setEmail(em);
  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation de Paiment de Salaire");
            alert.setHeaderText(null);
            alert.setContentText("le montant que vous aller envoyée à  '"+nom.getText()+"'   est '"+p.calculSalaire().toString()+"'DT");
            alert.showAndWait();
         
         sendEmail(email.getText(),p.calculSalaire().toString()); 
  int status=sf.ajouterPaiement(p);
  }   Notification n = new Notification();
        n.displayTray("Paiment", "bien insérér");
        clearFields();

/*try {
			new Add_paie_profController().createOrder(true);
		} catch (com.paypal.http.exceptions.HttpException e) {
			System.out.println(e.getLocalizedMessage());
		}
catch (Exception e) {
			e.printStackTrace();
		}*/
   mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/paiment_prof.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
	
  }

    public void clearFields() {
        nom.clear();
        prenom.clear();
      salaire_heure.clear();
       // cin.clear();
        nbre_heure.clear();
      

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
            alert.setContentText("Le nombres des heures n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }
        private boolean validatorInt1(String s) {
        Pattern p = Pattern.compile("[1-9]+[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le salaire  n'est pas valide !");
            alert.showAndWait();
            return false;
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
    private boolean isEmpty() {
        String c=cin.getValue().toString();
      if (validatorMail(email.getText())==false) {

            return true;
        }
        if (mode_reglement.getValue() == null) {
            warning("Veuillez selectionner!");
            return true;
        }
        if (validatorInt1(salaire_heure.getText()) == false) {
            
            return true;
        }
        if (validatorInt(nbre_heure.getText())==false) {

            return true;
        }
          if (validatorInt(c)==false) {

            return true;
        }
   

        return false;
    }
    	private OrderRequest buildRequestBody() {
            
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.checkoutPaymentIntent("CAPTURE");

		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().referenceId("PUHF")
				.customId("")
				.amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value("90.00")
						.amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value("90.00"))
								
								))
				.items(new ArrayList<Item>() {
					{
						add(new Item().name("Montant ")
								.unitAmount(new Money().currencyCode("USD").value("90.00"))
								.quantity("1")
								);
                                                
						
					}
				})
				.shippingDetail(new ShippingDetail().name(new Name().fullName("Dhoha Elkamel"))
					);
		purchaseUnitRequests.add(purchaseUnitRequest);
		orderRequest.purchaseUnits(purchaseUnitRequests);
		return orderRequest;
	}


	public HttpResponse<Order> createOrder(boolean debug) throws IOException, JSONException {
		OrdersCreateRequest request = new OrdersCreateRequest();
		request.header("prefer","return=representation");
		request.requestBody(buildRequestBody());
		HttpResponse<Order> response = client().execute(request);
		if (debug) {
			if (response.statusCode() == 201) {
				System.out.println("Status Code: " + response.statusCode());
				System.out.println("Status: " + response.result().status());
				System.out.println("Order ID: " + response.result().id());
				System.out.println("Intent: " + response.result().checkoutPaymentIntent());
				System.out.println("Links: ");
				/*for (LinkDescription link : response.result().links()) {
					System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
				}*/
				System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
						+ " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
				System.out.println("Full response body:");
				System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
			}
		}
		return response;
	}
        
          
}
