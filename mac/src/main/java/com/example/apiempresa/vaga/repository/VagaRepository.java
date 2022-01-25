package com.example.apiempresa.vaga.repository;

import com.example.apiempresa.vaga.model.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<VagaEntity,Long> {
    public  List<VagaEntity> findByTituloContainingIgnoreCaseAndIdEmpresa(String nome, Long idEmpresa);

    List<VagaEntity> findAllByStatus(String status);

    List<VagaEntity> findAllByIdEmpresaAndStatusNot(Long idEmpresa, String status);
}
