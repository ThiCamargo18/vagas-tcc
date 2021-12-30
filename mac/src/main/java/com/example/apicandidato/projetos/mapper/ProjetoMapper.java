package com.example.apicandidato.projetos.mapper;

import com.example.apicandidato.projetos.model.ProjetoEntity;
import com.example.apicandidato.projetos.model.ProjetoEntrada;
import com.example.apicandidato.projetos.model.ProjetoSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjetoMapper {
    ProjetoMapper INSTANCE = Mappers.getMapper(ProjetoMapper.class);

    @Mapping(source = "idUsuario",target = "idUsuario")
    ProjetoEntity mapToEntity(ProjetoEntrada projetoEntrada, Long idUsuario);

    ProjetoSaida mapToSaida(ProjetoEntity projetoEntity);

    List<ProjetoSaida> mapToSaidaList(List<ProjetoEntity> habilidades);
}
