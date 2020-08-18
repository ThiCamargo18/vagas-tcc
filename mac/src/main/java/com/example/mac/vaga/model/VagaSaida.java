package com.example.mac.vaga.model;

import com.example.mac.enums.CategoriaEnum;
import com.example.mac.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private Experiencia experiencia;
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
    private Long idEmpresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<String> beneficios) {
        this.beneficios = beneficios;
    }

    public List<String> getDescricaoHabilidades() {
        return descricaoHabilidades;
    }

    public void setDescricaoHabilidades(List<String> descricaoHabilidades) {
        this.descricaoHabilidades = descricaoHabilidades;
    }

    public Integer getNumeroVagasEncontradas() {
        return numeroVagasEncontradas;
    }

    public void setNumeroVagasEncontradas(Integer numeroVagasEncontradas) {
        this.numeroVagasEncontradas = numeroVagasEncontradas;
    }
}
