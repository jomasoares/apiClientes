package dev.joao.desafioUnimed.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {
    
    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO) throws CnpjNotUniqueException {
        return clienteService.createCliente(clienteDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> listClients(@RequestParam(required = false) RegimeTributario regimeTributario) {
        if(regimeTributario != null){
            return clienteService.listByRegimeTributario(regimeTributario);
        }
        return clienteService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO findClienteById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findClienteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) throws ClienteNotFoundException{
        clienteService.deleteClienteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO updateCliente(@RequestBody @Valid ClienteDTO clienteDTO) throws ClienteNotFoundException {
        return clienteService.updateCliente(clienteDTO);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int clienteCount() {
        return clienteService.clientesCount();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ArgumentNotValidResponse validationErrorHandler(MethodArgumentNotValidException e) {
        
        return new ArgumentNotValidResponse(e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MethodArgumentTypeMismatchResponse typeMismatchErrorHandler(MethodArgumentTypeMismatchException e) {
        return new MethodArgumentTypeMismatchResponse(e);
    }
}
