package com.example.apicandidato.dadosPessoais.mapper;

import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DadosPessoaisMapper {
    DadosPessoaisMapper INSTANCE = Mappers.getMapper(DadosPessoaisMapper.class);

    DadosPessoaisEntity mapToEntity(DadosPessoaisEntrada dadosPessoaisEntrada);

    DadosPessoaisSaida mapToSaida(DadosPessoaisEntity dadosPessoaisEntity);

    List<DadosPessoaisSaida> mapToSaidaList(List<DadosPessoaisEntity> dadosPessoaisEntityList);
}
