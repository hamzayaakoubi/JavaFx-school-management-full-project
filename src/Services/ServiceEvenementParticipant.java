/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Evenement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class ServiceEvenementParticipant {
         public static void InsertEvenementParticipant(Integer idUser,Integer idEvenement) throws SQLException {
        String sql = "insert into evenement_participant (idUser,idEvenement) values ('" + idUser + "', '" + idEvenement +  "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Vous avez déja participer");

            throw e;
        }
    }
         
         
//             public void AddViewFAQ(Evenement u, int idEvenement) {
//
//        String requete2 = "UPDATE  faq SET nbPlaces=? where idEvenement =?";
//        try {
//            PreparedStatement pst = Database.getInstance().getCnx()
//                    .prepareStatement(requete2);
//            pst.setInt(1, u.getNbPlaces() - 1);
//            pst.setInt(2, idEvenement);
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//JOptionPane.showMessageDialog(null,"Il n'y pas de places");        }
//
//    }
             public void UpvoteCategorie(int idEvenement, int nbPlaces) throws SQLException {

        String sql = "UPDATE  evenement SET  nbPlaces= '" + nbPlaces+ "'where idEvenement ='" + idEvenement + "' ";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Vous avez déja participer");

            throw e;
        }

    }
}
