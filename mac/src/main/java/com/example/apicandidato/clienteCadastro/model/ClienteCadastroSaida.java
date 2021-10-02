package com.example.apicandidato.clienteCadastro.model;

import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCadastroSaida {
    CandidatoSaida cliente;
    DadosAdicionaisSaida dadosAdicionais;
    DadosPessoaisSaida dadosPessoais;
    ExperienciaSaida experiencia;
    HabilidadesSaida habilidades;
    RegistroSaida registro;
}
