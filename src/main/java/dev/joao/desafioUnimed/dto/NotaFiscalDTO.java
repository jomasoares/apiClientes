package dev.joao.desafioUnimed.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dev.joao.desafioUnimed.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class NotaFiscalDTO {
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Cliente cliente;

    @NotNull
    private Double valor;
}
