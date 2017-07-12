/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import mx.com.ejemplosjdbc.personacapadatos.dao.PersonaDao;
import mx.com.ejemplosjdbc.personacapadatos.dao.PersonasDaoJDBC;
import mx.com.ejemplosjdbc.personacapadatos.dto.PersonaDTO;
import mx.com.ejemplosjdbc.personacapadatos.jdbc.Conexion;

/**
 *
 * @author German Juarez
 */
public class TestPersonas {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PersonaDao personaDao;
		
		try {
			conn = Conexion.getConnection();
			conn.setAutoCommit(false);
			
			personaDao = new PersonasDaoJDBC(conn);

//			PersonaDTO personaDTO = new PersonaDTO();
//			personaDTO.setNombre("TEST");
//			personaDTO.setApellido("TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
//			
//			personaDao.insert(personaDTO);
//			System.out.println("Transaccion Correcta");
			conn.commit();
			
			List<PersonaDTO> personas = personaDao.select();
			for (PersonaDTO persona : personas) {
				System.out.println(persona);
			}
		} catch (Exception e) {
			System.out.println("Excepcion en la capa de prueba, REALIZANDO ROLLBACK");
			conn.rollback();
			
			e.printStackTrace();
		} finally {
			Conexion.closeConnection(conn);
		}
	}
}
