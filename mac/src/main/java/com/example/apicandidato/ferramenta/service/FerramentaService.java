package com.example.apicandidato.ferramenta.service;

import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.ferramenta.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FerramentaService {
    @Autowired
    private FerramentaRepository ferramentaRepository;

    public void crar(List<FerramentaEntity> ferramentaEntities, Long idCandidato) {
        for (FerramentaEntity ferramenta: ferramentaEntities) {
            if (ferramenta.getId() != null) {
                ferramenta.setIdCandidato(idCandidato);

                ferramentaRepository.save(ferramenta);
            }
        }
    }
}
