package com.example.apicandidato.tecnologia.repository;

import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<TecnologiaEntity, Long> {
}
