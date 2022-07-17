/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
 

import Entites.Absence ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.Connexion ; 

/**
 *
 * @author pablo
 */
public class ServiceAbsence {
    
      private Connection con = Connexion.getInstance().getCnx();

    public void ajouterAbsence(Absence a) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into absence values (" + null + ",'" + a.getNom() + "','" + a.getPrenom() + "','" + a.getMatiere() + "','" + a.getDate() + "','" + a.getNbAbsence() + "','" + a.getIdEleve()+ "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void afficherAbsence() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from absence ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println(" [cin :" + rs.getInt(1) + ",nom" + rs.getString(2) + ",prenom" + rs.getString(3)+ ",nbAbsence" + rs.getInt(4) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
         public void modifierAbsence(Absence a )   {
  PreparedStatement pt;
        try {
           pt = con.prepareStatement("update absence set nom = ? , prenom = ? , matiere = ? , date  = ?  ,nbAbsence = ?    where IDD = ?  ");
           
            pt.setString(1, a.getNom());
            pt.setString(2, a.getPrenom());
             pt.setString(3, a.getMatiere());
               pt.setString(4, a.getDate().toString());
            pt.setInt(5, a.getNbAbsence());
            
            pt.setInt(6, a.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         
         
           public void supprimerAbsence(int id ) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from absence where IDD = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }
               public void searchAbsence(int cin){
        try {
            PreparedStatement pt =con.prepareStatement("select * from absence where cin=?");
            pt.setInt(1,cin);
          ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println(" [cin :" + rs.getInt(1) + ",nom" + rs.getString(2) + ",prenom" + rs.getString(3)+ ",matiere" + rs.getString(4)+ ",note" + rs.getInt(5));
            }
        } catch (SQLException ex) {
          Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void trierA() {
        Statement pt;
        String req = "select * from absence ORDER BY nbAbsence";
        try {
            pt = con.createStatement();
            ResultSet rs = pt.executeQuery(req);
            System.out.println("tri par nombre d'absence: ");

            while (rs.next()) {
                System.out.println(  "nom:" +" "+ rs.getString("nom")  + " "+   ",note:" +    rs.getInt("nbAbsence"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    }
  
}
