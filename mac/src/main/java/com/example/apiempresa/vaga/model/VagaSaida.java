package com.example.apiempresa.vaga.model;

import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private String responsabilidade;
    private String salario;
    private String observacao;
    private String turno;
    private String dataLimite;
    private Integer numeroInscritos;
    private Integer numeroVagasEncontradas;
    private String inscrito = "NAO";
    private String status;
    private Long idEmpresa;
    private List<TecnologiaEntity> tecnologias;
    private List<FrameworkEntity> frameworks;
    private List<FerramentaEntity> ferramentas;
}
