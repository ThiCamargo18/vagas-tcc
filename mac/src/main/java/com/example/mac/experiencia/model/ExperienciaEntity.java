package com.example.mac.experiencia.model;

import com.example.mac.enums.CategoriaEnum;
import com.example.mac.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "experiencia")
public class ExperienciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "possui")
    @Enumerated(EnumType.STRING)
    private Experiencia possui;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "dataInicio")
    private String dataInicio;

    @Column(name = "dataFim")
    private String dataFim;

    @Column(name = "salario")
    private String salario;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "idUsuario")
    private Long idUsuario;
}
