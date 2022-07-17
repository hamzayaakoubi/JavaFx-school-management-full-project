/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entites.Matiere;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface MatiereInterface {
	public void addMatiere(Matiere c) throws SQLException;

	public ObservableList<Matiere> getAllMatiere() throws SQLException;

	public Matiere getById(int id) throws SQLException;

	public int updateMatiere(Matiere c) throws SQLException;

	public int deleteMatiere(int id) throws SQLException;

	public int getIdMaitere(String name) throws SQLException;
}
