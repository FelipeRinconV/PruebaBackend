package com.example.pedidos.controller;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.pedidos.datasource.Datasource;
import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.PedidoInputDto;
import com.example.pedidos.models.PedidoOutPutDto;
import com.example.pedidos.models.Usuario;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PedidosController {

	Datasource data = new Datasource();

	@RequestMapping(value="/createOrder", method = RequestMethod.POST)
	public ResponseEntity<PedidoOutPutDto> createCita(@RequestBody PedidoInputDto pedidoInput) {
		try {
			PedidoOutPutDto pedidoOuput = new PedidoOutPutDto();
			data.inicialezarDatos();
			Usuario user = data.getUser(pedidoInput.getUser().getCedula());

			if (user == null) {
				pedidoOuput.setMensaje("El usuario con la cedula " + pedidoInput.getUser().getCedula()
						+ " no se encuentra registrado");
				pedidoOuput.setPedido(null);

				return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_FOUND);
			} else {

				double total = 75000;
				Pedido pedido = new Pedido();
				pedido.setUser(user);
				pedido.setFechaPedido(new Date());
				pedido.setTotalPedido(total);
				pedido.setListaProductos(pedidoInput.getListaIdProductos());
				pedido.setValueIva(total*0.19);

				if (total > 70000) {
					pedido.setValorDomiciolio((double) 8000);
					data.insertPedido(1, pedido);
					return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_FOUND);

				} else if (total > 10000) {
					pedido.setValorDomiciolio((double) 0);
					data.insertPedido(1, pedido);
					return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_FOUND);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
