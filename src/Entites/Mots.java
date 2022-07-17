/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import javafx.scene.control.Button;

/**
 *
 * @author MSI
 */
public class Mots {
	private int id;
	private String textmot;
	private int gravitemot;

	public void setGravitemot(int gravitemot) {
		this.gravitemot = gravitemot;
	}

	public Mots(int id, String textmot, int gravitemot) {
		this.id = id;
		this.textmot = textmot;
		this.gravitemot = gravitemot;
	}

	public Mots(int id, String textmot, int gravitemot, Button btn) {
		this.id = id;
		this.textmot = textmot;
		this.gravitemot = gravitemot;
	}

	public int getGravitemot() {
		return gravitemot;
	}

	public Mots(int id, String textmot) {
		this.id = id;
		this.textmot = textmot;
	}

	public int getId() {
		return id;
	}

	public String getTextmot() {
		return textmot;
	}

	public Mots() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTextmot(String textmot) {
		this.textmot = textmot;
	}

	@Override
	public String toString() {
		return "Mots{" + "id=" + id + ", textmot=" + textmot + '}';
	}

}
