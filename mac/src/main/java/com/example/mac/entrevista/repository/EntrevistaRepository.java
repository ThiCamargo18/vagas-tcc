package com.example.mac.entrevista.repository;

import com.example.mac.entrevista.model.EntrevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrevistaRepository extends JpaRepository<EntrevistaEntity,Long> {
}
