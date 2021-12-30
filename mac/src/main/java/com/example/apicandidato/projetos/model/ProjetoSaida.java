package com.example.apicandidato.projetos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoSaida {
    private Long id;
    private String nome;
    private String descricao;
    private Long idUsuario;
}
