package br.com.rocketwave.api.repository;

import br.com.rocketwave.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryItem extends JpaRepository<Item, String> {

}
