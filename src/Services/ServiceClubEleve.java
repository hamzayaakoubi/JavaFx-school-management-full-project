/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class ServiceClubEleve {
      public static void InsertClubEleve(Integer idClub, Integer idUser, String etat) throws SQLException {
        String sql = "insert into club_eleve (idClub,idUser,etat) values ('" + idClub + "', '" + idUser +"', '" + "inactif" +  "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Vous etes déja inscrit");
            throw e;
        }
    }
      public static void SuppClub1(Integer idC,Integer id) throws SQLException {
        String sql = "delete from club_eleve  where idClub ='" + idC+ "' and  idUser='"+id+"' " ;
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }
      
      
        public static void ModifMembre(String etat , Integer idClub ,Integer idUser) throws SQLException {
        String sql = "update club_eleve set etat= '" + etat+   "' where idClub ='" + idClub + "' and  idUser='"+idUser+"' ";
        try{    
        Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }

    }
    
}
