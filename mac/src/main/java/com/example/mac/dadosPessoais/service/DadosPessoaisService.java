package com.example.mac.dadosPessoais.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.repository.DadosPessoaisRepository;
import com.example.mac.exception.MyException;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.service.HabilidadesService;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class DadosPessoaisService {
    @Autowired
    DadosPessoaisRepository dadosPessoaisRepository;
    @Autowired
    HabilidadesService habilidadesService;
    @Autowired
    ClienteService clienteService;


    public DadosPessoaisSaida criar(DadosPessoaisEntrada dadosPessoais,Long id,String cidade) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity = DadosPessoaisMapper.INSTANCE.mapToEntity(dadosPessoais);

        dadosPessoaisEntity.setIdUsuario(id);
        dadosPessoaisEntity.setCidade(cidade);

        try {
            dadosPessoaisRepository.save(dadosPessoaisEntity);
        }catch (DataIntegrityViolationException e){
            String mensagem = pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

        return DadosPessoaisMapper.INSTANCE.mapToSaida(dadosPessoaisEntity);
    }

    public String dataParaDDMMAAA(String data){
        LocalDate localDate = LocalDate.parse(data);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data = dateTimeFormatter.format(localDate);

        return data;
    }

    public String pegarOCampoComIdUnique(String mensagem){
        int inicio=0,fim=0,i=0;
        boolean verificador = true;
        while(verificador == true){
            String letraAtual = String.valueOf(mensagem.charAt(i));
            if(letraAtual.equals("(")) inicio = i+1;
            if(letraAtual.equals(")")) fim = i;
            i++;

            if(inicio != 0 && fim !=0) verificador = false;
        }
        String res = mensagem.substring(inicio,fim);

        return res;
    }

    public DadosPessoaisEntity buscarPorIdCliente(long id) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity =  dadosPessoaisRepository.findByIdUsuario(id);

        if(dadosPessoaisEntity == null){
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

        try {
            dadosPessoaisRepository.save(dadosPessoaisEntity);
        }catch (DataIntegrityViolationException e){
            String mensagem = pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) "+mensagem);
        }

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

        if(dadosPessoaisEntity == null){
            throw new Exception("Candidato não encontrado na base de dados!");
        }
        dadosPessoaisEntity.setCidade(cidade);
        dadosPessoaisRepository.save(dadosPessoaisEntity);
    }

    public String buscarEmailUsuarioPorId(Long idUsuario) throws Exception {
        Object dadosPessoaisEntity = clienteService.buscarEmailUsuarioPorId(idUsuario);

        if(dadosPessoaisEntity == null){
            throw new Exception("Não foi possivel localizar o e-mail do usuario!");
        }

        return dadosPessoaisEntity.toString();
    }

    public String buscarEmailUsuarioPorIdNovaVaga(long idUsuario) throws Exception {
        Object dadosPessoaisEntity = clienteService.buscarEmailUsuarioPorId(idUsuario);

        if(dadosPessoaisEntity == null){
            throw new Exception("Não foi possivel localizar o e-mail do usuario! Pois o mesmo ainda não fez o cadastro completo.");
        }

        String emailUsuario = dadosPessoaisEntity.toString();

        return emailUsuario;
    }

    public ClienteEntity buscarEVerificarExistenciaClientePorCpf(String cpf) throws Exception {
        String resultado = dadosPessoaisRepository.getCandidatoByCpf(cpf);

        if(resultado == null) {
            throw new Exception("CPF não encontrado");
        }

        String[] split = resultado.split(",");
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(Long.parseLong(split[0]));
        clienteEntity.setNome(split[1]);

        return clienteEntity;
    }
}
