package com.example.apicandidato.dadosAdicionais.mapper;

import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DadosAdicionaisMapper {
    DadosAdicionaisMapper INSTANCE = Mappers.getMapper(DadosAdicionaisMapper.class);

    DadosAdicionaisEntity mapToEntity(DadosAdicionaisEntrada dadosAdicionaisEntrada);

    DadosAdicionaisSaida mapToSaida(DadosAdicionaisEntity dadosAdicionaisEntity);
}
