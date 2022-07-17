/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import static java.sql.JDBCType.NULL;
import javafx.scene.control.CheckBox;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author pablo
 */
public class Note {

	private int id;
	private String nom;
	private String prenom;
	private String matiere;
	private int note;

	private int idEleve;
	private CheckBox ch_box;

	public Note(int id, String nom, String prenom, String matiere, int note, CheckBox ch_box) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.note = note;
		this.ch_box = new CheckBox();
	}

	public void setCh_box(CheckBox ch_box) {
		this.ch_box = ch_box;
	}

	public CheckBox getCh_box() {
		return ch_box;
	}

	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public Note(int id, String nom, String prenom, String matiere, int note) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.note = note;
		this.ch_box = new CheckBox();

	}

	public Note(String nom, String prenom, String matiere, int note) {

		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.note = note;
		this.ch_box = new CheckBox();

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

	public String getMatiere() {
		return matiere;
	}

	public int getNote() {
		return note;
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

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public void setNote(int note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Note{" + "IDD=" + id + ", nom=" + nom + ", prenom=" + prenom + ", matiere=" + matiere + ", note=" + note
				+ '}';
	}

	public Note(String nom, String prenom, String matiere, int note, int idEleve) {
		this.nom = nom;
		this.prenom = prenom;
		this.matiere = matiere;
		this.note = note;
		this.idEleve = idEleve;
	}

}
