package com.example.apicandidato.dadosPessoais.repository;

import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoaisEntity,Long> {
    public DadosPessoaisEntity findByIdUsuario(long id);

    public List<DadosPessoaisEntity> findByNomeCompletoContainingIgnoreCase(String nome);

    public DadosPessoaisEntity findEmailByIdUsuario(Long idUsuario);

    @Query("select s.idUsuario,s.nomeCompleto from dadosPessoais s where s.cpf = :cpf")
    String getCandidatoByCpf(@Param("cpf") String cpf);
}
