/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entites.Classe;
import IService.ClasseInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ClasseService   implements ClasseInterface {
 Connection cnx;
    
    public ClasseService() throws SQLException, ClassNotFoundException {
       cnx = MyDBcon.getInstance().getCnx();     
    }
    @Override
    public void addClasse(Classe c) {
         try {

            String req = "INSERT INTO `classe`(`annee`,`specialite`,`groupe`) VALUES (?,?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setString(1, c.getAnnee());
            pstm.setString(2, c.getSpecialite());
            pstm.setString(3, c.getGroupe());

            pstm.executeUpdate();
            System.out.println("Classe est Ajouté ");


        } catch (SQLException ex) {
        }
       
    }

    @Override
    public ArrayList<Classe> getAllClasses() {
         ArrayList<Classe> listClasse = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM classe  ";
         
         ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
             int id = resultat.getInt("id");
             String annee = resultat.getString("annee");
             String specialte = resultat.getString("specialite");
             String groupe = resultat.getString("groupe");
             Classe classe = new Classe(id, specialte, annee, groupe);
             
             listClasse.add(classe);

         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listClasse;
     
    }

    @Override
    public Classe getById(int id) throws SQLException{
    
         String req = "SELECT * FROM classe WHERE id=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setInt(1, id);
         
         ResultSet resultat = pstm.executeQuery();
         if (resultat.next())
         {
              return new Classe(id, resultat.getString("annee"),resultat.getString("specialite"), resultat.getString("groupe"));
}
         else 
             return new Classe("", "", "");
     
    }

    @Override
    public int updateClasse(Classe c) throws SQLException{
  int x=0;
            String query = "update classe set annee=?,specialite=?,groupe=? where id=?";
            PreparedStatement pre = cnx.prepareStatement(query);

            pre.setString(1, c.getAnnee());
                pre.setString(2, c.getSpecialite());
            pre.setString(3, c.getGroupe());

            pre.setInt(4, c.getId());

           x= pre.executeUpdate();
             System.out.println("Classe est Modifié ");

return x;
    
    }

    @Override
    public int deleteClasse(int id) throws SQLException{
       int x=0;
            PreparedStatement prep = cnx.prepareStatement("delete from classe where id=?");

            prep.setInt(1, id);

            x=prep.executeUpdate();
            System.out.println("Classe est Supprimé ");

   return x;
    }
    
}
