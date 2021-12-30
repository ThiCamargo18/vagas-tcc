package com.example.apicandidato.projetos.service;

import com.example.apicandidato.projetos.mapper.ProjetoMapper;
import com.example.apicandidato.projetos.model.ProjetoEntity;
import com.example.apicandidato.projetos.model.ProjetoEntrada;
import com.example.apicandidato.projetos.model.ProjetoSaida;
import com.example.apicandidato.projetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {
    @Autowired
    ProjetoRepository projetoRepository;

    public ProjetoSaida atualizar(ProjetoEntrada projetoEntrada, Long idUsuario) {
        ProjetoEntity habilidadeSalva = projetoRepository.findByIdUsuario(idUsuario);

        ProjetoEntity projetoEntity = ProjetoMapper.INSTANCE.mapToEntity(projetoEntrada, idUsuario);

        if (habilidadeSalva != null)
            projetoEntity.setId(habilidadeSalva.getId());

        projetoRepository.save(projetoEntity);

        return ProjetoMapper.INSTANCE.mapToSaida(projetoEntity);
    }

    public ProjetoEntity buscarPorIdCLiente(long id) {
        return projetoRepository.findByIdUsuario(id);
    }
}
