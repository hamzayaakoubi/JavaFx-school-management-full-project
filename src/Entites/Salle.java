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
public class Salle {
	private int id;
	private String bloc;
	private int num;

	public Salle(int id, String bloc, int num) {
		this.id = id;
		this.bloc = bloc;
		this.num = num;
	}

	public Salle(String bloc, int num) {
		this.bloc = bloc;
		this.num = num;
	}

	public Salle() {

	}

	@Override
	public String toString() {
		return "Salle{" + "id=" + id + ", bloc=" + bloc + ", num=" + num + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBloc() {
		return bloc;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
