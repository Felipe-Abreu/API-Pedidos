package br.com.rocketwave.api.services;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.repository.RepositoryClient;
import br.com.rocketwave.api.repository.RepositoryItem;
import br.com.rocketwave.api.repository.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices implements OutBoundCalls {

    private final RepositoryClient repositoryClient;
    private final RepositoryPedido repositoryPedido;
    private final RepositoryItem repositoryItem;

    @Autowired
    public ClienteServices(RepositoryClient repositoryClient, RepositoryPedido repositoryPedido, RepositoryItem repositoryItem) {
        this.repositoryPedido = repositoryPedido;
        this.repositoryClient = repositoryClient;
        this.repositoryItem = repositoryItem;
    }

    public List<Cliente> clienteConsuming() {
        List<Cliente> clientes = repositoryClient.findAll();
        return clientes;
    }

    public Cliente postCliente(@RequestBody @Valid Cliente cliente) {
        Cliente clienteSalva = repositoryClient.save(cliente);
        return clienteSalva;
    }

    public Cliente atualizaCliente(@RequestBody @Valid Cliente cliente){
        Optional<Cliente> atualizaCliente = repositoryClient.findById(cliente.getCpf());
        if (atualizaCliente != null){
            return repositoryClient.save(cliente);
        } else {
            return null;
        }
    }

    public void deletaCliente(Cliente cliente) {
        if (repositoryClient.existsById(cliente.getCpf())){
            repositoryClient.deleteById(cliente.getCpf());
        }
    }
}
