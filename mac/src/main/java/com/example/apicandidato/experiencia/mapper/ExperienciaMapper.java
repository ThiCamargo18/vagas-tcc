package com.example.apicandidato.experiencia.mapper;

import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExperienciaMapper {
    ExperienciaMapper INSTANCE = Mappers.getMapper(ExperienciaMapper.class);

    ExperienciaEntity mapToEntity(ExperienciaEntrada experienciaEntrada);

    ExperienciaSaida mapToSaida(ExperienciaEntity experienciaEntity);
}
