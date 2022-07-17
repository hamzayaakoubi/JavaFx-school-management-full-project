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
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmploiAdminXMLController implements Initializable {

    private ObservableList<EmploiModel> listEmplois;

    @FXML
    private TableView<EmploiModel> emploi;
    @FXML
    private TableColumn<EmploiModel, Timestamp> dateDebut;
    @FXML
    private TableColumn<EmploiModel, Timestamp> DateFin;
    @FXML
    private TableColumn<EmploiModel, String> matiere;
    @FXML
    private TableColumn<EmploiModel, String> eneignant;
    @FXML
    private TableColumn<EmploiModel, String> classe;
    @FXML
    private TableColumn<EmploiModel, String> salle;
    @FXML
    private TableColumn<EmploiModel, Integer> id;
    @FXML
    private TableColumn<EmploiModel, Integer> idEnseignant;
    @FXML
    private TableColumn<EmploiModel, Integer> idClasse;
    @FXML
    private TableColumn<EmploiModel, Integer> idSalle;
    @FXML
    private TableColumn<EmploiModel, Integer> idMatiere;
    @FXML
    private ComboBox<Integer> jj;
    @FXML
    private ComboBox<Integer> mm;
    @FXML
    private ComboBox<Integer> yyyy;
    @FXML
    private ComboBox<Integer> mmdebut;
    @FXML
    private ComboBox<Integer> hhdebut;
    @FXML
    private ComboBox<Integer> hhfin;
    @FXML
    private ComboBox<Integer> mmfin;
    @FXML
    private ComboBox<EneignantModel> enseignant;
    @FXML
    private ComboBox<MatiereModel> matiere1;
    @FXML
    private ComboBox<ClasseModel> Classe;
    @FXML
    private ComboBox<SalleModel> salle1;
    @FXML
    public Label idLabel;
    @FXML
    private ComboBox<ClasseModel> searchClasse;
    @FXML
    private ComboBox<EneignantModel> searchEns;
    @FXML
    private Label idCla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            searchClasse.valueProperty().addListener(new ChangeListener<ClasseModel>() {

                @Override
                public void changed(ObservableValue<? extends ClasseModel> observable, ClasseModel oldValue, ClasseModel newValue) {
                    try {
                        affichageByMatiere(newValue.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(EmploiAdminXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmploiAdminXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
                   searchEns.valueProperty().addListener(new ChangeListener<EneignantModel>() {

                @Override
                public void changed(ObservableValue<? extends EneignantModel> observable, EneignantModel oldValue, EneignantModel newValue) {
                    try {
                        affichageByEnsei(newValue.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(EmploiAdminXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmploiAdminXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });

            ObservableList<MatiereModel> matiereCombo = FXCollections.observableArrayList();

            MatiereService ms = new MatiereService();
            for (Matiere mat : ms.getAllMatiere()) {
                matiereCombo.add(new MatiereModel(mat.getId(), mat.getNom()));
            }
            matiere1.getItems().addAll(matiereCombo);
            matiere1.setConverter(new StringConverter<MatiereModel>() {

                @Override
                public String toString(MatiereModel object) {
                    return object.getValue();
                }

                @Override
                public MatiereModel fromString(String string) {
                    return matiere1.getValue();
                }

            });
            ObservableList<EneignantModel> enseignantCombo = FXCollections.observableArrayList();

            EnseignantService es = new EnseignantService();
            for (Enseignant ens : es.getAllEnseignant()) {
                enseignantCombo.add(new EneignantModel(ens.getId(), ens.getPrenom() + " " + ens.getNom()));
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
            searchEns.getItems().addAll(enseignantCombo);
            searchEns.setConverter(new StringConverter<EneignantModel>() {

                @Override
                public String toString(EneignantModel object) {
                    return object.getValue();
                }

                @Override
                public EneignantModel fromString(String string) {
                    return searchEns.getValue();
                }
            });
         
            ObservableList<SalleModel> SalleCombo = FXCollections.observableArrayList();

            for (Salle sall : new SalleService().getAll()) {
                SalleCombo.add(new SalleModel(sall.getId(), sall.getBloc() + "" + sall.getNum()));
            }
            salle1.getItems().addAll(SalleCombo);
            salle1.setConverter(new StringConverter<SalleModel>() {

                @Override
                public String toString(SalleModel object) {
                    return object.getValue();
                }

                @Override
                public SalleModel fromString(String string) {
                    return salle1.getValue();
                }
            });
            ObservableList<ClasseModel> ClasseCombo = FXCollections.observableArrayList();

            for (Classe classe : new ClasseService().getAllClasses()) {
                ClasseCombo.add(new ClasseModel(classe.getId(), classe.getAnnee() + " " + classe.getSpecialite() + " " + classe.getSpecialite()));
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
               searchClasse.getItems().addAll(ClasseCombo);
            searchClasse.setConverter(new StringConverter<ClasseModel>() {

                @Override
                public String toString(ClasseModel object) {
                    return object.getValue();
                }

                @Override
                public ClasseModel fromString(String string) {
                    return searchClasse.getValue();
                }
            });
            
            mm.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
            jj.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
            yyyy.getItems().addAll(2020, 2021, 2022, 2023, 2024, 2025);
            for (int i = 1; i <= 60; i++) {
                if (i <= 24) {
                    hhdebut.getItems().add(i);
                    hhfin.getItems().add(i);

                }
                mmdebut.getItems().add(i);
                mmfin.getItems().add(i);
            }
            affichage();
            setcellValue();
            // TODO
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void affichage() throws SQLException, ClassNotFoundException {
        listEmplois = FXCollections.observableArrayList();

        TempService ts = new TempService();
        for (EmploiTemps emp : ts.getAllTemps()) {
            EmploiModel empMo = new EmploiModel();
            empMo.setClasse(emp.getIdClasse());
            empMo.setDateDebut(emp.getDateDebut());
            empMo.setDateFin(emp.getDateFin());
            empMo.setEnsiegnant(emp.getIdEnseignant());
            empMo.setMatiere(new MatiereService().getById(emp.getIdMatiere()).getNom());
            empMo.setSalle(emp.getSalle());
            empMo.setId(emp.getId());
            empMo.setIdClasse(emp.getIdClasse());
            empMo.setIdSalle(emp.getSalle());
            empMo.setIdEnseignant(emp.getIdEnseignant());
            empMo.setIdMatiere(emp.getIdMatiere());
            listEmplois.add(empMo);
        }
        emploi.setItems(listEmplois);
        matiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("matiere"));
        salle.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("salle"));
        classe.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("classe"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateDebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateFin"));
        eneignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("ensiegnant"));
        id.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("id"));
        idClasse.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
        idEnseignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idEnseignant"));
        idSalle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idSalle"));
        idMatiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idMatiere"));

    }

    public void affichageByMatiere(int idd) throws SQLException, ClassNotFoundException {
        listEmplois = FXCollections.observableArrayList();

        TempService ts = new TempService();
        for (EmploiTemps emp : ts.getTempsByClasse(idd)) {
            EmploiModel empMo = new EmploiModel();
            empMo.setClasse(emp.getIdClasse());
            empMo.setDateDebut(emp.getDateDebut());
            empMo.setDateFin(emp.getDateFin());
            empMo.setEnsiegnant(emp.getIdEnseignant());
            empMo.setMatiere(new MatiereService().getById(emp.getIdMatiere()).getNom());
            empMo.setSalle(emp.getSalle());
            empMo.setId(emp.getId());
            empMo.setIdClasse(emp.getIdClasse());
            empMo.setIdSalle(emp.getSalle());
            empMo.setIdEnseignant(emp.getIdEnseignant());
            empMo.setIdMatiere(emp.getIdMatiere());
            listEmplois.add(empMo);
        }
        emploi.setItems(listEmplois);
        matiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("matiere"));
        salle.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("salle"));
        classe.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("classe"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateDebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateFin"));
        eneignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("ensiegnant"));
        id.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("id"));
        idClasse.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
        idEnseignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idEnseignant"));
        idSalle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idSalle"));
        idMatiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idMatiere"));

    }

    
    public void affichageByEnsei(int idd) throws SQLException, ClassNotFoundException {
        listEmplois = FXCollections.observableArrayList();

        TempService ts = new TempService();
        for (EmploiTemps emp : ts.getTempsByEns(idd)) {
            EmploiModel empMo = new EmploiModel();
            empMo.setClasse(emp.getIdClasse());
            empMo.setDateDebut(emp.getDateDebut());
            empMo.setDateFin(emp.getDateFin());
            empMo.setEnsiegnant(emp.getIdEnseignant());
            empMo.setMatiere(new MatiereService().getById(emp.getIdMatiere()).getNom());
            empMo.setSalle(emp.getSalle());
            empMo.setId(emp.getId());
            empMo.setIdClasse(emp.getIdClasse());
            empMo.setIdSalle(emp.getSalle());
            empMo.setIdEnseignant(emp.getIdEnseignant());
            empMo.setIdMatiere(emp.getIdMatiere());
            listEmplois.add(empMo);
        }
        emploi.setItems(listEmplois);
        matiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("matiere"));
        salle.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("salle"));
        classe.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("classe"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateDebut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<EmploiModel, Timestamp>("dateFin"));
        eneignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, String>("ensiegnant"));
        id.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("id"));
        idClasse.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idClasse"));
        idEnseignant.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idEnseignant"));
        idSalle.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idSalle"));
        idMatiere.setCellValueFactory(new PropertyValueFactory<EmploiModel, Integer>("idMatiere"));

    }
    private void setcellValue() { //set the value in the cell 

        emploi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                EmploiModel l = emploi.getItems().get(emploi.getSelectionModel().getSelectedIndex());
                jj.setValue(l.getDateDebut().getDay() + 1);
                mm.setValue(l.getDateDebut().getMonth() + 1);
                yyyy.setValue(l.getDateDebut().getYear() + 1900);
                mmdebut.setValue(l.getDateDebut().getMinutes());
                mmfin.setValue(l.getDateFin().getMinutes());
                hhdebut.setValue(l.getDateDebut().getHours() + 1);
                hhfin.setValue(l.getDateFin().getHours() + 1);
                matiere1.setValue(new MatiereModel(l.getId(), l.getMatiere()));
                salle1.setValue(new SalleModel(l.getIdSalle(), l.getSalle()));
                enseignant.setValue(new EneignantModel(l.getIdEnseignant(), l.getEnsiegnant()));
                Classe.setValue(new ClasseModel(l.getIdClasse(), l.getClasse()));
                idLabel.setVisible(false);
               idCla.setVisible(false);
                idLabel.setText(Integer.toString(l.getId()));
                idCla.setText(Integer.toString(l.getIdClasse()));
                

            }
        });
    }

    @FXML
    private void update(ActionEvent event) throws SQLException, ClassNotFoundException, AWTException {
        EmploiTemps emploi = new EmploiTemps();
        emploi.setId(Integer.parseInt(idLabel.getText()));
        String datedebut = yyyy.getValue() + "-" + mm.getValue() + "-" + jj.getValue() + " " + hhdebut.getValue() + ":" + mmdebut.getValue() + ":00.0";
        String datefin = yyyy.getValue() + "-" + mm.getValue() + "-" + jj.getValue() + " " + hhfin.getValue() + ":" + mmfin.getValue() + ":00.0";

        java.sql.Timestamp datedeb = java.sql.Timestamp.valueOf(datedebut);
        java.sql.Timestamp datefi = java.sql.Timestamp.valueOf(datefin);
        TempService ts = new TempService();
        EmploiTemps emploiTemps =  new EmploiTemps(Integer.parseInt(idLabel.getText()), matiere1.getSelectionModel().getSelectedItem().getId(), datedeb, datefi, salle1.getSelectionModel().getSelectedItem().getId(), Classe.getSelectionModel().getSelectedItem().getId());
        ts.updateTemps(emploiTemps);
        String subject = " Votre Seance dz  "+matiere1.getSelectionModel().getSelectedItem().getValue()+" a été modifiée veuillez consulter Votre Emploi du temps";
      sendEmail("rabiaachaaben91@gmail.com", "Seance Modifiée", subject);           

for(Enseignant ens :new EnseignantService().getClasseById(Integer.parseInt(idCla.getText()))){
    
    
   
}

        affichage();
         Notifications11 n = new Notifications11();
        n.displayTray("Seance", "bien modifier"); 
         n.displayTray("Bonjour", "votre seance est modifie consulter votre emploi du temps"); 
    }

    
    
    
    
    
    @FXML
    private void delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        new TempService().deleteTemps(Integer.parseInt(idLabel.getText()));
        affichage();
    }
     public static void sendEmail(String toEmail, String subject, String body) {
        final String username = "xiheb01@gmail.com";
        final String password = "98945544";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");


        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date(0));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void retour(ActionEvent event) {
        try {
            Classe.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("MenuFXML.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AddClasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          


}
