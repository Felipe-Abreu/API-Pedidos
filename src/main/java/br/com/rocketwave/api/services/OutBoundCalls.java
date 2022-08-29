package br.com.rocketwave.api.services;

import br.com.rocketwave.api.model.Cliente;

import java.util.List;

public interface OutBoundCalls {

    List<Cliente> clienteConsuming();

    Cliente postCliente(Cliente cliente);
}
