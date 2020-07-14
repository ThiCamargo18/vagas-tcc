package com.example.mac.dadosPessoais.repository;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoaisEntity,Long> {
    public DadosPessoaisEntity findByIdUsuario(long id);

    public List<DadosPessoaisEntity> findByNomeCompletoContainingIgnoreCase(String nome);
}
