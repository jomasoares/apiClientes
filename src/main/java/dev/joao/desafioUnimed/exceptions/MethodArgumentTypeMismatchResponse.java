package dev.joao.desafioUnimed.exceptions;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.Data;

/**
 * Classe que representa uma resposta personalizada para uma requisição Bad Request com o tipo inválido.
 */
@Data
public class MethodArgumentTypeMismatchResponse {
    private String error;
    private int status = 400;

    public MethodArgumentTypeMismatchResponse(MethodArgumentTypeMismatchException e) {
        error = String.format("%s: formato inválido.", e.getParameter().getParameterName());
    }
}
