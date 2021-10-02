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
    EmpresaRepository empresaRepository;

    public EmpresaSaida criar(EmpresaEntrada empresaEntrada) throws Exception {
        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);

        if(verificarSeJaFoiCriadoAEmpresa().equals(true)){
            throw new Exception("Só é possivel cadastrar 1 empresa no sistema");
        }

        empresaRepository.save(empresaEntity);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity);
    }

    public Boolean verificarSeJaFoiCriadoAEmpresa() {
        Optional<EmpresaEntity> empresaEntity = empresaRepository.findById("1");

        if(empresaEntity.isPresent()){
            return true;
        }

        return false;
    }

    public EmpresaSaida listar() {
        List<EmpresaEntity> empresaEntity = empresaRepository.findAll();

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity.get(0));
    }

    public EmpresaSaida atualizar(EmpresaEntrada empresaEntrada) {
        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);

//        empresaEntity.setId("1");

        empresaRepository.save(empresaEntity);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity);
    }
}