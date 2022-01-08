package com.example.apicandidato.clienteCadastro.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.clienteCadastro.mapper.ClienteCadastroMapper;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosAdicionais.service.DadosAdicionaisService;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.registroNacional.model.RegistroEntity;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.registroNacional.service.RegistroNacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteCadastroService {
    @Autowired
    CandidatoService candidatoService;
    @Autowired
    DadosAdicionaisService dadosAdicionaisService;
    @Autowired
    DadosPessoaisService dadosPessoaisService;
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    HabilidadesService habilidadesService;
    @Autowired
    RegistroNacionalService registroNacionalService;

    public ClienteCadastroSaida criar(ClienteCadastroEntrada entrada, Long idCliente) throws Exception {
        CandidatoSaida candidatoSaida = candidatoService.atualizarCadastro(idCliente, 1);
        DadosAdicionaisSaida dadosAdicionaisSaida = dadosAdicionaisService.criar(entrada.getDadosAdicionais(), idCliente);
        DadosPessoaisSaida dadosPessoaisSaida = dadosPessoaisService.criar(entrada.getDadosPessoais(), idCliente, dadosAdicionaisSaida.getCidade());
        RegistroSaida registroSaida = registroNacionalService.criar(entrada.getRegistro(), idCliente);

        return ClienteCadastroMapper.INSTANCE.mapToSaida(candidatoSaida, dadosAdicionaisSaida,
                dadosPessoaisSaida, null, null, registroSaida);
    }

    public ClienteCadastroSaida buscar(Long id) throws Exception {
        CandidatoEntity candidatoEntity = candidatoService.buscarEVerificarExistenciaClientePorIdVaga(id);
        DadosAdicionaisEntity dadosAdicionaisEntity = dadosAdicionaisService.buscarPorIdCliente(candidatoEntity.getId());
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisService.buscarPorIdCliente(candidatoEntity.getId());
//        ExperienciaEntity experienciaEntity = experienciaService.buscarPorIdCliente(candidatoEntity.getId());
        HabilidadesEntity habilidadesEntity = habilidadesService.buscarPorIdCLiente(candidatoEntity.getId());
        RegistroEntity registroEntity = registroNacionalService.buscarPorIdCliente(candidatoEntity.getId());

        return ClienteCadastroMapper.INSTANCE.mapToSaidaFromEntity(candidatoEntity, dadosAdicionaisEntity,
                dadosPessoaisEntity, null, habilidadesEntity, registroEntity);
    }

}
