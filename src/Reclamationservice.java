/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Haytham
 */
public class Reclamationservice {
    
    
     public ArrayList<Reclamation2> selectAll() {

        ArrayList<Reclamation2> reclamations = new ArrayList();

        try {
            String requete = " SELECT * From reclamation ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                Reclamation2 r = new Reclamation2();
                r.setId(res.getInt("id"));
                r.setSujet(res.getString("sujet"));
                r.setContenu(res.getString("contenu"));
                r.setEtat(res.getString("etat"));
                r.setEmail(res.getString("email"));
              
                reclamations.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.Reclamationservice.selectAll()");
        }

        return reclamations;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
