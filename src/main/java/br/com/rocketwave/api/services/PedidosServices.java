package br.com.rocketwave.api.services;

import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.repository.RepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServices {

    @Autowired
    private RepositoryDB repositoryDB;

    public List<Pedido> readLogs() {
        return repositoryDB.findAll();
    }

}
