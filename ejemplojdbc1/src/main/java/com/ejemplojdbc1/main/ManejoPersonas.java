/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplojdbc1.main;

import com.ejemplojdbc1.dao.Conexion;
import com.ejemplojdbc1.dao.PersonasJDBC;
import com.ejemplojdbc1.entity.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ManejoPersonas {
	public static void main(String[] args) {
		PersonasJDBC personasJDBC;
		
		Connection conn = null;
			
		try {
			conn = Conexion.getConnection();
			conn.setAutoCommit(false);

			personasJDBC = new PersonasJDBC(conn);
			personasJDBC.update(2, "Dalin", "Zambrozuki");
			//personasJDBC.insert("Aldo", "Apache");
			//personasJDBC.insert("Aldo", "Ayala12341234123412341234123412341234123412341234123412341234123412341234123412341234");
			
			System.out.println("Realizando commit");
			conn.commit();
			
			List<Persona> personas = personasJDBC.select();
			for (Persona persona : personas) {
				System.out.println(persona);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				System.out.println("Realizando rollback");
				conn.rollback();
			} catch (SQLException ex) {
				e.printStackTrace();
			}
		} finally {
			Conexion.closeConnection(conn);
		}
	}
}
