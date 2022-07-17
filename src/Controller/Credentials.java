/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Dhoha
 */
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
public class Credentials {
        static String clientId = "AXxbLLh2oeX9PgrSuJ1Mstym67S8Wgrqv2zNL3EMC_S7W-uptqXXslRipIZZkL-J63uVieMOVdGZtXOa";
    static String secret = "EDQS5ln3PFIolCNi442a7GPopebM-qtXn3jsJ2dwGYZ2zr9Yyc6Vjsoe6C8sqViu6bACfvEnPuywCpaL";
    
   private static PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);

  /**
   *PayPal HTTP client instance with environment that has access
   *credentials context. Use to invoke PayPal APIs.
   */
 static PayPalHttpClient client = new PayPalHttpClient(environment);

  /**
   *Method to get clent object
   *
   *@return PayPalHttpClient client
   */

}
