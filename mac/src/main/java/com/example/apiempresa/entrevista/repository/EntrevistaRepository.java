package com.example.apiempresa.entrevista.repository;

import com.example.apiempresa.entrevista.model.EntrevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrevistaRepository extends JpaRepository<EntrevistaEntity,Long> {
    List<EntrevistaEntity> findAllByIdEmpresa(Long idEmpresa);
    List<EntrevistaEntity> findAllByIdCandidato(Long idCliente);
}
