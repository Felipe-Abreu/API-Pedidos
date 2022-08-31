package br.com.rocketwave.api.controller;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Item;
import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.services.ItensServices;
import br.com.rocketwave.api.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
public class PedidosController {

    private final PedidosServices pedidosServices;

    @Autowired
    public PedidosController(PedidosServices pedidosServices) {

        this.pedidosServices = pedidosServices;
    }

    @GetMapping("listaPedidos")
    public List<Pedido> pedidos() {

        return pedidosServices.readPedidos();
    }

    @PostMapping(value = "criaPedido", consumes = APPLICATION_JSON_VALUE)
    public Pedido criaPedido(@RequestBody @Valid Pedido pedido) {
        pedido.calcuateValorTotalItem();
        return pedidosServices.postPedido(pedido);
    }

    @DeleteMapping(value = "listaPedidos/{id}")
    public void deletaPedido(@PathVariable("id") Pedido pedido){
        pedidosServices.deletaPedido(pedido);
    }
}
