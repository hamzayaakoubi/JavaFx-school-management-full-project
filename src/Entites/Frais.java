/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dhoha
 */
public class Frais {
	// int id;
	// String type_frais;
	// float montant;
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty type_frais = new SimpleStringProperty();
	private final FloatProperty montant = new SimpleFloatProperty();

	public Frais(int aInt, String string, float aFloat) {

	}

	public Frais(String string, float aFloat) {

	}

	@Override
	public String toString() {
		return "Frais{" + "id=" + id + ", Frais=" + type_frais + ", montant=" + montant + '}';
	}

	public int getId() {
		return id.get();
	}

	public String getType_frais() {
		return type_frais.get();
	}

	public float getMontant() {
		return montant.get();
	}

	public void setId(int value) {
		id.set(value);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setMontant(Float value) {
		montant.set(value);
	}

	public FloatProperty montantProperty() {
		return montant;
	}

	public void setFris(String value) {
		type_frais.set(value);
	}

	public StringProperty fraisProperty() {
		return type_frais;
	}

	/*
	 * public Frais(int id, String type_frais, float montant) { this.id = id;
	 * this.type_frais = type_frais; this.montant = montant; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getType_frais() { return type_frais; }
	 * 
	 * public void setType_frais(String type_frais) { this.type_frais = type_frais;
	 * }
	 * 
	 * public float getMontant() { return montant; }
	 * 
	 * public void setMontant(float montant) { this.montant = montant; }
	 * 
	 * @Override public String toString() { return "Frais{" + "id=" + id +
	 * ", type_frais=" + type_frais + ", montant=" + montant + '}'; }
	 */

	public Frais() {
	}

}
