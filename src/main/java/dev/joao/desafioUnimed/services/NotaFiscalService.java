package dev.joao.desafioUnimed.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.joao.desafioUnimed.dto.NotaFiscalDTO;
import dev.joao.desafioUnimed.entities.Cliente;
import dev.joao.desafioUnimed.entities.NotaFiscal;
import dev.joao.desafioUnimed.exceptions.ClienteNotFoundException;
import dev.joao.desafioUnimed.mappers.NotaFiscalMapper;
import dev.joao.desafioUnimed.repositories.ClienteRepository;
import dev.joao.desafioUnimed.repositories.NotaFiscalRepository;
import lombok.AllArgsConstructor;

/**
 * Clase com os serviços relacionados ao cliente. Segunda camada da aplicação.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalService {
    
    private final NotaFiscalRepository notaFiscalRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final NotaFiscalMapper notaFiscalMapper = NotaFiscalMapper.INSTANCE;

    /**
     * Serviço que cadastra uma nota fiscal
     * @param notaFiscalDTO Obrigatório. Nota fiscal a ser cadastrada.
     * @return
     * @throws ClienteNotFoundException
     */
    public NotaFiscalDTO createNotaFiscal(NotaFiscalDTO notaFiscalDTO) throws ClienteNotFoundException {
        clienteService.findClienteById(notaFiscalDTO.getCliente().getId());
        NotaFiscal notaFiscal = notaFiscalMapper.toEntity(notaFiscalDTO);

        notaFiscal = notaFiscalRepository.save(notaFiscal);
        return notaFiscalMapper.toDTO(notaFiscal);
    }

    /**
     * Serviço que lista as notas fiscais. pode ser filtrado para as notas de um cliente especifico.
     * @param clienteId Optativo. Id do cliente para filtrar as notas.
     * @return Uma lista com as otas fiscais.
     * @throws ClienteNotFoundException
     */
    public List<NotaFiscalDTO> list(Long clienteId) throws ClienteNotFoundException {
        if(clienteId == null) {
            return notaFiscalRepository.findAll()
                    .stream()
                    .map(notaFiscalMapper::toDTO)
                    .collect(Collectors.toList());
        }
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()) {
            return notaFiscalRepository.findByCliente(cliente.get())
                    .stream()
                    .map(notaFiscalMapper::toDTO)
                    .collect(Collectors.toList());
        }
        throw new ClienteNotFoundException();
    }

    /**
     * Serviço para deletar uma nota fiscal.
     * @param id Obrigatório. Id da nota fiscal a ser deletada.
     */
    public void deleteNotaFiscalById(Long id) {
        notaFiscalRepository.deleteById(id);
    }
}
