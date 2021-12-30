package com.example.apicandidato.projetos.repository;

import com.example.apicandidato.projetos.model.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<ProjetoEntity,Long> {
    public ProjetoEntity findByIdUsuario(long id);
}
