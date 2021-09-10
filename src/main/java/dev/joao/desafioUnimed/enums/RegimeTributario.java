package dev.joao.desafioUnimed.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum que contém os tipos de regime tributário.
 */
@Getter
@AllArgsConstructor
public enum RegimeTributario {
    SIMPLES_NACIONAL {
        public String toString() {
            return "Simples Nacional";
        }
    },
    LUCRO_PRESUMIDO {
        public String toString() {
            return "Lucro Presumido";
        }
    }
}
