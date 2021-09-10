package dev.joao.desafioUnimed.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import dev.joao.desafioUnimed.dto.NotaFiscalDTO;
import dev.joao.desafioUnimed.entities.NotaFiscal;

@Mapper
@MapperConfig(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface NotaFiscalMapper {
    NotaFiscalMapper INSTANCE = Mappers.getMapper(NotaFiscalMapper.class);

    NotaFiscal toEntity(NotaFiscalDTO notaFiscalDTO);

    NotaFiscalDTO toDTO(NotaFiscal notaFiscal);
}
