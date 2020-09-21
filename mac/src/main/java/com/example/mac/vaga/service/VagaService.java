package com.example.mac.vaga.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.exception.MyException;
import com.example.mac.dadosPessoais.mapper.DadosPessoaisMapper;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.empresa.service.EmpresaService;
import com.example.mac.vaga.mapper.VagaMapper;
import com.example.mac.vaga.model.VagaEntity;
import com.example.mac.vaga.model.VagaEntrada;
import com.example.mac.vaga.model.VagaSaida;
import com.example.mac.vaga.repository.VagaRepository;
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
    VagaRepository vagaRepository;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    public void criar(VagaEntrada entrada) throws MyException {
        VagaEntity vagaEntity = VagaMapper.INSTANCE.mapToEntity(entrada);

        vagaEntity.setStatus("ATIVA");
        vagaEntity.setNumeroInscritos(0);
        vagaEntity.setIdEmpresa(1L);
        vagaEntity.setDataLimite(dataParaDDMMAAA(vagaEntity.getDataLimite()));

        vagaRepository.save(vagaEntity);
    }

    public List<VagaSaida> listar() {
        List<VagaEntity> vagaEntities = vagaRepository.findAll();

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public VagaSaida inscrever(Long idUsuario, Long idVaga) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if(!vagaEntityOptional.isPresent()){
            throw new Exception("Vaga não encontrada");
        }

        ClienteEntity clienteEntity = clienteService.buscarEVerificarExistenciaClientePorIdVaga(idUsuario);
        VagaEntity vagaEntity = vagaEntityOptional.get();

        List<ClienteEntity> clientesCadastrados = vagaEntity.getClientes();

        for(ClienteEntity cliente : clientesCadastrados){
            if(cliente.getId()==clienteEntity.getId()){
                throw new Exception("Você já se inscreveu nessa vaga!");
            }
        }

        clientesCadastrados.add(clienteEntity);
        vagaEntity.setClientes(clientesCadastrados);
        vagaEntity.setNumeroInscritos(vagaEntity.getNumeroInscritos()+1);
        vagaRepository.save(vagaEntity);

        return VagaMapper.INSTANCE.mapToSaida(vagaEntity);
    }

    public String deletar(Long id) {
        vagaRepository.deleteById(id);

        return "concluido";
    }


    public List<DadosPessoaisSaida> buscarInscritosPorVaga(Long id) throws Exception {
        List<DadosPessoaisEntity> dadosPessoaisEntityList = new ArrayList<>();
        Optional<VagaEntity> vagaEntity = vagaRepository.findById(id);
        List<ClienteEntity> clienteEntityList = vagaEntity.get().getClientes();

        for(ClienteEntity cliente : clienteEntityList){
            DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisService.buscarPorIdCliente(cliente.getId());
            dadosPessoaisEntityList.add(dadosPessoaisEntity);
        }

        return DadosPessoaisMapper.INSTANCE.mapToSaidaList(dadosPessoaisEntityList);
    }

    public VagaSaida buscarVaga(Long id) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(id);

        if(!vagaEntityOptional.isPresent()){
            throw new Exception("Vaga não encontrada!");
        }

        return VagaMapper.INSTANCE.mapToSaida(vagaEntityOptional.get());
    }

    public List<VagaSaida> buscarPorNome(String nome) throws Exception {
        List<VagaEntity> vagaEntities = vagaRepository.findByTituloContainingIgnoreCase(nome);

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public List<VagaEntity> filtrar(VagaEntity vagaEntity) {
        Example example = Example.of( vagaEntity,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) );
        return vagaRepository.findAll(example);
    }

    public void atualizar(VagaEntrada vagaEntrada) {
        VagaEntity vagaEntity = VagaMapper.INSTANCE.mapToEntity(vagaEntrada);

        vagaEntity.setIdEmpresa(1l);
        vagaEntity.setDataLimite(dataParaDDMMAAA(vagaEntity.getDataLimite()));

        vagaRepository.save(vagaEntity);
    }

    public String atualizarStatus(Long idVaga) throws Exception {
        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if(!vagaEntityOptional.isPresent()){
            throw new Exception("Vaga não encontrada, busque novamente");
        }
        VagaEntity vagaEntity = vagaEntityOptional.get();
        if(vagaEntity.getStatus().equals("ATIVA")) vagaEntity.setStatus("OCULTA");
        else vagaEntity.setStatus("ATIVA");

        vagaRepository.save(vagaEntity);

        return "sucesso";
    }

    public List<VagaSaida> listarVagasAtivas() {
        List<VagaEntity> vagaEntityList = vagaRepository.findAllByStatus("ATIVA");

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntityList);
    }

    public VagaSaida validarInscricao(VagaSaida vagaSaida,Long idUsuario) throws Exception {
        ClienteEntity clienteEntity = clienteService.buscarEVerificarExistenciaClientePorIdVaga(idUsuario);
        VagaEntity vagaEntity = vagaRepository.findById(vagaSaida.getId()).get();

        List<ClienteEntity> clientesCadastrados = vagaEntity.getClientes();

        for(ClienteEntity cliente : clientesCadastrados){
            if(cliente.getId()==clienteEntity.getId()){
                vagaSaida.setInscrito("SIM");
            }
        }

        return vagaSaida;
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
}
