/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Categorie;
import DataBase.MyConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class CategorieService {
    
    private final Connection cnx = MyConnection.getInstance().getCnx();
    private Statement ste;
    
    public void AjouterCategorie(Categorie  m) throws SQLException{
       
        String req = "INSERT INTO categorie(id,libelecategorie,desccategorie,nbrabo,nbrvu) VALUES(?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, m.getId());
        pre.setInt(4, m.getNbrabo());
        pre.setInt(5, m.getNbrvu());
        pre.setString(2, m.getLibele_categ());
        pre.setString(3,m.getDescription_categ());
        pre.executeUpdate();
        System.out.println("Categorie Ajouté");
        
       
    }
    
    public List<Categorie> AfficherCategorie() throws SQLException {
        String req = "SELECT * FROM categorie ";
        ste = cnx.createStatement();
        ResultSet r = ste.executeQuery(req);
        System.out.println(r.toString());
       List<Categorie> Categorie = new ArrayList<>();

        while (r.next()) {
                    

            Categorie.add(new Categorie(r.getInt(1), r.getString(3),r.getInt(4),r.getInt(5), r.getString(7)));
            
        }
                       //for(int i=0;i<Categorie.size();i++){CompterArticles(Categorie.get(i));}

        return Categorie;
    }
     public void SupprimerTousCateg() throws SQLException {
            
        //int id = m.getId();
        
        String req = "DELETE FROM categorie ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
     
     public void IncrementerVus(Categorie m) throws SQLException {
            
        int id = m.getId();
        
        String req = "UPDATE categorie SET nbrvu=nbrvu+1 WHERE id='" + id + "' ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
     
     public void CompterArticles(Categorie m) throws SQLException {
            
        int id = m.getId();
        String req = "UPDATE categorie SET nbrabo=(SELECT COUNT(id) FROM article WHERE article.categ_id='" + id + "') WHERE id='" + id + "'";
        //String req = "UPDATE categorie SET nbrabo=(COUNT * FROM article WHERE id='" + id + "') ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
    public void SupprimerCategorie(Categorie m) throws SQLException {
            
        int id = m.getId();
        String req1 = "DELETE FROM Article WHERE categ_id='" + id + "' ";
        PreparedStatement pre1 = cnx.prepareStatement(req1);
        pre1.executeUpdate();
        String req = "DELETE FROM categorie WHERE id='" + id + "' ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
    
    public void ModifierCategorie(Categorie m,int id) throws SQLException {
        String req = "UPDATE categorie SET  id=?, libelecategorie=?, desccategorie=? WHERE id='" + id + "'";
        PreparedStatement pre = cnx.prepareStatement(req);
        
        pre.setInt(1, m.getId());
        pre.setString(2, m.getLibele_categ());
        pre.setString(3, m.getDescription_categ());
        pre.executeUpdate();
         }
    
    
}
