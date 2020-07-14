package com.example.mac.registroNacional.repository;

import com.example.mac.registroNacional.model.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroNacionalRepository extends JpaRepository<RegistroEntity,Long> {
    public RegistroEntity findByIdUsuario(long id);
}
