/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entites.Classe;
import Entites.EmploiTemps;
import IService.EmploiTempsInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class TempService implements EmploiTempsInterface{
 Connection cnx;

      public TempService() throws SQLException, ClassNotFoundException {
       cnx = MyDBcon.getInstance().getCnx();     
    }
    @Override
    public void addTemps(EmploiTemps empt) {
        
         try { 
             Boolean test = true ;

          TempService ts = new TempService(); 
          ArrayList<EmploiTemps> listTemps = ts.getAllTemps();//get list mta3 les seanec el kol 
          //boucle for 
             for (EmploiTemps temps : listTemps) {
                 // test sur la salle s'il exit 
                 if(temps.getSalle()==(empt.getSalle())){
                     //si salle amte3na occupé fil waet adheka wala lé
                     if(empt.getDateFin().after(temps.getDateDebut() ) && empt.getDateFin().before(temps.getDateFin()) || empt.getDateFin().compareTo(temps.getDateFin())==0){
                     test=false ;
                     }
                 }
                 
             }
           if(test==true){

            String req = "INSERT INTO `emploi_temps`(`idMatiere`,`dateDebut`,`dateFin`,`salle`,`idClasse`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setInt(1, empt.getIdMatiere());
            pstm.setTimestamp(2, empt.getDateDebut());
            pstm.setTimestamp(3,  empt.getDateFin());
            pstm.setInt(4, empt.getSalle());
            pstm.setInt(5,empt.getIdClasse());
      



            pstm.executeUpdate();
            System.out.println("Seance  est Ajouté ");
           }else {
               System.out.println("Cette salle est occupé ");
           }

        } catch (SQLException ex) {
             System.out.println(ex);
        } catch (ClassNotFoundException ex) {
         Logger.getLogger(TempService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    

    @Override
    public void deleteTemps(int id) throws SQLException{
  PreparedStatement prep = cnx.prepareStatement("delete from emploi_temps where id=?");

            prep.setInt(1, id);

            prep.executeUpdate();
            System.out.println("Seance est Supprimé ");   
    }

    @Override
    public void updateTemps(EmploiTemps empt) throws SQLException{
     try {
         Boolean test = true ;
         
         TempService ts = new TempService();
         ArrayList<EmploiTemps> listTemps = ts.getAllTemps();
         for (EmploiTemps temps : listTemps) {
             if(temps.getSalle()==empt.getSalle()){
                 if(empt.getDateFin().after(temps.getDateDebut() ) && empt.getDateFin().before(temps.getDateFin()) || empt.getDateFin().compareTo(temps.getDateFin())==0){
                     test=false ;
                 }
             }
             
         }
         if(test){
             
             String query = "update emploi_temps set idMatiere=?,dateDebut=?,dateFin=? ,salle=? ,idClasse=? where id=?";
             PreparedStatement pre = cnx.prepareStatement(query);
             
             pre.setInt(1,empt.getIdMatiere());
             pre.setTimestamp(2,  empt.getDateDebut());
             pre.setTimestamp(3,  empt.getDateFin());
             pre.setInt(4, empt.getSalle());
             pre.setInt(5,  empt.getIdClasse());
         

             pre.setInt(7,  empt.getId());
             pre.executeUpdate();
             System.out.println("Seance est Modifié ");
         }
         else {
             System.out.println("Cette salee est occupée");
         }
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(TempService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

  @Override
    public ArrayList<EmploiTemps> getAllTemps() {
           ArrayList<EmploiTemps> listTemps = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM emploi_temps ";
         
         ResultSet resultat = stm.executeQuery(req);
         while (resultat.next()) {
             int id = resultat.getInt("id");
             int matiere = resultat.getInt("iDMatiere");
             Timestamp dateDebut = resultat.getTimestamp("dateDebut");
             Timestamp dateFin = resultat.getTimestamp("dateFin");
         int salle = resultat.getInt("salle");
                 

             int idClasse = resultat.getInt("idClasse");
             EmploiTemps temps = new EmploiTemps(id, matiere, dateDebut, dateFin , salle , idClasse);
             
             listTemps.add(temps);

         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listTemps;

     
    }
    public ObservableList getrmplie(int idEns){
        ObservableList<EmploiTemps> liste=FXCollections.observableArrayList();
        try {
         Statement stm = cnx.createStatement();
         String req = "SELECT *  FROM emploi_temps   ";
          PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, idEns);
         ResultSet resultat = stm.executeQuery(req);
        
         while (resultat.next()) {
          EmploiTemps et=new EmploiTemps();
          et.setDateDebut(resultat.getTimestamp("dateDebut"));
          et.setDateFin(resultat.getTimestamp("dateFin"));
          et.setId(resultat.getInt("id"));
          et.setIdClasse(resultat.getInt("idClasse"));
          et.setSalle(resultat.getInt("Salle"));
          et.setIdMatiere(resultat.getInt("idMatiere"));
         // et.setIdEnseignant(resultat.getInt("idEnseignant"));
         
         liste.add(et);
         }

     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
        
    }
        return FXCollections.observableArrayList(liste);
                }

    @Override
    public EmploiTemps getByIdTemps(int id) throws SQLException{
         String req = "SELECT * FROM emploi_temps WHERE id=? ";
         PreparedStatement pstm = cnx.prepareStatement(req);
         pstm.setInt(1, id);
         
         ResultSet resultat = pstm.executeQuery();
         if (resultat.next())
         {
              return new EmploiTemps(id, resultat.getInt("idMatiere"),resultat.getTimestamp("dateDebut"), resultat.getTimestamp("dateFin"), resultat.getInt("salle"),resultat.getInt("idClasse"));
}
         else 
             return new EmploiTemps();
         }

    @Override
    public ArrayList<EmploiTemps> getTempsByClasse(int idClasse) throws SQLException {
        ArrayList<EmploiTemps> listTemps = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT * FROM emploi_temps WHERE idClasse= ? ";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, idClasse);
         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             int id = resultat.getInt("id");
             int matiere = resultat.getInt("idMatiere");
             Timestamp dateDebut = resultat.getTimestamp("dateDebut");
             Timestamp dateFin = resultat.getTimestamp("dateFin");
         int salle = resultat.getInt("salle");
                

             
             EmploiTemps temps = new EmploiTemps(id, matiere, dateDebut, dateFin , salle , idClasse);
             
             listTemps.add(temps);

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return listTemps;
    }

    @Override
    public ArrayList<EmploiTemps> getTempsByEns(int idEns) throws SQLException {
               ArrayList<EmploiTemps> listTemps = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT * FROM emploi_temps ";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, idEns);
         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             int id = resultat.getInt("id");
             int matiere = resultat.getInt("idMatiere");
             Timestamp dateDebut = resultat.getTimestamp("dateDebut");
             Timestamp dateFin = resultat.getTimestamp("dateFin");
         int salle = resultat.getInt("salle");
              

             
             EmploiTemps temps = new EmploiTemps(id, matiere, dateDebut, dateFin , salle , idEns);
             
             listTemps.add(temps);

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return listTemps;
        
    }
     public ObservableList getTempsBy(int idEns) throws SQLException {
               ArrayList<EmploiTemps> listTemps = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT * FROM emploi_temps  ";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, idEns);
         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             int id = resultat.getInt("id");
             int matiere = resultat.getInt("idMatiere");
             Timestamp dateDebut = resultat.getTimestamp("dateDebut");
             Timestamp dateFin = resultat.getTimestamp("dateFin");
         int salle = resultat.getInt("salle");
               

             
             EmploiTemps temps = new EmploiTemps(id, matiere, dateDebut, dateFin , salle , idEns);
             
             listTemps.add(temps);

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return FXCollections.observableArrayList(listTemps);
        
    }
         public ObservableList getTempsByCla(int idClasse) throws SQLException {
        ArrayList<EmploiTemps> listTemps = new ArrayList<>();

     try {
         Statement stm = cnx.createStatement();
         String req = "SELECT * FROM emploi_temps WHERE idClasse= ? ";
         PreparedStatement psm = cnx.prepareStatement(req); 
         psm.setInt(1, idClasse);
         
         ResultSet resultat = psm.executeQuery();
         while (resultat.next()) {
             int id = resultat.getInt("id");
             int matiere = resultat.getInt("idMatiere");
             Timestamp dateDebut = resultat.getTimestamp("dateDebut");
             Timestamp dateFin = resultat.getTimestamp("dateFin");
         int salle = resultat.getInt("salle");
                

             
             EmploiTemps temps = new EmploiTemps(id, matiere, dateDebut, dateFin , salle , idClasse);
             
             listTemps.add(temps);

         }

     } catch (SQLException ex) {
         System.out.println(ex);
     }
     return FXCollections.observableArrayList(listTemps);
    }
}
