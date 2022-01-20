package com.example.apiempresa.empresa.repository;

import com.example.apiempresa.empresa.model.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long> {

    EmpresaEntity findByLogin(String id);
}
