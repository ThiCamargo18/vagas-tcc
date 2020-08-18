package com.example.mac.entrevista.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.entrevista.mapper.EntrevistaMapper;
import com.example.mac.entrevista.model.EntrevistaEntity;
import com.example.mac.entrevista.model.EntrevistaEntrada;
import com.example.mac.entrevista.model.EntrevistaSaida;
import com.example.mac.entrevista.repository.EntrevistaRepository;
import com.example.mac.enums.Presenca;
import com.example.mac.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrevistaService {
    @Autowired
    EntrevistaRepository entrevistaRepository;
    @Autowired
    ClienteService clienteService;

    public EntrevistaSaida criar(EntrevistaEntrada entrevistaEntrada) throws MyException {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);
        entrevistaEntity.setPresenca(Presenca.AGENDADO);

        entrevistaRepository.save(entrevistaEntity);

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public List<EntrevistaSaida> listar() {
        List<EntrevistaEntity> entrevistas = entrevistaRepository.findAll();

        return EntrevistaMapper.INSTANCE.mapToSaidaList(entrevistas);
    }

    public String deletar(Long id) {
        entrevistaRepository.deleteById(id);

        return "concluido";
    }

    public EntrevistaSaida novaEntrevistaPelaNavbar(EntrevistaEntrada entrevistaEntrada) throws MyException {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);
        entrevistaEntity.setPresenca(Presenca.AGENDADO);

        entrevistaRepository.save(entrevistaEntity);

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public EntrevistaSaida atualizar(long id, EntrevistaEntrada entrevistaEntrada) throws MyException {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);

        entrevistaRepository.save(entrevistaEntity);

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public EntrevistaSaida buscar(long id) throws MyException {
        Optional<EntrevistaEntity> entrevistaEntity = entrevistaRepository.findById(id);

        if(!entrevistaEntity.isPresent()){
            throw new MyException("Entrevista não encontrada");
        }

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity.get());
    }
}
