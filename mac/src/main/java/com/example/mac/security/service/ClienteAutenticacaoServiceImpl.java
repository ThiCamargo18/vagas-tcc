package com.example.mac.security.service;

import com.example.mac.candidato.mapper.CandidatoMapper;
import com.example.mac.candidato.model.CandidatoEntity;
import com.example.mac.candidato.model.CandidatoEntrada;
import com.example.mac.candidato.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ClienteAutenticacaoServiceImpl implements ClienteAutenticacaoService {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CandidatoEntity findByEmail(String email) {
        return candidatoRepository.findByEmail(email);
    }

    @Override
    public CandidatoEntity save(CandidatoEntrada candidatoEntrada) {
        CandidatoEntity candidatoEntity = CandidatoMapper.INSTANCE.mapToEntity(candidatoEntrada);
        candidatoEntity.setSenha(passwordEncoder.encode(candidatoEntrada.getSenha()));
        candidatoEntity.setPrimeiroAcesso(true);
        candidatoEntity.setSituacao("CONCORRENDO");
        candidatoEntity.setRoles(new HashSet<>(candidatoEntrada.getRoles()));

        return candidatoRepository.save(candidatoEntity);
    }
}
