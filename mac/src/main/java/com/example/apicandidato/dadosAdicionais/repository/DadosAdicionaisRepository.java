package com.example.apicandidato.dadosAdicionais.repository;

import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosAdicionaisRepository extends JpaRepository<DadosAdicionaisEntity,Long> {
    public DadosAdicionaisEntity findByIdCandidato(long id);
}
