package br.com.rocketwave.api.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Data
public class Item {

    @NotEmpty
    @Id
    private String sku;
    @NotEmpty
    private String nome;
    @NotNull
    @Min(value = 1)
    private BigDecimal quantidade;
    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal valorUnitario;
    @Setter
    private BigDecimal valorTotal;

    public BigDecimal setValorTotal() {
        this.valorTotal = valorUnitario.multiply(quantidade);
        return this.valorTotal;
    }
}
