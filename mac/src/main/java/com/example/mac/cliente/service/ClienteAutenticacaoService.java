package com.example.mac.cliente.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClienteAutenticacaoService extends UserDetailsService {
    ClienteEntity findByEmail(String email);

    ClienteEntity save(ClienteEntrada clienteEntrada);
}
