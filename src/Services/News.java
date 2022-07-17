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






public class News {
    
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

    private final StringProperty style = new SimpleStringProperty(this, "Style");

    public StringProperty styleProperty() {
        return style;
    }

    public final String getStyle() {
        return styleProperty().get();
    }

    public final void setStyle(String style) {
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

    private final IntegerProperty prix = new SimpleIntegerProperty(this, "Prix");

    public IntegerProperty prixProperty() {
        return prix;
    }

    public final Integer getPrix() {
        return prixProperty().get();
    }

    public final void setPrix(Integer prix) {
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public News() {
    }

    public News(Integer id,Integer numero, String style, String description, String photo  , String etat, Integer prix,String nbpersonne) {
        setId(id);
        setStyle(style);
        setDescription(description);
        setnumero(numero);
        setNbpersonne(nbpersonne);
        setPrix(prix);
        setEtat(etat);
        setPhoto( photo);
       
    }

  

}

