package com.example.apicandidato.registroNacional.service;

import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.registroNacional.mapper.RegistroNacionalMapper;
import com.example.apicandidato.registroNacional.model.RegistroEntity;
import com.example.apicandidato.registroNacional.model.RegistroEntrada;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.registroNacional.repository.RegistroNacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegistroNacionalService {
    @Autowired
    RegistroNacionalRepository registroNacionalRepository;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public RegistroSaida criar(RegistroEntrada registroEntrada,Long id) throws Exception {
        RegistroEntity registroEntity = RegistroNacionalMapper.INSTANCE.mapToEntity(registroEntrada);

        registroEntity.setIdCandidato(id);
        try {
            registroNacionalRepository.save(registroEntity);
        }catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo número de RG cadastrado");
        }

        return RegistroNacionalMapper.INSTANCE.mapToSaida(registroEntity);
    }

    public RegistroEntity buscarPorIdCliente(long id) {
        return registroNacionalRepository.findByIdUsuario(id);
    }

    public RegistroSaida atualizar(Long id, RegistroEntrada registroEntrada) throws Exception {
        RegistroEntity registroEntityBanco = registroNacionalRepository.findByIdUsuario(id);
        RegistroEntity registroEntity = RegistroNacionalMapper.INSTANCE.mapToEntity(registroEntrada);

        registroEntity.setId(registroEntityBanco.getId());
        registroEntity.setIdCandidato(registroEntityBanco.getIdCandidato());
        try {
            registroNacionalRepository.save(registroEntity);
        }catch (DataIntegrityViolationException e){
            String mensagem = dadosPessoaisService.pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return RegistroNacionalMapper.INSTANCE.mapToSaida(registroEntity);
    }
}
