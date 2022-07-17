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
public class categorieProposees {
	private int id;
	private String nom;
	private int upvotes;

	public categorieProposees() {
	}

	public categorieProposees(int id, String nom, int upvotes) {
		this.id = id;
		this.nom = nom;
		this.upvotes = upvotes;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	@Override
	public String toString() {
		return "categorieProposees{" + "id=" + id + ", nom=" + nom + ", upvotes=" + upvotes + '}';
	}

}
