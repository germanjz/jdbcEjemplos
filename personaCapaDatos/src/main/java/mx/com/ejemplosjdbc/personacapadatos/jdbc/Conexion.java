package mx.com.ejemplosjdbc.personacapadatos.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author German Juarez
 */
public class Conexion {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String JDBC_URL = "jdbc:mysql://localhost:3306/sga?useSSL=false";
	private static String JDBC_USER = "user";
	private static String JDBC_PASS = "userAdmin";
	private static Driver driver = null;

	@SuppressWarnings("rawtypes")
	public static synchronized Connection getConnection() throws SQLException {
		if (driver == null) {
			try {
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			} catch (Exception e) {
				System.out.println("Fallo en cargar el driver JDBC");
				e.printStackTrace();
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				System.out.println("Cerrando ResultSet");
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void closePreparedStatement(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				System.out.println("Cerrando PreparedStatement");
				stmt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement st) {
		try {
			if (st != null) {
				System.out.println("Cerrando Statement");
				st.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	//Cierre de la conexion
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				System.out.println("Cerrando Conexion");
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
