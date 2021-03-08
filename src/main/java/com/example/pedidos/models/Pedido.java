package com.example.pedidos.models;


import java.util.Date;
import java.util.List;



public class Pedido {
	

	private Integer id;
	private Usuario user;
    
	private Date fechaPedido;
	private List<LineaPedido> listaProductos;
	
	private Double valorTotalProductos;
	private Double valorDomicilio;
	private Double valueIva;
	private Double totalPedido;
	

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
		return valorDomicilio;
	}

	public void setValorDomiciolio(Double valorDomiciolio) {
		this.valorDomicilio = valorDomiciolio;
	}

	public Double getValueIva() {
		return valueIva;
	}

	public void setValueIva(Double valueIva) {
		this.valueIva = valueIva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorTotalProductos() {
		return valorTotalProductos;
	}

	public void setValorTotalProductos(Double valorTotalProductos) {
		this.valorTotalProductos = valorTotalProductos;
	}
	

	
	

}
