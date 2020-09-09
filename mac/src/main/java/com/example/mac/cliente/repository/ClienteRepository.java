package com.example.mac.cliente.repository;

import com.example.mac.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    public ClienteEntity findByEmail(String email);

    @Query("select s.email from cliente s where s.id = :idUsuario")
    Object getEmailByIdUser(@Param("idUsuario") Long idUsuario);
}
