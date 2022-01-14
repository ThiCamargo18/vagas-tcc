package com.example.apicandidato.framework.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.framework.repository.FrameworkRepository;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FrameworkService {
    @Autowired
    private FrameworkRepository frameworkRepository;
    @Autowired
    private CandidatoService candidatoService;

    public void atualizar(List<Long> frameworks, Long idCandidato) {
        List<FrameworkEntity> allById = frameworkRepository.findAllById(frameworks);

        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(idCandidato);

        candidatoEntity.setFrameworks(allById);

        candidatoService.salvar(candidatoEntity);
    }

    public List<CandidatoEntity> buscarCandidatos(List<Long> frameworksLong) {
        List<FrameworkEntity> frameworks = frameworkRepository.findAllById(frameworksLong);

        if (frameworks.isEmpty())
            return new ArrayList<>();

        List<CandidatoEntity> lista1 = frameworks.get(0).getCandidato();

        for (FrameworkEntity framework: frameworks) {
            List<CandidatoEntity> lista2 = framework.getCandidato();

            lista1.removeIf(candidato -> !lista2.contains(candidato));
        }

        return lista1;
    }
}
