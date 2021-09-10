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

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalService {
    
    private final NotaFiscalRepository notaFiscalRepository;
    private final ClienteRepository clienteRepository;
    private final NotaFiscalMapper notaFiscalMapper = NotaFiscalMapper.INSTANCE;

    public NotaFiscalDTO createNotaFiscal(NotaFiscalDTO notaFiscalDTO) {
        NotaFiscal notaFiscal = notaFiscalMapper.toEntity(notaFiscalDTO);

        notaFiscal = notaFiscalRepository.save(notaFiscal);
        // notaFiscal.setCliente(clienteRepository.getById(notaFiscal.getCliente().getId()));
        return notaFiscalMapper.toDTO(notaFiscal);
    }

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

    public void deleteNotaFiscalById(Long id) {
        notaFiscalRepository.deleteById(id);
    }

    /* 
    public void deleteClienteById(Long id) throws ClienteNotFoundException {
        validateIfExists(id);
        clienteRepository.deleteById(id);
    } */
}
