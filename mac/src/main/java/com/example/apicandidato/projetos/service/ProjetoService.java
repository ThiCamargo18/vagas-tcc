package com.example.apicandidato.projetos.service;

import com.example.apicandidato.projetos.mapper.ProjetoMapper;
import com.example.apicandidato.projetos.model.ProjetoEntity;
import com.example.apicandidato.projetos.model.ProjetoEntrada;
import com.example.apicandidato.projetos.model.ProjetoSaida;
import com.example.apicandidato.projetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    ProjetoRepository projetoRepository;

    public ProjetoSaida atualizar(ProjetoEntrada projetoEntrada, Long idUsuario) throws Exception {
        ProjetoEntity projetoEntity = ProjetoMapper.INSTANCE.mapToEntity(projetoEntrada, idUsuario);

        if (projetoEntrada.getId() != null) {
            Optional<ProjetoEntity> projetoSalvo = projetoRepository.findById(projetoEntrada.getId());

            if (projetoSalvo.isPresent() && projetoSalvo.get().getIdCandidato().equals(idUsuario))
                projetoEntity.setId(projetoSalvo.get().getId());
            else
                throw new Exception("Violação, esse ID de projeto não pertence a esse usuario");
        }

        projetoRepository.save(projetoEntity);

        return ProjetoMapper.INSTANCE.mapToSaida(projetoEntity);
    }

    public List<ProjetoEntity> buscarPorIdCLiente(long id) {
        return projetoRepository.findAllByIdCandidato(id);
    }
}
