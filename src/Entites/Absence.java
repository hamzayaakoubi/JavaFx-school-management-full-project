/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author pablo
 */
public class Absence {

	private int id;
	private String nom;
	private String prenom;
	private String matiere;
	private java.sql.Date date;
	private int nbAbsence;
	private CheckBox ch_box;

	public Absence(String nom, String prenom, String matiere, Date date, int nbAbsence, int idEleve) {
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.date = date;
		this.nbAbsence = nbAbsence;
		this.idEleve = idEleve;
	}

	private int idEleve;

	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public Absence(int id, String nom, String prenom, String matiere, Date date, int nbAbsence, CheckBox ch_box) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.date = date;
		this.nbAbsence = nbAbsence;
		this.ch_box = new CheckBox();
	}

	public Absence(int id, String nom, String prenom, String matiere, java.sql.Date date, int nbAbsence) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.date = date;
		this.nbAbsence = nbAbsence;
		this.ch_box = new CheckBox();
	}

	public Absence(String nom, String prenom, String matiere, Date date, int nbAbsence) {

		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.date = date;
		this.nbAbsence = nbAbsence;
		this.ch_box = new CheckBox();
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNbAbsence() {
		return nbAbsence;
	}

	public CheckBox getCh_box() {
		return ch_box;
	}

	public void setCh_box(CheckBox ch_box) {
		this.ch_box = ch_box;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNbAbsence(int nbAbsence) {
		this.nbAbsence = nbAbsence;
	}

	@Override
	public String toString() {
		return "Absence{" + "IDD=" + id + ", nom=" + nom + ", prenom=" + prenom + ",matiere=" + matiere + ",date="
				+ date + ", nbAbsence=" + nbAbsence + '}';
	}

}
