package com.example.mac.clienteCadastro.model;

import com.example.mac.candidato.model.CandidatoEntrada;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.mac.experiencia.model.ExperienciaEntrada;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.registroNacional.model.RegistroEntrada;
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
