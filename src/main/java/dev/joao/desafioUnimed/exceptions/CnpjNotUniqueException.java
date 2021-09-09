package dev.joao.desafioUnimed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CnpjNotUniqueException extends Exception{
    public CnpjNotUniqueException(String cnpj) {
        super(String.format("Cliente com CNPJ %s já está cadastrado.", cnpj));
    }
    
}
