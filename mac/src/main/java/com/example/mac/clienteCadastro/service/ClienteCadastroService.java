package com.example.mac.clienteCadastro.service;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.clienteCadastro.mapper.ClienteCadastroMapper;
import com.example.mac.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.mac.clienteCadastro.model.ClienteCadastroSaida;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosAdicionais.service.DadosAdicionaisService;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.experiencia.model.ExperienciaEntity;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.experiencia.service.ExperienciaService;
import com.example.mac.habilidades.model.HabilidadesEntity;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.habilidades.service.HabilidadesService;
import com.example.mac.registroNacional.model.RegistroEntity;
import com.example.mac.registroNacional.model.RegistroSaida;
import com.example.mac.registroNacional.service.RegistroNacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteCadastroService {
    @Autowired
    ClienteService clienteService;
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
        ClienteSaida clienteSaida = clienteService.atualizarPrimeiroAcesso(idCliente);
        DadosAdicionaisSaida dadosAdicionaisSaida = dadosAdicionaisService.criar(entrada.getDadosAdicionais(),idCliente);
        DadosPessoaisSaida dadosPessoaisSaida = dadosPessoaisService.criar(entrada.getDadosPessoais(),idCliente,dadosAdicionaisSaida.getCidade());
        ExperienciaSaida experienciaSaida = experienciaService.criar(entrada.getExperiencia(),idCliente);
        HabilidadesSaida habilidadesSaida = habilidadesService.criar(entrada.getHabilidades(),idCliente,dadosPessoaisSaida.getNomeCompleto());
        RegistroSaida registroSaida = registroNacionalService.criar(entrada.getRegistro(),idCliente );

        return ClienteCadastroMapper.INSTANCE.mapToSaida(clienteSaida,dadosAdicionaisSaida,
                dadosPessoaisSaida,experienciaSaida,habilidadesSaida,registroSaida);
    }

    public ClienteCadastroSaida buscar(Long id) throws Exception {
        ClienteEntity clienteEntity = clienteService.buscarEVerificarExistenciaClientePorIdVaga(id);
        DadosAdicionaisEntity dadosAdicionaisEntity = dadosAdicionaisService.buscarPorIdCliente(clienteEntity.getId());
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisService.buscarPorIdCliente(clienteEntity.getId());
        ExperienciaEntity experienciaEntity = experienciaService.buscarPorIdCliente(clienteEntity.getId());
        HabilidadesEntity habilidadesEntity = habilidadesService.buscarPorIdCLiente(clienteEntity.getId());
        RegistroEntity registroEntity = registroNacionalService.buscarPorIdCliente(clienteEntity.getId());

        return ClienteCadastroMapper.INSTANCE.mapToSaidaFromEntity(clienteEntity,dadosAdicionaisEntity,
                dadosPessoaisEntity,experienciaEntity,habilidadesEntity,registroEntity);
    }

}
