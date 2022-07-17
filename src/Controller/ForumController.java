/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataBase.connection;
import Services.Blog;
import Services.Forum;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class ForumController implements Initializable {

    @FXML
    private Pane mainpane;
    @FXML
    private TableView<Forum> BlogTable;
    @FXML
    private TableColumn<Forum, Integer> idcol;
    @FXML
    private TableColumn<Forum, Integer> stylecol;
    @FXML
    private TableColumn<Forum, Integer> descriptioncol;
    @FXML
    private TableColumn<Forum, String> numerocol;
    @FXML
    private TableColumn<Forum, Integer> imgcol;
    @FXML
    private TableColumn<Forum, String> etatcol;
    @FXML
    private TextField id;
    @FXML
    private TextField style;
    @FXML
    private TextField description;
    @FXML
    private TextField numero;
    @FXML
    private TextField photo;
    @FXML
    private TextField etat;
    @FXML
    private TextField jaime;
           ObservableList<Forum> oblist = FXCollections.observableArrayList();


     public void populate() {
        String sql = "select * from sujet";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Forum(rs.getInt("id_f"), rs.getInt("id_user"), rs.getInt("strike"),
                        rs.getString("description_f"), rs.getString("date"), rs.getInt("nbre_jaime")));
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
//        imgview.setImage(null);
        

    }
    
    
     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {


 populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        stylecol.setCellValueFactory(new PropertyValueFactory<>("style"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        numerocol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
                imgcol.setCellValueFactory(new PropertyValueFactory<>("photo"));

        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));


        BlogTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                
                
                
                
                id.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getStyle()));
                numero.setText(BlogTable.getSelectionModel().getSelectedItem().getDescription());
                description.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getnumero()));
               
               jaime.setText(BlogTable.getSelectionModel().getSelectedItem().getPhoto());
               

            etat.setText(Integer.toString(BlogTable.getSelectionModel().getSelectedItem().getEtat()));
                 
                                
                                
            
               
                     
                     
                     
                   
                     
                     
                     
                     
                     
                     
                     
                     
                 }

        });

    }

   
   
   
  















    
    
}
