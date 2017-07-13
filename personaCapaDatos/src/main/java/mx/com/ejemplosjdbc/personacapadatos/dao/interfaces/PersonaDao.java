/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.com.ejemplosjdbc.personacapadatos.dto.PersonaDTO;

/**
 *
 * @author German Juarez
 */
public interface PersonaDao {
	/**
	 * Sentencia insert
	 * @param persona
	 * @return 
	 * @throws SQLException 
	 */
	public int insert(PersonaDTO persona) throws SQLException;
	/**
	 * Sentencia update
	 * @param persona
	 * @return 
	 * @throws SQLException 
	 */
	public int update(PersonaDTO persona) throws SQLException;
	/**
	 * Semtencia delete
	 * @param persona
	 * @return 
	 * @throws SQLException 
	 */
	public int delete(PersonaDTO persona) throws SQLException;
	/**
	 * Sentencia Select
	 * @return 
	 * @throws SQLException 
	 */
	public List<PersonaDTO> select() throws SQLException;
	/**
	 * 
	 * @param idPersona
	 * @return
	 * @throws SQLException 
	 */
	public PersonaDTO select(int idPersona) throws SQLException;
}
