/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author ihebb
 */
public class categorieFAQ {
	private int id;
	private String nom;

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public categorieFAQ(String nom) {
		this.nom = nom;
	}

	public categorieFAQ() {
	}

	@Override
	public String toString() {
		return nom;
	}

}
