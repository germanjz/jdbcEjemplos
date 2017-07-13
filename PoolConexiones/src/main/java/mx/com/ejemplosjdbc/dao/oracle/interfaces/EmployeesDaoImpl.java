/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.dao.oracle.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.com.ejemplosjdbc.dao.oracle.impl.EmployeesDao;

/**
 *
 * @author German Juarez
 */
public class EmployeesDaoImpl implements EmployeesDao {
	private final static String SELECT = " SELECT * FROM employees WHERE employee_id in (100,101,102,103,104) ";
	private Connection conn;

	/**
	 * Default Constructor.
	 */
	public EmployeesDaoImpl() {
		super();
	}

	/**
	 * 
	 * Default Constructor.
	 * @param conn 
	 */
	public EmployeesDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void obtenerEmpleados() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(SELECT);
			rs = stmt.executeQuery();
			System.out.println("Ejecutando query: " + SELECT);
			System.out.print("");

			if (rs != null) {
				while (rs.next()) {
					int id_employee = rs.getInt(1);
					String name = rs.getString(2);
					String lastName = rs.getString(3);
					
					System.out.print("idEmpleado: " + id_employee);
					System.out.print(", nombre: " + name);
					System.out.print(", apellido: " + lastName);
					System.out.println("");
				}
			}
			System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			if (stmt != null) {
				System.out.println("Cerrando Statement");
				stmt.close();
			}
			if (rs != null) {
				System.out.println("Cerrando ResultSet");
				rs.close();
			}
		}
	}
}
