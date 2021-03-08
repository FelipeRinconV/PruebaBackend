package com.example.pedidos.dto;

import com.example.pedidos.models.Usuario;

public class FacturaCanceladoDto {
	
	
	private Usuario user;
	
	private Double pagoPorCancelar;
	
	private String mensaje;
	
	
	public FacturaCanceladoDto() {
		super();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Double getPagoPorCancelar() {
		return pagoPorCancelar;
	}

	public void setPagoPorCancelar(Double pagoPorCancelar) {
		this.pagoPorCancelar = pagoPorCancelar;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	

}
