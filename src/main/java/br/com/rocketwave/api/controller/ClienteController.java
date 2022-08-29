package br.com.rocketwave.api.controller;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.services.OutBoundCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
public class ClienteController {

    private final OutBoundCalls outBoundServices;

    @Autowired
    public ClienteController(OutBoundCalls outBoundServices) {

        this.outBoundServices = outBoundServices;
    }

    @GetMapping("listaClientes")
    public List<Cliente> listCliente() {
        return outBoundServices.clienteConsuming();
    }

    @PostMapping(value = "criaCliente", consumes = APPLICATION_JSON_VALUE)
    public Cliente createCar(@RequestBody Cliente cliente) {
        return outBoundServices.postCliente(cliente);
    }

}
