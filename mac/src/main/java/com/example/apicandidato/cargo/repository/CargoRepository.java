package com.example.apicandidato.cargo.repository;

import com.example.apicandidato.cargo.model.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<CargoEntity, Long> {
}
