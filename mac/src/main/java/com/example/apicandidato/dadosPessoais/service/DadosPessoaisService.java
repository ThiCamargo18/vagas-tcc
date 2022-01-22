package com.example.apicandidato.dadosPessoais.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.repository.DadosPessoaisRepository;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosPessoaisService {
    @Autowired
    DadosPessoaisRepository dadosPessoaisRepository;
    @Autowired
    HabilidadesService habilidadesService;
    @Autowired
    CandidatoService candidatoService;


    public DadosPessoaisSaida criar(DadosPessoaisEntrada dadosPessoais, Long id, String cidade) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity = DadosPessoaisMapper.INSTANCE.mapToEntity(dadosPessoais);

        dadosPessoaisEntity.setIdCandidato(id);
        dadosPessoaisEntity.setCidade(cidade);
        dadosPessoaisEntity.setIdade(dadosPessoaisEntity.getDataNascimento());

        try {
            dadosPessoaisRepository.save(dadosPessoaisEntity);
        } catch (DataIntegrityViolationException e) {
            String mensagem = pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) " + mensagem);
        }

        return DadosPessoaisMapper.INSTANCE.mapToSaida(dadosPessoaisEntity);
    }

    public String pegarOCampoComIdUnique(String mensagem) {
        int inicio = 0, fim = 0, i = 0;
        boolean verificador = true;
        while (verificador) {
            String letraAtual = String.valueOf(mensagem.charAt(i));
            if (letraAtual.equals("(")) inicio = i + 1;
            if (letraAtual.equals(")")) fim = i;
            i++;

            if (inicio != 0 && fim != 0) verificador = false;
        }
        String res = mensagem.substring(inicio, fim);

        return res;
    }

    public DadosPessoaisEntity buscarPorIdCliente(long id) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisRepository.findByIdCandidato(id);

        if (dadosPessoaisEntity == null) {
            throw new Exception("Candidato não encontrado!");
        }

        return dadosPessoaisEntity;
    }

    public DadosPessoaisSaida atualizar(DadosPessoaisEntrada dadosPessoaisEntrada, Long id) throws Exception {
        DadosPessoaisEntity dadosRetornoEntity = dadosPessoaisRepository.findByIdCandidato(id);

        DadosPessoaisEntity dadosPessoaisEntity = DadosPessoaisMapper.INSTANCE.mapToEntity(dadosPessoaisEntrada);
        dadosPessoaisEntity.setId(dadosRetornoEntity.getId());
        dadosPessoaisEntity.setIdCandidato(dadosRetornoEntity.getIdCandidato());

        try {
            dadosPessoaisRepository.save(dadosPessoaisEntity);
        } catch (DataIntegrityViolationException e) {
            String mensagem = pegarOCampoComIdUnique(e.getMessage());

            throw new Exception("Já existe um candidato com o mesmo(a) " + mensagem);
        }

        return DadosPessoaisMapper.INSTANCE.mapToSaida(dadosPessoaisEntity);
    }

    public List<DadosPessoaisSaida> filtrar(DadosPessoaisEntity entrada) {
        Example<DadosPessoaisEntity> example = Example.of(entrada,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        List<DadosPessoaisEntity> saida = dadosPessoaisRepository.findAll(example);

        return DadosPessoaisMapper.INSTANCE.mapToSaidaList(saida);
    }

    public List<DadosPessoaisEntity> buscarPorNome(String nome) {
        return dadosPessoaisRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }

    public void atualizarCidade(Object o, Long id, String cidade) throws Exception {
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisRepository.findByIdCandidato(id);

        if (dadosPessoaisEntity == null) {
            throw new Exception("Candidato não encontrado na base de dados!");
        }
        dadosPessoaisEntity.setCidade(cidade);
        dadosPessoaisRepository.save(dadosPessoaisEntity);
    }

    public String buscarEmailUsuarioPorId(Long idUsuario) throws Exception {
        Object dadosPessoaisEntity = candidatoService.buscarEmailUsuarioPorId(idUsuario);

        if (dadosPessoaisEntity == null) {
            throw new Exception("Não foi possivel localizar o e-mail do usuario!");
        }

        return dadosPessoaisEntity.toString();
    }

    public String buscarEmailUsuarioPorIdNovaVaga(long idUsuario) throws Exception {
        Object dadosPessoaisEntity = candidatoService.buscarEmailUsuarioPorId(idUsuario);

        if (dadosPessoaisEntity == null) {
            throw new Exception("Não foi possivel localizar o e-mail do usuario! Pois o mesmo ainda não fez o cadastro completo.");
        }

        String emailUsuario = dadosPessoaisEntity.toString();

        return emailUsuario;
    }

    public CandidatoEntity buscarEVerificarExistenciaClientePorCpf(String cpf) throws Exception {
        String resultado = dadosPessoaisRepository.getCandidatoByCpf(cpf);

        if (resultado == null) {
            throw new Exception("CPF não encontrado");
        }

        String[] split = resultado.split(",");
        CandidatoEntity candidatoEntity = new CandidatoEntity();
        candidatoEntity.setId(Long.parseLong(split[0]));
        candidatoEntity.setNome(split[1]);

        return candidatoEntity;
    }

    public List<DadosPessoaisSaida> findAllByIdCandidato(List<Long> idCandidatos) {
        List<DadosPessoaisEntity> dadosPessoaisEntityList = dadosPessoaisRepository.findAllByIdCandidatoIn(idCandidatos);

        return DadosPessoaisMapper.INSTANCE.mapToSaidaList(dadosPessoaisEntityList);
    }
}
