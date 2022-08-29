package br.com.rocketwave.api.controller;

import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PedidosController {

    private final PedidosServices pedidosCliente;

    @Autowired
    public PedidosController(PedidosServices pedidosCliente) {

        this.pedidosCliente = pedidosCliente;
    }

    @GetMapping("pedidos")
    public List<Pedido> pedidos() {

        return pedidosCliente.readLogs();
    }
}
