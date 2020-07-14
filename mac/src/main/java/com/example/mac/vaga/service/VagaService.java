package com.example.mac.vaga.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.service.ClienteService;
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
import org.springframework.stereotype.Service;

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

    public VagaSaida criar(VagaEntrada entrada) throws Exception {
        VagaEntity vagaEntity = VagaMapper.INSTANCE.mapToEntity(entrada);

        if(empresaService.verificarSeJaFoiCriadoAEmpresa().equals(false)){
            throw new Exception("Cadastre sua empresa antes de publicar uma vaga");
        }
        vagaEntity.setNumeroInscritos(0);
        vagaEntity.setIdEmpresa(1L);

        vagaRepository.save(vagaEntity);

        return VagaMapper.INSTANCE.mapToSaida(vagaEntity);
    }

    public List<VagaSaida> listar() {
        List<VagaEntity> vagaEntities = vagaRepository.findAll();

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }

    public Boolean atualizarNumeroInscritosVaga(Long id) throws Exception {
        Optional<VagaEntity> vagaEntity = vagaRepository.findById(id);

        if(!vagaEntity.isPresent()){
            return false;
        }

        VagaEntity vaga = vagaEntity.get();
        vaga.setNumeroInscritos(vaga.getNumeroInscritos()+1);
        vagaRepository.save(vaga);

        return true;
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


    public List<DadosPessoaisSaida> buscarInscritosPorVaga(Long id) {
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

        if(vagaEntities.isEmpty()){
            throw new Exception("Nenhuma vaga com esse nome, busque novamente");
        }

        return VagaMapper.INSTANCE.mapToSaidaList(vagaEntities);
    }
}
