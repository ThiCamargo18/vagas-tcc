package com.example.apiempresa.vaga.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.exception.MyException;
import com.example.apicandidato.ferramenta.service.FerramentaService;
import com.example.apicandidato.framework.service.FrameworkService;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import com.example.apiempresa.empresa.service.EmpresaService;
import com.example.apiempresa.vaga.mapper.VagaMapper;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaEntrada;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private DadosPessoaisService dadosPessoaisService;
    @Autowired
    private TecnologiaService tecnologiaService;
    @Autowired
    private FerramentaService ferramentaService;
    @Autowired
    private FrameworkService frameworkService;

    public VagaEntity criar(VagaEntrada entrada, Long idEmpresa) throws MyException {
        VagaEntity vagaEntity = VagaMapper.INSTANCE.mapToEntity(entrada);

        vagaEntity.setStatus("CRIADA");
        vagaEntity.setNumeroInscritos(0);
        vagaEntity.setIdEmpresa(idEmpresa);
        vagaEntity.setDataLimite(dataParaDDMMAAA(vagaEntity.getDataLimite()));

        return vagaRepository.save(vagaEntity);
    }

    public List<VagaSaida> listar(Long idEmpresa) {
        List<VagaEntity> vagaEntities = vagaRepository.findAllByIdEmpresa(idEmpresa);

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public String deletar(Long id) {
        vagaRepository.deleteById(id);

        return "concluido";
    }

    public List<DadosPessoaisSaida> buscarInscritosPorVaga(Long id) throws Exception {
        List<DadosPessoaisEntity> dadosPessoaisEntityList = new ArrayList<>();
        Optional<VagaEntity> vagaEntity = vagaRepository.findById(id);
        List<CandidatoEntity> candidatoEntityList = vagaEntity.get().getClientes();

        for(CandidatoEntity cliente : candidatoEntityList){
            DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisService.buscarPorIdCliente(cliente.getId());
            dadosPessoaisEntityList.add(dadosPessoaisEntity);
        }

        return DadosPessoaisMapper.INSTANCE.mapToSaidaList(dadosPessoaisEntityList);
    }

    public VagaSaida buscarVaga(Long id) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(id);

        if(vagaEntityOptional.isEmpty()) throw new Exception("Vaga não encontrada!");

        return VagaMapper.INSTANCE.mapToSaida(vagaEntityOptional.get());
    }

    public List<VagaSaida> buscarPorNome(String nome) throws Exception {
        List<VagaEntity> vagaEntities = vagaRepository.findByTituloContainingIgnoreCase(nome);

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public List<VagaEntity> filtrar(VagaEntity vagaEntity) {
        Example<VagaEntity> example = Example.of( vagaEntity,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) );
        return vagaRepository.findAll(example);
    }

    public void atualizar(VagaEntrada vagaEntrada) {
        VagaEntity vagaEntity = VagaMapper.INSTANCE.mapToEntity(vagaEntrada);

        vagaEntity.setIdEmpresa(1L); //TODO
        vagaEntity.setDataLimite(dataParaDDMMAAA(vagaEntity.getDataLimite()));

        vagaRepository.save(vagaEntity);
    }

    public void atualizarStatus(Long idVaga) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if(vagaEntityOptional.isEmpty()) throw new Exception("Vaga não encontrada, busque novamente");

        VagaEntity vagaEntity = vagaEntityOptional.get();

        if(vagaEntity.getStatus().equals("ATIVA"))
            vagaEntity.setStatus("OCULTA");
        else
            vagaEntity.setStatus("ATIVA");

        vagaRepository.save(vagaEntity);
    }

    public String dataParaDDMMAAA(String data){
        if(data.equals("")) return data;

        String validador1 = String.valueOf(data.charAt(2));
        String validador2 = String.valueOf(data.charAt(4));

        if(validador1.equals("/") || validador2.equals("/")) return data; //Já está formatado, se entrar no localDate quebra

        LocalDate localDate = LocalDate.parse(data);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data = dateTimeFormatter.format(localDate);

        return data;
    }

    public void validarSePertenceEmpresa(Long idVaga, Long idEmpresa) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if (vagaEntityOptional.isEmpty()) throw new Exception("Vaga nao pertence a essa empresa");

        if (!vagaEntityOptional.get().getIdEmpresa().equals(idEmpresa)) throw new Exception("Vaga nao pertence a essa empresa");
    }

    public void atualizarInfomacoesAdicionais(VagaEntrada vagaEntrada, Long idVaga) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if (vagaEntityOptional.isEmpty()) throw new Exception("Vaga Nao Encontrada");

        VagaEntity vagaEntity = vagaEntityOptional.get();

        vagaEntity.setStatus("ATIVA");
        vagaEntity.setTecnologias(tecnologiaService.findAllById(vagaEntrada.getTecnologia()));
        vagaEntity.setFrameworks(frameworkService.findAllById(vagaEntrada.getFramework()));
        vagaEntity.setFerramentas(ferramentaService.findAllById(vagaEntrada.getFerramenta()));

        vagaRepository.save(vagaEntity);
    }
}
