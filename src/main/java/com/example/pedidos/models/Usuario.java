package com.example.pedidos.models;



public class Usuario {

	private String cedula;
	
	private String direccion;

	
	public Usuario() {
		super();
	}

	public Usuario(String cedula, String direccion) {
		super();
		this.cedula = cedula;
		this.direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	
	
}
