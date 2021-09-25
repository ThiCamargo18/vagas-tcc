package com.example.mac.security.service;

import com.example.mac.candidato.model.CandidatoEntity;
import com.example.mac.candidato.model.CandidatoEntrada;

public interface ClienteAutenticacaoService {
    CandidatoEntity findByEmail(String email);

    CandidatoEntity save(CandidatoEntrada candidatoEntrada);
}
