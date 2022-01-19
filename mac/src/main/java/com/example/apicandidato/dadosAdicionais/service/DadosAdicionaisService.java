package com.example.apicandidato.dadosAdicionais.service;

import com.example.apicandidato.dadosAdicionais.mapper.DadosAdicionaisMapper;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosAdicionais.repository.DadosAdicionaisRepository;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosAdicionaisService {
    @Autowired
    DadosAdicionaisRepository dadosAdicionaisRepository;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public DadosAdicionaisSaida criar(DadosAdicionaisEntrada dadosAdicionais,Long id) {
        DadosAdicionaisEntity dadosAdicionaisEntity = DadosAdicionaisMapper.INSTANCE.mapToEntity(dadosAdicionais);
        dadosAdicionaisEntity.setIdCandidato(id);
        dadosAdicionaisRepository.save(dadosAdicionaisEntity);

        return DadosAdicionaisMapper.INSTANCE.mapToSaida(dadosAdicionaisEntity);
    }

    public DadosAdicionaisEntity buscarPorIdCliente(long id) {
        return dadosAdicionaisRepository.findByIdUsuario(id);
    }


    public DadosAdicionaisSaida atualizar(DadosAdicionaisEntrada dadosAdicionaisEntrada, Long id) throws Exception {
        dadosPessoaisService.atualizarCidade(null,id,dadosAdicionaisEntrada.getCidade());
        DadosAdicionaisEntity entity = dadosAdicionaisRepository.findByIdUsuario(id);

        DadosAdicionaisEntity dadosAdicionaisEntity = DadosAdicionaisMapper.INSTANCE.mapToEntity(dadosAdicionaisEntrada);
        dadosAdicionaisEntity.setId(entity.getId());
        dadosAdicionaisEntity.setIdCandidato(entity.getIdCandidato());

        dadosAdicionaisRepository.save(dadosAdicionaisEntity);

        return DadosAdicionaisMapper.INSTANCE.mapToSaida(dadosAdicionaisEntity);
    }
}
