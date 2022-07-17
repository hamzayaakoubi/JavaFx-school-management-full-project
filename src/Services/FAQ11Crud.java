/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import com.jfoenix.controls.JFXListView;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import Utils.MyDB;


/**
 *
 * @author pubkhalil
 */
public class FAQ11Crud {
   
    
 public ObservableList<PieChart.Data> Stats() {
        String requete = "SELECT mode_reglement,count(num_recu) FROM `finance` GROUP BY mode_reglement";
        try {
            Statement st2 = MyDB.getInstance().getConnexion().createStatement();

            ResultSet rs = st2.executeQuery(requete);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while (rs.next()) {
                pieChartData.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));

            }

            return pieChartData;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }}