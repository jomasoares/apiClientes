package dev.joao.desafioUnimed.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import dev.joao.desafioUnimed.enums.RegimeTributario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO do cliente. Clase criada para não sobrecarregar a classe Cliente
 * com duas responsabilidades (seguindo os conceitos SOLID). Enquanto a classe Cliente é a entidade principal
 * e serve omo referência para o banco de dados, esta classe contém as regras de validação de dados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String razaoSocial;

    @NotNull
    @CNPJ
    private String cnpj;

    @NotNull
    private RegimeTributario regimeTributario;

    @NotNull
    @Email
    private String email;

}
