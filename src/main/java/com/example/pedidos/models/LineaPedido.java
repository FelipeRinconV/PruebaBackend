package com.example.pedidos.models;

public class LineaPedido {

	private Producto producto;
	
	private int cantidadPedido;

	private double ivaProducto;
	

	public LineaPedido() {
		super();
	}

	public LineaPedido(Producto producto, int cantidadPedido, double ivaProducto) {
		super();
		this.producto = producto;
		this.cantidadPedido = cantidadPedido;
		this.ivaProducto = ivaProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadPedido() {
		return cantidadPedido;
	}

	public void setCantidadPedido(int cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}

	public double getIvaProducto() {
		return ivaProducto;
	}

	public void setIvaProducto(double ivaProducto) {
		this.ivaProducto = ivaProducto;
	}
	
	
	
	
	
	
}
