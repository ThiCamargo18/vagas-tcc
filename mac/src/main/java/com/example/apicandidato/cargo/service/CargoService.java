package com.example.apicandidato.cargo.service;

import com.example.apicandidato.cargo.mapper.CargoMapper;
import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.model.CargoSaida;
import com.example.apicandidato.cargo.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    public CargoEntity criar(CargoEntity cargoEntity, Long idUsuario) {
        cargoEntity.setIdUsuario(idUsuario);

        return cargoRepository.save(cargoEntity);
    }

    public CargoEntity buscar(Long idCargo) {
        return cargoRepository.findById(idCargo).get();
    }

    public List<CargoSaida> buscarPorIdCliente() {
        List<CargoEntity> cargoEntities = cargoRepository.findAll();

        return CargoMapper.INSTANCE.mapToSaida(cargoEntities);
    }
}
