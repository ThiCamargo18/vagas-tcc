package com.example.apicandidato.experiencia.service;

import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.experiencia.mapper.ExperienciaMapper;
import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.experiencia.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    CandidatoService candidatoService;
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
        experienciaEntity.setIdUsuario(entity.getIdUsuario());
        experienciaRepository.save(experienciaEntity);

        return ExperienciaMapper.INSTANCE.mapToSaida(experienciaEntity);
    }
}
