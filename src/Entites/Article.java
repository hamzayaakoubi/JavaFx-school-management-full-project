/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author MSI
 */
public class Article {
	private int id_user;
	private int id;
	private int id_categ;
	private Timestamp date_article;
	private String title;
	private int nbrdislike;
	private String contenu;
	private int nbrlike;
	private int nbrvu;
	private int nbrcomment;

	public Article() {
		this.date_article = new Timestamp(System.currentTimeMillis());
		this.nbrcomment = 0;
		this.nbrvu = 0;
		this.nbrlike = 0;
		this.nbrdislike = 0;
	}

	public Article(int id_user, int id, int id_categ, String title, String contenu) {
		this.id_user = id_user;
		this.id = id;
		this.id_categ = id_categ;
		this.title = title;
		this.contenu = contenu;
		this.date_article = new Timestamp(System.currentTimeMillis());
		this.nbrcomment = 0;
		this.nbrvu = 0;
		this.nbrlike = 0;
		this.nbrdislike = 0;
	}

	public int getId_user() {
		return id_user;
	}

	public int getId() {
		return id;
	}

	public int getId_categ() {
		return id_categ;
	}

	public Article(int id_user, int id, int id_categ, Timestamp date_article, String title, String contenu, int nbrlike,
			int nbrvu, int nbrcomment) {
		this.id_user = id_user;
		this.id = id;
		this.id_categ = id_categ;
		this.date_article = date_article;
		this.title = title;
		this.contenu = contenu;
		this.nbrlike = nbrlike;
		this.nbrvu = nbrvu;
		this.nbrcomment = nbrcomment;
	}

	public Timestamp getDate_article() {
		return date_article;
	}

	public String getTitle() {
		return title;
	}

	public int getNbrdislike() {
		return nbrdislike;
	}

	public String getContenu() {
		return contenu;
	}

	public int getNbrlike() {
		return nbrlike;
	}

	public int getNbrvu() {
		return nbrvu;
	}

	public int getNbrcomment() {
		return nbrcomment;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}

	public void setDate_article(Timestamp date_article) {
		this.date_article = date_article;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setNbrdislike(int nbrdislike) {
		this.nbrdislike = nbrdislike;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public void setNbrlike(int nbrlike) {
		this.nbrlike = nbrlike;
	}

	public void setNbrvu(int nbrvu) {
		this.nbrvu = nbrvu;
	}

	public void setNbrcomment(int nbrcomment) {
		this.nbrcomment = nbrcomment;
	}

	@Override
	public String toString() {
		return "Article{" + "id_user=" + id_user + ", id=" + id + ", id_categ=" + id_categ + ", date_article="
				+ date_article + ", title=" + title + ", nbrdislike=" + nbrdislike + ", contenu=" + contenu
				+ ", nbrlike=" + nbrlike + ", nbrvu=" + nbrvu + ", nbrcomment=" + nbrcomment + '}';
	}

}
