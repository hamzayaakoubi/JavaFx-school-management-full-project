/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.stat;


import Services.FAQ1Crud ;
        
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
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ihebb
 */
public class PieChart1Controller implements Initializable {

    @FXML
    private PieChart piechart;
    private PieChart pie;

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
//      stat c=new stat();
      FAQ1Crud c=new FAQ1Crud();
       piechart.setData(c.Stats());

    }    

    
    }
    
   
    
    
    
    
    
    
    

