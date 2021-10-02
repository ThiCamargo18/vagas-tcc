package com.example.apicandidato.security;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoEntrada;

public interface CandidatoAutenticacaoService {
    CandidatoEntity findByEmail(String email);

    CandidatoEntity save(CandidatoEntrada candidatoEntrada);
}
