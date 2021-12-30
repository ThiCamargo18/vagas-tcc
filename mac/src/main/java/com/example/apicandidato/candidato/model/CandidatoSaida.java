package com.example.apicandidato.candidato.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoSaida {
    private Long id;
    private String email;
    private String senha;
    private String login;
    private String nome;
    private Boolean cadastroBasicoRealizado;
    private Boolean cadastroAdicionalRealizado;
    private String situacao;
}
