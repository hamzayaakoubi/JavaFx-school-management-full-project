/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entites.Enseignant;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface EnseignantInterface {

	public void addEnseignant(Enseignant ens) throws SQLException;

	public ArrayList<Enseignant> getAllEnseignant() throws SQLException;

	public Enseignant getById(int id) throws SQLException;

	public int updateEnseignant(Enseignant ens) throws SQLException;

	public int deleteEnseignant(int id) throws SQLException;

	public Enseignant login(String username, String password) throws SQLException;

	public ArrayList<Enseignant> getClasseById(int id) throws SQLException;

}
