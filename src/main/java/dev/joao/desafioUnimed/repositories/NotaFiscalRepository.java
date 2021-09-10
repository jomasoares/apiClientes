package dev.joao.desafioUnimed.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.joao.desafioUnimed.entities.Cliente;
import dev.joao.desafioUnimed.entities.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>{
    
    List<NotaFiscal> findByCliente(Cliente cliente);
}
