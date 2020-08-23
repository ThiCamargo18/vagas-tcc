package com.example.mac.dadosAdicionais.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.repository.ClienteRepository;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.dadosAdicionais.mapper.DadosAdicionaisMapper;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosAdicionais.repository.DadosAdicionaisRepository;
import com.example.mac.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import org.hibernate.annotations.LazyToOne;
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
        dadosAdicionaisEntity.setIdUsuario(id);
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
        dadosAdicionaisEntity.setIdUsuario(entity.getIdUsuario());

        dadosAdicionaisRepository.save(dadosAdicionaisEntity);

        return DadosAdicionaisMapper.INSTANCE.mapToSaida(dadosAdicionaisEntity);
    }
}
