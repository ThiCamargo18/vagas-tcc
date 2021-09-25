package com.example.mac.candidato.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoSessao {
    private Long id;
    private String nome;
    private Boolean primeiroAcesso;
}
