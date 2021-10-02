package com.example.apicandidato.entrevista.repository;

import com.example.apicandidato.entrevista.model.EntrevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrevistaRepository extends JpaRepository<EntrevistaEntity,Long> {
    EntrevistaEntity findByIdCliente(Long idCliente);
}
