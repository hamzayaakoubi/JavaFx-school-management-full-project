/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entites.Classe;
import Entites.Salle;
import com.sun.org.apache.xpath.internal.functions.Function;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface SalleInterface {
	public void addSalle(Salle s) throws SQLException;

	public ArrayList<Salle> getAll() throws SQLException;

	public Salle getById(int id) throws SQLException;

	public int updateSalle(Salle s) throws SQLException;

	public int deleteSalle(int id) throws SQLException;

}
