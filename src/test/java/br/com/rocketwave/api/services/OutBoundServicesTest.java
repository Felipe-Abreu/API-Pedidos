package br.com.rocketwave.api.services;

import br.com.rocketwave.api.config.ConfigurationApi;
import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Pedido;
import br.com.rocketwave.api.repository.RepositoryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
class OutBoundServicesTest {

    @Mock
    OutBoundCalls outBoundCalls;
    @Mock
    RestTemplate restTemplate;
    @Mock
    RepositoryDB repositoryDB;
    Cliente clienteWithoutID;
    Cliente clienteWithID;
    OutBoundServices outBoundServices;
    Pedido pedidoWithID;
    @Mock
    ConfigurationApi configurationApi;

    @BeforeEach
    void load() {
        outBoundCalls = Mockito.mock(OutBoundCalls.class);
        restTemplate = Mockito.mock(RestTemplate.class);
        repositoryDB = Mockito.mock(RepositoryDB.class);
        configurationApi = Mockito.mock(ConfigurationApi.class);
        Mockito.when(configurationApi.getBhutApi()).thenReturn("");
        outBoundServices = new OutBoundServices(restTemplate, repositoryDB, configurationApi);
    }

    @Test
    void testLog() {
        initializeCar();
        Mockito.when(restTemplate.postForObject(configurationApi.getBhutApi(), clienteWithoutID, Cliente.class)).thenReturn(clienteWithID);
        initializeLog();
        outBoundServices.postCliente(clienteWithoutID);
        Mockito.when(repositoryDB.save(Mockito.any(Pedido.class))).thenReturn(pedidoWithID);
        verify(repositoryDB, times(1)).save(Mockito.any(Pedido.class));
    }

    private void initializeCar() {
        clienteWithID = new Cliente();
        clienteWithoutID = new Cliente();
        clienteWithoutID.setAge(2001);
        clienteWithoutID.setBrand("VW");
        clienteWithoutID.setPrice("50");
        clienteWithoutID.setTitle("Fusca");
        clienteWithID.setAge(2001);
        clienteWithID.setId("2");
        clienteWithID.setBrand("VW");
        clienteWithID.setPrice("50");
        clienteWithID.setTitle("Fusca");
    }

    private void mockCarId() {
        initializeCar();
        Mockito.when(outBoundCalls.postCliente(clienteWithoutID)).thenReturn(clienteWithID);
    }

    private void initializeLog() {
        pedidoWithID = new Pedido();
        pedidoWithID.setId("23");
        pedidoWithID.setCarId("2");
        pedidoWithID.setDateCar(Date.from(OffsetDateTime.now().toInstant()));
    }

    private void mockListEmpty() {

        Mockito.when(outBoundCalls.clienteConsuming()).thenReturn(List.of());
    }

    private void mockListTwoCars() {
        var carTwo = new Cliente();
        var carOne = new Cliente();
        carOne.setAge(2001);
        carOne.setId("1");
        carOne.setBrand("VW");
        carOne.setPrice("50");
        carOne.setTitle("Fusca");
        carTwo.setAge(2020);
        carTwo.setId("2");
        carTwo.setBrand("Ford");
        carTwo.setPrice("5000");
        carTwo.setTitle("Ka");
        Mockito.when(outBoundCalls.clienteConsuming()).thenReturn(List.of(carOne, carTwo));
    }

    @Test
    void testIfListIsEmpty() {
        mockListEmpty();
        Assertions.assertTrue(outBoundCalls.clienteConsuming().isEmpty());
    }

    @Test
    void testIfListTwoCars() {
        mockListTwoCars();
        var cars = outBoundCalls.clienteConsuming();
        var carFord = cars.stream().filter(cliente -> cliente.getBrand().equals("Ford")).findFirst().orElse(null);
        var carVW = cars.stream().filter(cliente -> cliente.getBrand().equals("VW")).findFirst().orElse(null);
        Assertions.assertNotNull(carFord);
        Assertions.assertNotNull(carVW);
        Assertions.assertEquals("Ka", carFord.getTitle());
        Assertions.assertEquals("Fusca", carVW.getTitle());
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    void testCreateCar() {
        var car = outBoundCalls.postCliente(clienteWithoutID);
        Assertions.assertEquals(clienteWithID, car);
    }
}