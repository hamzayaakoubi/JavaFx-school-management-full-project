/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class EmploiTemps {

	private int id;
	private int idMatiere;
	private Timestamp dateDebut;
	private Timestamp dateFin;
	private int salle;
	private int idClasse;
	private int idEnseignant;

	public EmploiTemps() {
	}

	public EmploiTemps(int id, int idMatiere, Timestamp dateDebut, Timestamp dateFin, int idsalle, int idClasse) {
		this.id = id;
		this.idMatiere = idMatiere;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.salle = salle;
		this.idClasse = idClasse;
		this.idEnseignant = idEnseignant;
	}

	public EmploiTemps(int idMatiere, Timestamp dateDebut, Timestamp dateFin, int idsalle, int idClasse) {
		this.idMatiere = idMatiere;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.salle = salle;
		this.idClasse = idClasse;

	}

	@Override
	public String toString() {
		return "EmploiTemps{" + "id=" + id + ", idMatiere=" + idMatiere + ", dateDebut=" + dateDebut + ", dateFin="
				+ dateFin + ", salle=" + salle + ", idClasse=" + idClasse + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}

	public Timestamp getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Timestamp getDateFin() {
		return dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public int getIdEnseignant() {
		return idEnseignant;
	}

	public void setIdEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

}
