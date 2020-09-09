package com.example.mac.cliente.mapper;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteEntity mapToEntity(ClienteEntrada clienteEntrada);

    ClienteSaida mapToSaida(ClienteEntity clienteEntity);

    @Mappings({
            @Mapping(source = "clienteEntrada.senha",target = "senha"),
            @Mapping(source = "clienteEntity.email",target = "email"),
            @Mapping(source = "clienteEntity.login",target = "login"),
            @Mapping(source = "clienteEntity.nome",target = "nome"),
            @Mapping(source = "clienteEntity.primeiroAcesso",target = "primeiroAcesso"),
            @Mapping(source = "clienteEntity.situacao",target = "situacao"),
    })
    ClienteEntity mapToEntityAtualizar(ClienteEntrada clienteEntrada, ClienteEntity clienteEntity);
}
