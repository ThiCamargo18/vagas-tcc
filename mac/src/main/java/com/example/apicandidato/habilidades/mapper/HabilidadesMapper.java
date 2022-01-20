package com.example.apicandidato.habilidades.mapper;

import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import com.example.apicandidato.habilidades.model.HabilidadesEntrada;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HabilidadesMapper {
    HabilidadesMapper INSTANCE = Mappers.getMapper(HabilidadesMapper.class);

    @Mapping(source = "idUsuario",target = "idCandidato")
    HabilidadesEntity mapToEntity(HabilidadesEntrada habilidadesEntrada, Long idUsuario);

    HabilidadesSaida mapToSaida(HabilidadesEntity habilidadesEntity);

    List<HabilidadesSaida> mapToSaidaList(List<HabilidadesEntity> habilidades);
}
