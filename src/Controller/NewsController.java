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
import Services.News;
import com.jfoenix.controls.JFXButton;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
public class NewsController implements Initializable {

    @FXML
    private TableView<News> chambreTable;

    @FXML
    private TableColumn<News, Integer> idcol;

    @FXML
    private TableColumn<News, Integer> stylecol;

    @FXML
    private TableColumn<News, String> descriptioncol;

    @FXML
    private TableColumn<News, String> numerocol;

    @FXML
    private TableColumn<News, String> nbpersonnecol;


    @FXML
    private TableColumn<News, Integer> etatcol;
    
    
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Image image;
    private AnchorPane anchorPane;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    
    
    

    @FXML
    private TextField id;

    private TextField style;

    private TextField description;

    private TextField numero;

    private TextField nbpersonne;

    private TextField prix;

    private TextField etat;

    private Label msg;
    ObservableList<News> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> imgcol;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField photo;
    @FXML
    private TableColumn<News, String> reservcol;
    @FXML
    private Pane Mainpane;
    @FXML
    private TextField iduser;
    @FXML
    private TextField nomart;
   
    @FXML
    private TextField titre;
    @FXML
    private TextField nbvue;
    @FXML
    private TextField date;
    @FXML
    private TextArea contenu;

    public void populate() {
        String sql = "select * from news";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new News(rs.getInt("Id_Article"), rs.getInt("Id_User"), rs.getString("Nom_Article"),
                        rs.getString("Contenu_Article"), rs.getString("Image_Article"), rs.getString("Titre_Event"), rs.getInt("nbrevue"), rs.getString("Date_Article")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chambreTable.setItems(oblist);
    }

    
     public void clearFields() {
        id.clear();
        style.clear();
        description.clear();
        numero.clear();
        photo.clear();
        imgview.setImage(null);
        etat.clear();
         nbpersonne.clear();
          prix.clear();

    }
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
       idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        stylecol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("style"));
        numerocol.setCellValueFactory(new PropertyValueFactory<>("description"));
                        imgcol.setCellValueFactory(new PropertyValueFactory<>("photo"));

        nbpersonnecol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
                                reservcol.setCellValueFactory(new PropertyValueFactory<>("nbpersonne"));



        chambreTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                 try{
                
                
              id.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getId()));
             iduser.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getnumero()));

                nomart.setText(chambreTable.getSelectionModel().getSelectedItem().getStyle());
                contenu.setText(chambreTable.getSelectionModel().getSelectedItem().getDescription());
                
                 
                 titre.setText(chambreTable.getSelectionModel().getSelectedItem().getEtat());
                                date.setText(chambreTable.getSelectionModel().getSelectedItem().getNbpersonne());

                nbvue.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getPrix()));

            photo.setText("file:/C:/xampp2/htdocs/madame/web/imagesEvents/"  + chambreTable.getSelectionModel().getSelectedItem().getPhoto());
               
                Image image = new Image(photo.getText());
                                imgview.setImage(image); 
                 
                 
                 
                 
                 }
                 catch(Exception e){
                     
                     
                     
                    id.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getId()));
                                    numero.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getnumero()));

                style.setText(chambreTable.getSelectionModel().getSelectedItem().getStyle());
                description.setText(chambreTable.getSelectionModel().getSelectedItem().getDescription());
                
                 
                 
                 etat.setText(chambreTable.getSelectionModel().getSelectedItem().getEtat());
                prix.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getPrix()));

                nbpersonne.setText(chambreTable.getSelectionModel().getSelectedItem().getNbpersonne());
                 
                      photo.setText("file:/C:/xampp2/htdocs/madame/web/imagesEvents/"  + chambreTable.getSelectionModel().getSelectedItem().getPhoto());
               Image image = new Image(photo.getText());
                                imgview.setImage(image);
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     
                 }}

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
    
    
    
    
    

  

    

   
        
        
        
        
        
        
        
    }


