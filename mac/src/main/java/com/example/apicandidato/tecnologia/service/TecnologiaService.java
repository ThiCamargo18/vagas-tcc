package com.example.apicandidato.tecnologia.service;

import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import com.example.apicandidato.tecnologia.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnologiaService {
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    public void criar(List<TecnologiaEntity> tecnologiaEntities, Long idCandidato) {
        for (TecnologiaEntity tecnologia: tecnologiaEntities) {
            if (tecnologia.getId() != null) {
                tecnologia.setIdCandidato(idCandidato);

                tecnologiaRepository.save(tecnologia);
            }
        }
    }
}
