package dev.joao.desafioUnimed.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dev.joao.desafioUnimed.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO da NotaFiscal. Clase criada para não sobrecarregar a classe NotaFiscal
 * com duas responsabilidades (seguindo os conceitos SOLID). Enquanto a classe NotaFiscal é a entidade principal
 * e serve omo referência para o banco de dados, esta classe contém as regras de validação de dados.
 */
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
