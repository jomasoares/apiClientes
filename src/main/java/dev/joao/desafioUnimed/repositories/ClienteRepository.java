package dev.joao.desafioUnimed.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joao.desafioUnimed.entities.Cliente;
import dev.joao.desafioUnimed.enums.RegimeTributario;

/**
 * Repositório para a entidade Cliente. Terceira camada da aplicação.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Optional<Cliente> findByCnpj(String cnpj);

    List<Cliente> findByRegimeTributario(RegimeTributario regimeTributario);
}
