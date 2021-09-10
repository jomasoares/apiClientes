package dev.joao.desafioUnimed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.joao.desafioUnimed.enums.RegimeTributario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa os clientes (empresas). Contém as regras de persistência com o JPA.
 * As regras de validação de dados estão na classe ClienteDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RegimeTributario regimeTributario;

    @Column(nullable = true)
    private String email;
    
}
