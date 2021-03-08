package com.example.pedidos.datasource;

import java.util.HashMap;

import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.Producto;
import com.example.pedidos.models.Usuario;

//Mapas para simular la base de datos
public class Datasource {

	HashMap<String,Usuario> mapUsers;

    HashMap<Integer,Pedido>mapPedidos;

	HashMap<Integer,Producto> mapProductos;

	public Datasource() {
		super();
		mapUsers = new HashMap<String, Usuario>();
		mapPedidos = new HashMap<Integer, Pedido>();
		mapProductos = new HashMap<Integer, Producto>();
	}

	public void inicialezarDatos() {
		mapUsers.put("12345", new Usuario("12345", "11#14-08"));

		mapProductos.put(1, new Producto(2000.00, "Item 1"));
		mapProductos.put(2, new Producto(30000.00, "Item 2"));
		mapProductos.put(3, new Producto(22000.00, "Item 3"));
		mapProductos.put(4, new Producto(25000.00, "Item 4"));
		mapProductos.put(4, new Producto(50.000, "Item 4"));
		
		
		
	}

	public void insertPedido(Integer id, Pedido pedido) {
		mapPedidos.putIfAbsent(id, pedido);
	}

	public void eliminarPedido(Integer id) {
		mapPedidos.remove(id);
	}

	public void actualizarPedido(int id, Pedido pedido) {
		mapPedidos.put(id, pedido);
	}

	public Usuario getUser(String cedula) {
		return mapUsers.get(cedula);
	}

	public Pedido getPedido(int key) {
		return mapPedidos.get(key);
	}

	public Producto getProducto(int key) {
		return mapProductos.get(key);
	}

}
