package com.example.pedidos.dto;

import java.util.List;

import com.example.pedidos.models.LineaPedido;
import com.example.pedidos.models.Usuario;

public class PedidoNuevoDto {
	
	
	private  int idPedido;
	
	private List<LineaPedido> lineasPedido;
	
	
	private Usuario user;


	public PedidoNuevoDto() {
		super();
	}


	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}


	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}


	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}


	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
	
	

}
