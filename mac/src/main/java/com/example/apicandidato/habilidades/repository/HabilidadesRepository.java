package com.example.apicandidato.habilidades.repository;

import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabilidadesRepository extends JpaRepository<HabilidadesEntity,Long> {
    public HabilidadesEntity findByIdCandidato(long id);
}
