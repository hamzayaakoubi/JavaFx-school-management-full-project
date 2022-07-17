package Controller;


import com.jfoenix.controls.JFXButton;


import Services.Reclamation2;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import DataBase.MyConnection;
import DataBase.TrayIconDemo;

import java.sql.Statement;
import DataBase.connection;
import Entites.Notifications;
import Services.Reclamationservice;
import Services.ServiceClub;
import Utils.Database2;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.FileOutputStream;

import java.sql.Date;
import java.sql.DriverManager;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.naming.spi.DirStateFactory.Result;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation2> clubTable;
    @FXML
    private TextField id;
    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;
    @FXML
    private DatePicker dateR;
    @FXML
    private TextField email;
    @FXML
    private TextField etat;
    @FXML
    private TextField typerec;
   ObservableList<Reclamation2> observableList;
    
    @FXML
    private Button ajouter;

    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    
  @FXML
    private Pane mainpane;
    @FXML
    private Label msg;
    @FXML
    private TableColumn<Reclamation2, Integer> idcol;
    @FXML
    private TableColumn<Reclamation2, String> sujetcol;
    @FXML
    private TableColumn<Reclamation2, String> contenucol;
    private TableColumn<Reclamation2, Date> dateRcol;
    @FXML
    private TableColumn<Reclamation2, String> emailcol;
    @FXML
    private TableColumn<Reclamation2, String> etatcol;
    @FXML
    private TableColumn<Reclamation2, String> typereccol;
   
    
    
       ObservableList<Reclamation2> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> datecol;
    @FXML
    private Button pdfbutt;
    @FXML
    private Button mail;
    @FXML
    private Button Stats;
    @FXML
    private Button FAQ1;
    public void populate() {
        String sql = "select * from reclamation";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Reclamation2(rs.getInt("id"), rs.getString("sujet"), rs.getString("contenu"),
                        rs.getDate("dateR"),rs.getString("etat"),rs.getString("email"), rs.getString("typerec")));
                System.out.println(rs.getDate("dateR"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clubTable.setItems(oblist);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
//           Reclamationservice rService = new Reclamationservice();
//        ArrayList List = (ArrayList) rService.selectAll();
//        observableList = FXCollections.observableArrayList(List);
//        clubTable.setItems(observableList);
        
        
        populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sujetcol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenucol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        typereccol.setCellValueFactory(new PropertyValueFactory<>("typerec"));
        
        
        
        
        
        


        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
        id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId()));
         sujet.setText(clubTable.getSelectionModel().getSelectedItem().getSujet());
        contenu.setText(clubTable.getSelectionModel().getSelectedItem().getContenu());
         dateR.setValue(clubTable.getSelectionModel().getSelectedItem().getDate().toLocalDate());
                         etat.setText(clubTable.getSelectionModel().getSelectedItem().getEtat());

              email.setText(clubTable.getSelectionModel().getSelectedItem().getEmail());
                typerec.setText(clubTable.getSelectionModel().getSelectedItem().getType());

                

              }

        });

    }
        
  
    
    
    
 
    
    
    
    
    
    
    
    
    
    @FXML
    private void ajouterbtn(ActionEvent event) throws SQLException, AWTException {
        
        Reclamation2.InsertReclamation(Integer.parseInt(id.getText()),sujet.getText(), contenu.getText(), java.sql.Date.valueOf(dateR.getValue()).toString(), "", email.getText(), typerec.getText());
        populate();
        msg.setText("Réclamation ajoutée");
    
      Notifications n = new Notifications();
                n.displayTray("reclamation", "bien ajouté");
        
        
        
        
        
        
        
        
    }
    
    
    
    

    @FXML
    private void Modifrec(ActionEvent event) throws SQLException, AWTException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reclamation2.ModifReclamation(Integer.parseInt(id.getText()),sujet.getText(),contenu.getText(),java.sql.Date.valueOf(dateR.getValue()).toString(), etat.getText(),email.getText(),typerec.getText());
            populate();
            msg.setText("Réclamation Modifiée");
              Notifications n = new Notifications();
                n.displayTray("reclamation", "bien modifié");
        }
        
        
        
        
    }

    @FXML
    private void Supprec(ActionEvent event) throws SQLException, AWTException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            Reclamation2.SuppReclamation(Integer.parseInt(id.getText()));

            populate();
            msg.setText("Réclamation  Supprimée");
              Notifications n = new Notifications();
                n.displayTray("reclamation", "bien supprimé");
        }
        
        
        
        
    }

   
 
    
    
        
        
        
          @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Admin\\Desktop\\reclamation.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(4);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("Id");
       table.addCell("Sujet");
       table.addCell("Contenu");
       table.addCell("Date_réclamation");

        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("ISchool");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from reclamation");
       while(rs.next()){
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("sujet"));

                   table.addCell(rs.getString("contenu"));

                          table.addCell(rs.getString("dateR"));


       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }
    
    
    
    @FXML
    private void Stats(ActionEvent event) throws IOException {
      
        
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/PieChart1.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    
    }
    
    private void stats (ActionEvent event) throws IOException {
       mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/stat.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void send(ActionEvent event) throws IOException {
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Mail.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void afficherFAQ1(ActionEvent event) throws IOException {
        
        
          mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
        
        
    }

    
        
        
        
        
        
        
   
    
}