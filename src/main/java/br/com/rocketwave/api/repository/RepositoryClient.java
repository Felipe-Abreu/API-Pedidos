package br.com.rocketwave.api.repository;

import br.com.rocketwave.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryClient extends JpaRepository<Cliente, String> {

}
