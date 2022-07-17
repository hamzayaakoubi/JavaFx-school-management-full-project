/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entites.Enseignant;
import Entites.Matiere;
import IService.MatiereInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class MatiereService implements MatiereInterface{
    Connection cnx;
    
    public MatiereService() throws SQLException, ClassNotFoundException {
       cnx = MyDBcon.getInstance().getCnx();     
    }

    @Override
    public void addMatiere(Matiere c) throws SQLException {
        try {

            String req = "INSERT INTO `matiere`(`nom`,`coef`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setString(1, c.getNom());
            pstm.setDouble(2, c.getCoef());

            pstm.executeUpdate();
            System.out.println("Matiere est Ajouté ");


        } catch (SQLException ex) {
        }    }
    

    @Override
    public ObservableList<Matiere> getAllMatiere() throws SQLException {
        ObservableList<Matiere> listMatiere = FXCollections.observableArrayList();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM matiere  ";
         
         ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
             int id = resultat.getInt("id");
             String nom = resultat.getString("nom");
             Double coef = resultat.getDouble("coef");
             Matiere enseignant = new Matiere(id, nom, coef);
             
             listMatiere.add(enseignant);

         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listMatiere;


    }

    @Override
    public Matiere getById(int id) throws SQLException {
   String req = "SELECT * FROM matiere WHERE id=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setInt(1, id);
         
         ResultSet resultat = pstm.executeQuery();
         if (resultat.next())
         {
              return new Matiere(id, resultat.getString("nom"),resultat.getDouble("coef"));
}
         else 
             return new Matiere("", 0.0);   }

    @Override
    public int updateMatiere(Matiere c) throws SQLException {
        int x=0;
             Statement pre = cnx.createStatement();
String query = "update matiere set nom='"+c.getNom()+"',coef='"+c.getCoef()+"' where id='"+c.getId()+"'";
       

            

           x= pre.executeUpdate(query);
             System.out.println("Matiere est Modifié ");    
             return x;
    }

    @Override
    public int deleteMatiere(int id) throws SQLException {
        int x=0;
         PreparedStatement prep = cnx.prepareStatement("delete from matiere where id=?");
            prep.setInt(1, id);
            x=prep.executeUpdate();
            System.out.println("Metiere est Supprimé ");
            return x;
    }

    @Override
    public int getIdMaitere(String nom) throws SQLException {
          String req = "SELECT * FROM matiere WHERE name=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setString(1, nom);
         
         ResultSet resultat = pstm.executeQuery();
      
          return resultat.getInt("id");
 
    
    }
}
