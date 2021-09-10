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

/**
 * Controlador das requisições sobre notas fiscais, primeira camada da aplicação
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/notasFiscais")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    /**
     * Endpoint para o cadastro de notas fiscais. 
     * @param notaFiscalDTO Obrigatório. A nota fiscal a ser cadastrada.
     * @return os dados completos da nota fiscal após o cadastro.
     * @throws ClienteNotFoundException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscalDTO createNotaFiscal(@RequestBody @Valid NotaFiscalDTO notaFiscalDTO) throws ClienteNotFoundException {
        return notaFiscalService.createNotaFiscal(notaFiscalDTO);
    }

    /**
     * Método para listar as notas fiscais. Aceita um filtro que retorna somente as
     * notas de um cliente específico dado seu id.
     * @param clienteId Optativo. Id do cliente que se deseja trazer as notas fiscais.
     * @return
     * @throws ClienteNotFoundException
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotaFiscalDTO> list(@RequestParam(required = false) Long clienteId) throws ClienteNotFoundException {
        List<NotaFiscalDTO> aaa = notaFiscalService.list(clienteId);
        return aaa;
    }

    /**
     * Método para deletar uma nota fiscal usando um id passado como parâmetro.
     * @param id Obrigatório. O id da nota fiscal que se deseja deletar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotaFiscal(@PathVariable Long id) {
        notaFiscalService.deleteNotaFiscalById(id);
    }
}
