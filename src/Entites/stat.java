/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import Utils.MyDB;

/**
 *
 * @author Dhoha
 */
public class stat {
	public ObservableList<PieChart.Data> Stats() {
		String requete = "SELECT montant, COUNT(montant) FROM `PAIMENT` GROUP BY nom";
		try {
			Statement st2 = MyDB.getInstance().getConnexion().createStatement();

			ResultSet rs = st2.executeQuery(requete);
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			while (rs.next()) {
				pieChartData.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

			}

			return pieChartData;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public ObservableList<PieChart.Data> StatsEleve() {
		String requete = "SELECT SUM(reste) as Somme_Reste, SUM(montant)as Somme_Montant FROM `finance` GROUP BY nom";
		String req = "SELECT * FROM finance INNER JOIN fos_user ON fos_user.cin =finance.cin";
		try {
			Statement st2 = MyDB.getInstance().getConnexion().createStatement();

			ResultSet rs = st2.executeQuery(requete);
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			while (rs.next()) {
				pieChartData.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

			}

			return pieChartData;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}
}
