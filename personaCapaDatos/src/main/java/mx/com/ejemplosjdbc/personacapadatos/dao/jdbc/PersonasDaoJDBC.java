/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.ejemplosjdbc.personacapadatos.dao.interfaces.PersonaDao;
import mx.com.ejemplosjdbc.personacapadatos.dto.PersonaDTO;
import mx.com.ejemplosjdbc.personacapadatos.jdbc.Conexion;

/**
 *
 * @author German Juarez
 */
public class PersonasDaoJDBC implements PersonaDao {
	private Connection userConn;
	private final String SQL_INSERT		= "INSERT INTO persona(nombre, apellido) VALUES(?,?)";
	private final String SQL_UPDATE		= "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";
	private final String SQL_DELETE		= "DELETE FROM persona WHERE id_persona = ?";
	private final String SQL_SELECT		= "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";
	private final String SQL_SELECT_ID  = "SELECT id_persona, nombre, apellido FROM persona WHERE id_persona = ? ORDER BY id_persona";
	
	/**
	 * Public Constructor
	 */
	public PersonasDaoJDBC() {
		super();
	}
	/**
	 * Public Constructor
	 * @param userConn 
	 */
	public PersonasDaoJDBC(Connection userConn) {
		this.userConn = userConn;
	}
	
	@Override
	public int insert(PersonaDTO persona) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows; //registros afectados
		
		try {
			conn = this.userConn;
			stmt = conn.prepareStatement(SQL_INSERT);
			int index = 1;//contador de columnas
			stmt.setString(index++, persona.getNombre());//param 1 => ?
			stmt.setString(index++, persona.getApellido());//param 2 => ?
			System.out.println("Ejecutando query: " + SQL_INSERT);
			rows = stmt.executeUpdate();//no. registros afectados
			System.out.println("Registros afectados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	
	@Override
	public int update(PersonaDTO persona) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			int index = 1;
			stmt.setString(index++, persona.getNombre());
			stmt.setString(index++, persona.getApellido());
			stmt.setInt(index, persona.getIdPersona());
			rows = stmt.executeUpdate();
			System.out.println("Registros actualizados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	
	@Override
	public int delete(PersonaDTO persona) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, persona.getIdPersona());
			rows = stmt.executeUpdate();
			System.out.println("Registros eliminados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	
	@Override
	public List<PersonaDTO> select() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PersonaDTO> personas = new ArrayList<>();
		Connection conn;
		
		try {
			conn = this.userConn;
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			System.out.println("Ejecutando query: " + SQL_SELECT);

			if (rs != null) {
				while (rs.next()) {
					int id_persona = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);

					PersonaDTO personaDTO = new PersonaDTO();
					personaDTO.setIdPersona(id_persona);
					personaDTO.setNombre(nombre);
					personaDTO.setApellido(apellido);
					personas.add(personaDTO);
				}
			}

			return personas;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}
	
	@Override
	public PersonaDTO select(int idPersona) throws SQLException {
		Connection conn;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PersonaDTO personaDTO = null;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_SELECT_ID);
			stmt = conn.prepareStatement(SQL_SELECT_ID);
			stmt.setInt(1, idPersona);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					int id_persona = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);

					personaDTO = new PersonaDTO();
					personaDTO.setIdPersona(id_persona);
					personaDTO.setNombre(nombre);
					personaDTO.setApellido(apellido);
				}
			}

			return personaDTO;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}	
}
