package com.example.mac.dadosAdicionais.mapper;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DadosAdicionaisMapper {
    DadosAdicionaisMapper INSTANCE = Mappers.getMapper(DadosAdicionaisMapper.class);

    DadosAdicionaisEntity mapToEntity(DadosAdicionaisEntrada dadosAdicionaisEntrada);

    DadosAdicionaisSaida mapToSaida(DadosAdicionaisEntity dadosAdicionaisEntity);
}
