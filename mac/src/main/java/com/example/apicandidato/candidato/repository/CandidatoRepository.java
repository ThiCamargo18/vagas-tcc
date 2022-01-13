package com.example.apicandidato.candidato.repository;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {
    public CandidatoEntity findByEmail(String email);

    @Query("select s.email from candidato s where s.id = :idUsuario")
    Object getEmailByIdUser(@Param("idUsuario") Long idUsuario);
}
