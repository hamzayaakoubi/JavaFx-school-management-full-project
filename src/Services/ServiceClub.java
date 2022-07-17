/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.eventChartController;
import Entites.Club;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Utils.Database2;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Haytham
 */
public class ServiceClub {
        private final Connection cnx = Database2.getInstance().getCnx();


    public static void InsertClub(String nomClub, String dateCreation, String emailClub, Integer idPresident, String imageClub) throws SQLException {
        String sql = "insert into club (nomClub,dateCreation,emailClub,idPresident,imageClub) values ('" + nomClub + "', '" + dateCreation + "', '" + emailClub + "', '" + idPresident + "', '" + imageClub + "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppClub(Integer id) throws SQLException {
        String sql = "DELETE FROM `club` WHERE `club`.`idClub` =" + Integer.toString(id);
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }
    
    
          public static void SuppClub1(Integer id) throws SQLException {
        String sql = "DELETE FROM `club_eleve` WHERE `club_eleve`.`idClub` =" + Integer.toString(id);
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }
         public static void SuppClub2(Integer id) throws SQLException {
        String sql = "DELETE FROM `evenement` WHERE `evenement`.`idClub` =" + Integer.toString(id);
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }
  public static void SuppClub3(Integer idClub) throws SQLException {
        String sql = "DELETE FROM evenement_participant WHERE idEvenement  = (Select idEvenement from evenement where idClub='"+idClub+"');";
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifClub(Integer idClub, String nomClub, String dateCreation, String emailClub, Integer idPresident, String imageClub) throws SQLException {
        String sql = "update club set nomClub= '" + nomClub + "',dateCreation ='" + dateCreation + "',emailClub='" + emailClub + "',idPresident='" + idPresident + "',imageClub='" + imageClub + "'  where idClub ='" + idClub + "' ";
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User inexisistant");

            throw e;
        }

    }
    
    

         public static void ModifUser(Integer id,String role) throws SQLException {
            
        
        
        String req = "UPDATE fos_user SET roles2 = \"President\" WHERE id in (SELECT idPresident FROM club);";
        
         try {
            Database2.dbExecuteQuery(req);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User inexisistant");

            throw e;
        }
    }
                public static void ModifUser2(Integer id,String role) throws SQLException {
            
        
        
        String req = "UPDATE fos_user SET roles2 = \"etudiant\" WHERE roles not IN ('etudiant','parent','enseignant','admin','Etudiant','Parent','Enseignant','Admin') and id not IN (SELECT idPresident FROM club);";
        
         try {
            Database2.dbExecuteQuery(req);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User inexisistant");

            throw e;
        }
    }
         
         
//         public static void ModifUser2(Integer id,String role) throws SQLException {
//            
//        
//        
//        String req = "UPDATE fos_user SET roles = \"Etudiant\" WHERE id not in (SELECT idPresident FROM club);";
//        
//         try {
//            Database2.dbExecuteQuery(req);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "User inexisistant");
//
//            throw e;
//        }
//    }
//        
     

    }
    
    
    
    
    
    
    


