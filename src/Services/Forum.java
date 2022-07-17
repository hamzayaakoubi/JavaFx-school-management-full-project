/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author pubkhalil
 */
public class Forum {
    
    
    
    
   String d ="C:\\xampp2\\htdocs\\new blog\\TalandWEB\\web\\uploads\\post\\";

    private final IntegerProperty id_f = new SimpleIntegerProperty(this, "id_f");

    public IntegerProperty idProperty() {
        return id_f;
    }

    public final Integer getId() {
        return idProperty().get();
    }

    public final void setId(Integer id_f) {
        idProperty().set(id_f);
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

    private final IntegerProperty numero = new SimpleIntegerProperty(this, "numero");

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public final Integer getnumero() {
        return numeroProperty().get();
    }

    public final void setnumero(Integer numero) {
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

    private final IntegerProperty etat = new SimpleIntegerProperty(this, "Etat");

    public IntegerProperty etatProperty() {
        return etat;
    }

    public final Integer getEtat() {
        return etatProperty().get();
    }

    public final void setEtat(Integer etat) {
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Forum() {
    }

    public Forum(Integer id_f, Integer style, Integer numero, String description, String photo, Integer etat) {
        setId(id_f);
        setStyle(style);
        setDescription(description);
        setnumero(numero);
       
        setPhoto( photo);
           setEtat( etat);
      
    }

 

    
  

     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
