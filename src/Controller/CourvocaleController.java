/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class CourvocaleController implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private JFXButton play;
    @FXML
    private JFXButton stop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void play(ActionEvent event) {
    }

    @FXML
    private void stop(ActionEvent event) {
    }
    
}
