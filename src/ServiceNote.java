/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Note ; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.Connexion;

/**
 *
 * @author pablo
 */
public class ServiceNote {
    private Connection con = Connexion.getInstance().getCnx();

    public void ajouterNote(Note p) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into notes values (" + null + ",'" + p.getNom() + "','" + p.getPrenom() + "','" + p.getMatiere() + "','" + p.getNote() + "','" + p.getIdEleve() + "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherNote() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from notes ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println(" [id :" + rs.getInt(1) + ",nom" + rs.getString(2) + ",prenom" + rs.getString(3)+ ",matiere" + rs.getString(4)+ ",note" + rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      public void modifier(Note n )   {
      PreparedStatement pt;
        try {
           pt = con.prepareStatement("update notes set nom = ? , prenom = ? , matiere = ? , note = ? where IDD = ?  ");
           
            pt.setString(1, n.getNom());
            pt.setString(2, n.getPrenom());
            pt.setString(3, n.getMatiere());
            pt.setInt(4, n.getNote());
            pt.setInt(5, n.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        

    

    public void supprimerNote(int id ) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from notes where IDD = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void searchNote(int id){
        try {
            PreparedStatement pt =con.prepareStatement("select * from notes where cin=?");
            pt.setInt(1,id);
          ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println(  "nom:" + " " +rs.getString(2) + ",note;" + " " + rs.getInt(5));
            }
        } catch (SQLException ex) {
          Logger.getLogger(ServiceNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void trier() {
        Statement pt;
        String req = "select * from notes ORDER BY ID";
        try {
            pt = con.createStatement();
            ResultSet rs = pt.executeQuery(req);
            System.out.println("tri par ID: ");

            while (rs.next()) {
                System.out.println(rs.getInt("ID"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    }
    
}
