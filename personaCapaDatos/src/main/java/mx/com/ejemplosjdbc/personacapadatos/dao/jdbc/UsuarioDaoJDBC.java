/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.ejemplosjdbc.personacapadatos.dao.interfaces.UsuarioDao;
import mx.com.ejemplosjdbc.personacapadatos.dto.PersonaDTO;
import mx.com.ejemplosjdbc.personacapadatos.dto.UsuarioDTO;
import mx.com.ejemplosjdbc.personacapadatos.jdbc.Conexion;

/**
 *
 * @author German Juarez
 */
public class UsuarioDaoJDBC implements UsuarioDao {
	private Connection userConn;
	private final String SQL_INSERT		= "INSERT INTO usuarios(usuario, password) VALUES(?,?)";
	private final String SQL_UPDATE		= "UPDATE usuarios SET usuario=?, password=? WHERE id_usuario=?";
	private final String SQL_DELETE		= "DELETE FROM usuarios WHERE id_usuario = ?";
	private final String SQL_SELECT		= "SELECT id_usuario, usuario, password FROM usuarios ORDER BY id_usuario";
	private final String SQL_SELECT_ID  = "SELECT id_usuario, usuario, password FROM usuarios WHERE id_usuario = ? ORDER BY id_usuario";

	public UsuarioDaoJDBC() {
		super();
	}
	
	/**
	 * 
	 * @param userConn 
	 */
	public UsuarioDaoJDBC(Connection userConn) {
		super();
		this.userConn = userConn;
	}
	
	
	@Override
	public int insert(UsuarioDTO usuario) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows; //registros afectados
		
		try {
			conn = this.userConn;
			stmt = conn.prepareStatement(SQL_INSERT);
			int index = 1;//contador de columnas
			stmt.setString(index++, usuario.getUsuario());//param 1 => ?
			stmt.setString(index++, usuario.getPassword());//param 2 => ?
			System.out.println("Ejecutando query: " + SQL_INSERT);
			rows = stmt.executeUpdate();//no. registros afectados
			System.out.println("Registros afectados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}

	@Override
	public int update(UsuarioDTO usuario) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_UPDATE);
			stmt = conn.prepareStatement(SQL_UPDATE);
			int index = 1;
			stmt.setString(index++, usuario.getUsuario());
			stmt.setString(index++, usuario.getPassword());
			stmt.setInt(index, usuario.getIdUsuario());
			rows = stmt.executeUpdate();
			System.out.println("Registros actualizados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}

	@Override
	public int delete(UsuarioDTO usuario) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn;
		int rows;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_DELETE);
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, usuario.getIdUsuario());
			rows = stmt.executeUpdate();
			System.out.println("Registros eliminados:" + rows);

			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closePreparedStatement(stmt);
		}
	}

	@Override
	public List<UsuarioDTO> select() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UsuarioDTO> usuarios = new ArrayList<>();
		Connection conn;
		
		try {
			conn = this.userConn;
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			System.out.println("Ejecutando query: " + SQL_SELECT);

			if (rs != null) {
				while (rs.next()) {
					int id_usuario = rs.getInt(1);
					String usuario = rs.getString(2);
					String password = rs.getString(3);

					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setIdUsuario(id_usuario);
					usuarioDTO.setUsuario(usuario);
					usuarioDTO.setPassword(password);
					usuarios.add(usuarioDTO);
				}
			}

			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}

	@Override
	public UsuarioDTO select(int idUsuario) throws SQLException {
		Connection conn;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UsuarioDTO usuarioDTO = null;
		
		try {
			conn = this.userConn;
			System.out.println("Ejecutando query: " + SQL_SELECT_ID);
			stmt = conn.prepareStatement(SQL_SELECT_ID);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					int id_usuario = rs.getInt(1);
					String usuario = rs.getString(2);
					String password = rs.getString(3);

					usuarioDTO = new UsuarioDTO();
					usuarioDTO.setIdUsuario(id_usuario);
					usuarioDTO.setUsuario(usuario);
					usuarioDTO.setPassword(password);
				}
			}

			return usuarioDTO;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			Conexion.closeResultSet(rs);
			Conexion.closePreparedStatement(stmt);
		}
	}

}
