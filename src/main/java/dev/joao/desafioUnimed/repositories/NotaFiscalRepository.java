package dev.joao.desafioUnimed.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joao.desafioUnimed.entities.Cliente;
import dev.joao.desafioUnimed.entities.NotaFiscal;

/**
 * Repositório para a entidade Nota Fiscal.  Terceira camada da aplicação.
 */
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>{
    
    List<NotaFiscal> findByCliente(Cliente cliente);
}
