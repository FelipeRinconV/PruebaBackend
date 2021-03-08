package com.example.pedidos.models;


import java.util.Date;
import java.util.List;



public class Pedido {
	

	private Usuario user;

    private Double totalPedido;
	
	private Date fechaPedido;
	
	private List<LineaPedido> listaProductos;
	
	private Double valorDomiciolio;
	
	private Double valueIva;
	
	

	public Pedido(Usuario user, Double totalPedido, Date fechaPedido, List<LineaPedido> listaProductos) {
		super();
		this.user = user;
		this.totalPedido = totalPedido;
		this.fechaPedido = fechaPedido;
		this.listaProductos = listaProductos;
	}

	public Pedido() {
	super();
	}


	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public List<LineaPedido> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<LineaPedido> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Double getValorDomiciolio() {
		return valorDomiciolio;
	}

	public void setValorDomiciolio(Double valorDomiciolio) {
		this.valorDomiciolio = valorDomiciolio;
	}

	public Double getValueIva() {
		return valueIva;
	}

	public void setValueIva(Double valueIva) {
		this.valueIva = valueIva;
	}
	
	
	
	
	

}
