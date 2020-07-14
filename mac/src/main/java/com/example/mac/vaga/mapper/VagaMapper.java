package com.example.mac.vaga.mapper;

import com.example.mac.registroNacional.mapper.RegistroNacionalMapper;
import com.example.mac.registroNacional.model.RegistroEntity;
import com.example.mac.registroNacional.model.RegistroEntrada;
import com.example.mac.registroNacional.model.RegistroSaida;
import com.example.mac.vaga.model.VagaEntity;
import com.example.mac.vaga.model.VagaEntrada;
import com.example.mac.vaga.model.VagaSaida;
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
