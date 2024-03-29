package com.example.apiempresa.vaga.mapper;

import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaEntrada;
import com.example.apiempresa.vaga.model.VagaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VagaMapper {
    VagaMapper INSTANCE = Mappers.getMapper(VagaMapper.class);

    VagaEntity mapToEntity(VagaEntrada vagaEntrada);

    VagaSaida mapToSaida(VagaEntity vagaEntity);

    List<VagaSaida> mapToSaidaList(List<VagaEntity> vagaEntities);
}
