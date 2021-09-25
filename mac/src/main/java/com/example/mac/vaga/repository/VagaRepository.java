package com.example.mac.vaga.repository;

import com.example.mac.vaga.model.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<VagaEntity,Long> {
    public  List<VagaEntity> findByTituloContainingIgnoreCase(String nome);

    List<VagaEntity> findAllByStatus(String status);
}
