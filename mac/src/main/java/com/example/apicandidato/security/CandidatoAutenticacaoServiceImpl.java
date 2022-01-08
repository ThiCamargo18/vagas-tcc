package com.example.apicandidato.security;

import com.example.apicandidato.candidato.mapper.CandidatoMapper;
import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoEntrada;
import com.example.apicandidato.candidato.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CandidatoAutenticacaoServiceImpl implements CandidatoAutenticacaoService {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CandidatoEntity findByEmail(String id) {
        return candidatoRepository.findByEmail(id);
    }

    @Override
    public CandidatoEntity save(CandidatoEntrada candidatoEntrada) {
        CandidatoEntity candidatoEntity = CandidatoMapper.INSTANCE.mapToEntity(candidatoEntrada);
        candidatoEntity.setSenha(passwordEncoder.encode(candidatoEntrada.getSenha()));
        candidatoEntity.setNivelCadastroRealizado(0);
        candidatoEntity.setSituacao("CONCORRENDO");
        candidatoEntity.setRoles(new HashSet<>(candidatoEntrada.getRoles()));

        return candidatoRepository.save(candidatoEntity);
    }
}
