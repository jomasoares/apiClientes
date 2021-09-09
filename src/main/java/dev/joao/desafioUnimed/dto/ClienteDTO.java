package dev.joao.desafioUnimed.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import dev.joao.desafioUnimed.enums.RegimeTributario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
