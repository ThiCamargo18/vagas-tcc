package com.example.mac.candidato.mapper;

import com.example.mac.candidato.model.CandidatoEntity;
import com.example.mac.candidato.model.CandidatoEntrada;
import com.example.mac.candidato.model.CandidatoSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatoMapper {
    CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);

    CandidatoEntity mapToEntity(CandidatoEntrada candidatoEntrada);

    CandidatoSaida mapToSaida(CandidatoEntity candidatoEntity);

    @Mappings({
            @Mapping(source = "candidatoEntrada.senha",target = "senha"),
            @Mapping(source = "candidatoEntity.email",target = "email"),
            @Mapping(source = "candidatoEntity.roles",target = "roles"),
            @Mapping(source = "candidatoEntity.nome",target = "nome"),
            @Mapping(source = "candidatoEntity.primeiroAcesso",target = "primeiroAcesso"),
            @Mapping(source = "candidatoEntity.situacao",target = "situacao"),
    })
    CandidatoEntity mapToEntityAtualizar(CandidatoEntrada candidatoEntrada, CandidatoEntity candidatoEntity);
}
