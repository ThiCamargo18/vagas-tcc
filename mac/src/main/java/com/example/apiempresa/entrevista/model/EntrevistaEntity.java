package com.example.apiempresa.entrevista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "entrevista")
public class EntrevistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario")
    private String horario;

    @Column(name = "data")
    private String data;

    @Column(name = "local")
    private String local;

    @Column(name = "presenca")
    private String vizualizado;

    @Column(name = "dataHoraInclusaoRegistro")
    private LocalDateTime dataHoraInclusaoRegistro;

    @Column(name = "idCandidato")
    private Long idCandidato;

    @Column(name = "idEmpresa")
    private Long idEmpresa;
}
