package com.example.apiempresa.entrevista.mapper;

import com.example.apiempresa.entrevista.model.EntrevistaEntity;
import com.example.apiempresa.entrevista.model.EntrevistaEntrada;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntrevistaMapper {
    EntrevistaMapper INSTANCE = Mappers.getMapper(EntrevistaMapper.class);

    EntrevistaEntity mapToEntity(EntrevistaEntrada entrevistaEntrada);

    EntrevistaSaida mapToSaida(EntrevistaEntity entrevistaEntity);

    List<EntrevistaSaida> mapToSaidaList(List<EntrevistaEntity> entrevistas);
}
