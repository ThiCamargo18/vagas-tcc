package com.example.apicandidato.vaga.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apiempresa.vaga.mapper.VagaMapper;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaCandidatoService {
    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private CandidatoService candidatoService;

    public VagaSaida buscarVaga(Long id) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(id);

        if (vagaEntityOptional.isEmpty()) {
            throw new Exception("Vaga não encontrada!");
        }

        return VagaMapper.INSTANCE.mapToSaida(vagaEntityOptional.get());
    }

    public List<VagaSaida> buscarPorNome(String nome) throws Exception {
        List<VagaEntity> vagaEntities = vagaRepository.findByTituloContainingIgnoreCase(nome);

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public List<VagaEntity> filtrar(VagaEntity vagaEntity) {
        Example<VagaEntity> example = Example.of(vagaEntity,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return vagaRepository.findAll(example);
    }

    public List<VagaSaida> listarVagasAtivas() {
        List<VagaEntity> vagaEntityList = vagaRepository.findAllByStatus("ATIVA");

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntityList);
    }

    public VagaSaida validarInscricao(VagaSaida vagaSaida, Long idUsuario) throws Exception {
        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(idUsuario);
        VagaEntity vagaEntity = vagaRepository.findById(vagaSaida.getId()).get();

        List<CandidatoEntity> clientesCadastrados = vagaEntity.getClientes();

        for (CandidatoEntity cliente : clientesCadastrados) {
            if (cliente.getId() == candidatoEntity.getId()) {
                vagaSaida.setInscrito("SIM");
            }
        }

        return vagaSaida;
    }

    public void inscrever(Long idUsuario, Long idVaga) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if (vagaEntityOptional.isEmpty()) {
            throw new Exception("Vaga não encontrada");
        }

        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(idUsuario);
        VagaEntity vagaEntity = vagaEntityOptional.get();

        List<CandidatoEntity> clientesCadastrados = vagaEntity.getClientes();

        for (CandidatoEntity candidato : clientesCadastrados) {
            if (candidato.getId().equals(candidatoEntity.getId())) {
                throw new Exception("Você já se inscreveu nessa vaga!");
            }
        }

        clientesCadastrados.add(candidatoEntity);

        vagaEntity.setClientes(clientesCadastrados);
        vagaEntity.setNumeroInscritos(vagaEntity.getNumeroInscritos() + 1);

        vagaRepository.save(vagaEntity);

        VagaMapper.INSTANCE.mapToSaida(vagaEntity);
    }
}
