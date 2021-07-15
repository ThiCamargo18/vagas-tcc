package com.example.mac.security.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;

public interface ClienteAutenticacaoService {
    ClienteEntity findByEmail(String email);

    ClienteEntity save(ClienteEntrada clienteEntrada);
}
