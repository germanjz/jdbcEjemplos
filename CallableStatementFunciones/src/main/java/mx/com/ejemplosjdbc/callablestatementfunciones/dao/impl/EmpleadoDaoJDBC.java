/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.callablestatementfunciones.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import mx.com.ejemplosjdbc.callablestatementfunciones.dao.interfaces.EmpleadoDao;
import mx.com.ejemplosjdbc.callablestatementfunciones.datos.Conexion;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author German Juarez
 */
public class EmpleadoDaoJDBC implements EmpleadoDao {
	private final static String FUNCTION		 = "{ ? = call fn_get_employee_salary(?) }";
	private final static String STORED_PROCEDURE = "{ call sp_set_employee_salary(?,?) }";
	private final static String CURSOR			 = "{ ? = call ref_cursor_package.get_dept_ref_cursor(?) }";
	private final static String SELECT_EMPLEADO  = " SELECT first_name, salary FROM employees WHERE employee_id = ";
	private final static String SELECT_EMPLEADOS = " SELECT * FROM employees ";
	private Connection con;
	
	/**
	 * 
	 */
	public EmpleadoDaoJDBC() {
		super();
	}
	
	/**
	 * 
	 * @param con 
	 */
	public EmpleadoDaoJDBC(Connection con) {
		super();
		this.con = con;
	}
	
	@Override
	public void obtenerSalarioEmpleado(int idEmpleado) throws SQLException {
		CallableStatement cstmt = null;
		try {	
			double salarioMensual;
			// Llamamos a una funcion de Oracle
			cstmt = con.prepareCall(FUNCTION);

			// Una funcion regresa un valor
			// por lo que lo registramos como el parametro 1
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

			// registrmos el segundo parametro
			cstmt.setInt(2, idEmpleado);
			cstmt.execute();

			salarioMensual = cstmt.getDouble(1);
			System.out.println("Empleado con id: " + idEmpleado);
			System.out.println("Salario $" + salarioMensual);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeCallableStatement(cstmt);
		}
	}

	@Override
	public void asignarSalarioEmpleado(int idEmpleado, double incrementoSalario) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
			
			//Llamamos al SP para incrementar el salario
			System.out.println("Aumento del 10% al empleado: " + idEmpleado);
			cstmt = con.prepareCall(STORED_PROCEDURE);
			cstmt.setInt(1, idEmpleado);
			cstmt.setDouble(2, incrementoSalario);
			cstmt.execute();
			
			//Obtenemos el nuevo valor del salario para el empleado seleccionado
			String query = SELECT_EMPLEADO + idEmpleado;
			
			rs = st.executeQuery(query);
			rs.next();
			
			System.out.println("Nombre: " + rs.getString(1));
			System.out.println("Salario nuevo: $" + rs.getFloat(2));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeCallableStatement(cstmt);
			Conexion.closeResultSet(rs);
			Conexion.closeStatement(st);
		}
	}

	@Override
	public void obtenerDepartamentos(int idDepartamento) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			cstmt = con.prepareCall(CURSOR);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setInt(2, idDepartamento);
			cstmt.execute();
			
			//Recuperamos el resultSet y lo convertimos a un tipo Oracle
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                System.out.print(" Id_departamento: " + rs.getInt(1));
                System.out.print(", Nombre_departamento: " + rs.getString(2));
                System.out.print(", Ubicación_id: " + rs.getString(3));
                System.out.println();
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closeCallableStatement(cstmt);
		}
	}

	@Override
	public void obtenerMetaDatos() throws SQLException {
		Statement st = null;
        ResultSet rs = null;
		ResultSetMetaData rsmd = null;
        try {
			st = con.createStatement();
            rs = st.executeQuery(SELECT_EMPLEADOS);
			rsmd = rs.getMetaData();
			
			if (rsmd != null) {
				int columnCount = rsmd.getColumnCount();
				System.out.println("Numero de columnas: " + columnCount);
				
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(" Nombre Columna: " + rsmd.getColumnName(i));
					System.out.print(", Tipo Columna: " + rsmd.getColumnTypeName(i));
					
					switch (rsmd.isNullable(i)) {
						case ResultSetMetaData.columnNoNulls:
							System.out.print(", Si acepta nulos");
							break;
						case ResultSetMetaData.columnNullable:
							System.out.print(", No acepta nulos");
							break;
						default:
							System.out.print(", Valor nulo desconocido");
							break;
					}
					
					System.out.println("");
				}
				
			} else {
				System.out.println("No existen metadatos");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closeStatement(st);
		}
	}
}
