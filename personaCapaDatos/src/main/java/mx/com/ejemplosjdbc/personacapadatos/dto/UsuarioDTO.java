/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.ejemplosjdbc.personacapadatos.dto;

/**
 *
 * @author German Juarez
 */
public class UsuarioDTO {
	private int idUsuario;
	private String usuario;
	private String password;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(int idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioDTO{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", password=" + password + '}';
	}
}
