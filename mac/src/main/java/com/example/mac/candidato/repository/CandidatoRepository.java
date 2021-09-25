package com.example.mac.candidato.repository;

import com.example.mac.candidato.model.CandidatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {
    public CandidatoEntity findByEmail(String email);

    @Query("select s.email from cliente s where s.id = :idUsuario")
    Object getEmailByIdUser(@Param("idUsuario") Long idUsuario);
}
