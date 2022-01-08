package com.example.apicandidato.cargo.repository;

import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.model.CargoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CargoRepository extends JpaRepository<CargoEntity, Long> {

    @Query(nativeQuery = true, value = "select id, nome from cargo")
    List<CargoId> findNomeCargos();
}
