package com.example.apicandidato.clienteCadastro.model;

import com.example.apicandidato.candidato.model.CandidatoEntrada;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.habilidades.model.HabilidadesEntrada;
import com.example.apicandidato.registroNacional.model.RegistroEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCadastroEntrada {
    CandidatoEntrada cliente;
    DadosAdicionaisEntrada dadosAdicionais;
    DadosPessoaisEntrada dadosPessoais;
    ExperienciaEntrada experiencia;
    HabilidadesEntrada habilidades;
    RegistroEntrada registro;
}
