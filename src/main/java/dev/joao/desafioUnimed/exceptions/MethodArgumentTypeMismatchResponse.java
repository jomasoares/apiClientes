package dev.joao.desafioUnimed.exceptions;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.Data;

@Data
public class MethodArgumentTypeMismatchResponse {
    private String error;
    private int status = 400;

    public MethodArgumentTypeMismatchResponse(MethodArgumentTypeMismatchException e) {
        error = String.format("%s: formato inválido.", e.getParameter().getParameterName());
    }
}
