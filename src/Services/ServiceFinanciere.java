/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Paie_Prof;
import  Entites.Paiment_eleve;
import Entites.User;
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
public class ServiceFinanciere  {
        Connection cnx;
        public ServiceFinanciere(){
            cnx = MyDB.getInstance().getConnexion();
        }


    public int ajouterPaiement(Paie_Prof nouveauPaiement) {
       int a=0;
        try {
              //  String requeteAjoutPrepared = "INSERT INTO paiment (cin,nom,prenom,nbre_heure,salaire_heure,montant,mode_reg)VALUES (1,"jj","kk",11,50,44,"ff")";
          String requeteAjoutPrepared = "INSERT INTO paiment (cin,nom,prenom,nbre_heure,salaire_heure,montant,mode_reg,email)VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstAjoutPaiement = cnx.prepareStatement(requeteAjoutPrepared,Statement.RETURN_GENERATED_KEYS);
        //PreparedStatement pstAjoutPaiement=(PreparedStatement)cnx.prepareStatement(requeteAjoutPrepared);
           // pstAjoutPaiement.setTimestamp(2, new java.sql.Timestamp(nouveauPaiement.getDate().getTime()));
            pstAjoutPaiement.setDouble(6, nouveauPaiement.calculSalaire());
            pstAjoutPaiement.setFloat(4, nouveauPaiement.getNbre_heure());
             //pstAjoutPaiement.setFloat(5, nouveauPaiement.getSalaire_heure());
           //   pstAjoutPaiement.setDouble(7, nouveauPaiement.getMontant());
           pstAjoutPaiement.setString(8, nouveauPaiement.getEmail());
            pstAjoutPaiement.setString(7, nouveauPaiement.getMode_reg());
            pstAjoutPaiement.setFloat(5, nouveauPaiement.getSalaire_heure());
              pstAjoutPaiement.setString(3,nouveauPaiement.getPrenom());
       pstAjoutPaiement.setString(2,nouveauPaiement.getNom());
              pstAjoutPaiement.setString(1, nouveauPaiement.getCin());
                          
               a=pstAjoutPaiement.executeUpdate();
              // cnx.close();
             ResultSet rst = pstAjoutPaiement.getGeneratedKeys();
             if(rst.next()){
                System.out.println("a :"+a);
               a=rst.getInt(1);
            }
              cnx.close();
        } catch (SQLException ex) {
           // Logger.getLogger(ServiceFinanciere.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
    }

 
    public void supprimerPaiement(int num_paiment) {
       try {
            String requeteSuppression = "DELETE FROM paiment WHERE num_paiment=?";
            PreparedStatement pst = cnx.prepareStatement(requeteSuppression);
            pst.setInt(1, num_paiment);
            pst.executeUpdate();
            System.out.println("Paiement supprimé.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Paie_Prof m) {
 
        PreparedStatement ps;
        try {
            ps =cnx.prepareStatement("delete from paiment where num_paiment = ?");
           
            ps.setInt(1, m.getNum_paiment());
            ps.execute();
        } catch (Exception e) {
          
        }
    }
 public  int Update(Paie_Prof emp) {
		int st=0;
		try{
			String sql="UPDATE paiment SET `mode_reg`=?,"
					+ "`nbre_heure`=?,"
					+ "`salaire_heure`=?,"
					+ "`montant`=?"
					
					+ "WHERE num_paiment=?";
			Connection con=MyDB.getInstance().getConnexion();
			PreparedStatement preparedStatement=(PreparedStatement)con.prepareStatement(sql);
			preparedStatement.setString(1,emp.getMode_reg());
			preparedStatement.setFloat(2,emp.getSalaire_heure());
			preparedStatement.setFloat(3,emp.getNbre_heure());
			preparedStatement.setDouble(4,emp.calculSalaire());
			
			
			preparedStatement.setInt(5,emp.getNum_paiment());
		st=preparedStatement.executeUpdate();
		//con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
   
   

 
    public List<Paie_Prof> getListePaiements() {
      List<Paie_Prof> paiment = new ArrayList<>();
        try {
            String req = "select * from paiment";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Paie_Prof commentaire = new Paie_Prof(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4),rs.getDouble(5),rs.getString(6),rs.getDate(7));
                paiment.add(commentaire);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return paiment;
    }
    public ObservableList getListePaiement() {
  System.out.println("Liste des paiment");
        
  ObservableList<Paie_Prof> listeDesPaiements = FXCollections.observableArrayList();
  //List<Paiment_eleve> listeDesPaiements = new ArrayList<>();
        String req = "SELECT * FROM paiment";
        try {
            Statement selectArticlesStatement = cnx.createStatement();
            ResultSet resultSetListPaiements = selectArticlesStatement.executeQuery(req);
            while (resultSetListPaiements.next()) {
                Paie_Prof paiement = new Paie_Prof();
                paiement.setNum_paiment(resultSetListPaiements.getInt("num_paiment"));
                 paiement.setCin(resultSetListPaiements.getString("cin"));
                // paiement.setNbre_moins(resultSetListPaiements.getInt("nbre_mois"));
                 paiement.setSalaire_heure(resultSetListPaiements.getFloat("salaire_heure"));
                  paiement.setNbre_heure(resultSetListPaiements.getFloat("nbre_heure"));
                 paiement.setMontant(resultSetListPaiements.getDouble("montant"));
                 paiement.setNom(resultSetListPaiements.getString("nom"));
    
                paiement.setPrenom(resultSetListPaiements.getString("prenom"));
                
           
                     paiement.setDate(resultSetListPaiements.getTimestamp("date"));
                
                ;
                listeDesPaiements.add(paiement);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(listeDesPaiements);
    }

    public Paie_Prof getPaimentParId(int num_paiment) {
       Paie_Prof paiment=new Paie_Prof();
       try {
            String requeteSelectP = "SELECT * FROM paiment WHERE num_paiment=?";
            PreparedStatement selectUnPStatement = this.cnx.prepareStatement(requeteSelectP);
            selectUnPStatement.setInt(1, num_paiment);

            ResultSet resultSetListP = selectUnPStatement.executeQuery();

            resultSetListP.next();
              paiment.setNum_paiment(resultSetListP.getInt("num_paiment"));
               paiment.setCin(resultSetListP.getString("cin"));
               paiment.setNbre_heure(resultSetListP.getFloat("nbre_heure"));
                paiment.setSalaire_heure(resultSetListP.getInt("salaire_heure"));
               
                paiment.setMontant(resultSetListP.getDouble("montant"));
                paiment.setMode_reg(resultSetListP.getString("mode_reg"));
                 paiment.setDate(resultSetListP.getTimestamp("date"));
               
                
            } catch (SQLException ex) {
            Logger.getLogger(ServiceFinanciere.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paiment;
    }

    
    public void modifier_paiment_prof(Paie_Prof p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public ObservableList<Paie_Prof> likeByName(String a) {
        
        ObservableList<Paie_Prof> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from paiment where nom like '%"+a+"%'";
            ResultSet rs =cnx.createStatement().executeQuery(sql);
            while (rs.next()) {  
                Paie_Prof m = new Paie_Prof();
                m.setNum_paiment(rs.getInt(1));
                m.setCin(rs.getString(2));
                m.setNom(rs.getString(3));
                m.setPrenom(rs.getString(4));
                listData.add(m);
            }
        } catch (Exception ex) {
            
        }
        return listData;
    }
 
}
