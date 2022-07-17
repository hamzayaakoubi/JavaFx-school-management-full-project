/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
import Services.Blog;
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
/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class BlogController implements Initializable {

    @FXML
    private TableView<Blog> BlogTable;
    @FXML
    private TableColumn<Blog, Integer> idcol;
    @FXML
    private TableColumn<Blog, Integer> stylecol;
    @FXML
    private TableColumn<Blog, String> descriptioncol;
    @FXML
    private TableColumn<Blog, String> numerocol;
    
     private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Image image;
    private AnchorPane anchorPane;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    
    
    @FXML
    private TableColumn<?, ?> imgcol;
    @FXML
    private TextField id;
    @FXML
    private TextField style;
    @FXML
    private TextField description;
    @FXML
    private TextField numero;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField photo;
       ObservableList<Blog> oblist = FXCollections.observableArrayList();
    @FXML
    private Pane mainpane;
    @FXML
    private TableColumn<?, ?> etatcol;
    @FXML
    private TextField etat;

       
       public void populate() {
        String sql = "select * from post";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Blog(rs.getInt("id"), rs.getInt("creator"), rs.getString("title"),
                        rs.getString("description"), rs.getString("photo"), rs.getString("postdate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BlogTable.setItems(oblist);
    }

    
     public void clearFields() {
        id.clear();
        style.clear();
        description.clear();
        numero.clear();
        photo.clear();
        imgview.setImage(null);
        

    }
    
    
     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {


 populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        stylecol.setCellValueFactory(new PropertyValueFactory<>("style"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numerocol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        
                imgcol.setCellValueFactory(new PropertyValueFactory<>("photo"));

        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));


        BlogTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                 try{
                
                
                
                id.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getStyle()));
                description.setText(BlogTable.getSelectionModel().getSelectedItem().getDescription());
                numero.setText(BlogTable.getSelectionModel().getSelectedItem().getnumero());
               
                photo.setText(BlogTable.getSelectionModel().getSelectedItem().getPhoto());
                Image image = new Image(photo.getText());
                                imgview.setImage(image);

                               etat.setText(BlogTable.getSelectionModel().getSelectedItem().getEtat());
                 
                                
                                
            }
                 catch(Exception e){
                     
                     
                     
                   id.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getStyle()));
                description.setText(BlogTable.getSelectionModel().getSelectedItem().getDescription());
                numero.setText(BlogTable.getSelectionModel().getSelectedItem().getnumero());
                photo.setText("file:/C:/xampp2/htdocs/madame/web/imagesEvents/" + BlogTable.getSelectionModel().getSelectedItem().getPhoto());
                Image image = new Image(photo.getText());
                                imgview.setImage(image); 
                     
                     
                                                 etat.setText(BlogTable.getSelectionModel().getSelectedItem().getEtat());
   
                     
                     
                     
                     
                     
                     
                     
                     
                     
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
