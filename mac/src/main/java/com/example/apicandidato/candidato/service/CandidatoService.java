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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoService {
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public CandidatoSaida criar(CandidatoEntrada cliente) throws Exception {
        CandidatoEntity candidatoEntityOptional = candidatoRepository.findByEmail(cliente.getEmail());

        if(candidatoEntityOptional != null){
            throw new Exception("Já existe um candidato com esse E-mail cadastrado!");
        }
        CandidatoEntity candidatoEntity = CandidatoMapper.INSTANCE.mapToEntity(cliente);
        candidatoEntity.setPrimeiroAcesso(true);
        candidatoEntity.setSituacao("CONCORRENDO");

        try{
            candidatoRepository.save(candidatoEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }


        return CandidatoMapper.INSTANCE.mapToSaida(candidatoEntity);
    }

    public CandidatoEntity buscarEVerificarExistenciaClientePorEmail(String email) {
        return candidatoRepository.findByEmail(email);
    }

    public CandidatoEntity buscarEVerificarExistenciaClientePorIdVaga(Long idUsuario) throws MyException {
        Optional<CandidatoEntity> clienteEntity = candidatoRepository.findById(idUsuario);

        if(!clienteEntity.isPresent()){
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

    public CandidatoSaida atualizarPrimeiroAcesso(Long id) throws Exception {
        Optional<CandidatoEntity> clienteEntityOptional = candidatoRepository.findById(id);

        CandidatoEntity candidatoEntity = clienteEntityOptional.get();

        candidatoEntity.setPrimeiroAcesso(false);

        try{
            candidatoRepository.save(candidatoEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return CandidatoMapper.INSTANCE.mapToSaida(candidatoEntity);
    }

    public Object buscarEmailUsuarioPorId(Long id){
        return candidatoRepository.getEmailByIdUser(id);
    }

}
