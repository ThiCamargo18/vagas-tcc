package com.example.mac.dadosAdicionais.repository;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosAdicionaisRepository extends JpaRepository<DadosAdicionaisEntity,Long> {
    public DadosAdicionaisEntity findByIdUsuario(long id);
}
