package com.example.apicandidato.ferramenta.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.ferramenta.repository.FerramentaRepository;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FerramentaService {
    @Autowired
    private FerramentaRepository ferramentaRepository;
    @Autowired
    private CandidatoService candidatoService;

    public void atualizar(List<Long> ferramentas, Long idCandidato) {
        List<FerramentaEntity> allById = ferramentaRepository.findAllById(ferramentas);

        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(idCandidato);

        candidatoEntity.setFerramentas(allById);

        candidatoService.salvar(candidatoEntity);
    }

    public List<CandidatoEntity> buscarCandidatos(List<Long> ferramentasLong) {
        List<FerramentaEntity> ferramentas = ferramentaRepository.findAllById(ferramentasLong);

        if (ferramentas.isEmpty())
            return new ArrayList<>();

        List<CandidatoEntity> lista1 = ferramentas.get(0).getCandidato();

        for (FerramentaEntity ferramenta: ferramentas) {
            List<CandidatoEntity> lista2 = ferramenta.getCandidato();

            lista1.removeIf(candidato -> !lista2.contains(candidato));
        }

        return lista1;
    }
}
