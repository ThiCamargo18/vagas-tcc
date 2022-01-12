package com.example.apicandidato.framework.service;

import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.framework.repository.FrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameworkService {
    @Autowired
    private FrameworkRepository frameworkRepository;

    public void criar(List<FrameworkEntity> frameworkEntities, Long idCandidato) {
        for (FrameworkEntity framework: frameworkEntities) {
            if (framework.getId() != null) {
                framework.setIdCandidato(idCandidato);

                frameworkRepository.save(framework);
            }
        }
    }
}
