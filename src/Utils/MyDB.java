/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entites.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author bhk
 */
public class MyDB {

	LocalDateTime today = LocalDateTime.now();
	String url = "jdbc:mysql://localhost/symfony";
	String login = "root";
	String password = "";

	private Connection connexion;
	private static Connection con;
	private static MyDB instance;

	public MyDB() {
		try {

			connexion = DriverManager.getConnection(url, login, password);
			System.out.println("Connexion etablie");
		} catch (SQLException ex) {
			System.out.println("Erreur de connexion");
		}

	}

	public Connection getConnexion() {
		return connexion;
	}

	public static MyDB getInstance() {
		if (instance == null) {
			instance = new MyDB();
		}

		return instance;
	}

	public static User getEmpoyeeId(String cin) {
		User emp = new User();
		try {
			String sql = "SELECT * FROM `fos_user` WHERE cin=?";
			Connection con = MyDB.getInstance().getConnexion();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1, cin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				emp.setCin(resultSet.getString(17));
				emp.setNom(resultSet.getString(13));

				emp.setPrenom(resultSet.getString(14));
				emp.setEmail(resultSet.getString(4));
			}
			// con.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Data Insert");
			alert.setHeaderText("ERROR");
			alert.setContentText("Record ERROR");
			alert.showAndWait();
			System.out.println("record ERROR");
		}
		return emp;
	}

}
