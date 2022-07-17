/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.jfoenix.controls.JFXButton;
import Entites.stat;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import Services.FAQ1Crud;
import Utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class PieChart1Controller_1 implements Initializable {

    @FXML
    private PieChart pie;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           stat c=new stat();
       pie.setData(c.StatsEleve());
    }    
  
 
    }

   

    
    


