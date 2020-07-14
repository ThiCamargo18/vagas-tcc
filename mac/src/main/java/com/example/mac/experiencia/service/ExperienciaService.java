package com.example.mac.experiencia.service;

import com.example.mac.cliente.service.ClienteService;
import com.example.mac.experiencia.mapper.ExperienciaMapper;
import com.example.mac.experiencia.model.ExperienciaEntity;
import com.example.mac.experiencia.model.ExperienciaEntrada;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.experiencia.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    ClienteService clienteService;
    public ExperienciaSaida criar(ExperienciaEntrada experiencia,Long id) {
        ExperienciaEntity experienciaEntity = ExperienciaMapper.INSTANCE.mapToEntity(experiencia);

        experienciaEntity.setIdUsuario(id);
        experienciaRepository.save(experienciaEntity);

        return ExperienciaMapper.INSTANCE.mapToSaida(experienciaEntity);
    }

    public ExperienciaEntity buscarPorIdCliente(long id) {
        return experienciaRepository.findByIdUsuario(id);
    }

    public ExperienciaSaida atualizar(Long id, ExperienciaEntrada experienciaEntrada) throws Exception {
        ExperienciaEntity entity = experienciaRepository.findByIdUsuario(id);
        ExperienciaEntity experienciaEntity = ExperienciaMapper.INSTANCE.mapToEntity(experienciaEntrada);
        experienciaEntity.setId(entity.getId());
        experienciaEntity.setIdUsuario(id);
        experienciaRepository.save(experienciaEntity);

        return ExperienciaMapper.INSTANCE.mapToSaida(experienciaEntity);
    }
}
