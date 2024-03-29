package com.example.apicandidato.clienteCadastro.mapper;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntity;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.experiencia.model.ExperienciaEntity;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.habilidades.model.HabilidadesEntity;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.projetos.model.ProjetoEntity;
import com.example.apicandidato.registroNacional.model.RegistroEntity;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteCadastroMapper {
    ClienteCadastroMapper INSTANCE = Mappers.getMapper(ClienteCadastroMapper.class);

    @Mappings({
            @Mapping(source = "candidatoSaida",target = "cliente"),
            @Mapping(source = "dadosAdicionaisSaida",target = "dadosAdicionais"),
            @Mapping(source = "dadosPessoaisSaida",target = "dadosPessoais"),
            @Mapping(source = "registroSaida",target = "registro"),
    })
    ClienteCadastroSaida mapToSaida(CandidatoSaida candidatoSaida, DadosAdicionaisSaida dadosAdicionaisSaida,
                                    DadosPessoaisSaida dadosPessoaisSaida, ExperienciaSaida experienciaSaida,
                                    HabilidadesSaida habilidadesSaida, RegistroSaida registroSaida);
    @Mappings({
            @Mapping(source = "candidatoEntity",target = "cliente"),
            @Mapping(source = "dadosAdicionaisEntity",target = "dadosAdicionais"),
            @Mapping(source = "dadosPessoaisEntity",target = "dadosPessoais"),
            @Mapping(source = "registroEntity",target = "registro"),
    })
    ClienteCadastroSaida mapToSaidaFromEntity(CandidatoEntity candidatoEntity, DadosAdicionaisEntity dadosAdicionaisEntity,
                                              DadosPessoaisEntity dadosPessoaisEntity, RegistroEntity registroEntity,
                                              List<ExperienciaEntity> experiencias, HabilidadesEntity habilidade,
                                              List<ProjetoEntity> projetos, List<TecnologiaEntity> tecnologias,
                                              List<FrameworkEntity> frameworks, List<FerramentaEntity> ferramentas);
}
