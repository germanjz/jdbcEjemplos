/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.callablestatementfunciones.main;

import java.sql.Connection;
import java.sql.SQLException;
import mx.com.ejemplosjdbc.callablestatementfunciones.dao.interfaces.EmpleadoDao;
import mx.com.ejemplosjdbc.callablestatementfunciones.dao.impl.EmpleadoDaoJDBC;
import mx.com.ejemplosjdbc.callablestatementfunciones.datos.Conexion;

/**
 *
 * @author German Juarez
 */
public class TestCallableStatement {
	public static void main(String[] args) throws SQLException {
		int idEmpleado = 100; // indentificador a recuperar salario
		int idDepartamento = 200;
		Connection con = null;
        EmpleadoDao empleadoDao;
		try {
			// Obtenemos la Conexion
			con = Conexion.getConnection();
			empleadoDao = new EmpleadoDaoJDBC(con);
			
			empleadoDao.obtenerSalarioEmpleado(idEmpleado);
//			double incrementoSalario = 1.1;
//			empleadoDao.asignarSalarioEmpleado(idEmpleado, incrementoSalario);

			empleadoDao.obtenerDepartamentos(idDepartamento);
			
			empleadoDao.obtenerMetaDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			Conexion.closeConnection(con);
		}
	}
}
