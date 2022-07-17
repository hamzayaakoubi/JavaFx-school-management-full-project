/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Entites.Matiere;
import Services.ClasseService;
import Services.EnseignantService;
import Services.MatiereService;
import Services.SalleService;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class EmploiModel {
 private int id ; 
    private String matiere ; 
    private String salle ; 
    private String ensiegnant ; 
    private String classe ;
    private Timestamp dateDebut ; 
    private Timestamp dateFin ; 
    private int idMatiere ; 
    private int idEnseignant;
    private int idSalle ; 
    private int idClasse ; 

     public EmploiModel(int idMatiere, Timestamp dateDebut, Timestamp dateFin,  String Salle, int idClasse,int idEnseignant) {
this.idMatiere=idMatiere;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
                 this.salle = salle;

       
        this.idClasse = idClasse;
        this.idEnseignant=idEnseignant;
    }
     public EmploiModel(){
         
     }
    public EmploiModel(String salle, Timestamp dateDebut, Timestamp dateFin, int idMatiere, int idSalle, int idClasse) {
        this.salle = salle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idMatiere = idMatiere;
        this.idSalle = idSalle;
        this.idClasse = idClasse;
    }

    public EmploiModel(Timestamp dateDebut, Timestamp dateFin, int idMatiere, int idSalle, int idClasse) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idMatiere = idMatiere;
        this.idSalle = idSalle;
        this.idClasse = idClasse;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String id) throws SQLException, ClassNotFoundException {
 
         this.matiere = id;
    
     
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(int id) {
     try {
         this.salle = new SalleService().getById(id).getBloc()+""+new SalleService().getById(1).getNum();
     } catch (SQLException ex) {
         Logger.getLogger(EmploiModel.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(EmploiModel.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public String getEnsiegnant() {
        return ensiegnant;
    }

    public void setEnsiegnant(int id) {
     try {
         this.ensiegnant = new EnseignantService().getById(id).getPrenom()+""+new EnseignantService().getById(id).getNom();
     } catch (SQLException ex) {
         Logger.getLogger(EmploiModel.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(EmploiModel.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(int id) throws ClassNotFoundException {
     try {
         this.classe = new ClasseService().getById(id).getAnnee()+""+new ClasseService().getById(id).getSpecialite()+""+new Services.ClasseService().getById(id).getGroupe();
     } catch (SQLException ex) {
         Logger.getLogger(EmploiModel.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
    
    
}
