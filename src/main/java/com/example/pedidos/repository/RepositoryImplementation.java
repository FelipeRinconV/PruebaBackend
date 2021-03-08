package com.example.pedidos.repository;

import java.util.List;

import com.example.pedidos.datasource.Datasource;
import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.Usuario;

public class RepositoryImplementation  implements Repository{

	Datasource data = new Datasource();

	@Override
	public Pedido crearPedido(String cedula, String direccion, List<Integer> idProductos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido inser(Usuario user, Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido updatedOrder(Usuario user, Pedido peido) {
		// TODO Auto-generated method stub
		return null;
	}

}
