package br.com.rocketwave.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal valorTotal = new BigDecimal(0.0);
    @NotEmpty
    private String enderecoPedido;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_sku")
    private List<Item> item;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;

    public void calcuateValorTotalItem(){
        item.forEach(i -> {
            valorTotal=valorTotal.add(i.setValorTotal());
        });
    }

}
