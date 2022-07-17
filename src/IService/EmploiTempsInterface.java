/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entites.EmploiTemps;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface EmploiTempsInterface {
	public void addTemps(EmploiTemps empt) throws SQLException;

	public void deleteTemps(int id) throws SQLException;

	public void updateTemps(EmploiTemps empt) throws SQLException;

	public ArrayList<EmploiTemps> getAllTemps() throws SQLException;

	public EmploiTemps getByIdTemps(int id) throws SQLException;

	public ArrayList<EmploiTemps> getTempsByClasse(int idClasse) throws SQLException;

	public ArrayList<EmploiTemps> getTempsByEns(int idEns) throws SQLException;

}
