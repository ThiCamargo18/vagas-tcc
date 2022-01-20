package com.example.apiempresa.entrevista.repository;

import com.example.apiempresa.entrevista.model.EntrevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrevistaRepository extends JpaRepository<EntrevistaEntity,Long> {
    EntrevistaEntity findByIdCandidato(Long idCliente);
}
