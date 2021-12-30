package com.example.apicandidato.projetos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoEntrada {
    private String nome;
    private String descricao;
    private Long idUsuario;
}
