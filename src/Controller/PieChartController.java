/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.FAQ1Crud ;
import Services.FAQCrud;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private JFXButton Retour;
     @FXML
    private Pane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*ObservableList<PieChart.Data> pieChartData
                =FXCollections.observableArrayList(
                        new PieChart.Data("Cars", 13),
                        new PieChart.Data("Bikers", 25),
                        new PieChart.Data("Buses", 10),
                        new PieChart.Data("Cycles", 20));
        piechart.setData(pieChartData);*/
      FAQCrud c=new FAQCrud();
       piechart.setData(c.Stats());

    }    

    @FXML
    private void Retour(ActionEvent event) throws IOException {
       mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/FAQs1.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
        
    }
    
   
    
    
    
    
    
    
    
}
