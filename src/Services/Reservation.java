/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import DataBase.connection;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.swing.JOptionPane;

public class Reservation {

    private final StringProperty nom = new SimpleStringProperty(this, "Nom");

    public StringProperty nomProperty() {
        return nom;
    }

    public final String getNom() {
        return nomProperty().get();
    }

    public final void setNom(String nom) {
        nomProperty().set(nom);
    }

    private final StringProperty prenom = new SimpleStringProperty(this, "Prenom");

    public StringProperty prenomProperty() {
        return prenom;
    }

    public final String getPrenom() {
        return prenomProperty().get();
    }

    public final void setPrenom(String prenom) {
        prenomProperty().set(prenom);
    }
    
    
    
    
    
    private final IntegerProperty id= new SimpleIntegerProperty(this, "Id");

    public IntegerProperty idProperty() {
        return id;
    }

    public final Integer getId() {
        return idProperty().get();
    }

    public final void setId(Integer id) {
        idProperty().set(id);
    }
    
    
    
    
    
    
    
    
    
    
    

    private final StringProperty adresse = new SimpleStringProperty(this, "Adresse");

    public StringProperty adresseProperty() {
        return adresse;
    }

    public final String getAdresse() {
        return adresseProperty().get();
    }

    public final void setAdresse(String adresse) {
        adresseProperty().set(adresse);
    }

    private final IntegerProperty cin = new SimpleIntegerProperty(this, "Cin");

    public IntegerProperty cinProperty() {
        return cin;
    }

    public final Integer getCin() {
        return cinProperty().get();
    }

    public final void setCin(Integer cin) {
        cinProperty().set(cin);
    }

    private final IntegerProperty choix = new SimpleIntegerProperty(this, "Choix");

    public IntegerProperty choixProperty() {
        return choix;
    }

    public final Integer getChoix() {
        return choixProperty().get();
    }

    public final void setChoix(Integer choix) {
        choixProperty().set(choix);
    }

    public Reservation() {
    }

    public Reservation(String nom, String prenom, String adresse, Integer cin, Integer choix,Integer id) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setCin(cin);
        setChoix(choix);
    }

    public static void InsertReservation(String nom, String prenom, String adresse, Integer cin, Integer choix) throws SQLException {
        String sql = "insert into `reserv` (nom,prenom,adresse,cin,choix) values ('" + nom + "', '" + prenom + "', '" + adresse + "', '" + cin + "', '" + choix + "')";
 String sql1 = "UPDATE foyer SET reserv ='Non disponible' WHERE id in (SELECT choix FROM reserv)";
        try{

            connection.dbExecuteQuery(sql);      
                                    connection.dbExecuteQuery(sql1);


            JOptionPane.showMessageDialog(null," Réservation ajoutée avec succés ");
        }catch (SQLException e) {

        JOptionPane.showMessageDialog(null," vous etes déjà inscrit ou chambre reservée ");
        throw e ;
        }
        
        

        
        
        
        
        
        
        
        
        
    }

    public static void SuppReservation(Integer id) throws SQLException {
        String sql = "DELETE FROM `reserv` WHERE `reserv`.`id` =" + Integer.toString(id);
        try {
            connection.dbExecuteQuery(sql);
                    JOptionPane.showMessageDialog(null," Réservation supprimé");

        } catch (SQLException e) {
            
                                JOptionPane.showMessageDialog(null," Erreur de suppression");

            throw e;
        }
    }

    public static void ModifReservation(String nom, String prenom, String adr, Integer cin, Integer choix,int id) throws SQLException {
        String sql = "update reserv set nom= '" + nom + "',prenom ='" + prenom + "',adresse='" + adr + "',choix='" + choix + "' where id ='" + id + "' ";

        try {
            connection.dbExecuteQuery(sql);
                                JOptionPane.showMessageDialog(null," Réservation modifié");

        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null," Erreur de modification");

            throw e;
        }

    }
}
