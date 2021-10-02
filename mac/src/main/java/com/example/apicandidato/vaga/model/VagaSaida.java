package com.example.apicandidato.vaga.model;

import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VagaSaida {
    private Long id;
    private String titulo;
    private Integer quantidade;
    private String cidade;
    private CategoriaEnum categoria;
    private String formacao;
    private List<String> descricaoHabilidades;
    private String responsabilidade;
    private String salario;
    private List<String> beneficios; //mudar para lista
    private String observacao;
    private String turno;
    private String dataLimite;
    private Integer numeroInscritos;
    private Integer numeroVagasEncontradas;
    private String inscrito = "NAO";
    private String status;
    private Long idEmpresa;
}
