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

/**
 * Clase com os serviços relacionados ao cliente. Segunda camada da aplicação.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    /**
     * Serviço responsável por cadastrar um cliente.
     * @param clienteDTO Obrigatório. O cliente que deve ser cadastrado.
     * @return os dados do cliente cadastrados no banco.
     * @throws CnpjNotUniqueException
     */
    public ClienteDTO createCliente(ClienteDTO clienteDTO) throws CnpjNotUniqueException {
        validateCnpjUniqueness(clienteDTO.getCnpj());

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Serviço responsável por listar todos os clientes.
     * @return Uma lista com todos os clientes cadastrados.
     */
    public List<ClienteDTO> listAll() {
        return clienteRepository.findAll()
                    .stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
    }

    /**
     * Serviço responsável por listar os clientes com determidado regime tributário.
     * @param regimeTributario Obrigatório. Regime tributário desejado.
     * @return Uma lista com os clientes que possuem o regime tributário desejado.
     */
    public List<ClienteDTO> listByRegimeTributario(RegimeTributario regimeTributario) {
        return clienteRepository.findByRegimeTributario(regimeTributario)
                    .stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
    }

    /**
     * Serviço responsável por buscar os dados de um cliente específico.
     * @param id Obrigatório. Id do cliente desejado.
     * @return O cliente desejado.
     * @throws ClienteNotFoundException
     */
    public ClienteDTO findClienteById(Long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
            () -> new ClienteNotFoundException()
        );
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Serviço responsável por atualizar os dados de um cliente.
     * @param clienteDTO Obrigatório. Dados do cliente para atualizar.
     * @return O cliente com os dados atualizados.
     * @throws ClienteNotFoundException
     */
    public ClienteDTO updateCliente(ClienteDTO clienteDTO) throws ClienteNotFoundException {
        validateIfExists(clienteDTO.getId());
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Serviço responsável por deletar um cliente.
     * @param id Obrigatório. Id do cliente que se deseja deletar.
     * @throws ClienteNotFoundException
     */
    public void deleteClienteById(Long id) throws ClienteNotFoundException {
        validateIfExists(id);
        clienteRepository.deleteById(id);
    }

    /**
     * Serviço responsável por retornar o total de clientes cadastrados no banco.
     * @return inteiro com o total de clientes no banco.
     */
    public int clientesCount() {
        return (int)clienteRepository.count();
    }

    /**
     * Serviço responsável por validar se um CNPJ já está cadastrado no banco.
     * @param cnpj Obrigatório. CNPJ procurado.
     * @throws CnpjNotUniqueException
     */
    private void validateCnpjUniqueness(String cnpj) throws CnpjNotUniqueException {
        Optional<Cliente> cliente = clienteRepository.findByCnpj(cnpj);
        if (cliente.isPresent())
            throw new CnpjNotUniqueException(cnpj);
    }

    /**
     * Serviço que valida se um cliente existe no banco de dados.
     * @param id Obrigatório. Id do cliente desejado
     * @throws ClienteNotFoundException
     */
    private void validateIfExists(Long id) throws ClienteNotFoundException {
        clienteRepository.findById(id).orElseThrow(
            () -> new ClienteNotFoundException()
        );
    }
}
