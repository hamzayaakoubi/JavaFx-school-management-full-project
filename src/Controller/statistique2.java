/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Utils.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 *
 * @author pablo
 */
public class statistique2 {
    
    
      public ObservableList<PieChart.Data> Stats1() {
        String requete = "SELECT (SELECT count(*)  from absence where nbAbsence>=10 ) as nbAbsence_sup , (SELECT count(*)  from absence where nbAbsence<10 ) as nbAbsence_inf  from absence ";
        try {
            Statement st2 = Connexion.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
           rs.next() ; 
                pieChartData.add(new PieChart.Data("nbAbsence_sup : %"+String.valueOf(100* Float.valueOf(rs.getInt(1))/Float.valueOf(rs.getInt(1)+rs.getInt(2))), rs.getInt(1)));
                 pieChartData.add(new PieChart.Data("nbAbsence_inf", rs.getInt(2)));

            

            return pieChartData;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
       
    }
    
    
}
