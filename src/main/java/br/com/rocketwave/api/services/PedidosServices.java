package br.com.rocketwave.api.services;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Item;
import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.repository.RepositoryClient;
import br.com.rocketwave.api.repository.RepositoryItem;
import br.com.rocketwave.api.repository.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class PedidosServices {

    private final RepositoryClient repositoryClient;
    private final RepositoryPedido repositoryPedido;
    private final RepositoryItem repositoryItem;

    @Autowired
    public PedidosServices(RepositoryClient repositoryClient, RepositoryPedido repositoryPedido, RepositoryItem repositoryItem) {
        this.repositoryPedido = repositoryPedido;
        this.repositoryClient = repositoryClient;
        this.repositoryItem = repositoryItem;
    }

    public List<Pedido> readPedidos() {
        return repositoryPedido.findAll();
    }

    public Pedido postPedido(@RequestBody @Valid Pedido pedido) {
        return repositoryPedido.save(pedido);

    }

    public void deletaPedido(Pedido pedido) {
        if (repositoryPedido.existsById(String.valueOf(pedido.getId()))){
            repositoryPedido.deleteById(String.valueOf(pedido.getId()));
        }
    }
}
