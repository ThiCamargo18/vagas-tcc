package com.example.mac.habilidades.repository;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.habilidades.model.HabilidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabilidadesRepository extends JpaRepository<HabilidadesEntity,Long> {
    public HabilidadesEntity findByIdUsuario(long id);
}
