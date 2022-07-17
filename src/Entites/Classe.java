/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Objects;

/**
 *
 * @author kimoz
 */
public class Classe {
	private int id;
	private String specialite;
	private String annee;
	private String groupe;

	public Classe(String specialite, String annee, String groupe) {
		this.specialite = specialite;
		this.annee = annee;
		this.groupe = groupe;
	}

	public Classe(int id, String annee, String specialite, String groupe) {
		this.id = id;
		this.annee = annee;
		this.specialite = specialite;
		this.groupe = groupe;
	}

	public Classe() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	@Override
	public String toString() {
		return "Classe{" + "id=" + id + ", specialite=" + specialite + ", annee=" + annee + ", groupe=" + groupe + '}';
	}

}
