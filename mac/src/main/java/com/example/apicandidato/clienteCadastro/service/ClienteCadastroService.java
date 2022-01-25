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
import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.ferramenta.service.FerramentaService;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.framework.service.FrameworkService;
import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.projetos.model.ProjetoEntity;
import com.example.apicandidato.projetos.service.ProjetoService;
import com.example.apicandidato.registroNacional.model.RegistroEntity;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.registroNacional.service.RegistroNacionalService;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCadastroService {
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private DadosAdicionaisService dadosAdicionaisService;
    @Autowired
    private DadosPessoaisService dadosPessoaisService;
    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private HabilidadesService habilidadesService;
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private RegistroNacionalService registroNacionalService;

    public ClienteCadastroSaida criar(ClienteCadastroEntrada entrada, Long idCliente) throws Exception {
        CandidatoSaida candidatoSaida = candidatoService.atualizarCadastro(idCliente, 1);

        DadosAdicionaisSaida dadosAdicionaisSaida = dadosAdicionaisService.criar(entrada.getDadosAdicionais(), idCliente);
        DadosPessoaisSaida dadosPessoaisSaida = dadosPessoaisService.criar(entrada.getDadosPessoais(), idCliente, dadosAdicionaisSaida.getCidade());
        RegistroSaida registroSaida = registroNacionalService.criar(entrada.getRegistro(), idCliente);

        return ClienteCadastroMapper.INSTANCE.mapToSaida(candidatoSaida, dadosAdicionaisSaida,
                dadosPessoaisSaida, null, null, registroSaida);
    }

    public ClienteCadastroSaida buscar(Long id) throws Exception {
        CandidatoEntity candidatoEntity = candidatoService.buscarPorId(id);

        DadosAdicionaisEntity dadosAdicionaisEntity = dadosAdicionaisService.buscarPorIdCliente(id);
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisService.buscarPorIdCliente(id);
        RegistroEntity registroEntity = registroNacionalService.buscarPorIdCliente(id);
        List<ExperienciaEntity> experiencias = experienciaService.buscarPorIdCliente(id);
        HabilidadesEntity habilidade = habilidadesService.buscarPorIdCLiente(id);
        List<ProjetoEntity> projetos = projetoService.buscarPorIdCLiente(id);
        List<TecnologiaEntity> tecnologias = candidatoEntity.getTecnologias();
        List<FrameworkEntity> frameworks = candidatoEntity.getFrameworks();
        List<FerramentaEntity> ferramentas = candidatoEntity.getFerramentas();


        return ClienteCadastroMapper.INSTANCE.mapToSaidaFromEntity(candidatoEntity, dadosAdicionaisEntity, dadosPessoaisEntity,
                registroEntity, experiencias, habilidade, projetos, tecnologias, frameworks, ferramentas);
    }

}
