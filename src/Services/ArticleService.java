/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Article;
import Entites.Mots;
import DataBase.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ArticleService {
    
    private final Connection cnx = MyConnection.getInstance().getCnx();
    private Statement ste;
    
    public void AjouterArticle(Article  a) throws SQLException{
       
        String req = "INSERT INTO article(id, categ_id, user_id, titrearticle, contenuarticle,nbrlike,nbrvu,nbrcomment,datearticle,nbrdislike) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, a.getId());
        pre.setInt(3, a.getId_user());
        pre.setInt(2, a.getId_categ());
        pre.setString(4, a.getTitle());
        pre.setString(5, a.getContenu());
        pre.setInt(6,a.getNbrlike());
        pre.setInt(7,a.getNbrvu());
        pre.setInt(8,a.getNbrcomment());
        pre.setTimestamp(9,a.getDate_article());
        pre.setInt(10,a.getNbrdislike());
        pre.executeUpdate();
        System.out.println("Article Ajouté");
        
    }
    public void IncrementerVus(Article m) throws SQLException {
            
        int id = m.getId();
        
        String req = "UPDATE article SET nbrvu=nbrvu+1 WHERE id='" + id + "' ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
    public void Testmot(Mots m) throws SQLException {
            
        String mot= m.getTextmot();
        System.out.println(mot);
        String req = "SELECT id FROM Article WHERE contenuarticle LIKE '%" + mot + "%'";
        ste = cnx.createStatement();
        ResultSet r = ste.executeQuery(req);
        List<Integer> ids = new ArrayList<>();
        while (r.next()) {
            ids.add(r.getInt(1));   
        } 
        for (int i=0;i<ids.size();i++){SupprimerArticle(ids.get(i));}
    }
    public List<Article> AfficherArticle(int id) throws SQLException {
        String req = "SELECT * FROM Article WHERE categ_id='" + id + "' ";
        ste = cnx.createStatement();
        ResultSet r = ste.executeQuery(req);
        System.out.println(r.toString());
       List<Article> Article = new ArrayList<>();
        while (r.next()) {
            Article.add(new Article(r.getInt(3),r.getInt(1),r.getInt(2),r.getTimestamp(12),r.getString(4),r.getString(5),r.getInt(8),r.getInt(10),r.getInt(11)));
        }
        return Article;
    }
    
    public Article AfficherunArticle(int id) throws SQLException{
        String req="SELECT * FROM Article WHERE id='" + id + "' ";
        Statement ste1 = cnx.createStatement();
        ResultSet r = ste1.executeQuery(req);
        System.out.println(r.toString());
        Article a= new Article();
        while (r.next()) {
        a = new Article(r.getInt(3),r.getInt(1),r.getInt(2),r.getTimestamp(12),r.getString(4),r.getString(5),r.getInt(8),r.getInt(10),r.getInt(11));
            System.out.println(a); 
        }
        return a;
    }
     public void SupprimerArticle(int id) throws SQLException {
            
        
        String req = "DELETE FROM Article WHERE id='" + id + "' ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
  /*
     public void SupprimerTousCateg() throws SQLException {
            
        //int id = m.getId();
        String req = "DELETE FROM Article ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
   
    
    public void ModifierArticle(Article m,int id) throws SQLException {
        String req = "UPDATE Article SET  id=?, libeleArticle=?, descArticle=? WHERE id='" + id + "'";
        PreparedStatement pre = cnx.prepareStatement(req);
        
        pre.setInt(1, m.getId());
        pre.setString(2, m.getLibele_categ());
        pre.setString(3, m.getDescription_categ());
        pre.executeUpdate();
         }
    */
}
