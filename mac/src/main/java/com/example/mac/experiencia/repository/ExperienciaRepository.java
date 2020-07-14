package com.example.mac.experiencia.repository;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.experiencia.model.ExperienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienciaRepository extends JpaRepository<ExperienciaEntity,Long> {
    public ExperienciaEntity findByIdUsuario(long id);
}
