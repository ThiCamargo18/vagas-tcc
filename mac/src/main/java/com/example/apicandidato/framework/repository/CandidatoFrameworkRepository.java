package com.example.apicandidato.framework.repository;

import com.example.apicandidato.framework.model.CandidatoFrameworkEntity;
import com.example.apicandidato.framework.model.CandidatoFrameworkEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoFrameworkRepository extends JpaRepository<CandidatoFrameworkEntity, CandidatoFrameworkEntityKey> {
    List<CandidatoFrameworkEntity> findByFrameworkEntityIdIn(List<Long> frameworksIds);
}
