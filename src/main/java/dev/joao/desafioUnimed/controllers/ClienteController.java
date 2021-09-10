package dev.joao.desafioUnimed.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import dev.joao.desafioUnimed.dto.ClienteDTO;
import dev.joao.desafioUnimed.enums.RegimeTributario;
import dev.joao.desafioUnimed.exceptions.ArgumentNotValidResponse;
import dev.joao.desafioUnimed.exceptions.ClienteNotFoundException;
import dev.joao.desafioUnimed.exceptions.CnpjNotUniqueException;
import dev.joao.desafioUnimed.exceptions.MethodArgumentTypeMismatchResponse;
import dev.joao.desafioUnimed.services.ClienteService;
import lombok.AllArgsConstructor;

/**
 * Controlador das requisições sobre clientes, primeira camada da aplicação
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {
    
    private final ClienteService clienteService;

    /**
     * Endpoint que lida com a criação de um cliente (empresa)
     * @param clienteDTO Obrigatório. DTO do cliente passado no corpo da requisição
     * @return o cliente cadastrado no banco
     * @throws CnpjNotUniqueException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO) throws CnpjNotUniqueException {
        return clienteService.createCliente(clienteDTO);
    }

    /**
     * Endppoint para listar os clientes. pode filtrar por regime tributário.
     * @param regimeTributario Optativo. filtro que indica qual o regime tributário desejado.
     * @return lista de clientes, todos ou somente os filtrados
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> listClients(@RequestParam(required = false) RegimeTributario regimeTributario) {
        if(regimeTributario != null){
            return clienteService.listByRegimeTributario(regimeTributario);
        }
        return clienteService.listAll();
    }

    /**
     * Endpoint que retorna as informações de um cliente dado um id passado como path variable.
     * @param id Obrigatório. Id do cliente desejado.
     * @return as informações do cliente.
     * @throws ClienteNotFoundException
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO findClienteById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findClienteById(id);
    }

    /**
     * Endpoint responsável por deletar um cliente cujo o id for passado como path variable.
     * @param id Obrigatório. Id do cliente que se deseja deletar.
     * @throws ClienteNotFoundException
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) throws ClienteNotFoundException{
        clienteService.deleteClienteById(id);
    }

    /**
     * Endpoint para atualizar os dados de um cliente. Todos os dados devem ser enviados.
     * @param clienteDTO Obrigatório. Novos dados do cliente para atualizar.
     * @return os dados atualizados do cliente.
     * @throws ClienteNotFoundException
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO updateCliente(@RequestBody @Valid ClienteDTO clienteDTO) throws ClienteNotFoundException {
        return clienteService.updateCliente(clienteDTO);
    }

    /**
     * Endpoint que retorna a quantidade de clientes cadastrados.
     * @return um nomero inteiro com o total de clientes.
     */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int clienteCount() {
        return clienteService.clientesCount();
    }

    /**
     * Método responsável por guiar a execução da aplicação para uma resposta personalizada 
     * quando um erro do tipo MethodArgumentNotValidException é identificado.
     * @param e Obrigatório. Erro emitido.
     * @return Um erro personalizado para bad request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ArgumentNotValidResponse validationErrorHandler(MethodArgumentNotValidException e) {
        
        return new ArgumentNotValidResponse(e);
    }

    /**
     * Método responsável por guiar a execução da aplicação para uma resposta personalizada 
     * quando um erro do tipo MethodArgumentTypeMismatchException é identificado.
     * @param e Obrigatório. Erro emitido.
     * @return Um erro personalizado para bad request.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MethodArgumentTypeMismatchResponse typeMismatchErrorHandler(MethodArgumentTypeMismatchException e) {
        return new MethodArgumentTypeMismatchResponse(e);
    }
}
