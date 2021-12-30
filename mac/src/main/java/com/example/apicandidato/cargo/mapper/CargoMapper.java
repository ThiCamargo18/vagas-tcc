package com.example.apicandidato.cargo.mapper;

import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.model.CargoSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CargoMapper {
    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    List<CargoSaida> mapToSaida(List<CargoEntity> cargoEntities);
}
