package com.example.apicandidato.experiencia.repository;

import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienciaRepository extends JpaRepository<ExperienciaEntity,Long> {
    public ExperienciaEntity findByIdUsuario(long id);
}
