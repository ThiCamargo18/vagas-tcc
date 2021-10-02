package com.example.apicandidato.vaga.mapper;

import com.example.apicandidato.vaga.model.VagaEntity;
import com.example.apicandidato.vaga.model.VagaEntrada;
import com.example.apicandidato.vaga.model.VagaSaida;
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
