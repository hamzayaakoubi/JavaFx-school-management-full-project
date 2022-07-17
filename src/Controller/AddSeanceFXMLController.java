/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Controller;

import Entites.Classe;
import Entites.EmploiTemps;
import Entites.Enseignant;
import Entites.Matiere;
import Entites.Notifications11;
import Entites.Salle;
import Services.ClasseService;
import Services.EnseignantService;
import Services.MatiereService;
import Services.SalleService;
import Services.TempService;
import static com.oracle.jrockit.jfr.Transition.To;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddSeanceFXMLController implements Initializable {
    @FXML
    private ComboBox<MatiereModel> matiere;
    @FXML
    private ComboBox<EneignantModel> enseignant;
    @FXML
    private ComboBox<SalleModel> salle;
    @FXML
    private ComboBox<ClasseModel> Classe;
    @FXML
    private ComboBox<Integer> mm;
    @FXML
    private ComboBox<Integer> jj;
    @FXML
    private ComboBox<Integer> yyyy;
    @FXML
    private ComboBox<Integer> hhdebut;
    @FXML
    private ComboBox<Integer> mmdebut;
    @FXML
    private ComboBox<Integer> mmfin;
    @FXML
    private ComboBox<Integer> hhfin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try { 
           ObservableList<MatiereModel> matiereCombo =  FXCollections.observableArrayList();
      

            MatiereService ms = new MatiereService();
            for (Matiere mat : ms.getAllMatiere()) {
                matiereCombo.add(new MatiereModel(mat.getId(), mat.getNom()));
            }   
          matiere.getItems().addAll(matiereCombo);
          matiere.setConverter(new StringConverter<MatiereModel>() {

               @Override
               public String toString(MatiereModel object) {
                   return object.getValue();
               }

               @Override
               public MatiereModel fromString(String string) {
                   return matiere.getValue();
               }
               
           });
                     ObservableList<EneignantModel> enseignantCombo =  FXCollections.observableArrayList();

            EnseignantService es = new EnseignantService(); 
            for(Enseignant ens : es.getAllEnseignant()){
                enseignantCombo.add(new EneignantModel(ens.getId(),ens.getPrenom()+" "+ens.getNom()));
            }
            enseignant.getItems().addAll(enseignantCombo);
            enseignant.setConverter(new StringConverter<EneignantModel>() {

               @Override
               public String toString(EneignantModel object) {
                   return object.getValue();
               }

               @Override
               public EneignantModel fromString(String string) {
return enseignant.getValue();
               }
           });
                ObservableList<SalleModel> SalleCombo =  FXCollections.observableArrayList();

            for (Salle sall : new SalleService().getAll()) {
                SalleCombo.add(new SalleModel(sall.getId(), sall.getBloc()+""+sall.getNum()));       
            }
            salle.getItems().addAll(SalleCombo);
            salle.setConverter(new StringConverter<SalleModel>() {

               @Override
               public String toString(SalleModel object) {
return object.getValue();}

               @Override
               public SalleModel fromString(String string) {
return salle.getValue();
               }
           });
                ObservableList<ClasseModel> ClasseCombo =  FXCollections.observableArrayList();

             for (Classe classe : new ClasseService().getAllClasses()) {
                ClasseCombo.add(new ClasseModel(classe.getId(), classe.getAnnee()+" "+classe.getSpecialite()+" "+classe.getSpecialite()));
             }
             Classe.getItems().addAll(ClasseCombo);
             Classe.setConverter(new StringConverter<ClasseModel>() {

               @Override
                   public String toString(ClasseModel object) {
                   return object.getValue();
               }

               @Override
               public ClasseModel fromString(String string) {
return Classe.getValue();
               }
           });
             
            mm.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
            jj.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
            yyyy.getItems().addAll(2020,2021,2022,2023,2024,2025);
            for(int i=1;i<=60;i++){
                if(i<=24){
                hhdebut.getItems().add(i);
                hhfin.getItems().add(i);

                }
                mmdebut.getItems().add(i);
                mmfin.getItems().add(i);
            }
            
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }    
    @FXML
    public void addSeance() throws SQLException, ClassNotFoundException, AWTException{
        String datedebut = yyyy.getValue()+"-"+mm.getValue()+"-"+jj.getValue()+" "+hhdebut.getValue()+":"+mmdebut.getValue()+":00.0";
        String datefin = yyyy.getValue()+"-"+mm.getValue()+"-"+jj.getValue()+" "+hhfin.getValue()+":"+mmfin.getValue()+":00.0";

               java.sql.Timestamp dateDebut = java.sql.Timestamp.valueOf(datedebut);
               java.sql.Timestamp dateFin = java.sql.Timestamp.valueOf(datefin);
                TempService ts = new TempService(); 
                ts.addTemps(new EmploiTemps(matiere.getSelectionModel().getSelectedItem().getId(), dateDebut, dateFin, salle.getSelectionModel().getSelectedItem().getId(), Classe.getSelectionModel().getSelectedItem().getId()));
        Notifications11 n = new Notifications11();
        n.displayTray("Seance", "bien insérér"); 
        
	}
       
        
    
    

    @FXML
    private void retour(ActionEvent event) {
        try {
            yyyy.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("MenuFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          
}
