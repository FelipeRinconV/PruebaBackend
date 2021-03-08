package com.example.pedidos.models;

import java.util.List;

public class PedidoInputDto {

	private Usuario user;
	
	private List<LineaPedido> listaIdProductos;

	
	public PedidoInputDto(Usuario user, List<LineaPedido> listaIdProductos) {
		super();
		this.user = user;
		this.listaIdProductos = listaIdProductos;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<LineaPedido> getListaIdProductos() {
		return listaIdProductos;
	}

	public void setListaIdProductos(List<LineaPedido> listaIdProductos) {
		this.listaIdProductos = listaIdProductos;
	}
	
	
	
}
