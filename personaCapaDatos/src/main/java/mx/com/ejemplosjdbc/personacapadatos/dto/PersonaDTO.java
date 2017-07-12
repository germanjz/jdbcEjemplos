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
public class PersonaDTO {
	private int idPersona;
	private String nombre;
	private String apellido;

	public PersonaDTO() {
		super();
	}

	public PersonaDTO(int idPersona) {
		super();
		this.idPersona = idPersona;
	}
	
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "PersonaDTO{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + '}';
	}
}
