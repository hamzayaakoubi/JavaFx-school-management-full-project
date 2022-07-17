/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entites.Classe;
import Entites.EmploiTemps;
import Entites.Enseignant;
import IService.EnseignantInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class EnseignantService implements EnseignantInterface{
 Connection cnx;
    
    public EnseignantService() throws SQLException, ClassNotFoundException {
       cnx = MyDBcon.getInstance().getCnx();     
    }
    @Override
    public void addEnseignant(Enseignant ens) throws SQLException {
 try {

            String req = "INSERT INTO `fos_user`(`nom`,`prenom`,`email`,`role`) VALUES (?,?,?,'a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}')";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setString(1, ens.getNom());
            pstm.setString(2, ens.getPrenom());
            pstm.setString(3, ens.getEmail());

            pstm.executeUpdate();
            System.out.println("enseignant est Ajouté ");


        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    @Override
    public ArrayList<Enseignant> getAllEnseignant() throws SQLException {
  ArrayList<Enseignant> listEnseignant = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM fos_user where role='a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}'  ";
         
         ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
             int id = resultat.getInt("id");
             String nom = resultat.getString("nom");
             String prenom = resultat.getString("prenom");
             String email = resultat.getString("email");
             Enseignant enseignant = new Enseignant(id, nom, prenom, email);
             
             listEnseignant.add(enseignant);

         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listEnseignant;
         }

    @Override
    public Enseignant getById(int id) throws SQLException {
          String req = "SELECT * FROM fos_user WHERE id=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setInt(1, id);
         
         ResultSet resultat = pstm.executeQuery();
         if (resultat.next())
         {
              return new Enseignant(id, resultat.getString("nom"),resultat.getString("prenom"), resultat.getString("email"));
}
         else 
             return new Enseignant("", "", "");
     
    }

    @Override
    public int updateEnseignant(Enseignant ens) throws SQLException {
        int x=0;
   String query = "update fos_user set nom=?,prenom=?,email=? where id=?";
            PreparedStatement pre = cnx.prepareStatement(query);

            pre.setString(1, ens.getNom());
            pre.setString(2,ens.getPrenom());
            pre.setString(3, ens.getEmail());

            pre.setInt(4, ens.getId());

           x= pre.executeUpdate();
             System.out.println("Enseignant est Modifié ");
             return x;
    }

    @Override
    public int deleteEnseignant(int id) throws SQLException {
         int x=0;
         PreparedStatement prep = cnx.prepareStatement("delete from enseignant where id=?");
            prep.setInt(1, id);
            x=prep.executeUpdate();
            System.out.println("Enseignant est Supprimé ");
return x;
    }

    @Override
    public Enseignant login(String username, String password) throws SQLException {
 Enseignant temps = new Enseignant("", "", "") ; 
     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT * FROM fos_user WHERE nom= ? AND password=?";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setString(1, username);
         psm.setString(2, password);

         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             int id = resultat.getInt("id");
           

             
         temps = new Enseignant(id, resultat.getString("nom"), resultat.getString("prenom"), resultat.getString("email"),resultat.getString("role"),resultat.getInt("idClasse"));
             

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return temps;
        
    }

    @Override
    public ArrayList<Enseignant> getClasseById(int id) throws SQLException {
  ArrayList<Enseignant> listEnseignant = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM fos_user where idClasse=?  ";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, id);

         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             String nom = resultat.getString("nom");
             String prenom = resultat.getString("prenom");
             String email = resultat.getString("email");
             Enseignant enseignant = new Enseignant(id, nom, prenom, email);
             
             listEnseignant.add(enseignant);

         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listEnseignant;

    }
    
    
}
