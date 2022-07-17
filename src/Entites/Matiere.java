/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author ASUS
 */
public class Matiere {
	private int id;
	private String nom;
	private double coef;

	public Matiere(int id, String nom, double coef) {
		this.id = id;
		this.nom = nom;
		this.coef = coef;
	}

	public Matiere(String nom, double coef) {
		this.nom = nom;
		this.coef = coef;
	}

	public Matiere() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getCoef() {
		return coef;
	}

	public void setCoef(double coef) {
		this.coef = coef;
	}

	@Override
	public String toString() {
		return "Matiere{" + "id=" + id + ", nom=" + nom + ", coef=" + coef + '}';
	}

}
