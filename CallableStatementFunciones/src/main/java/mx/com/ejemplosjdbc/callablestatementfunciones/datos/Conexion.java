/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.callablestatementfunciones.datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author German Juarez
 */
public class Conexion {
	//Vamos a crear un archivo .properties de nombre ConexionJDBC
	private final static String JDBC_FILE_NAME = "jdbc";
	//Variables de conexion
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;

    /**
     * Metodo que lee el archivo de propiedades con los valores a utilizar para
     * conectarnos a la BD
     *
     * @param file
     * @return
     */
    public static Properties loadProperties(String file) {
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key;
        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            prop.put(key, bundle.getObject(key));
        }

		//Asignamos los valores del archivo de propiedades 
        //a las variables de la clase
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL    = prop.getProperty("url");
        JDBC_USER	= prop.getProperty("user");
        JDBC_PASS	= prop.getProperty("pass");

		//Regresamos el objeto properties con los valores
        //de la conexion a la BD
        return prop;
    }

	/**
	 * Para que no haya problemas al obtener la conexion de
     * manera concurrente, se usa la palabra synchronized
	 * @return
	 * @throws SQLException 
	 */
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                //Cargamos las propiedades de conexion a la BD
                loadProperties(JDBC_FILE_NAME);
                //Se registra el driver
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        //regresa un objeto de tipo Connection
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

	/**
	 * 
	 * Cierre del ResultSet
	 * @param rs 
	 */
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
	
	
	/**
	 * 
	 * Cierre del Statement
	 * @param st 
	 */
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
	
    /**
	 * 
	 * Cierre del PrepareStatement
	 * @param stmt 
	 */
    public static void closePreparedStatement(PreparedStatement stmt) {
        try {
            if (stmt != null) {
				System.out.println("Cerrando PrepareStatement");
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

	/**
	 *
	 * Cierre del CallableStatement
	 * @param cstmt
	 */
    public static void closeCallableStatement(CallableStatement cstmt) {
        try {
            if (cstmt != null) {
				System.out.println("Cerrando CallableStatement");
                cstmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
	
	/**
	 *
	 * Cierre de la conexion
	 * @param conn
	 */
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
