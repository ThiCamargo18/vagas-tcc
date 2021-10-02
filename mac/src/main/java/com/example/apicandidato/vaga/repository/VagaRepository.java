package com.example.apicandidato.vaga.repository;

import com.example.apicandidato.vaga.model.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<VagaEntity,Long> {
    public  List<VagaEntity> findByTituloContainingIgnoreCase(String nome);

    List<VagaEntity> findAllByStatus(String status);
}
