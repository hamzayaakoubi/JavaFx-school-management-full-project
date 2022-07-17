/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import Utils.MyDB;

/**
 *
 * @author Dhoha
 */
public class Paiment_eleve {
	Connection cnx;

	// Stripe.apiKey = "sk_test_pnfuYUNCotWhyq7uOfqDmuGE";
	public Paiment_eleve() {
		cnx = MyDB.getInstance().getConnexion();
	}

	int num_recu;
	String mode_reglement;
	int nbre_moins;
	float montant_total;
	String type_frais;
	int remise;
	double montant;
	String cin;
	String mois;
	String mode_paiment;
	float reste;
	Date date;
	User user;
	String prenom, nom, email;
	// public final StringProperty nom = new SimpleStringProperty();
//  private final StringProperty nom = new SimpleStringProperty();

	public Paiment_eleve(int num_recu, String mode_reglement, String type_frais, int remise, double montant, String cin,
			String mois, float reste, Date date, String nom, String prenom, String email) {
		this.num_recu = num_recu;
		this.mode_reglement = mode_reglement;
		this.type_frais = type_frais;
		this.remise = remise;
		this.montant = montant;
		this.cin = cin;
		this.mois = mois;
		this.reste = reste;
		this.date = date;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNum_recu() {
		return num_recu;
	}

	public void setNum_recu(int num_recu) {
		this.num_recu = num_recu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMode_reglement() {
		return mode_reglement;
	}

	public void setMode_reglement(String mode_reglement) {
		this.mode_reglement = mode_reglement;
	}

	public int getNbre_moins() {
		return nbre_moins;
	}

	public void setNbre_moins(int nbre_moins) {
		this.nbre_moins = nbre_moins;
	}

	public float getMontant_total() {
		return montant_total;
	}

	public void setMontant_total(float montant_total) {
		this.montant_total = montant_total;
	}

	public String getType_frais() {
		return type_frais;
	}

	public void setType_frais(String type_frais) {
		this.type_frais = type_frais;
	}

	public int getRemise() {
		return remise;
	}

	public void setRemise(int remise) {
		this.remise = remise;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getMode_paiment() {
		return mode_paiment;
	}

	public void setMode_paiment(String mode_paiment) {
		this.mode_paiment = mode_paiment;
	}

	public float getReste() {
		return reste;
	}

	public void setReste(float reste) {
		this.reste = reste;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Paiment_eleve(String mode_reglement, String cin, String type_frais, int remise, String mode_paiment) {
		// this.num_recu = num_recu;
		this.mode_reglement = mode_reglement;
		// this.nbre_moins = nbre_moins;
		// this.montant_total = montant_total;
		this.type_frais = type_frais;
		this.remise = remise;
		// this.montant = montant;
		this.cin = cin;
		// this.mois = mois;
		this.mode_paiment = mode_paiment;
		// this.reste = reste;
		// this.date = date;
	}

	public Paiment_eleve(int montant_total) {
		this.montant_total = montant_total;
	}

	@Override
	public String toString() {
		return "Paiment{" + "num_recu=" + num_recu + ", mode_reglement=" + mode_reglement + ", nbre_moins=" + nbre_moins
				+ ", montant_total=" + montant_total + ", type_frais=" + type_frais + ", remise=" + remise
				+ ", montant=" + montant + ", cin=" + cin + ", mois=" + mois + ", mode_paiment=" + mode_paiment
				+ ", reste=" + reste + ", date=" + date + '}';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Paiment_eleve other = (Paiment_eleve) obj;
		if (this.num_recu != other.num_recu) {
			return false;
		}
		if (this.nbre_moins != other.nbre_moins) {
			return false;
		}
		if (this.montant_total != other.montant_total) {
			return false;
		}
		if (this.remise != other.remise) {
			return false;
		}
		if (Double.doubleToLongBits(this.montant) != Double.doubleToLongBits(other.montant)) {
			return false;
		}
		if (this.cin != other.cin) {
			return false;
		}
		if (Float.floatToIntBits(this.reste) != Float.floatToIntBits(other.reste)) {
			return false;
		}
		if (!Objects.equals(this.mode_reglement, other.mode_reglement)) {
			return false;
		}
		if (!Objects.equals(this.type_frais, other.type_frais)) {
			return false;
		}
		if (!Objects.equals(this.mois, other.mois)) {
			return false;
		}
		if (!Objects.equals(this.mode_paiment, other.mode_paiment)) {
			return false;
		}
		if (!Objects.equals(this.date, other.date)) {
			return false;
		}
		return true;
	}

	public float calculmontantTotal() throws SQLException {
		Connection con = MyDB.getInstance().getConnexion();
		PreparedStatement pt = con.prepareStatement("select montant As m from frais where type_frais=? ");

		pt.setString(1, this.getType_frais());

		ResultSet rs = pt.executeQuery();
		try {
			while (rs.next()) {

				montant_total = rs.getFloat("m");

			}
		} catch (SQLException ex) {

		}

		/*
		 * if(this.type_frais=="foyer"&& this.mode_paiment=="par mois") { montant_total=
		 * 350; } else if(this.type_frais=="cantine"&& this.mode_paiment=="par mois") {
		 * montant_total= 350; }else
		 * if(this.mode_paiment=="Annuelle"&&this.type_frais=="foyer"){
		 * 
		 * montant_total=1700; }else
		 * if(this.mode_paiment=="Annuelle"&&this.type_frais=="cantine"){
		 * 
		 * montant_total=1700; }else if(this.type_frais=="inscription"){
		 * montant_total=300;
		 * 
		 * }else{ montant_total=150; }
		 */
		return montant_total;
	}

	public Double calculmontant() throws SQLException {

		montant = ((this.calculmontantTotal()) - (this.calculmontantTotal()) * remise / 100);
		System.out.println(montant);
		return montant;
	}

	public float reste() throws SQLException {
		/*
		 * Connection con = MyDB.getInstance().getConnexion(); PreparedStatement pt =
		 * con.prepareStatement(
		 * "select montant As m from frais where type_frais='Education' ");
		 * 
		 * ResultSet rs = pt.executeQuery(); try { while (rs.next()) {
		 * 
		 * reste=(float) ((rs.getFloat("m"))-(this.calculmontant()));
		 * 
		 * 
		 * } } catch (SQLException ex) {
		 * 
		 * }
		 */
		Connection con = MyDB.getInstance().getConnexion();
		PreparedStatement pt = con.prepareStatement("select SUM(montant) As m from finance where cin=? ");
		pt.setString(1, this.getCin());
		ResultSet rs = pt.executeQuery();
		try {
			while (rs.next()) {

				reste = (1500) - (rs.getFloat("m"));

			}
		} catch (SQLException ex) {

		}
		return reste;
	}

}
