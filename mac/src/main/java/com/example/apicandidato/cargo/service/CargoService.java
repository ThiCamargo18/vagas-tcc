package com.example.apicandidato.cargo.service;

import com.example.apicandidato.cargo.mapper.CargoMapper;
import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.model.CargoSaida;
import com.example.apicandidato.cargo.model.CargoSaida2;
import com.example.apicandidato.cargo.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    public CargoEntity criar(CargoEntity cargoEntity, Long idUsuario) {

        return cargoRepository.save(cargoEntity);
    }

    public CargoEntity buscar(Long idCargo) {
        return cargoRepository.findById(idCargo).get();
    }

    public List<CargoSaida> buscar() {
        List<CargoEntity> cargoEntities = cargoRepository.findAll();

        return CargoMapper.INSTANCE.mapToSaida(cargoEntities);
    }

    public CargoSaida2 buscarEMapear(Long idCargo) {
        CargoSaida2 cargoSaida = new CargoSaida2();

        cargoSaida.setCargo(cargoRepository.findNomeCargos());

        CargoEntity cargoEntity;

        if (idCargo == null)
            cargoEntity = this.buscar(cargoSaida.getCargo().get(0).getId());
        else
            cargoEntity = this.buscar(idCargo);

        cargoSaida.setFerramenta(cargoEntity.getFerramentas());
        cargoSaida.setTecnologia(cargoEntity.getTecnologias());
        cargoSaida.setFramework(cargoEntity.getFrameworks());

        return cargoSaida;
    }
}
