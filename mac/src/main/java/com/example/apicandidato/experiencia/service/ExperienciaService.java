package com.example.apicandidato.experiencia.service;

import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.experiencia.mapper.ExperienciaMapper;
import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.experiencia.repository.ExperienciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    CandidatoService candidatoService;

    public ExperienciaSaida atualizar(ExperienciaEntrada experienciaEntrada, Long idUsuario) throws Exception {
        ExperienciaEntity experienciaEntity = ExperienciaMapper.INSTANCE.mapToEntity(experienciaEntrada, idUsuario);

        if (experienciaEntrada.getId() != null) {
            Optional<ExperienciaEntity> experienciaSalva = experienciaRepository.findById(experienciaEntrada.getId());

            if (experienciaSalva.isPresent() && experienciaSalva.get().getIdUsuario().equals(idUsuario))
                experienciaEntity.setId(experienciaSalva.get().getId());
            else
                throw new Exception("Violação, esse ID de experiencia não pertence a esse usuario");
        }

        experienciaRepository.save(experienciaEntity);

        return ExperienciaMapper.INSTANCE.mapToSaida(experienciaEntity);
    }

    public List<ExperienciaEntity> buscarPorIdCliente(long id) {
        return experienciaRepository.findAllByIdUsuario(id);
    }
}
