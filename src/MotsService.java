/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Mots;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DataBase.MyConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class MotsService {
   
    private final Connection cnx = MyConnection.getInstance().getCnx();
    private Statement ste;
    
    
    public void AjouterMot(Mots  m){
        try{
        String req = "INSERT INTO motscensure(id,textmot,gravitemot) VALUES(?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, m.getId());
        pre.setString(2, m.getTextmot());
        pre.setInt(3,m.getGravitemot());
        pre.executeUpdate();
        System.out.println("mot Ajouté");
        }
        catch(SQLException s) {
            System.err.println("fddfsdfs");
        }
    }
    
    public List<Mots> AfficherMots() throws SQLException {
        
        String req = "SELECT * FROM motscensure ";
        ste = cnx.createStatement();
        ResultSet r = ste.executeQuery(req);
        System.out.println(r.toString());
       List<Mots> Mots = new ArrayList<>();
        while (r.next()) {
            Mots.add(new Mots(r.getInt(1), r.getString(2),r.getInt(3)));
        }
        return Mots;
    }
    
    public void SupprimerMot(Mots m) throws SQLException {
            
        int id = m.getId();
        String req = "DELETE FROM motscensure WHERE id='" + id + "' ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
    
     public void SupprimerTousMots() throws SQLException {
            
        //int id = m.getId();
        String req = "DELETE FROM motscensure ";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate(); 
    }
    public void ModifierMot(Mots m,int id) throws SQLException {
        String req = "UPDATE motscensure SET  id=?, textmot=?, gravitemot=? WHERE id='" + id + "'";
        PreparedStatement pre = cnx.prepareStatement(req);
        
        pre.setInt(1, m.getId());
        pre.setString(2, m.getTextmot());
        pre.setInt(3, m.getGravitemot());
        pre.executeUpdate();
         }
}
