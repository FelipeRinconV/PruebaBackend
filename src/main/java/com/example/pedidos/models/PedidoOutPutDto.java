package com.example.pedidos.models;

public class PedidoOutPutDto {


	private Pedido pedido;
	
	private String mensaje;
	
	
	public PedidoOutPutDto() {
		super();
	}

	public PedidoOutPutDto(Pedido pedido, String mensaje) {
		super();
		this.pedido = pedido;
		this.mensaje = mensaje;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
