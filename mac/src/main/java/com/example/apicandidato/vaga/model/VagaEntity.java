package com.example.apicandidato.vaga.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vaga")
public class VagaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "formacao")
    private String formacao;

    @Column(name = "descricaoHabilidades")
    @ElementCollection(targetClass=String.class)
    private List<String> descricaoHabilidades;

    @Lob
    @Column(name = "responsabilidade")
    private String responsabilidade;

    @Column(name = "salario")
    private String salario;

    @Column(name = "beneficios")
    @ElementCollection(targetClass=String.class)
    private List<String> beneficios;

    @Lob
    @Column(name = "observacao")
    private String observacao;

    @Column(name = "turno")
    private String turno;

    @Column(name = "dataLimite")
    private String dataLimite;

    @Column(name = "numeroInscritos")
    private Integer numeroInscritos;

    @Column(name = "idEmpresa")
    private Long idEmpresa;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CandidatoEntity> clientes;
}

