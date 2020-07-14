package com.example.mac.entrevista.mapper;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.entrevista.model.EntrevistaEntity;
import com.example.mac.entrevista.model.EntrevistaEntrada;
import com.example.mac.entrevista.model.EntrevistaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntrevistaMapper {
    EntrevistaMapper INSTANCE = Mappers.getMapper(EntrevistaMapper.class);

    EntrevistaEntity mapToEntity(EntrevistaEntrada entrevistaEntrada);

    EntrevistaSaida mapToSaida(EntrevistaEntity entrevistaEntity);

    List<EntrevistaSaida> mapToSaidaList(List<EntrevistaEntity> entrevistas);
}
