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
public class faq {

	private int id;
	private String question;
	private String reponse;
	private categorieFAQ categorie;
	private int views;

	public faq() {

	}

	public String getQuestion() {
		return question;
	}

	public String getReponse() {
		return reponse;
	}

	public int getViews() {
		return views;
	}

	public categorieFAQ getCategorie() {
		return categorie;
	}

	public void setCategorie(categorieFAQ categorie) {
		this.categorie = categorie;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public faq(int id, String question, String reponse, int views) {
		this.id = id;
		this.question = question;
		this.reponse = reponse;
		this.views = views;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "" + question;
	}

}
