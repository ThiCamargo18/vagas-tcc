package com.example.apicandidato.tecnologia.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import com.example.apicandidato.tecnologia.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TecnologiaService {
    @Autowired
    private TecnologiaRepository tecnologiaRepository;
    @Autowired
    private CandidatoService candidatoService;

    public void atualizar(List<Long> tecnologiaIds, Long idCandidato) {
        List<TecnologiaEntity> allById = tecnologiaRepository.findAllById(tecnologiaIds);

        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(idCandidato);

        candidatoEntity.setTecnologias(allById);

        candidatoService.salvar(candidatoEntity);
    }

    public List<CandidatoEntity> buscarCandidatos(List<Long> tecnologiasLong) {
        List<TecnologiaEntity> tecnologias = tecnologiaRepository.findAllById(tecnologiasLong);

        if (tecnologias.isEmpty())
            return new ArrayList<>();

        List<CandidatoEntity> lista1 = tecnologias.get(0).getCandidato();

        for (TecnologiaEntity tecnologia: tecnologias) {
            List<CandidatoEntity> lista2 = tecnologia.getCandidato();

            lista1.removeIf(candidato -> !lista2.contains(candidato));
        }

        return lista1;
    }

    public List<TecnologiaEntity> findAllById(List<Long> tecnologiaIds){
        return tecnologiaRepository.findAllById(tecnologiaIds);
    }
}
