package com.example.pedidos.datasource;

import java.util.HashMap;

import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.Producto;
import com.example.pedidos.models.Usuario;

//Mapas para simular la base de datos
public class Datasource {

	//TODO varibale sin usar, por si se desea registrar usuarios
	HashMap<String,Usuario> mapUsers;

    HashMap<Integer,Pedido>mapPedidos;

	HashMap<Integer,Producto> mapProductos;
	
	int id=1;

	public Datasource() {
		super();
		mapUsers = new HashMap<String, Usuario>();
		mapPedidos = new HashMap<Integer, Pedido>();
		mapProductos = new HashMap<Integer, Producto>();
		inicialezarDatos();
	}

	public void inicialezarDatos() {
		//mapUsers.put("12345", new Usuario("12345", "11#14-08"));

		// PRODUCTOS DISPONIBLES PARA LAS PRUEBAS
		mapProductos.put(id, new Producto(2000.00, "Item 1"));
		id++;
		mapProductos.put(id, new Producto(30000.00, "Item 2"));
		id++;
		mapProductos.put(id, new Producto(22000.00, "Item 3"));
		id++;
		mapProductos.put(id, new Producto(25000.00, "Item 4"));
		id++;
		mapProductos.put(id, new Producto(50.000, "Item 4"));
		
	}

	public void insertPedido(Pedido pedido) {
		id++;
		pedido.setId(id);
		mapPedidos.putIfAbsent(id, pedido);
	}


	public void eliminarPedido(Integer id) {
		mapPedidos.remove(id);
	}

	public void actualizarPedido(Pedido pedido) {
		mapPedidos.put(pedido.getId(), pedido);
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
