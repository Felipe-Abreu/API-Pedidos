package br.com.rocketwave.api.repository;

import br.com.rocketwave.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPedido extends JpaRepository<Pedido, String> {

}
