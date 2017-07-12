/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplojdbc1.dao;

import com.ejemplojdbc1.entity.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author German Juarez
 */
public class PersonasJDBC {
	private Connection userConn;
	private final String SQL_INSERT		= "INSERT INTO persona(nombre, apellido) VALUES(?,?)";
	private final String SQL_UPDATE		= "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";
	private final String SQL_DELETE		= "DELETE FROM persona WHERE id_persona = ?";
	private final String SQL_SELECT		= "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";
	private final String SQL_SELECT_ID  = "SELECT id_persona, nombre, apellido FROM persona WHERE id_persona = ? ORDER BY id_persona";
	
	/**
	 * Public Constructor
	 */
	public PersonasJDBC() {
		super();
	}
	/**
	 * Public Constructor
	 * @param userConn 
	 */
	public PersonasJDBC(Connection userConn) {
		this.userConn = userConn;
	}
	/**
	 * Sentencia insert
	 * @param nombre
	 * @param apellido
	 * @return 
	 * @throws SQLException 
	 */
	public int insert(String nombre, String apellido) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows; //registros afectados
		
		try {
			conn = this.userConn;
			stmt = conn.prepareStatement(SQL_INSERT);
			int index = 1;//contador de columnas
			stmt.setString(index++, nombre);//param 1 => ?
			stmt.setString(index++, apellido);//param 2 => ?
			System.out.println("Ejecutando query: " + SQL_INSERT);
			rows = stmt.executeUpdate();//no. registros afectados
			System.out.println("Registros afectados:" + rows);

			return rows;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	/**
	 * Sentencia update
	 * @param id_persona
	 * @param nombre
	 * @param apellido
	 * @return 
	 * @throws SQLException 
	 */
	public int update(int id_persona, String nombre, String apellido) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			int index = 1;
			stmt.setString(index++, nombre);
			stmt.setString(index++, apellido);
			stmt.setInt(index, id_persona);
			rows = stmt.executeUpdate();
			System.out.println("Registros actualizados:" + rows);

			return rows;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	/**
	 * Semtencia delete
	 * @param id_persona
	 * @return 
	 * @throws SQLException 
	 */
	public int delete(int id_persona) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id_persona);
			rows = stmt.executeUpdate();
			System.out.println("Registros eliminados:" + rows);

			return rows;
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}
	/**
	 * Sentencia Select
	 * @return 
	 * @throws SQLException 
	 */
	public List<Persona> select() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Persona> personas = new ArrayList<>();
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

					Persona persona = new Persona();
					persona.setIdPersona(id_persona);
					persona.setNombre(nombre);
					persona.setApellido(apellido);
					personas.add(persona);
				}
			}

			return personas;
		} catch (SQLException e) {			
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}
	/**
	 * 
	 * @param idPersona
	 * @return
	 * @throws SQLException 
	 */
	public Persona select(int idPersona) throws SQLException {
		Connection conn;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona persona = null;
		
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

					persona = new Persona();
					persona.setIdPersona(id_persona);
					persona.setNombre(nombre);
					persona.setApellido(apellido);
				}
			}

			return persona;
		} catch (SQLException e) {			
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}
}
