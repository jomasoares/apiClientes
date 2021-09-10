package dev.joao.desafioUnimed.builder;

import dev.joao.desafioUnimed.dto.ClienteDTO;
import dev.joao.desafioUnimed.enums.RegimeTributario;
import lombok.Builder;

@Builder
public class ClienteDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String razaoSocial = "Empresa Teste Ltda.";

    @Builder.Default
    private String cnpj = "77.938.610/0001-65";

    @Builder.Default
    private RegimeTributario regimeTributario = RegimeTributario.LUCRO_PRESUMIDO;

    @Builder.Default
    private String email = "teste@teste.com";

    public ClienteDTO toClienteDTO() {
        return new ClienteDTO(
            id,
            razaoSocial,
            cnpj,
            regimeTributario,
            email
        );
    }
}
