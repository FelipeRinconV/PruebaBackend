package com.example.pedidos.repository;


import java.util.List;

import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.Usuario;

public interface Repository {
	

	Pedido crearPedido(String cedula,String direccion, List<Integer> idProductos);

	Pedido inser(Usuario user,Pedido pedido);
	
	Pedido updatedOrder(Usuario user,Pedido peido);
	

}
