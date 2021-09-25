package com.example.mac.clienteCadastro.model;

import com.example.mac.candidato.model.CandidatoSaida;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.registroNacional.model.RegistroSaida;
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
