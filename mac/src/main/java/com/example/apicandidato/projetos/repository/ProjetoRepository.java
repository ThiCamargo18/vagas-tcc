package com.example.apicandidato.projetos.repository;

import com.example.apicandidato.projetos.model.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<ProjetoEntity,Long> {
    List<ProjetoEntity> findAllByIdUsuario(long id);
}
