package br.com.rocketwave.api.services;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Item;
import br.com.rocketwave.api.repository.RepositoryClient;
import br.com.rocketwave.api.repository.RepositoryItem;
import br.com.rocketwave.api.repository.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ItensServices {

    private final RepositoryItem repositoryItem;

    @Autowired
    public ItensServices(RepositoryItem repositoryItem) {
        this.repositoryItem = repositoryItem;
    }

    public List<Item> itemConsuming() {
        List<Item> itens = repositoryItem.findAll();
        return itens;
    }

    public Item postItem(@RequestBody Item item) {
        item.setValorTotal();
        Item itemSalva = repositoryItem.save(item);
        return itemSalva;
    }
}
