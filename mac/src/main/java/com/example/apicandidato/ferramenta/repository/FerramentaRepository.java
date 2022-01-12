package com.example.apicandidato.ferramenta.repository;

import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerramentaRepository extends JpaRepository<FerramentaEntity, Long> {
}
