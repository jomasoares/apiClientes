package dev.joao.desafioUnimed.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.Data;

/**
 * Classe que representa uma resposta personalizada para uma requisição Bad Request com argumento inválido.
 */
@Data
public class ArgumentNotValidResponse {
    private List<String> errors;
    private int status = 400;

    public ArgumentNotValidResponse(MethodArgumentNotValidException e){
       errors = e.getFieldErrors().stream()
                    .map((fieldError) -> formatMsg(fieldError))
                    .collect(Collectors.toList());

    }

    private String formatMsg(FieldError fieldError) {
        return String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
