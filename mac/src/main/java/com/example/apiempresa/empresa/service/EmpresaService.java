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

    public EmpresaSaida atualizar(EmpresaEntrada empresaEntrada, Long idEmpresa) {
        Optional<EmpresaEntity> empresaEntityOptional = empresaRepository.findById(idEmpresa);

        EmpresaEntity empresaSalva = empresaEntityOptional.get();

        EmpresaEntity empresaEntity = EmpresaMapper.INSTANCE.mapToEntity(empresaEntrada);

        empresaEntity.setId(idEmpresa);
        empresaEntity.setLogin(empresaSalva.getLogin());
        empresaEntity.setSenha(empresaSalva.getSenha());
        empresaEntity.setRoles(empresaSalva.getRoles());

        empresaRepository.save(empresaEntity);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntity);
    }

    public EmpresaSaida buscarPorId(Long idEmpresa) {
        Optional<EmpresaEntity> empresaEntityOptional = empresaRepository.findById(idEmpresa);

        return EmpresaMapper.INSTANCE.mapToSaida(empresaEntityOptional.get());
    }
}
