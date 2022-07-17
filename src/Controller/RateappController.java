/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.rate;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class RateappController implements Initializable {

    @FXML
    private Label label_rating;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Rating rating;
    @FXML
    private JFXButton ratebtn;
    @FXML
    private JFXButton statvote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Object> data = FXCollections.observableArrayList();
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              label_rating.setText(t1.toString());
            }
            
        });
    }    

    @FXML
    private void rate(ActionEvent event) throws SQLException {
        UserService sp = new UserService();
        String x1=label_rating.getText();
        sp.RateApp(x1);
          System.err.println("insertion effectue");
        
        
        
    }

    @FXML
    private void statvote(ActionEvent event) throws IOException {
        
            mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/statRate.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
    
    
}
