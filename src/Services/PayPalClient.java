/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import org.json.JSONException;

public class PayPalClient {

	/**
	 * Setting up PayPal SDK environment with PayPal Access credentials. For demo
	 * purpose, we are using SandboxEnvironment. In production this will be
	 * LiveEnvironment.
         * 
	 */
            static String clientId = "AXxbLLh2oeX9PgrSuJ1Mstym67S8Wgrqv2zNL3EMC_S7W-uptqXXslRipIZZkL-J63uVieMOVdGZtXOa";
    static String secret = "EDQS5ln3PFIolCNi442a7GPopebM-qtXn3jsJ2dwGYZ2zr9Yyc6Vjsoe6C8sqViu6bACfvEnPuywCpaL";
    
   private static PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);
	/*private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			System.getProperty("AXxbLLh2oeX9PgrSuJ1Mstym67S8Wgrqv2zNL3EMC_S7W-uptqXXslRipIZZkL-J63uVieMOVdGZtXOa") != null ? System.getProperty("AXxbLLh2oeX9PgrSuJ1Mstym67S8Wgrqv2zNL3EMC_S7W-uptqXXslRipIZZkL-J63uVieMOVdGZtXOa")
					: "<<AXxbLLh2oeX9PgrSuJ1Mstym67S8Wgrqv2zNL3EMC_S7W-uptqXXslRipIZZkL-J63uVieMOVdGZtXOa>>",
			System.getProperty("EDQS5ln3PFIolCNi442a7GPopebM-qtXn3jsJ2dwGYZ2zr9Yyc6Vjsoe6C8sqViu6bACfvEnPuywCpaL") != null ? System.getProperty("EDQS5ln3PFIolCNi442a7GPopebM-qtXn3jsJ2dwGYZ2zr9Yyc6Vjsoe6C8sqViu6bACfvEnPuywCpaL")
					: "<<EDQS5ln3PFIolCNi442a7GPopebM-qtXn3jsJ2dwGYZ2zr9Yyc6Vjsoe6C8sqViu6bACfvEnPuywCpaL>>");
*/
	/**
	 * PayPal HTTP client instance with environment which has access credentials
	 * context. This can be used invoke PayPal API's provided the credentials have
	 * the access to do so.
	 */
	PayPalHttpClient client = new PayPalHttpClient(environment);

	/**
	 * Method to get client object
	 *
	 * @return PayPalHttpClient client
	 */
	public PayPalHttpClient client() {
		return this.client;
	}

	/**
	 * Method to pretty print a response
	 *
	 * @param jo  JSONObject
	 * @param pre prefix (default="")
	 * @return String pretty printed JSON
	 */

}