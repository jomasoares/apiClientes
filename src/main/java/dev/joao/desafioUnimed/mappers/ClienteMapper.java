package dev.joao.desafioUnimed.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.joao.desafioUnimed.dto.ClienteDTO;
import dev.joao.desafioUnimed.entities.Cliente;

/**
 * Mapper responsável por transformar uma instância da classe
 * Cliente para a classe ClienteDTO e vice-versa.
 */
@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO clienteDTO);

    ClienteDTO toDTO(Cliente cliente);
}
