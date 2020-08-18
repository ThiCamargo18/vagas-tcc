package com.example.mac.habilidades.mapper;

import com.example.mac.habilidades.model.HabilidadesEntity;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.model.HabilidadesSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HabilidadesMapper {
    HabilidadesMapper INSTANCE = Mappers.getMapper(HabilidadesMapper.class);

    HabilidadesEntity mapToEntity(HabilidadesEntrada habilidadesEntrada);

    HabilidadesSaida mapToSaida(HabilidadesEntity habilidadesEntity);

    List<HabilidadesSaida> mapToSaidaList(List<HabilidadesEntity> habilidades);
}
