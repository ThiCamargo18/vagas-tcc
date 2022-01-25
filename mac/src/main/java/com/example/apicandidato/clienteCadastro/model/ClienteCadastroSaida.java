package com.example.apicandidato.clienteCadastro.model;

import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.ferramenta.model.FerramentaSaida;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.framework.model.FrameworkSaida;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.projetos.model.ProjetoSaida;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import com.example.apicandidato.tecnologia.model.TecnologiaSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCadastroSaida {
    private CandidatoSaida cliente;
    private DadosAdicionaisSaida dadosAdicionais;
    private DadosPessoaisSaida dadosPessoais;
    private RegistroSaida registro;
    private List<ExperienciaSaida> experiencias;
    private HabilidadesSaida habilidade;
    private List<ProjetoSaida> projetos;
    private List<TecnologiaSaida> tecnologias;
    private List<FrameworkSaida> frameworks;
    private List<FerramentaSaida> ferramentas;
}
