package com.example.mac.habilidades.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.habilidades.mapper.HabilidadesMapper;
import com.example.mac.habilidades.model.HabilidadesEntity;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.habilidades.repository.HabilidadesRepository;
import com.example.mac.vaga.model.VagaEntity;
import com.example.mac.vaga.repository.VagaRepository;
import com.example.mac.vaga.service.VagaService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HabilidadesService {
    @Autowired
    HabilidadesRepository habilidadesRepository;
    @Autowired
    VagaRepository vagaRepository;


    public HabilidadesSaida criar(HabilidadesEntrada habilidades,Long id,String nomeCompleto) {
        HabilidadesEntity habilidadesEntity = HabilidadesMapper.INSTANCE.mapToEntity(habilidades);
        habilidadesEntity.setIdUsuario(id);
        habilidadesEntity.setNomeUsuario(nomeCompleto);

        habilidadesRepository.save(habilidadesEntity);

        return HabilidadesMapper.INSTANCE.mapToSaida(habilidadesEntity);
    }

    public HabilidadesEntity buscarPorIdCLiente(long id) {
        return habilidadesRepository.findByIdUsuario(id);
    }

    public HabilidadesSaida atualizar(Long id, HabilidadesEntrada habilidadesEntrada) throws Exception {
        HabilidadesEntity habilidadesEntityBanco = habilidadesRepository.findByIdUsuario(id);
        HabilidadesEntity habilidadesEntity = HabilidadesMapper.INSTANCE.mapToEntity(habilidadesEntrada);

        if(habilidadesEntity.equals(null)){
            throw new Exception("Candidato não encontrado! Faça login novamente.");
        }

        habilidadesEntity.setId(habilidadesEntityBanco.getId());
        habilidadesEntity.setIdUsuario(habilidadesEntityBanco.getIdUsuario());
        habilidadesEntity.setNomeUsuario(habilidadesEntrada.getNomeUsuario());
        habilidadesRepository.save(habilidadesEntity);

        return HabilidadesMapper.INSTANCE.mapToSaida(habilidadesEntity);
    }

    public List<HabilidadesSaida> filtrarPorResumoProfissional(Long idVaga,String param,VagaEntity vaga) throws Exception {

        List<HabilidadesEntity> habilidades = new ArrayList<>();
        List<HabilidadesEntity> habilidadesSelecionadas = new ArrayList<>();


        List<ClienteEntity> clienteEntity = vaga.getClientes();

        for(ClienteEntity cliente: clienteEntity){
            HabilidadesEntity habilidadesEntityOptional = habilidadesRepository.findByIdUsuario(cliente.getId());
            habilidades.add(habilidadesEntityOptional);
        }

        String[] res = param.split(",");

        List<String> listaParametros = Arrays.asList(res);

        for(int i=0;i<habilidades.size();i++){
            String resumoProfissional = habilidades.get(i).getResumoProfissional().toUpperCase();
            int contador = 0;
            for(int j=0;j<listaParametros.size();j++){
                String parametro = listaParametros.get(j).toUpperCase();

                if(resumoProfissional.contains(parametro)){
                    contador++;
                }
                if(contador == listaParametros.size()){
                    habilidadesSelecionadas.add(habilidades.get(i));
                }
            }

        }
        return HabilidadesMapper.INSTANCE.mapToSaidaList(habilidadesSelecionadas);
    }
}
