package com.example.pedidos.models;

public class Producto {

	
	private Double precio;
	
	private String nombre;

	
	
	public Producto() {
		super();
	}

	public Producto(Double precio, String nombre) {
		super();
		this.precio = precio;
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
}
