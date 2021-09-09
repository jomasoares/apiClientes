package dev.joao.desafioUnimed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente não encontrado.");
    }
}
