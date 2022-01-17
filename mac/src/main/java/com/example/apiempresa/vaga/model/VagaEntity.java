package com.example.apiempresa.vaga.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
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

    @Lob
    @Column(name = "responsabilidade")
    private String responsabilidade;

    @Column(name = "salario")
    private String salario;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TecnologiaEntity> tecnologias;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<FrameworkEntity> frameworks;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<FerramentaEntity> ferramentas;
}

