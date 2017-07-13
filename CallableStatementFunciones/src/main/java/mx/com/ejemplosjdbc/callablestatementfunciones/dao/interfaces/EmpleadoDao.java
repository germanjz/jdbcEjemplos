/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ejemplosjdbc.callablestatementfunciones.dao.interfaces;

import java.sql.SQLException;

/**
 *
 * @author German Juarez
 */
public interface EmpleadoDao {
	void obtenerSalarioEmpleado(int idEmpleado) throws SQLException;
	void asignarSalarioEmpleado(int idEmpleado, double incrementoSalario) throws SQLException;
	void obtenerDepartamentos(int idDepartamento) throws SQLException;
	void obtenerMetaDatos() throws SQLException;
}
