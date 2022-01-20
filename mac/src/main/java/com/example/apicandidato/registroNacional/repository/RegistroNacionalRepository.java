package com.example.apicandidato.registroNacional.repository;

import com.example.apicandidato.registroNacional.model.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroNacionalRepository extends JpaRepository<RegistroEntity,Long> {
    public RegistroEntity findByIdCandidato(long id);
}
