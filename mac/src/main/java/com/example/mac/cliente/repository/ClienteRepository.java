package com.example.mac.cliente.repository;

import com.example.mac.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    public ClienteEntity findByCpf(String cpf);
}
