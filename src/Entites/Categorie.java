/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author MSI
 */
public class Categorie {

	private int id;
	private String libele_categ;
	private int nbrabo;
	private int nbrvu;
	private String description_categ;

	public Categorie(int id, String libele_categ, String description_categ) {
		this.id = id;
		this.libele_categ = libele_categ;
		this.description_categ = description_categ;

	}

	public Categorie(int id, String libele_categ, int nbrabo, int nbrvu, String description_categ) {
		this.id = id;
		this.libele_categ = libele_categ;
		this.nbrabo = nbrabo;
		this.nbrvu = nbrvu;
		this.description_categ = description_categ;
	}

	public Categorie(int id, String libele_categ, int nbrabo, int nbrvu) {
		this.id = id;
		this.libele_categ = libele_categ;
		this.nbrabo = nbrabo;
		this.nbrvu = nbrvu;
	}

	public Categorie(int id, String libele_categ, int nbrabo) {
		this.id = id;
		this.libele_categ = libele_categ;
		this.nbrabo = nbrabo;
	}

	public Categorie(int id, String libele_categ) {
		this.id = id;
		this.libele_categ = libele_categ;
	}

	public Categorie(int id) {
		this.id = id;
	}

	public Categorie() {
		this.nbrabo = 0;
		this.nbrvu = 0;
	}

	public int getId() {
		return id;
	}

	public String getLibele_categ() {
		return libele_categ;
	}

	public int getNbrabo() {
		return nbrabo;
	}

	public int getNbrvu() {
		return nbrvu;
	}

	public String getDescription_categ() {
		return description_categ;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibele_categ(String libele_categ) {
		this.libele_categ = libele_categ;
	}

	public void setNbrabo(int nbrabo) {
		this.nbrabo = nbrabo;
	}

	public void setNbrvu(int nbrvu) {
		this.nbrvu = nbrvu;
	}

	public void setDescription_categ(String description_categ) {
		this.description_categ = description_categ;
	}

	@Override
	public String toString() {
		return "Categorie{" + "id=" + id + ", libele_categ=" + libele_categ + ", nbrabo=" + nbrabo + ", nbrvu=" + nbrvu
				+ ", description_categ=" + description_categ + '}';
	}

}
