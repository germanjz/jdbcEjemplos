/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.main;

import java.sql.Connection;
import java.sql.SQLException;
import mx.com.ejemplosjdbc.dao.mysql.impl.PersonaDao;
import mx.com.ejemplosjdbc.dao.mysql.interfaces.PersonaDaoImpl;
import mx.com.ejemplosjdbc.dao.oracle.impl.EmployeesDao;
import mx.com.ejemplosjdbc.dao.oracle.interfaces.EmployeesDaoImpl;
import mx.com.ejemplosjdbc.poolconexiones.pool.PoolConexionesMySQL;
import mx.com.ejemplosjdbc.poolconexiones.pool.PoolConexionesOracle;

/**
 *
 * @author German Juarez
 */
public class TestPoolConexiones {
	public static void main(String[] args) throws SQLException {
		Connection connMySQL = null;
		Connection connOracle = null;
		try {
			connMySQL = PoolConexionesMySQL.getConexion();
			System.out.println("Utilizaremos el pool de conexiones de MySQL");
			
			PersonaDao personaDao = new PersonaDaoImpl(connMySQL);
			personaDao.obtenerPersonas();
			
			System.out.println("");
			
			connOracle = PoolConexionesOracle.getConexion();
			System.out.println("Utilizaremos el pool de conexiones de Oracle");
			
			EmployeesDao employeesDao = new EmployeesDaoImpl(connOracle);
			employeesDao.obtenerEmpleados();
			
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connMySQL != null) {
				System.out.println("Cerrando conexion MYSQL");
				connMySQL.close();
			}
			if (connOracle != null) {
				System.out.println("Cerrando conexion Oracle");
				connOracle.close();
			}
		}
		
	}
}
