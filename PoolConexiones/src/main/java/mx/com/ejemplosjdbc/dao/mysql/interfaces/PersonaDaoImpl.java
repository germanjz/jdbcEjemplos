/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.dao.mysql.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.ejemplosjdbc.dao.mysql.impl.PersonaDao;

/**
 *
 * @author German Juarez
 */
public class PersonaDaoImpl implements PersonaDao {
	private final static String SELECT = " SELECT * FROM persona ";
	private Connection conn;

	/**
	 * Default Constructor.
	 */
	public PersonaDaoImpl() {
		super();
	}

	/**
	 * 
	 * Default Constructor.
	 * @param conn 
	 */
	public PersonaDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void obtenerPersonas() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(SELECT);
			rs = stmt.executeQuery();
			System.out.println("Ejecutando query: " + SELECT);
			System.out.print("");

			if (rs != null) {
				while (rs.next()) {
					int id_persona = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					
					System.out.print("idPersona: " + id_persona);
					System.out.print(", nombre: " + nombre);
					System.out.print(", apellido: " + apellido);
					System.out.println("");
				}
			}
			System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			if (stmt != null) {
				System.out.println("Cerrando conexion Statement");
				stmt.close();
			}
			if (rs != null) {
				System.out.println("Cerrando conexion ResultSet");
				rs.close();
			}
		}
	}
}
