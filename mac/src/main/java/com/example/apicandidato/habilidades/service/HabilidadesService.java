package com.example.apicandidato.habilidades.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.habilidades.mapper.HabilidadesMapper;
import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import com.example.apicandidato.habilidades.model.HabilidadesEntrada;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.habilidades.repository.HabilidadesRepository;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HabilidadesService {
    @Autowired
    HabilidadesRepository habilidadesRepository;
    @Autowired
    VagaRepository vagaRepository;

    public HabilidadesSaida atualizar(HabilidadesEntrada habilidadesEntrada, Long idUsuario) {
        HabilidadesEntity habilidadeSalva = habilidadesRepository.findByIdUsuario(idUsuario);

        HabilidadesEntity habilidadesEntity = HabilidadesMapper.INSTANCE.mapToEntity(habilidadesEntrada, idUsuario);

        if (habilidadeSalva != null)
            habilidadesEntity.setId(habilidadeSalva.getId());

        habilidadesRepository.save(habilidadesEntity);

        return HabilidadesMapper.INSTANCE.mapToSaida(habilidadesEntity);
    }

    public HabilidadesEntity buscarPorIdCLiente(long id) {
        return habilidadesRepository.findByIdUsuario(id);
    }

    public List<HabilidadesSaida> filtrarPorResumoProfissional(Long idVaga,String param,VagaEntity vaga) throws Exception {

        List<HabilidadesEntity> habilidades = new ArrayList<>();
        List<HabilidadesEntity> habilidadesSelecionadas = new ArrayList<>();


        List<CandidatoEntity> candidatoEntity = vaga.getClientes();

        for(CandidatoEntity cliente: candidatoEntity){
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
