package Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import Entites.Paiment_eleve;
import Entites.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MyDB;

/**
 *
 * @author Dhoha
 */
public class ServicePaiment_eleve{
      Connection cnx;
      // Stripe.apiKey = "sk_test_pnfuYUNCotWhyq7uOfqDmuGE";
    public ServicePaiment_eleve(){
    cnx = MyDB.getInstance().getConnexion();
    }
     public  int Update(Paiment_eleve emp) {
		int st=0;
		try{
			String sql="UPDATE finance SET `mode_reglement`=?,"
					+ "`montant_totale`=?,"
					+ "`type_frais`=?,"
					+ "`remise`=?,"
					+ "`montant`=?,"
					+ "`cin`=?,"
					+ "`mois`=?,"
					+ "`mode_paiment`=?,"
					+ "`reste`=? "
					+ "WHERE num_recu=?";
			Connection con=MyDB.getInstance().getConnexion();
			PreparedStatement preparedStatement=(PreparedStatement)con.prepareStatement(sql);
			preparedStatement.setString(1,emp.getMode_reglement());
			preparedStatement.setFloat(2,emp.getMontant_total());
			preparedStatement.setString(3,emp.getType_frais());
			preparedStatement.setInt(4,emp.getRemise());
			preparedStatement.setDouble(5,emp.getMontant());
			preparedStatement.setString(6,emp.getCin());
			preparedStatement.setString(7,emp.getMois());
			preparedStatement.setString(8,emp.getMode_paiment());
			preparedStatement.setFloat(9,emp.getReste());
			
			preparedStatement.setInt(10,emp.getNum_recu());
		st=preparedStatement.executeUpdate();
		//con.close();
               // preparedStatement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}


    public int ajouterPaiement(Paiment_eleve nouveauPaiement) {
         int a=0;
    try {
        String query = "SELECT * FROM fos_user where roles=('eleve')";
    
         Statement stp = cnx.createStatement();
         ResultSet rs = stp.executeQuery(query);
        
       
            while (rs.next()) {
                User paiement = new User();
//                paiement.setCin(rs.getInt(1));
            }  
      
          String query4= " SELECT mois FROM finance WHERE num_recu = (SELECT MAX(num_recu) FROM finance)";
           //String query2 = "SELECT DATE_ADD('2017-06-15', INTERVAL +1 MONTH)";
           Statement st3 = cnx.createStatement();
         ResultSet rs3 = st3.executeQuery(query4);
     String query2 = "select now() + interval 1 month";
         Statement stpp = cnx.createStatement();
         ResultSet rss = stpp.executeQuery(query2);
          
         rss.next();
              String query3 = "select now() + interval 2 month";
         Statement stppp = cnx.createStatement();
         ResultSet rsss = stppp.executeQuery(query3);
          
         rsss.next();
      
            String requeteAjoutPrepared = "INSERT INTO finance (mode_reglement,montant_totale,type_frais,remise,montant,cin,mois,mode_paiment,reste,nom,prenom,email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
           // PreparedStatement pstAjoutPaiement = cnx.prepareStatement(requeteAjoutPrepared);
           //PreparedStatement pstAjoutPaiement = cnx.prepareStatement(requeteAjoutPrepared,Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstAjoutPaiemen = cnx.prepareStatement(requeteAjoutPrepared,Statement.RETURN_GENERATED_KEYS);
            //pstAjoutPaiement.setInt(1, nouveauPaiement.getNum_recu());
            //pstAjoutPaiement.setTimestamp(2, new java.sql.Timestamp(nouveauPaiement.getDate().getTime()));
             pstAjoutPaiemen.setInt(4,nouveauPaiement.getRemise() );
            pstAjoutPaiemen.setDouble(5, nouveauPaiement.calculmontant());
            pstAjoutPaiemen.setFloat(2, nouveauPaiement.calculmontantTotal());
            pstAjoutPaiemen.setString(1, nouveauPaiement.getMode_reglement());
            //pstAjoutPaiement.setInt(2, nouveauPaiement.getNbre_moins());
            pstAjoutPaiemen.setString(12,nouveauPaiement.getEmail());
             pstAjoutPaiemen.setString(11,nouveauPaiement.getPrenom());
              pstAjoutPaiemen.setString(10,nouveauPaiement.getNom());
                //pstAjoutPaiement.setString(10,rsss.getString(1));
                //pstAjoutPaiement.setDate(10,nouveauPaiement.getDate());
                    pstAjoutPaiemen.setFloat(9, nouveauPaiement.reste());
                        pstAjoutPaiemen.setString(6,nouveauPaiement.getCin());
                    // pstAjoutPaiement.setString(7, rss.getString(1));
                     pstAjoutPaiemen.setString(7, nouveauPaiement.getMois());
                            pstAjoutPaiemen.setString(8, nouveauPaiement.getMode_paiment());
                                pstAjoutPaiemen.setString(3, nouveauPaiement.getType_frais());
            a=pstAjoutPaiemen.executeUpdate();
            ResultSet rst = pstAjoutPaiemen.getGeneratedKeys();
            if(rst.next()){
               // if(Paiment_eleve.getMois()=rss.getString(9))
                System.out.println("a :"+a);
                a=rst.getInt(1);
            }
           // cnx.close();
           // pstAjoutPaiemen.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePaiment_eleve.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }

    public int supprimerPaiement(int num_recu) {
        int x =0;
   try {
            String requeteSuppression = "DELETE FROM finance WHERE num_recu=?";
            PreparedStatement pst = cnx.prepareStatement(requeteSuppression);
            pst.setInt(1, num_recu);
           x= pst.executeUpdate();
            System.out.println("Paiement supprimé.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return x;
    }
    
    public ObservableList getListePaiements() {
  System.out.println("Liste des paiment");
        
  ObservableList<Paiment_eleve> listeDesPaiements = FXCollections.observableArrayList();
  //List<Paiment_eleve> listeDesPaiements = new ArrayList<>();
        String req = "SELECT * FROM finance";
        try {
            Statement selectArticlesStatement = cnx.createStatement();
            ResultSet resultSetListPaiements = selectArticlesStatement.executeQuery(req);
            while (resultSetListPaiements.next()) {
                Paiment_eleve paiement = new Paiment_eleve();
                paiement.setNum_recu(resultSetListPaiements.getInt("num_recu"));
                 paiement.setMode_reglement(resultSetListPaiements.getString("mode_reglement"));
                // paiement.setNbre_moins(resultSetListPaiements.getInt("nbre_mois"));
                 paiement.setMontant_total(resultSetListPaiements.getInt("montant_totale"));
                 paiement.setType_frais(resultSetListPaiements.getString("type_frais"));
                 paiement.setRemise(resultSetListPaiements.getInt("remise"));
    
                paiement.setMontant(resultSetListPaiements.getDouble("montant"));
                paiement.setCin(resultSetListPaiements.getString("cin"));
                paiement.setMois(resultSetListPaiements.getString("mois"));
                paiement.setMode_paiment(resultSetListPaiements.getString("mode_paiment"));
               
                paiement.setNom(resultSetListPaiements.getString("nom"));
                paiement.setPrenom(resultSetListPaiements.getString("prenom"));
                 paiement.setEmail(resultSetListPaiements.getString("email"));
                
                paiement.setReste(resultSetListPaiements.getInt("reste"));
                     paiement.setDate(resultSetListPaiements.getTimestamp("date"));
                ;
                
                listeDesPaiements.add(paiement);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(listeDesPaiements);
    }

   
    public Paiment_eleve getPaimentParId(int num_recu) {
       Paiment_eleve paiment=new Paiment_eleve();
       try {
            String requeteSelectP = "SELECT * FROM finance WHERE num_recu=?";
            PreparedStatement selectUnPStatement = this.cnx.prepareStatement(requeteSelectP);
            selectUnPStatement.setInt(1, num_recu);

            ResultSet resultSetListP = selectUnPStatement.executeQuery();

            resultSetListP.next();
              paiment.setNum_recu(resultSetListP.getInt("num_recu"));
              paiment.setMode_reglement(resultSetListP.getString("mode_reglement"));
              //paiment.setNbre_moins(resultSetListP.getInt("nbre_mois"));
              paiment.setMontant_total(resultSetListP.getInt("montant_totale"));
              paiment.setType_frais(resultSetListP.getString("type_frais"));
               paiment.setRemise(resultSetListP.getInt("remise"));
                paiment.setMontant(resultSetListP.getDouble("montant"));
                paiment.setCin(resultSetListP.getString("cin"));
                paiment.setMois(resultSetListP.getString("mois"));
                paiment.setMode_paiment(resultSetListP.getString("mode_paiment"));
                paiment.setReste(resultSetListP.getInt("reste"));
                paiment.setDate(resultSetListP.getTimestamp("date"));
                
            } catch (SQLException ex) {
            Logger.getLogger(ServicePaiment_eleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paiment;
    }

   
    public void modifier_paiment_eleve(int num_recu,String mode_reg) {
       
    }
     public void modifier(Paiment_eleve p) {
         
     }
     
     	public int ModifierPaiment(Paiment_eleve p) {
		//connection = ConnexionDB.getConnected();
		int x =0;
		System.out.println("PAimnet "+p.getNum_recu());
		try {
			Statement requete = cnx.createStatement();
			String req = "UPDATE finance SET mode_reglement =  '"
					+ p.getMode_reglement()+ 
					 ",montant_totale='"+p.calculmontantTotal()+"',type_frais="
					+ p.getType_frais() + ",remise="
					+ p.getRemise()+ ",montant="
					+p.getMontant()+"cin ="+p.getUser().getCin()+"mois= "+p.getMois()+
                                "mode_paiment = "+p.getMode_paiment()+"reste = "+p.getReste()+"date = "+p.getDate()
					+ " WHERE num_recu="+p.getNum_recu()+"";

			 x =requete.executeUpdate(req);
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
       
        	
     public int countPaiment() {
        String requete = "select SUM(montant) from finance";
        int count = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                count = (int) resultat.getDouble(1);
            }
            return count;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Users " + ex.getMessage());
            return 0;
        }
    }
       public void trier() {
        PreparedStatement pt;
        String req = "select * from finance ORDER BY cin";
        try {
           Statement pts = cnx.createStatement();
            ResultSet rs = pts.executeQuery(req);
            System.out.println("tri par cin: ");

            while (rs.next()) {
                System.out.println(rs.getInt("cin"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    } 

    
      
    public void rechercherPaimentparcaractere(String caractere) {

        String req = "select * from finance where mode_reglement  LIKE '" + caractere + "%'";
        try {
           Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                System.out.println("le mode que vous rechercher: " + rs.getString("mode_reglement"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur recherche");
        }
    }
    
  
    public void filtre(String caractere) {

        
       String req = "  SELECT * FROM finance WHERE mode_reglement like '"+ "%"+caractere+"%"+"' or type_frais like '"+ "%"+caractere+"%"+"' ORDER BY id DESC " ;
        try {
           Statement ste = cnx.createStatement();
             ResultSet rs = ste.executeQuery(req);
            System.out.println("filtre par id desc: ");

            while (rs.next()) {
                
                System.out.println("Cin : " + rs.getInt("cin") + "  mode_raglement :" + rs.getString("mode_reglement") + "  type_frais :" + 
                        rs.getString(" type_frais")
                        );
            

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    }
        public ObservableList<Paiment_eleve> recherchePaiment(String motCle) {
        System.out.println("Liste paiment recherche");
        List<Paiment_eleve> listeDesArticles = new ArrayList<>();
        String req = "SELECT * FROM finance WHERE nom LIKE '%" + motCle + "%'";
        //String req = "SELECT * FROM article WHERE titre LIKE '%" + motCle + "%' OR description LIKE '%"+motCle+"%' OR texte LIKE '%"+motCle+"%'";
        try {
            Statement selectArticlesStatement = cnx.createStatement();
            ResultSet resultSetListArticles = selectArticlesStatement.executeQuery(req);
            while (resultSetListArticles.next()) {
                Paiment_eleve article = new Paiment_eleve();
                article.setNum_recu(resultSetListArticles.getInt("num_recu"));
                article.setNom(resultSetListArticles.getString("nom"));
                article.setPrenom(resultSetListArticles.getString("prenom"));
               
                listeDesArticles.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FXCollections.observableArrayList(listeDesArticles);
    }
    
}
