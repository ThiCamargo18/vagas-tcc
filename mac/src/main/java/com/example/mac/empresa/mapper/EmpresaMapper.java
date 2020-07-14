package com.example.mac.empresa.mapper;

import com.example.mac.empresa.model.EmpresaEntity;
import com.example.mac.empresa.model.EmpresaEntrada;
import com.example.mac.empresa.model.EmpresaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    EmpresaEntity mapToEntity(EmpresaEntrada empresaEntrada);

    EmpresaSaida mapToSaida(EmpresaEntity empresaEntity);

    List<EmpresaSaida> mapToEntityList(List<EmpresaEntity> empresaEntity);
}
