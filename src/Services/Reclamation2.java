package Services;
import DataBase.MyConnection;
import DataBase.connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Reclamation2 {

 private int id;
 
 
 public Reclamation2(){}

    

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date dateR) {
        this.dateR = dateR;
    }



    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setType(String typerec) {
        this.typerec = typerec;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public void setDateArchive(String dateArchive) {
        this.dateArchive = dateArchive;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }

    public int getId() {
        return id;
    }
    
    
    
    
    
    
     public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public int getUser() {
        return user_id;
    }
    
    
    
    
    
    
    
    
    
    

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return dateR;
    }

   

    public int getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getEtat() {
        return etat;
    }

    public String getType() {
        return typerec;
    }

    public int getArchive() {
        return archive;
    }

    public String getDateArchive() {
        return dateArchive;
    }

    public int getCorbeille() {
        return corbeille;
    }
    private String sujet;
    private String contenu;
   private Date dateR;
     private String typerec;
     private int user_id;
   
private int cin;
private String email;
private String etat="non traite" ;

    public Reclamation2(String sujet, String contenu, Date dateR, String etat, String email, String typerec) {
        this.id = id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateR = dateR;
        
       
        this.email = email;
        this.typerec = typerec;
          this.etat = etat;
    }
    
       public Reclamation2(int id,String sujet,String contenu,Date dateR,String etat,String email,String typerec) {
       
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateR = dateR;
        
       this.id = id;
        this.email = email;
        this.typerec = typerec;
          this.etat = etat;
    }

    private int archive = 0;
    private String dateArchive = null;
    private int corbeille = 0;




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    







 public static void InsertReclamation(Integer user_id,String sujet, String contenu, String dateR, String etat, String email, String typerec) throws SQLException {
        String sql = "insert into `reclamation` (user_id,sujet,contenu,dateR,etat,email,typerec) values ('" + user_id + "','" + sujet + "', '" + contenu + "', '" + dateR + "', '" + "non traité" + "', '" + email+ "', '" + typerec + "')";

        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppReclamation(Integer id) throws SQLException {
        String sql = "DELETE FROM `reclamation` WHERE `reclamation`.`id` =" + Integer.toString(id);
        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifReclamation(int id,String sujet, String contenu, String dateR, String etat, String email, String typerec) throws SQLException {
        String sql = "update reclamation set sujet= '" + sujet + "',contenu ='" + contenu + "',dateR='" + dateR + "',etat='" + etat + "',email='" + email + "',typerec='" + typerec + "' where id ='" + id + "' ";

        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de modification" + e);
            throw e;
        }

    }  



   























}



