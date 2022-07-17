/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entites.Classe;
import Entites.Matiere;
import Entites.Salle;
import IService.SalleInterface;
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
public class SalleService implements SalleInterface{
     Connection cnx;
    
    public SalleService() throws SQLException, ClassNotFoundException {
       cnx = MyDBcon.getInstance().getCnx();     
    }

    @Override
    public void addSalle(Salle s) throws SQLException {
          try {

            String req = "INSERT INTO `salle`(`bloc`,`num`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setString(1, s.getBloc());
            pstm.setInt(2, s.getNum());

            pstm.executeUpdate();
            System.out.println("salle est Ajouté ");


        } catch (SQLException ex) {
              System.out.println(ex);
        }
    }

    @Override
    public ArrayList<Salle> getAll() throws SQLException {
          ArrayList<Salle> listSalle = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM salle  ";
         
         ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
             System.out.println(resultat.getInt("id"));
             int id = resultat.getInt("id");
             String bloc = resultat.getString("bloc") ; 
             int num = resultat.getInt("num") ; 
             Salle s = new Salle();
             s.setId(resultat.getInt("id"));
             s.setBloc(resultat.getString("bloc"));
             s.setNum(resultat.getInt("num"));
             listSalle.add(s);

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return listSalle;
     
    }

    @Override
    public Salle getById(int id) throws SQLException {
String req = "SELECT * FROM salle WHERE id=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setInt(1, id);
         
         ResultSet resultat = pstm.executeQuery();
         if (resultat.next())
         {
              return new Salle(id, resultat.getString("bloc"),resultat.getInt("num"));
}
         else 
             return new Salle("", 00); 
    }
   

      @Override
    public int updateSalle(Salle s) throws SQLException{
     int x=0;
        String query = "update salle set bloc=?,num=? where id=?";
            PreparedStatement pre = cnx.prepareStatement(query);

            pre.setString(1, s.getBloc());
            pre.setInt(2,s.getNum());

            pre.setInt(3, s.getId());

           x= pre.executeUpdate();
             System.out.println("Salle est Modifié ");
             return x;
    }

  
 

    @Override
    public int deleteSalle(int id) throws SQLException {
      int x=0;
         PreparedStatement prep = cnx.prepareStatement("delete from salle where id=?");
            prep.setInt(1, id);
           x= prep.executeUpdate();
            System.out.println("Salle est Supprimé ");  
          return x;
    }  
    }
  
    

   
   
    
    

