/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataBase;
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
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class StatRateController implements Initializable {

    @FXML
    private BarChart<String , Integer> chart;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            load();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void loadChart(ActionEvent event) throws SQLException
    {
        
        String query ="SELECT rate,count(id) FROM ratee group by rate ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
         Connection c = DataBase.getInstance().getConnection();
         ResultSet rs = c.createStatement().executeQuery(query);
          while (rs.next()){
              
              series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
              
          }
          chart.getData().add(series);
          }
          
    private void load() throws SQLException
    {
        String query ="SELECT rate,count(id) FROM ratee group by rate ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
         Connection c = DataBase.getInstance().getConnection();
         ResultSet rs = c.createStatement().executeQuery(query);
          while (rs.next()){
              
              series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
              
          }
          chart.getData().add(series);
          }

}
