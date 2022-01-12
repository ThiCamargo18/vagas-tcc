package com.example.apicandidato.framework.repository;

import com.example.apicandidato.framework.model.FrameworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameworkRepository extends JpaRepository<FrameworkEntity, Long> {
}
