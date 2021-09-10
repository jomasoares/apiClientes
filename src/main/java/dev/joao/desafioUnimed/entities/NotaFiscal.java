package dev.joao.desafioUnimed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa as notas fiscais. Contém as regras de persistência com o JPA.
 * As regras de validação de dados estão na classe NotaFiscalDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    private Cliente cliente;
}
