package com.example.apicandidato.habilidades.model;

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

    @Column(name = "idUsuario")
    private Long idCandidato;
}
