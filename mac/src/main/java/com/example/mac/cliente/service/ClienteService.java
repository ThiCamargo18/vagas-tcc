package com.example.mac.cliente.service;

import com.example.mac.cliente.mapper.ClienteMapper;
import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.repository.ClienteRepository;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.enums.SituacaoConcorrente;
import com.example.mac.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public ClienteSaida criar(ClienteEntrada cliente) throws Exception {
        ClienteEntity clienteEntityOptional = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteEntityOptional != null){
            throw new Exception("Já existe um candidato com esse E-mail cadastrado!");
        }
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(cliente);
        clienteEntity.setPrimeiroAcesso(true);
        clienteEntity.setSituacao("CONCORRENDO");

        try{
            clienteRepository.save(clienteEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }


        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteEntity buscarEVerificarExistenciaClientePorCpf(String email) {
        return clienteRepository.findByEmail(email);
    }

    public ClienteEntity buscarEVerificarExistenciaClientePorIdVaga(Long idUsuario) throws MyException {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(idUsuario);

        if(!clienteEntity.isPresent()){
            throw new MyException("Candidato não encontrado");
        }

        return clienteEntity.get();
    }

    public ClienteSaida atualizar(ClienteEntrada cliente, Long id) throws Exception {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(cliente);

        clienteEntity.setId(id);
        clienteEntity.setSenha(clienteEntityOptional.get().getSenha());
        try{
            clienteRepository.save(clienteEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteSaida atualizarSenha(ClienteEntrada clienteEntrada, Long id) throws Exception {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);

        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntityAtualizar(clienteEntrada,clienteEntityOptional.get());

        try{
            clienteRepository.save(clienteEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteSaida atualizarPrimeiroAcesso(Long id) throws Exception {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);

        ClienteEntity clienteEntity = clienteEntityOptional.get();

        clienteEntity.setPrimeiroAcesso(false);

        try{
            clienteRepository.save(clienteEntity);
        } catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public Object buscarEmailUsuarioPorId(Long id){
        return clienteRepository.getEmailByIdUser(id);
    }

}
