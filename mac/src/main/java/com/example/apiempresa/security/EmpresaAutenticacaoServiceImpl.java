package com.example.apiempresa.security;

import com.example.apiempresa.empresa.mapper.EmpresaMapper;
import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.empresa.model.EmpresaEntrada;
import com.example.apiempresa.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaAutenticacaoServiceImpl implements EmpresaAutenticacaoService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public EmpresaEntity findByUsername(String id) {
        Optional<EmpresaEntity> empresaEntity = empresaRepository.findById(id);

        if (empresaEntity.isEmpty())
            return null;
        else
            return empresaEntity.get();
    }

    @Override
    public EmpresaEntity save(EmpresaEntrada empresaEntrada) {
        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);
        empresaEntity.setSenha(passwordEncoder.encode(empresaEntrada.getSenha()));

        EmpresaEntity entity = empresaRepository.save(empresaEntity);
        return entity;
    }
}
