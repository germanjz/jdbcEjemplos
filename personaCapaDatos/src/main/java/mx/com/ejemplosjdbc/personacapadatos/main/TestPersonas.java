/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.main;

import java.sql.Connection;
import java.sql.SQLException;
import mx.com.ejemplosjdbc.personacapadatos.dao.interfaces.PersonaDao;
import mx.com.ejemplosjdbc.personacapadatos.dao.interfaces.UsuarioDao;
import mx.com.ejemplosjdbc.personacapadatos.dao.jdbc.PersonasDaoJDBC;
import mx.com.ejemplosjdbc.personacapadatos.dao.jdbc.UsuarioDaoJDBC;
import mx.com.ejemplosjdbc.personacapadatos.dto.PersonaDTO;
import mx.com.ejemplosjdbc.personacapadatos.dto.UsuarioDTO;
import mx.com.ejemplosjdbc.personacapadatos.jdbc.Conexion;

/**
 *
 * @author German Juarez
 */
public class TestPersonas {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PersonaDao personaDao;
		UsuarioDao usuarioDao;
		
		try {
			conn = Conexion.getConnection();
			conn.setAutoCommit(false);
			usuarioDao = new UsuarioDaoJDBC(conn);
			personaDao = new PersonasDaoJDBC(conn);
//			UsuarioDTO usuario = new UsuarioDTO();
//			usuario.setUsuario("Ger77x");
//			usuario.setPassword("Pase4321");
//			
//			usuarioDao.insert(usuario);
//			conn.commit();

			for (PersonaDTO persona : personaDao.select()) {
				System.out.println(persona.toString());
			}
			System.out.println();
			for (UsuarioDTO usuario : usuarioDao.select()) {
				System.out.println(usuario.toString());
			}
		} catch (Exception e) {
			System.out.println("Excepcion en la capa de prueba, REALIZANDO ROLLBACK");
			conn.rollback();
		} finally {
			Conexion.closeConnection(conn);
		}
	}
}
