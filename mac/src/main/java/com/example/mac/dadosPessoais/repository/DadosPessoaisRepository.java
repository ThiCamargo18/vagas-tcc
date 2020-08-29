package com.example.mac.dadosPessoais.repository;

import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoaisEntity,Long> {
    public DadosPessoaisEntity findByIdUsuario(long id);

    public List<DadosPessoaisEntity> findByNomeCompletoContainingIgnoreCase(String nome);

    @Query("select s.email from dadosPessoais s where s.idUsuario = :idUsuario")
    Object getEmailByIdUser(@Param("idUsuario") Long idUsuario);

    public DadosPessoaisEntity findEmailByIdUsuario(Long idUsuario);
}
