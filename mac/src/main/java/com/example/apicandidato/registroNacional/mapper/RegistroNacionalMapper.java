package com.example.apicandidato.registroNacional.mapper;

import com.example.apicandidato.registroNacional.model.RegistroEntity;
import com.example.apicandidato.registroNacional.model.RegistroEntrada;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegistroNacionalMapper {
    RegistroNacionalMapper INSTANCE = Mappers.getMapper(RegistroNacionalMapper.class);

    RegistroEntity mapToEntity(RegistroEntrada registroEntrada);

    RegistroSaida mapToSaida(RegistroEntity registroEntity);
}
