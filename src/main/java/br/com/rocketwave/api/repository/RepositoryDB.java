package br.com.rocketwave.api.repository;

import br.com.rocketwave.api.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongodb")
public interface RepositoryDB extends MongoRepository<Pedido, String> {

}
