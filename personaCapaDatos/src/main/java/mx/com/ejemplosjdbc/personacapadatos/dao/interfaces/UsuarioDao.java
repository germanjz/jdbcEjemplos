/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ejemplosjdbc.personacapadatos.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.com.ejemplosjdbc.personacapadatos.dto.UsuarioDTO;

/**
 *
 * @author usuario
 */
public interface UsuarioDao {
	/**
	 * Sentencia insert
	 * @param usuario
	 * @return 
	 * @throws SQLException 
	 */
	public int insert(UsuarioDTO usuario) throws SQLException;
	/**
	 * Sentencia update
	 * @param usuario
	 * @return 
	 * @throws SQLException 
	 */
	public int update(UsuarioDTO usuario) throws SQLException;
	/**
	 * Semtencia delete
	 * @param usuario
	 * @return 
	 * @throws SQLException 
	 */
	public int delete(UsuarioDTO usuario) throws SQLException;
	/**
	 * Sentencia Select
	 * @return 
	 * @throws SQLException 
	 */
	public List<UsuarioDTO> select() throws SQLException;
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException 
	 */
	public UsuarioDTO select(int idUsuario) throws SQLException;
}
