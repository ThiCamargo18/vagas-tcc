package com.example.mac.habilidades.service;

import com.example.mac.cliente.service.ClienteService;
import com.example.mac.habilidades.mapper.HabilidadesMapper;
import com.example.mac.habilidades.model.HabilidadesEntity;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.habilidades.repository.HabilidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilidadesService {
    @Autowired
    HabilidadesRepository habilidadesRepository;
    @Autowired
    ClienteService clienteService;
    public HabilidadesSaida criar(HabilidadesEntrada habilidades,Long id) {
        HabilidadesEntity habilidadesEntity = HabilidadesMapper.INSTANCE.mapToEntity(habilidades);
        habilidadesEntity.setIdUsuario(id);
        habilidadesRepository.save(habilidadesEntity);

        return HabilidadesMapper.INSTANCE.mapToSaida(habilidadesEntity);
    }

    public HabilidadesEntity buscarPorIdCLiente(long id) {
        return habilidadesRepository.findByIdUsuario(id);
    }

    public HabilidadesSaida atualizar(Long id, HabilidadesEntrada habilidadesEntrada) throws Exception {
        HabilidadesEntity habilidadesEntityBanco = habilidadesRepository.findByIdUsuario(id);
        HabilidadesEntity habilidadesEntity = HabilidadesMapper.INSTANCE.mapToEntity(habilidadesEntrada);

        habilidadesEntity.setId(habilidadesEntityBanco.getId());
        habilidadesEntity.setIdUsuario(id);
        habilidadesRepository.save(habilidadesEntity);

        return HabilidadesMapper.INSTANCE.mapToSaida(habilidadesEntity);
    }
}
