package com.example.apicandidato.framework.service;

import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.framework.model.CandidatoFrameworkEntity;
import com.example.apicandidato.framework.model.CandidatoFrameworkEntityKey;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.framework.repository.CandidatoFrameworkRepository;
import com.example.apicandidato.framework.repository.FrameworkRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameworkService {
    @Autowired
    private FrameworkRepository frameworkRepository;
    @Autowired
    private CandidatoFrameworkRepository candidatoFrameworkRepository;
    @Autowired
    private CandidatoService candidatoService;

    public List<CandidatoFrameworkEntity> filtrar(List<Long> frameworksIds){
        return candidatoFrameworkRepository.findByFrameworkEntityIdIn(frameworksIds);
    }

    public void atualizar(List<FrameworkEntity> frameworkEntities, Long idCandidato) {
        for (FrameworkEntity framework: frameworkEntities) {
            if (framework.getId() != null) {
                CandidatoFrameworkEntityKey key = new CandidatoFrameworkEntityKey(idCandidato, framework.getId());

                CandidatoFrameworkEntity candidatoFrameworkEntity = new CandidatoFrameworkEntity(key);

                candidatoFrameworkEntity.setCandidatoEntity(candidatoService.buscarPorId(idCandidato));
                candidatoFrameworkEntity.setFrameworkEntity(frameworkRepository.findById(framework.getId()).get());

                candidatoFrameworkRepository.save(candidatoFrameworkEntity);
            }
        }
    }
}
