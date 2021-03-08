package com.example.pedidos.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.pedidos.datasource.Datasource;
import com.example.pedidos.dto.PedidoInputDto;
import com.example.pedidos.dto.PedidoOutPutDto;
import com.example.pedidos.models.LineaPedido;
import com.example.pedidos.models.Pedido;
import com.example.pedidos.models.Producto;
import com.example.pedidos.models.Usuario;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PedidosController {

	Datasource data = new Datasource();

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public ResponseEntity<PedidoOutPutDto> createOrder(@RequestBody PedidoInputDto pedidoInput) {
		try {
			PedidoOutPutDto pedidoOuput = new PedidoOutPutDto();

			Usuario user = pedidoInput.getUser();
			// Se verifica el caso de uso
			if (user.getCedula().equals("12345") && user.getDireccion().trim().equals("11#14-08")) {

				double total = 0;

				for (LineaPedido lineaP : pedidoInput.getListaIdProductos()) {

					Producto producto = data.getProducto(lineaP.getIdProducto());
					// Se verifican que los productos se encuentren registrados
					if (producto == null) {
						pedidoOuput.setMensaje("El producto con el id:" + lineaP.getIdProducto()
								+ " no se encuentra registrado en la base de datos.");
						pedidoOuput.setPedido(null);
						return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_FOUND);
					}
					total += lineaP.getCantidadProducto() * producto.getPrecio();
				}

				Pedido pedido = new Pedido();
				pedido.setUser(user);
				pedido.setFechaPedido(new Date());
				pedido.setListaProductos(pedidoInput.getListaIdProductos());
				pedido.setValueIva(total * 0.19);

				// Criterio 1
				if (total > 70000) {
					pedido.setValorDomiciolio((double) 8000);
					pedido.setTotalPedido(total + pedido.getValueIva() + pedido.getValorDomiciolio());

					data.insertPedido(pedido);
					pedidoOuput.setMensaje("El pedido fue asignado con exito.");
					pedidoOuput.setPedido(pedido);
					return new ResponseEntity<>(pedidoOuput, HttpStatus.CREATED);

				}
				// Criterio 2
				else if (total > 10000) {

					pedido.setValorDomiciolio((double) 0);
					pedido.setTotalPedido(total + pedido.getValueIva());
					data.insertPedido(pedido);

					pedidoOuput.setMensaje("El pedido fue asignado con exito.");
					pedidoOuput.setPedido(pedido);
					return new ResponseEntity<>(pedidoOuput, HttpStatus.CREATED);
				}
				// No se especifica caso de uso para < 70.000
				else {

					pedidoOuput.setMensaje("No se especifico caso de uso para estos parametros.");
					return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_IMPLEMENTED);
				}
			} else {
				pedidoOuput.setPedido(null);
				pedidoOuput.setMensaje("No se especifico caso de uso para estos parametros.");
				return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_IMPLEMENTED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public ResponseEntity<PedidoOutPutDto> createOrder( @RequestBody Pedido newPedido) {

		PedidoOutPutDto pedidoOutPutDto = new PedidoOutPutDto();

		Pedido pedidoActual = data.getPedido(newPedido.getId());

		if (pedidoActual != null) {

			long diferenciaEn_ms = pedidoActual.getFechaPedido().getTime() - new Date().getTime();
			long horas = TimeUnit.HOURS.convert(diferenciaEn_ms, TimeUnit.MILLISECONDS);

			// Primer critero de aceptacion
			if (horas > 5) {
				double valorProductosNuevos = 0;
				for(int i = 0 ; i < newPedido.getListaProductos().size();i++) {
					//Verifica que los nuevos  productos existen en la base de datos
					int idProducto = newPedido.getListaProductos().get(i).getIdProducto();
					Producto producto = data.getProducto(idProducto);
					if (producto == null) {
						pedidoOutPutDto.setMensaje("El producto con el id:" + idProducto
								+ " no se encuentra registrado en la base de datos.");
						pedidoOutPutDto.setPedido(null);
						return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.NOT_FOUND);
					}
					valorProductosNuevos+=producto.getPrecio();
				}
				
				double valorProductosAnteriores = 0;
				
				for(int i = 0 ; i < newPedido.getListaProductos().size();i++) {
					int idProducto = newPedido.getListaProductos().get(i).getIdProducto();
					Producto producto = data.getProducto(idProducto);
					valorProductosAnteriores+=producto.getPrecio();
				}
				
				
				if(valorProductosNuevos >= valorProductosAnteriores ) {
					
					if(valorProductosNuevos> 100000) {
						newPedido.setValorDomiciolio((double) 0);
					}

					data.actualizarPedido(newPedido);
					pedidoOutPutDto.setMensaje("El pedido fue actualizado exitosamente.");
					pedidoOutPutDto.setPedido(newPedido);
					return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.ACCEPTED);
				}
				

			} else {
				pedidoOutPutDto.setMensaje("El pedido se realizo hace mas de 5 horas por lo tanto no puede ser editado.");
				pedidoOutPutDto.setPedido(null);
				return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.ACCEPTED);
			}

		} else {
			pedidoOutPutDto
					.setMensaje("El pedido con el id " + newPedido.getId() + " no se encuentra registrado en la base de datos.");
			pedidoOutPutDto.setPedido(null);
			return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.NOT_FOUND);
		}

		return null;

	}

}
