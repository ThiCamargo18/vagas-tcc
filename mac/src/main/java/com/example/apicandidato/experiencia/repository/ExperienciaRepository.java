package com.example.apicandidato.experiencia.repository;

import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienciaRepository extends JpaRepository<ExperienciaEntity,Long> {
    List<ExperienciaEntity> findAllByIdUsuario(long id);
}
