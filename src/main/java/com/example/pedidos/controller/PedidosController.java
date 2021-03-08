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
import com.example.pedidos.dto.PedidoNuevoDto;
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
			String dire = pedidoInput.getUser().getDireccion().replaceAll(" ", "");
			
			if (user.getCedula().equals("12345") && dire.equals("11#14-08")) {

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

				if (total > 70000) {
					if (total > 100000) {

						pedido.setValorDomiciolio((double) 0);
						pedido.setValorTotalProductos(total);
						pedido.setTotalPedido(total + pedido.getValueIva());
						data.insertPedido(pedido);

						pedidoOuput.setMensaje("El pedido fue asignado con exito.");
						pedidoOuput.setPedido(pedido);
						return new ResponseEntity<>(pedidoOuput, HttpStatus.CREATED);
					}
					// Criterio 1
					else {
						pedido.setValorDomiciolio((double) 8000);
						pedido.setValorTotalProductos(total);
						pedido.setTotalPedido(total + pedido.getValueIva() + pedido.getValorDomiciolio());

						data.insertPedido(pedido);
						pedidoOuput.setMensaje("El pedido fue asignado con exito.");
						pedidoOuput.setPedido(pedido);
						return new ResponseEntity<>(pedidoOuput, HttpStatus.CREATED);

					}
				} else {
					pedidoOuput.setMensaje("No se especifico caso de uso para valores  menores a 70.000.");
					return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_IMPLEMENTED);
				}
			} else {
				pedidoOuput.setPedido(null);
				pedidoOuput.setMensaje("No se especifico caso de uso para estos parametros ..");
				return new ResponseEntity<>(pedidoOuput, HttpStatus.NOT_IMPLEMENTED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/actualizarPedido", method = RequestMethod.POST)
	public ResponseEntity<PedidoOutPutDto> actualizarPedido(@RequestBody PedidoNuevoDto newPedido) {

		// Pojo para la salida del json
		PedidoOutPutDto pedidoOutPutDto = new PedidoOutPutDto();

		// Se recupera el pedido de la base de datos
		Pedido pedidoActual = data.getPedido(newPedido.getIdPedido());

		// Se verifica que el pedido exista
		if (pedidoActual != null) {

			double valorProductosNuevos = 0;
			for (int i = 0; i < newPedido.getLineasPedido().size(); i++) {
				// Verifica que los nuevos productos existen en la base de datos
				int idProducto = newPedido.getLineasPedido().get(i).getIdProducto();
				Producto producto = data.getProducto(idProducto);
				if (producto == null) {
					pedidoOutPutDto.setMensaje(
							"El producto con el id:" + idProducto + " no se encuentra registrado en la base de datos.");
					pedidoOutPutDto.setPedido(null);
					return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.NOT_FOUND);
				}
				valorProductosNuevos += producto.getPrecio()*newPedido.getLineasPedido().get(i).getCantidadProducto();
			}

			double valorProductosAnteriores = 0;
			for (int i = 0; i < pedidoActual.getListaProductos().size(); i++) {
				int idProducto = pedidoActual.getListaProductos().get(i).getIdProducto();
				Producto producto = data.getProducto(idProducto);
				valorProductosAnteriores += producto.getPrecio() * pedidoActual.getListaProductos().get(i).getCantidadProducto();
			}

			long diferenciaEn_ms = pedidoActual.getFechaPedido().getTime() - new Date().getTime();
			long horas = TimeUnit.HOURS.convert(diferenciaEn_ms, TimeUnit.MILLISECONDS);

			// Primer critero de aceptacion
			if (horas > 5) {

				if (valorProductosNuevos > 100000) {
					pedidoActual.setValorDomiciolio((double) 0);
				}

				pedidoActual.setListaProductos(newPedido.getLineasPedido());
				pedidoActual.setUser(newPedido.getUser());
				pedidoActual.setValueIva(valorProductosNuevos * 0.19);
				pedidoActual.setValorTotalProductos(valorProductosNuevos);
				pedidoActual.setTotalPedido(pedidoActual.getValueIva() + pedidoActual.getValorDomiciolio() + valorProductosNuevos);
				pedidoActual.setFechaPedido(new Date());

				pedidoOutPutDto.setMensaje("El pedido fue actualizado exitosamente.");
				pedidoOutPutDto.setPedido(pedidoActual);
				data.actualizarPedido(pedidoActual);

				return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.CREATED);

			} else {

				if (valorProductosNuevos >= valorProductosAnteriores) {

					if (valorProductosNuevos > 100000) {
						pedidoActual.setValorDomiciolio((double) 0);
					}

					pedidoActual.setListaProductos(newPedido.getLineasPedido());
					pedidoActual.setUser(newPedido.getUser());
					pedidoActual.setValueIva(valorProductosNuevos * 0.19);
					pedidoActual.setValorTotalProductos(valorProductosNuevos);
					pedidoActual.setTotalPedido((double)pedidoActual.getValueIva() + pedidoActual.getValorDomiciolio() + valorProductosNuevos);
					pedidoActual.setFechaPedido(new Date());

					data.actualizarPedido(pedidoActual);

					pedidoOutPutDto.setMensaje("El pedido fue actualizado exitosamente.");
					pedidoOutPutDto.setPedido(pedidoActual);

					return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.CREATED);
				} else {
					pedidoOutPutDto.setMensaje(
							"El pedido solo es actualizable antes de transcurrir 5 horas si el monto es igual o mayor al anterior pedido, si no es asi por "
									+ "por favor espere que transcurra minimo 5 horas, Nota: Horas actuales transcurridas.: "
									+ horas);
					pedidoOutPutDto.setPedido(null);
					return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.ACCEPTED);
				}

			}

		} else {
			pedidoOutPutDto.setMensaje("El pedido con el id " + newPedido.getIdPedido()
					+ " no se encuentra registrado en la base de datos.");
			pedidoOutPutDto.setPedido(null);
			return new ResponseEntity<>(pedidoOutPutDto, HttpStatus.NOT_FOUND);
		}

	}

}
