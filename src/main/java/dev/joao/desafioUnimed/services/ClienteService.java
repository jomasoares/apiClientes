package dev.joao.desafioUnimed.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.joao.desafioUnimed.dto.ClienteDTO;
import dev.joao.desafioUnimed.entities.Cliente;
import dev.joao.desafioUnimed.enums.RegimeTributario;
import dev.joao.desafioUnimed.exceptions.ClienteNotFoundException;
import dev.joao.desafioUnimed.exceptions.CnpjNotUniqueException;
import dev.joao.desafioUnimed.mappers.ClienteMapper;
import dev.joao.desafioUnimed.repositories.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    public ClienteDTO createCliente(ClienteDTO clienteDTO) throws CnpjNotUniqueException {
        validateCnpjUniqueness(clienteDTO.getCnpj());

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteDTO> listAll() {
        return clienteRepository.findAll()
                    .stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
    }

    public List<ClienteDTO> listByRegimeTributario(RegimeTributario regimeTributario) {
        return clienteRepository.findByRegimeTributario(regimeTributario)
                    .stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
    }

    public ClienteDTO findClienteById(Long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
            () -> new ClienteNotFoundException()
        );
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO updateCliente(ClienteDTO clienteDTO) throws ClienteNotFoundException {
        validateIfExists(clienteDTO.getId());
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    public void deleteClienteById(Long id) throws ClienteNotFoundException {
        validateIfExists(id);
        clienteRepository.deleteById(id);
    }

    public int clientesCount() {
        return (int)clienteRepository.count();
    }

    private void validateCnpjUniqueness(String cnpj) throws CnpjNotUniqueException {
        Optional<Cliente> cliente = clienteRepository.findByCnpj(cnpj);
        if (cliente.isPresent())
            throw new CnpjNotUniqueException(cnpj);
    }

    private void validateIfExists(Long id) throws ClienteNotFoundException {
        clienteRepository.findById(id).orElseThrow(
            () -> new ClienteNotFoundException()
        );
    }
}
