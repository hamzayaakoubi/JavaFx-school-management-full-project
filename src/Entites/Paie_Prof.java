/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dhoha
 */
public class Paie_Prof {
	int num_paiment;
	String cin;
	float nbre_heure;
	float salaire_heure;
	double montant;
	String mode_reg, nom, prenom, email;
	Date date;

	public Paie_Prof(int num_paiment, String cin, float nbre_heure, float salaire_heure, double montant,
			String mode_reg, Date date) {
		this.num_paiment = num_paiment;
		this.cin = cin;
		this.nbre_heure = nbre_heure;
		this.salaire_heure = salaire_heure;
		this.montant = montant;
		this.mode_reg = mode_reg;
		this.date = date;
	}

	public Paie_Prof(String cin, float nbre_heure, float salaire_heure, double montant, String mode_reg, String nom,
			String prenom, Date date, String email) {
		this.cin = cin;
		this.nbre_heure = nbre_heure;
		this.salaire_heure = salaire_heure;
		this.montant = montant;
		this.mode_reg = mode_reg;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Paie_Prof(int num_paiment, String cin, float nbre_heure, float salaire_heure, String mode_reg) {
		this.num_paiment = num_paiment;
		this.cin = cin;
		this.nbre_heure = nbre_heure;
		this.salaire_heure = salaire_heure;
		this.mode_reg = mode_reg;
	}

	public Paie_Prof() {

	}

	public int getNum_paiment() {
		return num_paiment;
	}

	public void setNum_paiment(int num_paiment) {
		this.num_paiment = num_paiment;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public float getNbre_heure() {
		return nbre_heure;
	}

	public void setNbre_heure(float nbre_heure) {
		this.nbre_heure = nbre_heure;
	}

	public float getSalaire_heure() {
		return salaire_heure;
	}

	public void setSalaire_heure(float salaire_heure) {
		this.salaire_heure = salaire_heure;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMode_reg() {
		return mode_reg;
	}

	public void setMode_reg(String mode_reg) {
		this.mode_reg = mode_reg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		int hash = 5;
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
		final Paie_Prof other = (Paie_Prof) obj;
		if (this.num_paiment != other.num_paiment) {
			return false;
		}
		if (this.cin != other.cin) {
			return false;
		}
		if (Float.floatToIntBits(this.nbre_heure) != Float.floatToIntBits(other.nbre_heure)) {
			return false;
		}
		if (Float.floatToIntBits(this.salaire_heure) != Float.floatToIntBits(other.salaire_heure)) {
			return false;
		}
		if (Double.doubleToLongBits(this.montant) != Double.doubleToLongBits(other.montant)) {
			return false;
		}
		if (!Objects.equals(this.mode_reg, other.mode_reg)) {
			return false;
		}
		if (!Objects.equals(this.date, other.date)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Paie_Prof{" + "num_paiment=" + num_paiment + ", cin=" + cin + ", nbre_heure=" + nbre_heure
				+ ", salaire_heure=" + salaire_heure + ", montant=" + montant + ", mode_reg=" + mode_reg + ", date="
				+ date + '}';
	}

	public Double calculSalair(float nbre_heure, float salaire_heure) {
		montant = nbre_heure * salaire_heure;
		return montant;
	}

	public Double calculSalaire() {
		montant = nbre_heure * salaire_heure;
		return montant;
	}
}
