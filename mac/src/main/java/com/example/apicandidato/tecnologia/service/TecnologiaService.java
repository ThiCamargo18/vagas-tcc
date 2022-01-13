package com.example.apicandidato.tecnologia.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import com.example.apicandidato.tecnologia.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<TecnologiaEntity> findAllById(List<Long> entrada) {
        return tecnologiaRepository.findAllById(entrada);
    }

    public Set<CandidatoEntity> buscarCandidatos(List<TecnologiaEntity> tecnologias) {
        Set<CandidatoEntity> retorno = new HashSet<>();

        for (TecnologiaEntity tecnologia: tecnologias) {
            HashSet<CandidatoEntity> candidatos = new HashSet<>(tecnologia.getCandidato()); //Remove duplicados

            retorno.addAll(candidatos);
        }

        return retorno;
    }
}
