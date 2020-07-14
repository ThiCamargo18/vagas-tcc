package com.example.mac.registroNacional.mapper;

import com.example.mac.registroNacional.model.RegistroEntity;
import com.example.mac.registroNacional.model.RegistroEntrada;
import com.example.mac.registroNacional.model.RegistroSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegistroNacionalMapper {
    RegistroNacionalMapper INSTANCE = Mappers.getMapper(RegistroNacionalMapper.class);

    RegistroEntity mapToEntity(RegistroEntrada registroEntrada);

    RegistroSaida mapToSaida(RegistroEntity registroEntity);
}
