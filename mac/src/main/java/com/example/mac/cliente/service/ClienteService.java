package com.example.mac.cliente.service;

import com.example.mac.cliente.mapper.ClienteMapper;
import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.repository.ClienteRepository;
import com.example.mac.enums.SituacaoConcorrente;
import com.example.mac.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public ClienteSaida criar(ClienteEntrada cliente) throws Exception {
        ClienteEntity clienteEntityOptional = clienteRepository.findByCpf(cliente.getCpf());

        if(clienteEntityOptional != null){
            throw new Exception("Já existe um candidato com esse CPF cadastrado!");
        }
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(cliente);
        clienteEntity.setPrimeiroAcesso(true);
        clienteEntity.setSituacao("CONCORRENDO");

        clienteRepository.save(clienteEntity);

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteEntity buscarEVerificarExistenciaClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public ClienteEntity buscarEVerificarExistenciaClientePorIdVaga(Long idUsuario) throws MyException {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(idUsuario);

        if(!clienteEntity.isPresent()){
            throw new MyException("Candidato não encontrado");
        }

        return clienteEntity.get();
    }

    public ClienteSaida atualizar(ClienteEntrada cliente, Long id) {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(cliente);

        clienteEntity.setId(id);
        clienteEntity.setSenha(clienteEntityOptional.get().getSenha());
        clienteRepository.save(clienteEntity);

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteSaida atualizarSenha(ClienteEntrada clienteEntrada, Long id) {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);

        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntityAtualizar(clienteEntrada,clienteEntityOptional.get());

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public ClienteSaida atualizarPrimeiroAcesso(Long id){
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);

        ClienteEntity clienteEntity = clienteEntityOptional.get();

        clienteEntity.setPrimeiroAcesso(false);

        clienteRepository.save(clienteEntity);

        return ClienteMapper.INSTANCE.mapToSaida(clienteEntity);
    }

    public Boolean validarExistencia(Long id) throws Exception {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);

        if(!clienteEntity.isPresent()){
            throw new Exception("Candidato não encontrado!");
        }

        return true;
    }


}
