package dev.joao.desafioUnimed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção que deve ser lançada quando um cliente não for encontrado na camada de persistência.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente não encontrado.");
    }
}
