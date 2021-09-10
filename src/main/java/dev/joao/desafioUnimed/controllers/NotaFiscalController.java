package dev.joao.desafioUnimed.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.joao.desafioUnimed.dto.NotaFiscalDTO;
import dev.joao.desafioUnimed.exceptions.ClienteNotFoundException;
import dev.joao.desafioUnimed.services.NotaFiscalService;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("api/v1/notasFiscais")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscalDTO createNotaFiscal(@RequestBody @Valid NotaFiscalDTO notaFiscalDTO) {
        return notaFiscalService.createNotaFiscal(notaFiscalDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotaFiscalDTO> list(@RequestParam(required = false) Long clienteId) throws ClienteNotFoundException {
        List<NotaFiscalDTO> aaa = notaFiscalService.list(clienteId);
        return aaa;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotaFiscal(@PathVariable Long id) {
        notaFiscalService.deleteNotaFiscalById(id);
    }

    /* 
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) throws ClienteNotFoundException{
        clienteService.deleteClienteById(id);
    } */

}
