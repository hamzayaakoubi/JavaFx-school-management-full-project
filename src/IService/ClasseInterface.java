/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entites.Classe;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface ClasseInterface {
	public void addClasse(Classe c) throws SQLException;

	public ArrayList<Classe> getAllClasses() throws SQLException;

	public Classe getById(int id) throws SQLException;

	public int updateClasse(Classe c) throws SQLException;

	public int deleteClasse(int id) throws SQLException;

}
