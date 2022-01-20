package com.example.apicandidato.experiencia.mapper;

import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExperienciaMapper {
    ExperienciaMapper INSTANCE = Mappers.getMapper(ExperienciaMapper.class);

    @Mapping(source = "idUsuario",target = "idCandidato")
    ExperienciaEntity mapToEntity(ExperienciaEntrada experienciaEntrada, Long idUsuario);

    ExperienciaSaida mapToSaida(ExperienciaEntity experienciaEntity);
}
