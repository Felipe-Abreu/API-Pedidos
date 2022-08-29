package br.com.rocketwave.api.services;

import br.com.rocketwave.api.config.ConfigurationApi;
import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.repository.RepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OutBoundServices implements OutBoundCalls {

    private final RestTemplate restTemplate;
    private final RepositoryDB repositoryDB;
    private final ConfigurationApi configurationApi;

    @Autowired
    public OutBoundServices(RestTemplate restTemplate, RepositoryDB repositoryDB, ConfigurationApi configurationApi) {

        this.restTemplate = restTemplate;
        this.repositoryDB = repositoryDB;
        this.configurationApi = configurationApi;
    }

    public List<Cliente> clienteConsuming() {
        var response = restTemplate.getForEntity(configurationApi.getBhutApi(), Cliente[].class);
        return Optional.ofNullable(response.getBody()).map(List::of).orElse(List.of());
    }

    public Cliente postCliente(Cliente cliente) {
        var resultCar = restTemplate.postForObject(configurationApi.getBhutApi(), cliente, Cliente.class);
        if (Objects.nonNull(resultCar)) {
            var log = new Pedido();
            log.setDateCar(Date.from(OffsetDateTime.now().toInstant()));
            log.setCarId(resultCar.getId());
            repositoryDB.save(log);
            return resultCar;
        }
        return null;
    }
}
