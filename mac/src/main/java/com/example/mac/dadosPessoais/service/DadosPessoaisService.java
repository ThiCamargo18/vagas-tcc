package com.example.mac.dadosPessoais.service;

import com.example.mac.cliente.service.ClienteService;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.repository.DadosPessoaisRepository;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.service.HabilidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DadosPessoaisService {
    @Autowired
    DadosPessoaisRepository dadosPessoaisRepository;
    @Autowired
    HabilidadesService habilidadesService;


    public DadosPessoaisSaida criar(DadosPessoaisEntrada dadosPessoais,Long id,String cidade) {
        DadosPessoaisEntity dadosPessoaisEntity = DadosPessoaisMapper.INSTANCE.mapToEntity(dadosPessoais);

        dadosPessoaisEntity.setIdUsuario(id);
        dadosPessoaisEntity.setCidade(cidade);
        dadosPessoaisRepository.save(dadosPessoaisEntity);

        return DadosPessoaisMapper.INSTANCE.mapToSaida(dadosPessoaisEntity);
    }

    public DadosPessoaisEntity buscarPorIdCliente(long id) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity =  dadosPessoaisRepository.findByIdUsuario(id);

        if(dadosPessoaisEntity.equals(null)){
            throw new Exception("Candidato não encontrado!");
        }

        return dadosPessoaisEntity;
    }

    public DadosPessoaisSaida atualizar(DadosPessoaisEntrada dadosPessoaisEntrada, Long id) throws Exception {
        HabilidadesEntrada habilidadesEntrada = new HabilidadesEntrada();
        habilidadesEntrada.setNomeUsuario(dadosPessoaisEntrada.getNomeCompleto());
        habilidadesService.atualizar(id,habilidadesEntrada);
        DadosPessoaisEntity dadosRetornoEntity = dadosPessoaisRepository.findByIdUsuario(id);
        DadosPessoaisEntity dadosPessoaisEntity = DadosPessoaisMapper.INSTANCE.mapToEntity(dadosPessoaisEntrada);
        dadosPessoaisEntity.setId(dadosRetornoEntity.getId());
        dadosPessoaisEntity.setIdUsuario(dadosRetornoEntity.getIdUsuario());

        dadosPessoaisRepository.save(dadosPessoaisEntity);

        return DadosPessoaisMapper.INSTANCE.mapToSaida(dadosPessoaisEntity);
    }

    public List<DadosPessoaisSaida> filtrar(DadosPessoaisEntity entrada) {
        Example example = Example.of( entrada,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) );
        return dadosPessoaisRepository.findAll(example);
    }

    public List<DadosPessoaisEntity> buscarPorNome(String nome) {
        return dadosPessoaisRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }

    public void atualizarCidade(Object o, Long id, String cidade) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisRepository.findByIdUsuario(id);

        if(dadosPessoaisEntity.equals(null)){
            throw new Exception("Candidato não encontrado na base de dados!");
        }
        dadosPessoaisEntity.setCidade(cidade);
        dadosPessoaisRepository.save(dadosPessoaisEntity);
    }
}
