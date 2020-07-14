package com.example.mac.registroNacional.service;

import com.example.mac.cliente.service.ClienteService;
import com.example.mac.registroNacional.mapper.RegistroNacionalMapper;
import com.example.mac.registroNacional.model.RegistroEntity;
import com.example.mac.registroNacional.model.RegistroEntrada;
import com.example.mac.registroNacional.model.RegistroSaida;
import com.example.mac.registroNacional.repository.RegistroNacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RegistroNacionalService {
    @Autowired
    RegistroNacionalRepository registroNacionalRepository;
    @Autowired
    ClienteService clienteService;

    public RegistroSaida criar(RegistroEntrada registroEntrada,Long id){
        RegistroEntity registroEntity = RegistroNacionalMapper.INSTANCE.mapToEntity(registroEntrada);

        registroEntity.setIdUsuario(id);
        registroNacionalRepository.save(registroEntity);

        return RegistroNacionalMapper.INSTANCE.mapToSaida(registroEntity);
    }

    public RegistroEntity buscarPorIdCliente(long id) {
        return registroNacionalRepository.findByIdUsuario(id);
    }

    public RegistroSaida atualizar(Long id, RegistroEntrada registroEntrada) throws Exception {
        RegistroEntity registroEntityBanco = registroNacionalRepository.findByIdUsuario(id);
        RegistroEntity registroEntity = RegistroNacionalMapper.INSTANCE.mapToEntity(registroEntrada);

        registroEntity.setId(registroEntityBanco.getId());
        registroEntity.setIdUsuario(id);
        registroNacionalRepository.save(registroEntity);

        return RegistroNacionalMapper.INSTANCE.mapToSaida(registroEntity);
    }
}
