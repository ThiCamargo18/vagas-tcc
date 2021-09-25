package com.example.mac.habilidades.model;

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
@Entity(name = "habilidades")
public class HabilidadesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "resumoProfissional")
    private String resumoProfissional;

    @Lob
    @Column(name = "objetivos")
    private String objetivos;

    @Column(name = "descricao")
    @ElementCollection(targetClass=String.class)
    private List<String> descricao;

    @Column(name = "nomeUsuario")
    private String nomeUsuario;

    @Column(name = "idUsuario")
    private Long idUsuario;
}
