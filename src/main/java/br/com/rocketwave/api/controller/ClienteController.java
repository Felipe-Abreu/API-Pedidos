package br.com.rocketwave.api.controller;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.services.ClienteServices;
import br.com.rocketwave.api.services.OutBoundCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
public class ClienteController {

    private final ClienteServices clienteServices;

    @Autowired
    public ClienteController(ClienteServices clienteServices) {

        this.clienteServices = clienteServices;
    }

    @GetMapping(value = "listaClientes")
    public List<Cliente> listCliente() {
        return clienteServices.clienteConsuming();
    }

    @PostMapping(value = "criaCliente", consumes = APPLICATION_JSON_VALUE)
    public Cliente criaCliente(@RequestBody @Valid Cliente cliente) {
        return clienteServices.postCliente(cliente);
    }

    @PutMapping(value = "listaClientes/{id}", consumes = APPLICATION_JSON_VALUE)
    public Cliente atualizaCliente(@RequestBody @Valid Cliente cliente){
        return clienteServices.atualizaCliente(cliente);
    }

    @DeleteMapping(value = "listaClientes/{id}")
    public void deletaCliente(@PathVariable("id") Cliente cliente){
        clienteServices.deletaCliente(cliente);
    }

}
