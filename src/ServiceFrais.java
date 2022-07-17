/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Frais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MyDB;

/**
 *
 * @author Dhoha
 */
public class ServiceFrais {
    Connection cnx;
        public ServiceFrais(){
    cnx = MyDB.getInstance().getConnexion();
    }
        public int addFrais(Frais f) {
int x=0;
        try {
            String requete = "INSERT INTO frais (type_frais,montant) VALUES (?,?)";
            PreparedStatement ps =cnx.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, f.getType_frais());
              ps.setFloat(2, f.getMontant());
            
           x= ps.executeUpdate();
                ResultSet rst = ps.getGeneratedKeys();

//ps.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
return x;
    }
           public void InsertClub(String type_frais,float montant) throws SQLException {
                try {
        String sql = "insert into frais (type_frais,montant) values ('" +type_frais + "', '" + montant + "')";

       
            Statement requete = cnx.createStatement();
            MyDB.getInstance().getConnexion();
            requete.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }
        					 

            public int updateFrais(Frais p) {
                int x=0;
        System.out.println("Frais "+p.getId());
		try {
			Statement requete = cnx.createStatement();
			String req = "UPDATE frais SET type_frais= '"+ p.getType_frais()+"', montant='"+p.getMontant()+"' WHERE id='"+p.getId()+"'";
                        
					

			 x =requete.executeUpdate(req);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }

    public int deleteFrais(int id) {
        int x=0;
        String reqDel="DELETE FROM frais WHERE id=? ";
         try {
            PreparedStatement preparedStatement = cnx.prepareStatement(reqDel);
            preparedStatement.setInt(1, id);
           x= preparedStatement.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }  
         return x;
    }
        public List<Frais> listerFrais() {
        List<Frais> frais = new ArrayList<>();
        try {
            String req = "select * from frais";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Frais commentaire = new Frais(rs.getInt(1),rs.getString(2),rs.getFloat(3));
                frais.add(commentaire);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return frais;
    }
    public ObservableList<Frais> getAll() {
      
        ObservableList<Frais> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from frais";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {  
                Frais m = new Frais();
              m.setFris(rs.getString(2));
              m.setId(rs.getInt(1));
              m.setMontant(rs.getFloat(3));
                listData.add(m);
            }
        } catch (Exception ex) {
            Logger.getLogger(ServiceFrais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }

   
}
