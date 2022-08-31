package br.com.rocketwave.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @NotEmpty
    private String nome;
    @Id
    @NotEmpty
    @CPF
    private String cpf;
    @Column(unique = true)
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String endereco;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotNull
    private Integer telefone;

}
