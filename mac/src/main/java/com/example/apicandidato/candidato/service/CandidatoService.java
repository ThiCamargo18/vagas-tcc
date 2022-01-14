package com.example.apicandidato.candidato.service;

import com.example.apicandidato.candidato.mapper.CandidatoMapper;
import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoEntrada;
import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.candidato.repository.CandidatoRepository;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidatoService {
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public CandidatoEntity buscarPorId(Long idUsuario) throws MyException {
        Optional<CandidatoEntity> clienteEntity = candidatoRepository.findById(idUsuario);

        if(clienteEntity.isEmpty()){
            throw new MyException("Candidato não encontrado");
        }

        return clienteEntity.get();
    }

    public CandidatoSaida atualizar(CandidatoEntrada cliente, Long id) throws Exception {
        Optional<CandidatoEntity> clienteEntityOptional = candidatoRepository.findById(id);
        CandidatoEntity candidatoEntity = CandidatoMapper.INSTANCE.mapToEntity(cliente);

        candidatoEntity.setId(id);
        candidatoEntity.setSenha(clienteEntityOptional.get().getSenha());
        try{
            candidatoRepository.save(candidatoEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return CandidatoMapper.INSTANCE.mapToSaida(candidatoEntity);
    }

    public CandidatoSaida atualizarSenha(CandidatoEntrada candidatoEntrada, Long id) throws Exception {
        Optional<CandidatoEntity> clienteEntityOptional = candidatoRepository.findById(id);

        CandidatoEntity candidatoEntity = CandidatoMapper.INSTANCE.mapToEntityAtualizar(candidatoEntrada,clienteEntityOptional.get());

        try{
            candidatoRepository.save(candidatoEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return CandidatoMapper.INSTANCE.mapToSaida(candidatoEntity);
    }

    public CandidatoSaida atualizarCadastro(Long id, int nivelCadastro) {
        Optional<CandidatoEntity> clienteEntityOptional = candidatoRepository.findById(id);

        CandidatoEntity candidatoEntity = clienteEntityOptional.get();

        candidatoEntity.setNivelCadastroRealizado(nivelCadastro);

        candidatoRepository.save(candidatoEntity);

        return CandidatoMapper.INSTANCE.mapToSaida(candidatoEntity);
    }

    public Object buscarEmailUsuarioPorId(Long id){
        return candidatoRepository.getEmailByIdUser(id);
    }

    public Integer cadastroBasicoRealizado(long id) {
        Optional<CandidatoEntity> clienteEntityOptional = candidatoRepository.findById(id);

        CandidatoEntity candidatoEntity = clienteEntityOptional.get();

        return candidatoEntity.getNivelCadastroRealizado();
    }

    public List<CandidatoEntity> filtrar(List<CandidatoEntity> lista1, List<CandidatoEntity> lista2) {
        if (lista2.isEmpty())
            return lista1;

        lista1.removeIf(candidato -> !lista2.contains(candidato));

        return lista1;
    }

    public void salvar(CandidatoEntity candidatoEntity) {
        candidatoRepository.save(candidatoEntity);
    }
}
