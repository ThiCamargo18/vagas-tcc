package com.example.apicandidato.experiencia.model;

import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "experiencia")
public class ExperienciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "dataInicio")
    private String dataInicio;

    @Column(name = "dataFim")
    private String dataFim;

    @Column(name = "salario")
    private String salario;

    @Column(name = "nomeEmpresa")
    private String nomeEmpresa;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "idUsuario")
    private Long idUsuario;
}
