package com.puerta18.model;

import java.sql.Date;

public class Socio {

	int id;
	String nombre;
	String apellido;
	String email;
	String dni;
	String celular;
	String telefono;
	String telefono2;
	String direccion;
	String genero;
	String localidad;
	Date fecha_de_nacimiento;
	boolean presente;
	
	


	public Socio(int id, String nombre, String apellido, String email, String dni, String celular, String telefono,
			String telefono2, String direccion, String genero, String localidad, Date fecha_de_nacimiento,
			boolean presente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.dni = dni;
		this.celular = celular;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.direccion = direccion;
		this.genero = genero;
		this.localidad = localidad;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.presente = presente;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
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




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
	}




	public String getCelular() {
		return celular;
	}




	public void setCelular(String celular) {
		this.celular = celular;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getTelefono2() {
		return telefono2;
	}




	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}




	public String getDireccion() {
		return direccion;
	}




	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String genero) {
		this.genero = genero;
	}




	public String getLocalidad() {
		return localidad;
	}




	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}




	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}




	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}




	public boolean isPresente() {
		return presente;
	}




	public void setPresente(boolean presente) {
		this.presente = presente;
	}




	
	
}
