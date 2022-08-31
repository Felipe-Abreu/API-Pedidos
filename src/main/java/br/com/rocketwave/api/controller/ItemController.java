package br.com.rocketwave.api.controller;

import br.com.rocketwave.api.model.Cliente;
import br.com.rocketwave.api.model.Item;
import br.com.rocketwave.api.services.ItensServices;
import br.com.rocketwave.api.services.OutBoundCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
public class ItemController {

    private final ItensServices itensServices;

    @Autowired
    public ItemController(ItensServices itensServices) {

        this.itensServices = itensServices;
    }

    @GetMapping(value = "listaItens")
    public List<Item> listItem() {
        return itensServices.itemConsuming();
    }

    @PostMapping(value = "criaItens", consumes = APPLICATION_JSON_VALUE)
    public Item criaItem(@RequestBody @Valid Item item) {
        return itensServices.postItem(item);
    }

}
