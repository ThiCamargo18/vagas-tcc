package com.example.apiempresa.empresa.repository;

import com.example.apiempresa.empresa.model.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,String> {

}
