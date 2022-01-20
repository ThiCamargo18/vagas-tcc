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
public class VagaEntrada {
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
    private String status;
    private Long idEmpresa;
    private List<Long> tecnologia = new ArrayList<>();
    private List<Long> framework = new ArrayList<>();
    private List<Long> ferramenta = new ArrayList<>();
}
