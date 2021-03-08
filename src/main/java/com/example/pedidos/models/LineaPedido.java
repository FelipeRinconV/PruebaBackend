package com.example.pedidos.models;

public class LineaPedido {

	
	private Integer idLinePedido;
	private Integer IdProducto;
	private int cantidadProducto;


	public LineaPedido() {
		super();
	}


	public Integer getIdLinePedido() {
		return idLinePedido;
	}


	public void setIdLinePedido(Integer idLinePedido) {
		this.idLinePedido = idLinePedido;
	}


	public Integer getIdProducto() {
		return IdProducto;
	}


	public void setIdProducto(Integer idProducto) {
		IdProducto = idProducto;
	}


	public int getCantidadProducto() {
		return cantidadProducto;
	}


	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	

  
	
	
	
	
	
	
}
