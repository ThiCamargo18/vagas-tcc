package com.example.apicandidato.experiencia.service;

import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.experiencia.mapper.ExperienciaMapper;
import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.experiencia.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    CandidatoService candidatoService;

    public ExperienciaSaida atualizar(ExperienciaEntrada experienciaEntrada, Long id) {
        ExperienciaEntity experienciaEntity = ExperienciaMapper.INSTANCE.mapToEntity(experienciaEntrada, id);

        if (experienciaEntrada.getId() != null) {
            Optional<ExperienciaEntity> experienciaSalva = experienciaRepository.findById(experienciaEntrada.getId());
            experienciaSalva.ifPresent(entity -> experienciaEntity.setId(entity.getId()));
        }

        experienciaRepository.save(experienciaEntity);

        return ExperienciaMapper.INSTANCE.mapToSaida(experienciaEntity);
    }

    public List<ExperienciaEntity> buscarPorIdCliente(long id) {
        return experienciaRepository.findAllByIdUsuario(id);
    }
}
