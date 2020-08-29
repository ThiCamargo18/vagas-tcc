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
public class VagaEntrada {
    private Long id;
    private String titulo; //
    private Integer quantidade;//
    private String cidade;//
    private CategoriaEnum categoria; //
    private Experiencia experiencia;
    private String formacao; //
    private List<String> descricaoHabilidades;
    private String responsabilidade;
    private String salario;
    private List<String> beneficios;
    private String observacao;
    private String turno;
    private String dataLimite;
    private Integer numeroInscritos;
    private String status;
    private Long idEmpresa;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
