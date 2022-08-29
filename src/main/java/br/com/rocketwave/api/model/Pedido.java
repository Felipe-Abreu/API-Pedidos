package br.com.rocketwave.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Pedido {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sku;
    private String nome;
    private Integer quantidade;
    private Integer valorUnitario;
    private Integer valorTotal;

}
