package com.example.apicandidato.cargo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cargo")
public class CargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TecnologiaEntity> tecnologias;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<FrameworkEntity> frameworks;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<FerramentaEntity> ferramentas;
}
