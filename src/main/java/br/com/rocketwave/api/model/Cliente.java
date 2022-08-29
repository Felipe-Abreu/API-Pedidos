package br.com.rocketwave.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private LocalDate DataNascimento;
    private Integer telefone;

}
