package com.example.apiempresa.empresa.service;

import com.example.apiempresa.empresa.mapper.EmpresaMapper;
import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.empresa.model.EmpresaEntrada;
import com.example.apiempresa.empresa.model.EmpresaSaida;
import com.example.apiempresa.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaSaida criar(EmpresaEntrada empresaEntrada) throws Exception {
        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);

        empresaRepository.save(empresaEntity);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity);
    }

    public EmpresaSaida listar() {
        List<EmpresaEntity> empresaEntity = empresaRepository.findAll();

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity.get(0));
    }

    public EmpresaSaida atualizar(EmpresaEntrada empresaEntrada) {
        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);

        empresaRepository.save(empresaEntity);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity);
    }
}
