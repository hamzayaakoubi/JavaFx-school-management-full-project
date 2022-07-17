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
package Controller;



import DataBase.TrayIconDemo;
import DataBase.connection;
import Entites.Notifications;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.Chambre;
import com.jfoenix.controls.JFXButton;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
public class choixchambreController implements Initializable {

    @FXML
    private TableView<Chambre> chambreTable;

    @FXML
    private TableColumn<Chambre, Integer> idcol;

    @FXML
    private TableColumn<Chambre, String> stylecol;

    @FXML
    private TableColumn<Chambre, String> descriptioncol;

    @FXML
    private TableColumn<Chambre, Float> numerocol;

    @FXML
    private TableColumn<Chambre, String> nbpersonnecol;

    @FXML
    private TableColumn<Chambre, Float> prixcol;

    @FXML
    private TableColumn<Chambre, String> etatcol;
    
    
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Image image;
    private AnchorPane anchorPane;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    
    
    

    @FXML
    private TextField id;

    @FXML
    private TextField style;

    @FXML
    private TextField description;

    @FXML
    private TextField numero;

    @FXML
    private TextField nbpersonne;

    @FXML
    private TextField prix;

    @FXML
    private TextField etat;
    @FXML
    private Pane Mainpane;

    private Label msg;
    ObservableList<Chambre> oblist = FXCollections.observableArrayList();
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button reservation;
    @FXML
    private TableColumn<?, ?> imgcol;
    @FXML
    private JFXButton choose;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField photo;
    @FXML
    private TableColumn<Chambre, String> reservcol;
    public void populate() {
        String sql = "select * from foyer where reserv='Disponible' ";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Chambre(rs.getInt("id"), rs.getString("style"), rs.getString("description"),
                        rs.getInt("numero"), rs.getString("nbpersonne"), rs.getFloat("prix"), rs.getString("etat"), rs.getString("photo"), rs.getString("reserv")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chambreTable.setItems(oblist);
    }
    
    
     
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        stylecol.setCellValueFactory(new PropertyValueFactory<>("style"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numerocol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nbpersonnecol.setCellValueFactory(new PropertyValueFactory<>("nbpersonne"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
                imgcol.setCellValueFactory(new PropertyValueFactory<>("photo"));
                                                reservcol.setCellValueFactory(new PropertyValueFactory<>("reserv"));


        chambreTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                     try{
                
                id.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(chambreTable.getSelectionModel().getSelectedItem().getStyle());
                description.setText(chambreTable.getSelectionModel().getSelectedItem().getDescription());
                numero.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getnumero()));
                nbpersonne.setText(chambreTable.getSelectionModel().getSelectedItem().getNbpersonne());
                prix.setText(Float.toString(chambreTable.getSelectionModel().getSelectedItem().getPrix()));
                etat.setText(chambreTable.getSelectionModel().getSelectedItem().getEtat());
                photo.setText(chambreTable.getSelectionModel().getSelectedItem().getPhoto());
                Image image = new Image(photo.getText());
                                                imgview.setImage(image);
                                                
                                                
                           }
                 catch(Exception e){                      
                                                
                                                
                 
                       id.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(chambreTable.getSelectionModel().getSelectedItem().getStyle());
                description.setText(chambreTable.getSelectionModel().getSelectedItem().getDescription());
                numero.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getnumero()));
                nbpersonne.setText(chambreTable.getSelectionModel().getSelectedItem().getNbpersonne());
                prix.setText(Float.toString(chambreTable.getSelectionModel().getSelectedItem().getPrix()));
                etat.setText(chambreTable.getSelectionModel().getSelectedItem().getEtat());
                photo.setText("file:/C:/xampp2/htdocs/new blog/TalandWEB/web/uploads/post/" + chambreTable.getSelectionModel().getSelectedItem().getPhoto());
                Image image = new Image(photo.getText());
                                                imgview.setImage(image);      
                     
                     
                     
                     
                     
                     
                                                
                                                
                                                
                 }
            }

        });

    }

   
     private void handleBrowser(ActionEvent event) {
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

  

    @FXML
    private void InsertVoiture(ActionEvent event) throws SQLException {
        
        Chambre.InsertChambre(style.getText(), description.getText(), Integer.parseInt(numero.getText()), nbpersonne.getText(), Float.parseFloat(prix.getText()), etat.getText(), photo.getText(),"");
        populate();
        msg.setText("Chambre ajoutée");
        
        
        
    }

    @FXML
    private void ModifVoiture(ActionEvent event) throws SQLException {
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Chambre.ModifChambre(Integer.parseInt(id.getText()), style.getText(), description.getText(), Integer.parseInt(numero.getText()), nbpersonne.getText(), Float.parseFloat(prix.getText()), etat.getText(), photo.getText());
            populate();
            msg.setText("Chambre Modifiée");
        }
        
        
        
        
    }

    @FXML
    private void SuppVoiture(ActionEvent event) throws SQLException, AWTException {
         
      
        
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            Chambre.SuppChambre(Integer.parseInt(id.getText()));

            populate();
            msg.setText("Chambre Supprimée");
        }
        
        
        
        
    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
        
        Mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("/Fxml/affichReserv.fxml"));
        Mainpane.getChildren().add(parent);
        Mainpane.toFront();
        
        
        
        
        
        
        
        
    }

    @FXML
    private void importerimage(ActionEvent event) {
        
        
        
        
          FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        
        
        
        
        if (selectedfile != null) {

            photo.setText(selectedfile.toURI().toString());
            Image image = new Image(photo.getText());
            imgview.setImage(image);

        }
        
        
        
        
        
        
        
        
    }
}


