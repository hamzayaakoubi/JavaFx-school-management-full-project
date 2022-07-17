/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import DataBase.connection;
import java.sql.SQLException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.swing.JOptionPane;






public class Blog{
    
    String d ="C:\\xampp2\\htdocs\\new blog\\TalandWEB\\web\\uploads\\post\\";

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");

    public IntegerProperty idProperty() {
        return id;
    }

    public final Integer getId() {
        return idProperty().get();
    }

    public final void setId(Integer id) {
        idProperty().set(id);
    }

    private final IntegerProperty style = new SimpleIntegerProperty(this, "Style");

    public IntegerProperty styleProperty() {
        return style;
    }

    public final Integer getStyle() {
        return styleProperty().get();
    }

    public final void setStyle(Integer style) {
        styleProperty().set(style);
    }

    private final StringProperty description = new SimpleStringProperty(this, "Description");

    public StringProperty descriptionProperty() {
        return description ;
    }

    public final String getDescription () {
        return descriptionProperty().get();
    }

    public final void setDescription(String description ) {
        descriptionProperty().set(description );
    }

    private final StringProperty numero = new SimpleStringProperty(this, "numero");

    public StringProperty numeroProperty() {
        return numero;
    }

    public final String getnumero() {
        return numeroProperty().get();
    }

    public final void setnumero(String numero) {
       numeroProperty().set(numero);
    }
    private final StringProperty nbpersonne = new SimpleStringProperty(this, "Nbpersonne");

    public StringProperty nbpersonneProperty() {
        return nbpersonne;
    }

    public final String getNbpersonne() {
        return nbpersonneProperty().get();
    }

    public final void setNbpersonne(String nbpersonne) {
        nbpersonneProperty().set(nbpersonne);
    }

    private final FloatProperty prix = new SimpleFloatProperty(this, "Prix");

    public FloatProperty prixProperty() {
        return prix;
    }

    public final Float getPrix() {
        return prixProperty().get();
    }

    public final void setPrix(Float prix) {
        prixProperty().set(prix);
    }

    private final StringProperty etat = new SimpleStringProperty(this, "Etat");

    public StringProperty etatProperty() {
        return etat;
    }

    public final String getEtat() {
        return etatProperty().get();
    }

    public final void setEtat(String etat) {
        etatProperty().set(etat);
    }

    
     private final StringProperty photo = new SimpleStringProperty(this, "Photo");

    public StringProperty photoProperty() {
        return photo ;
    }

    public final String getPhoto () {
        return photoProperty().get();
    }

    public final void setPhoto(String photo ) {
        photoProperty().set(photo );
    }
    
    
    
    
    
    private final StringProperty reserv = new SimpleStringProperty(this, "Reserv");

    public StringProperty reservProperty() {
        return reserv ;
    }

    public final String getReserv () {
        return reservProperty().get();
    }

    public final void setReserv (String reserv ) {
        reservProperty().set(reserv);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Blog() {
    }

    public Blog(Integer id, Integer style, String description, String numero, String photo, String etat) {
        setId(id);
        setStyle(style);
        setDescription(description);
        setnumero(numero);
       
        setPhoto( photo);
           setEtat( etat);
      
    }

 

    
  

   

   
        
        
        
        
        
        
    }



